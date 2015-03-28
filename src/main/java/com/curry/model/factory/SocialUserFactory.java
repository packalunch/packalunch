package com.curry.model.factory;

import com.curry.model.Customer;
import com.curry.model.dto.SocialUserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

/**
 * CurryWithAri
 * Created by sadra on 2/2/15.
 */
public class SocialUserFactory {

    public static SocialUserDetail build (Customer customer){

        Set<GrantedAuthority> authorities = new HashSet<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(customer.getCredential().getRole().toString());
        authorities.add(authority);

        SocialUserDetail socialUser = new SocialUserDetail(customer.getEmail(),
                customer.getCredential().getPassword(),
                authorities);

        socialUser
                .setId(customer.getId())
                .setFirstName(customer.getFirst_name())
                .setLastName(customer.getLast_name())
                .setRole(customer.getCredential().getRole())
                .setSocialSignInProvider(customer.getCredential().getSignInProvider());

        return socialUser;

    }
}
