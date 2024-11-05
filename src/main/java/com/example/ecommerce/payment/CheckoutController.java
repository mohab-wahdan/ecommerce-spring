package com.example.ecommerce.payment;

import com.example.ecommerce.product.dto.SubProductDTO;
import com.example.ecommerce.exceptions.OrderProcessError;
import com.example.ecommerce.product.mappers.SubProductMapper;
import com.example.ecommerce.models.Customer;
import com.example.ecommerce.models.SubProduct;
import com.example.ecommerce.cartItems.services.CartItemsService;
import com.example.ecommerce.cutomer.services.CustomerService;
import com.example.ecommerce.order.services.OrderService;
import com.example.ecommerce.product.services.SubProductService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final SubProductService subProductService;
    private CartItemsService cartItemsService;
//    private final OrderService orderService;

    public CheckoutController(OrderService orderService, CustomerService customerService
            , SubProductService subProductService, CartItemsService cartItemsService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.subProductService = subProductService;
        this.cartItemsService = cartItemsService;
    }

    @PostMapping("/{customerId}")
    public String checkout(@PathVariable int customerId, @RequestBody List<ItemDTO> items) {
        Map<SubProductDTO, Integer> cart = new HashMap<>();
        for (ItemDTO item : items) {
            SubProduct subProduct = subProductService.findSubProductById(item.getSubProductId());
            System.out.println(subProduct.getPrice());
            cart.put(SubProductMapper.convertEntityToDTO(subProduct), item.getQuantity());
        }
        Customer customer = customerService.getCustomerOriginalById(customerId);
        cartItemsService.setCartFromDto(cart);
        Optional<OrderProcessError> optionalOrderProcessError = Optional.ofNullable(orderService.createOrder(cartItemsService, customer));
        return "success";
    }
}
