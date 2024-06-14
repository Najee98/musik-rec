package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.User.AppUser;

public interface UserService {

    Integer getUserIdFromLogin();

    AppUser getUserById(Integer userId);

}
