package com.prp.Hisab.mapper;

import com.prp.Hisab.config.GlobalMapperConfig;
import com.prp.Hisab.domain.Institution;
import com.prp.Hisab.domain.entity.InstitutionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GlobalMapperConfig.class)
public interface InstitutionMapper {
  @Mapping(target = "createdById", source = "createdBy.id")
  Institution toDomain(InstitutionEntity entity);

  @Mapping(target = "createdBy", ignore = true)
  InstitutionEntity toEntity(Institution domain);
}
