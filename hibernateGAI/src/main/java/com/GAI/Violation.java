package com.GAI;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "VIOLATIONS")
public class Violation {
    private int vid;
    private int oid;
    private String articleNum;
    private Date date;
    Violation(String articleNum, String date, Owner owner){
        this.articleNum = articleNum;
        try{
            this.date = new SimpleDateFormat("dd.MM.yyyy").parse(date);
        }catch (Exception e){
            System.out.println(e);
        }
        this.oid = owner.getOid();
    }
    public Violation(){

    }
    @Id
    @Column(name="idVIOLATIONS")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getVid(){
        return this.vid;
    }
    public void setVid(int vid){
        this.vid=vid;
    }
    @Column(name = "OWNERS_idOWNERS")
    public int getOid(){
        return this.oid;
    }
    public void setOid(int oid){
        this.oid = oid;
    }
    @Column(name = "ArticleNum")
    public String getArticleNum(){
        return this.articleNum;
    }
    public void setArticleNum(String articleNum){
        this.articleNum = articleNum;
    }

    @Column(name = "Date")
    public String getDate(){
        SimpleDateFormat formatForInspectionDate = new SimpleDateFormat("dd.MM.yyyy  ");
        return formatForInspectionDate.format(this.date);
    }
    public void setDate(String date){
        try{
            this.date = new SimpleDateFormat("dd.MM.yyyy").parse(date);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    @Transient
    public String getViolInfo(){
        return getVid() +"  "+ getArticleNum()+"  "+getDate();
    }

    @Transient
    public String getViolMonth(){
        String date = getDate();
        String[] data = date.split("\\.");

        String month = data[1];
        return month;
    }
}
