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
public interface GamersDAO {
    public void insert(Gamers gamer);
    public Gamers selectByLifecode(String lifecode);
    public Gamers selectByID(int id);
    public Gamers selectByIDAndLC(int id, String lifecode);
    public void update(Gamers gamer);
    public void deleteAll();
    public Gamers selectByIDVictim(int idVictim);
    public List<Gamers> selectAll();
    public List<Gamers> selectAllActive();
    public Gamers getGamer(String nick, String pass);
    public boolean checkLC(String LC);
}
