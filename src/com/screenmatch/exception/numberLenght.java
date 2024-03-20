package com.screenmatch.exception;

public class numberLenght extends RuntimeException {
        private String mensagem;

        public numberLenght(String mensagem) {
            this.mensagem = mensagem;
        }

        @Override
        public String getMessage() {
            return this.mensagem;
        }
}
