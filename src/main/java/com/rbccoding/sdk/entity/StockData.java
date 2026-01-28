package com.rbccoding.sdk.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "stock_data")
@Getter
@Setter
@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // mandatory for JPA

    private Integer quarter;
    private String stock;
    private String date; // or LocalDate if you want parsing
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Long volume;
    private Double percentChangePrice;
    private Double percentChangeVolumeOverLastWeek;
    private Long previousWeeksVolume;
    private Double nextWeeksOpen;
    private Double nextWeeksClose;
    private Double percentChangeNextWeeksPrice;
    private Integer daysToNextDividend;
    private Double percentReturnNextDividend;


}

