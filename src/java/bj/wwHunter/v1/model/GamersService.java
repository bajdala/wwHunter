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
public class GamersService implements GamersDAO{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*GamersService obj = new GamersService();
         * 
         * Gamers gamer = new Gamers();
         * gamer.setNickName("root");
         * gamer.setLastName("Байдала");
         * gamer.setFirstName("Юрий");
         * gamer.setSex("MALE");
         * gamer.setBirthday("21.08.1976");
         * gamer.setWhiteZones("мои штаны");
         * gamer.setPhone("0682353535");
         * gamer.setEmail("qwerty@gmail.com");
         * gamer.setPhotoPath("d:/qwwqew.jpg;d:/qwwqew.jpg");
         * gamer.setLifeCode(new LCGenerator().generateLC());
         * gamer.setDateTimeImmunitet(new Date(76, 7, 21));
         * 
         * ControllerImpl.getInstance().saveNewGamer(gamer);*/
        
        //Gamers gmr = new Gamers("admin", "Петров", "Иван", GamerSexState.MALE, new Date(), "..", "дом, работа", "068-235-11-94", "jura.abc@rambler.ru", "a12d0z32", new Date(), 12, GamerState.ACTIVE);
        //obj.insert(gmr);
        //System.out.println(obj.selectByLifecode("a12d0z32"));
        //System.out.println(obj.selectByID(1));
        //System.out.println(obj.selectByIDAndLC(1, "a12d0z32"));
        /*gmr = obj.selectByID(1);
        gmr.setSex(GamerSexState.FEMALE);
        obj.update(gmr);*/
        //System.out.println(obj.selectAll());
        //System.out.println(obj.selectByIDVictim(12));
        //obj.deleteAll();
        //System.out.println(obj.selectByIDVictim(12));
    }

    public void insert(Gamers gamer) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        session.save(gamer);
        
        tr.commit();
        session.close();
    }

    public Gamers selectByLifecode(String lifecode) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        SQLQuery query = session.createSQLQuery("SELECT * FROM gamers WHERE lifeCode = '" + lifecode + "';");
        query.addEntity(Gamers.class);
        
        Gamers out = null;
        
        Iterator<Gamers> e = query.list().iterator();
        if(e.hasNext()){
            out = e.next();
        }
                
        tr.commit();
        session.close();
        
        return out;
    }

    public Gamers selectByID(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        SQLQuery query = session.createSQLQuery("SELECT * FROM gamers WHERE id = " + id + ";");
        query.addEntity(Gamers.class);
        
        Gamers out = null;
        
        Iterator<Gamers> e = query.list().iterator();
        if(e.hasNext()){
            out = e.next();
        }
        
        tr.commit();
        session.close();
        return out;
    }

    public Gamers selectByIDAndLC(int id, String lifecode) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        SQLQuery query = session.createSQLQuery("SELECT * FROM gamers WHERE id = " + id + " AND lifeCode = '" + lifecode + "';");
        query.addEntity(Gamers.class);
        
        Gamers out = null;
        
        Iterator<Gamers> e = query.list().iterator();
        if(e.hasNext()){
            out = e.next();
        }
        
        tr.commit();
        session.close();
        return out;
    }

    public void update(Gamers gamer) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        session.update(gamer);
        
        tr.commit();
        session.close();
    }

    public void deleteAll() {
        
        List<Gamers> list = selectAll();
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        for(Gamers gmr: list){
            session.delete(gmr);
        }
        
        tr.commit();
        session.close();
    }

    public Gamers selectByIDVictim(int idVictim) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        SQLQuery query = session.createSQLQuery("SELECT * FROM gamers WHERE idVictim = " + idVictim + ";");
        query.addEntity(Gamers.class);
        
        Gamers out = null;
        
        Iterator<Gamers> e = query.list().iterator();
        if(e.hasNext()){
            out = e.next();
        }
        
        tr.commit();
        session.close();
        return out;
    }

    public List<Gamers> selectAll() {
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        SQLQuery query = session.createSQLQuery("SELECT * FROM gamers;");
        query.addEntity(Gamers.class);
        
        List<Gamers> gamers = query.list();
        
        tr.commit();
        session.close();
        
        return gamers;
    }
    
    public List<Gamers> selectAllActive() {
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        SQLQuery query = session.createSQLQuery("SELECT * FROM gamers WHERE status != 'DISQUALIFIED';");
        query.addEntity(Gamers.class);
        
        List<Gamers> gamers = query.list();
        
        tr.commit();
        session.close();
        
        return gamers;
    }

    public Gamers getGamer(String nick, String pass) {
        
        Gamers gmr = selectByLifecode(pass);
        if (gmr != null && gmr.getNickName().equals(nick)) {
            return gmr;
        } else {
            return null;
        }
        
    }

    public boolean checkLC(String LC) {
        Gamers gmr = selectByLifecode(LC);
        if (gmr != null) {
            return true;
        } else {
            return false;
        }
    }

    
}
