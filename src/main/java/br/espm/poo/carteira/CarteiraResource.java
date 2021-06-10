package br.espm.poo.carteira;

import br.espm.poo.cambio.common.controller.CambioController;
import br.espm.poo.cambio.common.datatype.Cotacao;
import br.espm.poo.cambio.common.datatype.Moeda;
import br.espm.poo.carteira.common.controller.CarteiraController;
import br.espm.poo.carteira.common.datatype.Carteira;
import br.espm.poo.carteira.common.datatype.TransacaoBean;
import br.espm.poo.carteira.common.datatype.TransacaoCambio;
import br.espm.poo.carteira.common.datatype.TransacaoTipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@EnableFeignClients(basePackages = "br.espm.poo.cambio.common.controller")
@RestController
public class CarteiraResource implements CarteiraController {

    @Autowired
    private CarteiraService carteiraService;

    @Autowired
    private TransacaoCambioService transacaoCambioService;

    @Override
    public List<Carteira> carteiras() {
        return null;
    }

    @Override
    public Carteira carteira(String id) {
        return carteiraService.findBy(id);
    }

    @Override
    public Carteira create(Carteira carteira) {
        Carteira c = new Carteira();
        c.setSaldo(BigDecimal.ZERO);
        return carteiraService.create(c);
    }

    @Override
    public TransacaoCambio cambioComprar(String idCarteira, TransacaoBean bean) {
        return transacaoCambioService.comprar(idCarteira, bean);
    }

    @Override
    public TransacaoCambio cambioVender(String idCarteira, TransacaoBean bean) {
        return transacaoCambioService.vender(idCarteira, bean);
    }

}
