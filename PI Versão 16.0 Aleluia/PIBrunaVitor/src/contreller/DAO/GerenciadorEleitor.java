/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contreller.DAO;

import conexao.Conexao;
import conexao.exceptions.ConnectionException;
import conexao.exceptions.SQLStatementException;
import excecaoEleitor.MatriculaException;
import excecaoEleitor.NomeException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Eleitor;
import model.exceptions.ValidarCursoException;
import model.exceptions.ValidarEmailException;

/**
 *
 * @author Bruna Branco
 */
public class GerenciadorEleitor implements interfaces.IGerenciadorEleitor {

    @Override
    public void inserir(Eleitor eleitor) throws SQLStatementException, ConnectionException, NomeException, MatriculaException, ValidarEmailException, ValidarCursoException {

        String sql = "INSERT INTO ELEITOR(NOME, EMAIL, MATRICULA,CURSO)"
                + "VALUES(?,?,?,?);";

        try {
            PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, eleitor.getNome());
            ps.setString(2, eleitor.getEmail());
            ps.setString(3, eleitor.getMatricula());
            ps.setString(4, eleitor.getCurso());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLStatementException("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + e.getMessage(), e.getCause());

        }
    }

    @Override
    public void atualizar(Eleitor eleitor) throws SQLStatementException, ConnectionException, NomeException, MatriculaException, ValidarEmailException, ValidarCursoException {
        String sql = "UPDATE ELEITOR SET NOME = ?, EMAIL = ?, MATRICULA = ?, CURSO = ? "
                + "WHERE idEleitor = ?";

        try {
            PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, eleitor.getNome());
            ps.setString(2, eleitor.getEmail());
            ps.setString(3, eleitor.getMatricula());
            ps.setString(4, eleitor.getCurso());
            ps.setInt(5, eleitor.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLStatementException("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + e.getMessage(), e.getCause());

        }
    }

    @Override
    public void remover(Eleitor eleitor) throws SQLStatementException, ConnectionException, NomeException {
        String sql = "DELETE FROM ELEITOR WHERE idEleitor= ?";
        try {
            java.sql.PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, eleitor.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {

            throw new SQLStatementException("Erro na instrução SQL: \n"
                    + "[" + sql + "]\n" + ex.getMessage(), ex.getCause());
        }
    }

    private ArrayList<Eleitor> resultSetParaList(ResultSet rs) throws SQLStatementException, MatriculaException, NomeException, ValidarEmailException, ValidarCursoException {
        ArrayList<Eleitor> lista = new ArrayList<>();
        try {
            while (rs.next()) {
                Eleitor v = transformarResultSetEmObjeto(rs);
                lista.add(v);
            }
        } catch (SQLException ex) {
            throw new SQLStatementException("Campos inexistentes da tabela de banco de dados.\n"
                    + "Msg: " + ex.getMessage(), ex.getCause());
        }
        return lista;
    }

    private Eleitor transformarResultSetEmObjeto(ResultSet rs) throws SQLStatementException, MatriculaException, NomeException, ValidarEmailException, ValidarCursoException {
        Eleitor e = new Eleitor();
        try {
            e.setId(rs.getInt("IDELEITOR"));
            e.setNome(rs.getString("NOME"));
            e.setEmail(rs.getString("EMAIL"));
            e.setMatricula(rs.getString("MATRICULA"));
            e.setCurso(rs.getString("CURSO"));

        } catch (SQLException ex) {
            throw new SQLStatementException("Campos inexistentes da tabela de banco de dados.\n"
                    + "Msg: " + ex.getMessage(), ex.getCause());
        }
        return e;
    }

    @Override
    public ArrayList<Eleitor> obterTodos() throws SQLStatementException, ConnectionException, NomeException, MatriculaException, ValidarEmailException, ValidarCursoException {
        String sql = "SELECT * FROM ELEITOR;";
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

    @Override
    public Eleitor obterEleitor(int id) throws SQLStatementException, ConnectionException, NomeException, MatriculaException, ValidarEmailException, ValidarCursoException {
        String sql = "SELECT * FROM ELEITOR WHERE idEleitor = ?;";
        try {
            PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Eleitor e = transformarResultSetEmObjeto(rs);
                return e;
            } 
//            else {
//                throw new SQLStatementException("Não há Eleitor com este ID.\n");
//            }
        } catch (SQLException ex) {
            throw new SQLStatementException("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
        return null;
    }
    
    public ArrayList<Eleitor> obterTodos(String curso) throws SQLStatementException, ConnectionException, NomeException, MatriculaException, ValidarEmailException, ValidarCursoException {
        String sql = "SELECT * FROM ELEITOR WHERE curso like ?;";
        ResultSet rs = null;
        try {
            java.sql.PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, curso +"%");
            
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            throw new SQLStatementException("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
        return resultSetParaList(rs);
    }
    
    public ArrayList<Eleitor> obterTodosPorNome(String nome) throws SQLStatementException, ConnectionException, NomeException, MatriculaException, ValidarEmailException, ValidarCursoException {
        String sql = "SELECT * FROM ELEITOR WHERE nome like ?;";
        ResultSet rs = null;
        try {
            java.sql.PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, nome +"%");
            
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            throw new SQLStatementException("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
        return resultSetParaList(rs);
    }
    public ArrayList<Eleitor> obterTodosPorNomeECurso(String nome, String curso) throws SQLStatementException, ConnectionException, NomeException, MatriculaException, ValidarEmailException, ValidarCursoException {
        String sql = "SELECT * FROM ELEITOR WHERE nome like ? and curso like ?;";
        ResultSet rs = null;
        try {
            java.sql.PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, nome +"%");
            ps.setString(2, curso +"%");
            
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            throw new SQLStatementException("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
        return resultSetParaList(rs);
    }
    
    
    public Eleitor obterEleitor(String matricula) throws SQLStatementException, ConnectionException, NomeException, MatriculaException, ValidarEmailException, ValidarCursoException {
        String sql = "SELECT * FROM ELEITOR   WHERE matricula = ?;";
        try {
            PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, matricula);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Eleitor e = transformarResultSetEmObjeto(rs);
                return e;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            throw new SQLStatementException("Erro na instrução sql: " + sql + ".\n"
                    + "Msg.: " + ex.getMessage(), ex.getCause());
        }
    }
    
    
}
