package com.prp.Hisab.repository;

import com.prp.Hisab.domain.entity.AccountEntity;

import java.util.List;
import java.util.UUID;

import com.prp.Hisab.repository.projection.ListAccountProjection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {
    
    List<ListAccountProjection> findAllByInstitutionId(UUID institutionId);
}
