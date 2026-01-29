package com.prp.Hisab.service.impl;

import com.prp.Hisab.domain.User;
import com.prp.Hisab.domain.entity.UserEntity;
import com.prp.Hisab.mapper.UserMapper;
import com.prp.Hisab.repository.UserRepository;
import com.prp.Hisab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl
  implements UserService {
  
  private final UserMapper userMapper;
  private final UserRepository userRepository;
  
  @Override
  public User getOrCreateUser(String keyCloackId, String email, String name) {
	return userRepository
			 .findByKeyCloackId(keyCloackId)
			 .map(userMapper::toDomain)
			 .orElseGet(() -> {
			   UserEntity newUser = new UserEntity();
			   newUser.setKeyCloackId(keyCloackId);
			   newUser.setEmail(email);
			   newUser.setName(name);
			   return userMapper.toDomain(userRepository.save(newUser));
			 });
  }
}
