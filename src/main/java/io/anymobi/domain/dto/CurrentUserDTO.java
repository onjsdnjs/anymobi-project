package io.anymobi.domain.dto;


import io.anymobi.domain.entity.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUserDTO extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUserDTO(User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {return user.getId();}

    @Override
    public String toString() {
        return "CurrentUserDTO{" +
                "user=" + user +
                "} " + super.toString();
    }

}
