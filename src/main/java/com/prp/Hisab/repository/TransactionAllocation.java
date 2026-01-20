package com.prp.Hisab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionAllocation extends JpaRepository<TransactionAllocation, UUID> {}
