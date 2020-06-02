package com.bruno;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CursomcApplicationTests {

	@Test
	public void contextLoads() {

		
		int quantidadeCarros=15;
		String[] fabricantes = new String[quantidadeCarros];
		String[] modelos = new String[quantidadeCarros];
		String[] cores = new String[quantidadeCarros];
		
		for(int i =0; i <= quantidadeCarros; i++) {
			
			System.out.println("DIGITE O NUMERO DO FABRICANTE");
			fabricantes[i] = "Fabricante"+i;
			
			System.out.println("DIGITE O NUMERO DO MODELO");
			modelos[i] = "Modelo"+i;
			
			System.out.println("DIGITE A COR");
			cores[i] = "Cor"+i;
			
		}

		//Esse for e para vc ver o q tem dentro dos Arrays 
		
		for(int i =0; i <= fabricantes.length; i++) {
			
			System.out.println("Fabricantes :"+fabricantes[i] +"-" + "Modelos" + modelos[i] + "-" + "Cores" + cores[i]);
		}


	}
	@Test
	public void metodoTeste() {

		int quantidadeCarros = 3;
		String[] fabricantes = new String[quantidadeCarros];
		String[] modelos = new String[quantidadeCarros];
		String[] cores = new String[quantidadeCarros];
		int[] valor = new int[quantidadeCarros];

		for (int i = 0; i <= quantidadeCarros; i++) {

			System.out.println("DIGITE O NUMERO DO FABRICANTE");
			fabricantes[i] = "Fabricante" + i;

			System.out.println("DIGITE O NUMERO DO MODELO");
			modelos[i] = "Modelo" + i;

			System.out.println("DIGITE A COR");
			cores[i] = "Cor" + i;
			
			System.out.println("VALOR :");
			valor[i] = i;

		}

		String v ="asd";
		// Esse for e para vc ver o q tem dentro dos Arrays

		for (int i = 0; i <= quantidadeCarros -1; i++) {

			System.out.println(
					"Fabricantes :" + fabricantes[i] + "-" + "Modelos" + modelos[i] + "-" + "Cores" + cores[i] + "Valor :" + valor[i]);
			
		}
		int total=0;
		for (int i = 0; i <= valor.length -1; i++) {
			 total += valor[i];
			 // e a mesma coisa que total = total + valor[i];
		}
		System.out.println("Total valor de carros somados :" +total);
		
		
	}

}
