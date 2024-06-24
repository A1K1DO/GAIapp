package com.GAI;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "CARS")
public class Car {
    private int cid;
    private int oid;
    private String plate;
    private String brand;
    private String model;
    private int year;
    private Date inspectionDate;


    Car(String plate, String brand, String model, int year, String inspectionDate, Owner owner){
        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.year = year;
        try{
            this.inspectionDate = new SimpleDateFormat("dd.MM.yyyy").parse(inspectionDate);
        }catch (Exception e){
            System.out.println(e);
        }
        this.oid = owner.getOid();
    }

    public Car() {

    }

    @Id
    @Column(name = "idCARS")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getCid(){
        return this.cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Column(name = "OWNERS_idOWNERS")
    public int getOid(){
        return this.oid;
    }
    public void setOid(int oid){
        this.oid = oid;
    }
    @Column(name = "Plate")
    public String getPlate(){
       return this.plate;
    }
    public void setPlate(String plate){
       this.plate=plate;
    }

    @Column(name = "Brand")
    public String getBrand(){
       return this.brand;
    }
    public void setBrand(String brand){
       this.brand=brand;
    }

    @Column(name = "Model")
    public String getModel(){
       return this.model;
    }
    public void setModel(String model){
       this.model=model;
    }

    @Column(name = "Year")
    public int getYear(){
        return this.year;
    }
    public void setYear(int year){
        this.year=year;
    }

    @Column(name = "InspectionDate")
    public String getInspectionDate(){
        SimpleDateFormat formatForInspectionDate = new SimpleDateFormat("dd.MM.yyyy  ");
        return formatForInspectionDate.format(this.inspectionDate);
    }
    public void setInspectionDate(String inspectionDate) {
        try{
            this.inspectionDate = new SimpleDateFormat("dd.MM.yyyy").parse(inspectionDate);
        }catch (Exception e){
            System.out.println(e);
        }
    }


    @Transient
    public String getCarInfo(){
        return getCid() +" "+ getPlate() +" "+ getBrand() +" "+ getModel() +" "+ getYear() +" "+ getInspectionDate();
    }

}
