package com.prp.Hisab.user.infrastructure;

import com.prp.Hisab.common.config.GlobalMapperConfig;
import com.prp.Hisab.user.domain.User;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class)
public interface UserMapper {
  User toDomain(UserEntity entity);

  UserEntity toEntity(User domain);
}
