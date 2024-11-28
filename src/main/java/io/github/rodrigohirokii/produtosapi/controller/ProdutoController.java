package io.github.rodrigohirokii.produtosapi.controller;

import io.github.rodrigohirokii.produtosapi.controller.dto.ErroResposta;
import io.github.rodrigohirokii.produtosapi.controller.dto.ProdutoDTO;
import io.github.rodrigohirokii.produtosapi.exceptions.RegistroDuplicadoExceptions;
import io.github.rodrigohirokii.produtosapi.model.Produto;
import io.github.rodrigohirokii.produtosapi.repository.ProdutosRepository;
import io.github.rodrigohirokii.produtosapi.service.ProdutosService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutosRepository produtosRepository;
    private final ProdutosService produtosService;

    public ProdutoController(ProdutosRepository produtosRepository, ProdutosService produtosService) {
        this.produtosRepository = produtosRepository;
        this.produtosService = produtosService;
    }

    @PostMapping
    public ResponseEntity<Object> salvarProduto(@RequestBody @Valid ProdutoDTO produtoDTO) {
        try {
            // Transformar o produtoDTO em produto
            Produto produto = produtoDTO.mapearParaProduto();

            // Salvar no repositório
            Produto save = produtosService.salvarProduto(produto);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(save.getId())
                    .toUri();


            return ResponseEntity.created(location).build();
        } catch (RegistroDuplicadoExceptions e) {
            var erroDto = ErroResposta.respostaPadrao(e.getMessage());
            return ResponseEntity.status(erroDto.status()).body(erroDto);
        }


    }

    @GetMapping("{id}")
    public ResponseEntity<Object> obterProduto(@PathVariable("id") UUID id) {
        // Usar o repositório para encontrar esse ID
        Optional<Produto> byId = produtosRepository.findById(id);

        if (byId.isPresent()) {
            Produto produto = byId.get();
            ProdutoDTO produtoDTO = new ProdutoDTO(produto.getNome(), produto.getDescricao(), produto.getPreco());

            return ResponseEntity.ok(produtoDTO);
        }
        // Retorna 404 se o produto não foi encontrado
        return ResponseEntity.status(404).body("Produto não encontrado.");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable("id") UUID id) {
        // Verifica se o produto existe
        if (!produtosRepository.existsById(id)) {
            // Retorna 404 se o produto não foi encontrado
            return ResponseEntity.notFound().build();
        }

        // Exclui diretamente pelo ID
        produtosRepository.deleteById(id);

        // Retorna 204 (No Content) para indicar sucesso sem conteúdo no corpo
        return ResponseEntity.noContent().build();
    }


}
