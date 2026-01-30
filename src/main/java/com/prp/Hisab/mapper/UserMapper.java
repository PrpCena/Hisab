package com.prp.Hisab.mapper;

import com.prp.Hisab.config.GlobalMapperConfig;
import com.prp.Hisab.domain.User;
import com.prp.Hisab.domain.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class, uses = {InstitutionMapper.class})
public interface UserMapper {
    User toDomain(UserEntity entity);
    
    UserEntity toEntity(User domain);
}
