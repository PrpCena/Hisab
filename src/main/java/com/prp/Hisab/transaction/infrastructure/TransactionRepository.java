package com.prp.Hisab.transaction.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {
  List<ListTransactionProjection> findAllByAccountId(UUID accountId);
  
  Optional<TransactionEntity> findByIdAndAccount_Institution_CreatedBy_Id(UUID transactionId, UUID userId);
}
