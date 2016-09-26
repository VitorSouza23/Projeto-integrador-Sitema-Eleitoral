/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import conexao.exceptions.ConnectionException;
import conexao.exceptions.SQLStatementException;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Administrador;
import model.exceptions.ValidarAdministradorException;

/**
 *
 * @author Cliente
 */
public interface IGerenciadorAdministrador {
    public void salvar(Administrador a) throws SQLStatementException, ConnectionException;
    public void atualizar(Administrador a) throws ConnectionException, SQLStatementException;
    public void remover(Administrador a) throws ConnectionException, SQLStatementException;
    public ArrayList<Administrador> getTodosAdministradores() throws ConnectionException, SQLStatementException, SQLException, ValidarAdministradorException;
    public Administrador getAdministrador(int id) throws ConnectionException, SQLStatementException, SQLException, ValidarAdministradorException;
}
