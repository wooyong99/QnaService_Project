package com.exam.jwy.Security;

import com.exam.jwy.user.SiteUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class CustomDetails implements UserDetails {
    private SiteUser siteUser;
    public CustomDetails(SiteUser siteUser) { this.siteUser = siteUser; }
//    해당 유저의 권한을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return siteUser.getRole();
            }
        });
        return null;
    }

    @Override
    public String getPassword() {
        return siteUser.getPassword();
    }

    @Override
    public String getUsername() {
        return siteUser.getUsername();
    }
    //    계정이 만료되지 않았는지 리턴 (true: 만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //    계정이 잠겨있는지 않았는지 리턴 (true: 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //    비밀번호가 만료되지 않았는지 리턴한다. (true: 만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //    계정이 활성화(사용가능)인지 리턴 (true: 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
