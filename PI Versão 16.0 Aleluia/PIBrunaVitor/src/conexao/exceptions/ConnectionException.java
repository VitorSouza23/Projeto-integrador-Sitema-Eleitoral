/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao.exceptions;

/**
 *
 * @author Marcos
 */
public class ConnectionException extends Exception {

    /**
     * Creates a new instance of
     * <code>ConnectionException</code> without detail message.
     */
    public ConnectionException() {
    }

    /**
     * Constructs an instance of
     * <code>ConnectionException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ConnectionException(String msg) {
        super(msg);
    }
    
    /**
     * Constructs an instance of 
     * <code>ConnectionException</code> whit the specified detail message and cause.
     * @param msg
     * @param cause 
     */
    public ConnectionException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
