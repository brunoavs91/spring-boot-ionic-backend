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

	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
	}

}
