package com.example.demo.controller;

import com.example.demo.model.CouponRule;
import com.example.demo.service.CouponRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rules")
@Tag(name = "Coupon Rules", description = "Manage coupon rules (Admin operations)")
@SecurityRequirement(name = "bearerAuth")
public class CouponRuleController {
    
    @Autowired
    private CouponRuleService couponRuleService;
    
    @GetMapping
    @Operation(summary = "List all coupon rules", description = "Returns a list of all available coupon rules")
    public ResponseEntity<List<CouponRule>> listRules() {
        List<CouponRule> rules = couponRuleService.listRules();
        return ResponseEntity.ok(rules);
    }
    
    @PostMapping
    @Operation(summary = "Add a new coupon rule", description = "Creates a new coupon rule with discount percentage, minimum purchase amount, and expiry days")
    public ResponseEntity<CouponRule> addRule(@RequestBody CouponRule rule) {
        CouponRule savedRule = couponRuleService.addRule(rule);
        return ResponseEntity.ok(savedRule);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a coupon rule", description = "Deletes a coupon rule by its ID")
    public ResponseEntity<String> deleteRule(@PathVariable long id) {
        String message = couponRuleService.deleteRule(id);
        return ResponseEntity.ok(message);
    }
}

