package br.com.cooperative.assembly.exception;

import java.time.LocalDateTime;
import java.util.Arrays;

import br.com.cooperative.assembly.domain.ErrorType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessingException extends RuntimeException {

        private final ErrorResponse errorResponse;
        private final ErrorType type;

        public ProcessingException(String message) {
            super(message);
            this.type = ErrorType.PROCESSING;
            errorResponse = new ErrorResponse(
                "OK",
                Arrays.asList(message),
                LocalDateTime.now()
            );
        }

}
