package com.prp.Hisab.institution.infrastructure;

import com.prp.Hisab.common.config.GlobalMapperConfig;
import com.prp.Hisab.institution.domain.Institution;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = GlobalMapperConfig.class)
public interface InstitutionMapper {
  Institution toDomain(InstitutionEntity entity);

  @Mapping(target = "created", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  InstitutionEntity toEntity(Institution domain);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "created", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  void updateEntity(Institution domain, @MappingTarget InstitutionEntity entity);
}
