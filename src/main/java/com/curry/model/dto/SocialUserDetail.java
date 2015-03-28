package com.curry.model.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUser;

import java.util.Collection;

/**
 * CurryWithAri
 * Created by sadra on 2/2/15.
 */
public class SocialUserDetail extends SocialUser {

    private Integer id;

    private String firstName;

    private String lastName;

    private Role role;

    private SocialMediaService socialSignInProvider;

    public SocialUserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public Integer getId() {
        return id;
    }

    public SocialUserDetail setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public SocialUserDetail setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SocialUserDetail setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public SocialUserDetail setRole(Role role) {
        this.role = role;
        return this;
    }

    public SocialMediaService getSocialSignInProvider() {
        return socialSignInProvider;
    }

    public SocialUserDetail setSocialSignInProvider(SocialMediaService socialSignInProvider) {
        this.socialSignInProvider = socialSignInProvider;
        return this;
    }

    @Override
    public String toString() {
        return "SocialUserDetail{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", socialSignInProvider=" + socialSignInProvider +
                '}';
    }
}
