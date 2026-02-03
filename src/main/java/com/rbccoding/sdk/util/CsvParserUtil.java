package com.rbccoding.sdk.util;

import com.rbccoding.sdk.entity.StockData;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import com.opencsv.CSVReader;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Builder
public class CsvParserUtil {
    private CsvParserUtil() {} // prevent instantiation

    public static List<StockData> parse(MultipartFile file) {

        List<StockData> stocks = new ArrayList<>();

        try (CSVReader reader =
                     new CSVReader(new InputStreamReader(file.getInputStream()))) {

            String[] row;

            // ✅ skip header
            reader.readNext();

            while ((row = reader.readNext()) != null) {

                if (row.length < 9) continue; // skip bad rows

                StockData stock = StockData.builder()
                        .ticker(row[1].trim())
                        .tradeDate(LocalDate.parse(row[2].trim()))
                        .openPrice(parseDecimal(row[3]))
                        .highPrice(parseDecimal(row[4]))
                        .lowPrice(parseDecimal(row[5]))
                        .closePrice(parseDecimal(row[6]))
                        .volume(parseLong(row[7]))
                        .percentChange(parseDecimal(row[8]))
                        .build();

                stocks.add(stock);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error parsing CSV file", e);
        }

        return stocks;
    }

    // ✅ SAFE decimal parsing (no precision loss)
    private static BigDecimal parseDecimal(String s) {
        if (s == null || s.isBlank()) return BigDecimal.ZERO;
        return new BigDecimal(s.replace("$", "").trim());
    }

    private static Long parseLong(String s) {
        if (s == null || s.isBlank()) return 0L;
        return Long.parseLong(s.trim());
    }
}
