package uk.ac.ucl.model;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.io.BufferedReader;


public class Model
{
  private final String fileName = "data/patients100.csv";
  private DataFrame dataFrame = DataLoader.loadData(fileName);
  public DataFrame getDataFrame(){return dataFrame;}

  //this function returns IDs and Names ("PREFIX" + "FIRST" + "LAST") as a HashMap
  public HashMap<String, String> getAllPatientNames()
  {
    HashMap<String, String> patientIdAndNames = new HashMap<>();
    for(int rowIndex = 0; rowIndex < dataFrame.getRowCount(); rowIndex++)
    {
      String patientName = "";
      patientName = patientName + dataFrame.getValue("PREFIX",rowIndex) + " ";
      patientName = patientName + dataFrame.getValue("FIRST",rowIndex) + " ";
      patientName = patientName + dataFrame.getValue("LAST",rowIndex);
      patientIdAndNames.put(dataFrame.getValue("ID",rowIndex),patientName);
    }
    return patientIdAndNames;
  }

  //This function returns a List of Names of the IDs entered
  //This is used to get names of patients after applying the date filters
  public List<String> getSelectivePatientNames(List<String> ids){
    List<String> patientNames = new ArrayList<>();
    for(int rowIndex = 0; rowIndex < dataFrame.getRowCount(); rowIndex++)
    {
      if(ids.contains(dataFrame.getValue("ID",rowIndex))) {
        String patientName = "";
        patientName = patientName + dataFrame.getValue("PREFIX", rowIndex) + " ";
        patientName = patientName + dataFrame.getValue("FIRST", rowIndex) + " ";
        patientName = patientName + dataFrame.getValue("LAST", rowIndex);
        patientNames.add(patientName);
      }
    }
    return patientNames;
  }

  //This function returns an entire record as a List by taking the ID as the parameter
  public List<String> getRecordById(String id)
  {
    List<String> record = new ArrayList<>();
    int rowIndex = 0;
    while(true)
    {
      if(dataFrame.getValue("ID",rowIndex).equals(id))
      {
        break;
      }
      else
      {
        rowIndex += 1;
      }
    }

    for(String columnName : dataFrame.getColumnNames())
    {
      record.add(dataFrame.getValue(columnName,rowIndex));
    }
    return record;
  }

  //This is my search function
  public List<String> searchFor(String keyword, String columnSearched)
  {
    if(dataFrame.getColumnNames().contains(columnSearched) || columnSearched.equals("")) {
      List<String> result = new ArrayList<>();
      for (int rowIndex = 0; rowIndex < dataFrame.getRowCount(); rowIndex++) {
        //If columnSearched is a column then search for the keyword in the column.
        if (dataFrame.getColumnNames().contains(columnSearched) && dataFrame.getValue(columnSearched, rowIndex).contains(keyword)) {
          result.add(dataFrame.getValue("ID", rowIndex));
        }
        //else if columnSearched is empty (""), search for the keyword in every column
        else{
          for(String column : dataFrame.getColumnNames())
          {
            if(dataFrame.getValue(column,rowIndex).contains(keyword))
            {
              result.add(dataFrame.getValue("ID",rowIndex));
              break;
            }
          }
        }
      }
      return result;
    }
    //return null if columnSearched is not a column and is not empty
    else{
      return null;
    }

  }

  //This function returns IDs of all alive patients by checking if DEATHDATE is empty
  public List<String> getAlivePatients()
  {
    List<String> alive = new ArrayList<>();
    for(int rowIndex = 0; rowIndex < dataFrame.getRowCount(); rowIndex++){
      if(dataFrame.getValue("DEATHDATE", rowIndex).isEmpty()) {
        alive.add(dataFrame.getValue("ID", rowIndex));
      }
    }
    return alive;
  }

  //This function returns IDs of all dead patients by checking if DEATHDATE is not empty
  public List<String> getDeadPatients()
  {
    List<String> dead = new ArrayList<>();
    for(int rowIndex = 0; rowIndex < dataFrame.getRowCount(); rowIndex++){
      if(!(dataFrame.getValue("DEATHDATE", rowIndex).isEmpty())) {
        dead.add(dataFrame.getValue("ID", rowIndex));
      }
    }
    return dead;
  }

  //This is an auxiliary function that converts a String to a LocalDate
  private LocalDate stringToDate(String dateString)
  {
    //splitting the date String
    List<String> dateListString = List.of(dateString.split("-"));
    List<Integer> dateListInt = new ArrayList<>();
    //converting all Strings to Integer
    for (String s : dateListString) {
      int dateInt = Integer.parseInt(s);
      dateListInt.add(dateInt);
    }
      return LocalDate.of(dateListInt.get(0),dateListInt.get(1),dateListInt.get(2));
  }

