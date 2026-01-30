package com.prp.Hisab.repository;

import com.prp.Hisab.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository
        extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByKeyCloackId(String keyCloackId);
}

