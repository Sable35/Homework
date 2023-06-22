package ru.sber.services;

import java.util.List;

/**
 * Сервис сайта OnlyFellows
 */
public interface OnlyFellowsServiceInterface {
    /**
     * Проверить данные пользователя при автоизации
     */
    public void authorizeCheckFellow(String login, String password);
    /**
     * Установить изображения пользователя
     */
    public void setImages(List<String> images);
    /**
     * Подписать пользователя
     */
    public void subscribeFellow();
}
