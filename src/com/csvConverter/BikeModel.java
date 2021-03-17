package com.csvConverter;


import lombok.*;

import java.util.ArrayList;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BikeModel {
    private String modelNumber;
    private ArrayList<String> headings;
    private ArrayList<String> data;
    private ArrayList<String> modelInfoFromCsv = new ArrayList<>();

    public void splitInfo() {
        ArrayList<String[]> splittedRows = firstSplit();
        rowsToHeadingsAndData(splittedRows);
    }

    private void rowsToHeadingsAndData(ArrayList<String[]> splittedRows) {
        headings = new ArrayList<>();
        //Bike model number
        modelNumber = splittedRows.get(0)[1];

        //Info - Headings
        for (int i = 0; i < 7; i++) {
            headings.add(splittedRows.get(i)[0]);
        }
        headings.add(splittedRows.get(23)[0]);
        for (int i = 7; i < 23; i++) {
            headings.add("Front Spring " + splittedRows.get(i)[0]);
        }
        for (int i = 7; i < 23; i++) {
            headings.add("Front Rebound " + splittedRows.get(i)[0]);
        }
        for (int i = 7; i < 23; i++) {
            headings.add("Front Fork Sag " + splittedRows.get(i)[0]);
        }
        for (int i = 24; i < 40; i++) {
            headings.add("Rear Spring " + splittedRows.get(i)[0]);
        }
        for (int i = 24; i < 40; i++) {
            headings.add("Rear Rebound " + splittedRows.get(i)[0]);
        }
        for (int i = 24; i < 40; i++) {
            headings.add("Rear Shock Stroke " + splittedRows.get(i)[0]);
        }
        for (int i = 24; i < 40; i++) {
            headings.add("Rear Shock Sag " + splittedRows.get(i)[0]);
        }

        //Info - data
        data  = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            data.add(splittedRows.get(i)[1]);
        }
        data.add(splittedRows.get(23)[1]);
        for (int j = 1; j < 4; j++) {
            for (int i = 7; i < 23; i++) {
                data.add(splittedRows.get(i)[j].replace(",", " "));
            }

        }
        for (int j = 1; j < 5; j++) {
            for (int i = 24; i < 40; i++) {
                data.add(splittedRows.get(i)[j].replace(",", " "));
            }

        }
    }

    private ArrayList<String[]> firstSplit() {
        ArrayList<String[]> splittedRows = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            splittedRows.add(modelInfoFromCsv.get(i).split(",", -1));
        }
        splittedRows.add(modelInfoFromCsv.get(7).split(",", 2));
        for (int i = 9; i < 25; i++) {
            splittedRows.add(modelInfoFromCsv.get(i).split(",(?![Pcm])"));
        }
        splittedRows.add(modelInfoFromCsv.get(26).split(",", 2));
        for (int i = 28; i < 44; i++) {
            splittedRows.add(modelInfoFromCsv.get(i).split(",(?![Pcm])"));
        }

        return splittedRows;
    }


}
