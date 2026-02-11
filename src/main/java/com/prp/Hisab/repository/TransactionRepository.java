package com.prp.Hisab.repository;

import com.prp.Hisab.domain.entity.TransactionEntity;
import com.prp.Hisab.repository.projection.ListTransactionProjection;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {
  List<ListTransactionProjection> findAllByAccountId(UUID accountId);
}
