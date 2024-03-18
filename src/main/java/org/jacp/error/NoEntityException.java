package org.jacp.error;

/**
 * @author saffchen created on 18.03.2024
 */
public class NoEntityException extends RuntimeException {
    public NoEntityException(String messageError) {
        super(messageError);
    }
}