package com.prp.Hisab.account.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

  List<ListAccountProjection> findAllByInstitution_IdAndInstitution_CreatedBy_Id(
      UUID institutionId, UUID userId);

  Optional<AccountEntity> findByIdAndInstitution_CreatedBy_Id(UUID accountId, UUID userId);
}
