package br.com.alex.ondecomprei.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Usuario implements Serializable {
    @Id
    private String email;
    private String senha;
    @OneToMany(mappedBy = "usuario")
    private List<Compra> compras;
}
