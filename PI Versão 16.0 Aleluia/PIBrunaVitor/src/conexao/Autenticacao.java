/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import conexao.exceptions.ConnectionException;
import conexao.exceptions.SQLStatementException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Administrador;
import model.exceptions.ValidarAdministradorException;

/**
 *
 * @author Aluno
 */
public class Autenticacao {

    /**
     * @return the administrador
     */
    public static Administrador getAdministrador() {
        return administrador;
    }

    /**
     * @param aA the administrador to set
     */
    public static void setAdministrador(Administrador aA) {
        administrador = aA;
    }
    private boolean autentificado;
    private static Administrador administrador;
    public boolean consultar(String Usuario, String senha) throws SQLException, ConnectionException, SQLStatementException {
        try{
        
        String sql;
        sql = "select id, usuario, senha from ADMINISTRADOR where usuario = ? and senha = ?";
        PreparedStatement ps;

        ps = Conexao.getConexao().prepareStatement(sql);
        ps.setString(1, Usuario);
        ps.setString(2, senha);

        ResultSet rs;
        rs = ps.executeQuery();

        if (rs.next()) {
                setAdministrador(new Administrador(rs.getInt("id"), rs.getString("USUARIO"), rs.getString("SENHA")));
            autentificado = true;
        }
        
        

        ps.close();

             
        }catch (SQLException ex) {  
            System.out.println("Erro ao recuperar cliente/ senha.");  
            Logger.getLogger(Autenticacao.class.getName()).log(Level.SEVERE, null, ex);
        throw new RuntimeException();
    }   catch (ValidarAdministradorException ex) {
            System.out.println("Usuário não encontrado...");
        }
          return autentificado;
    }
    
 
}
