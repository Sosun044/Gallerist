package com.sosunmuhammed.gallerist.exception;

import lombok.Getter;

@Getter
public enum MessageType {
    NO_RECORD_EXIST("1004","Kayıt Bulunamadı!!!"),
    TOKEN_IS_EXPIRED("1005","TOKEN SURESİ BİTTMİSTİR"),
    USER_NOT_FOUND("1006","USERNAME BULUNAMADI"),
    USERNAME_OR_PASSWORD_INVALID("1007","USERNAME VEYA KULLANICI ADI GEÇERSİZ"),
    REFRESH_TOKEN_NOT_FOUND("1008","REFRESH TOKEN BULUNAMADI"),
    REFRESH_TOKEN_IS_EXPIRED("1009","REFRESH TOKENIN SÜRESİ BİTMİŞTİR "),
    CURRENCY_RATES_IS_OCCURED("1010","DÖVİZ KURU ALINAMADI"),
    CUSTOMER_AMOUNT_IS_NOT_ENOUGH("1011","MÜŞTERİ PARASI YETERLİ DEĞİL"),
    CAR_STATUS_IS_ALREADY_SALED("1012","ARABA  SATILDI GÖRÜNDÜĞÜ İÇİN SATILAMAZ"),
    GENERAL_EXCEPTION("9999","GENEL BİR HATA OLUSTU!!!!");

    private String code;

    private String message;

    MessageType(String code,String message){
        this.code = code;
        this.message = message;
    }
}
