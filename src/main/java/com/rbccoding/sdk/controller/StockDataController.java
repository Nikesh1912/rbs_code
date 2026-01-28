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

    private  final StockDataService stockDataService;

    @PostMapping("/bulk-insert")
    public ResponseEntity<String> bulkUpload(
            @RequestHeader("X-Client_Id") String clientId,
            @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        stockDataService.bulkInsert(file); // <-- works now
        return ResponseEntity.ok("Bulk upload successful");
    }


    @GetMapping("/{ticker}")
    public List<StockData> getByTicker(@PathVariable String ticker) {
        return stockDataService.getByTicker(ticker);
    }

    @PostMapping
    public StockData addRecord(
            @RequestHeader("X-Client_Id") String clientId,
            @RequestBody StockData data) {

        return stockDataService.addRecord(data);
    }
}
