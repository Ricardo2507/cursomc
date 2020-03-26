package com.ricardo.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.ricardo.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	// aqui chamaríamos um webservice que gerarria o boleto
    public void preeencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instante) {
        Calendar calendar = Calendar.getInstance();
        //Adiciona a data da criação do pedido
        calendar.setTime(instante);
        //No campo Dia da semana será adicionado mais 7 dias
        calendar.set(Calendar.DAY_OF_WEEK, 7);
        pagto.setDataVencimento(calendar.getTime());
    }
}
