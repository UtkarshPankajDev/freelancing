package com.freelance.repository;

import com.freelance.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This repository will be used to perform DB operations on Buyer object
 * JpaRepository is already annotated with Repository, so no need of that annotation here
 * Also, all methods of JpaRepository are @Transactional, so no need to make any method annotated with it here
 *
 */
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
    Buyer findByEmail(String email);
}
