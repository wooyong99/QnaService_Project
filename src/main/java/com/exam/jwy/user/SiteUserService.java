package com.exam.jwy.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SiteUserService {
    private final SiteUserRepository siteUserRepository;

    public SiteUser create(JoinForm joinForm){
        SiteUser user = new SiteUser();
        user.setUsername(joinForm.getUsername());
        user.setPassword(joinForm.getPassword());
        user.setEmail(joinForm.getPassword());
        siteUserRepository.save(user);
        return user;

    }

}
