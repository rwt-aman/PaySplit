package com.amanrwt.paysplit.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String registerUser(String name, String email, String rawPassword) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already registered");
        }

        String otp = generateOtp();

        User user = new User(name, email, passwordEncoder.encode(rawPassword));
        user.setOtpCode(otp);
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(10));
        user.setVerified(false);

        userRepository.save(user);

        // TEMPORARY: prints OTP to your terminal since email isn't wired up yet.
        // Remove this once real email sending is added — never log OTPs in production.
        System.out.println("OTP for " + email + " is: " + otp);

        return "Registration successful. Please verify the OTP sent to your email.";
    }

    public String verifyOtp(String email, String otpInput) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (user.isVerified()) {
            throw new IllegalArgumentException("User already verified");
        }

        if (user.getOtpExpiry().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("OTP expired, please request a new one");
        }

        if (!user.getOtpCode().equals(otpInput)) {
            throw new IllegalArgumentException("Invalid OTP");
        }

        user.setVerified(true);
        user.setOtpCode(null);
        user.setOtpExpiry(null);
        userRepository.save(user);

        return "Email verified successfully. You can now log in.";
    }

    public String login(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!user.isVerified()) {
            throw new IllegalArgumentException("Please verify your email before logging in");
        }

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // always 6 digits
        return String.valueOf(otp);
    }
}