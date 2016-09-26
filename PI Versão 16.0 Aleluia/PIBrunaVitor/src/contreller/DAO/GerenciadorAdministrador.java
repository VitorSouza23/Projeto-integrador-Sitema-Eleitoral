/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package contreller.DAO;

import conexao.exceptions.ConnectionException;
import conexao.exceptions.SQLStatementException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Administrador;
import model.exceptions.ValidarAdministradorException;

/**
 *
 * @author Cliente
 */
public class GerenciadorAdministrador implements interfaces.IGerenciadorAdministrador{
    @Override
    public void salvar(Administrador a) throws SQLStatementException, ConnectionException {
        String salvar = "INSERT INTO administrador (usuario, senha)"
                + " values (?, ?);";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(salvar);
            ps.setString(1, a.getUsuario());
            ps.setString(2, a.getSenha());
            

            ps.executeUpdate();
        } catch (SQLException ex) {
            
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + salvar + "\n"
                    + ex.getMessage(), ex.getCause());
        }
    }
    @Override
    public void atualizar(Administrador a) throws ConnectionException, SQLStatementException {
        String atualizar = "UPDATE administrador SET USUARIO = ?, SENHA = ?"
                + " where id = ?;";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(atualizar);
            ps.setString(1, a.getUsuario());
            ps.setString(2, a.getSenha());
            ps.setInt(3, a.getIdAdministrador());

            ps.executeUpdate();
        } catch (SQLException ex) {
            
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + atualizar + "\n"
                    + ex.getMessage(), ex.getCause());
        }
    }
    
    @Override
    public void remover(Administrador a) throws ConnectionException, SQLStatementException {
        String remover = "DELETE FROM  administrador"
                + " WHERE ID = ?;";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(remover);
            ps.setInt(1, a.getIdAdministrador());
            ps.executeUpdate();
        } catch (SQLException ex) {
            
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + remover + "\n"
                    + ex.getMessage(), ex.getCause());
        }
    }
    
    @Override
    public ArrayList<Administrador> getTodosAdministradores() throws ConnectionException, SQLStatementException, SQLException, ValidarAdministradorException {
        String todos = "SELECT * FROM administrador;";
        ResultSet rs = null;
        ArrayList<Administrador> lista = new ArrayList<>();

        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(todos);
            rs = ps.executeQuery();

            while (rs.next()) {
                Administrador a = new Administrador(rs.getInt("id"), rs.getString("usuario"), rs.getString("senha"));
                lista.add(a);
            }

        } catch (SQLException ex) {
            
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + todos + "\n"
                    + ex.getMessage(), ex.getCause());
        }
        return lista;

    }
    
    @Override
    public Administrador getAdministrador(int id) throws ConnectionException, SQLStatementException, SQLException, ValidarAdministradorException {
        String pesquisar = "SELECT * FROM administrador WHERE ID = ?;";
        ResultSet rs = null;

        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(pesquisar);
            ps.setInt(1, id);
            rs = ps.executeQuery();

           if (rs.first()) {
                Administrador a = new Administrador(rs.getInt("id"), rs.getString("usuario"), rs.getString("senha"));
                return a;
            }else{
               return null;
           }

        } catch (SQLException ex) {
            
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + pesquisar + "\n"
                    + ex.getMessage(), ex.getCause());
        }
       

    }
}
