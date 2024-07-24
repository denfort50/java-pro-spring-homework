# java-pro-spring-homework

## Описание
Проект для выполнения домашних заданий по разделу Spring курса Java PRO от Академии Т1.
В проекте реализованы 2 веб-приложения, общающиеся между собой по протоколу HTTP.

## Стек используемых технологий
* Java 17
* Apache Maven 3.8.2
* Spring Boot 3.2.2
* PostgreSQL 42.7.1
* HikariCP 5.0.1
* FlyWay 9.22.3
* Checkstyle 10.7.0

## Требуемое окружение для запуска
* JDK 17
* Apache Maven 3.8
* Docker

## Инструкция по запуску проекта
1) Запустить docker-контейнер с тестовой базой данных в `docker-compose.yaml`
2) Запустить сервис продуктов `ru/dkalchenko/ProductServiceApplication.java`
3) Запустить платежный сервис `ru/dkalchenko/PaymentServiceApplication.java`
4) Проверить работу ендпойнтов, использя запросы в файле `test.http`
