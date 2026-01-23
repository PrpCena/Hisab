package com.prp.Hisab.mapper;

import com.prp.Hisab.config.GlobalMapperConfig;
import com.prp.Hisab.domain.Transaction;
import com.prp.Hisab.domain.entity.TransactionEntity;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class, uses = {AccountMapper.class, TransactionAllocationMapper.class})
public interface TransactionMapper {
  Transaction toDomain(TransactionEntity entity);
  
  TransactionEntity toEntity(Transaction domain);
}
