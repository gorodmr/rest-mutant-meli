package co.com.mercado.libre.quasar.application;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.mercado.libre.quasar.exception.UndeterminableMessageException;
import co.com.mercado.libre.quasar.util.MessageProcessorUtil;

@RunWith(SpringRunner.class)
public class MessageProcessorUtilTest {
	
	private final String[] message1 = new String[] {"este", "", "", "mensaje"};
	private final String[] message2 = new String[] {"", "es", "", ""};
	private String[] message3 = new String[] {"", "es", "un", ""};

	@Test
	public void whenMessagesAreOk_thenReturnCompleteMessage() {
		String message = MessageProcessorUtil.getMessage(message1, message2, message3);
		Assert.assertEquals("este es un mensaje", message);
	}
	
	@Test(expected = UndeterminableMessageException.class)
	public void whenMessagesWithDifferentWordsAtSamePosition_thenReturnException() {
		message3 = new String[] {"", "el", "un", ""};
		MessageProcessorUtil.getMessage(message1, message2, message3);
	}
	
	@Test(expected = UndeterminableMessageException.class)
	public void whenArrayMessagesHaveDifferentLength_thenReturnException() {
		message3 = new String[] {"", "es", "un", "", ""};
		MessageProcessorUtil.getMessage(message1, message2, message3);
	}
	
	@Test(expected = UndeterminableMessageException.class)
	public void whenMessagesHaveOneUndeterminableWord_thenReturnException() {
		message3 = new String[] {"", "es", "", ""};
		MessageProcessorUtil.getMessage(message1, message2, message3);
	}
}
