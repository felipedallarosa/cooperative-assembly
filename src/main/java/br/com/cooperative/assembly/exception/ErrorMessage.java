package br.com.cooperative.assembly.exception;

public class ErrorMessage {

        public static final String OK = "OK";
        public static final String ERROR = "ERROR";
        public static final String WARNING = "WARNING";
        public static final String SUCCESS = "SUCCESS";

        private ErrorMessage(){
            throw new IllegalArgumentException("ErrorMessage utility class");
        }
}
