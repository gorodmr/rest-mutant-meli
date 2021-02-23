package co.com.mercado.libre.quasar;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { QuasarOperationApplication.class })
public class QuasarFireOperationApplicationTests {

	@Test
	public void main() {
		QuasarOperationApplication.main(new String[] {});
		assertTrue(true);
	}

}
