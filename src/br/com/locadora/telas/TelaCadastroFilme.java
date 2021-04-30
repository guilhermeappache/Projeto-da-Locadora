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
import br.com.locadora.dal.DvdDao;
import java.sql.*;
import br.com.locadora.dal.ModuloConexao;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.beans.PropertyVetoException;
import javax.swing.JOptionPane;
import util.Util;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class TelaCadastroFilme extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaCadastroFilme
     */
    public TelaCadastroFilme() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    //Metodo para consultar o Filme no Banco de dados
    public void consultar() {
        //String sql = "select * from dvd_titulo where cod_dvd=?";
        String sql = "SELECT dvd.cod_dvd, dvd.nom_dvd, genero.descri_genero, dvd.dat_cadastro, dvd.qtd_cadastrada, dvd.censu_dvd   \n"
                + " FROM dvd_titulo dvd  \n"
                + " right JOIN genero_dvd genero ON dvd.cod_genero = genero.cod_genero \n"
                + " where cod_dvd=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, codFilme.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                codFilme.setText(rs.getString(1));
                nomeFilme.setText(rs.getString(2));
                datCadastro.setText(rs.getString(4));
                quantidadeFilme.setText(rs.getString(5));
                // Abaixo aqui capturo a opções no ComboBox;
                cbGenero.setSelectedItem(rs.getString(3));
                cbClass.setSelectedItem(rs.getString(6));
            } else {
                JOptionPane.showMessageDialog(null, "Filme não encontrado");
                // Caso haja informações no campo, serão limpas.
                codFilme.setText(null);
                nomeFilme.setText(null);
                datCadastro.setText(null);
                quantidadeFilme.setText(null);
                cbClass.setSelectedItem(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    /*
     public void consultar() {
      
        try {
           DvdDao dao = new DvdDao();
          ResultSet rs2 = dao.consultar(codFilme.getText());
           if (rs2.next()) {
                codFilme.setText(rs2.getString(1));
                nomeFilme.setText(rs2.getString(2));
                datCadastro.setText(rs2.getString(4));
                quantidadeFilme.setText(rs2.getString(5));
                // Abaixo aqui capturo a opções no ComboBox;
                cbGenero.setSelectedItem(rs2.getString(3));
                cbClass.setSelectedItem(rs2.getString(6));
            } else {
                JOptionPane.showMessageDialog(null, "Filme não encontrado");
                // Caso haja informações no campo, serão limpas.
                codFilme.setText(null);
                nomeFilme.setText(null);
                datCadastro.setText(null);
                quantidadeFilme.setText(null);
                cbClass.setSelectedItem(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
     */
    // Metodo adicionar 
    private void adicionar() {
        String sql = "insert into dvd_titulo"
                + "(nom_dvd, cod_genero, dat_cadastro, qtd_cadastrada, censu_dvd)"
                + "VALUES(?,?,?,?,?)";

        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(1, nomeFilme.getText());
            pst.setInt(2, obterGeneroDescr(cbGenero.getSelectedItem().toString()));
            pst.setString(3, datCadastro.getText());
            pst.setString(4, quantidadeFilme.getText());
            pst.setString(5, cbClass.getSelectedItem().toString());
            // Validação dos campos;

            if (codFilme.getText().isEmpty() || (nomeFilme.getText().isEmpty()) || (cbGenero.getSelectedItem().toString().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Algum campo esta vázio");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Filme cadastrado com sucesso");
                    codFilme.setText(null);
                    nomeFilme.setText(null);
                    datCadastro.setText(null);
                    quantidadeFilme.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void excluir() {
        // antes de excluir ira confirmar a exclusão do usuário
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluír este usuário", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION);
        //String upt ="SET SQL_SAFE_UPDATES=0";
        String sql = "delete  from dvd_titulo where cod_dvd=?";
        try {
            //pst = conexao.prepareStatement(upt);
            pst = conexao.prepareStatement(sql);
            pst.setString(1, codFilme.getText());
            int apagado = pst.executeUpdate();
            if (apagado > 0) {
                JOptionPane.showMessageDialog(null, "Usuário excluido com sucesso");
                codFilme.setText(null);
                nomeFilme.setText(null);
                datCadastro.setText(null);
                quantidadeFilme.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void alterar() {
        String sql = "update dvd_titulo set"
                + "(nom_dvd=?, cod_genero=?, dat_cadastro=?, qtd_cadastrada=?, censu_dvd=? where cod_dvd=?)";
        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(1, nomeFilme.getText());
            pst.setInt(2, obterGeneroDescr(cbGenero.getSelectedItem().toString()));
            pst.setString(3, quantidadeFilme.getText());
            pst.setString(4, cbClass.getSelectedItem().toString());
            pst.setString(6, codFilme.getText());

            if (codFilme.getText().isEmpty() || (nomeFilme.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Algum campo esta vázio");
            } else {
                pst.executeUpdate();
                // A estrutura abaixo é para confirmar a alteração de dados na tabela
                int adicionado = pst.executeUpdate();
                /* System.out.println(adicionado);*/ // // Essa linha Printa no console o número 1 no caso uma linha que atualizou;
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados alterados com sucesso");

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private int obterGeneroDescr(String descri) {
        String sql = "SELECT * from genero_dvd where descri_genero =?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, descri);
            rs = pst.executeQuery();
            if (rs.next()) {
                return Integer.valueOf(rs.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbClass = new javax.swing.JComboBox<>();
        codFilme = new javax.swing.JTextField();
        datCadastro = new javax.swing.JTextField();
        quantidadeFilme = new javax.swing.JTextField();
        nomeFilme = new javax.swing.JTextField();
        btcCadFil = new javax.swing.JButton();
        btnExcluiFil = new javax.swing.JButton();
        btnConFil = new javax.swing.JButton();
        btnAlteFil = new javax.swing.JButton();
        cbGenero = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de Filmes");
        setPreferredSize(new java.awt.Dimension(514, 466));

        jLabel1.setText("Código ");

        jLabel2.setText("Nome do Filme");

        jLabel3.setText("Código do Gênero");

        jLabel4.setText("Data de Cadastramento");

        jLabel5.setText("Quantidade");

        jLabel6.setText("Classificação");

        cbClass.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Livre", "10 Anos", "12 Anos", "14 Anos", "16 Anos", "18 Anos" }));

        datCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datCadastroActionPerformed(evt);
            }
        });

        btcCadFil.setText("Cadastrar");
        btcCadFil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcCadFilActionPerformed(evt);
            }
        });

        btnExcluiFil.setText("Excluir");
        btnExcluiFil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluiFilActionPerformed(evt);
            }
        });

        btnConFil.setText("Consultar");
        btnConFil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConFilActionPerformed(evt);
            }
        });

        btnAlteFil.setText("Alterar");
        btnAlteFil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlteFilActionPerformed(evt);
            }
        });

        cbGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ação", "Animação", "Aventura", "Comédia", "Drama", "Ficção ", "Infantil", "Policial", "Suspense ", "Terror", " " }));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 31, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(quantidadeFilme, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(70, 70, 70))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbClass, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(datCadastro)
                                    .addComponent(nomeFilme)
                                    .addComponent(cbGenero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(codFilme, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btcCadFil, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAlteFil, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluiFil, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConFil, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(btnConFil)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(30, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btcCadFil)
                        .addGap(66, 66, 66)
                        .addComponent(btnAlteFil)
                        .addGap(75, 75, 75)
                        .addComponent(btnExcluiFil)
                        .addGap(51, 51, 51))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(codFilme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(nomeFilme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(datCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(quantidadeFilme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cbClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33))))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbClass, cbGenero, codFilme, datCadastro, jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, nomeFilme, quantidadeFilme});

        setBounds(0, 0, 492, 475);
    }// </editor-fold>//GEN-END:initComponents

    private void datCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_datCadastroActionPerformed

    private void btnConFilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConFilActionPerformed
        // Aqui chama o Método de consultar :
        consultar();
    }//GEN-LAST:event_btnConFilActionPerformed

    private void btcCadFilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcCadFilActionPerformed
        // Aqui chama o Método de Adicionar
        adicionar();
    }//GEN-LAST:event_btcCadFilActionPerformed

    private void btnExcluiFilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluiFilActionPerformed
        // Aqui chama o método de excluir.
        excluir();
    }//GEN-LAST:event_btnExcluiFilActionPerformed

    private void btnAlteFilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlteFilActionPerformed
        // Aqui chama alterar
        alterar();
    }//GEN-LAST:event_btnAlteFilActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btcCadFil;
    private javax.swing.JButton btnAlteFil;
    private javax.swing.JButton btnConFil;
    private javax.swing.JButton btnExcluiFil;
    private javax.swing.JComboBox<String> cbClass;
    private javax.swing.JComboBox<String> cbGenero;
    private javax.swing.JTextField codFilme;
    private javax.swing.JTextField datCadastro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField nomeFilme;
    private javax.swing.JTextField quantidadeFilme;
    // End of variables declaration//GEN-END:variables

    private void setExtendedState(int MAXIMIZED_BOTH) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
