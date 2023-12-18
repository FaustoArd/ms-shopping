package com.lord.securityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lord.securityservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
