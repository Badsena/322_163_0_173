package com.example.demo.service;

import com.example.demo.model.Coupon;
import com.example.demo.model.CouponRule;
import com.example.demo.repository.CouponRepository;
import com.example.demo.repository.CouponRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CouponService {
    
    @Autowired
    private CouponRepository couponRepository;
    
    @Autowired
    private CouponRuleRepository couponRuleRepository;
    
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int COUPON_CODE_LENGTH = 10;
    
    public Coupon generateCoupon(long ruleId, String username) {
        Optional<CouponRule> ruleOpt = couponRuleRepository.findById(ruleId);
        if (ruleOpt.isEmpty()) {
            throw new RuntimeException("Rule with id " + ruleId + " not found");
        }
        
        CouponRule rule = ruleOpt.get();
        String couponCode = generateUniqueCouponCode();
        LocalDate expiryDate = LocalDate.now().plusDays(rule.getExpiryDays());
        
        Coupon coupon = new Coupon();
        coupon.setCouponCode(couponCode);
        coupon.setDiscountPercentage(rule.getDiscountPercentage());
        coupon.setAssignedToUser(username);
        coupon.setExpiryDate(expiryDate);
        coupon.setStatus("ACTIVE");
        
        return couponRepository.save(coupon);
    }
    
    public List<Coupon> getUserCoupons(String username) {
        List<Coupon> coupons = couponRepository.findByAssignedToUser(username);
        // Update expired coupons
        LocalDate today = LocalDate.now();
        for (Coupon coupon : coupons) {
            if (coupon.getStatus().equals("ACTIVE") && coupon.getExpiryDate().isBefore(today)) {
                coupon.setStatus("EXPIRED");
                couponRepository.save(coupon);
            }
        }
        return couponRepository.findByAssignedToUser(username);
    }
    
    public String useCoupon(long couponId) {
        Optional<Coupon> couponOpt = couponRepository.findById(couponId);
        if (couponOpt.isEmpty()) {
            return "Coupon not found";
        }
        
        Coupon coupon = couponOpt.get();
        
        // Check if expired
        if (coupon.getExpiryDate().isBefore(LocalDate.now()) && coupon.getStatus().equals("ACTIVE")) {
            coupon.setStatus("EXPIRED");
            couponRepository.save(coupon);
            return "Coupon expired";
        }
        
        if (coupon.getStatus().equals("USED")) {
            return "Coupon already used";
        }
        
        if (coupon.getStatus().equals("EXPIRED")) {
            return "Coupon expired";
        }
        
        if (coupon.getStatus().equals("ACTIVE")) {
            coupon.setStatus("USED");
            couponRepository.save(coupon);
            return "Coupon used successfully";
        }
        
        return "Coupon not found";
    }
    
    private String generateUniqueCouponCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        String generatedCode;
        
        do {
            code.setLength(0);
            for (int i = 0; i < COUPON_CODE_LENGTH; i++) {
                code.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
            }
            generatedCode = code.toString();
        } while (couponRepository.findByCouponCode(generatedCode).isPresent());
        
        return generatedCode;
    }
}

