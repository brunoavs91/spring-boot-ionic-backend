package com.bruno;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CursomcApplicationTests {

	@Test
	public void contextLoads() {

		final int a = 127;
		final int b = 10;
		final int c = 5;
		final boolean d = false;
		final boolean e = true;

		System.out.println("A) nao D");
		if (!d) {
			System.out.println("VERDADEIRO");
		} else {
			System.out.println("FALSO");
		}

	}

}
