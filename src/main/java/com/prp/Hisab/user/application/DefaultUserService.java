package com.prp.Hisab.user.application;

import com.prp.Hisab.user.domain.User;
import com.prp.Hisab.user.infrastructure.UserEntity;
import com.prp.Hisab.user.infrastructure.UserMapper;
import com.prp.Hisab.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;

  @Override
  public User getOrCreateUser(String keyCloackId, String email, String name) {
    return userRepository
        .findByKeyCloackId(keyCloackId)
        .map(userMapper::toDomain)
        .orElseGet(
            () -> {
              UserEntity newUser = new UserEntity();
              newUser.setKeyCloackId(keyCloackId);
              newUser.setEmail(email);
              newUser.setName(name);
              return userMapper.toDomain(userRepository.save(newUser));
            });
  }
}
