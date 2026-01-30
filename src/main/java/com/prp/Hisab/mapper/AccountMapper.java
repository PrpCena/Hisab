package com.prp.Hisab.mapper;

import com.prp.Hisab.config.GlobalMapperConfig;
import com.prp.Hisab.domain.Account;
import com.prp.Hisab.domain.entity.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class, uses = {TransactionMapper.class})
public interface AccountMapper {
    Account toDomain(AccountEntity entity);
    
    AccountEntity toEntity(Account domain);
}
