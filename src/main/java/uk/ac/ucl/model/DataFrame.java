package uk.ac.ucl.model;
import java.util.ArrayList;

public class DataFrame {
    private ArrayList<Column> columns = new ArrayList<>();

    public void addColumn(String columnName)
    {
        Column column = new Column();
        column.setName(columnName);
        columns.add(column);
    }

    public ArrayList<String> getColumnNames()
    {
        ArrayList<String> columnNames = new ArrayList<>();
        for(Column column : columns)
        {
            columnNames.add(column.getName());
        }
        return columnNames;
    }

    public int getRowCount()
    {
        return columns.getFirst().getSize();
    }

    public String getValue(String columnName, int row)
    {
        ArrayList<String> columnNames = getColumnNames();
        int columnIndex = columnNames.indexOf(columnName);
        return columns.get(columnIndex).getRowValue(row);
    }

    public void putValue(String columnName, int row, String value)
    {
        ArrayList<String> columnNames = getColumnNames();
        int columnIndex = columnNames.indexOf(columnName);
        columns.get(columnIndex).setRowValue(row, value);
    }

    public void addValue(String columnName, String value)
    {
        ArrayList<String> columnNames = getColumnNames();
        int columnIndex = columnNames.indexOf(columnName);
        columns.get(columnIndex).addRowValue(value);
    }

    public int getColumnIndex(String columnName)
    {
        ArrayList<String> columnNames = getColumnNames();
        return columnNames.indexOf(columnName);
    }



}