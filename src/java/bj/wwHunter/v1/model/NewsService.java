/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.wwHunter.v1.model;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author user
 */
public class NewsService implements NewsDAO{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        News nw = new News("Строится черновой вариант портала", NewState.ACTIVE, new Date());
        NewsService obj = new NewsService();
        obj.insert(nw);
    }

    public List<News> selectAll() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        SQLQuery query = session.createSQLQuery("SELECT * FROM news;");
        query.addEntity(News.class);
        
        List<News> list = query.list();
        
        tr.commit();
        session.close();
        
        return list;
    }

    public List<News> selectActive() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        SQLQuery query = session.createSQLQuery("SELECT * FROM news WHERE statusNews = 'ACTIVE';");
        query.addEntity(News.class);
        List<News> list = query.list();
        tr.commit();
        session.close();
        
        return list;
    }

    public News selectByID(int id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        SQLQuery query = session.createSQLQuery("SELECT * FROM news WHERE id = " + id + ";");
        query.addEntity(News.class);
        
        News out = null;
        
        Iterator<News> e = query.list().iterator();
        if(e.hasNext()){
            out = e.next();
        }
        
        tr.commit();
        session.close();
        
        return out;
    }

    public void insert(News news) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        session.save(news);
        
        tr.commit();
        session.close();
    }

    public void update(News news) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        session.update(news);
        
        tr.commit();
        session.close();
    }

    public void deleteAll() {
        List<News> list = selectAll();
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        
        for(News nw: list){
            session.delete(nw);
        }
        
        tr.commit();
        session.close();
    }

    public void updateNewStatus(int id, String state) {
        News nw = selectByID(id);
        if (!nw.getStatusNews().equals(state)) {
            nw.setStatusNews(state);
            update(nw);
        }
    }
}
