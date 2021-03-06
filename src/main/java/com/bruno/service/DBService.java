package com.bruno.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
import com.bruno.domain.enums.Perfil;
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

@Service
public class DBService {
	
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
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	

	public void instantiateTestDataBase() throws Exception {
		
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
//		Produto p12 = new Produto(null, "Produto 12", 10.00);
//		Produto p13 = new Produto(null, "Produto 13", 10.00);
//		Produto p14 = new Produto(null, "Produto 14", 10.00);
//		Produto p15 = new Produto(null, "Produto 15", 10.00);
//		Produto p16 = new Produto(null, "Produto 16", 10.00);
//		Produto p17 = new Produto(null, "Produto 17", 10.00);
//		Produto p18 = new Produto(null, "Produto 18", 10.00);
//		Produto p19 = new Produto(null, "Produto 19", 10.00);
//		Produto p20 = new Produto(null, "Produto 20", 10.00);
//		Produto p21 = new Produto(null, "Produto 21", 10.00);
//		Produto p22 = new Produto(null, "Produto 22", 10.00);
//		Produto p23 = new Produto(null, "Produto 23", 10.00);
//		Produto p24 = new Produto(null, "Produto 24", 10.00);
//		Produto p25 = new Produto(null, "Produto 25", 10.00);
//		Produto p26 = new Produto(null, "Produto 26", 10.00);
//		Produto p27 = new Produto(null, "Produto 27", 10.00);
//		Produto p28 = new Produto(null, "Produto 28", 10.00);
//		Produto p29 = new Produto(null, "Produto 29", 10.00);
//		Produto p30 = new Produto(null, "Produto 30", 10.00);
//		Produto p31 = new Produto(null, "Produto 31", 10.00);
//		Produto p32 = new Produto(null, "Produto 32", 10.00);
//		Produto p33 = new Produto(null, "Produto 33", 10.00);
//		Produto p34 = new Produto(null, "Produto 34", 10.00);
//		Produto p35 = new Produto(null, "Produto 35", 10.00);
//		Produto p36 = new Produto(null, "Produto 36", 10.00);
//		Produto p37 = new Produto(null, "Produto 37", 10.00);
//		Produto p38 = new Produto(null, "Produto 38", 10.00);
//		Produto p39 = new Produto(null, "Produto 39", 10.00);
//		Produto p40 = new Produto(null, "Produto 40", 10.00);
//		Produto p41 = new Produto(null, "Produto 41", 10.00);
//		Produto p42 = new Produto(null, "Produto 42", 10.00);
//		Produto p43 = new Produto(null, "Produto 43", 10.00);
//		Produto p44 = new Produto(null, "Produto 44", 10.00);
//		Produto p45 = new Produto(null, "Produto 45", 10.00);
//		Produto p46 = new Produto(null, "Produto 46", 10.00);
//		Produto p47 = new Produto(null, "Produto 47", 10.00);
//		Produto p48 = new Produto(null, "Produto 48", 10.00);
//		Produto p49 = new Produto(null, "Produto 49", 10.00);
//		Produto p50 = new Produto(null, "Produto 50", 10.00);

		cat1.setProdutos(new ArrayList<>(Arrays.asList(p1, p2, p3)));
//		cat1.getProdutos()
//				.addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27,
//						p28, p29, p30, p31, p32, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47,
//						p48, p49, p50));
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
		
//		p12.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p13.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p14.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p15.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p16.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p17.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p18.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p19.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p20.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p21.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p22.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p23.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p24.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p25.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p26.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p27.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p28.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p29.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p30.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p31.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p32.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p33.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p34.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p35.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p36.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p37.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p38.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p39.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p40.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p41.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p42.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p43.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p44.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p45.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p46.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p47.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p48.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p49.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
//		p50.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
		
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia",est1);
		Cidade c2 = new Cidade(null, "Sao Paulo",est2);
		Cidade c3 =new Cidade(null, "Campinas",est2);
		
		est1.setCidades(new ArrayList<>(Arrays.asList(c1)));
		est2.setCidades(new ArrayList<>(Arrays.asList(c2,c3)));
		
		Cliente cli1= new Cliente(null,"Bruno","brunoav91@gmail.com", "23423423",TipoCliente.PESSOAFISICA,passwordEncoder.encode("123"));
		cli1.setTelefones(new HashSet<>(Arrays.asList("99999994","998989899")));
		cli1.addPerfil(Perfil.ADMIN);
		Cliente cli2= new Cliente(null,"Joao","brunobh91@gmail.com", "234267423",TipoCliente.PESSOAFISICA,passwordEncoder.encode("123"));
		cli2.setTelefones(new HashSet<>(Arrays.asList("99999994","998989899")));
		cli2.addPerfil(Perfil.CLIENTE);
		
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "32323232", cli1, c1);
		Endereco e2 = new Endereco(null,"Avenida Mtos","105","Sala 800", "Centro", "31223244", cli1, c2);
		Endereco e3 = new Endereco(null,"Avenida Doida","104","Apto 402", "Centro", "31226244", cli2, c2);
		
		cli1.setEnderecos(new ArrayList<>(Arrays.asList(e1,e2)));
		cli2.setEnderecos(new ArrayList<>(Arrays.asList(e3)));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));
//		produtoRepository.saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
//				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
//				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		
		clienteRepository.saveAll(Arrays.asList(cli1,cli2));
		
		enderecoRepository.saveAll(Arrays.asList(e1,e2,e3));
		
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
