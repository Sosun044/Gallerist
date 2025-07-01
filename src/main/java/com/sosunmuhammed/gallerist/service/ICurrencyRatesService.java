package com.sosunmuhammed.gallerist.service;

import com.sosunmuhammed.gallerist.dto.CurrencyRatesResponse;

public interface ICurrencyRatesService {

    CurrencyRatesResponse currencyRates(String startDate,String endDate);
}
