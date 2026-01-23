package com.prp.Hisab.mapper;

import com.prp.Hisab.config.GlobalMapperConfig;
import com.prp.Hisab.domain.Institution;
import com.prp.Hisab.domain.entity.InstitutionEntity;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class, uses = {AccountMapper.class})
public interface InstitutionMapper {
  Institution toDomain(InstitutionEntity entity);
  
  InstitutionEntity toEntity(Institution domain);
}
