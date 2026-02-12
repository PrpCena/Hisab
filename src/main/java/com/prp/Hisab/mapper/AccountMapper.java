package com.prp.Hisab.mapper;

import com.prp.Hisab.config.GlobalMapperConfig;
import com.prp.Hisab.domain.Account;
import com.prp.Hisab.domain.entity.AccountEntity;
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
