package com.macro.mall.dto;

import com.macro.mall.common.domain.LoginUserDto;
import lombok.Data;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class SecurityUser implements UserDetails {

    //  id
    private Long id;
    //  用户名
    private String username;
    //  用户密码
    private String password;
    //  用户状态
    private Boolean enabled;
    //  客户端id
    private String clientId;
    //   权限集
    private Collection<SimpleGrantedAuthority> authorities;

    public SecurityUser() {
    }

    public SecurityUser(LoginUserDto loginUserDto) {
        this.setId(loginUserDto.getId());
        this.setUsername(loginUserDto.getUsername());
        this.setPassword(loginUserDto.getPassword());
        this.setEnabled(loginUserDto.getStatus() == 1);
        this.setClientId(loginUserDto.getClientId());
        if (loginUserDto.getRoles() != null) {
            authorities = new ArrayList<>();
            loginUserDto.getRoles().forEach(item -> authorities.add(new SimpleGrantedAuthority(item)));
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override//用户名
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
