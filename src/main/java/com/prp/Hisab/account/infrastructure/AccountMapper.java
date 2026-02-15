package com.prp.Hisab.account.infrastructure;

import com.prp.Hisab.common.config.GlobalMapperConfig;
import com.prp.Hisab.account.domain.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = GlobalMapperConfig.class)
public interface AccountMapper {
  @Mapping(target = "institution", source = "institution.id")
  Account toDomain(AccountEntity entity);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "institution", ignore = true)
  @Mapping(target = "created", ignore = true)
  AccountEntity toEntity(Account domain);
  
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "created", ignore = true)
  @Mapping(target = "institution", ignore = true)
  void updateEntity(Account domain, @MappingTarget AccountEntity entity);
}
