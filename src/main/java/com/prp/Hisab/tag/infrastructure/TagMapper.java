package com.prp.Hisab.tag.infrastructure;

import com.prp.Hisab.common.config.GlobalMapperConfig;
import com.prp.Hisab.tag.domain.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GlobalMapperConfig.class)
public interface TagMapper {
  Tag toDomain(TagEntity entity);

  @Mapping(target = "parents", ignore = true)
  @Mapping(target = "children", ignore = true)
  @Mapping(target = "createdBy",  ignore = true)
  TagEntity toEntity(Tag domain);
}
