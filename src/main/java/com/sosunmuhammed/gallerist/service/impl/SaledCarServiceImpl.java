package com.sosunmuhammed.gallerist.service.impl;

import com.sosunmuhammed.gallerist.dto.*;
import com.sosunmuhammed.gallerist.enums.CarStatusType;
import com.sosunmuhammed.gallerist.exception.BaseException;
import com.sosunmuhammed.gallerist.exception.ErrorMessage;
import com.sosunmuhammed.gallerist.exception.MessageType;
import com.sosunmuhammed.gallerist.model.Car;
import com.sosunmuhammed.gallerist.model.Customer;
import com.sosunmuhammed.gallerist.model.SaledCar;
import com.sosunmuhammed.gallerist.repository.*;
import com.sosunmuhammed.gallerist.service.ICurrencyRatesService;
import com.sosunmuhammed.gallerist.service.ISaledCarService;
import com.sosunmuhammed.gallerist.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

@Service
public class SaledCarServiceImpl implements ISaledCarService {

    @Autowired
    private SaledCarRepository saledCarRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ICurrencyRatesService currencyRatesService;

    private BigDecimal convertCustomerAmountToUSD(Customer customer){
        CurrencyRatesResponse currencyRatesResponse = currencyRatesService.currencyRates(
                DateUtils.getCurrentDate(new Date()),DateUtils.getCurrentDate(new Date()));
        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
        BigDecimal customerUSDAmount= customer.getAccount().getAmount().divide(usd,2, RoundingMode.HALF_UP);

        return customerUSDAmount;
    }

    public BigDecimal remainingCustomerAmount(Customer customer,Car car){
        BigDecimal customerUSDAmount = convertCustomerAmountToUSD(customer);
        BigDecimal remainingCustomerUSDAmount = customerUSDAmount.subtract(car.getPrice());

        CurrencyRatesResponse currencyRatesResponse = currencyRatesService.
                currencyRates(DateUtils.getCurrentDate(new Date()),DateUtils.getCurrentDate(new Date()));
        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
        return remainingCustomerUSDAmount.multiply(usd);
    }


    private boolean checkAmountCustomer(DtoSaledCarIU dtoSaledCarIU){
        Optional<Car> optCarPrice = carRepository.findById(dtoSaledCarIU.getCarId());
        if (optCarPrice.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoSaledCarIU.getCarId().toString()));
        }
        Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerId());
        if (optCustomer.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoSaledCarIU.getCustomerId().toString()));
        }
        BigDecimal customerUSDAmount = convertCustomerAmountToUSD(optCustomer.get());
        if (customerUSDAmount.compareTo(optCarPrice.get().getPrice()) == 0||customerUSDAmount.compareTo(optCarPrice.get().getPrice()) >0){
            return true;
        }
        return false;
    }

    private SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU){
        SaledCar saledCar = new SaledCar();
        saledCar.setCreateTime(new Date());

        saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()).orElse(null));
        saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()).orElse(null));
        saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()).orElse(null));

        return saledCar;
    }

    public  boolean checkCarStatus(Long carId){
        Optional<Car> optCar = carRepository.findById(carId);
        if (optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SALED.name())){
            return true;
        }
        return false;
    }
    @Override
    public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {

        if (!checkAmountCustomer(dtoSaledCarIU)){
            throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH,""));
        }
        if (checkCarStatus(dtoSaledCarIU.getCarId())){
            throw new BaseException(new ErrorMessage(MessageType.CAR_STATUS_IS_ALREADY_SALED,dtoSaledCarIU.getCarId().toString()));
        }
        SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(dtoSaledCarIU));
        Car car = savedSaledCar.getCar();
        car.setCarStatusType(CarStatusType.SALED);
        carRepository.save(car);

        Customer customer = savedSaledCar.getCustomer();
        customer.getAccount().setAmount(remainingCustomerAmount(customer,car));
        customerRepository.save(customer);


        return toDto(savedSaledCar);
    }
    private DtoSaledCar toDto(SaledCar saledCar){
        DtoSaledCar dtoSaledCar = new DtoSaledCar();
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoCar dtoCar = new DtoCar();
        DtoGallerist dtoGallerist = new DtoGallerist();

        BeanUtils.copyProperties(saledCar,dtoSaledCar);
        BeanUtils.copyProperties(saledCar.getCar(),dtoCar);
        BeanUtils.copyProperties(saledCar.getCustomer(),dtoCustomer);
        BeanUtils.copyProperties(saledCar.getGallerist(),dtoGallerist);

        dtoSaledCar.setCustomer(dtoCustomer);
        dtoSaledCar.setCar(dtoCar);
        dtoSaledCar.setGallerist(dtoGallerist);

        return dtoSaledCar;
    }
}
