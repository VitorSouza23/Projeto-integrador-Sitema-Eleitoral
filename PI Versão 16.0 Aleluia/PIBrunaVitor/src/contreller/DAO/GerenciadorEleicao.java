/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contreller.DAO;

import controller.selecao.GerenciadorSelecaoFiscal;
import controller.selecao.GerenciadorSelecaoEleitor;
import controller.selecao.GerenciadorSelecaoCandidato;
import conexao.exceptions.ConnectionException;
import conexao.exceptions.SQLStatementException;
import excecaoEleitor.MatriculaException;
import excecaoEleitor.NomeException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Candidato;
import model.Eleicao;
import model.Eleitor;
import model.Fiscal;
import model.Voto;
import model.exceptions.ValidacaoCandidatoException;
import model.exceptions.ValidarCursoException;
import model.exceptions.ValidarEleicaoException;
import model.exceptions.ValidarEmailException;

/**
 *
 * @author Bruna Branco
 */
public class GerenciadorEleicao implements interfaces.IGerenciadorEleicao {

    GerenciadorSelecaoCandidato gsc = new GerenciadorSelecaoCandidato();
    GerenciadorSelecaoEleitor gse = new GerenciadorSelecaoEleitor();
    GerenciadorSelecaoFiscal gsf = new GerenciadorSelecaoFiscal();

