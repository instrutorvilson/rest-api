/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
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
 * @author Vilson
 */
public class ContatoDao {

    Conexao con = new Conexao();

    public boolean salvar(Contato ct) {
        String sql = "insert into contato (nome, fone) values (?,?)";
        try {
            PreparedStatement stm = con.getConexao().prepareStatement(sql);
            stm.setString(1, ct.getNome());
            stm.setString(2, ct.getFone());
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ContatoDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
            con.fecharConexao();
        }
        return true;
    }

    public List<Contato> listar() {
        List<Contato> lista = new ArrayList<>();
        try {
            PreparedStatement stm
                    = con.getConexao()
                            .prepareStatement("select * from contato");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                lista.add(new Contato(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("fone")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContatoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.fecharConexao();
        }
        return lista;
    }

    public Contato listar(int id) {
        try {
            PreparedStatement stm
                    = con.getConexao()
                            .prepareStatement("select * from contato where id =" + id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new Contato(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("fone"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ContatoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.fecharConexao();
        }
        return null;
    }

    public boolean editar(Contato ct) {
        String sql = "update contato set";
        sql += " nome = ?, fone = ? ";
        sql += " where id = ? ";
        PreparedStatement stm;
        try {
            stm = con.getConexao().prepareStatement(sql);
            stm.setString(1, ct.getNome());
            stm.setString(2, ct.getFone());
            stm.setInt(4, ct.getId());
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ContatoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.fecharConexao();
        }
        return true;
    }

    public boolean excluir(Integer id) {
        String sql = "delete from contato ";
        sql += " where id = " + id;
        PreparedStatement stm;
        try {
            stm = con.getConexao().prepareStatement(sql);
            stm.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ContatoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.fecharConexao();
        }
        return false;
    }
}
