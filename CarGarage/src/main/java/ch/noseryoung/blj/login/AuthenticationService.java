package ch.noseryoung.blj.login;


import ch.noseryoung.blj.exceptions.objectNotFound.PersonNotFoundException;
import ch.noseryoung.blj.login.user.Role;
import ch.noseryoung.blj.login.user.User;
import ch.noseryoung.blj.login.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        log.info("Arrived to Service");
        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        String jwtToken = jwtService.generateToken(user);
        log.info("Generated Token");
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(user)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        log.info("Arrived to Service");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        log.info("Generated Token");
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(user)
                .role(user.getRole().name()) // oder .toString()
                .personId(user.getId())
                .build();
    }

    public User getUserByEmail(String email){
        return repository.findByEmail(email).orElseThrow(() -> new PersonNotFoundException(email));
    }

    public User findUserById(UUID personId){
        return repository.findById(personId).orElseThrow(() -> new PersonNotFoundException(personId));
    }
}