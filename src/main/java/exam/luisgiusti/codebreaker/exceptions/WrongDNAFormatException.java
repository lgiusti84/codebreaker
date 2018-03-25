package exam.luisgiusti.codebreaker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongDNAFormatException extends RuntimeException {

	public WrongDNAFormatException() {
		super();
	}

	public WrongDNAFormatException(String message) {
		super(message);
	}

	public WrongDNAFormatException(String message, Throwable cause) {
		super(message, cause);
	}

}
