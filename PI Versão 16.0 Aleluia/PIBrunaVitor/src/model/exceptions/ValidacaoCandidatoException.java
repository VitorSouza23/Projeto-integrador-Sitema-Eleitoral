/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.exceptions;

/**
 *
 * @author Cliente
 */
public class ValidacaoCandidatoException extends Exception {

    private int tipe;

    /**
     * Creates a new instance of
     * <code>ValidacaoCandidatoException</code> without detail message.
     */
    public ValidacaoCandidatoException() {
    }

    public ValidacaoCandidatoException(int tipe, String string) {
        super(string);
        this.tipe = tipe;
    }

    /**
     * Constructs an instance of
     * <code>ValidacaoCandidatoException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public ValidacaoCandidatoException(String msg) {
        super(msg);
    }

    /**
     * @return the tipe
     */
    public int getTipe() {
        return tipe;
    }

    /**
     * @param tipe the tipe to set
     */
    public void setTipe(int tipe) {
        this.tipe = tipe;
    }
}
