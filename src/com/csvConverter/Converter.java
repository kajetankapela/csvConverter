package com.csvConverter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
public class Converter {
    private ArrayList<String> csvFile = new ArrayList<>();
    ArrayList<BikeModel> bikeModels = new ArrayList<>();


    public void convert(String path)  {
        csvFile = readFile(path);
        if (csvFile != null) {
            csvToBikeModels(csvFile);
            newCsvFiles(bikeModels);
        }
    }

    private ArrayList<String> readFile(String path) {
        try {
            return (ArrayList<String>) Files.readAllLines(Paths.get(path));
        } catch (IOException ioException) {
            System.out.println("Something went wrong with file!");
        }
        return null;
    }

    private void csvToBikeModels(ArrayList<String> csvFile) {
        separateBikes(csvFile);
        for (BikeModel bikeModel : bikeModels) {
            bikeModel.splitInfo();
        }
    }

    private void separateBikes(ArrayList<String> csvFile) {
        for (int i = 0; i < csvFile.size(); i += 2) {
            ArrayList<String> separatedBike = new ArrayList<>();
            i++;
            while (!csvFile.get(i).contains("END BIKE MODEL")) {
                separatedBike.add(csvFile.get(i++));
            }
            bikeModels.add(BikeModel.builder().modelInfoFromCsv(separatedBike).build());
        }
    }



    private void newCsvFiles(ArrayList<BikeModel> bikeModels){
        for (BikeModel bikeModel : bikeModels) {
            try {
                FileWriter fileWriter = new FileWriter("csv/bike-" + bikeModel.getModelNumber() + ".csv");

            } catch (IOException ioException) {
                System.out.println("Something went wrong with file!");
            }
        }
    }

}
