package com.lord.purchasecartservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lord.purchasecartservice.model.PurchaseCart;

public interface PurchaseCartRepository extends JpaRepository<PurchaseCart, Long> {

}
