package com.sosunmuhammed.gallerist.controller;

import com.sosunmuhammed.gallerist.dto.CurrencyRatesResponse;

public interface IRestCurrencyRatesController {
    RootEntity<CurrencyRatesResponse> getCurrencyRates(String startDate,String endDate);
}
