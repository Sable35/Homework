package ru.sber.proxies;

import com.sun.source.tree.IfTree;
import org.springframework.stereotype.Component;

@Component
public class BankClientsAppProxy {

    public Boolean clientCheck(String phoneNum){
        //типа проверка на наличие клиента
        if(true) {
            return true;
        } else return false;
    }
}
