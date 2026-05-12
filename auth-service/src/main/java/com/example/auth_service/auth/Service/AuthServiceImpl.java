package com.example.auth_service.auth.Service;
import com.example.auth_service.auth.domain.Entity.User;
import com.example.auth_service.auth.domain.ValueObject.Email;
import com.example.auth_service.auth.domain.ValueObject.Uid;
import com.example.auth_service.auth.dto.Event.NotificationEvent;
import com.example.auth_service.auth.dto.request.LoginRequest;
import com.example.auth_service.auth.dto.request.RegisterRequest;
import com.example.auth_service.auth.dto.response.LoginResponse;
import com.example.auth_service.auth.dto.response.UserResponse;
import com.example.auth_service.auth.interfaces.repository.NotificationRepository;
import com.example.auth_service.auth.interfaces.services.AuthServiceInterface;
import com.example.auth_service.auth.interfaces.repository.UserRepositoryInterface;
import com.example.auth_service.shared.Exception.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthServiceInterface {

    private final UserRepositoryInterface userRepository;
    private final PasswordService passwordService;
    private final JwtTokenService jwtTokenService;
    private final NotificationProducer notificationProducer;
    private final NotificationRepository notificationRepository;

    public LoginResponse login(LoginRequest request) {

        Email email = Email.of(request.email());

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AuthException.LoginException(
                        "email",
                        "Email not found"
                ));

        if (!passwordService.matches(
                request.password(),
                user.getPassword().getHashedValue()
        )) {
            throw new AuthException.LoginException(
                    "password",
                    "Invalid password"
            );
        }

        String token = jwtTokenService.generateToken(user);

        NotificationEvent event = new NotificationEvent(
                Uid.generate().getValue(),
                user.getUid().getValue(),
                "EMAIL",
                user.getEmail().getValue(),
                "Login Berhasil",
                "User " + user.getFullName() + " berhasil login."
        );



        notificationRepository.insertPendingNotification(event);
        notificationProducer.publish(event);

        return new LoginResponse(token);


    }


    public UserResponse register(RegisterRequest request) {
        Email email = Email.of(request.email());
        String hashedPassword = passwordService.hash(request.password());

        User user = userRepository.register(
                Uid.generate(),
                email,
                hashedPassword,
                request.fullName(),
                request.role(),
                true
        );

        return new UserResponse(
                user.getUid().getValue(),
                user.getEmail().getValue(),
                user.getFullName(),
                user.getRole().getValue(),
                user.getCreatedAt()
        );
    }



}
