package com.prp.Hisab.mapper;

import com.prp.Hisab.config.GlobalMapperConfig;
import com.prp.Hisab.domain.Account;
import com.prp.Hisab.domain.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GlobalMapperConfig.class)
public interface AccountMapper {
  @Mapping(target = "institutionId", source = "institution.id")
  Account toDomain(AccountEntity entity);

  @Mapping(target = "institution", ignore = true)
  AccountEntity toEntity(Account domain);
}
