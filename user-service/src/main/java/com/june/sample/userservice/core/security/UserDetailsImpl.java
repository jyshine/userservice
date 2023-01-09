package com.june.sample.userservice.core.security;

import com.june.sample.userservice.core.enums.user.UserRoleType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * User auth 정보
 */
public class UserDetailsImpl implements UserDetails {

    /** 아이디 */
    @Getter // UserDetails 상의 규격.
    @Setter
    private String username;

    /** 비밀번호 */
    @Getter // UserDetails 상의 규격.
    @Setter
    private String password;

    /** 권한목록 */
    @Setter
    private UserRoleType authorities;

    /** 권한목록 */
    @Override   // UserDetails 상의 규격.
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.authorities != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
            grantedAuthorities.add(new SimpleGrantedAuthority(authorities.name()));
            return grantedAuthorities;
        } else {
            return Collections.emptyList();
        }
    }

    /** 삭제여부 */
    @Setter
    private boolean enabled = true;

    @Override // UserDetails 상의 규격.
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
