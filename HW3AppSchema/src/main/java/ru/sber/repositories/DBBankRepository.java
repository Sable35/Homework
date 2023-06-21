package ru.sber.repositories;

import org.springframework.stereotype.Repository;
import ru.sber.model.MoneyTransfer;
@Repository
public class DBBankRepository {

public void recordInDB(MoneyTransfer moneyTransfer){
    System.out.println("Запись в базу данных перевода на сумму: " + moneyTransfer.getTransferSum() + " на номер: " + moneyTransfer.getPhoneNum() + ", Дата: " + moneyTransfer.getDate());
}
}
