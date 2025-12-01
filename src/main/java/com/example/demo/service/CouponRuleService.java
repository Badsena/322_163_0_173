package com.example.demo.service;

import com.example.demo.model.CouponRule;
import com.example.demo.repository.CouponRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CouponRuleService {
    
    @Autowired
    private CouponRuleRepository couponRuleRepository;
    
    public List<CouponRule> listRules() {
        return couponRuleRepository.findAll();
    }
    
    public CouponRule addRule(CouponRule rule) {
        return couponRuleRepository.save(rule);
    }
    
    public String deleteRule(long id) {
        Optional<CouponRule> rule = couponRuleRepository.findById(id);
        if (rule.isPresent()) {
            couponRuleRepository.deleteById(id);
            return "Rule with id " + id + " deleted";
        } else {
            return "Rule with id " + id + " not found";
        }
    }
}

