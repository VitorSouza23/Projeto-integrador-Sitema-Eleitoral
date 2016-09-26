/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import conexao.exceptions.ConnectionException;
import conexao.exceptions.SQLStatementException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Candidato;
import model.exceptions.ValidacaoCandidatoException;

/**
 *
 * @author Aluno
 */
public interface IGerenciadorCandidato {
     public void salvar(Candidato c)throws SQLStatementException, ConnectionException, ValidacaoCandidatoException, IOException ;
     
     public void atualizar(Candidato c) throws ConnectionException, SQLStatementException, ValidacaoCandidatoException, IOException ;
     
     public void remover(Candidato c)throws ConnectionException, SQLStatementException;
     
     public ArrayList<Candidato> getTodosCandidatos()throws ConnectionException, SQLStatementException, SQLException, ValidacaoCandidatoException, IOException ;
     
     public  Candidato getCandidato(int numero)throws ConnectionException, SQLStatementException, ValidacaoCandidatoException, IOException;
}
