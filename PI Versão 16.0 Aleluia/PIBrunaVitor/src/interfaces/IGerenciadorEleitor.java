/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import conexao.exceptions.ConnectionException;
import conexao.exceptions.SQLStatementException;
import excecaoEleitor.MatriculaException;
import excecaoEleitor.NomeException;
import java.util.ArrayList;
import model.Eleitor;
import model.exceptions.ValidarCursoException;
import model.exceptions.ValidarEmailException;

/**
 *
 * @author Aluno
 */
public interface IGerenciadorEleitor {
    public void inserir(Eleitor eleitor) throws SQLStatementException, ConnectionException, NomeException, MatriculaException, ValidarEmailException, ValidarCursoException;
    
    public void atualizar(Eleitor eleitor) throws SQLStatementException, ConnectionException, NomeException, MatriculaException, ValidarEmailException, ValidarCursoException;
    
    public void remover(Eleitor eleitor) throws SQLStatementException, ConnectionException, NomeException;
    
    public ArrayList<Eleitor> obterTodos() throws SQLStatementException, ConnectionException, NomeException, MatriculaException, ValidarEmailException, ValidarCursoException;
    
    public Eleitor obterEleitor(int id) throws SQLStatementException, ConnectionException, NomeException, MatriculaException, ValidarEmailException, ValidarCursoException;
}
