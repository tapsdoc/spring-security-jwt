package com.example.Spring.SecurityAlibou.service.user;

import java.security.Principal;

public interface UserService {
    void changePassword(ChangePasswordRequest request, Principal user);
}
