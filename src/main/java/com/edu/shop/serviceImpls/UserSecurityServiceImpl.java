package com.edu.shop.serviceImpls;

import com.edu.shop.entity.User;
import com.edu.shop.entity.UserRol;
import com.edu.shop.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserSecurityServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserSecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("usuario no encontrara "+username));
        String[] roles = user.getRoles().stream().map(UserRol::getRole).toArray(String[]::new);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(this.grantedAuthorities(roles))
                //.roles(roles)//se setea el rol del usuario
                .accountLocked(user.isLocked())//saber si el usuario esta
                .disabled(user.isDisabled())
                .build();
    }
    private List<GrantedAuthority> grantedAuthorities(String[] roles){
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);
        for (String role: roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role));

            for (String authority: this.getAuthorities(role))
            {
                authorities.add(new SimpleGrantedAuthority(authority));
            }
        }
        return authorities;
    }
    private String[] getAuthorities(String role)
    {
        if ("ADMIN".equals(role) || "CUSTOMER".equals(role))
        {
            return new String[]{"add_shopping"};
        }
        return new String[]{};
    }
}
