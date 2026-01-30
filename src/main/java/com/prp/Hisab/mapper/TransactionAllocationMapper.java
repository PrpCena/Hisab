package com.prp.Hisab.mapper;

import com.prp.Hisab.config.GlobalMapperConfig;
import com.prp.Hisab.domain.TransactionAllocation;
import com.prp.Hisab.domain.entity.TransactionAllocationEntity;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class, uses = {TagMapper.class, TransactionMapper.class})
public interface TransactionAllocationMapper {
    TransactionAllocation toDomain(TransactionAllocationEntity entity);
    
    TransactionAllocationEntity toEntity(TransactionAllocation entity);
}
