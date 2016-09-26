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
public class ValidarEmailException extends Exception {

    /**
     * Creates a new instance of <code>ValidarEmailException</code> without
     * detail message.
     */
    public ValidarEmailException() {
    }

    /**
     * Constructs an instance of <code>ValidarEmailException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ValidarEmailException(String msg) {
        super(msg);
    }
}
