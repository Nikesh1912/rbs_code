package com.rbccoding.sdk.dao;

import com.rbccoding.sdk.entity.StockData;

import java.util.List;

public interface StockDataDao {

    void saveAll(List<StockData> data);

    List<StockData> findByStock(String stock);

    StockData save(StockData stockData);
}
