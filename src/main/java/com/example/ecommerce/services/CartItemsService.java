package com.example.ecommerce.services;
import com.example.ecommerce.dtos.CartItemsDTO;
import com.example.ecommerce.dtos.ItemsForCartDTO;
import com.example.ecommerce.dtos.SubProductDTO;
import com.example.ecommerce.mappers.CartItemsMapper;
import com.example.ecommerce.mappers.SubProductMapper;
import com.example.ecommerce.models.CartItems;
import com.example.ecommerce.models.Customer;
import com.example.ecommerce.models.EntitiesEmbeddedId.CustomerProductId;
import com.example.ecommerce.repositories.CartItemsRepository;
import com.example.ecommerce.repositories.CustomerRepository;
import com.example.ecommerce.repositories.SubProductRepository;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartItemsService implements Serializable {

    private final CartItemsRepository cartItemsRepository;
    private final CartItemsMapper cartItemsMapper;
    private final CustomerRepository customerRepository;
    private final SubProductRepository subProductRepository;


    @JsonIgnore
    private Map<SubProductDTO, Integer> cart = new HashMap<>();

    public void setCartFromDto(Map<SubProductDTO, Integer> cart) {
        this.cart = cart;
    }


    @Autowired
    public CartItemsService(CartItemsRepository cartItemsRepository, CartItemsMapper cartItemsMapper, CustomerRepository customerRepository, SubProductRepository subProductRepository) {
        this.cartItemsMapper=cartItemsMapper;
        this.cartItemsRepository = cartItemsRepository;
        this.customerRepository = customerRepository;
        this.subProductRepository = subProductRepository;

    } 
    public List<CartItemsDTO> getCartByCustomerId(Integer customerId) {
        // Fetch cart items from the repository
        List<CartItems> cartItems = cartItemsRepository.findByCustomerId(customerId);

        // Map the entities to DTOs
        return cartItems.stream()
                .map(cartItemsMapper::toDto)
                .collect(Collectors.toList());
    } 
    @JsonCreator
    public void setCart(@JsonProperty("cartItems") List<ItemsForCartDTO> cartItems) {
        this.cart = cartItems.stream()
                .collect(Collectors.toMap(ItemsForCartDTO::getSubProduct, ItemsForCartDTO::getQuantity));
    }
    public void addCartItem(SubProductDTO subProductDTO){
        if(cart.containsKey(subProductDTO)){
            Integer quantity = cart.get(subProductDTO) + 1;
            cart.put(subProductDTO, quantity);
        }else {
            cart.put(subProductDTO,1);
        }
    }
    public void addCartItem(SubProductDTO subProductDTO, Integer quantity){
        cart.put(subProductDTO, quantity);
    }
    public boolean removeCartItem(SubProductDTO subProductDTO){
        try{cart.remove(subProductDTO); return  true;}catch (Exception e){e.printStackTrace(); return false;}
    }

    public int getTotalQuantity(){
        return cart.values().stream().mapToInt(Integer::intValue).sum();
    }
    public BigDecimal getTotalPrice(){
        return cart.entrySet()
                .stream()
                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    @JsonIgnore
    public Map<SubProductDTO, Integer> getItems(){
        return this.cart;
    }
    public void clear(){
        this.cart.clear();
    }
    public Integer getQuantityOfSubProduct(SubProductDTO subProductDTO){
        if(cart.containsKey(subProductDTO))
            return cart.get(subProductDTO);
        else
            return 0;
    }
    public int getTotalCartItems() {
        return this.cart.size();
    }
    public void updateProductQuantity(SubProductDTO subProductDTO, int quantity) {
        if (cart.containsKey(subProductDTO)) {
            if (quantity > 0) {
                cart.put(subProductDTO, quantity);
            } else {
                cart.remove(subProductDTO);
            }
        }
    }
    @JsonProperty("cartItems")
    public List<ItemsForCartDTO> getCartItemsForJson() {
        return cart.entrySet().stream()
                .map(entry -> new ItemsForCartDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }


    //Cart Operations

    public CartItemsService mergeFromDBToSession(Customer customer, CartItemsService service){
        List<CartItems> cartItems = getCartItemFromDB(customer,cartItemsRepository);
        if (!cartItems.isEmpty())
            mergeCartItemToSession(cartItems,service);

        addToDB(cartItems,customer,service);
        return service;
    }
    public void addToDB(List<CartItems> cartItems, Customer customer, CartItemsService cartService){
        List<SubProductDTO> subProductList = convertCartServiceMapToList(cartService);
        if(subProductList.isEmpty()) {
            removeOldCartItemsFromDB(customer, subProductList, customerRepository);
            return;
        }
        List<CartItems> currentCartItems = getCartItemFromDB(customer,cartItemsRepository);
        if (currentCartItems.isEmpty()) {
            addNewCartItemsToDB(subProductList, customer, cartService, currentCartItems);
        } else {
            updateExistingCartItems(subProductList, customer, cartService, currentCartItems);
        }
        customerRepository.save(customer);


    }

    //End of Cart Operation

    private List<CartItems> getCartItemFromDB(Customer customer, CartItemsRepository cartRepository){
        return cartRepository.findByCustomer_Id(customer.getId());
    }
    private void mergeCartItemToSession(List<CartItems> cartItems, CartItemsService service){
        cartItems.forEach(cartItem -> {
            service.addCartItem(SubProductMapper.convertEntityToDTO(cartItem.getSubProduct())
                    ,cartItem.getQuantity()
            );
        });
    }

    private List<SubProductDTO> convertCartServiceMapToList(CartItemsService cartService){
        return new ArrayList<>(cartService.getItems().keySet());
    }
    private void removeOldCartItemsFromDB(Customer customer,List<SubProductDTO> subProductList, CustomerRepository customerRepository){
        Set<CartItems> currentCartItem = customer.getShoppingCart();
        Set<Integer> newSubProductIds = getSubProductIds(subProductList);
        removeUnwantedCartItems(currentCartItem, newSubProductIds);
        customerRepository.save(customer);
    }
    private Set<Integer> getSubProductIds(List<SubProductDTO> subProductList) {
        return subProductList.stream()
                .map(SubProductDTO::getId)
                .collect(Collectors.toSet());
    }
    private void removeUnwantedCartItems(Set<CartItems> currentCartItem, Set<Integer> newSubProductIds) {
        currentCartItem.removeIf(cartItem -> !newSubProductIds.contains(cartItem.getSubProduct().getId()));
    }
    private void addNewCartItemsToDB(List<SubProductDTO> subProductList, Customer customer,CartItemsService cartService,List<CartItems> currentCartItems){
        subProductList.forEach(subProductDTO -> {
            CartItems newCartItem = new CartItems();
            newCartItem.setCustomer(customer);
            newCartItem.setSubProduct(subProductRepository.findById(subProductDTO.getId()).get());
            newCartItem.setQuantity(cartService.getQuantityOfSubProduct(subProductDTO));
            currentCartItems.add(newCartItem);
        });
        customer.getShoppingCart().clear();
        customer.getShoppingCart().addAll(currentCartItems);
    }
    private  void updateExistingCartItems (List<SubProductDTO> subProductList, Customer customer,CartItemsService cartService,List<CartItems> currentCartItems){
        Set<CartItems> currentCartItem = customer.getShoppingCart();
        Set<Integer> newSubProductIds = getSubProductIds(subProductList);
        removeUnwantedCartItems(currentCartItem, newSubProductIds);

        subProductList.forEach(subProductDTO -> {
            CartItems existingCartItem = findCartItemBySubProductId(currentCartItem, subProductDTO.getId());

            if (existingCartItem != null) {
                updateCartItemQuantity(existingCartItem, cartService, subProductDTO);
            } else {
                addNewCartItem(currentCartItem, customer, cartService, subProductDTO);
            }
        });
    }
    private CartItems findCartItemBySubProductId(Set<CartItems> cartItems, Integer subProductId) {
        return cartItems.stream()
                .filter(cartItem -> cartItem.getSubProduct().getId().equals(subProductId))
                .findFirst()
                .orElse(null);
    }

    private void updateCartItemQuantity(CartItems cartItem, CartItemsService cartService, SubProductDTO subProductDTO) {
        Integer newQuantity = cartService.getQuantityOfSubProduct(subProductDTO);
        if (!cartItem.getQuantity().equals(newQuantity)) {
            cartItem.setQuantity(newQuantity);
        }
    }

    private void addNewCartItem(Set<CartItems> currentCartItem, Customer customer, CartItemsService cartService, SubProductDTO subProductDTO) {
        CartItems newCartItem = new CartItems();
        newCartItem.setCustomer(customer);
        newCartItem.setSubProduct(subProductRepository.findById(subProductDTO.getId()).get());
        newCartItem.setQuantity(cartService.getQuantityOfSubProduct(subProductDTO));
        currentCartItem.add(newCartItem);
    }
  

    public List<CartItemsDTO> getAllCartItems() {
        return cartItemsRepository.findAll()
                .stream()
                .map(cartItemsMapper::toDto)
                .collect(Collectors.toList());
    }

    public CartItemsDTO createCartItem(CartItemsDTO cartItemsDTO) {
        CartItems cartItems = cartItemsMapper.toEntity(cartItemsDTO);
        cartItems = cartItemsRepository.save(cartItems);
        return cartItemsMapper.toDto(cartItems);
    }

    public CartItemsDTO updateCartItem(CartItemsDTO cartItemsDTO) {
        CartItems cartItems = cartItemsMapper.toEntity(cartItemsDTO);
        cartItems = cartItemsRepository.save(cartItems);
        return cartItemsMapper.toDto(cartItems);
    }

//    // Delete a specific cart item
//    public boolean deleteCartItem(Integer id) {
//        if (cartItemsRepository.existsById(id)) {
//            cartItemsRepository.deleteById(id);
//            return true;
//        }
//        return false;
//    }

    // Delete all cart items
    public void deleteAllCartItems() {
        cartItemsRepository.deleteAll();
    }

    public CartItemsDTO getCartItemById(CustomerProductId id) {
        // Assuming you have a repository to fetch CartItems
        Optional<CartItems> cartItemOptional = cartItemsRepository.findById(id);

        // Check if the cart item exists
        if (cartItemOptional.isPresent()) {
            CartItems cartItem = cartItemOptional.get();

            // Map CartItems to CartItemsDTO
            CartItemsDTO cartItemsDTO = new CartItemsDTO();
            cartItemsDTO.setCustomerId(cartItem.getCustomer().getId()); // Assuming Customer has getId()
            cartItemsDTO.setSubProductId(cartItem.getSubProduct().getId()); // Assuming SubProduct has getId()
            cartItemsDTO.setQuantity(cartItem.getQuantity());

            return cartItemsDTO;
        } else {
            // Handle the case where the cart item does not exist
            throw new EntityNotFoundException("Cart item not found for ID: " + id);
        }
    }

    // Method to delete a cart item
    public void deleteCartItem(CustomerProductId id) {
        // Check if the cart item exists
        if (!cartItemsRepository.existsById(id)) {
            throw new EntityNotFoundException("Cart item not found for ID: " + id);
        }
        // Delete the cart item
        cartItemsRepository.deleteById(id);
    }

    // Method to update a cart item
    public CartItemsDTO updateCartItem(CustomerProductId id, CartItemsDTO cartItemsDTO) {
        // Check if the cart item exists
        CartItems existingCartItem = cartItemsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cart item not found for ID: " + id));

        // Update the fields as needed
        existingCartItem.setQuantity(cartItemsDTO.getQuantity());
        // Add any other fields you wish to update

        // Save the updated cart item
        CartItems updatedCartItem = cartItemsRepository.save(existingCartItem);

        // Map updated CartItems to CartItemsDTO
        CartItemsDTO updatedCartItemsDTO = new CartItemsDTO();
        updatedCartItemsDTO.setCustomerId(updatedCartItem.getCustomer().getId());
        updatedCartItemsDTO.setSubProductId(updatedCartItem.getSubProduct().getId());
        updatedCartItemsDTO.setQuantity(updatedCartItem.getQuantity());

        return updatedCartItemsDTO;
    }

    // Method to add a new cart item
    public CartItemsDTO addCartItem(CartItemsDTO cartItemsDTO) {
        // Create a new CartItems entity from the DTO
        CartItems newCartItem = new CartItems();

        // Set properties
        newCartItem.setQuantity(cartItemsDTO.getQuantity());

        // Assuming you have methods to retrieve the customer and subProduct by their IDs
        // Adjust these methods based on your actual implementation
        newCartItem.setCustomer(customerRepository.findById(cartItemsDTO.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found for ID: " + cartItemsDTO.getCustomerId())));

        newCartItem.setSubProduct(subProductRepository.findById(cartItemsDTO.getSubProductId())
                .orElseThrow(() -> new EntityNotFoundException("SubProduct not found for ID: " + cartItemsDTO.getSubProductId())));

        // Save the new cart item
        CartItems savedCartItem = cartItemsRepository.save(newCartItem);

        // Map saved CartItems to CartItemsDTO
        CartItemsDTO savedCartItemsDTO = new CartItemsDTO();
        savedCartItemsDTO.setCustomerId(savedCartItem.getCustomer().getId());
        savedCartItemsDTO.setSubProductId(savedCartItem.getSubProduct().getId());
        savedCartItemsDTO.setQuantity(savedCartItem.getQuantity());

        return savedCartItemsDTO;
    }

}