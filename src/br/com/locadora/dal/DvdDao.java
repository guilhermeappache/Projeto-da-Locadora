/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.locadora.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author guilh
 */
public class DvdDao {

    public ResultSet  consultar(String codDvd) {
        
        Connection conexao = ModuloConexao.conector();
        PreparedStatement pst = null;
        ResultSet rs = null;
        //String sql = "select * from dvd_titulo where cod_dvd=?";
        String sql = "SELECT dvd.cod_dvd, dvd.nom_dvd, genero.descri_genero, dvd.dat_cadastro, dvd.qtd_cadastrada, dvd.censu_dvd   \n"
                + " FROM dvd_titulo dvd  \n"
                + " right JOIN genero_dvd genero ON dvd.cod_genero = genero.cod_genero \n"
                + " where cod_dvd=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, codDvd) ;
            rs = pst.executeQuery();
            if (rs.next()) {
                return rs ;
            }
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
             return null;
    }
}
