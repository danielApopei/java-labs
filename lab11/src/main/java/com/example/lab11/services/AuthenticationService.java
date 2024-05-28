package com.example.lab11.services;


import com.example.lab11.auth.AuthenticationRequest;
import com.example.lab11.auth.AuthenticationResponse;
import com.example.lab11.auth.RegisterRequest;
import com.example.lab11.users.Role;
import com.example.lab11.users.User;
import com.example.lab11.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService service;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = null;
        if(request.getRole() == Role.USER){
            user = authenticate_user(request);
        } else if(request.getRole() == Role.ADMIN){
            user = authenticate_admin(request);
        } else {
            throw new RuntimeException("Must add Role");
        }
        var checkUser= repository.findByEmail(request.getEmail());
        //check if the user is already registered with this email
        if(checkUser.isPresent() && !user.isEnabled()){
            // resend the email if the user exists and the email is not confirmed
            System.out.println("We sent you an email to activate your account.");
        } else if(checkUser.isPresent() && user.isEnabled()){
            throw new RuntimeException("This user with this email already exists");
        }

        if(checkUser.isEmpty())
            repository.save(user);


        var jwtToken =  service.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }





    private User authenticate_admin(RegisterRequest request) {
        //todo: De trimis mie un email de confirmare ca sa poata deveni admin
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword() ))
                .role(Role.ADMIN)
                .build();
    }

    private User authenticate_user(RegisterRequest request){
        //todo: de trimis email de confirmare pe mailul utilizatorului
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword() ))
                .role(Role.USER)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user= repository.findByEmail(request.getEmail()).orElseThrow();

        var jwtToken =  service.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }



    public void enableUser(String email) {
        repository.enableUser(email);
    }
}
