package io.github.rodrigohirokii.produtosapi.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "produtos")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    UUID id;

    @Column(name = "nome")
    String nome;

    @Column(name = "descricao")
    String descricao;

    @Column(name = "preco")
    BigDecimal preco;

    @Column(name = "data_cadastro")
    @CreatedDate
    LocalDate dataCadastro;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    LocalDate dataAtualizacao;
}
