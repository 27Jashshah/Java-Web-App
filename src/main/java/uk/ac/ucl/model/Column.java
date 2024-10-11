package uk.ac.ucl.model;
import java.util.ArrayList;
public class Column {
    private String name;
    private ArrayList<String> rowData = new ArrayList<>();

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getSize() {return rowData.size();}

    public String getRowValue(int rowIndex)
    {
        return rowData.get(rowIndex);
    }

    public void setRowValue(int rowIndex, String value)
    {
        rowData.add(rowIndex, value);
    }

    public void addRowValue(String value)
    {
        rowData.add(value);
    }
}
