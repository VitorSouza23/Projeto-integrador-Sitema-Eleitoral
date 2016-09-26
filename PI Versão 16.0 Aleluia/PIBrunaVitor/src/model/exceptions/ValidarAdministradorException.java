/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.exceptions;

/**
 *
 * @author Aluno
 */
public class ValidarAdministradorException extends Exception {
private int tipe;
    /**
     * Creates a new instance of <code>ValidarAdministradorException</code>
     * without detail message.
     */
    public ValidarAdministradorException() {
    }

    /**
     * Constructs an instance of <code>ValidarAdministradorException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public ValidarAdministradorException(String msg) {
        super(msg);
    }

    public ValidarAdministradorException(int tipe, String message) {
        super(message);
        this.tipe = tipe;
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
