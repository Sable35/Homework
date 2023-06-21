package ru.sber.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sber.model.MoneyTransfer;
import ru.sber.proxies.BankClientsAppProxy;
import ru.sber.proxies.TransferByPhoneAppProxy;
import ru.sber.repositories.DBBankRepository;
@Service
public class MoneyTransferService {

    private BankClientsAppProxy bankClientsAppProxy;
    private TransferByPhoneAppProxy transferByPhoneAppProxy;
    private DBBankRepository dBBankRepository;
    @Autowired
    public MoneyTransferService(BankClientsAppProxy bankClientsAppProxy, TransferByPhoneAppProxy transferByPhoneAppProxy, DBBankRepository dBBankRepository) {
        this.bankClientsAppProxy = bankClientsAppProxy;
        this.transferByPhoneAppProxy = transferByPhoneAppProxy;
        this.dBBankRepository = dBBankRepository;
    }

    public void transfer(MoneyTransfer moneyTransfer){
        if(!bankClientsAppProxy.clientCheck(moneyTransfer.getPhoneNum())) {
            System.out.println("Не существует клиента с таким номером телефона");
        }
        transferByPhoneAppProxy.transferSum(moneyTransfer);
        dBBankRepository.recordInDB(moneyTransfer);
    }
}
