package br.espm.poo.carteira;

import br.espm.poo.cambio.common.datatype.Cotacao;
import br.espm.poo.carteira.common.datatype.Carteira;
import br.espm.poo.carteira.common.datatype.TransacaoCambio;
import br.espm.poo.carteira.common.datatype.TransacaoTipo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "transacaocambio")
public class TransacaoCambioModel {

    @Id
    @Column(name = "id_transacaocambio")
    private String idTransacaoCambio;

    @Column(name = "id_carteira")
    private String idCarteira;

    @Column(name = "id_cotacao")
    private String idCotacao;

    @Column(name = "dt_data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtData;

    @Column(name = "nr_qtd")
    private BigDecimal nrQtd;

    @Column(name = "nr_tipo")
    private Integer nrTipo;

    public TransacaoCambioModel() {

    }

    public TransacaoCambioModel(TransacaoCambio tc) {
        this.idTransacaoCambio = tc.getId();
        this.dtData = tc.getData();
        this.idCarteira = tc.getCarteira().getId();
        this.idCotacao = tc.getCotacao().getId();
        this.nrTipo = tc.getTipo().equals(TransacaoTipo.VENDA) ? 1 : 2;
        this.nrQtd = tc.getQuantidade();
    }

    public TransacaoCambio to() {
        Carteira carteira = new Carteira();
        carteira.setId(idCarteira);

        Cotacao cotacao = new Cotacao();
        cotacao.setId(idCotacao);

        TransacaoCambio tc = new TransacaoCambio();
        tc.setId(idTransacaoCambio);
        tc.setData(dtData);
        tc.setQuantidade(nrQtd);
        tc.setTipo(1 == nrTipo ? TransacaoTipo.VENDA : TransacaoTipo.COMPRA);
        tc.setCarteira(carteira);
        tc.setCotacao(cotacao);
        return tc;
    }

}
