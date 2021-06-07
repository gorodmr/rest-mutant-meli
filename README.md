# Challenge Meli Detector Mutante
Prueba para MercadoLibre por Diego Muñoz, en la que se da solución a la petición de magneto para identificar sí un humano es mutante o no 
 
* Versión 0.0.1 
 
### Instrucciones para realizar el deploy ### 
 
####Local #### 
1. Se debe bajar el proyecto del siguiente repositorio https://github.com/gorodmr/rest-mutant-meli 
2. Se genera el jar con el comando ./mvn clean package 
3. Nos ubicamos en la carpeta target donde se ha generado el jar y se ejecuta el siguiente comando  
java -jar target/rest-mutant-meli-0.0.1.jar 
4. Una ves se haya desplegado podemos ingresar a postman o desde swagger y realizar las pruebas correspondientes 
- Url swagger http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config en la que también 
contaremos con la documentación concerniente a los servicios expuestos en la API 

#### Cloud ####

El jar se encuentra desplegado en una instancia de EC2 con AWS Elastic Beanstalk
para ejecutar se debe ingresar a la siguiente url
- 
- url swagger 

### Frameworks Utilizados ###

* Java 8
* Maven
* Mongo
* AWS Elastic Beanstalk
* Lombok
* Doker

## Documentación ##

La documentación del programa se puede encontrar en base_path\rest-mutant-meli\documentation
En esta carpeta encontramos los siguientes archivos:
- Documento de evidencias de pruebas realizadas en postman
- Documento con las especificaciones y recursos utilizados para el desarrollo, asi como los reportes de coverage
- Colección de postman para realizar las pruebas del programa


