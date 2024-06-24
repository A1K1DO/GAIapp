package com.GAI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class InputWindow extends JFrame implements ActionListener {
    private static String typeWindow;
    private String[] data;
    private final JButton okBtn;
    private final JButton cancelBtn;
    private JButton dataFromFileBtn;
    private JFormattedTextField pathInput;
    private JPanel inputPathPanel;
    private JFormattedTextField nameInput;
    private JFormattedTextField surnameInput;
    private JFormattedTextField birthdayInput;
    private JFormattedTextField plateInput;
    private JFormattedTextField brandInput;
    private JFormattedTextField modelInput;
    private JFormattedTextField yearInput;
    private JFormattedTextField dateInspInput;
    private JFormattedTextField dateViolInput;
    private JFormattedTextField articleInput;
    private JFormattedTextField fmonthInput;
    private JFormattedTextField smonthInput;
    private JFormattedTextField idInput;
    private int idOwner;



    InputWindow(String typeWindowEnter){
        typeWindow = typeWindowEnter;

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout(0,15));
        this.getContentPane().setBackground(new Color(84,119,159));
        this.setBounds(600,300,250,210);
        this.setResizable(false);

        okBtn = new JButton();
        okBtn.setText("Ok");
        okBtn.setForeground(new Color(0, 0, 0, 153));
        okBtn.setFont(new Font("Roboto",Font.BOLD,13));
        okBtn.addActionListener(this);
        okBtn.setBackground(new Color(0x8000FF00, true));
        okBtn.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        okBtn.setOpaque(true);

        cancelBtn = new JButton();
        cancelBtn.setText("Cancel");
        cancelBtn.setForeground(new Color(0, 0, 0, 153));
        cancelBtn.setFont(new Font("Roboto",Font.BOLD,13));
        cancelBtn.addActionListener(this);
        cancelBtn.setBackground(new Color(0x73FF0000, true));
        cancelBtn.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        cancelBtn.setOpaque(true);

        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 15, 5));
        btnPanel.add(okBtn);
        btnPanel.add(cancelBtn);
        btnPanel.setBackground(new Color(84,119,159));

        this.add(btnPanel, BorderLayout.SOUTH);

        JLabel headText;
        JPanel inputPanel;
        if(Objects.equals(typeWindow, "addNewOwner")){
            this.setTitle("Добавление пользователя");

            dataFromFileBtn = new JButton();
            dataFromFileBtn.setText("Файл");
            dataFromFileBtn.setForeground(new Color(0, 0, 0, 153));
            dataFromFileBtn.setFont(new Font("Roboto",Font.BOLD,13));
            dataFromFileBtn.addActionListener(this);
            dataFromFileBtn.setBackground(new Color(0xC20BC7EC, true));
            dataFromFileBtn.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
            dataFromFileBtn.setOpaque(true);

            btnPanel.add(dataFromFileBtn,1);

            headText = new JLabel("Введите данные");
            headText.setHorizontalAlignment(JLabel.CENTER);
            headText.setForeground(new Color(238, 238, 238));
            headText.setFont(new Font("Roboto",Font.BOLD,17));

            nameInput = new JFormattedTextField();
            nameInput.setValue("Имя");
            nameInput.setBackground(new Color(0x4DFFFFFF, true));

            surnameInput = new JFormattedTextField();
            surnameInput.setValue("Фамилия");
            surnameInput.setBackground(new Color(0x4DFFFFFF, true));

            birthdayInput = new JFormattedTextField();
            birthdayInput.setValue("Дата рождения");
            birthdayInput.setBackground(new Color(0x4DFFFFFF, true));

            inputPanel = new JPanel(new GridLayout(4,1));
            inputPanel.setBackground(new Color(84,119,159));
            inputPanel.add(nameInput);
            inputPanel.add(surnameInput);
            inputPanel.add(birthdayInput);

            this.add(headText, BorderLayout.NORTH);
            this.add(inputPanel,BorderLayout.CENTER);

        } else if (Objects.equals(typeWindow, "addDataFromFile")) {
            this.setTitle("Данные из файла");

            headText = new JLabel("Введите путь к файлу");
            headText.setHorizontalAlignment(JLabel.CENTER);
            headText.setForeground(new Color(238, 238, 238));
            headText.setFont(new Font("Roboto",Font.BOLD,17));

            pathInput = new JFormattedTextField();
            pathInput.setValue("File Path");
            pathInput.setBackground(new Color(0x4DFFFFFF, true));

            inputPanel = new JPanel(new GridLayout(2,1));
            inputPanel.setBackground(new Color(84,119,159));
            inputPanel.add(pathInput);

            this.add(headText, BorderLayout.NORTH);
            this.add(inputPanel,BorderLayout.CENTER);



        } else if (Objects.equals(typeWindow,"changeOwnerInfo")) {
            this.setTitle("Добавление пользователя");

            headText = new JLabel("Введите данные");
            headText.setHorizontalAlignment(JLabel.CENTER);
            headText.setForeground(new Color(238, 238, 238));
            headText.setFont(new Font("Roboto",Font.BOLD,17));

            nameInput = new JFormattedTextField();
            nameInput.setValue("Имя");
            nameInput.setBackground(new Color(0x4DFFFFFF, true));

            surnameInput = new JFormattedTextField();
            surnameInput.setValue("Фамилия");
            surnameInput.setBackground(new Color(0x4DFFFFFF, true));

            birthdayInput = new JFormattedTextField();
            birthdayInput.setValue("Дата рождения");
            birthdayInput.setBackground(new Color(0x4DFFFFFF, true));

            inputPanel = new JPanel(new GridLayout(4,1));
            inputPanel.setBackground(new Color(84,119,159));
            inputPanel.add(nameInput);
            inputPanel.add(surnameInput);
            inputPanel.add(birthdayInput);


            this.add(headText, BorderLayout.NORTH);
            this.add(inputPanel,BorderLayout.CENTER);

        }else if (Objects.equals(typeWindow, "addCarToOwner") || Objects.equals(typeWindow, "changeCarInfo")) {
            this.setTitle("Добавление машины");
            this.setBounds(600,300,250,260);

            headText = new JLabel("Введите данные машины");
            headText.setHorizontalAlignment(JLabel.CENTER);
            headText.setForeground(new Color(238, 238, 238));
            headText.setFont(new Font("Roboto",Font.BOLD,17));

            plateInput = new JFormattedTextField();
            plateInput.setValue("Номер");
            plateInput.setBackground(new Color(0x4DFFFFFF, true));

            brandInput = new JFormattedTextField();
            brandInput.setValue("Марка");
            brandInput.setBackground(new Color(0x4DFFFFFF, true));

            modelInput = new JFormattedTextField();
            modelInput.setValue("Модель");
            modelInput.setBackground(new Color(0x4DFFFFFF, true));

            yearInput = new JFormattedTextField();
            yearInput.setValue("Год выпуска");
            yearInput.setBackground(new Color(0x4DFFFFFF, true));

            dateInspInput = new JFormattedTextField();
            dateInspInput.setValue("Дата испекции");
            dateInspInput.setBackground(new Color(0x4DFFFFFF, true));

            inputPanel = new JPanel(new GridLayout(5,1));
            inputPanel.setBackground(new Color(84,119,159));
            inputPanel.add(plateInput);
            inputPanel.add(brandInput);
            inputPanel.add(modelInput);
            inputPanel.add(yearInput);
            inputPanel.add(dateInspInput);

            this.add(headText, BorderLayout.NORTH);
            this.add(inputPanel,BorderLayout.CENTER);
        }else if (Objects.equals(typeWindow, "addViolToOwner") || Objects.equals(typeWindow, "changeViolInfo")) {
            this.setTitle("Добавление нарушения");

            headText = new JLabel("Введите данные нарушения");
            headText.setHorizontalAlignment(JLabel.CENTER);
            headText.setForeground(new Color(238, 238, 238));
            headText.setFont(new Font("Roboto", Font.BOLD, 17));

            articleInput = new JFormattedTextField();
            articleInput.setValue("Номер статьи в кодексе");
            articleInput.setBackground(new Color(0x4DFFFFFF, true));

            dateViolInput = new JFormattedTextField();
            dateViolInput.setValue("Дата нарушения");
            dateViolInput.setBackground(new Color(0x4DFFFFFF, true));

            inputPanel = new JPanel(new GridLayout(2, 1));
            inputPanel.setBackground(new Color(84, 119, 159));
            inputPanel.add(articleInput);
            inputPanel.add(dateViolInput);

            this.add(headText, BorderLayout.NORTH);
            this.add(inputPanel, BorderLayout.CENTER);
        }else if (Objects.equals(typeWindow, "createPdf")) {
            this.setTitle("Генирация отчета о нарушениях");

            headText = new JLabel("Введите данные владельца");
            headText.setHorizontalAlignment(JLabel.CENTER);
            headText.setForeground(new Color(238, 238, 238));
            headText.setFont(new Font("Roboto", Font.BOLD, 17));

            idInput = new JFormattedTextField();
            idInput.setValue("id владельца");
            idInput.setBackground(new Color(0x4DFFFFFF, true));

            fmonthInput = new JFormattedTextField();
            fmonthInput.setValue("Первый месяц");
            fmonthInput.setBackground(new Color(0x4DFFFFFF, true));

            smonthInput = new JFormattedTextField();
            smonthInput.setValue("Второй месяц");
            smonthInput.setBackground(new Color(0x4DFFFFFF, true));

            inputPanel = new JPanel(new GridLayout(3, 1));
            inputPanel.setBackground(new Color(84, 119, 159));
            inputPanel.add(idInput);
            inputPanel.add(fmonthInput);
            inputPanel.add(smonthInput);

            this.add(headText, BorderLayout.NORTH);
            this.add(inputPanel, BorderLayout.CENTER);
        }

        this.setVisible(true);
    }

    public static String getTypeWindow(){
        return typeWindow;
    }
    public int getId(){
        return idOwner;
    }
    public void setIdOwner(int id){
        idOwner=id;
    }

    private String getOName(){
        return nameInput.getText();
    }
    private String getOSurname(){
        return surnameInput.getText();
    }
    private String getOBirthday(){
        return birthdayInput.getText();
    }

    private String getFilePath(){
        return  pathInput.getText();
    }



    private void returnDataFromInputs(){

        switch (getTypeWindow()){
            case "addNewOwner": data = new String[3];
                                data[0] = getOName();
                                data[1] = getOSurname();
                                data[2] = getOBirthday();
            case "addDataFromFile": data = new String[1];
                                    data[0] = getFilePath();
            case "changeOwnerInfo": data = new String[3];
                                    data[0] = getOName();
                                    data[1] = getOSurname();
                                    data[2] = getOBirthday();
        }
    }


    private void checkData (JTextField sourceField) throws MyException,NullPointerException {
        switch (sourceField.getText()) {
            case "Имя" -> throw new MyException("Вы не ввели имя нового владельца");
            case "Фамилия" -> throw new MyException("Вы не ввели фамилию нового владельца");
            case "Дата рождения" -> throw new MyException("Вы не ввели дату рождения нового владельца");
            case "File Path" -> throw new MyException("Вы не ввели путь к файлу");
            case "Номер" -> throw new MyException("Вы не ввели номер машины");
            case "Марка" -> throw new MyException("Вы не ввели марку машины");
            case "Модель" -> throw new MyException("Вы не ввели модель машины");
            case "Год выпуска" -> throw new MyException("Вы не ввели год выпуска машины");
            case "Дата испекции" -> throw new MyException("Вы не ввели дату испекции машины");
            case "Номер статьи в кодексе" -> throw new MyException("Вы не ввели статью");
            case "Дата нарушения" -> throw new MyException("Вы не ввели дату совершения нарушения");
            case "id владельца" -> throw new MyException("Вы не ввели id владельца");
            case "Первый месяц" -> throw new MyException("Вы не ввели первый месяц");
            case "Второй месяц" -> throw new MyException("Вы не ввели второй месяц");

        }
        if (sourceField.getText().length() == 0) throw new MyException("Поле не может быть пустым!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==okBtn && Objects.equals(typeWindow, "addNewOwner")){
            try {
                checkData(nameInput);
                checkData(surnameInput);
                checkData(birthdayInput);

                setIdOwner(Main.insertOwnerInTheBD(nameInput.getText(),surnameInput.getText(),birthdayInput.getText()));

                this.dispose();

                if(JOptionPane.showInternalConfirmDialog(null,"Хотите добавить машину данном владельцу?")==0){
                    InputWindow temp = new InputWindow("addCarToOwner");
                    temp.setIdOwner(getId());
                } else {
                    if (JOptionPane.showInternalConfirmDialog(null,"Хотите добавить нарушение данном владельцу?")==0){
                        InputWindow temp = new InputWindow("addViolToOwner");
                        temp.setIdOwner(getId());
                    }
                }
            }catch (NullPointerException e1){
                JOptionPane.showMessageDialog(this,e1.toString());
            }catch (MyException e2){
                JOptionPane.showMessageDialog(null,e2.getMessage());
            }

        }else if (e.getSource() == okBtn && Objects.equals(typeWindow,"addDataFromFile")){
            try{
                String path = pathInput.getText();
                System.out.println(path);
                checkData(pathInput);
                GUI.inputFromXML(path);
                this.dispose();
            }catch (NullPointerException e1){
                JOptionPane.showMessageDialog(this, e1.toString());
            }catch (MyException e2){
                JOptionPane.showMessageDialog(null, e2.getMessage());
            }
        }else if (e.getSource() == okBtn && Objects.equals(typeWindow, "changeOwnerInfo")){
            try{
                checkData(nameInput);
                checkData(surnameInput);
                checkData(birthdayInput);

                int id = GUI.getId();

                Main.changeDataInDB(id, nameInput.getText(), surnameInput.getText(), birthdayInput.getText());

                GUI.inputDataFromDBInTable();

            }catch (NullPointerException e1){
                JOptionPane.showMessageDialog(this,e1.toString());
            }catch (MyException e2){
                JOptionPane.showMessageDialog(null,e2.getMessage());
            }
            this.dispose();
        } else if (e.getSource()==okBtn && Objects.equals(typeWindow,"addCarToOwner")) {
            try{
                checkData(brandInput);
                checkData(plateInput);
                checkData(modelInput);
                checkData(yearInput);
                checkData(dateInspInput);


                Main.addCarToOwner(plateInput.getText(),brandInput.getText(),modelInput.getText(),Integer.parseInt(yearInput.getText()),dateInspInput.getText(),getId());
                GUI.showMessage("Данные о машине добавлены в базу!");


                this.dispose();
                if (JOptionPane.showInternalConfirmDialog(null,"Хотите добавить нарушение данном владельцу?")==0){
                    InputWindow temp = new InputWindow("addViolToOwner");
                    temp.setIdOwner(getId());
                }
            }catch (MyException e3){
                GUI.showMessage(e3.toString()+'\n'+"Ошибка ввода данных");
            }

        } else if (e.getSource()==okBtn && Objects.equals(typeWindow,"addViolToOwner")) {
            try{
                checkData(articleInput);
                checkData(dateViolInput);

                System.out.println("ZDES' ID AAAA AA------------"+getId());
                Main.addViolToOwner(articleInput.getText(),dateViolInput.getText(),getId());
                GUI.showMessage("Данные о нарушении добавлены в базу!");


                this.dispose();
            }catch (MyException e3){
                GUI.showMessage(e3.toString()+'\n'+"Ошибка ввода данных");
            }

        } else if (e.getSource()==cancelBtn) {
            this.dispose();
        } else if (e.getSource()==dataFromFileBtn) {
            new InputWindow("addDataFromFile");
            this.dispose();
        } else if (e.getSource() == okBtn && Objects.equals(typeWindow,"createPdf")) {
            try {
                checkData(idInput);
                checkData(fmonthInput);
                checkData(smonthInput);

                int id;
                int fm;
                int sm;

                try{
                    id = Integer.parseInt(idInput.getText());
                    fm = Integer.parseInt(fmonthInput.getText());
                    sm = Integer.parseInt(smonthInput.getText());
                }catch (NumberFormatException e1){
                    throw new MyException("Данные введены некоректно.");
                }

                GUI.createPDF(Objects.requireNonNull(Main.getViolsInfoForPDF(id, fm, sm)));

            } catch (MyException | IOException ex) {
                throw new RuntimeException(ex);
            }
            this.dispose();
        } else if (e.getSource() == okBtn && Objects.equals(typeWindow,"changeCarInfo")) {
            try {
                checkData(brandInput);
                checkData(plateInput);
                checkData(modelInput);
                checkData(yearInput);
                checkData(dateInspInput);


                Main.changeCarInfo(GUI.getId(), plateInput.getText(),brandInput.getText(), modelInput.getText(),yearInput.getText(),dateInspInput.getText());
                GUI.showMessage("Данные о машине изменены!");
            } catch (MyException ex) {
                throw new RuntimeException(ex);
            }
            this.dispose();
        }else if (e.getSource() == okBtn && Objects.equals(typeWindow,"changeViolInfo")) {
            try {
                checkData(articleInput);
                checkData(dateViolInput);


                Main.changeViolInfo(GUI.getId(), articleInput.getText(), dateViolInput.getText());
                GUI.showMessage("Данные о нарушении изменены!");
            } catch (MyException ex) {
                throw new RuntimeException(ex);
            }
            this.dispose();
        }
    }


    public static void main(String[] args){
        //new InputWindow("addCarToOwner");
    }


}


