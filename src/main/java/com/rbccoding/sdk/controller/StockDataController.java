package com.rbccoding.sdk.controller;

import com.rbccoding.sdk.entity.StockData;
import com.rbccoding.sdk.model.StockDataModel;
import com.rbccoding.sdk.service.StockDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/stock-data")
public class StockDataController {
    private final StockDataService stockDataService;

    // Upload CSV
    @PostMapping(value = "/upload")
    public String upload(@RequestParam MultipartFile file) {
        stockDataService.uploadBulk(file);
        return "Uploaded successfully";
    }

    // Query by ticker
    @GetMapping(value = "/{ticker}")
    public List<StockData> get(@PathVariable String ticker) {
        return stockDataService.getByTicker(ticker);
    }

    // Add single record
    @PostMapping(value = "/newrecord")
    public StockDataModel add(@RequestBody StockDataModel stockDataModel) {
        return stockDataService.createEntry(stockDataModel);
    }

}
