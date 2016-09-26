/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.armazenamento;

import view.Principal;
import view.cadastros.DialogCadastroAdministrador;
import conexao.exceptions.ConnectionException;
import conexao.exceptions.SQLStatementException;
import contreller.DAO.GerenciadorAdministrador;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import model.Administrador;
import model.exceptions.ValidarAdministradorException;

/**
 *
 * @author Cliente
 */
public class DialogArmazenamentoAdministrador extends javax.swing.JDialog {

    GerenciadorAdministrador ga = new GerenciadorAdministrador();
    Administrador a;

    /**
     * Creates new form DialogArmazenamentoAdministrador
     */
    public DialogArmazenamentoAdministrador(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            definirValoresLista(ga.getTodosAdministradores());
        } catch (ConnectionException | SQLStatementException | SQLException | ValidarAdministradorException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro: " + ex.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
        } 
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lAdministradores = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        btMaisCandidatos = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btPesquisar = new javax.swing.JButton();
        btFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configurações da Conta");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Configurações da Conta", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Black", 1, 14))); // NOI18N

        jLabel1.setText("Contas Ativas:");

        lAdministradores.setBackground(new java.awt.Color(255, 255, 204));
        lAdministradores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lAdministradoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lAdministradores);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        btMaisCandidatos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btMaisCandidatos.setForeground(new java.awt.Color(0, 102, 0));
        btMaisCandidatos.setText("+ Contas");
        btMaisCandidatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMaisCandidatosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 31;
        gridBagConstraints.ipady = 15;
        jPanel2.add(btMaisCandidatos, gridBagConstraints);

        btExcluir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btExcluir.setForeground(new java.awt.Color(255, 0, 0));
        btExcluir.setText("Excluir");
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 31;
        gridBagConstraints.ipady = 15;
        jPanel2.add(btExcluir, gridBagConstraints);

        btPesquisar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btPesquisar.setText("Pesquisar");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 31;
        gridBagConstraints.ipady = 15;
        jPanel2.add(btPesquisar, gridBagConstraints);

        btFechar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btFechar.setForeground(new java.awt.Color(0, 0, 204));
        btFechar.setText("Fechar");
        btFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFecharActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 31;
        gridBagConstraints.ipady = 15;
        jPanel2.add(btFechar, gridBagConstraints);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
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

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFecharActionPerformed

        Principal p = new Principal();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btFecharActionPerformed

    private void btMaisCandidatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMaisCandidatosActionPerformed
        DialogCadastroAdministrador dca = new DialogCadastroAdministrador(null, rootPaneCheckingEnabled);
        dca.setVisible(true);
    }//GEN-LAST:event_btMaisCandidatosActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        try {
            if(ga.getTodosAdministradores().size() > 1){
            if (lAdministradores.getSelectedIndex() > -1) {
                int opc = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente remover o Administrador?", "Excluir..", JOptionPane.YES_NO_OPTION);
                if (opc == 0) {
                    ga.remover(ga.getTodosAdministradores().get(lAdministradores.getSelectedIndex()));
                    definirValoresLista(ga.getTodosAdministradores());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Atenção! Por favor selecione uma Conta a ser excluída!", "Atenção!", JOptionPane.WARNING_MESSAGE);
            }
        }else{
                JOptionPane.showMessageDialog(this, "Atenção! Só há uma conta cadastrada no momento," + "\n" 
                        + "Por favor, crie mais uma conta para poder excluir esta.", "Atenção!", JOptionPane.WARNING_MESSAGE);
            }
        } catch (ConnectionException | SQLStatementException | SQLException | ValidarAdministradorException ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btExcluirActionPerformed

    private void lAdministradoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lAdministradoresMouseClicked
        if (evt.getClickCount() == 2) {
            DialogCadastroAdministrador dca = new DialogCadastroAdministrador(null, rootPaneCheckingEnabled);

            try {
                dca.setAdministrador(ga.getTodosAdministradores().get(lAdministradores.getSelectedIndex()));
                dca.getTfNovaConta().setText(dca.getAdministrador().getUsuario());
                dca.getPsSenha().setText(dca.getAdministrador().getSenha());
            } catch (ConnectionException | SQLStatementException | SQLException | ValidarAdministradorException ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            } 
            dca.setVisible(true);
        }
    }//GEN-LAST:event_lAdministradoresMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Principal p = new Principal();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private static DefaultListModel<String> converterArrayEmDefautList(ArrayList<Administrador> listaArray) {
        DefaultListModel<String> defaultList = new DefaultListModel<>();
        for (Administrador a : listaArray) {
            defaultList.addElement(a.getUsuario());
        }
        return defaultList;
    }

    public static void definirValoresLista(ArrayList<Administrador> listaArray) {

        lAdministradores.setModel(converterArrayEmDefautList(listaArray));

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btFechar;
    private javax.swing.JButton btMaisCandidatos;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JList lAdministradores;
    // End of variables declaration//GEN-END:variables
}
