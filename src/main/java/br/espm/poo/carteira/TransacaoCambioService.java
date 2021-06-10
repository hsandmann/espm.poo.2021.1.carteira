package br.espm.poo.carteira;

import br.espm.poo.cambio.common.controller.CambioController;
import br.espm.poo.cambio.common.datatype.Cotacao;
import br.espm.poo.cambio.common.datatype.Moeda;
import br.espm.poo.carteira.common.datatype.Carteira;
import br.espm.poo.carteira.common.datatype.TransacaoBean;
import br.espm.poo.carteira.common.datatype.TransacaoCambio;
import br.espm.poo.carteira.common.datatype.TransacaoTipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TransacaoCambioService {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    private CambioController cambioController;

    @Autowired
    private CarteiraService carteiraService;

    @Autowired
    private TransacaoCambioRepository transacaoCambioRepository;

    public List<TransacaoCambio> listByCarteira(String idCarteira) {
        List<TransacaoCambio> l = transacaoCambioRepository
                .listByCarteira(idCarteira).stream()
                .map(TransacaoCambioModel::to)
                .collect(Collectors.toList());
        return l;
    }

    public TransacaoCambio comprar(String idCarteira, TransacaoBean bean) {
        Carteira c = carteiraService.findBy(idCarteira);
        if (c == null) {
            throw new RuntimeException("Carteira nao existe: " + idCarteira);
        }

        Date agora = new Date();

        Moeda moeda = cambioController.moedas(bean.getSimbolo());
        if (moeda == null) {
            throw new RuntimeException("Moeda nao existe: " + bean.getSimbolo());
        }

        Cotacao cotacao = cambioController.cotacao(moeda.getSimbolo(), sdf.format(agora));
        if (cotacao == null) {
            throw new RuntimeException("Cotacao nao existe: " + sdf.format(agora));
        }

        TransacaoCambio tc = new TransacaoCambio();
        tc.setId(UUID.randomUUID().toString());
        tc.setCarteira(c);
        tc.setCotacao(cotacao);
        tc.setTipo(TransacaoTipo.COMPRA);
        tc.setQuantidade(bean.getQtd());
        tc.setData(agora);

        return transacaoCambioRepository.save(new TransacaoCambioModel(tc)).to();
    }

    public TransacaoCambio vender(String idCarteira, TransacaoBean bean) {
        Carteira c = carteiraService.findBy(idCarteira);
        if (c == null) {
            throw new RuntimeException("Carteira nao existe: " + idCarteira);
        }

        Date agora = new Date();

        Moeda moeda = cambioController.moedas(bean.getSimbolo());
        if (moeda == null) {
            throw new RuntimeException("Moeda nao existe: " + bean.getSimbolo());
        }

        Cotacao cotacao = cambioController.cotacao(moeda.getSimbolo(), sdf.format(agora));
        if (cotacao == null) {
            throw new RuntimeException("Cotacao nao existe: " + sdf.format(agora));
        }

        if (BigDecimal.ZERO.compareTo(bean.getLimite()) <= 0
                && bean.getLimite().compareTo(cotacao.getValor()) > 0) {
            throw new RuntimeException("Cotacao limite, atual: " + cotacao.getValor());
        }

        TransacaoCambio tc = new TransacaoCambio();
        tc.setId(UUID.randomUUID().toString());
        tc.setCarteira(c);
        tc.setCotacao(cotacao);
        tc.setTipo(TransacaoTipo.VENDA);
        tc.setQuantidade(bean.getQtd());
        tc.setData(agora);

        return transacaoCambioRepository.save(new TransacaoCambioModel(tc)).to();
    }

}
