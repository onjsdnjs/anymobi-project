package io.anymobi.repositories;

import io.anymobi.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

@Repository
public class UserDetailRepositoryImpl implements UserDetailsRepository {

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactoryBean;

    @Override
    public User findUserByUsername(String username) {
        return entityManagerFactoryBean.getObject().createEntityManager().find(User.class, username);
    }
}
