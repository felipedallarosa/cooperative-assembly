package br.com.cooperative.assembly.exception;

import java.time.LocalDateTime;
import java.util.Arrays;

import br.com.cooperative.assembly.domain.ErrorType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class BusinessException extends RuntimeException {

        private final ErrorResponse errorResponse;
        private final ErrorType type;

        public BusinessException(String message) {
            super(message);
            this.type = ErrorType.BUSINESS;
            errorResponse = new ErrorResponse(
                "ERROR",
                Arrays.asList(message),
                LocalDateTime.now()
            );
        }

}
