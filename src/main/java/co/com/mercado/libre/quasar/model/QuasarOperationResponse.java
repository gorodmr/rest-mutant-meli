package co.com.mercado.libre.quasar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class QuasarOperationResponse extends GenericResponseDto{
	private Position position;

	@Builder
	public QuasarOperationResponse(Position position, String message){
		super(message);
		this.position = position;
	}

}
