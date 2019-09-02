package com.bruno;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bruno.domain.Categoria;
import com.bruno.domain.Produto;
import com.bruno.repository.CategoriaRepository;
import com.bruno.repository.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
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
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
	}

}
