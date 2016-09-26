/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.selecao;

import conexao.Conexao;
import conexao.exceptions.ConnectionException;
import conexao.exceptions.SQLStatementException;
import excecaoEleitor.MatriculaException;
import excecaoEleitor.NomeException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Eleicao;
import model.Eleitor;
import model.Voto;
import model.exceptions.ValidarCursoException;
import model.exceptions.ValidarEmailException;

/**
 *
 * @author Aluno
 */
public class GerenciadorSelecaoEleitor implements interfaces.IGerenciadorEleitor{

    @Override
    public void inserir(Eleitor eleitor) throws SQLStatementException, ConnectionException, NomeException, MatriculaException , ValidarCursoException {
        
        String sqlVoto = "INSERT INTO VOTO_HAS_ELEITOR (voto_idvoto, eleitor_idEleitor) "
                + " VALUES (?,?);";
        
        String sqlSelecao = "INSERT INTO SELECAOELEITOR (vhe_idVoto_Eleitor) "
                + " VALUES (last_insert_id());";

        try {
            PreparedStatement ps = Conexao.getConexao().prepareStatement(sqlVoto);
            ps.setInt(1, eleitor.getVoto().getIdVoto());
            ps.setInt(2, eleitor.getId());
            
            ps.executeUpdate();
           
        } catch (SQLException e) {
            throw new SQLStatementException("Erro na instrução SQL: \n"
                    + "[" + sqlVoto + "]\n" + e.getMessage(), e.getCause());

        }
        
        try {
            PreparedStatement ps1 = Conexao.getConexao().prepareStatement(sqlSelecao);
            
            
            ps1.executeUpdate();
            
        } catch (SQLException e) {
            throw new SQLStatementException("Erro na instrução SQL: \n"
                    + "[" + sqlSelecao + "]\n" + e.getMessage(), e.getCause());

        }
    }
    @Deprecated
    @Override
    public void atualizar(Eleitor eleitor) throws SQLStatementException, ConnectionException, NomeException, MatriculaException, ValidarCursoException{
        throw new UnsupportedOperationException("Método não utilizável");
    }
    
    @Override
    public void remover(Eleitor eleitor) throws SQLStatementException, ConnectionException, NomeException {
        String pesquisar = "SELECT idVoto_Eleitor FROM VOTO_HAS_ELEITOR vhe, SELECAOELEITOR se "
                + "WHERE eleitor_idEleitor = ? and "
                + "voto_idVoto = ? and "
                + "se.vhe_idVoto_Eleitor = vhe.idVoto_Eleitor;";
        String sql = "DELETE FROM SELECAOELEITOR "
                + "WHERE vhe_idVoto_Eleitor = ?;";
        ResultSet rs;
        int idVotoIdEleitor = -1;
        try {
            java.sql.PreparedStatement ps = Conexao.getConexao().prepareStatement(pesquisar);
            ps.setInt(1, eleitor.getId());
            ps.setInt(2, eleitor.getVoto().getIdVoto());
            rs = ps.executeQuery();
                if(rs.first()){
                    idVotoIdEleitor = rs.getInt("idVoto_Eleitor");
                }
        } catch (SQLException ex) {
            
            throw new SQLStatementException("Erro na instrução SQL: \n"
                    + "[" + pesquisar + "]\n" + ex.getMessage(), ex.getCause());
        }
        
        try {
            java.sql.PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, idVotoIdEleitor);
            ps.executeUpdate();
        } catch (SQLException ex) {
            
            throw new SQLStatementException("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + ex.getMessage(), ex.getCause());
        }
    }
    
    private ArrayList<Eleitor> resultSetParaList(ResultSet rs) throws SQLStatementException, MatriculaException, NomeException, ValidarEmailException, ValidarCursoException, ValidarCursoException {
        ArrayList<Eleitor> lista = new ArrayList<>();
        try {
            while (rs.next()) {
                Eleitor e = transformarResultSetEmObjeto(rs);
                lista.add(e);
            }
        } catch (SQLException ex) {
            throw new SQLStatementException("Campos inexistentes da tabela de banco de dados.\n"
                    + "Msg: " + ex.getMessage(), ex.getCause());
        }
        return lista;
    }
    