    @Override
    public void salvar(Eleicao e) throws ConnectionException, SQLStatementException, NomeException, ValidacaoCandidatoException, ValidarEleicaoException, ValidarEmailException, MatriculaException, ValidarCursoException {
        String salvar = "INSERT INTO eleicao (nome, dataInicial, dataFinal, tipoEleicao)"
                + "VALUES (?, ?, ?, ?);";
        String candidato = "INSERT INTO eleicao_has_candidato (eleicao_ideleicao, candidato_idCandidato)"
                + "VALUES (?, ?)";
        String eleitor = "INSERT INTO eleicao_has_eleitor (eleicao_ideleicao, eleitor_idEleitor)"
                + "VALUES (?, ?)";
        String fiscal = "INSERT INTO eleicao_has_fiscal (eleicao_ideleicao, fiscal_idFiscal)"
                + "VALUES (?, ?)";
        String votoEleitor = "UPDATE VOTO_HAS_ELEITOR vhe, ELEITOR el, VOTO v SET eleicao_idEleicao = ? WHERE el.idEleitor = ? "
                + " and vhe.voto_idVoto = v.idVoto"
                + " and vhe.eleitor_idEleitor  = el.idEleitor and vhe.eleicao_idEleicao = 0;";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(salvar);

            ps.setString(1, e.getNome());
            ps.setTimestamp(2, new java.sql.Timestamp(e.getDataHoraInico()));
            ps.setTimestamp(3, new java.sql.Timestamp(e.getDataHoraFinal()));
            ps.setString(4, e.getTipoEleicao());
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + salvar + "\n"
                    + ex.getMessage(), ex.getCause());
        }

        int idEleicao = getIdMaxEleicao();
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(candidato);
            for (Candidato c : gsc.getTodosCandidatos()) {
                ps.setInt(1, idEleicao);
                ps.setInt(2, c.getIdCandidato());
                ps.executeUpdate();
            }

        } catch (SQLException ex) {
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + candidato + "\n"
                    + ex.getMessage(), ex.getCause());
        }

        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(eleitor);
            for (Eleitor e2 : gse.obterTodos()) {
                ps.setInt(1, idEleicao);
                ps.setInt(2, e2.getId());
                ps.executeUpdate();
            }

        } catch (SQLException ex) {
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + eleitor + "\n"
                    + ex.getMessage(), ex.getCause());
        }

        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(fiscal);
            for (Fiscal f : gsf.obterTodos()) {
                ps.setInt(1, idEleicao);
                ps.setInt(2, f.getId());
                ps.executeUpdate();
            }

        } catch (SQLException ex) {
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + fiscal + "\n"
                    + ex.getMessage(), ex.getCause());
        }

        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(votoEleitor);
            for (Eleitor e2 : gse.obterTodos()) {
                ps.setInt(1, idEleicao);

                ps.setInt(2, e2.getId());
                ps.executeUpdate();
            }

        } catch (SQLException ex) {
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + votoEleitor + "\n"
                    + ex.getMessage(), ex.getCause());
        }

    }

    private int getIdMaxEleicao() throws SQLStatementException, ConnectionException {
        String maxID = "SELECT MAX(ideleicao) FROM ELEICAO;";
        ResultSet rs;
        int idMax = 0;

        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(maxID);

            rs = ps.executeQuery();

            if (rs.first()) {
                idMax = rs.getInt("MAX(ideleicao)");
            }

        } catch (SQLException ex) {

            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + maxID + "\n"
                    + ex.getMessage(), ex.getCause());
        }
        return idMax;

    }

    @Deprecated
    @Override
    public void atualizar(Eleicao e) throws ConnectionException, SQLStatementException, NomeException, ValidacaoCandidatoException, ValidarEleicaoException, ValidarEmailException, MatriculaException, ValidarCursoException {
        //Não se pode permitir que uma eleição seja atualizada, se permitido, uma brecha na segurança da elição pode ser aberta.
    }

    @Override
    public void remover(Eleicao e) throws ConnectionException, SQLStatementException {

        String setarCandidato = "UPDATE ELEICAO e, ELEICAO_HAS_CANDIDATO ehc, CANDIDATO c SET c.qtdVotosRecebidos = 0"
                + " WHERE e.idEleicao = ? and ehc.eleicao_idEleicao = e.idEleicao and c.idCandidato = ehc.candidato_idCandidato;";

        String removerCandidatos = "DELETE FROM ELEICAO_HAS_CANDIDATO WHERE eleicao_idEleicao = ?;";

        String removerEleitores = "DELETE FROM ELEICAO_HAS_ELEITOR WHERE eleicao_idEleicao = ?;";

        String removerFiscais = "DELETE FROM ELEICAO_HAS_FISCAL WHERE eleicao_idEleicao = ?;";

        String removerVotoEleitor = "DELETE FROM VOTO_HAS_ELEITOR WHERE eleicao_idEleicao = ?";

        String remover = "DELETE FROM ELEICAO WHERE idEleicao = ?;";

        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(setarCandidato);
            ps.setInt(1, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {

            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + setarCandidato + "\n"
                    + ex.getMessage(), ex.getCause());
        }

        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(removerCandidatos);
            ps.setInt(1, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {

            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + removerCandidatos + "\n"
                    + ex.getMessage(), ex.getCause());
        }

        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(removerEleitores);
            ps.setInt(1, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {

            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + removerEleitores + "\n"
                    + ex.getMessage(), ex.getCause());
        }

        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(removerFiscais);
            ps.setInt(1, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {

            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + removerFiscais + "\n"
                    + ex.getMessage(), ex.getCause());
        }

        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(removerVotoEleitor);
            ps.setInt(1, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {

            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + setarCandidato + "\n"
                    + ex.getMessage(), ex.getCause());
        }

        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(remover);
            ps.setInt(1, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {

            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + remover + "\n"
                    + ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public ArrayList<Eleicao> getTodosEleicoes() throws ConnectionException, SQLStatementException, SQLException, ValidacaoCandidatoException, ValidarEleicaoException, ValidarEmailException, ValidarCursoException, ValidarCursoException {
        String todos = "SELECT * FROM ELEICAO e,ELEICAO_HAS_ELEITOR ehe, ELEICAO_HAS_CANDIDATO ehc, VOTO_HAS_ELEITOR vhe, ELEICAO_HAS_FISCAL ehf,CANDIDATO c, "
                + "FISCAL f, ELEITOR el,"
                + "VOTO v  WHERE (e.idEleicao = ehe.eleicao_ideleicao) and (e.idEleicao = ehc.eleicao_ideleicao)"
                + "and (e.idEleicao = ehf.eleicao_ideleicao) and (ehc.candidato_idCandidato = c.idCandidato)and (ehf.fiscal_idFiscal = f.idFiscal)"
                + "and (ehe.eleitor_idEleitor = el.idEleitor) and (v.idVoto = vhe.voto_idvoto);";

        String sqlids = "SELECT idEleicao FROM eleicao;";

        ResultSet rs = null;
        ResultSet ids = null;
        ArrayList<Eleicao> lista = new ArrayList<>();
        Eleicao e = null;

        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(todos);
            PreparedStatement psids = conexao.Conexao.getConexao().prepareStatement(sqlids);
            rs = ps.executeQuery();
            ids = psids.executeQuery();

            while (ids.next()) {
                e = this.getEleicao(ids.getInt("idEleicao"));
                lista.add(e);
            }

        } catch (SQLException ex) {

            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + todos + "\n"
                    + sqlids + "\n"
                    + ex.getMessage(), ex.getCause());
        }
        return lista;

    }

    @Override
    public Eleicao getEleicao(int id) throws ConnectionException, SQLStatementException, ValidacaoCandidatoException, ValidarEleicaoException, ValidarEmailException, ValidarCursoException, ValidarCursoException {
        String pesquisar = "SELECT * FROM ELEICAO e,ELEICAO_HAS_ELEITOR ehe, ELEICAO_HAS_CANDIDATO ehc, VOTO_HAS_ELEITOR vhe, ELEICAO_HAS_FISCAL ehf,CANDIDATO c, "
                + "FISCAL f, ELEITOR el,"
                + "VOTO v  WHERE (e.idEleicao = ehe.eleicao_ideleicao) and (e.idEleicao = ehc.eleicao_ideleicao)"
                + "and (e.idEleicao = ehf.eleicao_ideleicao) and (ehc.candidato_idCandidato = c.idCandidato)and (ehf.fiscal_idFiscal = f.idFiscal)"
                + "and (ehe.eleitor_idEleitor = el.idEleitor) and (v.idVoto = vhe.voto_idvoto) and (e.idEleicao = ?);";

        Eleicao e;

        ResultSet rs = null;
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(pesquisar);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.first()) {
                e = definirDadosBasicosEleicao(rs);
                e.setListaCandidato(montarEleicaoCandidato(e));
                e.setListaEleitor(montarEleicaoEleitor(e));
                e.setListaFiscal(montarEleicaoFiscal(e));

                return e;
            } else {
                return null;
            }
        } catch (SQLException ex) {

            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + pesquisar + "\n"
                    + ex.getMessage(), ex.getCause());
        } catch (NomeException ex) {
            Logger.getLogger(GerenciadorEleicao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Candidato definirCandidato(ResultSet rs) throws SQLException, ValidacaoCandidatoException, ConnectionException, SQLStatementException {
        Candidato c = new Candidato(rs.getString("c.NOME"), rs.getBytes("c.FOTO"), rs.getInt("c.NUMERO"), rs.getInt("c.IDCANDIDATO"));
        return c;

    }

    private Fiscal definirFiscal(ResultSet rs) throws SQLException, NomeException, ValidarEmailException {
        Fiscal f = new Fiscal(rs.getInt("f.IDFISCAL"), rs.getString("f.NOME"), rs.getString("f.ENDERECO"),
                rs.getString("f.EMAIL"), rs.getString("f.TELEFONE"));
        return f;
    }

    private Eleitor definirEleitor(ResultSet rs) throws SQLException, NomeException, MatriculaException, ValidarEmailException, ValidarCursoException {
        Eleitor e1 = new Eleitor(rs.getInt("el.IDELEITOR"), rs.getString("el.NOME"), rs.getString("el.EMAIL"), rs.getString("el.MATRICULA"),
                rs.getString("el.CURSO"), new Voto(rs.getString("v.TIPO"), rs.getDouble("v.VALOR"), rs.getInt("v.IDVOTO")));
        return e1;

    }

    private Eleicao definirDadosBasicosEleicao(ResultSet rs) throws SQLException, ValidarEleicaoException {

        boolean situacao = false;

        if (rs.getTimestamp("e.DATAINICIAL").getTime() <= System.currentTimeMillis() && rs.getTimestamp("e.DATAFINAL").getTime() >= System.currentTimeMillis()) {
            situacao = true;
        }
        Eleicao e = new Eleicao(rs.getInt("e.IDELEICAO"), rs.getString("e.NOME"), rs.getString("e.TIPOELEICAO"),
                rs.getTimestamp("e.DATAINICIAL").getTime(), rs.getTimestamp("e.DATAFINAL").getTime(), situacao);

        return e;
    }

    public void votar(Candidato candidato, Eleitor eleitor, Eleicao eleicao, Voto voto) throws ConnectionException, SQLStatementException {
        String selecionarCandidato = "Select * from ELEICAO e, ELEICAO_HAS_ELEITOR ehe, ELEICAO_HAS_CANDIDATO ehc, CANDIDATO c,\n"
                + "ELEITOR el, VOTO v, VOTO_HAS_ELEITOR vhe  \n"
                + "WHERE  \n"
                + "e.idEleicao = ? and c.idCandidato = ? and el.idEleitor = ? and v.idVoto = ? and\n"
                + "e.idEleicao = ehe.eleicao_idEleicao and\n"
                + "e.idEleicao = ehc.eleicao_idEleicao and \n"
                + "c.idCandidato = ehc.candidato_idCandidato and\n"
                + "el.idEleitor = ehe.eleitor_idEleitor and\n"
                + "el.idEleitor = vhe.eleitor_idEleitor and\n"
                + "v.idVoto = vhe.voto_idVoto;";

        ResultSet rs;
        int idEleitor = 0;
        double valorVoto = 0;
        double quantidadeAtualDeVotos = 0;

        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(selecionarCandidato);

            ps.setInt(1, eleicao.getId());
            ps.setInt(2, candidato.getIdCandidato());
            ps.setInt(3, eleitor.getId());
            ps.setInt(4, voto.getIdVoto());
            rs = ps.executeQuery();
            if (rs.next()) {
                idEleitor = rs.getInt("c.idCandidato");
                valorVoto = rs.getDouble("v.valor");
                quantidadeAtualDeVotos = rs.getDouble("c.qtdVotosRecebidos");
            }

        } catch (SQLException ex) {
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + selecionarCandidato + "\n"
                    + ex.getMessage(), ex.getCause());
        }

        confirmarVoto(idEleitor, valorVoto, quantidadeAtualDeVotos);

        setVotou(eleicao, eleitor);
        setQuantidadeAtualDeELeitoresQueVotaram(eleicao);

    }

    private void confirmarVoto(int idCandidato, double valor, double quantidadeAtualDeVotos) throws ConnectionException, SQLStatementException {
        String votoParaCandidato = "UPDATE candidato SET qtdVotosRecebidos = ? where idCandidato = ?";

        try {

            PreparedStatement psvoto = conexao.Conexao.getConexao().prepareStatement(votoParaCandidato);
            psvoto.setDouble(1, valor + quantidadeAtualDeVotos);
            psvoto.setInt(2, idCandidato);
            psvoto.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + votoParaCandidato + "\n"
                    + ex.getMessage(), ex.getCause());
        }

    }

    public void votarEmBranco(Eleicao eleicao, Eleitor eleitor) throws ConnectionException, SQLStatementException {
        String qtdVotos = "SELECT qtdVotosBrancos FROM ELEICAO WHERE idEleicao = ?;";

        String votar = "UPDATE ELEICAO e   \n "
                + "SET e.qtdVotosBrancos  = ? WHERE  \n "
                + "e.idEleicao = ?;";

        ResultSet rs;
        int qtdAtualVotosBrancos = 0;

        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(qtdVotos);
            ps.setInt(1, eleicao.getId());

            rs = ps.executeQuery();
            if (rs.first()) {
                qtdAtualVotosBrancos = rs.getInt("qtdVotosBrancos");

            }

            ps = conexao.Conexao.getConexao().prepareStatement(votar);

            ps.setInt(1, qtdAtualVotosBrancos + 1);
            ps.setInt(2, eleicao.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + votar + "\n"
                    + ex.getMessage(), ex.getCause());
        }
        setVotou(eleicao, eleitor);
        setQuantidadeAtualDeELeitoresQueVotaram(eleicao);

    }

    public void AnularVoto(Eleicao eleicao, Eleitor eleitor) throws ConnectionException, SQLStatementException {
        String qtdVotos = "SELECT qtdVotosNulos FROM ELEICAO WHERE idEleicao = ?;";

        String votar = "UPDATE ELEICAO e   \n "
                + "SET e.qtdVotosNulos  = ? WHERE  \n "
                + "e.idEleicao = ?;";
        ResultSet rs;
        int qtdAtualVotosNulos = 0;
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(qtdVotos);
            ps.setInt(1, eleicao.getId());
            rs = ps.executeQuery();
            if (rs.first()) {
                qtdAtualVotosNulos = rs.getInt("qtdVotosNulos");
            }

            ps = conexao.Conexao.getConexao().prepareStatement(votar);
            ps.setInt(1, qtdAtualVotosNulos + 1);
            ps.setInt(2, eleicao.getId());

            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + votar + "\n"
                    + ex.getMessage(), ex.getCause());
        }
        setVotou(eleicao, eleitor);
        setQuantidadeAtualDeELeitoresQueVotaram(eleicao);

    }

    public boolean jaVotou(int idEleitor, int idEleicao) throws ConnectionException, SQLStatementException, NomeException, MatriculaException, ValidarEmailException {
        String eleitor = "SELECT * FROM ELEITOR el, ELEICAO e, ELEICAO_HAS_ELEITOR ehe"
                + " WHERE el.idEleitor = ? and"
                + " e.idEleicao = ? and "
                + " ehe.eleicao_idEleicao = e.idEleicao and "
                + " ehe.eleitor_idEleitor = el.idEleitor;";

        ResultSet rs;
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(eleitor);
            ps.setInt(1, idEleitor);
            ps.setInt(2, idEleicao);

            rs = ps.executeQuery();

            if (rs.first()) {
                if (rs.getBoolean("ehe.eleitor_votou")) {
                    return true;
                }
            }
        } catch (SQLException ex) {

            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + eleitor + "\n"
                    + ex.getMessage(), ex.getCause());
        }
        return false;
    }

    private ArrayList<Candidato> montarEleicaoCandidato(Eleicao e) throws ConnectionException, SQLStatementException, ValidacaoCandidatoException {
        String montarCandidatos = "SELECT * FROM ELEICAO e, ELEICAO_HAS_CANDIDATO ehc, CANDIDATO c "
                + " WHERE (e.idEleicao = ehc.eleicao_ideleicao)"
                + "and (ehc.candidato_idCandidato = c.idCandidato)"
                + "and (e.idEleicao = ?);";

        Candidato c;
        ArrayList<Candidato> listaCandidato = new ArrayList<>();
        ResultSet rs = null;
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(montarCandidatos);
            ps.setInt(1, e.getId());

            rs = ps.executeQuery();

            while (rs.next()) {
                c = definirCandidato(rs);
                listaCandidato.add(c);
            }

        } catch (SQLException ex) {

            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + montarCandidatos + "\n"
                    + ex.getMessage(), ex.getCause());
        }
        return listaCandidato;

    }

    private ArrayList<Eleitor> montarEleicaoEleitor(Eleicao e) throws ConnectionException, SQLStatementException, ValidarEmailException, ValidarCursoException {
        String montarEleitor = "SELECT * FROM ELEICAO e,ELEICAO_HAS_ELEITOR ehe, VOTO_HAS_ELEITOR vhe, ELEITOR el,"
                + "VOTO v  WHERE (e.idEleicao = ehe.eleicao_ideleicao) "
                + "and (ehe.eleitor_idEleitor = el.idEleitor) and (v.idVoto = vhe.voto_idvoto) and (e.idEleicao = ?) "
                + " and (ehe.eleitor_idEleitor = vhe.eleitor_idEleitor)"
                + " and vhe.eleicao_idEleicao = e.ideleicao;";

        Eleitor el;
        ArrayList<Eleitor> listaEleitor = new ArrayList<>();
        ResultSet rs = null;
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(montarEleitor);
            ps.setInt(1, e.getId());

            rs = ps.executeQuery();

            while (rs.next()) {
                el = definirEleitor(rs);
                listaEleitor.add(el);
            }
        } catch (SQLException ex) {

            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + montarEleitor + "\n"
                    + ex.getMessage(), ex.getCause());
        } catch (NomeException | MatriculaException ex) {
            Logger.getLogger(GerenciadorEleicao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaEleitor;
    }

    private ArrayList<Fiscal> montarEleicaoFiscal(Eleicao e) throws ConnectionException, SQLStatementException, NomeException, ValidarEmailException {
        String montarFiscal = "SELECT * FROM ELEICAO e,ELEICAO_HAS_FISCAL ehf, "
                + "FISCAL f WHERE (e.idEleicao = ehf.eleicao_ideleicao) and (ehf.fiscal_idFiscal = f.idFiscal)"
                + " and (e.idEleicao = ?);";

        Fiscal f;
        ArrayList<Fiscal> listaFiscal = new ArrayList<>();
        ResultSet rs = null;
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(montarFiscal);
            ps.setInt(1, e.getId());

            rs = ps.executeQuery();
            while (rs.next()) {
                f = definirFiscal(rs);
                listaFiscal.add(f);
            }

        } catch (SQLException ex) {

            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + montarFiscal + "\n"
                    + ex.getMessage(), ex.getCause());
        }
        return listaFiscal;
    }

    public boolean isContemCandidato(int idCandidato) throws ConnectionException, SQLStatementException, NomeException, MatriculaException, ValidarEmailException {
        String eleitor = "SELECT * FROM CANDIDATO c,  ELEICAO_HAS_CANDIDATO ehc "
                + " WHERE c.idCandidato = ? and ehc.candidato_idCandidato = c.idCandidato;";

        ResultSet rs;
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(eleitor);
            ps.setInt(1, idCandidato);

            rs = ps.executeQuery();

            if (rs.first()) {

                return true;
            }
        } catch (SQLException ex) {

            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + eleitor + "\n"
                    + ex.getMessage(), ex.getCause());
        }
        return false;
    }

    public boolean isContemCandidato(int idCandidato, int idEleicao) throws ConnectionException, SQLStatementException, NomeException, MatriculaException, ValidarEmailException {
        String eleitor = "SELECT * FROM CANDIDATO c, ELEICAO e,  ELEICAO_HAS_CANDIDATO ehc "
                + " WHERE c.idCandidato = ? and"
                + " e.idEleicao = ? and "
                + " ehc.eleicao_idEleicao = e.idEleicao and "
                + " ehc.candidato_idCandidato = c.idCandidato;";

        ResultSet rs;
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(eleitor);
            ps.setInt(1, idCandidato);
            ps.setInt(2, idEleicao);

            rs = ps.executeQuery();

            if (rs.first()) {

                return true;
            }
        } catch (SQLException ex) {

            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + eleitor + "\n"
                    + ex.getMessage(), ex.getCause());
        }
        return false;
    }

    public boolean isContemEleitor(int idEleitor, int idEleicao) throws ConnectionException, SQLStatementException, NomeException, MatriculaException, ValidarEmailException {
        String eleitor = "SELECT * FROM ELEITOR el, ELEICAO e, VOTO v, ELEICAO_HAS_ELEITOR ehe, VOTO_HAS_ELEITOR vhe"
                + " WHERE el.idEleitor = ? and"
                + " e.idEleicao = ? and "
                + " ehe.eleicao_idEleicao = e.idEleicao and "
                + " ehe.eleitor_idEleitor = el.idEleitor and"
                + " vhe.voto_idVoto = v.idVoto and"
                + " vhe.eleitor_idEleitor = el.idELeitor";

        ResultSet rs;
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(eleitor);
            ps.setInt(1, idEleitor);
            ps.setInt(2, idEleicao);

            rs = ps.executeQuery();

            if (rs.first()) {

                return true;
            }
        } catch (SQLException ex) {

            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + eleitor + "\n"
                    + ex.getMessage(), ex.getCause());
        }
        return false;
    }

    public Eleitor getEleitor(int idEleitor, int idEleicao) throws ConnectionException, SQLStatementException, NomeException, MatriculaException, ValidarEmailException, ValidarCursoException {
        String eleitor = "SELECT * FROM ELEITOR el, ELEICAO e, VOTO v, ELEICAO_HAS_ELEITOR ehe, VOTO_HAS_ELEITOR vhe"
                + " WHERE el.idEleitor = ? and"
                + " e.idEleicao = ? and "
                + " ehe.eleicao_idEleicao = e.idEleicao and "
                + " ehe.eleitor_idEleitor = el.idEleitor and"
                + " vhe.voto_idVoto = v.idVoto and"
                + " vhe.eleitor_idEleitor = el.idELeitor";
        Eleitor el;
        ResultSet rs = null;
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(eleitor);
            ps.setInt(1, idEleitor);
            ps.setInt(2, idEleicao);

            rs = ps.executeQuery();

            if (rs.first()) {
                el = definirEleitor(rs);
                return el;
            }
        } catch (SQLException ex) {

            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + eleitor + "\n"
                    + ex.getMessage(), ex.getCause());
        }
        return null;
    }

    private void setQuantidadeAtualDeELeitoresQueVotaram(Eleicao eleicao) throws ConnectionException, SQLStatementException {
        String quantidadedeEleitores = "SELECT qtdEleitoresQueVotaram FROM eleicao where ideleicao = ?;";

        String atualizarQuantidadeDeEleitores = "UPDATE eleicao SET qtdEleitoresQueVotaram = ? WHERE ideleicao = ?;";

        ResultSet rs;
        int qtdDeEleitoresQueVotaram = 0;

        try {

            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(quantidadedeEleitores);
            ps.setInt(1, eleicao.getId());
            rs = ps.executeQuery();
            if (rs.first()) {
                qtdDeEleitoresQueVotaram = rs.getInt("qtdEleitoresQueVotaram");
            }
        } catch (SQLException ex) {
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + quantidadedeEleitores + "\n"
                    + ex.getMessage(), ex.getCause());
        }

        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(atualizarQuantidadeDeEleitores);

            ps.setInt(1, qtdDeEleitoresQueVotaram + 1);
            ps.setInt(2, eleicao.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + atualizarQuantidadeDeEleitores + "\n"
                    + ex.getMessage(), ex.getCause());
        }
    }

    private void setVotou(Eleicao eleicao, Eleitor eleitor) throws ConnectionException, SQLStatementException {
        String votou = "Update ELEICAO_HAS_ELEITOR ehe, Eleicao e, Eleitor el set ehe.eleitor_votou = true where e.idEleicao = ? and\n"
                + "el.idEleitor = ? and\n"
                + "ehe.eleitor_idEleitor = el.idEleitor and\n"
                + "ehe.eleicao_ideleicao = e.ideleicao;";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(votou);

            ps.setInt(1, eleicao.getId());
            ps.setInt(2, eleitor.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + votou + "\n"
                    + ex.getMessage(), ex.getCause());
        }
    }

    public Candidato getCandidato(int idCandidato, int idEleicao) throws ConnectionException, SQLStatementException, NomeException, ValidacaoCandidatoException {
        String eleitor = "SELECT * FROM CANDIDATO c, ELEICAO e, ELEICAO_HAS_CANDIDATO ehc \n"
                + "WHERE c.idCandidato = ? and \n"
                + "e.idEleicao = ? and \n"
                + "ehc.eleicao_idEleicao = e.idEleicao and \n"
                + "ehc.candidato_idCandidato = c.idCandidato;";
        Candidato c;
        ResultSet rs = null;
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(eleitor);
            ps.setInt(1, idCandidato);
            ps.setInt(2, idEleicao);

            rs = ps.executeQuery();

            if (rs.first()) {
                c = definirCandidato(rs);
                return c;
            }
        } catch (SQLException ex) {

            throw new SQLStatementException("Erro de Concordância SQL; \n"
                    + eleitor + "\n"
                    + ex.getMessage(), ex.getCause());
        }
        return null;
    }

}
