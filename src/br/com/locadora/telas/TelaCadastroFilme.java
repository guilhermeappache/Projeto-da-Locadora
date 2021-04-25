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
import javax.swing.JOptionPane;

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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de Filmes");
        setPreferredSize(new java.awt.Dimension(514, 466));

        jLabel1.setText("Código ");

        jLabel2.setText("Nome do Filme");

        jLabel3.setText("Genero");

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

        btnExcluiFil.setText("Excluir");

        btnConFil.setText("Consultar");
        btnConFil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConFilActionPerformed(evt);
            }
        });

        btnAlteFil.setText("Alterar");

        cbGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ação", "Animação", "Aventura", "Comédia", "Drama", "Ficção Cientifíca", "Infantil", "Policial", "Suspense ", "Terror", " " }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(codFilme)
                    .addComponent(cbClass, 0, 145, Short.MAX_VALUE)
                    .addComponent(datCadastro)
                    .addComponent(quantidadeFilme)
                    .addComponent(nomeFilme)
                    .addComponent(cbGenero, 0, 145, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btcCadFil)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnAlteFil, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExcluiFil, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConFil, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(codFilme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(btcCadFil)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nomeFilme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(btnExcluiFil)
                    .addComponent(cbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(datCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(btnConFil)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(quantidadeFilme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(btnAlteFil)))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        setBounds(0, 0, 514, 466);
    }// </editor-fold>//GEN-END:initComponents

    private void datCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_datCadastroActionPerformed

    private void btnConFilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConFilActionPerformed
        // Aqui chama o Método de consultar :
        consultar();
    }//GEN-LAST:event_btnConFilActionPerformed


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
    private javax.swing.JTextField nomeFilme;
    private javax.swing.JTextField quantidadeFilme;
    // End of variables declaration//GEN-END:variables
}
