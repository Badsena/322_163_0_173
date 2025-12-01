package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "coupon_rules")
public class CouponRule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @NotBlank(message = "Rule name is required")
    private String ruleName;
    
    @Column(nullable = false)
    @NotNull(message = "Discount percentage is required")
    @Min(value = 0, message = "Discount percentage must be >= 0")
    private Double discountPercentage;
    
    @Column(nullable = false)
    @NotNull(message = "Minimum purchase amount is required")
    @Min(value = 0, message = "Minimum purchase amount must be >= 0")
    private Double minPurchaseAmount;
    
    @Column(nullable = false)
    @NotNull(message = "Expiry days is required")
    @Min(value = 1, message = "Expiry days must be >= 1")
    private Integer expiryDays; // number of days from creation
    
    public CouponRule() {
    }
    
    public CouponRule(String ruleName, Double discountPercentage, Double minPurchaseAmount, Integer expiryDays) {
        this.ruleName = ruleName;
        this.discountPercentage = discountPercentage;
        this.minPurchaseAmount = minPurchaseAmount;
        this.expiryDays = expiryDays;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getRuleName() {
        return ruleName;
    }
    
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
    
    public Double getDiscountPercentage() {
        return discountPercentage;
    }
    
    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
    
    public Double getMinPurchaseAmount() {
        return minPurchaseAmount;
    }
    
    public void setMinPurchaseAmount(Double minPurchaseAmount) {
        this.minPurchaseAmount = minPurchaseAmount;
    }
    
    public Integer getExpiryDays() {
        return expiryDays;
    }
    
    public void setExpiryDays(Integer expiryDays) {
        this.expiryDays = expiryDays;
    }
}

