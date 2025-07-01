package com.sosunmuhammed.gallerist.service.impl;

import com.sosunmuhammed.gallerist.dto.CurrencyRatesResponse;
import com.sosunmuhammed.gallerist.exception.BaseException;
import com.sosunmuhammed.gallerist.exception.ErrorMessage;
import com.sosunmuhammed.gallerist.exception.MessageType;
import com.sosunmuhammed.gallerist.service.ICurrencyRatesService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyRatesResponseImpl implements ICurrencyRatesService {
    @Override
    public CurrencyRatesResponse currencyRates(String startDate, String endDate) {
        String rootUrl="https://evds2.tcmb.gov.tr/service/evds/";
        String series = "TP.DK.USD.A";
        String type = "json";
        String endPoint = rootUrl+"series="+series+"&startDate="+startDate+"&endDate="+endDate+"&type="+type;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("key","RhHDn1C4bl");
        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<CurrencyRatesResponse> response = restTemplate.exchange(endPoint, HttpMethod.GET, httpEntity,
                    new ParameterizedTypeReference<CurrencyRatesResponse>() {
                    });
            if (response.getStatusCode().is2xxSuccessful()){
                return response.getBody();
            }
        }catch(Exception e){
            throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATES_IS_OCCURED,e.getMessage()));
        }
        return null;
    }
}
