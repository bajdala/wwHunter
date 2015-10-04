/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.wwHunter.v1.model;

/**
 *
 * @author user
 */
public class ModelImpl implements Model{

    GamersDAO gamers = new GamersService();
    GameEventsDAO gEvents = new GameEventsService();
    MessagesDAO msgs = new MessagesService();
    NewsDAO news = new NewsService();
    PenaltysDAO penaltys = new PenaltysService();
    
    public GameEventsDAO getGameEventsDAO() {
        return gEvents;
    }

    public GamersDAO getGamersDAO() {
        return gamers;
    }

    public MessagesDAO getMessagesDAO() {
        return msgs;
    }

    public NewsDAO getNewsDAO() {
        return news;
    }

    public PenaltysDAO getPenaltysDAO() {
        return penaltys;
    }
    
}
