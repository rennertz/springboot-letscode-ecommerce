package com.letscode.ecommerce.configuration.security;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.letscode.ecommerce.dao.ClienteDao;
import com.letscode.ecommerce.models.Cliente;

@Service
public class UserServiceImpl implements UserDetailsService{

    @Autowired
    ClienteDao clienteDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        Cliente cliente = clienteDao.findByEmail(email);

        if (Objects.isNull(cliente)) {
            throw new UsernameNotFoundException(email);
        }
        
        return new UserPrincipalDetails(cliente);
    }
    
}
