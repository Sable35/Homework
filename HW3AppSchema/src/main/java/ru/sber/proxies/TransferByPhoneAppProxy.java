package ru.sber.proxies;

import org.springframework.stereotype.Component;
import ru.sber.model.MoneyTransfer;
@Component
public class TransferByPhoneAppProxy {
    public void transferSum(MoneyTransfer moneyTransfer) {
        System.out.println("Совершен перевод на сумму: " + moneyTransfer.getTransferSum() + " на номер: " + moneyTransfer.getPhoneNum());
    }
}
