package com.intecap.ctiniguar.Articulos.services;

import com.intecap.ctiniguar.Articulos.models.Usuario;
import com.intecap.ctiniguar.Articulos.models.dao.IUsuarioDao;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(UsuarioService.class); // findByNombreUsuario es un método personalizado de IUsuarioDao que busca un usuario por nombre de usuario.

    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioDao.findByNombreUsuario(username);

        if (usuario == null) {
            log.error("Error en el login: no existe el usuario '" + username + "' en el sistema!");
            throw new UsernameNotFoundException("Error en el login: no existe el usuario '" + username + "' en el sistema!");
        }
        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority -> log.info("Roles: " + authority.getAuthority()))
                .collect(Collectors.toList());
        return new User(usuario.getNombreUsuario(), usuario.getPassword(), usuario.getHabilitado(), true, true, true, authorities);
    }
}
