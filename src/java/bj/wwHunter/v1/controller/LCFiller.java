package bj.wwHunter.v1.controller;

import bj.wwHunter.v1.model.GamerState;
import bj.wwHunter.v1.model.Gamers;
import bj.wwHunter.v1.model.Model;
import bj.wwHunter.v1.model.ModelImpl;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * заполнение поля "жизненный код" по активным игрокам
 * @author bajdala
 */
public class LCFiller {

    List<Gamers> gamers;
    
    public List<Gamers> fillLC(List<Gamers> gamers){
        
        List<Gamers> out = new LinkedList<Gamers>();
        this.gamers = gamers;
        Random r = new Random();
        Gamers g = gamers.remove(0);
                
        Gamers g2 = gamers.remove(r.nextInt(gamers.size()));
        g.setIdVictim(g2.getId());
        g.setStatus(GamerState.ACTIVE);
        
        while(gamers.size() > 0){
            
            Gamers g3 = gamers.remove(r.nextInt(gamers.size()));
            g2.setIdVictim(g3.getId());
            g2.setStatus(GamerState.ACTIVE);
            out.add(g2);
            g2 = g3;
        
        }
        
        g2.setIdVictim(g.getId());
        g2.setStatus(GamerState.ACTIVE);
        out.add(g2);
        out.add(0,g);
        
        
        return out;        
    }
    
    
    
    public static void main(String[] args) {
        LCFiller filler = new LCFiller();
        List <Gamers> test = filler.fillLC(new ModelImpl().getGamersDAO().selectAll());
        for(Gamers g:test){
            System.out.println("" + g.getId() + ":" + g.getIdVictim());
        }
    }
}