  //Another auxiliary function that calculates the age by taking Strings date-of-birth and death date as parameters
  private float calculateAge(String dobStr, String deathStr){
    LocalDate dob = stringToDate(dobStr);
    LocalDate death;
    if(deathStr.isEmpty()) {
      death = LocalDate.now(); // patient is alive
    }
    else{
      death = stringToDate(deathStr); // patient is dead
    }
    Period diff = Period.between(dob,death);

    return (diff.getYears() + ((float)diff.getMonths()/12) + ((float)diff.getDays()/365));
  }

  //Another auxiliary function that returns ages of the patients with the IDs entered
  private List<Float> getAges(List<String> ids)
  {
    List<List<String>> records = new ArrayList<>();
    List<Float> patientAges = new ArrayList<>();
    float age;

    for (int index = 0; index < ids.size();index++)
    {
      records.add(getRecordById(ids.get(index)));
    }
    for(List<String> record : records)
    {
      if(record.get(dataFrame.getColumnIndex("DEATHDATE")).isEmpty()){
        age = calculateAge(record.get(dataFrame.getColumnIndex("BIRTHDATE")),""); // patient is alive
      } else {
        age = calculateAge(record.get(dataFrame.getColumnIndex("BIRTHDATE")),record.get(dataFrame.getColumnIndex("DEATHDATE"))); //patient is dead
      }
      patientAges.add(age);
    }
    return patientAges;
  }

  //This is a function that returns IDs of the oldest people and their ranking as a 2D List
  public List<List<String>> getMostOldest(String number)
  {
    List<String> ids = getAlivePatients();
    List<Float> patientAges = getAges(ids);
    List<List<String>> oldestIdsAndNumbers = new ArrayList<>();
    List<Float> tempPatientAges = new ArrayList<>(patientAges);
    int index;
    float maxAge;
    int n;

    //checks if the String number is an int
    try {
      n = Integer.parseInt(number);
    } catch (NumberFormatException e) {
      n = 0;
    }

    for(int count = 1; count <= n; count++)
    {
        //max age is calculated
        //a temporary List is used as if the original List is used, since it becomes immutable and an error/exception occurs.
        maxAge = Collections.max(tempPatientAges);
        //this while loop checks if there is someone else with the same age, if yes then add them to the final output with the same numbering
        while (tempPatientAges.contains(maxAge)) {
          index = tempPatientAges.indexOf(maxAge);
          oldestIdsAndNumbers.add(List.of(Integer.toString(count), ids.get(index)));
          //setting age to -1.0f as that can never be max until all patients have been added to the final output
          tempPatientAges.set(index, -1.0f);
        }
    }
    return oldestIdsAndNumbers;
  }

  //This is a function that returns IDs of the youngest people and their ranking as a 2D List
  public List<List<String>> getMostYoungest(String number)
  {
    List<String> ids = getAlivePatients();
    List<Float> patientAges = getAges(ids);
    List<List<String>> youngestIdsAndNumbers = new ArrayList<>();
    List<Float> tempPatientAges = new ArrayList<>(patientAges);
    int index;
    float minAge;
    int n;

    //checks if the String number is an int
    try {
      n = Integer.parseInt(number);
    } catch (NumberFormatException e) {
      n = 0;
    }

    //min age is calculated
    //a temporary List is used as if the original List is used, since it becomes immutable and an error/exception occurs.
    for(int count = 1; count <= n; count++)
    {
      minAge = Collections.min(tempPatientAges);
      while (tempPatientAges.contains(minAge)) {
        index = tempPatientAges.indexOf(minAge);
        youngestIdsAndNumbers.add(List.of(Integer.toString(count), ids.get(index)));
        //setting age to 1000.0f assuming no patient lives for 1000 years and as that can never be min until all patients have been added to the final output
        tempPatientAges.set(index, 1000.0f);
      }
    }
    return youngestIdsAndNumbers;
  }

  //This function filters the dates and returns IDs of all patients born before/after a date
  public List<String> dateFilter(String dateString, String operation)
  {
    List<List<String>> idsAndDob = new ArrayList<>();
    List<String> filteredIds = new ArrayList<>();
    LocalDate date = stringToDate(dateString);

    for(int rowIndex = 0; rowIndex < dataFrame.getRowCount();rowIndex++)
    {
      idsAndDob.add(List.of(dataFrame.getValue("ID",rowIndex),dataFrame.getValue("BIRTHDATE",rowIndex)));
    }

    for(int index = 0; index < idsAndDob.size();index++)
    {
      LocalDate dob = stringToDate(idsAndDob.get(index).get(1));
      //add to the output if dob is after or equal to the date entered
      if(operation.equals("After") && (dob.isAfter(date) || dob.isEqual(date))){
        filteredIds.add(idsAndDob.get(index).getFirst());
      }
      //add to the output if dob is before the date entered
      else if(operation.equals("Before") && dob.isBefore(date)){
        filteredIds.add(idsAndDob.get(index).getFirst());
      }
    }

    return filteredIds;
  }

