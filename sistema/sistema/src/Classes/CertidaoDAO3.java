/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author 5069597
 */
public class CertidaoDAO3 {
    Connection con = new Conexao().getConnection();
    
    public List<Assunto> buscaCertidao(){
        List<Assunto> assuntos = new ArrayList<Assunto>();
        try {
            String sql = "select * from assunto";
            PreparedStatement stmt = con.prepareStatement(sql);
            //stmt.setString(1, "1");
            
            //executando o select
            ResultSet rs = stmt.executeQuery();
            
            
            
            while (rs.next()){
                Assunto assunto = new Assunto();
                assunto.setCodigo(Integer.parseInt(rs.getString("codigo")));
                assunto.setNome(rs.getString("nome"));
                assuntos.add(assunto);
            }
            
            rs.close();
            stmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(CertidaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return assuntos;
    }
    
    public List<Assunto> buscaHibernate(){
        List<Assunto> assuntos = new ArrayList<Assunto>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Assunto.class);
        assuntos = criteria.list();
        if(assuntos.size() == 0){
            JOptionPane.showMessageDialog(null, "Não encontrou nada");
        }
        return assuntos;
    }
    
}
