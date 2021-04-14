package com.cepedi.curso;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import com.cepedi.curso.domain.Categoria;
import com.cepedi.curso.domain.Cidade;
import com.cepedi.curso.domain.Cliente;
import com.cepedi.curso.domain.Endereco;
import com.cepedi.curso.domain.Estado;
import com.cepedi.curso.domain.ItemPedido;
import com.cepedi.curso.domain.Pagamento;
import com.cepedi.curso.domain.PagamentoComBoleto;
import com.cepedi.curso.domain.PagamentoComCartao;
import com.cepedi.curso.domain.Pedido;
import com.cepedi.curso.domain.Produto;
import com.cepedi.curso.domain.enums.EstadoPagamento;
import com.cepedi.curso.domain.enums.TipoCliente;
import com.cepedi.curso.repositories.CategoriaRepository;
import com.cepedi.curso.repositories.CidadeRepository;
import com.cepedi.curso.repositories.ClienteRepository;
import com.cepedi.curso.repositories.EnderecoRepository;
import com.cepedi.curso.repositories.EstadoRepository;
import com.cepedi.curso.repositories.ItemPedidoRepository;
import com.cepedi.curso.repositories.PagamentoRepository;
import com.cepedi.curso.repositories.PedidoRepository;
import com.cepedi.curso.repositories.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursoApplication implements CommandLineRunner {
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {

		SpringApplication.run(CursoApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto p5 = new Produto(null, "toalha", 50.00);
		Produto p6 = new Produto(null, "colcha", 	200.00);
		Produto p7 = new Produto(null, "TV True Color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, " Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente ",180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);



		

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));	


		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));

		Estado e2 = new Estado(null, "Bahia");
		Estado e1 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Salvador", e2);
		Cidade c2 = new Cidade(null, "Sao Paulo", e1);
		Cidade c3 = new Cidade(null, "Ilheus", e2);

		e1.getCidades().addAll(Arrays.asList(c2));
		e2.getCidades().addAll(Arrays.asList(c1, c3));

		estadoRepository.saveAll(Arrays.asList(e1, e2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "00223556998", TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("40028922", "996699969"));

		Endereco end1 = new Endereco(null, "logradouro", "numero", "complemento", "bairro", "cep", cli1, c1);
		Endereco end2 = new Endereco(null, "Rua das dores", "75", "casa sem chão", "bairro das graças", "4569985", cli1,
				c2);
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		clienteRepository.saveAll(Arrays.asList(cli1));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 10:31"), cli1, end2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 12:30"),
				null);

		ped1.setPagamento(pagto1);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, p1.getPreco());
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 1, p3.getPreco());
		ItemPedido ip3 = new ItemPedido(ped2, p2, 0.00, 1, p2.getPreco());

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}

}
