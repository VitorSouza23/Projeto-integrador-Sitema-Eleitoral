/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package excecaoEleitor;

/**
 *
 * @author Aluno
 */
public class MatriculaException extends Exception {

    /**
     * Creates a new instance of
     * <code>Matricula</code> without detail message.
     */
    public MatriculaException() {
    }

    /**
     * Constructs an instance of
     * <code>Matricula</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public MatriculaException(String msg) {
        super(msg);
    }
}
