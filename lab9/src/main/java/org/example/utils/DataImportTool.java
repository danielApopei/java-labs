package org.example.utils;

import org.example.config.MyLoggerConfig;
import org.example.dao.BookDAO;
import org.example.models.Book;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class DataImportTool {

    private static final Logger logger = MyLoggerConfig.LOGGER;

    public void readCSV(String file) throws IOException, ParseException, InterruptedException {
        String line = "";
        BufferedReader br = new BufferedReader(new FileReader(file));
        br.readLine();
        while((line = br.readLine()) != null) {
            String[] data = line.split(",", -1);
            String[] authors = data[2].split("/", -1);
            List<String> authors2 = new ArrayList<>(Arrays.asList(authors));
//            String[] date = data[10].split("/", -1);
//            Date d = new Date(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
//            LocalDate d2 = LocalDate.parse(data[10], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            Date df = null;
            try{
                df = formatter.parse(data[10]);
                Book book = new Book(
                        data[1], // title
                        Integer.parseInt(data[7]), // num of pages
                        df // publication date
                        );
                System.out.println(book.toString());
                BookDAO.create(book);
            } catch (ParseException e) {
                logger.info("Invalid date format! Ignoring entry...");
                System.out.println("Invalid date format! Ignoring entry...");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
