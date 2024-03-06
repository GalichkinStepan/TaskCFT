Чтобы развернуть проект требуется:

	Java 11
	Maven 3+
	PostgresSQL

Создадим новую базу данных в Postgresql, где создадим 2 таблицы:

	CREATE TABLE IntIntervals
	(
    	id BIGSERIAL PRIMARY KEY,
    	start1 INT,
    	end1 INT
	);

	CREATE TABLE CharIntervals
	(
    	id BIGSERIAL PRIMARY KEY,
    	start1 CHAR(1),
    	end1 CHAR(1)
	);

После в файле application.properties прописываем подключение к вашей созданной бд:

	spring.datasource.url= jdbc:postgresql://localhost:5432/NameMyDB
	spring.datasource.username= MyUsername
	spring.datasource.password= MyPassword

Все готово! Данное приложение будет работать по адресу localhost:8080
