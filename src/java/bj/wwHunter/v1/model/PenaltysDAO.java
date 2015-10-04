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
public interface PenaltysDAO {
    public Penaltys selectActiveByIDGamer(int idGamer);
    public List<Penaltys> selectAll();
    public void insert(Penaltys penalty);
    public void update(Penaltys penalty);
    public void deleteAll();  
    public void addPenalty(Penaltys pnlt);
}
