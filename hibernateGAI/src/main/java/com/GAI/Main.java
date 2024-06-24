/* MySQLPass = qwerty2002*/
package com.GAI;

import javax.persistence.*;
import java.io.IOException;
import java.util.List;
import java.util.jar.JarOutputStream;

public class Main {
    private final static String PERSISTENCE_UNIT_NAME = "GAI_persistence";
    public static void main(String[] args) throws MyException, IOException {
        new GUI();
    }
    public static int insertOwnerInTheBD(String name, String surname, String birthday){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Owner owner = new Owner(name,surname,birthday);
        try {
            em.persist(owner);
            em.getTransaction().commit();
        }catch (PersistenceException er){
            GUI.showMessage("Неравильно введена дата");
        }
        System.out.println("Owner id is - " + owner.getOid());
        try {
            GUI.model.addData(new String[]{String.valueOf(owner.getOid()),owner.getName(), owner.getSurname(), owner.getBirthday()});
            GUI.showMessage("Данные добавились в форму!");
        }catch (Exception e){
            GUI.showMessage(e.toString()+'\n'+"Ошибка добавления данных в форму.");
        }
        return owner.getOid();
    }
    public static void addCarToOwner(String plate,String brand, String model, int year, String inspDate, int id) throws MyException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();


        try{
            em.getTransaction().begin();



            Owner owner = em.find(Owner.class, id);

            Car car = new Car(plate, brand, model, year, inspDate, owner);

            em.persist(car);

            em.getTransaction().commit();
        }catch (IllegalArgumentException e){
            throw new MyException("Владельца с таким id не существует.");
        }
    }
    public static void addViolToOwner(String article,String date, int id) throws MyException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();


        try{
            em.getTransaction().begin();

            Owner owner = em.find(Owner.class, id);

            Violation viol = new Violation(article,date,owner);

            em.persist(viol);

            em.getTransaction().commit();
        }catch (IllegalArgumentException e){
            throw new MyException("Владельца с таким id не существует.");
        }
    }
    public static void deleteOwnerFromBdById(int id) throws MyException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();

            Owner owner = em.find(Owner.class, id);
            em.remove(owner);

            em.getTransaction().commit();
        }catch (IllegalArgumentException e){
            throw new MyException("Владельца с таким id не существует.");
        }
    }
    public static void deleteCarFromDbById(int id) throws MyException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();

            Car car = em.find(Car.class, id);
            em.remove(car);

            em.getTransaction().commit();
        }catch (IllegalArgumentException e){
            throw new MyException("Машины с таким id не существует.");
        }
    }
    public static void deleteViolFromDbById(int id) throws MyException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();

            Violation viol = em.find(Violation.class, id);
            em.remove(viol);

            em.getTransaction().commit();
        }catch (IllegalArgumentException e){
            throw new MyException("Нарушения с таким id не существует.");
        }
    }
    public static List getAllCars(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        return em.createQuery("SELECT c FROM Car c where cid > 0").getResultList();
    }
    public static List getAllViols(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        return em.createQuery("SELECT v FROM Violation v where vid > 0").getResultList();
    }
    public static List<Car> getCarsByOid(int ownerId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        Owner owner = em.find(Owner.class, ownerId);
        List<Car> cars;

        if(owner!=null){
            cars = em.createQuery("SELECT c FROM Car c where oid = "+ownerId).getResultList();
        }else{
            cars = null;
        }

        return cars;
    }
    public static List<Violation> getViolsByOid(int ownerId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        Owner owner = em.find(Owner.class,ownerId);
        List<Violation> viols;

        if(owner != null){
            viols = em.createQuery("SELECT v FROM Violation v where oid = "+ownerId).getResultList();
        }else{
            viols = null;
        }

        return viols;
    }
    public static void deleteAllFromBD(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();



        List<Owner> ownerList = em.createQuery("SELECT o FROM Owner o where oid > 0").getResultList();
        if(ownerList != null){
            em.getTransaction().begin();

            for (Owner owner : ownerList) {
                em.remove(owner);
            }

            em.getTransaction().commit();
        }
    }
    public static void changeDataInDB (int id, String name, String surname, String birthday) throws MyException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();

            Owner owner = em.find(Owner.class, id);
            owner.setName(name);
            owner.setSurname(surname);
            owner.setBirthday(birthday);

            em.getTransaction().commit();

        }catch (IllegalArgumentException e){
            throw new MyException("Владельца с таким id не существует.");
        }
    }
    public static void changeCarInfo (int id, String plate, String brand, String model, String year, String date) throws MyException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();

            Car car = em.find(Car.class, id);
            car.setPlate(plate);
            car.setBrand(brand);
            car.setModel(model);
            car.setYear(Integer.parseInt(year));
            car.setInspectionDate(date);

            em.getTransaction().commit();

        }catch (IllegalArgumentException e){
            throw new MyException("Владельца с таким id не существует.");
        }
    }
    public static void changeViolInfo (int id, String articleNum, String date) throws MyException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();

            Violation viol = em.find(Violation.class, id);
            viol.setArticleNum(articleNum);
            viol.setDate(date);

            em.getTransaction().commit();

        }catch (IllegalArgumentException e){
            throw new MyException("Владельца с таким id не существует.");
        }
    }
    public static List getAllOwners(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        return em.createQuery("SELECT o FROM Owner o where oid > 0").getResultList();
    }
    public static Object getCarByPlate(String plate){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        try {
            Car car = (Car) em.createQuery("SELECT c FROM Car c WHERE plate = :plateParam")
                    .setParameter("plateParam", plate)
                    .getSingleResult();
            return car;
        }catch (NoResultException e1){GUI.showMessage("Машина с таким номерным знаком не соодежиться в базе");}

        return null;

    }
    public static String[][] getAllOwnersInfo(){
        List<Owner> ownerList = getAllOwners();
        if(ownerList != null) {
            String[][] result = new String[ownerList.size()][4];
            for (int i = 0; i < ownerList.size(); i++) {
                result[i][0] = String.valueOf(ownerList.get(i).getOid());
                result[i][1] = ownerList.get(i).getName();
                result[i][2] = ownerList.get(i).getSurname();
                result[i][3] = ownerList.get(i).getBirthday();
            }
            return result;
        }
        return null;
    }
    public static String[] getAllOwnersInfoForPDF(){
        List<Owner> ownerList = getAllOwners();
        if(ownerList != null) {
            String[] result = new String[ownerList.size()];
            for (int i = 0; i < ownerList.size(); i++) {
                result[i] = i+1+". "+ownerList.get(i).getFullInfo();
            }

            return result;
        }
        return null;
    }
    private static boolean monthCheck(int fm, int sm){
        if (fm > 12 || fm < 1){
            GUI.showMessage("Неправильно введен первый месяц.");
            return false;
        } else if (sm > 12 || sm < 1) {
            GUI.showMessage("Неправильно введен второй месяц.");
            return false;
        }else if (fm > sm){
            GUI.showMessage("Первый месяц должен быть меньше второго.");
            return false;
        }
        return true;
    }
    public static String[] getViolsInfoForPDF(int id, int firstMonth, int secondMoth){
        int j = 1;
        if(monthCheck(firstMonth,secondMoth)){
            List<Violation> viols = getViolsByOid(id);

            if(viols != null ) {
                if(!viols.isEmpty()){
                    String[] result = new String[viols.size()];
                    for (int i = 0; i < viols.size(); i++) {
                        if(Integer.parseInt(viols.get(i).getViolMonth())>=firstMonth && Integer.parseInt(viols.get(i).getViolMonth())<=secondMoth){
                            result[i] = j+". "+viols.get(i).getViolInfo();
                            j++;
                        }
                    }
                    return result;
                }
                else{GUI.showMessage("У данного владельца нет нарушений!");}
            }else {GUI.showMessage("Владельца с таким id не существует.");}
        }
        return null;
    }
    public static String findOwnerByPlate(String plate) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        Car car = (Car) getCarByPlate(plate);
        if (car != null) {
            Owner owner = em.find(Owner.class, car.getOid());

            return owner.getFullInfo();
        }
        return null;
    }
    public static String getInspDate(String plate){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        Car car = (Car) getCarByPlate(plate);
        if(car != null) {
            return car.getInspectionDate();
        }
        return null;
    }
    public static String[] getOwnerViols(String plate){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        Car car = (Car) getCarByPlate(plate);

        if(car != null) {
            Owner owner = em.find(Owner.class, car.getOid());

            List<Violation> ownerViols = em.createQuery("SELECT v FROM Violation v WHERE oid = :oidParam")
                    .setParameter("oidParam", owner.getOid())
                    .getResultList();
            String[] res = new String[ownerViols.size()];

            for (int i = 0; i < ownerViols.size(); i++) {
                res[i] = ownerViols.get(i).getViolInfo();
            }
            return res;
        }
        return null;
    }
    public static StringBuilder getPlatesById(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = emf.createEntityManager();

        StringBuilder res = new StringBuilder();

        List<Car> cars = em.createQuery("SELECT c FROM Car c WHERE oid = :oidParam")
                            .setParameter("oidParam", id)
                            .getResultList();
        if(cars!=null){
            for (int i = 0; i <cars.size() ; i++) {
                res.append(i+1+". "+cars.get(i).getPlate()+'\n');
            }
        }
        return res;
    }

}

