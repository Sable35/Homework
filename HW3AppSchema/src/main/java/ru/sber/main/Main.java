package ru.sber.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sber.appConfig.AppConfig;
import ru.sber.model.MoneyTransfer;
import ru.sber.services.MoneyTransferService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        var applicationService = context.getBean(MoneyTransferService.class);
        applicationService.transfer(new MoneyTransfer("89517423137", BigDecimal.valueOf(5000), LocalDate.now()));
    }
}
