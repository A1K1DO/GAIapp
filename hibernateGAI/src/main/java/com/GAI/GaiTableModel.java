package com.GAI;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * This class realize model of the table
 */
public class GaiTableModel extends AbstractTableModel {

    /**
     * This int variable stores the number of columns
     */
    private int columns = 4;
    private List<String[]> data;
    public GaiTableModel(){
        data = new ArrayList<String[]>();
        for(int i = 0; i < data.size(); i++){
            data.add(new String[getColumnCount()]);
        }

    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    /**
     * This method defines count of the columns.
     * @return
     */
    @Override
    public int getColumnCount() {
        return columns;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = data.get(rowIndex);
        return rows[columnIndex];
    }

    /**
     * This method initializes the column's names.
     * @param columnIndex  the column being queried
     * @return
     */
    @Override
    public String getColumnName (int columnIndex){
        switch (columnIndex){
            case 0: return "Id";
            case 1: return "Name";
            case 2: return "Surname";
            case 3: return "Birthday";
        }
        return "";
    }
    public void removeRow(int rowNum){
        data.remove(rowNum);
    }

    public void addData(String[] inputData){
        try{
            this.data.add(inputData);
            fireTableDataChanged();
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}