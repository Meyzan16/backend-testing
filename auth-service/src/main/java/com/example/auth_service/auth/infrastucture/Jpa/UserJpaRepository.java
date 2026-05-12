package com.example.auth_service.auth.infrastucture.Jpa;

import com.example.auth_service.auth.infrastucture.Entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
}