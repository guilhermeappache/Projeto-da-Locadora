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
import static br.com.locadora.telas.TelaUsuario.id_camp;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.beans.PropertyVetoException;
import javax.swing.JOptionPane;
import util.Util;
import java.sql.Date;
import java.text.SimpleDateFormat;
import net.proteanit.sql.DbUtils;

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
    private void consultar() {
        String sql = "select * from dvd_titulo where cod_dvd=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, codFilme.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                nomeFilme.setText(rs.getString(2));
                cbGenero.setSelectedItem(rs.getString(6));
                // Abaixo aqui capturo a opções no ComboBox;
                datCadastro.setText(rs.getString(3));
                quantidadeFilme.setText(rs.getString(4));
                cbClass.setSelectedItem(rs.getString(5));
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado");
                // Caso haja informações no campo, serão limpas.
                nomeFilme.setText(null);
                cbGenero.setSelectedItem(null);
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
                + "(nom_dvd, genero_filme, qtd_cadastrada, dat_cadastro, censu_dvd)"
                + "VALUES(?,?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nomeFilme.getText());
            pst.setString(2, cbGenero.getSelectedItem().toString());
            pst.setString(3, quantidadeFilme.getText());
            pst.setString(4, datCadastro.getText());
            pst.setString(5, cbClass.getSelectedItem().toString());

            /*
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nomeFilme.getText());
            pst.setInt(2, obterGeneroDescr(cbGenero.getSelectedItem().toString()));
            pst.setString(3, datCadastro.getText());
            pst.setString(4, quantidadeFilme.getText());
            pst.setString(5, cbClass.getSelectedItem().toString());
             */
            // Validação dos campos;
            if (cbClass.getSelectedItem().toString().isEmpty() || (nomeFilme.getText().isEmpty()) || quantidadeFilme.getText().isEmpty()) {
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
                JOptionPane.showMessageDialog(null, "Filme excluido com sucesso");
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
        String sql;
        sql = "update dvd_titulo set nom_dvd=?, qtd_cadastrada=?, censu_dvd=?, genero_filme=? where cod_dvd =?";
        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(1, nomeFilme.getText());
            pst.setString(2, quantidadeFilme.getText());
            pst.setString(3, cbClass.getSelectedItem().toString());
            pst.setString(4, cbGenero.getSelectedItem().toString());
            pst.setString(5, codFilme.getText());

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

    // Método para pesquisa avançada
    private void pesquisarCliente() {
        String sql = "select * from dvd_titulo where nom_dvd like ? ";
        try {
            pst = conexao.prepareStatement(sql);
            // passando o conteúdo da caixa de pesquisa nomeFilme para o ? 
            // Atenção ao % - Continuação do comando Sql
            pst.setString(1, campPesquisa.getText() + "%");
            rs = pst.executeQuery();
            // A linha usa a biblioteca rs2Xml.jar para preencher a tabela.
            tbFilmes.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void setarCampo() {
        int setar = tbFilmes.getSelectedRow();
        
        nomeFilme.setText(tbFilmes.getModel().getValueAt(setar, 1).toString());
        datCadastro.setText(tbFilmes.getModel().getValueAt(setar, 2).toString());
        cbClass.setSelectedItem((tbFilmes.getModel().getValueAt(setar, 4).toString()));
        cbGenero.setSelectedItem((tbFilmes.getModel().getValueAt(setar, 5).toString()));
        quantidadeFilme.setText(tbFilmes.getModel().getValueAt(setar, 3).toString());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbClass = new javax.swing.JComboBox<>();
        codFilme = new javax.swing.JTextField();
        datCadastro = new javax.swing.JTextField();
        quantidadeFilme = new javax.swing.JTextField();
        campPesquisa = new javax.swing.JTextField();
        btcCadFil = new javax.swing.JButton();
        btnExcluiFil = new javax.swing.JButton();
        btnConFil = new javax.swing.JButton();
        btnAlteFil = new javax.swing.JButton();
        cbGenero = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbFilmes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        nomeFilme = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

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

        jLabel3.setText("Gênero");

        jLabel4.setText("Dat. Cadastro");

        jLabel5.setText("Quantidade");

        jLabel6.setText("Classificação");

        cbClass.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Livre", "10 Anos", "12 Anos", "14 Anos", "16 Anos", "18 Anos" }));

        datCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datCadastroActionPerformed(evt);
            }
        });

        campPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campPesquisaActionPerformed(evt);
            }
        });
        campPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campPesquisaKeyReleased(evt);
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

        tbFilmes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Título 5", "Título 6"
            }
        ));
        tbFilmes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFilmesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbFilmes);

        jLabel2.setText("Título");

        jLabel7.setText("Pesquisar por Título");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(codFilme, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(campPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btcCadFil)
                            .addComponent(btnConFil)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(64, 64, 64)
                                .addComponent(nomeFilme, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(datCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(10, 10, 10)
                                .addComponent(cbClass, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(quantidadeFilme, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAlteFil, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExcluiFil, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(btcCadFil)
                        .addGap(66, 66, 66))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConFil)
                            .addComponent(codFilme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAlteFil)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(nomeFilme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(quantidadeFilme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(cbClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnExcluiFil)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(datCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(379, 379, 379))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {campPesquisa, cbClass, cbGenero, codFilme, datCadastro, jLabel1, jLabel3, jLabel4, jLabel5, jLabel6, quantidadeFilme});

        setBounds(0, 0, 739, 475);
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

    private void campPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campPesquisaKeyReleased
        // O evento é dó tipo " Enquanto for digitando faça em tempo real "
        pesquisarCliente();
    }//GEN-LAST:event_campPesquisaKeyReleased

    private void campPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campPesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campPesquisaActionPerformed

    private void tbFilmesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFilmesMouseClicked
        // Aqui ao clicar na tabela os campos são automaticamente colocados.
        setarCampo();
    }//GEN-LAST:event_tbFilmesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btcCadFil;
    private javax.swing.JButton btnAlteFil;
    private javax.swing.JButton btnConFil;
    private javax.swing.JButton btnExcluiFil;
    private javax.swing.JTextField campPesquisa;
    private javax.swing.JComboBox<String> cbClass;
    protected javax.swing.JComboBox<String> cbGenero;
    private javax.swing.JTextField codFilme;
    private javax.swing.JTextField datCadastro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField nomeFilme;
    private javax.swing.JTextField quantidadeFilme;
    private javax.swing.JTable tbFilmes;
    // End of variables declaration//GEN-END:variables

    private void setExtendedState(int MAXIMIZED_BOTH) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
