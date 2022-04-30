# university-pdb

Многомодульное web приложение Spring Boot + Hibernate

university-api:
  Хранит доменную модель и интерфейсы
university-core:
  Основная библиотека с бизнес логикой и DAL.
university-gateway:
  Gateway для бизнес логики. Использует university-core как библиотеку. Написан с приминениеи шаблонизатора thymeleaf. Единственный имеет точку входа (main).
