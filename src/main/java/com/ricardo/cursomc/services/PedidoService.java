package com.ricardo.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ricardo.cursomc.domain.ItemPedido;
import com.ricardo.cursomc.domain.PagamentoComBoleto;
import com.ricardo.cursomc.domain.Pedido;
import com.ricardo.cursomc.domain.enums.EstadoPagamento;
import com.ricardo.cursomc.repositories.ItemPedidoRepository;
import com.ricardo.cursomc.repositories.PagamentoRepository;
import com.ricardo.cursomc.repositories.PedidoRepository;

import com.ricardo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	// Essa anotação Autowired instância automaticamente a classe PedidoRepository
	@Autowired
	private PedidoRepository repositorio;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ClienteService clienteService;

	/*
	 * @Autowired private EmailService emailService;
	 */
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repositorio.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());

		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);

		// Se o pagamento do obj é uma instância de PagamentoComBoleto
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preeencherPagamentoComBoleto(pagto, obj.getInstante());
		}

		repositorio.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0d);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		System.out.println(obj);
		/* emailService.sandOrderConfirmationEmail(obj); */
		return obj;
	}

}
