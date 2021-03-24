package br.com.alex.ondecomprei.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String site;
    private String preco;
    private String observacoes;
    @ManyToOne
    private Usuario usuario;
}
