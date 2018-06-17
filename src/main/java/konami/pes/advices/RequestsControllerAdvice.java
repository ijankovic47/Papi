package konami.pes.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import konami.pes.exceptions.FieldErrorException;
import konami.pes.exceptions.NoSuchEntityException;

@ControllerAdvice
public class RequestsControllerAdvice {

	@ExceptionHandler(NoSuchEntityException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ResponseBody
	public String resourceNotFoundResponse(NoSuchEntityException exception) {

		return exception.getMessage();
	}
	@ExceptionHandler(FieldErrorException.class)
	@ResponseStatus(code= HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String fieldErrorResponse(FieldErrorException exception){
		
		return exception.getMessage();
	}
}
