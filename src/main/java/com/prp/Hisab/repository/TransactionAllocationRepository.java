package com.prp.Hisab.repository;

import com.prp.Hisab.domain.entity.TransactionAllocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionAllocationRepository
  extends JpaRepository<TransactionAllocationEntity, UUID> {}
