//package com.rbccoding.sdk.dao;
//
//import com.rbccoding.sdk.entity.StockData;
//import com.rbccoding.sdk.repository.StockDataRepository;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class StockDataDaoImpl implements StockDataDao {
//    private final StockDataRepository repository;
//
//    public StockDataDaoImpl(StockDataRepository repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public void saveAll(List<StockData> data) {
//        repository.saveAll(data);
//    }
//
//    @Override
//    public List<StockData> findByStock(String stock) {
//        return repository.findByStockIgnoreCase(stock);
//    }
//
//    @Override
//    public StockData save(StockData stockData) {
//        return repository.save(stockData);
//    }
//}