  //This is a generic function that returns a HashMap with column data as keys and the number of times it is present as values
  //It is used to make pie charts.
  public HashMap<String,Integer> hashForGraphs(String column){

      HashMap<String,Integer> result = new HashMap<>();
      String value;
      //Just in case if there is empty data
      result.put("'Not Disclosed'", 0);

      for(int rowIndex = 0; rowIndex < dataFrame.getRowCount(); rowIndex++)
      {
        //checks if data is empty, if yes then adds 1 to 'Not Disclosed'
        if(dataFrame.getValue(column,rowIndex).isEmpty()){
          result.replace("'Not Disclosed'", result.get("'Not Disclosed'") + 1);
        } else {
          value = "'" + dataFrame.getValue(column, rowIndex) + "'";
          //checks if the key is already present in the HashMap, if not then adds the key to the HashMap with the value 1
          if (!(result.containsKey(value))) {
            result.put(value, 1);
          }
          //if yes, then adds 1 to the key's value
          else {
            result.replace(value, result.get(value) + 1);
          }
        }
      }
      return result;
  }

  //This function returns a HashMaps for the age graph
  //It has ranges as keys and count as values
  public HashMap<String,Integer> hashForAgeGraphs() {
    HashMap<String,Integer> result = new HashMap<>();
    List<String> ids = new ArrayList<>();

    for(int rowIndex = 0; rowIndex < dataFrame.getRowCount(); rowIndex++){
      ids.add(dataFrame.getValue("ID", rowIndex));
    }

    List<Float> ages = getAges(ids);
    Collections.sort(ages);
    int ageLB = (int) Math.floor(Collections.min(ages)/ 10.0) * 10; //Lower Bound is lower multiple of 10 of minimum
    int ageUB = (int) Math.ceil(Collections.max(ages)/ 10.0) * 10; //Upper Bound is upper multiple of 10 of maximum
    int low,up;
    String keyName;
      for (Float age : ages) {
        //for every age low and up indexes are set to lower bound and lower bound + 10 respectively
        low = ageLB;
        up = ageLB + 10;
        //while loop goes until low index reaches upper bound
        while(low != ageUB)
        {
          //checks if the age is within the range of the low and up indexes
          if(age > low && age < up){
            keyName = "'" + low + "-" + up + "'";
            //when the first age of a range is found, the key is added to the HashMap
            if (!(result.containsKey(keyName))) {
              result.put(keyName, 1);
            }
            //else the value of the key is just updated by adding 1
            else {
              result.replace(keyName, result.get(keyName) + 1);
            }
            break;
          }
          low += 10;
          up += 10;
        }
      }
      return result;

  }

  //This function returns the ethnicities and their counts for a specific race
  public HashMap<String,Integer> hashForEthnicityGraphs(String race){

    HashMap<String,Integer> result = new HashMap<>();
    String value;
    if(race.equals("'Not Disclosed'"))
    {
      result.put("'Not Disclosed'",0);
    }
    else{
      for(int rowIndex = 0; rowIndex < dataFrame.getRowCount(); rowIndex++) {
        //Checks if record is of the race that is required
        if (("'" + dataFrame.getValue("RACE", rowIndex) + "'").equals(race)) {
          value = "'" + dataFrame.getValue("ETHNICITY", rowIndex) + "'";
          //Checks if the key is there in the HashMap, if not then adds the key with value 1 to the HashMap
          if (!(result.containsKey(value))) {
            result.put(value, 1);
          }
          //if yes, then the value of the key is just updated by adding 1
          else {
            result.replace(value, result.get(value) + 1);
          }
        }
      }
    }
    return result;
  }

  //This function adds a record to the CSV file and the DataFrame
  public void addRecord(List<String> recordList){
    try (FileWriter fileWriter = new FileWriter(fileName, true)) {
      List<String> columnNames = dataFrame.getColumnNames();
      int colIndex = 0;
      String recordStr = "";
      //Converts the List of the Record into a String for csv format
      for(String data: recordList)
      {
        recordStr = recordStr + data + ",";
        dataFrame.addValue(columnNames.get(colIndex),data);
        colIndex++;
      }
      recordStr = recordStr.substring(0,recordStr.length() - 1); //removes the last extra ',' in the String
      recordStr = recordStr + "\n";

      //writes the String into the file
      fileWriter.write(recordStr);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //This functions deletes a record from the CSV file and reloads the file onto the dataframe
  public void deleteRecord(String id){
    List<String> lines = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      //Reading all lines and adding it to the List
      while ((line = reader.readLine()) != null) {
        lines.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    //Removes the line that contains the id
    lines.removeIf(line -> line.contains(id));

    //Writes new data onto a new file with the same name, therefore the new file overwrites the old one
    try (FileWriter fileWriter = new FileWriter(fileName)) {
      for (String updatedLine : lines) {
        fileWriter.write(updatedLine + "\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    dataFrame = DataLoader.loadData(fileName); //reloading file as data in the CSV has changed
  }

}
