package com.prp.Hisab.repository;

import com.prp.Hisab.domain.entity.InstitutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InstitutionRepository extends JpaRepository<InstitutionEntity, UUID> {}
