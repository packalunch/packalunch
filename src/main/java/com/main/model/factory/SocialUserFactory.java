package com.main.model.factory;

import com.main.model.user.User;
import com.main.model.dto.SocialUserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

/**
 * PackALunch
 * Created by sadra on 2/2/15.
 */
public class SocialUserFactory {

    public static SocialUserDetail build (User user){

        Set<GrantedAuthority> authorities = new HashSet<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getCredential().getRole().toString());
        authorities.add(authority);

        SocialUserDetail socialUser = new SocialUserDetail(user.getEmail(),
                user.getCredential().getPassword(),
                authorities);

        socialUser
                .setId(user.getId())
                .setFirstName(user.getFirst_name())
                .setLastName(user.getLast_name())
                .setRole(user.getCredential().getRole())
                .setSocialSignInProvider(user.getCredential().getSignInProvider());

        return socialUser;

    }
}
