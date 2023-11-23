package br.com.fatecdiadema.projetotoyoyamongodb.exception;

public class PostoCollectionException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public PostoCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String id) {
        return "Posto com id " + id + " não encontrado";
    }

    public static String NomeAlreadyExists() {
        return "Nome de posto já existe";
    }

    public static String EmailAlreadyExists() {
        return "Email já existe";
    }

    public static String CNPJAlreadyExists() {
        return "CNPJ já existe";
    }
}
