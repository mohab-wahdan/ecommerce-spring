package com.example.ecommerce.cartItems.dto;

import com.example.ecommerce.product.dto.SubProductDTO;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemsForCartDTO {

    @JsonProperty("subProduct")
    private SubProductDTO subProduct;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonCreator
    public ItemsForCartDTO(@JsonProperty("subProduct") SubProductDTO subProduct, @JsonProperty("quantity") Integer quantity) {
        this.subProduct = subProduct;
        this.quantity = quantity;
    }

    // Getters
    public SubProductDTO getSubProduct() {
        return subProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    // Setters
    public void setSubProduct(SubProductDTO subProduct) {
        this.subProduct = subProduct;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
