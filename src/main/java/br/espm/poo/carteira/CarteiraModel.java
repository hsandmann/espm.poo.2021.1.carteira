package br.espm.poo.carteira;

import br.espm.poo.carteira.common.datatype.Carteira;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "carteira")
public class CarteiraModel {

    @Id
    @Column(name = "id_carteira")
    private String idCarteira;

    @Column(name = "id_usuario")
    private String idUsuario;

    @Column(name = "vr_saldo")
    private BigDecimal vrSaldo;

    public CarteiraModel() {

    }

    public CarteiraModel(Carteira c) {
        this.idCarteira = c.getId();
        // this.idUsuario = c.getUsuario.getId();
        this.vrSaldo = c.getSaldo();
    }

    public Carteira to() {
        // Usuario usuario = new Usuario();
        // usuario.setId(idUsuario);

        Carteira c = new Carteira();
        c.setId(this.idCarteira);
        c.setSaldo(this.vrSaldo);
        // c.setUsuario(usuario);
        return c;
    }

}
