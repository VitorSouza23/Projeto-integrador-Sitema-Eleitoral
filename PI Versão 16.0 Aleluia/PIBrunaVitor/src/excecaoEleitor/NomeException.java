/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package excecaoEleitor;

/**
 *
 * @author Aluno
 */
public class NomeException extends Exception {

    /**
     * Creates a new instance of
     * <code>Nome</code> without detail message.
     */
    public NomeException() {
    }

    /**
     * Constructs an instance of
     * <code>Nome</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public NomeException(String msg) {
        super(msg);
    }
}
