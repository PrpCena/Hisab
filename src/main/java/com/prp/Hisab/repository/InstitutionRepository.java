package com.prp.Hisab.repository;

import com.prp.Hisab.domain.entity.InstitutionEntity;
import com.prp.Hisab.repository.projection.ListInstitutionProjection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionRepository extends JpaRepository<InstitutionEntity, UUID> {
  List<ListInstitutionProjection> findAllByCreatedById(UUID id);

  int deleteByIdAndCreatedById(UUID id, UUID createdById);
  
  Optional<InstitutionEntity> findByIdAndCreatedById(UUID id,  UUID createdById);
}
