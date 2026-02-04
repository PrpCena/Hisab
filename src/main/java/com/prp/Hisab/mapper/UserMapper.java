package com.prp.Hisab.mapper;

import com.prp.Hisab.config.GlobalMapperConfig;
import com.prp.Hisab.domain.User;
import com.prp.Hisab.domain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GlobalMapperConfig.class, uses = {InstitutionMapper.class})
public interface UserMapper {
    @Mapping(target = "institutions", ignore = true)
    User toDomain(UserEntity entity);
    
    UserEntity toEntity(User domain);
}
