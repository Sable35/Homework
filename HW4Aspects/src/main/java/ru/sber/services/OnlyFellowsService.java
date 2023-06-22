package ru.sber.services;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.sber.aspects.NotEmpty;
import ru.sber.aspects.NotEmptyAspect;

import java.util.List;
import java.util.logging.Logger;

@Service
public class OnlyFellowsService implements OnlyFellowsServiceInterface{

    private Logger logger = Logger.getLogger(NotEmptyAspect.class.getName());
    @NotEmpty
    @Override
    public void authorizeCheckFellow(String login, String password) {
        logger.info("Проверка на правильность ввода данных пользователя в authorizeCheckFellow(String login, String password)");
    }
    @NotEmpty
    @Override
    public void setImages(List<String> images) {
        logger.info("Установка изображений пользователя в setImages(List<String> images)");
    }
    @NotEmpty
    @Override
    public void subscribeFellow() {
        logger.info("Подписка пользователя в subscribeFellow()");
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
