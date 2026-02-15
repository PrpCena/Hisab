package com.prp.Hisab.transaction.infrastructure;

import com.prp.Hisab.common.config.GlobalMapperConfig;
import com.prp.Hisab.tag.infrastructure.TagEntity;
import com.prp.Hisab.transaction.domain.Transaction;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = GlobalMapperConfig.class)
public interface TransactionMapper {
  @Mapping(target = "accountId", source = "account.id")
  @Mapping(target = "tagIds", source = "tags")
  Transaction toDomain(TransactionEntity entity);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "account", ignore = true)
  @Mapping(target = "tags", ignore = true)
  TransactionEntity toEntity(Transaction domain);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "account", ignore = true)
  @Mapping(target = "tags", ignore = true)
  void updateEntity(Transaction domain, @MappingTarget TransactionEntity entity);

  default UUID mapTagToId(TagEntity entity) {
    if (entity == null) {
      return null;
    }
    return entity.getId();
  }
}
