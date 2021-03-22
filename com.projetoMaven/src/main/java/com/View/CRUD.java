package com.View;

import com.Controler.Utilidades;
import com.DAO.ConexaoDAO;
import com.Modelos.Cliente;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aleph Mantovani
 */
public class CRUD extends javax.swing.JFrame {

    Cliente cli;
    ConexaoDAO con;
    Utilidades util;
    DefaultTableModel tab;

    public CRUD() {
        initComponents();
        configWindow();

        tab = (DefaultTableModel) tabelaConsulta.getModel();
        consultaTudo();
    }

    //Configurar o Frame
    private void configWindow() {
        setTitle("..::CRUD - Cadastro de Clientes::..");
        setLocationRelativeTo(this);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().setBackground(Color.WHITE);
        
        //Icone do sistema
        util = new Utilidades();
        util.inserirIcone(this);
    }

    //Inserir ou Alterar registro no Banco de Dados
    private void inserir() throws Exception {

        con = new ConexaoDAO();
        cli = new Cliente();
        util = new Utilidades();

        if (txt_Nome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo NOME!", "Campo NOME Vazio", 2);
            txt_Nome.requestFocus();
        } else {
            if (txt_Sobrenome.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o campo SOBRENOME!", "Campo SOBRENOME Vazio", 2);
                txt_Sobrenome.requestFocus();
            } else {
                try {
                    cli.setNome(txt_Nome.getText());
                    cli.setSobrenome(txt_Sobrenome.getText());

                    cli = con.salvar(cli);

                    util.limparTodosCampos(pnl_Add);
                    consultaTudo();
                } catch (Exception ex) {
                    Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    //Consulta e coloca na tabela
    private void consultaTudo() {
        con = new ConexaoDAO();

        tab.setRowCount(0);
        con.consultaTudo().forEach((c) -> {
            tab.addRow(new Object[]{c.getId(), c.getNome(), c.getSobrenome()});
        });
    }

    //Exclui o cadastro selecionado na tabela
    private void excluir() throws Exception {
        con = new ConexaoDAO();
        cli = new Cliente();

        try {
            if (tabelaConsulta.getSelectedRow() != -1) {
                cli.setId((Long) tab.getValueAt(tabelaConsulta.getSelectedRow(), 0));

                int resp = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esse cadastro?",
                        "ATENÇÃO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (resp == 0) {
                    con.remover(cli.getId());
                    consultaTudo();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma linha na tabela!", "AVISO", 2);
            }
        } catch (Exception ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Alterar o cadastro selecionado na tabela
    private void alterar() {
        cli = new Cliente();
        con = new ConexaoDAO();

        try {
            if (tabelaConsulta.getSelectedRow() != -1) {
                cli.setId((Long) tab.getValueAt(tabelaConsulta.getSelectedRow(), 0));
                cli.setNome((String) tab.getValueAt(tabelaConsulta.getSelectedRow(), 1));
                cli.setSobrenome((String) tab.getValueAt(tabelaConsulta.getSelectedRow(), 2));

                int resp = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja alterar esse cadastro?",
                        "ATENÇÃO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (resp == 0) {
                    con.salvar(cli);
                    consultaTudo();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma linha na tabela!", "AVISO", 2);
            }
        } catch (Exception ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_Add = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_Nome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_Sobrenome = new javax.swing.JTextField();
        btn_Inserir = new javax.swing.JButton();
        pnl_Consulta = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaConsulta = new javax.swing.JTable();
        btnSair = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setType(java.awt.Window.Type.POPUP);

        pnl_Add.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Inserir Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 12), new java.awt.Color(0, 0, 153))); // NOI18N
        pnl_Add.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("NOME");

        txt_Nome.setBackground(new java.awt.Color(244, 244, 244));
        txt_Nome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_Nome.setName("nome"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 102));
        jLabel2.setText("SOBRENOME");

        txt_Sobrenome.setBackground(new java.awt.Color(244, 244, 244));
        txt_Sobrenome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_Sobrenome.setName("sobrenome"); // NOI18N

        btn_Inserir.setBackground(new java.awt.Color(102, 102, 255));
        btn_Inserir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_Inserir.setForeground(new java.awt.Color(242, 242, 255));
        btn_Inserir.setText("INSERIR");
        btn_Inserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InserirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_AddLayout = new javax.swing.GroupLayout(pnl_Add);
        pnl_Add.setLayout(pnl_AddLayout);
        pnl_AddLayout.setHorizontalGroup(
            pnl_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_AddLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_AddLayout.createSequentialGroup()
                        .addGroup(pnl_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txt_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnl_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_AddLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txt_Sobrenome)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_AddLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_Inserir)))
                .addContainerGap())
        );
        pnl_AddLayout.setVerticalGroup(
            pnl_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_AddLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Sobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btn_Inserir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnl_Consulta.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consulta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 12), new java.awt.Color(0, 51, 153))); // NOI18N
        pnl_Consulta.setOpaque(false);

        tabelaConsulta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabelaConsulta.setForeground(new java.awt.Color(102, 102, 102));
        tabelaConsulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME", "SOBRENOME"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabelaConsulta.setGridColor(new java.awt.Color(204, 204, 255));
        jScrollPane1.setViewportView(tabelaConsulta);
        if (tabelaConsulta.getColumnModel().getColumnCount() > 0) {
            tabelaConsulta.getColumnModel().getColumn(0).setResizable(false);
            tabelaConsulta.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabelaConsulta.getColumnModel().getColumn(1).setResizable(false);
            tabelaConsulta.getColumnModel().getColumn(1).setPreferredWidth(200);
            tabelaConsulta.getColumnModel().getColumn(2).setResizable(false);
            tabelaConsulta.getColumnModel().getColumn(2).setPreferredWidth(340);
        }

        javax.swing.GroupLayout pnl_ConsultaLayout = new javax.swing.GroupLayout(pnl_Consulta);
        pnl_Consulta.setLayout(pnl_ConsultaLayout);
        pnl_ConsultaLayout.setHorizontalGroup(
            pnl_ConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnl_ConsultaLayout.setVerticalGroup(
            pnl_ConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSair.setBackground(new java.awt.Color(102, 102, 255));
        btnSair.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSair.setForeground(new java.awt.Color(242, 242, 255));
        btnSair.setText("SAIR");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnAlterar.setBackground(new java.awt.Color(102, 102, 255));
        btnAlterar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAlterar.setForeground(new java.awt.Color(242, 242, 255));
        btnAlterar.setText("ALTERAR");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnExcluir.setBackground(new java.awt.Color(102, 102, 255));
        btnExcluir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnExcluir.setForeground(new java.awt.Color(242, 242, 255));
        btnExcluir.setText("EXCLUIR");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnl_Add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_Consulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAlterar)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSair)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnl_Add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_Consulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSair)
                    .addComponent(btnAlterar)
                    .addComponent(btnExcluir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //INSERIR
    private void btn_InserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_InserirActionPerformed
        try {
            inserir();
        } catch (Exception ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_InserirActionPerformed

    //SAIR
    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try {
            excluir();
        } catch (Exception ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        alterar();
    }//GEN-LAST:event_btnAlterarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CRUD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btn_Inserir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnl_Add;
    private javax.swing.JPanel pnl_Consulta;
    private javax.swing.JTable tabelaConsulta;
    private javax.swing.JTextField txt_Nome;
    private javax.swing.JTextField txt_Sobrenome;
    // End of variables declaration//GEN-END:variables
}
