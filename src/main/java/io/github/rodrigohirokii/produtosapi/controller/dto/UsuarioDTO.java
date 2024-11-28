package io.github.rodrigohirokii.produtosapi.controller.dto;

import io.github.rodrigohirokii.produtosapi.model.Usuario;

import java.util.List;

public record UsuarioDTO(String login, String senha, List<String> roles) {


    public Usuario mapearParaUsuario() {
        Usuario usuario = new Usuario();
        usuario.setLogin(this.login());
        usuario.setSenha(this.senha());
        usuario.setRoles(this.roles());

        return usuario;
    }
}
