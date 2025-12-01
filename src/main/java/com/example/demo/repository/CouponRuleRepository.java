package com.example.demo.repository;

import com.example.demo.model.CouponRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRuleRepository extends JpaRepository<CouponRule, Long> {
}

