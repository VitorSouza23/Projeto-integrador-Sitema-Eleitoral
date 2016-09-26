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
import model.Candidato;
import model.exceptions.ValidacaoCandidatoException;

/**
 *
 * @author Aluno
 */
public class GerenciadorSelecaoCandidato implements interfaces.IGerenciadorCandidato{

    @Override
    public void salvar(Candidato c) throws SQLStatementException, ConnectionException, ValidacaoCandidatoException {
        String salvar = "insert into SELECAOCANDIDATO (candidato_idCandidato)"
                + " values (?);";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(salvar);
            ps.setInt(1, c.getIdCandidato());
            

            ps.executeUpdate();
        } catch (SQLException ex) {
            
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + salvar + "\n"
                    + ex.getMessage(), ex.getCause());
        }
    }

    

    @Override
    public void remover(Candidato c) throws ConnectionException, SQLStatementException {
        String remover = "delete from  SELECAOCANDIDATO"
                + " where candidato_idCandidato = ?;";
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
        String todos = "Select * from SELECAOCANDIDATO sc, CANDIDATO c "
                + "WHERE sc.candidato_idCandidato = c.idCandidato;";
        ResultSet rs = null;
        ArrayList<Candidato> lista = new ArrayList<>();

        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(todos);
            rs = ps.executeQuery();

            while (rs.next()) {
                Candidato c = new Candidato();
                c.setNome(rs.getString("c.nome"));
                c.setIdCandidato(rs.getInt("c.idCandidato"));
                c.setNumero(rs.getInt("c.numero"));
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
    
    @Override
     public  Candidato getCandidato(int numero) throws ConnectionException, SQLStatementException, ValidacaoCandidatoException{
        String pesquisar = "select * from SELECAOCANDIDATO, CANDIDATO c"
                + " where c.numero = ?;";
        Candidato c = new Candidato();
        ResultSet rs = null;
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(pesquisar);
            ps.setInt(1, numero);

            rs = ps.executeQuery();

            if (rs.next()) {
                c.setNome(rs.getString("nome"));
                c.setIdCandidato(rs.getInt("idCAndidato"));
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
     
     public void removerTodos() throws ConnectionException, SQLStatementException {
        String remover = "DELETE FROM  SELECAOCANDIDATO WHERE IDSELECAOCANDIDATO >= 0;";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(remover);
            ps.executeUpdate();
        } catch (SQLException ex) {
            
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + remover + "\n"
                    + ex.getMessage(), ex.getCause());
        }
    }

    @Deprecated
    @Override
    public void atualizar(Candidato c) throws ConnectionException, SQLStatementException {
        throw new UnsupportedOperationException("Método não utilizável"); 
    }
    
    public ArrayList<Candidato> getTodosCandidatos(int idCandidato) throws ConnectionException, SQLStatementException, SQLException, ValidacaoCandidatoException {
        String todos = "Select * from SELECAOCANDIDATO sc, CANDIDATO c "
                + "WHERE sc.candidato_idCandidato = c.idCandidato and idCandidato = ?;";
        ResultSet rs = null;
        ArrayList<Candidato> lista = new ArrayList<>();

        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(todos);
            ps.setInt(1, idCandidato);
            rs = ps.executeQuery();

            while (rs.next()) {
                Candidato c = new Candidato();
                c.setNome(rs.getString("c.nome"));
                c.setIdCandidato(rs.getInt("c.idCandidato"));
                c.setNumero(rs.getInt("c.numero"));
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
    
    
    
}
