package com.bruno.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bruno.domain.Cliente;
import com.bruno.domain.Pedido;



@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	@org.springframework.transaction.annotation.Transactional(readOnly =true)
	Page<Pedido> findByCliente(Cliente cliente ,PageRequest pageRequest);

}
