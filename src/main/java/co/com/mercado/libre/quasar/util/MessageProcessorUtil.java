package co.com.mercado.libre.quasar.util;

import co.com.mercado.libre.quasar.exception.QuasarOperationErrors;
import co.com.mercado.libre.quasar.exception.UndeterminableMessageException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Diego Muñoz
 * @since 14-12-2020
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageProcessorUtil {
	
	/**
	 * Determines the message iterating all the messages and fulling the positions one by one
	 * 
	 * @param messages - Bidimentional String array with uncomplete messages 
	 * @return String with complete message
	 * @throws UndeterminableMessageException
	 */
	public static String getMessage(String[]... messages) {
		for (int i = 1; i < messages.length; i++) {
			if (messages[i].length != messages[i - 1].length)
				throw new UndeterminableMessageException(QuasarOperationErrors.DIFFERENT_ARRAY_LENGTH);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < messages[0].length; i++) {
			String valueToAdd = "";
			for(int j = 0; j < messages.length; j++) {
				if(!messages[j][i].trim().isEmpty() && !valueToAdd.isEmpty() && !valueToAdd.equals(messages[j][i].trim()))
					throw new UndeterminableMessageException(QuasarOperationErrors.DIFFERENT_WORD_AT_INDEX);
				valueToAdd = !messages[j][i].isEmpty() ? messages[j][i] : valueToAdd;
			}
			if(valueToAdd.equals("")) {
				throw new UndeterminableMessageException(QuasarOperationErrors.UNDETERMINABLE_WORD);
			}
			sb.append(valueToAdd).append(" ");
		}
		return sb.toString().trim();
	}
}
