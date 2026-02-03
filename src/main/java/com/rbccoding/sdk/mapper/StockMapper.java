package com.rbccoding.sdk.mapper;

import com.rbccoding.sdk.entity.StockData;
import com.rbccoding.sdk.model.StockDataModel;
import org.springframework.stereotype.Component;


@Component
public class StockMapper {

    public StockData populateEntity(StockDataModel stockDataModel) {
        return StockData.builder()
                .id(stockDataModel.getId())
                .closePrice(stockDataModel.getClosePrice())
                .highPrice(stockDataModel.getHighPrice())
                .openPrice(stockDataModel.getOpenPrice())
                .tradeDate(stockDataModel.getTradeDate())
                .ticker(stockDataModel.getTicker())
                .volume(stockDataModel.getVolume())
                .percentChange(stockDataModel.getPercentChange())
                .build();
    }

    public StockDataModel populateModel(StockData stockData) {
        return StockDataModel.builder()
                .id(stockData.getId())
                .ticker(stockData.getTicker())
                .tradeDate(stockData.getTradeDate())
                .openPrice(stockData.getOpenPrice())
                .highPrice(stockData.getHighPrice())
                .lowPrice(stockData.getLowPrice())
                .closePrice(stockData.getClosePrice())
                .volume(stockData.getVolume())
                .percentChange(stockData.getPercentChange())
                .build();
    }
}
