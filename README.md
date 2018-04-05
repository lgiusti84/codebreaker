# Magneto's Code breaker - Examen para MercadoLibre
Examen mutantes, rest API

One Paragraph of project description goes here

## API:
El API REST solicitado está disponible en http://meli.giusti.net.ar/

* /mutant/
  * Tipo: POST
  * Respuesta para DNA **mutante: 200 (OK)**
  * Respuesta para DNA **humano: 403 (Forbidden)**
  * Respuesta para **otros valores: 400 (Bad request)**
  * Acciones, valida el DNA y lo guarda en la base de datos (si es que no se encontraba ya en la misma)
  * formato esperado (JSON): Objeto que contiene un Array de strings llamado dna

    Ej:
    ```
    { "dna": ["AAAA", "ACGT", "ACCC", "AGTA"] }
    ```

* /stats/
  * Tipo: GET
  * Respuesta: 200 OK
  * Acciones: devuelve las estadisticas de cantidad de mutantes y humanos en la base de datos (si es que los hay)

* /stats/reset/
  * Tipo: GET
  * Acciones: borra los datos de la tabla que contiene los datos de mutantes/humanos y redirecciona a /stats/


## Como correrlo
Conseguir una copia de GIT, correrla con el **perfil de spring: "local"** activado.

Ej:
1. ir a la ubicacion del achivo pom.xml
2. ejecutar en consola: mvn spring-boot:run -P local -Dspring.profiles.active=local
    * se puso tambien el perfil local al llamar a maven (-P local), para asegurar que se corre el dicho perfil, ya que si hay una variable de entorno llamada envname con valor aws, se va a correr el perfil prod

### Tecnologias y servicios utilizados:
Se utilizó **Java** como el lenguaje de programacion utilizando **Spring Boot 2**.
Los reportes de test coverage  hechos con **JaCoCo** están en la carpeta report.
Project Managment: apache **Maven**

La aplicación está subida a **AWS EC2** y tiene su base alojada en  **RDS de Amazon**, la misma utiliza **mySQL**.
La versión de la Aplicacion que está subida en el server es la que está en el branch _Jenkins_.
El DNS utilizado es el Route 53 de Amazon.

### Autor
Luis J. Giusti
* [mail](mailto:lgiusti84@gmail.com)
* [linkedin](https://www.linkedin.com/in/luisgiusti/)
