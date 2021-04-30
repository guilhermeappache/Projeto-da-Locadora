
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.locadora.telas;

/**
 *
 * @author guilh
 */
import java.sql.*;
import br.com.locadora.dal.ModuloConexao;
import static java.awt.Frame.MAXIMIZED_BOTH;
import javax.swing.JOptionPane;

public class TelaUsuario extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    //use setForeground para mudar a cor e Color pra colocar o código rgb da cor

    /**
     * Creates new form TelaUsuario
     */
    public TelaUsuario() {
        initComponents();
        conexao = ModuloConexao.conector();
       
    }
    // metodo para consultar usuários...

    private void consultar() {
        String sql = "select * from usuario where id=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id_camp.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txt_login.setText(rs.getString(1));
                txt_senha.setText(rs.getString(2));
                // Abaixo aqui capturo a opções no ComboBox;
                opc_perfil.setSelectedItem(rs.getString(3));
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado");
                // Caso haja informações no campo, serão limpas.
                txt_login.setText(null);
                txt_senha.setText(null);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    // Metodo para adicionar usuários*-*

    private void adicionar() {
        String sql = "insert into usuario(login, senha, perfil, id) VALUES (?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txt_login.getText());
            pst.setString(2, txt_senha.getText());
            pst.setString(3, opc_perfil.getSelectedItem().toString());
            pst.setString(4, id_camp.getText());
            // Validação dos campos;

            if (txt_login.getText().isEmpty() || (txt_senha.getText().isEmpty()) || (id_camp.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Algum campo esta vázio");
            } else {

                pst.executeUpdate();
                // A estrutura abaixo é para confirmar a inserção de dados na tabela
                int adicionado = pst.executeUpdate();
                /* System.out.println(adicionado); */ // Essa linha Printa no console o número 1 no caso uma linha que atualizou;
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso");
                    txt_login.setText(null);
                    txt_senha.setText(null);
                    id_camp.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    // Abaixo estou criando o metodo para alterar dados dos usuarios;
    private void alterar() {
        String sql = "update usuario set login=?, senha=?, perfil=? where id=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txt_login.getText());
            pst.setString(2, txt_senha.getText());
            pst.setString(3, opc_perfil.getSelectedItem().toString());
            pst.setString(4, id_camp.getText());
            if (txt_login.getText().isEmpty() || (txt_senha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Algum campo esta vázio");
            } else {

                pst.executeUpdate();
                // A estrutura abaixo é para confirmar a alteração de dados na tabela
                int adicionado = pst.executeUpdate();
                /*System.out.println(adicionado); */ // Essa linha Printa no console o número 1 no caso uma linha que atualizou;
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados alterados com sucesso");
                    txt_login.setText(null);
                    txt_senha.setText(null);
                    id_camp.setText(null);

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    // metodo que excluí usuários
    private void excluir() {
        // antes de excluir ira confirmar a exclusão do usuário
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluír este usuário", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION);
        //String upt ="SET SQL_SAFE_UPDATES=0";
        String sql = "delete  from usuario where ID=?";
        try {
            //pst = conexao.prepareStatement(upt);
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id_camp.getText());
            int apagado = pst.executeUpdate();
            if (apagado > 0) {
                JOptionPane.showMessageDialog(null, "Usuário excluido com sucesso");
                txt_login.setText(null);
                txt_senha.setText(null);
                id_camp.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_login = new javax.swing.JTextField();
        txt_senha = new javax.swing.JTextField();
        opc_perfil = new javax.swing.JComboBox<>();
        btc_add_usu = new javax.swing.JButton();
        bnc_usu_delete = new javax.swing.JButton();
        btn_usu_search = new javax.swing.JButton();
        btnc_usu_editar = new javax.swing.JButton();
        lbl_id = new javax.swing.JLabel();
        id_camp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuários");
        setPreferredSize(new java.awt.Dimension(514, 0));

        jLabel1.setText("Login");

        jLabel2.setText("Senha");

        jLabel3.setText("Perfil");

        opc_perfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "funcionario" }));

        btc_add_usu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/locadora/icones/add.png"))); // NOI18N
        btc_add_usu.setToolTipText("Adicionar");
        btc_add_usu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btc_add_usu.setPreferredSize(new java.awt.Dimension(80, 80));
        btc_add_usu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btc_add_usuActionPerformed(evt);
            }
        });

        bnc_usu_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/locadora/icones/delete.png"))); // NOI18N
        bnc_usu_delete.setToolTipText("Delete");
        bnc_usu_delete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bnc_usu_delete.setPreferredSize(new java.awt.Dimension(80, 80));
        bnc_usu_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnc_usu_deleteActionPerformed(evt);
            }
        });

        btn_usu_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/locadora/icones/search.png"))); // NOI18N
        btn_usu_search.setToolTipText("Procurar");
        btn_usu_search.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_usu_search.setPreferredSize(new java.awt.Dimension(80, 80));
        btn_usu_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_usu_searchActionPerformed(evt);
            }
        });

        btnc_usu_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/locadora/icones/edit.png"))); // NOI18N
        btnc_usu_editar.setToolTipText("Editar");
        btnc_usu_editar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnc_usu_editar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnc_usu_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnc_usu_editarActionPerformed(evt);
            }
        });

        lbl_id.setText("ID");

        jLabel4.setText("Todos os campos são obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btc_add_usu, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(bnc_usu_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl_id))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(52, 52, 52)
                                        .addComponent(id_camp, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(19, 19, 19)
                                        .addComponent(opc_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(34, 34, 34)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btn_usu_search, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnc_usu_editar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(14, 14, 14)
                            .addComponent(txt_login, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_senha, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bnc_usu_delete, btc_add_usu, btn_usu_search, btnc_usu_editar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_senha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txt_login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_id)
                            .addComponent(id_camp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(opc_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(78, 78, 78)
                .addComponent(jLabel4)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bnc_usu_delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_usu_search, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnc_usu_editar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btc_add_usu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );

        setBounds(0, 0, 515, 466);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_usu_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_usu_searchActionPerformed
        // Aqui chama o metodo consultar :
        consultar();
    }//GEN-LAST:event_btn_usu_searchActionPerformed

    private void btc_add_usuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btc_add_usuActionPerformed
        // Aqui  chama o metodo adicionar
        adicionar();
    }//GEN-LAST:event_btc_add_usuActionPerformed

    private void btnc_usu_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnc_usu_editarActionPerformed
        // Aqui chama o metodo alterar
        alterar();
    }//GEN-LAST:event_btnc_usu_editarActionPerformed

    private void bnc_usu_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnc_usu_deleteActionPerformed
        // Chama o método de excluir
        excluir();
    }//GEN-LAST:event_bnc_usu_deleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnc_usu_delete;
    private javax.swing.JButton btc_add_usu;
    private javax.swing.JButton btn_usu_search;
    private javax.swing.JButton btnc_usu_editar;
    public static javax.swing.JTextField id_camp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lbl_id;
    private javax.swing.JComboBox<String> opc_perfil;
    private javax.swing.JTextField txt_login;
    private javax.swing.JTextField txt_senha;
    // End of variables declaration//GEN-END:variables

    private void setExtendedState(int MAXIMIZED_BOTH) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
