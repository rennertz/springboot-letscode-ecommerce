package com.letscode.ecommerce.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.letscode.ecommerce.dao.ClienteDao;
import com.letscode.ecommerce.models.Cliente;
import com.letscode.ecommerce.models.PerfilEnum;

@Component
public class SetupAdmin implements 
                ApplicationListener<ContextRefreshedEvent>{
    
    boolean alreadySetup = false;
    private final String EMAIL = "admin";
    private final String SENHA = "admin123";

    @Autowired
    private ClienteDao clienteDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        Cliente admin = new Cliente();
        admin.setEmail(EMAIL);
        admin.setSenha(passwordEncoder.encode(SENHA));
        admin.setPerfil(PerfilEnum.ADMIN);

        clienteDao.save(admin);

        alreadySetup = true;
    }
}
