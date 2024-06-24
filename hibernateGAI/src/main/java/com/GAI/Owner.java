package com.GAI;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "OWNERS")
public class Owner {
    private int oid;
    private String name;
    private String surname;
    private Date birthday;
    Owner(String name, String surname, String birthday){
        this.name = name;
        this.surname = surname;
        try{
            this.birthday = new SimpleDateFormat("dd.MM.yyyy").parse(birthday);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public Owner(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOWNERS")
    public int getOid(){
        return this.oid;
    }
    public void setOid(int oid){
        this.oid=oid;
    }

    @Column(name = "Name")
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    @Column(name = "Surname")
    public String getSurname(){
        return this.surname;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public String getBirthday(){
        SimpleDateFormat formatForInspectionDate = new SimpleDateFormat("dd.MM.yyyy  ");
        return formatForInspectionDate.format(this.birthday);
    }

    @Column(name = "Birthday")
    public void setBirthday(String birthday){
        try{
            this.birthday = new SimpleDateFormat("dd.MM.yyyy").parse(birthday);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Transient
    public String getFullInfo(){
        return this.surname+" "+this.name+" "+getBirthday();
    }

}

