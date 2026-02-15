package com.prp.Hisab.user.application;

import com.prp.Hisab.user.domain.User;

public interface UserService {
    User getOrCreateUser(String keyCloackId, String email, String name);
}
