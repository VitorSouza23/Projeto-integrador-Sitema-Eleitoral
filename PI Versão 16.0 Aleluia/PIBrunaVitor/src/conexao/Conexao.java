/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import conexao.exceptions.ConnectionException;
import conexao.exceptions.SQLStatementException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Cliente
 */
public class Conexao {
    private static Connection connection;

    public static Connection getConexao() throws ConnectionException, SQLStatementException {
        if (connection != null) {
            return connection;
        } else {
            return connection = conectar();
        }
    }

    private static Connection conectar() throws ConnectionException, SQLStatementException {
        String url = null;
        String user = null;
        String pass = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://localhost:3306/bd_pi";
            user = "root";
            pass = "motherlode";
            connection = DriverManager.getConnection(url, user, pass);
            
        } catch (ClassNotFoundException ex) {
            throw new ConnectionException("Falha na conexão. \n"
                    + "Verifique seu URL ou biblioteca do drive. \n"
                    + "  " + url + "\n"
                    + " [" + ex.getMessage() + "].", ex.getCause());
        } catch (SQLException ex) {
            throw new SQLStatementException(""
                    + "Verifique os seguintes parâmetros: \n"
                    + "URL: " + url + "\n"
                    + "Usuário: " + user + "\n"
                    + "Senha: " + pass + ".\n"
                    + "Usuário, URL or Senha Estão incorretos. Verifique-os.\n"
                    + "[" + ex.getMessage() + "].", ex.getCause());
        }
        return connection;
    }

    public static void encerrarConexao() throws SQLStatementException {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new SQLStatementException("Erro ao tentar encerrar a conexão.\n"
                    + "[" + ex.getMessage() + "].", ex.getCause());
        }
    } 
}
