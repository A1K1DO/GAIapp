package com.GAI;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.w3c.dom.*;
import org.apache.log4j.Logger;

import org.xml.sax.SAXException;
import org.apache.pdfbox.pdmodel.PDDocument;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.List;

import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * This is a main class which implements GUI of program complex
 */
public class GUI extends JFrame implements ActionListener {
    /**
     * <b>log</> - logger.
     */
    private static final Logger log = Logger.getLogger(GUI.class);
    /**
     * <b>id</b> - this is variable for keep the input value of user id.
     */
    private static int id;
    /**
     * <b>addBtn</b> - this is a button for add some information into the table.
     */
    private  JButton addBtn;
    /**
     * <b>pdfButton</b> - this is a button for create a pdf report about owner's violations in the curr period.
     */
    private  JButton pdfBtn;
    /**
     * <b>violBtn</b> - this is a button for get all owner's violations.
     */
    private  JButton violBtn;
    /**
     * <b>findPlateBtn</b> - this is a button for get all plates related to the owner.
     * */
    private JButton findPlateBtn;
    /**
     * <b>tiBtn</b> - this is a button for get the date of a technical inspiration of the car.
     */
    private  JButton tiBtn;
    /**
     * <b>findBtn</b> - this is a button for find an owner by a plate of its car.
     */
    private  JButton findBtn;
    /**
     * <b>changeBtn</b> - this is a button for change information in the table.
     */
    private  JButton changeBtn;
    /**
     * <b>delBtn</b> - this is a button for delete information from the table.
     */
    private  JButton delBtn;
    /**
     * These are all panels used in GUI.
     * <b>upButPanel</b> - this is a panel which contains upper buttons.
     */
    private JPanel upButPanel;
    /**
     * <b>downButPanel</b> - this is a panel which contains bottom buttons.
     */
    private JPanel downButPanel;
    /**
     * <b>tablePanel</b> - this is a panel which contains a table.
     */
    private JPanel tablePanel;
    /**
     * This is a table model.
     */
    public static GaiTableModel model;

    /**
     * This is a table object.
     */
    private JTable OWNERS;
    /**
     * This is a table with a scroll.
     */
    private JScrollPane scroll;

