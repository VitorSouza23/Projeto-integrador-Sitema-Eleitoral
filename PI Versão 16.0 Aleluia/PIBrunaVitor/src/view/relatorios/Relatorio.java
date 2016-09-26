/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.relatorios;

import conexao.Conexao;
import conexao.exceptions.ConnectionException;
import conexao.exceptions.SQLStatementException;
import contreller.DAO.GerenciadorEleicao;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import model.Eleicao;
import model.exceptions.ValidacaoCandidatoException;
import model.exceptions.ValidarCursoException;
import model.exceptions.ValidarEleicaoException;
import model.exceptions.ValidarEmailException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import view.Principal;
import view.gerenciamento.tabelaConfig.EleicaoColumnModel;
import view.gerenciamento.tabelaConfig.EleicaoTableModel;

/**
 *
 * @author Cliente
 */
public class Relatorio extends javax.swing.JFrame {

    private GerenciadorEleicao ge = new GerenciadorEleicao();
    Conexao cn = new Conexao();
    ArrayList<Eleicao> listaEleicaoFechada = new ArrayList<>();
    Eleicao eleicao;

    /**
     * Creates new form Relatorio
     */
    public Relatorio() {
        initComponents();
        try {
            EleicaoTableConfig(definirArrayDeEleicoesFechadas(ge.getTodosEleicoes()));
        } catch (ConnectionException | SQLStatementException | SQLException | ValidacaoCandidatoException | ValidarEleicaoException | ValidarEmailException | ValidarCursoException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro: " + ex.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
        }
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btVoltar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tEleicoes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204)), "Relatório das Eleições Encerradas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Black", 1, 14), new java.awt.Color(0, 0, 255))); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        btVoltar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btVoltar.setText("voltar");
        btVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltarActionPerformed(evt);
            }
        });
        jPanel2.add(btVoltar);

        tEleicoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Eleição", "Data Inicial", "Data Final", "Situação"
            }
        ));
        tEleicoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tEleicoesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tEleicoes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        Principal p = new Principal();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btVoltarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Principal p = new Principal();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void tEleicoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tEleicoesMouseClicked
        if (evt.getClickCount() == 2) {
            try {
                if (listaEleicaoFechada.get(tEleicoes.getSelectedRow()) != null) {
                    eleicao = listaEleicaoFechada.get(tEleicoes.getSelectedRow());
                    data();
                }

            } catch (ConnectionException | SQLException | SQLStatementException | ValidacaoCandidatoException | ValidarEleicaoException | ValidarEmailException | ValidarCursoException ex) {
                JOptionPane.showMessageDialog(rootPane, "Erro: " + ex.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_tEleicoesMouseClicked
    private void EleicaoTableConfig(ArrayList<Eleicao> list) {
        setEleicaoDataList(list);
        tEleicoes.setAutoCreateColumnsFromModel(false);
        java.awt.FontMetrics fm = tEleicoes.getFontMetrics(tEleicoes.getFont());
        tEleicoes.setColumnModel(new EleicaoColumnModel(fm));
        tEleicoes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    private ArrayList<Eleicao> definirArrayDeEleicoesFechadas(ArrayList<Eleicao> lista) {
        for (Eleicao e : lista) {
            if (!e.isSituacao()) {
                listaEleicaoFechada.add(e);
            }
        }
        return listaEleicaoFechada;
    }

    public static void setEleicaoDataList(java.util.List<Eleicao> list) {
        tEleicoes.setModel(new EleicaoTableModel(list));
    }

    public void data() throws SQLException, ConnectionException, SQLStatementException, ValidacaoCandidatoException, ValidarEleicaoException, ValidarEmailException, ValidarCursoException {

        try {

            InputStream is = getClass().getResourceAsStream("RelatorioEleicao.jasper");
            HashMap map = new HashMap();

            map.put("idEleicao", eleicao.getId());

            JasperPrint rel;
            rel = JasperFillManager.fillReport(is, map, conexao.Conexao.getConexao());

            JasperViewer viewer = new JasperViewer(rel, false);
            viewer.setLocationRelativeTo(null);
            viewer.setExtendedState(MAXIMIZED_BOTH);
            viewer.setVisible(true);
            viewer.setZoomRatio((float) 0.8);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro: " + ex.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btVoltar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable tEleicoes;
    // End of variables declaration//GEN-END:variables
}
