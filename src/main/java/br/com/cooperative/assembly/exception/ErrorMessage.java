package br.com.cooperative.assembly.exception;

public class ErrorMessage {

        public static final String ERROR = "ERROR";

        protected ErrorMessage(){
            throw new IllegalArgumentException("ErrorMessage utility class");
        }
}
