package com.exam.jwy.user;

import com.exam.jwy.Exception.SignUpEmailException;
import com.exam.jwy.Exception.SignUpUserNameException;
import com.exam.jwy.Form.JoinForm;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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
        try{
            siteUserRepository.save(user);
        }catch (DataIntegrityViolationException e){
            System.out.println(user.getUsername());
            System.out.println(user.getEmail());
            if(siteUserRepository.findByUsername(user.getUsername()).isPresent()){
                throw new SignUpUserNameException("username");
            }
            if(siteUserRepository.findByEmail(user.getEmail()).isPresent()){
                throw new SignUpEmailException("email");
            }
        }


        return user;
    }
}
