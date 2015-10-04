package bj.wwHunter.v1.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * бин для обработки формы редактирования личных данных игрока
 * @author bajdala
 */
public class UserPageForm {

    String id;
    String nick;
    String firstName;
    String lastName;
    String sex;
    String birthday;
    String phone;
    String email;
    String lifeCode;
    String immDateTime;
    String idVictim;
    String status;
    String whiteZones;

    public UserPageForm(String id, String nick, String firstName, String lastName, String sex, String birthday, String phone, String email, String lifeCode, String immDateTime, String idVictim, String status, String whiteZones) {
        this.id = id;
        this.nick = nick;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.lifeCode = lifeCode;
        this.immDateTime = immDateTime;
        this.idVictim = idVictim;
        this.status = status;
        this.whiteZones = whiteZones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLifeCode() {
        return lifeCode;
    }

    public void setLifeCode(String lifeCode) {
        this.lifeCode = lifeCode;
    }

    public String getImmDateTime() {
        return immDateTime;
    }

    public void setImmDateTime(String immDateTime) {
        this.immDateTime = immDateTime;
    }

    public String getIdVictim() {
        return idVictim;
    }

    public void setIdVictim(String idVictim) {
        this.idVictim = idVictim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWhiteZones() {
        return whiteZones;
    }

    public void setWhiteZones(String whiteZones) {
        this.whiteZones = whiteZones;
    }
    
    
    
    
    
    
    public static void main(String[] args) throws ParseException {
        UserPageForm f = new UserPageForm("1", "admin", "admin", "admin", "sex", "qwesdf", "asdfasd", "asdfasdf", "qdsasffd", "1976-08-21 00:00:00", "zxcvzxcv", "acvzxc", "zscvzxc");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        System.out.println(df.parse(f.getImmDateTime()));
    }
}
