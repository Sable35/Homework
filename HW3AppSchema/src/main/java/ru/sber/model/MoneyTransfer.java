package ru.sber.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MoneyTransfer {

    private String phoneNum;
    private BigDecimal TransferSum;
    private LocalDate date;

    public MoneyTransfer(String phoneNum, BigDecimal transferSum, LocalDate date) {
        this.phoneNum = phoneNum;
        TransferSum = transferSum;
        this.date = date;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public BigDecimal getTransferSum() {
        return TransferSum;
    }

    public LocalDate getDate() {
        return date;
    }
}
