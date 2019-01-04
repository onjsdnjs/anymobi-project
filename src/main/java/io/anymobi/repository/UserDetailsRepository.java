package io.anymobi.repository;

import io.anymobi.model.User;

public interface UserDetailsRepository {

    User findUserByUsername(String username);

}
