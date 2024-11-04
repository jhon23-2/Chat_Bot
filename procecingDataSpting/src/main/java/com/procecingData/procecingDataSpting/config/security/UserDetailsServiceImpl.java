package com.procecingData.procecingDataSpting.config.security;

import com.procecingData.procecingDataSpting.entity.PersonEntity;
import com.procecingData.procecingDataSpting.service.serviceInterface.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PersonService personService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        PersonEntity person = personService.findByUsername(username).orElseThrow(()->{
            throw new UsernameNotFoundException("User not Found");
        });

        List<GrantedAuthority> authoritiesRoles = new ArrayList<>();

        person.getRoles().stream().forEach(role -> {
            authoritiesRoles.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRole().name())));
        });


        return new User(
                person.getUsername(),
                person.getPassword(),
                true,
                true,
                true,
                true,
                authoritiesRoles

        );
    }
}
