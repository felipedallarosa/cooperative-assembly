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
public class InvalidDocumentException extends RuntimeException {

        private final ErrorResponse errorResponse;
        private final ErrorType type;

        public InvalidDocumentException(String message) {
            super(message);
            this.type = ErrorType.NOT_FOUND;
            errorResponse = new ErrorResponse(
                "ERROR",
                Arrays.asList(message),
                LocalDateTime.now()
            );
        }

}
