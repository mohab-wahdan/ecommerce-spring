package com.example.ecommerce.services;

import com.example.ecommerce.dtos.OrderViewDTO;
import com.example.ecommerce.enums.Status;
import com.example.ecommerce.exceptions.OrderProcessError;
import com.example.ecommerce.models.*;
import com.example.ecommerce.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository ;
//    private final SubProductRepository subProductRepository;
//    private final CustomerRepository customerRepository;
//    private final CartRepository cartRepository;
//
//    @Autowired
//    public OrderService(OrderRepository orderRepository, SubProductRepository subProductRepository, CustomerRepository customerRepository, CartRepository cartRepository) {
//        this.orderRepository = orderRepository;
//        this.subProductRepository = subProductRepository;
//        this.customerRepository = customerRepository;
//        this.cartRepository = cartRepository;
//    }


    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

//    private BigDecimal decreaseCustomerCreditLimit(Customer customer, CartService cartService){
//        return customer.getCreditLimit().subtract(cartService.getTotalPrice());
//    }
//    private List<SubProductDTO> convertCartServiceMapToList(CartService cartService){
//        return cartService.getItems().keySet().stream().collect(Collectors.toList());
//    }
//    private boolean hasStockErrors(List<SubProductDTO> subProductList, OrderProcessError orderProcessError) {
//        for (SubProductDTO subProductDTO : subProductList) {
//            SubProduct subProduct = subProductRepository.findById(subProductDTO.getId());
//            if (subProductDTO.getQuantity() > subProduct.getStock()) {
//                orderProcessError.setSubProductDTO(subProductDTO);
//                return true;
//            }
//        }
//        return false;
//    }
//    private boolean isOrderValid(CartService cartService, BigDecimal remainingCreditLimit) {
//        return cartService != null && remainingCreditLimit.compareTo(BigDecimal.ZERO) > 0;
//    }
    private Order createPendingOrder(Customer customer) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setCreatedAt(new Date());
        order.setStatus(Status.PENDING);
        orderRepository.save(order);
        return order;
    }
//
//    private Set<OrderItem> createOrderItems(List<SubProductDTO> subProductList, CartService cartService, Order order, OrderProcessError orderProcessError) {
//        Set<OrderItem> orderItems = new HashSet<>();
//
//        for (SubProductDTO subProductDTO : subProductList) {
//            SubProduct subProduct = subProductRepository.findById(subProductDTO.getId());
//
//            if (subProductDTO.getQuantity() > subProduct.getStock()) {
//                orderProcessError.setSubProductDTO(subProductDTO);
//                return orderItems;
//            }
//
//            OrderItem orderItem = new OrderItem();
//            orderItem.setSubProduct(subProduct);
//            orderItem.setOrder(order);
//            orderItem.setQuantity(cartService.getQuantityOfSubProduct(subProductDTO));
//            orderItem.setPrice(subProductDTO.getPrice());
//            orderItems.add(orderItem);
//
//            updateSubProductStock(subProduct, subProductDTO, cartService);
//        }
//
//        return orderItems;
//    }
//    private void updateSubProductStock(SubProduct subProduct, SubProductDTO subProductDTO, CartService cartService) {
//        int newStock = subProduct.getStock() - cartService.getQuantityOfSubProduct(subProductDTO);
//        subProduct.setStock(newStock);
//        subProductRepository.save(subProduct);
//    }
//    private void finalizeOrder(Order order, Set<OrderItem> orderItems, Customer customer, CartService cartService) {
//        order.getOrderItems().clear();
//        order.getOrderItems().addAll(orderItems);
//
//        BigDecimal totalPrice = cartService.getTotalPrice();
//        customer.setCreditLimit(customer.getCreditLimit().subtract(totalPrice));
//
//        clearCustomerShoppingCart(customer);
//        customerRepository.save(customer);
//    }
//
//    private void clearCustomerShoppingCart(Customer customer) {
//        List<CartItems> cartItems = cartRepository.findAllByID(customer.getId()).orElse(Collections.emptyList());
//        if (!cartItems.isEmpty()) {
//            customer.getShoppingCart().clear();
//        }
//    }


    //Order Operations
//    public OrderProcessError createOrder(CartService cartService, Customer customer) {
//        BigDecimal remainingCustomerCreditLimit = decreaseCustomerCreditLimit(customer,cartService);
//        List<SubProductDTO> subProductList = convertCartServiceMapToList(cartService);
//        OrderProcessError orderProcessError = new OrderProcessError();
//        if (hasStockErrors(subProductList, orderProcessError)) {
//            return orderProcessError;
//        }
//
//        if (isOrderValid(cartService, remainingCustomerCreditLimit)) {
//            Order order = createPendingOrder(customer);
//            Set<OrderItem> orderItems = createOrderItems(subProductList, cartService, order, orderProcessError);
//
//            if (orderProcessError.getSubProductDTO() != null) {
//                return orderProcessError;
//            }
//
//            finalizeOrder(order, orderItems, customer, cartService);
//            orderProcessError.setOrder(order);
//            orderProcessError.setSubProductDTO(null);
//        }
//        return orderProcessError;
//    }
    public List<OrderViewDTO> getAllOrdersOfSpecificCustomer(String id) {
        Integer customerId = Integer.parseInt(id);
        return orderRepository.findOrdersByCustomerId(customerId);
    }
    public void updateOrderStatus(int id, Status status) {
        int result = orderRepository.updateOrderStatus(id, status.name());
    }

    public Optional<Order> getOrderById(String orderId) {
        Integer orderIdInt = Integer.parseInt(orderId);
        return orderRepository.findById(orderIdInt);
    }
}
