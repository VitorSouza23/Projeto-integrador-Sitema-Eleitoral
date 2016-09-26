/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import conexao.exceptions.ConnectionException;
import conexao.exceptions.SQLStatementException;
import excecaoEleitor.NomeException;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Fiscal;
import model.exceptions.ValidarEmailException;

/**
 *
 * @author Aluno
 */
public interface IGerenciadorDeFiscal {
    public void inserir(Fiscal fiscal) throws SQLStatementException, ConnectionException, NomeException, ValidarEmailException;
    
    public void atualizar(Fiscal fiscal) throws SQLStatementException, ConnectionException, NomeException, ValidarEmailException;
    
    public void remover(Fiscal fiscal) throws SQLStatementException, ConnectionException, NomeException;
    
    public ArrayList<Fiscal> obterTodos() throws SQLStatementException, ConnectionException, NomeException, SQLException, ValidarEmailException;
    
    public Fiscal obterFiscal(String nome) throws ConnectionException, NomeException,SQLStatementException, ValidarEmailException;
}
