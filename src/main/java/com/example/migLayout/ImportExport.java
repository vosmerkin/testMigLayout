package com.example.migLayout;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImportExport {

    final String SPLITTER = ":";
    final String DATE_FORMAT = "yyyy-MM-dd";

    public String importFromFile(String filename) {
        String s;
        try {
            File file = new File(filename);
            FileReader fileReader = new FileReader(file);
            InputStream in = new InputStream(fileReader);
            s = in.read();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        String[] s1;
        try (BufferedReader in = new BufferedReader(new FileReader(new File(filename).getAbsoluteFile()))) {

            while ((s = in.readLine()) != null) {
                s1 = s.split(SPLITTER);
                list.add(new User(s1[0], s1[1], LocalDate.parse(s1[2], DateTimeFormatter.ofPattern(DATE_FORMAT)), new Address(s1[3], s1[4], Integer.parseInt(s1[5]))));
                System.out.println(s);
            }
        } catch (IOException e) {
            System.out.println("File not found");
            System.out.println(e.getMessage());
        }
        return list;


    }

    public void exportToFile(String filename, List<User> list) {
        try (PrintWriter out = new PrintWriter(new File(filename).getAbsoluteFile())) {
            for (User user : list) {
                out.println(user.toString());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.out.println(e.getMessage());
        }
    }


    public void showOnScreen(List<User> list) {
        for (User user : list) {
            System.out.println(user.toString());
        }
    }

    public Map<String, List<User>> getMapByProperty(List<User> list, String propertyName) {
        Map<String, List<User>> mp = new HashMap<>();
        String s1;
        for (User user : list) {
            s1 = user.getProperty(propertyName);

            if (mp.containsKey(s1)) {
                mp.get(s1).add(user);
            } else {
                mp.put(s1, new ArrayList<>());
                mp.get(s1).add(user);
            }
        }
        return mp;
    }

}
