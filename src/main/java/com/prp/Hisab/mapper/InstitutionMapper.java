package com.prp.Hisab.mapper;

import com.prp.Hisab.config.GlobalMapperConfig;
import com.prp.Hisab.domain.Institution;
import com.prp.Hisab.domain.entity.InstitutionEntity;
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
