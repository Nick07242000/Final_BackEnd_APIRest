package com.example.test.servicios.auth;

import com.example.test.model.auth.AppUser;
import com.example.test.model.auth.AppUsuarioRoles;
import com.example.test.repository.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("password");
        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String hashedPassword2 = passwordEncoder2.encode("password2");
        if(userRepository.findByEmail("diego@digital.com").isEmpty())
            userRepository.save(new AppUser("Diego", "diego", "diego@digital.com", hashedPassword, AppUsuarioRoles.ADMIN));
        if(userRepository.findByEmail("paula@digital.com").isEmpty())
            userRepository.save(new AppUser("Paula", "paula", "paula@digital.com", hashedPassword2, AppUsuarioRoles.USER));
    }
}
