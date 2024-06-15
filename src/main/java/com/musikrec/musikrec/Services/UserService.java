package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.User.AppUser;

public interface UserService {

    AppUser getUserFromLogin();

    AppUser getUserById(Integer userId);

}
