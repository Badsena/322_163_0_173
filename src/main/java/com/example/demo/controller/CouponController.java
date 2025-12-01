package com.example.demo.controller;

import com.example.demo.model.Coupon;
import com.example.demo.service.CouponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupons")
@Tag(name = "Coupons", description = "Generate and manage coupons")
@SecurityRequirement(name = "bearerAuth")
public class CouponController {
    
    @Autowired
    private CouponService couponService;
    
    @PostMapping("/generate/{ruleId}")
    @Operation(summary = "Generate a coupon", description = "Generates a new coupon based on a coupon rule for the authenticated user")
    public ResponseEntity<Coupon> generateCoupon(
            @PathVariable long ruleId,
            @RequestHeader("Authorization") String token) {
        Authentication authentication = org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication();
        String username = authentication.getName();
        
        Coupon coupon = couponService.generateCoupon(ruleId, username);
        return ResponseEntity.ok(coupon);
    }
    
    @GetMapping("/my")
    @Operation(summary = "Get user's coupons", description = "Returns all coupons assigned to the authenticated user")
    public ResponseEntity<List<Coupon>> getUserCoupons() {
        Authentication authentication = org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication();
        String username = authentication.getName();
        
        List<Coupon> coupons = couponService.getUserCoupons(username);
        return ResponseEntity.ok(coupons);
    }
    
    @PutMapping("/use/{couponId}")
    @Operation(summary = "Use a coupon", description = "Marks a coupon as USED if it's ACTIVE, otherwise returns appropriate error message")
    public ResponseEntity<String> useCoupon(@PathVariable long couponId) {
        String message = couponService.useCoupon(couponId);
        return ResponseEntity.ok(message);
    }
}

