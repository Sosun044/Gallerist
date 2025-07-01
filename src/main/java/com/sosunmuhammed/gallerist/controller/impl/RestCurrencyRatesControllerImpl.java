package com.sosunmuhammed.gallerist.controller.impl;

import com.sosunmuhammed.gallerist.controller.IRestCurrencyRatesController;
import com.sosunmuhammed.gallerist.controller.RestBaseController;
import com.sosunmuhammed.gallerist.controller.RootEntity;
import com.sosunmuhammed.gallerist.dto.CurrencyRatesResponse;
import com.sosunmuhammed.gallerist.service.ICurrencyRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/")
public class RestCurrencyRatesControllerImpl extends RestBaseController implements IRestCurrencyRatesController {

    @Autowired
    private ICurrencyRatesService ratesService;
    @GetMapping("/currency-rates")
    @Override
    public RootEntity<CurrencyRatesResponse> getCurrencyRates(@RequestParam("startDate") String startDate,
                                                              @RequestParam("startDate") String endDate) {
        return ok(ratesService.currencyRates(startDate,endDate));
    }
}
