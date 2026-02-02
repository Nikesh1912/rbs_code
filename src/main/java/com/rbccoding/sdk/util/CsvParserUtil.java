package com.rbccoding.sdk.util;

import com.rbccoding.sdk.entity.StockData;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import com.opencsv.CSVReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CsvParserUtil {

    public static List<StockData> parse(MultipartFile file) {

        List<StockData> stocks = new ArrayList<>();

        try (CSVReader reader =
                     new CSVReader(new InputStreamReader(file.getInputStream()))) {

            String[] row;

            while ((row = reader.readNext()) != null) {

                StockData stock = StockData.builder()
                        .ticker(row[1])
                        .tradeDate(LocalDate.parse(row[2]))
                        .openPrice(parseDouble(row[3]))
                        .highPrice(parseDouble(row[4]))
                        .lowPrice(parseDouble(row[5]))
                        .closePrice(parseDouble(row[6]))
                        .volume(Long.parseLong(row[7]))
                        .percentChange(Double.parseDouble(row[8]))
                        .build();

                stocks.add(stock);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return stocks;
    }

    private static Double parseDouble(String s) {
        return Double.parseDouble(s.replace("$",""));
    }
}
