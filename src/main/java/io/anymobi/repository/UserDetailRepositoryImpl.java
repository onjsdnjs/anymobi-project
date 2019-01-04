package io.anymobi.repository;

import io.anymobi.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDetailRepositoryImpl implements UserDetailsRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findUserByUsername(String username) {
        return sessionFactory.getCurrentSession().get(User.class, username);
    }
}
