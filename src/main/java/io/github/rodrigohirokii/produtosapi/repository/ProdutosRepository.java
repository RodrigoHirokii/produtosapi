package io.github.rodrigohirokii.produtosapi.repository;

import io.github.rodrigohirokii.produtosapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutosRepository extends JpaRepository<Produto, UUID> {

    boolean existsByNome(String nome);

    Optional<Produto> findByNomeAndDescricaoAndPreco(String nome, String descricao, BigDecimal preco);


    List<Produto> findByOrderByPrecoAsc();
}
