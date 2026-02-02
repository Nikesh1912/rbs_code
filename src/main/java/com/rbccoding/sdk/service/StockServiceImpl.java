package com.rbccoding.sdk.service;

import com.rbccoding.sdk.entity.StockData;
import com.rbccoding.sdk.repository.StockDataRepository;
import com.rbccoding.sdk.util.CsvParserUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class StockServiceImpl implements StockDataService{

    private final StockDataRepository stockDataRepository;

    @Override
    public void uploadBulk(MultipartFile file) {

        List<StockData> stocks = CsvParserUtil.parse(file);

        stockDataRepository.saveAll(stocks);   // batch insert
    }

    @Override
    @Transactional(readOnly = true)
    public List<StockData> getByTicker(String ticker) {
        return stockDataRepository.findByTicker(ticker);
    }

    @Override
    public StockData save(StockData stock) {
        return stockDataRepository.save(stock);
    }
}
