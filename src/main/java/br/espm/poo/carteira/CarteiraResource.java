package br.espm.poo.carteira;

import br.espm.poo.cambio.common.controller.CambioController;
import br.espm.poo.cambio.common.datatype.Cotacao;
import br.espm.poo.carteira.common.controller.CarteiraController;
import br.espm.poo.carteira.common.datatype.Carteira;
import br.espm.poo.carteira.common.datatype.TransacaoCambio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@EnableFeignClients(basePackages = "br.espm.poo.cambio.common.controller")
@RestController
public class CarteiraResource implements CarteiraController {

    @Autowired
    private CambioController cambioController;

    @Override
    public Carteira carteira(String id) {
        Carteira carteira = new Carteira();

        Cotacao cotacao = new Cotacao();
        cotacao.setId(UUID.randomUUID().toString());

        // Magica tem que acontecer
        cotacao.setMoeda(cambioController.moedas().get(0));


        cotacao.setData(new Date());
        cotacao.setValor(6.7);

        TransacaoCambio tCambio = new TransacaoCambio();
        tCambio.setCarteira(null); // HATEOS
        tCambio.setData(new Date());
        tCambio.setCotacao(cotacao);
        tCambio.setQuantidade(100.0);

        List<TransacaoCambio> listTransacoesCambio = new ArrayList<>();
        listTransacoesCambio.add(tCambio);

        carteira.setTransacoesCambio(listTransacoesCambio);
        return carteira;
    }

}
