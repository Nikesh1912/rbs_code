package com.rbccoding.sdk.controller;

import com.rbccoding.sdk.entity.StockData;
import com.rbccoding.sdk.service.StockDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/stock-data")
public class StockDataController {
    private final StockDataService stockDataService;

    // Upload CSV
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) {
        stockDataService.uploadBulk(file);
        return "Uploaded successfully";
    }

    // Query by ticker
    @GetMapping("/{ticker}")
    public List<StockData> get(@PathVariable String ticker) {
        return stockDataService.getByTicker(ticker);
    }

    // Add single record
    @PostMapping
    public StockData add(@RequestBody StockData stockData) {
        return stockDataService.save(stockData);
    }

}
