package br.com.cooperative.assembly.exception;

import java.time.LocalDateTime;
import java.util.Arrays;

import br.com.cooperative.assembly.domain.ErrorType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FeignExceptionException extends RuntimeException {

        private final ErrorResponse errorResponse;
        private final ErrorType type;

        public FeignExceptionException(String message) {
            super(message);
            this.type = ErrorType.INTERNAL_ERROR;
            errorResponse = new ErrorResponse(
                "ERROR",
                Arrays.asList(message),
                LocalDateTime.now()
            );
        }

}
