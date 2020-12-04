/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Compromisso;
import entidades.Contato;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexao;

/**
 *
 * @author User
 */
public class CompromissoDao {

    Conexao con = new Conexao();

    public boolean salvar(Compromisso ct) {
        String sql = "insert into compromisso (contato, local) values (?,?)";
        try {
            PreparedStatement stm = con.getConexao().prepareStatement(sql);
            stm.setString(2, ct.getLocal());
            stm.setInt(1, ct.getContato().getId());
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ContatoDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
            con.fecharConexao();
        }
        return true;
    }

    public List<Compromisso> listar() {
        List<Compromisso> lista = new ArrayList<>();
        try {
            PreparedStatement stm
                    = con.getConexao()
                            .prepareStatement("select * from compromisso");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ContatoDao cdao = new ContatoDao();               
                
                lista.add(new Compromisso(
                        rs.getInt("id"),
                        cdao.listar(rs.getInt("contato")),
                        rs.getString("local")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContatoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.fecharConexao();
        }
        return lista;
    }

}
