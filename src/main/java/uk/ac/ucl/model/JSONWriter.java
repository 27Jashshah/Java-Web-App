package uk.ac.ucl.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JSONWriter {

    public String convertToJSON(DataFrame dataFrame) throws JsonProcessingException {
        List<String> columnNames = dataFrame.getColumnNames();
        List<HashMap<String, String>> records = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        //this for loop is adding each record as a HashMap to the list of HashMaps
        for (int rowIndex = 0; rowIndex < dataFrame.getRowCount(); rowIndex++) {
            HashMap<String, String> record = new HashMap<>();
            for (int colIndex = 0; colIndex < columnNames.size(); colIndex++) {
                record.put(columnNames.get(colIndex), dataFrame.getValue(columnNames.get(colIndex), rowIndex));
            }
            records.add(record);
        }

        //The example JSON follows this format
        HashMap<String, List<HashMap<String, String>>> HashMapJSON = new HashMap<>();
        HashMapJSON.put("patients:", records);

        //returns the JSON as a String
        return objectMapper.writeValueAsString(HashMapJSON);
    }

}
