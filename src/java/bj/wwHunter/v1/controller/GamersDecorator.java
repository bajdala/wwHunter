package bj.wwHunter.v1.controller;

import bj.wwHunter.v1.model.GamerSexState;
import bj.wwHunter.v1.model.Gamers;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bajdala
 */
public class GamersDecorator {
    
    Gamers gamer;
    
    private String fullName;
    private String sex;
    private String photo1;
    private String photo2;
    private String whiteZones;
    private String immTime;
    private String firstName;
    private String lastName;
    

    public GamersDecorator(Gamers gamer) {
        this.gamer = gamer;
    }

    public Gamers getGamer() {
        return gamer;
    }

    public void setGamer(Gamers gamer) {
        this.gamer = gamer;
    }

    public String getFullName() {
        fullName = getFirstName() + " " + getLastName();
        return fullName;
    }

    public String getSex() {
        
        if(getGamer().getSx() == GamerSexState.MALE){
            sex = "мужской";
        }else{
            sex = "женский";
        }
        
        return sex;
    }

    public String getPhoto1() {
        
        photo1 = getGamer().getPhotoPath().split(";")[0];
        photo1 = photo1.substring(photo1.lastIndexOf("\\")+1);
        
        return photo1;
    }

    public String getPhoto2() {
        
        photo2 = getGamer().getPhotoPath().split(";")[1];
        photo2 = photo2.substring(photo2.lastIndexOf("\\")+1);
        
        return photo2;
    }

    public String getWhiteZones() {
        try {
            whiteZones = new String(getGamer().getWhiteZones().getBytes("latin1"),"cp1251");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(GamersDecorator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return whiteZones;
    }

    public String getImmTime() {
        
        immTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getGamer().getDateTimeImmunitet());
        
        return immTime;
    }

    public String getFirstName() {
        try {
            firstName = new String(getGamer().getFirstName().getBytes("latin1"),"cp1251");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(GamersDecorator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return firstName;
    }

    public String getLastName() {
        try {
            lastName = new String(getGamer().getLastName().getBytes("latin1"),"cp1251");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(GamersDecorator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lastName;
    }
    
    
    
}
