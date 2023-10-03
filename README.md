# ___Terminal___

Финальный проект курса Java.

### ___Архитектура___
Сервис предназначен для работы с Интернет-банком и представляет собой REST API.
___
### ___Запуск проекта___
~~~
mvn clean install
mvn plugins spring-boot:run
~~~
____
### ___База данных___
Используется PostgreSQL
##### Схема базы данных:
<img src="database.png">

___
### ___Функционал сервиса___
```
GET /balance/get - получение актуального баланса пользователя.
PUT /balance/put - пополнение баланса пользователя.
PUT /balance/take - снятие заданной суммы с баланса пользователя.
GET /operation/getList - получение списка операций для заднного пользователя в заданный промежуток времени.
PUT /balance/transfer - перевод денежныхпо указанным параметрам.
PUT /user/addUser - добавление пользователя
PUT /user/deletUser - удаление пользователя
PUT /user/updateUser - изменение данных пользователя
```
___
<img src="img.png" height="50" width="50" /> 
Produced by Joker)) axaxaxaxaxaxaxxa



                                                                              

