[![Build Status](https://travis-ci.com/wgcisotto/carrinho-compras-be.svg?branch=master)](https://travis-ci.com/wgcisotto/carrinho-compras-be)
[![codecov](https://codecov.io/gh/wgcisotto/word-server/branch/master/graph/badge.svg)](https://codecov.io/gh/wgcisotto/word-server)

## BackEnd  

#### Carrinho de Compras service

- Contem o crud para Item, Usuario 
- Controle de Carrinho de compras 

#### Items
Method	| Path	| Description 
------------- | ------------------------- | ------------------------------- | 
GET	| /items	| get a list of items
POST| /items	| create new item
GET| /items/{id}	| get item by id
PUT| /items/{id}	| update an item
DELETE| /items/{id}	| delete an item

#### User
Method	| Path	| Description 
------------- | ------------------------- | ------------------------------- | 
GET	| /users	| get a list of users
POST| /users	| create new user
GET| /users/{id}	| get user by id
PUT| /users/{id}	| update a user
DELETE| /users/{id}	| delete a user

#### User
Method	| Path	| Description 
------------- | ------------------------- | ------------------------------- | 
PUT	| /carts/user/{id}	| add an item to the cart by user
GET| /carts	| get a list of carts
GET| /carts/user/{id}	| get cart by user id
PUT| /carts/{id}	| remove an item to the cart 
DELETE| /carts/{id}	| delete a cart

## Starting application

**Pre**

``install mongo``
``port: 27017``
``dbname: mongo-db``

**Build**

``./mvnw clean install dockerfile:build`` 

**Start Jar**

``java -jar /target/carrinho-compras-be-0.0.1-SNAPSHOT.jar``

**Run Docker**

``docker run -p 8080:8080 wgcisotto/carrinho-compras-be``

**Testing**

``curl https://localhost:8080/``
 