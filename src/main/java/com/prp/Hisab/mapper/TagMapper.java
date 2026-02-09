package com.prp.Hisab.mapper;

import com.prp.Hisab.config.GlobalMapperConfig;
import com.prp.Hisab.domain.Tag;
import com.prp.Hisab.domain.entity.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GlobalMapperConfig.class)
public interface TagMapper {
  Tag toDomain(TagEntity entity);

  @Mapping(target = "parents", ignore = true)
  @Mapping(target = "children", ignore = true)
  TagEntity toEntity(Tag domain);
}
