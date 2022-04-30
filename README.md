# university-pdb

# Многомодульное web приложение Spring Boot + Spring Data

## Модули:

### university-api:
  Хранит доменную модель и интерфейсы
  
  
### university-core:
  Основная библиотека с бизнес логикой и DAL.
  
  
### university-gateway:
  Gateway для бизнес логики. Использует university-core как библиотеку. Написан с приминениеи шаблонизатора thymeleaf. Единственный имеет точку входа (main).

## Сторонние сервисы:

PostgreSQL 14.2

Базу удобно поднимать в докере: 
docker run --name postgresql -d -p 5432:5432 -e POSTGRES_USER=university_user -e POSTGRES_PASSWORD=university_pass -e POSTGRES_DB=university postgres:14.2
