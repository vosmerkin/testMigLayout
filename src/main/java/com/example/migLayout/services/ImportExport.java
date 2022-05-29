package com.example.migLayout.services;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImportExport {


    public String importFromFile(String filename) {
        String s;
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader in = new BufferedReader(new FileReader(new File(filename).getAbsoluteFile()))) {

            while ((s = in.readLine()) != null) {
                stringBuilder.append(s);
            }
        } catch (IOException e) {
            System.out.println("File not found");
            System.out.println(e.getMessage());
        }
        return stringBuilder.toString();


    }

//    public void exportToFile(String filename, List<User> list) {
//        try (PrintWriter out = new PrintWriter(new File(filename).getAbsoluteFile())) {
//            for (User user : list) {
//                out.println(user.toString());
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found");
//            System.out.println(e.getMessage());
//        }
//    }




}
