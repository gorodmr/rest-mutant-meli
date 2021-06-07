package com.meli.mutant;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { MutantDNAApplication.class })
public class MutantDNAApplicationTests {

	@Test
	public void main() {
		MutantDNAApplication.main(new String[] {});
		assertTrue(true);
	}



}
