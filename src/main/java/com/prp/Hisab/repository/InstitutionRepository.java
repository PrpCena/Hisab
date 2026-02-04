package com.prp.Hisab.repository;

import com.prp.Hisab.domain.entity.InstitutionEntity;
import java.util.List;
import java.util.UUID;

import com.prp.Hisab.repository.projection.InstitutionIdAndNameProjection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionRepository extends JpaRepository<InstitutionEntity, UUID> {
  List<InstitutionIdAndNameProjection> findAllByCreatedById(UUID id);
}
