package com.bruno;

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
import com.bruno.domain.Produto;
import com.bruno.domain.enums.TipoCliente;
import com.bruno.repository.CategoriaRepository;
import com.bruno.repository.CidadeRepository;
import com.bruno.repository.ClienteRepository;
import com.bruno.repository.EnderecoRepository;
import com.bruno.repository.EstadoRepository;
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
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1= new Categoria(null, "Informatica");
		Categoria cat2= new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000D);
		
		Produto p2 = new Produto(null, "Impressora", 800D);
		Produto p3 = new Produto(null, "Mouse", 80D);
		
		cat1.setProdutos(new ArrayList<>(Arrays.asList(p1,p2,p3)));
		
		cat2.setProdutos(new ArrayList<>(Arrays.asList(p2)));
		
		p1.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
		
		p2.setCategorias(new ArrayList<>(Arrays.asList(cat1,cat2)));
		
		p3.setCategorias(new ArrayList<>(Arrays.asList(cat1)));
		
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
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		clienteRepository.save(cli1);
		
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		
	}

}
