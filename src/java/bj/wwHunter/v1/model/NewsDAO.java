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
public interface NewsDAO {
    public List<News> selectAll();
    public List<News> selectActive();
    public News selectByID(int id);
    public void insert(News news);
    public void update(News news);
    public void deleteAll();
    public void updateNewStatus(int id, String state);
}
