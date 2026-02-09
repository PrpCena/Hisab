package com.prp.Hisab.mapper;

import com.prp.Hisab.config.GlobalMapperConfig;
import com.prp.Hisab.domain.Transaction;
import com.prp.Hisab.domain.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GlobalMapperConfig.class)
public interface TransactionMapper {
  @Mapping(target = "accountId", source = "account.id")
  Transaction toDomain(TransactionEntity entity);

  @Mapping(target = "account", ignore = true)
  @Mapping(target = "tags", ignore = true)
  TransactionEntity toEntity(Transaction domain);
}