    /**
     * This is a constructor of the main class without any params.
     */
    GUI(){

        log.info("Создание экраннной формы");

        //Параметры окна
        this.setTitle("GAI-PC");//Название
        this.setSize(800,600);
        this.setLayout(null);
        this.setResizable(false);//Запрет масштабирования
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Окно закрывается при нажатии на крестик
        this.getContentPane().setBackground(new Color(84,119,159));//Цвет заднего фона

        //Создание кнопок на экранной форме
        findBtn = new JButton();
        findBtn.setIcon(new ImageIcon("src/main/resources/serch.png"));
        findBtn.setBackground(new Color(33,129,183));
        findBtn.setOpaque(true);
        findBtn.setFocusable(false);
        findBtn.addActionListener(this);//Слушатель на кнопку для реакции на нажатие
        findBtn.setBorder(BorderFactory.createLineBorder(Color.black));//Окантовка

        findPlateBtn = new JButton();
        findPlateBtn.setIcon(new ImageIcon("src/main/resources/search2.png"));
        findPlateBtn.setBackground(new Color(33,129,183));
        findPlateBtn.setOpaque(true);
        findPlateBtn.setFocusable(false);
        findPlateBtn.addActionListener(this);
        findPlateBtn.setBorder(BorderFactory.createLineBorder(Color.black));

        tiBtn = new JButton();
        tiBtn.setIcon(new ImageIcon("src/main/resources/look.png"));
        tiBtn.setBackground(new Color(33,129,183));
        tiBtn.setOpaque(true);
        tiBtn.setFocusable(false);
        tiBtn.addActionListener(this);
        tiBtn.setBorder(BorderFactory.createLineBorder(Color.black));

        violBtn = new JButton();
        violBtn.setIcon(new ImageIcon("src/main/resources/viol.png"));
        violBtn.setBackground(new Color(33,129,183));
        violBtn.setOpaque(true);
        violBtn.setFocusable(false);
        violBtn.addActionListener(this);
        violBtn.setBorder(BorderFactory.createLineBorder(Color.black));

        pdfBtn = new JButton();
        pdfBtn.setIcon(new ImageIcon("src/main/resources/pdf.png"));
        pdfBtn.setBackground(new Color(33,129,183));
        pdfBtn.setOpaque(true);
        pdfBtn.setFocusable(false);
        pdfBtn.addActionListener(this);
        pdfBtn.setBorder(BorderFactory.createLineBorder(Color.black));

        //Создание верхней панели для размещения кнопок
        upButPanel = new JPanel(new GridLayout(1,5,10,0));
        upButPanel.setBounds(14,14,260,41);
        upButPanel.setBackground(new Color(84,119,159));
        upButPanel.add(findBtn);
        upButPanel.add(tiBtn);
        upButPanel.add(violBtn);
        upButPanel.add(pdfBtn);
        upButPanel.add(findPlateBtn);

        addBtn = new JButton("Добавить");
        addBtn.setBackground(new Color(223,201,84));
        addBtn.setOpaque(true);
        addBtn.setFocusable(false);
        addBtn.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        addBtn.addActionListener(this);

        changeBtn = new JButton("Изменить");
        changeBtn.setBackground(new Color(223,201,84));
        changeBtn.setOpaque(true);
        changeBtn.setFocusable(false);
        changeBtn.addActionListener(this);
        changeBtn.setBorder(BorderFactory.createLineBorder(Color.orange));

        delBtn = new JButton("Удалить");
        delBtn.setBackground(new Color(223,201,84));
        delBtn.setOpaque(true);
        delBtn.setFocusable(false);
        delBtn.addActionListener(this);
        delBtn.setBorder(BorderFactory.createLineBorder(Color.red));

        //Создание нижней панели для размещения кнопок
        downButPanel = new JPanel(new GridLayout(1,3,10,0));
        downButPanel.setBounds(14,515,380,39);
        downButPanel.setBackground(new Color(84,119,159));
        downButPanel.add(addBtn);
        downButPanel.add(changeBtn);
        downButPanel.add(delBtn);

        //Создание таблицы с информацией о владельце
        model = new GaiTableModel();
        inputDataFromDBInTable();//Внесение данных в таблицу их базы данных
        OWNERS = new JTable(model);
        scroll = new JScrollPane(OWNERS);
        scroll.setPreferredSize(new Dimension(780,450));

        tablePanel = new JPanel();
        tablePanel.setBounds(14,55,780,450);
        tablePanel.setBackground(new Color(84,119,159));
        tablePanel.add(scroll);

        //Добавление элементов в экранную форму
        this.add(tablePanel);
        this.add(downButPanel);
        this.add(upButPanel);
        this.setVisible(true);

        log.info("Экранная форма сформирована");
    }

    /*
    * Создание пустого документа.
    * */
    public static Document emptyDoc() {
        Document doc = null;
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = builder.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return doc;
    }

