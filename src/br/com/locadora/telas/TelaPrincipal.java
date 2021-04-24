/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.locadora.telas;

import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author guilh
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        usu_conectado = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblUsu = new javax.swing.JLabel();
        dat = new javax.swing.JLabel();
        desktop = new javax.swing.JDesktopPane();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        menu = new javax.swing.JMenuBar();
        main_cad = new javax.swing.JMenu();
        main_cad_cli = new javax.swing.JMenuItem();
        main_cad_depen = new javax.swing.JMenuItem();
        main_cad_usu = new javax.swing.JMenuItem();
        main_filme = new javax.swing.JMenu();
        main_cad_filme = new javax.swing.JMenuItem();
        main_nova_loc = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        main_opc = new javax.swing.JMenu();
        main_sair = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        ajuda = new javax.swing.JMenuItem();

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/locadora/icones/iconfinder_Flying_Iron_Man_73371.png"))); // NOI18N

        jLabel4.setText("jLabel4");

        jDesktopPane1.setLayer(usu_conectado, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(692, 692, 692)
                .addComponent(usu_conectado)
                .addContainerGap(242, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(555, 555, 555)
                .addComponent(usu_conectado)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jLabel6.setText("jLabel6");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/locadora/icones/iconfinder_Flying_Iron_Man_73371.png"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("*Video Locadora*");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        lblUsu.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblUsu.setText("Usuário");

        dat.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        dat.setText("Data");

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 514, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 466, Short.MAX_VALUE)
        );

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/locadora/icones/iconfinder_.png"))); // NOI18N

        main_cad.setText("Cadastro");
        main_cad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                main_cadStateChanged(evt);
            }
        });

        main_cad_cli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        main_cad_cli.setText("Cliente");
        main_cad_cli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                main_cad_cliActionPerformed(evt);
            }
        });
        main_cad.add(main_cad_cli);

        main_cad_depen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK));
        main_cad_depen.setText("Dependente");
        main_cad.add(main_cad_depen);

        main_cad_usu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        main_cad_usu.setText("Usuário");
        main_cad_usu.setEnabled(false);
        main_cad_usu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                main_cad_usuActionPerformed(evt);
            }
        });
        main_cad.add(main_cad_usu);

        menu.add(main_cad);

        main_filme.setText("Filmes");

        main_cad_filme.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK));
        main_cad_filme.setText("Cadastrar filme");
        main_cad_filme.setEnabled(false);
        main_cad_filme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                main_cad_filmeActionPerformed(evt);
            }
        });
        main_filme.add(main_cad_filme);

        main_nova_loc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK));
        main_nova_loc.setText("Nova Locação");
        main_nova_loc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                main_nova_locActionPerformed(evt);
            }
        });
        main_filme.add(main_nova_loc);

        menu.add(main_filme);
        menu.add(jMenu3);

        main_opc.setText("Opções");

        main_sair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        main_sair.setText("Sair");
        main_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                main_sairActionPerformed(evt);
            }
        });
        main_opc.add(main_sair);

        menu.add(main_opc);

        jMenu1.setText("Ajuda");

        ajuda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.ALT_MASK));
        ajuda.setText("Sobre");
        ajuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajudaActionPerformed(evt);
            }
        });
        jMenu1.add(ajuda);

        menu.add(jMenu1);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dat)
                                .addGap(2474, 2474, 2474)
                                .addComponent(jLabel1))
                            .addComponent(lblUsu)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel9)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lblUsu)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(dat)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(97, 97, 97)
                .addComponent(jLabel9)
                .addGap(58, 58, 58))
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(772, 566));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void main_cad_cliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_main_cad_cliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_main_cad_cliActionPerformed

    private void main_nova_locActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_main_nova_locActionPerformed

    }//GEN-LAST:event_main_nova_locActionPerformed
    // Aqui é o metodo para capturar a data do sistema
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        dat.setText(formatador.format(data));

    }//GEN-LAST:event_formWindowActivated

    private void main_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_main_sairActionPerformed
        // Aqui abre a caixa de dialogo para confirmar a saída do programa
        // o If abaixo serve para na caixa de dialgo caso a respota seja SIM, então o sistema sera encerrado.
        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que quer sair ?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_main_sairActionPerformed

    private void ajudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajudaActionPerformed
        // Chama tela Sobre**/
        TelaSobre sobre = new TelaSobre();
        sobre.setVisible(true);
    }//GEN-LAST:event_ajudaActionPerformed

    private void main_cad_usuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_main_cad_usuActionPerformed
        // As linhas que seguem irão abrir o form TelaUsuario dentro da area DesktopPane.
        TelaUsuario usuario = new TelaUsuario();
        usuario.setVisible(true);
        desktop.add(usuario);

    }//GEN-LAST:event_main_cad_usuActionPerformed

    private void main_cadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_main_cadStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_main_cadStateChanged

    private void main_cad_filmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_main_cad_filmeActionPerformed
        // chama a a tela de cadastro de filme.
        TelaCadastroFilme filme = new TelaCadastroFilme();
        filme.setVisible(true);
        desktop.add(filme);
    }//GEN-LAST:event_main_cad_filmeActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ajuda;
    private javax.swing.JLabel dat;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    public static javax.swing.JLabel lblUsu;
    private javax.swing.JMenu main_cad;
    private javax.swing.JMenuItem main_cad_cli;
    private javax.swing.JMenuItem main_cad_depen;
    public static javax.swing.JMenuItem main_cad_filme;
    public static javax.swing.JMenuItem main_cad_usu;
    private javax.swing.JMenu main_filme;
    private javax.swing.JMenuItem main_nova_loc;
    private javax.swing.JMenu main_opc;
    private javax.swing.JMenuItem main_sair;
    private javax.swing.JMenuBar menu;
    private javax.swing.JLabel usu_conectado;
    // End of variables declaration//GEN-END:variables
}
