package io.github.rodrigohirokii.produtosapi.controller;

import io.github.rodrigohirokii.produtosapi.controller.dto.UsuarioDTO;
import io.github.rodrigohirokii.produtosapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody UsuarioDTO dto) {
        var usuario = dto.mapearParaUsuario();

        usuarioService.salvar(usuario);
    }
}
