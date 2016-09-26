/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.selecao;

import conexao.exceptions.ConnectionException;
import conexao.exceptions.SQLStatementException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Voto;

/**
 *
 * @author Aluno
 */
public class GerenciadorSelecaoVoto {
    
    public void salvar(Voto v) throws SQLStatementException, ConnectionException{
        String salvar = "INSERT INTO VOTO (tipo, valor)"
                + " values (?, ?);";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(salvar);
            ps.setString(1, v.getTipo());
            ps.setDouble(2, v.getValor());
            

            ps.executeUpdate();
        } catch (SQLException ex) {
            
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + salvar + "\n"
                    + ex.getMessage(), ex.getCause());
        }
    }

    
    public void atualizar(Voto v) throws ConnectionException, SQLStatementException {
        String atualizar = "UPDATE VOTO SET TIPO = ?, VALOR = ?"
                + " WHERE IDVOTO = ?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(atualizar);
            ps.setString(1, v.getTipo());
            ps.setDouble(2, v.getValor());
            ps.setInt(3, v.getIdVoto());
            

            ps.executeUpdate();
        } catch (SQLException ex) {
            
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + atualizar + "\n"
                    + ex.getMessage(), ex.getCause());
        }
    }

    
    public void remover(Voto v) throws ConnectionException, SQLStatementException {
        String remover = "DELETE FROM VOTO"
                + " WHERE TIPO = ?;";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(remover);
            ps.setString(1, v.getTipo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + remover + "\n"
                    + ex.getMessage(), ex.getCause());
        }
    }
    
    
    public ArrayList<Voto> getTodosVotos() throws ConnectionException, SQLStatementException, SQLException {
        String todos = "SELECT * FROM VOTO;";
        ResultSet rs = null;
        ArrayList<Voto> lista = new ArrayList<>();

        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(todos);
            rs = ps.executeQuery();

            while (rs.next()) {
                Voto v = new Voto();
                v.setIdVoto(rs.getInt("idVoto"));
                v.setTipo(rs.getString("tipo"));
                v.setValor(rs.getDouble("valor"));
                
                lista.add(v);
            }

        } catch (SQLException ex) {
            
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + todos + "\n"
                    + ex.getMessage(), ex.getCause());
        }
        return lista;

    }
    
    
     public  Voto getVoto(int id) throws ConnectionException, SQLStatementException{
        String pesquisar = "select * from SELECAOCANDIDATO"
                + " where numero = ?;";
        Voto v = new Voto();
        ResultSet rs = null;
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(pesquisar);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                
                v.setIdVoto(rs.getInt("idVoto"));
                v.setTipo(rs.getString("tipo"));
                v.setValor(rs.getDouble("valor"));

                return v;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + pesquisar + "\n"
                    + ex.getMessage(), ex.getCause());
        }
    }
     
     public  Voto getVoto(String tipo) throws ConnectionException, SQLStatementException{
        String pesquisar = "select * from VOTO"
                + " where tipo = ?;";
        Voto v = new Voto();
        ResultSet rs = null;
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(pesquisar);
            ps.setString(1, tipo);

            rs = ps.executeQuery();

            if (rs.next()) {
                
                v.setIdVoto(rs.getInt("idVoto"));
                v.setTipo(rs.getString("tipo"));
                v.setValor(rs.getDouble("valor"));

                return v;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + pesquisar + "\n"
                    + ex.getMessage(), ex.getCause());
        }
    }
}
