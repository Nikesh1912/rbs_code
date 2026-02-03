package com.rbccoding.sdk.service;

import com.rbccoding.sdk.entity.StockData;
import com.rbccoding.sdk.model.StockDataModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StockDataService {

    void uploadBulk(MultipartFile file);

    List<StockData> getByTicker(String ticker);

    StockDataModel createEntry(StockDataModel stockDataModel);
}