    /*
    * Парсер .xml файла.
    * */
    public static ArrayList<String[]> parseXML(String filePath){
        Document doc = null;
        try {
            doc = emptyDoc();
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = dBuilder.parse(new File(filePath));

            doc.getDocumentElement().normalize();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        NodeList nlOwners = doc.getElementsByTagName("owner");//Получение списка элементов с тегом <owner>.
        ArrayList<String[]> res = new ArrayList<>();//Результирующий массив строк.

        //Цикл по всем элементам списка.
        for (int temp = 0; temp < nlOwners.getLength(); temp++) {
            Node elem = nlOwners.item(temp);//Элемент списка (владелец).

            /*
            * Разбиение общего тега <owner> на составляющие.
            * */
            NamedNodeMap attrs = elem.getAttributes();
            String name = attrs.getNamedItem("name").getNodeValue();//Имя
            String surname = attrs.getNamedItem("surname").getNodeValue();//Фамилия
            String birthday = attrs.getNamedItem("birthday").getNodeValue();//Дата рождения

            /*Добавление строки с информацией о владельце в результирующий массив*/
            res.add(new String[]{name, surname, birthday});
        }
        return res;
    }

    /*
    * Вставка информации о владельцах из .xml файла в базу данных.
    * */
    public static void inputFromXML(String filePath){
        ArrayList<String[]> owners;

        owners = parseXML(filePath);

        for(int i=0;i< owners.size();i++){
            Main.insertOwnerInTheBD(owners.get(i)[0],owners.get(i)[1],owners.get(i)[2]);
        }
    }

    /*
    * Создание .xml файла.
    * */
    public static void createXML(){

        Document doc = emptyDoc();
        Node ownerList = doc.createElement("ownerlist");
        doc.appendChild(ownerList);

        //Цикл по строчкам таблицы.
        for (int i = 0; i < model.getRowCount(); i++)
        {
            Element owner = doc.createElement("owner");//Создаем тег <owner>
            ownerList.appendChild(owner);
            owner.setAttribute("id", (String)model.getValueAt(i, 0));//Добавляем тег <id>
            owner.setAttribute("name", (String)model.getValueAt(i, 1));//Добавляем тег <name>
            owner.setAttribute("surname", (String)model.getValueAt(i, 2));//Добавляем тег <surname>
            owner.setAttribute("birthday", (String)model.getValueAt(i, 3));//Добавляем тег <birthday>
        }
        try {
            //Сохранения файла
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            java.io.FileWriter fw = new FileWriter("owners.xml");
            trans.transform(new DOMSource(doc), new StreamResult(fw));
        }
        catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
    * Создание .pdf файла.
    * */
    public static void createPDF(String[] data) throws IOException {
        System.out.println(Arrays.toString(data));

        //
        if(!Arrays.equals(data, new String[data.length])) {
            PDDocument pdf = new PDDocument();
            PDPage firstPage = new PDPage();

            PDPageContentStream contentStream = new PDPageContentStream(pdf, firstPage);
            pdf.addPage(firstPage);

            contentStream.beginText();
            contentStream.setFont(PDType0Font.load(pdf, new File("/Users/aikido/Desktop/javaProjects/hibernateGAI/src/main/resources/arialmt.ttf")), 14);
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(25, 725);

            String firstLine = "ID   ARCTICLE    DATE";
            contentStream.showText(firstLine);
            contentStream.newLine();
            contentStream.newLine();


            for (int i = 0; i < data.length; i++) {
                String text = data[i];
                if(text != null) {
                    contentStream.showText(text);
                    contentStream.newLine();
                }
            }

            contentStream.endText();
            contentStream.close();

            pdf.save("GAI.pdf");
            GUI.showMessage("Документ " + "'GAI.pdf' " + "сформирован!");
            pdf.close();
        }else{showMessage("Нарушений за этот период нет!");}


    }
    public static void addDataFromFile(String fileName){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            Main.deleteAllFromBD();
            int rows = model.getRowCount();
            for (int i = 0; i < rows; i++) model.removeRow(0); // Очистка таблицы
            String id;
            do{
                id = reader.readLine();
                if(id != null){
                    String name = reader.readLine();
                    String surname = reader.readLine();
                    String birthday = reader.readLine();
                    Main.insertOwnerInTheBD(name,surname,birthday);
                    //model.addData(new String[]{id, name, surname, birthday}); // Запись строки в таблицу
                    System.out.println("Success add!");
                }
            } while(id != null);
            reader.close();
        }
        catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, e.toString());
        } // файл не найден
        catch (IOException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    public static void inputDataFromDBInTable(){
        int rows = model.getRowCount();
        for (int i = 0; i < rows; i++) model.removeRow(0);
        String[][] owners = Main.getAllOwnersInfo();
        if(owners!=null){
            for (String[] owner : owners) {
                model.addData(owner);
            }
        }
        model.fireTableDataChanged();
    }
    public static void showMessage(String message){
        JOptionPane.showMessageDialog(null, message);
    }
    public static int getId(){
        return id;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==addBtn){
            int var = JOptionPane.showConfirmDialog(null, "Хотите добавить нового владельца?");
            if(var == 0){
                new InputWindow("addNewOwner");
            } else if (var == 1) {
                var = JOptionPane.showConfirmDialog(null, "Хотите добавать машину");
                if(var == 0){
                    String input = JOptionPane.showInputDialog("Введите id владельца к которому будет привязана машина:");

                    try {
                        id = Integer.parseInt(input);
                        InputWindow addCarWindow = new InputWindow("addCarToOwner");
                        addCarWindow.setIdOwner(id);

                    }catch (NumberFormatException e1){
                        try {
                            throw new MyException("Данные некоректно введены");
                        } catch (MyException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } else if (var == 1) {
                    var = JOptionPane.showConfirmDialog(null, "Хотите добавить нарушение?");
                    if(var == 0){
                        String input = JOptionPane.showInputDialog("Введите id владельца который совершил нарушение:");

                        try {
                            id = Integer.parseInt(input);
                            InputWindow addViolWindow = new InputWindow("addViolToOwner");
                            addViolWindow.setIdOwner(id);

                        }catch (NumberFormatException e1){
                            try {
                                throw new MyException("Данные некоректно введены");
                            } catch (MyException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }
            }
        } else if (e.getSource()==delBtn) {
            int var = JOptionPane.showConfirmDialog(null, "Хотите удалить владельца?");
            if(var == 0) {
                String input = JOptionPane.showInputDialog("Введите id пользователя ('reset' если хотите удалить всех):");
                if (Objects.equals(input, "reset")) {
                    Main.deleteAllFromBD();
                    inputDataFromDBInTable();
                } else {
                    try {
                        id = Integer.parseInt(input);
                        Main.deleteOwnerFromBdById(id);
                        inputDataFromDBInTable();
                    } catch (NumberFormatException e1) {
                        try {
                            throw new MyException("Данные некоректно введены");
                        } catch (MyException ex) {
                            throw new RuntimeException(ex);
                        }
                    } catch (MyException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            } else if (var == 1) {
                 var = JOptionPane.showConfirmDialog(null, "Хотите удалить машину?");
                 if(var == 0){
                     String input = JOptionPane.showInputDialog("Введите id пользователя, у которого хотите удалить машину:");
                     try{
                         id = Integer.parseInt(input);
                         List<Car> cars = Main.getCarsByOid(id);

                         if(cars!=null){
                             StringBuilder info = new StringBuilder();
                             for (int i = 0; i < cars.size(); i++) {
                                 info.append(cars.get(i).getCarInfo()).append('\n');
                             }
                             GUI.showMessage("Все машины, привязанные к владельцу"+'\n'+info);

                             input = JOptionPane.showInputDialog("Введите id машины ('reset' если хотите удалить все):");

                             if (Objects.equals(input, "reset")) {
                                 for (int i = 0; i < cars.size(); i++) {
                                     Main.deleteCarFromDbById(cars.get(i).getCid());
                                 }

                             } else {
                                 try {

                                     id = Integer.parseInt(input);
                                     Main.deleteCarFromDbById(id);

                                 } catch (NumberFormatException e1) {
                                     try {
                                         throw new MyException("Данные некоректно введены");
                                     } catch (MyException ex) {
                                         throw new RuntimeException(ex);
                                     }
                                 } catch (MyException ex) {
                                     throw new RuntimeException(ex);
                                 }
                             }
                         }
                     } catch (NumberFormatException e1) {
                         try {
                             throw new MyException("Данные некоректно введены");
                         } catch (MyException ex) {
                             throw new RuntimeException(ex);
                         }
                     } catch (MyException ex) {
                         throw new RuntimeException(ex);
                     }
                 } else if (var == 1) {
                     var = JOptionPane.showConfirmDialog(null, "Хотите удалить нарушение?");
                     if(var == 0){
                         String input = JOptionPane.showInputDialog("Введите id пользователя, у которого хотите удалить нарушение:");
                         try{
                             id = Integer.parseInt(input);
                             List<Violation> viols = Main.getViolsByOid(id);

                             if(viols!=null){
                                 StringBuilder info = new StringBuilder();
                                 for (int i = 0; i < viols.size(); i++) {
                                     info.append(viols.get(i).getViolInfo()).append('\n');
                                 }
                                 GUI.showMessage("Все нарушения, привязанные к владельцу"+'\n'+info);

                                 input = JOptionPane.showInputDialog("Введите id нарушения ('reset' если хотите удалить все):");

                                 if (Objects.equals(input, "reset")) {
                                     for (int i = 0; i < viols.size(); i++) {
                                         Main.deleteViolFromDbById(viols.get(i).getVid());
                                     }

                                 } else {
                                     try {

                                         id = Integer.parseInt(input);
                                         Main.deleteViolFromDbById(id);

                                     } catch (NumberFormatException e1) {
                                         try {
                                             throw new MyException("Данные некоректно введены");
                                         } catch (MyException ex) {
                                             throw new RuntimeException(ex);
                                         }
                                     } catch (MyException ex) {
                                         throw new RuntimeException(ex);
                                     }
                                 }
                             }
                         } catch (NumberFormatException e1) {
                             try {
                                 throw new MyException("Данные некоректно введены");
                             } catch (MyException ex) {
                                 throw new RuntimeException(ex);
                             }
                         } catch (MyException ex) {
                             throw new RuntimeException(ex);
                         }
                     }

                 }
            }
        } else if (e.getSource() == changeBtn) {
            int var = JOptionPane.showConfirmDialog(null,"Хотите изменить данные владельца?","Изменение данных владельца",1);
            if(var == 0){
                String input = JOptionPane.showInputDialog(null,"Введите id владельца","Изменение данных владельца",1);
                try{
                    id = Integer.parseInt(input);
                    new InputWindow("changeOwnerInfo");
                }catch (NumberFormatException e2){
                    try {
                        throw new MyException("Данные введены некоректно");
                    } catch (MyException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            } else if (var == 1) {
                var = JOptionPane.showConfirmDialog(null,"Хотите изменить данные о машине?","Изменение данных авто",1);
                if (var == 0) {
                    String input = JOptionPane.showInputDialog(null,"Введите id владельца машины","Изменение данных авто",1);
                    try{
                        id = Integer.parseInt(input);
                        List<Car> cars = Main.getCarsByOid(id);
                        StringBuilder output = new StringBuilder();

                        if(cars != null){
                            if(cars.size() != 0){

                                for (int i = 0; i < cars.size(); i++) {
                                    output.append(cars.get(i).getCarInfo()).append('\n');
                                }
                                showMessage("Машины владельца с id " + id + '\n'
                                            + "id | Номер | Марка | Модель | Год | Дата ТО" +'\n'
                                            + output);

                                input = JOptionPane.showInputDialog(null,"Введите id машины","Изменение данны авто",1);

                                try{

                                    id = Integer.parseInt(input);
                                    new InputWindow("changeCarInfo");

                                }catch (NumberFormatException e3){
                                    try {
                                        throw new MyException("Данные введены некоректно");
                                    } catch (MyException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }

                            }else{showMessage("У данного владельца нет машин.");}
                        }else {showMessage("Владельца с таким id не существует.");}


                    }catch (NumberFormatException e2){
                        try {
                            throw new MyException("Данные введены некоректно");
                        } catch (MyException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                } else if (var == 1) {
                    var = JOptionPane.showConfirmDialog(null,"Хотите изменить данные о нарушении?","Изменение данных о нарушении",1);
                    if(var == 0){
                        String input = JOptionPane.showInputDialog(null,"Введите id владельца, нарушения которого хотите изменить","Изменение данных о нарушении",1);
                        List<Violation> viols = Main.getViolsByOid(id);

                        StringBuilder output = new StringBuilder();

                        if (viols != null) {
                            if (viols.size() != 0) {

                                for (int i = 0; i < viols.size(); i++) {
                                    output.append(viols.get(i).getViolInfo()).append('\n');
                                }
                                showMessage("Нарушения владельца машины с id " + id + '\n'
                                            + "id | СТАТЬЯ | ДАТА" + '\n'
                                            + output);

                                input = JOptionPane.showInputDialog(null,"Введите id нарушения","Изменение нарушения",1);

                                try{

                                    id = Integer.parseInt(input);
                                    new InputWindow("changeViolInfo");

                                }catch (NumberFormatException e3){
                                    try {
                                        throw new MyException("Данные введены некоректно");
                                    } catch (MyException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                            } else {showMessage("У данного владельца 0 нарушений!");}
                        }else {showMessage("Владельца с таким id не существует.");}
                    }
                }
            }
        } else if (e.getSource() == findBtn) {
            String plate = JOptionPane.showInputDialog(null,"Введите номер машины:","Поиск владельца по номеру",1);

            String output = Main.findOwnerByPlate(plate);
            if(output != null) {
                showMessage("Владелец машины с номером " + plate + '\n' + output);
            }
        } else if (e.getSource() == tiBtn) {
            String plate = JOptionPane.showInputDialog(null,"Введите номер машины:","Дата последнего ТО",1);

            showMessage("Машина с номером "+plate+'\n'+"Проходила ТО "+Main.getInspDate(plate));
        } else if (e.getSource() == violBtn) {
            String plate = JOptionPane.showInputDialog(null,"Введите номер машины:","Поиск нарушений",1);

            String[] violsInfo = Main.getOwnerViols(plate);
            System.out.println(Arrays.toString(violsInfo));

            StringBuilder output = new StringBuilder();

            if (violsInfo != null) {
                if (violsInfo.length != 0) {
                    for (int i = 0; i < violsInfo.length; i++) {
                        output.append(violsInfo[i]).append('\n');
                    }
                } else {
                    output.append("0 нарушений");
                }
                showMessage("Нарушения владельца машины с номером " + plate + '\n' + "id | СТАТЬЯ | ДАТА" + '\n' + output);
            }

        } else if (e.getSource() == findPlateBtn) {
            String input = JOptionPane.showInputDialog(null,"Введите id владельца:","Поиск номеров",1);

            try{
                id = Integer.parseInt(input);

                showMessage("Номера машин, привязанных к пользователю с id "+id+'\n'+Main.getPlatesById(id));
            }catch (NumberFormatException e2){
                try {
                    throw new MyException("Данные некоректно введены");
                } catch (MyException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else if (e.getSource() == pdfBtn) {
            new InputWindow("createPdf");
        }
    }
}
