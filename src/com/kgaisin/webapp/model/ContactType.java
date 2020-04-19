package com.kgaisin.webapp.model;

public enum ContactType {
    MOBILE_PHONE("Мобильный телефон"),
    HOME_PHONE("Домашний телефон"),
    SKYPE("Скайп"),
    TELEGRAM("Телеграм"),
    EMAIL("Электронная почта"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль StackOverflow"),
    HOMEPAGE("Домашняя страница");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
