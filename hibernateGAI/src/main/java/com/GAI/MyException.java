package com.GAI;

import javax.swing.*;
import java.util.Objects;

public class MyException extends Exception {
    public MyException(String message) {
        super(message);
        GUI.showMessage(message);
    }
}