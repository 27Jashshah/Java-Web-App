package uk.ac.ucl.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;


public class DataLoader {

    public static DataFrame loadData(String fileName)
    {
        DataFrame dataFrame = new DataFrame();

        try (
                Reader reader = new FileReader(fileName);
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader()))
        {
            for (String columnName : csvParser.getHeaderNames())
            {
                // The first row of the file contains the column headers, so they are the column Names
                dataFrame.addColumn(columnName);
            }

            int index;
            //This for loop is reading each record of the csv file and adding the data of each record to respective columns
            for (CSVRecord csvRecord: csvParser)
            {
                index = 0;
                for(String columnName : dataFrame.getColumnNames())
                {
                    dataFrame.addValue(columnName,csvRecord.get(index));
                    index += 1;
                }
            }
        } catch (
                IOException e)
        {
            e.printStackTrace();
        }

        return dataFrame;
    }

}

