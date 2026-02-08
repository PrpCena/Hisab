package com.prp.Hisab.mapper;

import com.prp.Hisab.config.GlobalMapperConfig;
import com.prp.Hisab.domain.Institution;
import com.prp.Hisab.domain.entity.InstitutionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GlobalMapperConfig.class)
public interface InstitutionMapper {
  @Mapping(target = "accounts", ignore = true)
  Institution toDomain(InstitutionEntity entity);

  InstitutionEntity toEntity(Institution domain);
}
