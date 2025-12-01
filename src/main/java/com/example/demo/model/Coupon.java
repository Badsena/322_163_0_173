package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "coupons")
public class Coupon {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    @NotBlank(message = "Coupon code is required")
    private String couponCode;
    
    @Column(nullable = false)
    @NotNull(message = "Discount percentage is required")
    private Double discountPercentage;
    
    @Column(nullable = false)
    @NotBlank(message = "Assigned user is required")
    private String assignedToUser;
    
    @Column(nullable = false)
    @NotNull(message = "Expiry date is required")
    private LocalDate expiryDate;
    
    @Column(nullable = false)
    @NotBlank(message = "Status is required")
    private String status; // "ACTIVE", "EXPIRED", "USED"
    
    public Coupon() {
    }
    
    public Coupon(String couponCode, Double discountPercentage, String assignedToUser, LocalDate expiryDate, String status) {
        this.couponCode = couponCode;
        this.discountPercentage = discountPercentage;
        this.assignedToUser = assignedToUser;
        this.expiryDate = expiryDate;
        this.status = status;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCouponCode() {
        return couponCode;
    }
    
    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
    
    public Double getDiscountPercentage() {
        return discountPercentage;
    }
    
    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
    
    public String getAssignedToUser() {
        return assignedToUser;
    }
    
    public void setAssignedToUser(String assignedToUser) {
        this.assignedToUser = assignedToUser;
    }
    
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}

