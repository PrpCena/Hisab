package com.prp.Hisab.mapper;

import com.prp.Hisab.config.GlobalMapperConfig;
import com.prp.Hisab.domain.Tag;
import com.prp.Hisab.domain.entity.TagEntity;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class, uses = {TransactionAllocationMapper.class})
public interface TagMapper {
    Tag toDomain(TagEntity entity);
    
    TagEntity toEntity(Tag domain);
}
