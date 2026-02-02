package com.rbccoding.sdk.entity;

import jakarta.persistence.*;

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
    private Long id;

    private String ticker;

    private LocalDate tradeDate;

    private Double openPrice;
    private Double highPrice;
    private Double lowPrice;
    private Double closePrice;

    private Long volume;

    private Double percentChange;


}

