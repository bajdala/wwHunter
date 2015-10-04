package bj.wwHunter.v1.controller;

import bj.wwHunter.v1.model.Model;
import bj.wwHunter.v1.model.ModelImpl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * генератор уникального жизненного кода
 * @author bajdala
 */
public class LCGenerator {

    /**
     * @param args the command line arguments
     */
    Random rand = new Random();
    private static final char[] charTable = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    
    public static void main(String[] args) {
        
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date("27 mar 2015 12:25:14")));
    }
    
    public String generateLC(){
        StringBuilder LC;
        
        do{
            LC = new StringBuilder();
            LC.append(charTable[rand.nextInt(charTable.length)])
                    .append(charTable[rand.nextInt(charTable.length)])
                    .append(charTable[rand.nextInt(charTable.length)])
                    .append(charTable[rand.nextInt(charTable.length)])
                    .append(charTable[rand.nextInt(charTable.length)])
                    .append(charTable[rand.nextInt(charTable.length)]);
        }while(checkLC(LC.toString()));
        
        return LC.toString();
    }
    
    private boolean checkLC(String LC){
        
        Model model = new ModelImpl();
        
        return model.getGamersDAO().checkLC(LC);
        
    }
    
}
