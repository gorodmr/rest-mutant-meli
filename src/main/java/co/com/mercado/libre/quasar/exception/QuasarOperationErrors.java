package co.com.mercado.libre.quasar.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
public enum QuasarOperationErrors {
	DIFFERENT_WORD_AT_INDEX("Las estructuras de los mensajes son diferentes", HttpStatus.NOT_FOUND),
	DIFFERENT_ARRAY_LENGTH("Los mensajes poseen diferente tamaño", HttpStatus.BAD_REQUEST),
	UNDETERMINABLE_WORD("No se puede determinar una de las palabras del mensaje", HttpStatus.NOT_FOUND),
	SATELLITE_NOT_FOUND("El satelite no existe", HttpStatus.NOT_FOUND),
	DUPLICATE_SATELLITE_INFORMATION("Ya habian sido ingresados los datos de este satelite", HttpStatus.NOT_FOUND),
	DISTANCE_NOT_FOUND("No fue posible calcular la posición, faltan distancias", HttpStatus.NOT_FOUND);

	private String message;
	private HttpStatus statusCode;

}
