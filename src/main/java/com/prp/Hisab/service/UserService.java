package com.prp.Hisab.service;

import com.prp.Hisab.domain.User;

public interface UserService {
  User getOrCreateUser(String keyCloackId, String email, String name);
}
