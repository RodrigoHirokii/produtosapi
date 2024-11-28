package io.github.rodrigohirokii.produtosapi.repository;

import io.github.rodrigohirokii.produtosapi.model.Produto;
import jakarta.persistence.Table;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class ProdutoRepositoryTest {

    @Autowired
    ProdutosRepository produtosRepository;

    @Test
    public void adicionarProduto() {
        Produto produto = new Produto();
        produto.setNome("Monitor Ultragear LG");
        produto.setDescricao("Monitor 144hz");
        produto.setPreco(BigDecimal.valueOf(1500));

        produtosRepository.save(produto);
    }

    @Test
    public void possivelProduto() {
        var id = UUID.fromString("5eac34e5-dee8-413e-8232-08c66f2b1ef5");

        Optional<Produto> possivelProduto = produtosRepository.findById(id);

        if (possivelProduto.isPresent()) {
            Produto produto = possivelProduto.get();

            System.out.println("Dados do Produto");
            System.out.println(produto.getNome());
            System.out.println(produto.getDescricao());
            System.out.println(produto.getPreco());
        }

    }

    @Test
    public void existePorNome() {
        var possivelNome = "TV LG";

        var nome = produtosRepository.existsByNome(possivelNome);

        System.out.println(nome);
    }

    @Test
    public void pesquisarPorNomeEDescricao() {
        Optional<Produto> tvLg = produtosRepository.findByNomeAndDescricao("TV LG", "Tv 45 polegadas com IA");


        if (tvLg.isPresent()) {
            Produto produto = tvLg.get();

            System.out.println("Dados do produto: ");
            System.out.println(produto.getNome());
            System.out.println(produto.getDescricao());
        }


    }

    @Test
    public void pesquisarProdutosOrdenado() {
        produtosRepository.findByOrderByPrecoAsc().stream().map(produto -> "Produto atual: " + produto.getPreco())
                .forEach(System.out::println);
    }
}
