package com.prp.Hisab.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository
        extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByKeyCloackId(String keyCloackId);
}

