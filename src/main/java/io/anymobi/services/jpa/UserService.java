package io.anymobi.services.jpa;

import io.anymobi.common.CommonLogger;
import io.anymobi.common.annotation.SoftTransational;
import io.anymobi.domain.entity.User;
import io.anymobi.repositories.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class UserService extends CommonLogger<UserService> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @SoftTransational
    public Optional<User> findById(Long id) {

        return userRepository.findById(id);
    }

    @SoftTransational
    public List<User> findAll() {

        return userRepository.findAll();
    }

    @SoftTransational
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @SoftTransational
    public boolean isExist(User user) {

        return userRepository.exists(Example.of(user));
    }

    @SoftTransational
    public void deleteById(Long id) {

        userRepository.deleteById(id);
    }
}
