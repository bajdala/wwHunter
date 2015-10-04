/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.wwHunter.v1.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author user
 */
public class GameEventsService implements GameEventsDAO{

    
    
    
    public static void main(String[] args) {
        
        //GameEventsDAO obj = new GameEventsService();
        //Gameevents ge = new Gameevents(new Date(), 1, 2, EventState.COMPLETE);
        //obj.deleteAll();
        //obj.insert(ge);
        //System.out.println(obj.test(obj.selectAll()));
        
    }

    public String test(List<Gameevents> list){
        String out = "";
        for(Gameevents ge: list){
            out = out + ge.getLabel() + "-" + ge.getDateTimeEvent() + "; ";
        }
                
        return out;
    }
    
    public List<Gameevents> selectAll() {
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        SQLQuery query = session.createSQLQuery("SELECT * FROM gameevents;");
        query.addEntity(Gameevents.class);
        List<Gameevents> list = query.list();
        tr.commit();
        session.close();
        
        return list;
        
    }

    public void insert(Gameevents gEvt) {
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        session.save(gEvt);
        
        tr.commit();
        session.close();
        
    }

    public void deleteAll() {
        
        List<Gameevents> list = selectAll();
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        for(Gameevents ge: list){
            session.delete(ge);
        }
        
        
        tr.commit();
        session.close();
    }

    public List<Gameevents> selectByIDHunter(int idHunter) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        SQLQuery query = session.createSQLQuery("SELECT * FROM gameevents WHERE idHunter=" + idHunter + ";");
        query.addEntity(Gameevents.class);
        List<Gameevents> list = query.list();
        tr.commit();
        session.close();
        
        return list;
    }

    public List<Gameevents> selectByIDVictim(int idVictim) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        SQLQuery query = session.createSQLQuery("SELECT * FROM gameevents WHERE idVictim=" + idVictim + ";");
        query.addEntity(Gameevents.class);
        List<Gameevents> list = query.list();
        tr.commit();
        session.close();
        
        return list;
    }

    public Date getActivity(Gamers gmr) {
        Date out = new Date(100, 0, 1);
        if (gmr.getStts() == GamerState.ACTIVE) {
            List<Gameevents> evnts = selectByIDHunter(gmr.getId());

            for (Gameevents evnt : evnts) {
                if (out.before(evnt.getDateTimeEvent())) {
                    out = evnt.getDateTimeEvent();
                }
            }

            List<Gameevents> evnts2 = selectByIDVictim(gmr.getId());

            for (Gameevents evnt : evnts2) {
                if (out.before(evnt.getDateTimeEvent()) && (evnt.getLbl() == EventState.DEFENSE || evnt.getLbl() == EventState.PENALTY)) {
                    out = evnt.getDateTimeEvent();
                }
            }

        }
        return out;
    }
}
