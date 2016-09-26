/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao.exceptions;

/**
 *
 * @author Marcos
 */
public class SQLStatementException extends Exception {

    /**
     * Creates a new instance of
     * <code>SQLStatementException</code> without detail message.
     */
    public SQLStatementException() {
    }

    /**
     * Constructs an instance of
     * <code>SQLStatementException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public SQLStatementException(String msg) {
        super(msg);
    }
    
    /**
     * Constructs an instance of
     * <code>SQLStatementException</code> with the specified detail message and cause.
     * @param msg
     * @param cause 
     */
    public SQLStatementException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
