/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.exceptions;

/**
 *
 * @author Cliente
 */
public class ValidarEleicaoException extends Exception {
private int type;
    /**
     * Creates a new instance of
     * <code>ValidarEleicaoException</code> without detail message.
     */
    public ValidarEleicaoException() {
    }

    /**
     * Constructs an instance of
     * <code>ValidarEleicaoException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ValidarEleicaoException(String msg) {
        super(msg);
    }

    public ValidarEleicaoException(int type, String string) {
        super(string);
        this.type = type;
    }
    

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }
}
