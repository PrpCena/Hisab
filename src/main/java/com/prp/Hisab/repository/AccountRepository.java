package com.prp.Hisab.repository;

import com.prp.Hisab.domain.entity.AccountEntity;
import com.prp.Hisab.repository.projection.ListAccountProjection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

  List<ListAccountProjection> findAllByInstitutionId(UUID institutionId);
  
  Optional<AccountEntity> findByIdAndInstitution_CreatedBy_Id(UUID accountId, UUID userId);
}
