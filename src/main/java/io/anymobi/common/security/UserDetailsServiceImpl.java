package io.anymobi.common.security;

import io.anymobi.repositories.jpa.UserRepository;
import io.anymobi.services.jpa.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        userRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new UsernameNotFoundException(email));
        UserAdapter userAdapter = new UserAdapter(userRepository.findByEmailIgnoreCase(email).get());
        return userAdapter;
    }

}
