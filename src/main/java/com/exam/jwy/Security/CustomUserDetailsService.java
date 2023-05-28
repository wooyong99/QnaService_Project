package com.exam.jwy.Security;

import com.exam.jwy.user.SiteUser;
import com.exam.jwy.user.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SiteUserRepository siteUserRepository;
    // 시큐리티 session에는 Authentication타입 필요
    // Authentication타입에서 User를 검사하기 위한 UserDetails타입 필요
    // 따라서 securityconfig의 loginProcessingUrl의 경로 접근 시 loadUserByUsername이 호출되어
    // UserDetails타입을 Authentication 내부에 return을해준다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SiteUser user = siteUserRepository.findByUsername(username);
        if(user != null) {
            return new CustomDetails(user);
        }
        return null;
    }
}
