/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.wwHunter.v1.model;

import java.util.List;

/**
 *
 * @author user
 */
public interface MessagesDAO {
    public void insert(Messages message);
    public void update(Messages message);
    public List<Messages> selectByIDSender(int idSender);
    public List<Messages> selectByIDGamer(int idGamer);
    public Messages selectByID(int id);
    public void deleteAll();
    public List<Messages> selectAll();
}
