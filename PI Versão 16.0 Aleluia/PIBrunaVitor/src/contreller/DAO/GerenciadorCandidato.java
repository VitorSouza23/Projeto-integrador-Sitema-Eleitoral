/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package contreller.DAO;

import conexao.exceptions.ConnectionException;
import conexao.exceptions.SQLStatementException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Candidato;
import model.exceptions.ValidacaoCandidatoException;

/**
 *
 * @author Bruna Branco
 */
public class GerenciadorCandidato implements interfaces.IGerenciadorCandidato{
    @Override
    public void salvar(Candidato c) throws SQLStatementException, ConnectionException, ValidacaoCandidatoException, IOException {
        String salvar = "insert into CANDIDATO (nome, numero, foto)"
                + " values (?, ?, ?);";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(salvar);
            ps.setString(1, c.getNome());
            ps.setInt(2, c.getNumero());
            ps.setBytes(3,  c.getFoto());
            

            ps.executeUpdate();
        } catch (SQLException ex) {
            
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + salvar + "\n"
                    + ex.getMessage(), ex.getCause());
        } 
    }

    @Override
    public void atualizar(Candidato c) throws ConnectionException, SQLStatementException, ValidacaoCandidatoException, IOException {
        String atualizar = "update CANDIDATO set nome = ?, numero = ?, foto = ?"
                + " where idCandidato = ?;";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(atualizar);
            ps.setString(1, c.getNome());
            ps.setInt(2, c.getNumero());
            ps.setBytes(3, c.getFoto());
            ps.setInt(4, c.getIdCandidato());

            ps.executeUpdate();
        } catch (SQLException ex) {
            
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + atualizar + "\n"
                    + ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public void remover(Candidato c) throws ConnectionException, SQLStatementException {
        String remover = "delete from  CANDIDATO "
                + " where idCandidato = ?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(remover);
            ps.setInt(1, c.getIdCandidato());
            ps.executeUpdate();
        } catch (SQLException ex) {
            
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + remover + "\n"
                    + ex.getMessage(), ex.getCause());
        }
    }
    
    @Override
    public ArrayList<Candidato> getTodosCandidatos() throws ConnectionException, SQLStatementException, SQLException, ValidacaoCandidatoException {
        String todos = "Select * from CANDIDATO;";
        ResultSet rs = null;
        ArrayList<Candidato> lista = new ArrayList<>();

        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(todos);
            rs = ps.executeQuery();

            while (rs.next()) {
                Candidato c = new Candidato();
                c.setNome(rs.getString("nome"));
                c.setIdCandidato(rs.getInt("idCandidato"));
                c.setNumero(rs.getInt("numero"));
                c.setFoto( rs.getBytes("foto"));
                
                lista.add(c);
            }

        } catch (SQLException ex) {
            
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + todos + "\n"
                    + ex.getMessage(), ex.getCause());
        }
        return lista;

    }
    
    @Override
     public  Candidato getCandidato(int id) throws ConnectionException, SQLStatementException, ValidacaoCandidatoException{
        String pesquisar = "select * from CANDIDATO"
                + " where idCandidato = ?;";
        Candidato c = new Candidato();
        ResultSet rs = null;
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(pesquisar);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                c.setNome(rs.getString("nome"));
                c.setIdCandidato(rs.getInt("idCandidato"));
                c.setNumero(rs.getInt("numero"));
                c.setFoto(rs.getBytes("foto"));

                return c;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + pesquisar + "\n"
                    + ex.getMessage(), ex.getCause());
        }
    }
     
     public ArrayList<Candidato> getTodosCandidatos(String nome) throws ConnectionException, SQLStatementException, SQLException, ValidacaoCandidatoException {
        String todos = "Select * from CANDIDATO where nome like ?;";
        ResultSet rs = null;
        ArrayList<Candidato> lista = new ArrayList<>();

        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(todos);
            ps.setString(1, nome+"%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Candidato c = new Candidato();
                c.setNome(rs.getString("nome"));
                c.setIdCandidato(rs.getInt("idCandidato"));
                c.setNumero(rs.getInt("numero"));
                c.setFoto(rs.getBytes("foto"));
                lista.add(c);
            }

        } catch (SQLException ex) {
            
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + todos + "\n"
                    + ex.getMessage(), ex.getCause());
        }
        return lista;

    }
     
     public ArrayList<Candidato> getTodosCandidatos(int numero) throws ConnectionException, SQLStatementException, SQLException, ValidacaoCandidatoException {
        String todos = "Select * from CANDIDATO where numero = ?";
        ResultSet rs = null;
        ArrayList<Candidato> lista = new ArrayList<>();

        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(todos);
            ps.setInt(1, numero);
            rs = ps.executeQuery();

            while (rs.next()) {
                Candidato c = new Candidato();
                c.setNome(rs.getString("nome"));
                c.setIdCandidato(rs.getInt("idCandidato"));
                c.setNumero(rs.getInt("numero"));
                c.setFoto(rs.getBytes("foto"));
                
                lista.add(c);
            }

        } catch (SQLException ex) {
            
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + todos + "\n"
                    + ex.getMessage(), ex.getCause());
        }
        return lista;

    }
     
     public ArrayList<Candidato> getTodosCandidatos(int numero, String nome) throws ConnectionException, SQLStatementException, SQLException, ValidacaoCandidatoException {
        String todos = "Select * from CANDIDATO where numero = ? and  nome like ?";
        ResultSet rs = null;
        ArrayList<Candidato> lista = new ArrayList<>();

        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(todos);
            ps.setInt(1, numero);
            ps.setString(2, nome+"%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Candidato c = new Candidato();
                c.setNome(rs.getString("nome"));
                c.setIdCandidato(rs.getInt("idCandidato"));
                c.setNumero(rs.getInt("numero"));
                c.setFoto(rs.getBytes("foto"));
                lista.add(c);
            }

        } catch (SQLException ex) {
            
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + todos + "\n"
                    + ex.getMessage(), ex.getCause());
        }
        return lista;

    }
     
      public  Candidato getCandidatoPorNumero(int numero) throws ConnectionException, SQLStatementException, ValidacaoCandidatoException{
        String pesquisar = "select * from CANDIDATO"
                + " where numero = ?;";
        Candidato c = new Candidato();
        ResultSet rs = null;
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(pesquisar);
            ps.setInt(1, numero);

            rs = ps.executeQuery();

            if (rs.next()) {
                c.setNome(rs.getString("nome"));
                c.setIdCandidato(rs.getInt("idCandidato"));
                c.setNumero(rs.getInt("numero"));
                c.setFoto(rs.getBytes("foto"));
                return c;
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
