package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.musikrec.musikrec.User.AppUser;
import com.musikrec.musikrec.User.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final AppUserRepository userRepository;

    @Override
    public AppUser getUserFromLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        AppUser user = (AppUser) authentication.getPrincipal();

        return user;
    }

    @Override
    public AppUser getUserById(Integer userId) {
        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + " not found"));

        return user;
    }
}
