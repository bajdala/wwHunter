/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.wwHunter.v1.model;

import java.util.Iterator;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author user
 */
public class MessagesService implements MessagesDAO{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    public void insert(Messages message) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        session.save(message);
        
        tr.commit();
        session.close();
    }

    public void update(Messages message) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        session.update(message);
        
        tr.commit();
        session.close();
    }

    public List<Messages> selectByIDSender(int idSender) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        SQLQuery query = session.createSQLQuery("SELECT * FROM messages WHERE idSender = " + idSender + ";");
        query.addEntity(Messages.class);
        List<Messages> list = query.list();
        tr.commit();
        session.close();
        
        return list;
    }

    public List<Messages> selectByIDGamer(int idGamer) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        SQLQuery query = session.createSQLQuery("SELECT * FROM messages WHERE idGamer = " + idGamer + " OR idSender = " + idGamer + " ORDER BY dateTimeMessage;");
        query.addEntity(Messages.class);
        List<Messages> list = query.list();
        tr.commit();
        session.close();
        
        return list;
    }

    public Messages selectByID(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        SQLQuery query = session.createSQLQuery("SELECT * FROM messages WHERE id = " + id + ";");
        query.addEntity(Messages.class);
        
        Messages out = null;
        
        Iterator<Messages> e = query.list().iterator();
        if(e.hasNext()){
            out = e.next();
        }
        
        tr.commit();
        session.close();
        
        return out;
    }

    public void deleteAll() {
        List<Messages> list = selectAll();
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        for(Messages msg: list){
            session.delete(msg);
        }
        
        tr.commit();
        session.close();
    }

    public List<Messages> selectAll() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        SQLQuery query = session.createSQLQuery("SELECT * FROM messages;");
        query.addEntity(Messages.class);
        
        List<Messages> list = query.list();
        
        tr.commit();
        session.close();
        
        return list;
    }
    
}
