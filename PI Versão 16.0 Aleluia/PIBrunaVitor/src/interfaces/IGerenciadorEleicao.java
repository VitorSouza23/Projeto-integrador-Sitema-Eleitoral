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
import java.sql.SQLException;
import java.util.ArrayList;
import model.Eleicao;
import model.exceptions.ValidacaoCandidatoException;
import model.exceptions.ValidarCursoException;
import model.exceptions.ValidarEleicaoException;
import model.exceptions.ValidarEmailException;

/**
 *
 * @author Aluno
 */
public interface IGerenciadorEleicao{
    public void salvar(Eleicao e) throws ConnectionException, SQLStatementException, NomeException, ValidacaoCandidatoException, 
            ValidarEleicaoException, ValidarEmailException, MatriculaException, ValidarCursoException;
    
    public void atualizar(Eleicao e) throws ConnectionException, SQLStatementException, NomeException, ValidacaoCandidatoException, ValidarEleicaoException, 
            ValidarEmailException, MatriculaException, ValidarCursoException;
    
    public void remover(Eleicao e) throws ConnectionException, SQLStatementException;
    
    public ArrayList<Eleicao> getTodosEleicoes() throws ConnectionException, SQLStatementException, SQLException, ValidacaoCandidatoException,
            ValidarEleicaoException, ValidarEmailException, MatriculaException, ValidarCursoException;
    
    public  Eleicao getEleicao(int id) throws ConnectionException, SQLStatementException, ValidacaoCandidatoException, ValidarEleicaoException, 
            ValidarEmailException, MatriculaException, ValidarCursoException;
    
    
}
