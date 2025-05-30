package ejada.task.taskManagement.controller;

import ejada.task.taskManagement.controller.dtos.AuthRequest;
import ejada.task.taskManagement.controller.dtos.AuthResponse;
import ejada.task.taskManagement.domain.user.User;
import ejada.task.taskManagement.service.JwtUtil;
import ejada.task.taskManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @CrossOrigin
    @PostMapping("/authenticate")
    public AuthResponse createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        return AuthResponse.builder()
                .token(jwtUtil.generateToken(userDetails.getUsername()))
                        .build();
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody User user) {
        userService.createUser(user);
    }
}
