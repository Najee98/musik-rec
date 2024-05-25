package com.musikrec.musikrec.Authentication;

import com.musikrec.musikrec.Authentication.Dto.AuthenticationRequest;
import com.musikrec.musikrec.Authentication.Dto.AuthenticationResponse;
import com.musikrec.musikrec.Authentication.Dto.RegisterRequest;
import com.musikrec.musikrec.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/user-id")
    public ResponseEntity<Long> getUserIdFromLogin(){
        return new ResponseEntity<>(userService.getUserIdFromLogin(), HttpStatus.ACCEPTED);
    }
}
