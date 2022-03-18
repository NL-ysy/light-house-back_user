package com.jo.user.userLogin.service;

import com.jo.user.userLogin.dto.UserDTO;
import com.jo.user.userLogin.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService{
    User create(User user);
    User getbyCredentials(final String email, final String password, PasswordEncoder passwordEncoder);
    User update(final UserDTO user);
//    User pointUpdate(User user, BadgeListEntity badgeList);
}
