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
public class ValidarCursoException extends Exception {

    /**
     * Creates a new instance of <code>ValidarCursoException</code> without
     * detail message.
     */
    public ValidarCursoException() {
    }

    /**
     * Constructs an instance of <code>ValidarCursoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ValidarCursoException(String msg) {
        super(msg);
    }
}
