package com.exam.jwy.user;

import com.exam.jwy.Form.JoinForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SiteUserService {
    private final SiteUserRepository siteUserRepository;
    private final PasswordEncoder passwordEncoder;
    public SiteUser create(JoinForm joinForm){
        String encPassword = passwordEncoder.encode(joinForm.getPassword());
        SiteUser user = new SiteUser();
        user.setUsername(joinForm.getUsername());
        user.setPassword(encPassword);
        user.setEmail(joinForm.getEmail());
        user.setRole("ROLE_USER");
        siteUserRepository.save(user);
        return user;
    }

}
