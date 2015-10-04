/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.wwHunter.v1.model;

/**
 *
 * @author user
 */
public interface Model {
    public GameEventsDAO getGameEventsDAO();
    public GamersDAO getGamersDAO();
    public MessagesDAO getMessagesDAO();
    public NewsDAO getNewsDAO();
    public PenaltysDAO getPenaltysDAO();
}