    private Eleitor transformarResultSetEmObjeto(ResultSet rs) throws SQLStatementException, MatriculaException, NomeException, ValidarEmailException, ValidarCursoException {
        Eleitor e = new Eleitor();
        Voto v = new Voto();
        try {
            v.setIdVoto(rs.getInt("v.idVoto"));
            v.setTipo(rs.getString("v.tipo"));
            v.setValor(rs.getDouble("v.valor"));
            
            e.setId(rs.getInt("e.ideleitor"));
            e.setNome(rs.getString("e.NOME"));
            e.setEmail(rs.getString("e.EMAIL"));
            e.setMatricula(rs.getString("e.MATRICULA"));
            e.setCurso(rs.getString("e.CURSO"));
            e.setVoto(v);
            
            
        } catch (SQLException ex) {
            throw new SQLStatementException("Campos inexistentes da tabela de banco de dados.\n"
                    + "Msg: " + ex.getMessage(), ex.getCause());
        }
        return e;
    }
    @Override
     public ArrayList<Eleitor> obterTodos() throws SQLStatementException, ConnectionException, NomeException, MatriculaException, ValidarEmailException, ValidarCursoException, ValidarCursoException {
        String sql = "SELECT * FROM SELECAOELEITOR se, VOTO_HAS_ELEITOR vh, VOTO v, ELEITOR e WHERE (vh.Voto_idVoto = v.idVoto) "
                + "and (vh.eleitor_idEleitor = e.idEleitor)"
                + "and (se.vhe_idVoto_Eleitor = vh.idVoto_Eleitor);";
        
        ResultSet rs = null;
        try {
            java.sql.PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            throw new SQLStatementException("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
        return resultSetParaList(rs);
    }
     
     public ArrayList<Eleitor> obterTodos(Eleicao e) throws SQLStatementException, ConnectionException, NomeException, MatriculaException, ValidarEmailException, ValidarCursoException, ValidarCursoException {
        String sql = "SELECT * FROM SELECAOELEITOR se, VOTO_HAS_ELEITOR vh, VOTO v, ELEITOR e WHERE (vh.Voto_idVoto = v.idVoto) "
                + "and (vh.eleitor_idEleitor = e.idEleitor)"
                + "and (se.eleitor_idEleitor = e.idEleitor)"
                + "and (e1.idEleicao != ?);";
        
        ResultSet rs = null;
        try {
            java.sql.PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, e.getId());
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            throw new SQLStatementException("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
        return resultSetParaList(rs);
    }
    @Override
     public Eleitor obterEleitor(int id) throws SQLStatementException, ConnectionException, NomeException, MatriculaException, ValidarEmailException, ValidarCursoException, ValidarCursoException  {
        String sql = "SELECT * FROM SELECAOELEITOR se, VOTO_HAS_ELEITOR vh, VOTO v, ELEITOR e WHERE (vh.Voto_idVoto = v.idVoto) "
                + "and (vh.eleitor_idEleitor = e.idEleitor)"
                + "and (se.eleitor_idEleitor = e.idEleitor) and (e.idEleitor = ?);";
        try {
            PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Eleitor e = transformarResultSetEmObjeto(rs);
                return e;
            } else {
                throw new SQLStatementException("Não há Eleitor com este ID.\n");
            }
        } catch (SQLException ex) {
            throw new SQLStatementException("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
    }
     
     public void removerTodos() throws SQLStatementException, ConnectionException, NomeException {
        String sql = "DELETE FROM SELECAOELEITOR WHERE IDSELECAOELEITOR >= 0 ";
        try {
            java.sql.PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            
            throw new SQLStatementException("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + ex.getMessage(), ex.getCause());
        }
    }
     
     public ArrayList<Eleitor> obterTodos(int idEleitor) throws SQLStatementException, ConnectionException, NomeException, MatriculaException, ValidarEmailException, ValidarCursoException, ValidarCursoException {
        String sql = "SELECT * FROM SELECAOELEITOR se, VOTO_HAS_ELEITOR vh, VOTO v, ELEITOR e WHERE (vh.Voto_idVoto = v.idVoto) "
                + "and (vh.eleitor_idEleitor = e.idEleitor)"
                + "and (se.vhe_idVoto_Eleitor = vh.idVoto_Eleitor)"
                + "and idEleitor = ?;";
        
        ResultSet rs = null;
        try {
            java.sql.PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, idEleitor);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            throw new SQLStatementException("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
        return resultSetParaList(rs);
    }
}
