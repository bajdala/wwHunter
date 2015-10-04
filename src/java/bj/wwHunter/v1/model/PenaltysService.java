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
public class PenaltysService implements PenaltysDAO{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    public Penaltys selectActiveByIDGamer(int idGamer) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        SQLQuery query = session.createSQLQuery("SELECT * FROM penaltys WHERE idGamer = " + idGamer + " AND status = 'ACTIVE';");
        query.addEntity(Penaltys.class);
        
        Penaltys out = null;
        
        Iterator<Penaltys> e = query.list().iterator();
        if(e.hasNext()){
            out = e.next();
        }
        
        tr.commit();
        session.close();
        
        return out;
    }

    public List<Penaltys> selectAll() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        SQLQuery query = session.createSQLQuery("SELECT * FROM penaltys;");
        query.addEntity(Penaltys.class);
        
        List<Penaltys> list = query.list();
        
        tr.commit();
        session.close();
        
        return list;
    }

    public void insert(Penaltys penalty) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        session.save(penalty);
        
        tr.commit();
        session.close();
    }

    public void update(Penaltys penalty) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        session.update(penalty);
        
        tr.commit();
        session.close();
    }

    public void deleteAll() {
        List<Penaltys> list = selectAll();
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        for(Penaltys pn: list){
            session.delete(pn);
        }
        
        tr.commit();
        session.close();
    }

    public void addPenalty(Penaltys pnlt) {
        Penaltys old = selectActiveByIDGamer(pnlt.getIdGamer());
        if (old != null) {
            old.setStatus(PenaltyState.EXCELENT);
            update(old);
        }

        insert(pnlt);
    }
}
