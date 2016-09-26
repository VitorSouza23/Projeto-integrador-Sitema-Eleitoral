/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.votacao;

import conexao.Autenticacao;
import conexao.exceptions.ConnectionException;
import conexao.exceptions.SQLStatementException;
import contreller.DAO.GerenciadorEleicao;
import contreller.DAO.GerenciadorEleitor;
import excecaoEleitor.MatriculaException;
import excecaoEleitor.NomeException;
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import model.Eleitor;
import model.exceptions.ValidarCursoException;
import model.exceptions.ValidarEmailException;

/**
 *
 * @author Aluno
 */
public class FrmIdentificacao extends javax.swing.JFrame implements ComponentListener {

    private GerenciadorEleitor gel = new GerenciadorEleitor();
    private GerenciadorEleicao ge = new GerenciadorEleicao();

    /**
     * Creates new form FrmIdentificacao
     */
    public FrmIdentificacao() {
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setUndecorated(true);
        initComponents();
        addComponentListener(this);
        centralizarPanel(jPanel2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tffMatricula = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        btProsseguir = new javax.swing.JButton();
        btCorrigir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 1, true), "Identifique-se", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Black", 1, 24), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Matrícula:");

        try {
            tffMatricula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#########-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tffMatricula.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tffMatricula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tffMatriculaKeyPressed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setForeground(new java.awt.Color(0, 204, 51));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        btProsseguir.setBackground(new java.awt.Color(0, 153, 0));
        btProsseguir.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btProsseguir.setForeground(new java.awt.Color(255, 255, 255));
        btProsseguir.setText("Prosseguir");
        btProsseguir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProsseguirActionPerformed(evt);
            }
        });
        jPanel3.add(btProsseguir);

        btCorrigir.setBackground(new java.awt.Color(255, 255, 0));
        btCorrigir.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btCorrigir.setText("Corrigir");
        btCorrigir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCorrigirActionPerformed(evt);
            }
        });
        jPanel3.add(btCorrigir);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("OBS: Utilize o teclado numérico para inserir sua matrícula.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tffMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tffMatricula))
                .addGap(48, 48, 48)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCorrigirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCorrigirActionPerformed
        this.tffMatricula.setText(null);
    }//GEN-LAST:event_btCorrigirActionPerformed

    private void btProsseguirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProsseguirActionPerformed
        try {
            Eleitor eleitor = gel.obterEleitor(this.tffMatricula.getText());
            if (eleitor != null) {

                ControleDeVotacao.setEleitorIdentificado(eleitor);
                FrmEscolherEleicao fee = new FrmEscolherEleicao();
                fee.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Está matrícula não está cadastrada!", "Matrícula inválida!", JOptionPane.ERROR_MESSAGE);
                this.tffMatricula.selectAll();
                this.tffMatricula.requestFocus();
            }

        } catch (SQLStatementException | ConnectionException | NomeException | MatriculaException | ValidarEmailException | ValidarCursoException ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btProsseguirActionPerformed

    private void tffMatriculaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tffMatriculaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            int opc = JOptionPane.showConfirmDialog(this, "Deseja encerrar a Votação?", "Encerrar Votação", JOptionPane.YES_NO_OPTION);
            if (opc == 0) {
                autenticarAdministrador();
            }

        }
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
             try {
            Eleitor eleitor = gel.obterEleitor(this.tffMatricula.getText());
            if (eleitor != null) {

                ControleDeVotacao.setEleitorIdentificado(eleitor);
                FrmEscolherEleicao fee = new FrmEscolherEleicao();
                fee.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Está matrícula não está cadastrada!", "Matrícula inválida!", JOptionPane.ERROR_MESSAGE);
                this.tffMatricula.selectAll();
                this.tffMatricula.requestFocus();
            }

        } catch (SQLStatementException | ConnectionException | NomeException | MatriculaException | ValidarEmailException | ValidarCursoException ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
        }
        }
    }//GEN-LAST:event_tffMatriculaKeyPressed

    private void centralizarPanel(JPanel p) {
        int pontoLargura = ((this.getWidth() / 2) - p.getWidth() / 2);
        int pontoAltura = ((this.getHeight() / 2) - p.getHeight() / 2 - 50);

        p.setLocation(pontoLargura, pontoAltura);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCorrigir;
    private javax.swing.JButton btProsseguir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JFormattedTextField tffMatricula;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentResized(ComponentEvent e) {
        centralizarPanel(jPanel2);
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

    private void autenticarAdministrador() {

        Autenticacao aut = new Autenticacao();

        JPasswordField password = new JPasswordField(10);
        password.setEchoChar('*');

        JLabel administrador = new JLabel("Administrador: " + Autenticacao.getAdministrador().getUsuario());
        administrador.setForeground(Color.BLUE);
        JLabel rotulo = new JLabel("Senha:");
        

        JPanel autenticarAdministrador = new JPanel();
        autenticarAdministrador.add(administrador);
        autenticarAdministrador.add(rotulo);
        autenticarAdministrador.add(password);

        JOptionPane.showMessageDialog(null, autenticarAdministrador, "Autenticar Administrador", JOptionPane.PLAIN_MESSAGE);

        String senha = new String(password.getPassword());
        try {
            if (aut.consultar(Autenticacao.getAdministrador().getUsuario(), senha)) {
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Senha incorreta!", "Erro!", JOptionPane.ERROR_MESSAGE);
                autenticarAdministrador();
            }
        } catch (SQLException | ConnectionException | SQLStatementException ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);

        }
    }

}
