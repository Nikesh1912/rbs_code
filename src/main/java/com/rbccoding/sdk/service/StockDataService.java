package com.rbccoding.sdk.service;

import com.rbccoding.sdk.dao.StockDataDao;
import com.rbccoding.sdk.entity.StockData;
import com.rbccoding.sdk.repository.StockDataRepository;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class StockDataService {
//    private final StockDataDao stockDataDao;

    private final StockDataRepository stockDataRepository;

    @Transactional
    public void bulkInsert(MultipartFile file) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            List<StockData> list = parseCsv(br);
            stockDataRepository.saveAll(list);
        } catch (Exception e) {
            throw new RuntimeException("Bulk insert failed", e);
        }
    }

    @Transactional
    public void bulkInsert(File file, String clientId) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            List<StockData> list = parseCsv(br);
            stockDataRepository.saveAll(list);
        } catch (Exception e) {
            throw new RuntimeException("Bulk insert failed", e);
        }
    }

    private List<StockData> parseCsv(BufferedReader br) throws Exception {
        List<StockData> list = new ArrayList<>();
        String line = br.readLine(); // skip header
        while ((line = br.readLine()) != null) {
            list.add(parseCsvLine(line));
        }
        return list;
    }


    public List<StockData> getByTicker(String ticker) {
        return stockDataRepository.findByStock(ticker);
    }

    @Transactional
    public StockData addRecord(StockData data) {
        return stockDataRepository.save(data);
    }


    /**
     * Parses Dow Jones CSV file
     */
    private StockData parseCsvLine(String line) {
        String[] data = line.split(",");
        StockData stock = new StockData();
        stock.setQuarter(parseInt(data[0]));
        stock.setStock(data[1]);
        stock.setDate(data[2]);
        stock.setOpen(parseDollar(data[3]));
        stock.setHigh(parseDollar(data[4]));
        stock.setLow(parseDollar(data[5]));
        stock.setClose(parseDollar(data[6]));
        stock.setVolume(parseLong(data[7]));
        stock.setPercentChangePrice(parseDouble(data[8]));
        stock.setPercentChangeVolumeOverLastWeek(parseNullableDouble(data[9]));
        stock.setPreviousWeeksVolume(parseNullableLong(data[10]));
        stock.setNextWeeksOpen(parseDollar(data[11]));
        stock.setNextWeeksClose(parseDollar(data[12]));
        stock.setPercentChangeNextWeeksPrice(parseDouble(data[13]));
        stock.setDaysToNextDividend(parseInt(data[14]));
        stock.setPercentReturnNextDividend(parseDouble(data[15]));
        return stock;
    }


    private Double parseDollar(String s) { return (s == null || s.isEmpty()) ? null : Double.parseDouble(s.replace("$","")); }
    private Double parseDouble(String s) { return (s == null || s.isEmpty()) ? null : Double.parseDouble(s); }
    private Double parseNullableDouble(String s) { return parseDouble(s); }
    private Long parseLong(String s) { return (s == null || s.isEmpty()) ? null : Long.parseLong(s); }
    private Long parseNullableLong(String s) { return parseLong(s); }
    private Integer parseInt(String s) { return (s == null || s.isEmpty()) ? null : Integer.parseInt(s); }


}
