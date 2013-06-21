package dk.es.br.vies;

/**
 * @author      osa
 * @since       21-06-2013
 */
public class ViesVatServiceException extends Exception {
    private String errorKey;

    public ViesVatServiceException(String key, String message) {
        super(message);
        this.errorKey = key;
    }

    public String getErrorKey() {
        return errorKey;
    }
}
