package com.bruno;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bruno.domain.Categoria;
import com.bruno.domain.Cidade;
import com.bruno.domain.Cliente;
import com.bruno.domain.Endereco;
import com.bruno.domain.Estado;
import com.bruno.domain.ItemPedido;
import com.bruno.domain.Pagamento;
import com.bruno.domain.PagamentoComBoleto;
import com.bruno.domain.PagamentoComCartao;
import com.bruno.domain.Pedido;
import com.bruno.domain.Produto;
import com.bruno.domain.enums.EstadoPagamento;
import com.bruno.domain.enums.TipoCliente;
import com.bruno.repository.CategoriaRepository;
import com.bruno.repository.CidadeRepository;
import com.bruno.repository.ClienteRepository;
import com.bruno.repository.EnderecoRepository;
import com.bruno.repository.EstadoRepository;
import com.bruno.repository.ItemPedidoRepository;
import com.bruno.repository.PagamentoRepository;
import com.bruno.repository.PedidoRepository;
import com.bruno.repository.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

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
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1= new Categoria(null, "Informatica");
		Categoria cat2= new Categoria(null, "Escritorio");
		Categoria cat3= new Categoria(null, "Cama Mesa e banho");
		Categoria cat4= new Categoria(null, "Eletronicos");
		Categoria cat5= new Categoria(null, "Jardinagem");
		Categoria cat6= new Categoria(null, "Decoraçao");
		Categoria cat7= new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null, "Computador", 2000D);
		
		Produto p2 = new Produto(null, "Impressora", 800D);
		Produto p3 = new Produto(null, "Mouse", 80D);
		Produto p4 = new Produto(null, "Mesa de Escritorio", 80D);
		Produto p5 = new Produto(null, "Toalha", 80D);
		Produto p6 = new Produto(null, "Colcha", 80D);
		Produto p7 = new Produto(null, "TV", 80D);
		Produto p8 = new Produto(null, "Rocadeira", 80D);
		Produto p9 = new Produto(null, "Abajour", 80D);
		Produto p10 = new Produto(null, "Pendente", 80D);
		Produto p11 = new Produto(null, "Shampoo", 80D);

		cat1.setProdutos(new ArrayList<>(Arrays.asList(p1, p2, p3)));
		cat2.setProdutos(new ArrayList<>(Arrays.asList(p2, p4)));
		cat3.setProdutos(new ArrayList<>(Arrays.asList(p5, p6)));
		cat4.setProdutos(new ArrayList<>(Arrays.asList(p1, p2, p3, p7)));
		cat5.setProdutos(new ArrayList<>(Arrays.asList(p8)));
		cat6.setProdutos(new ArrayList<>(Arrays.asList(p9, p10)));
		cat7.setProdutos(new ArrayList<>(Arrays.asList(p11)));
		
		p1.setCategorias(new ArrayList<>(Arrays.asList(cat1, cat4)));

		p2.setCategorias(new ArrayList<>(Arrays.asList(cat1, cat2, cat4)));

		p3.setCategorias(new ArrayList<>(Arrays.asList(cat1, cat4)));
		p4.setCategorias(new ArrayList<>(Arrays.asList(cat2)));
		p5.setCategorias(new ArrayList<>(Arrays.asList(cat3)));
		p6.setCategorias(new ArrayList<>(Arrays.asList(cat3)));
		p7.setCategorias(new ArrayList<>(Arrays.asList(cat4)));
		p8.setCategorias(new ArrayList<>(Arrays.asList(cat5)));
		p9.setCategorias(new ArrayList<>(Arrays.asList(cat6)));
		p10.setCategorias(new ArrayList<>(Arrays.asList(cat6)));
		p11.setCategorias(new ArrayList<>(Arrays.asList(cat7)));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia",est1);
		Cidade c2 = new Cidade(null, "Sao Paulo",est2);
		Cidade c3 =new Cidade(null, "Campinas",est2);
		
		est1.setCidades(new ArrayList<>(Arrays.asList(c1)));
		est2.setCidades(new ArrayList<>(Arrays.asList(c2,c3)));
		
		Cliente cli1= new Cliente(null,"Maria","maria@gmail.com", "23423423",TipoCliente.PESSOAFISICA);
		cli1.setTelefones(new HashSet<>(Arrays.asList("99999994","998989899")));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "32323232", cli1, c1);
		Endereco e2 = new Endereco(null,"Avenida Mtos","105","Sala 800", "Centro", "31223244", cli1, c2);
		
		cli1.setEnderecos(new ArrayList<>(Arrays.asList(e1,e2)));
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));
		
		clienteRepository.save(cli1);
		
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("09/11/2019 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/09/2019 11:32"), cli1, e2);
		
		Pagamento pagto1 =  new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2018  00:00"),null);
		
		ped2.setPagamento(pagto2);
		
		cli1.setPedidos(new ArrayList<Pedido>(Arrays.asList(ped1,ped2)));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1,0.00, 1,2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p3,100.00, 1, 800.00);
		
		ped1.getItens().addAll(new ArrayList<>(Arrays.asList(ip1,ip2)));
		ped2.getItens().addAll(new ArrayList<>(Arrays.asList(ip1,ip2)));
		
		
		p1.getItens().addAll(new ArrayList<>(Arrays.asList(ip1)));
		p2.getItens().addAll(new ArrayList<>(Arrays.asList(ip3)));
		p3.getItens().addAll(new ArrayList<>(Arrays.asList(ip2)));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}

}
