/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.wwHunter.v1.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author user
 */
public interface GameEventsDAO {
    public List<Gameevents> selectAll();
    public List<Gameevents> selectByIDHunter(int idHunter);
    public List<Gameevents> selectByIDVictim(int idVictim);
    public void insert(Gameevents gEvt);
    public void deleteAll();
    public Date getActivity(Gamers gmr);
}
