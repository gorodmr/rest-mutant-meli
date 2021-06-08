# Challenge Meli Detector Mutante
Prueba para MercadoLibre por Diego Muñoz, en la que se da solución a la petición de magneto para identificar sí un humano es mutante o no 
 
* Versión 0.0.1 
 
### Instrucciones para realizar el deploy ### 
 
####Local #### 
1. Se debe bajar el proyecto del siguiente repositorio https://github.com/gorodmr/rest-mutant-meli 
2. Se genera el jar con el comando ./mvn clean package 
3. Nos ubicamos en la carpeta target donde se ha generado el jar y se ejecuta el siguiente comando  
java -jar rest-mutant-meli-0.0.1.jar 
4. Una ves se haya desplegado podemos ingresar a postman o desde swagger y realizar las pruebas correspondientes 
- Url swagger http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config en la que también 
contaremos con la documentación concerniente a los servicios expuestos en la API 

#### Cloud ####

El jar se encuentra desplegado en una instancia de EC2 con AWS Elastic Beanstalk
para ejecutar se debe ingresar a la siguiente url
- http://detectormutantesmeli10-env.eba-jdufpgne.us-east-2.elasticbeanstalk.com/
- url swagger http://detectormutantesmeli10-env.eba-jdufpgne.us-east-2.elasticbeanstalk.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

### Frameworks Utilizados ###

* Java 8
* Maven
* Mongo
* AWS Elastic Beanstalk
* Lombok
* Doker

## Diseño ##

Se hizo uso del patrón observer para implementar la búsqueda de secuencias de forma asíncrona 
y se agregaron eventos a cada buscador(vertical, horizontal, diagonal up y diagonal down) de tal forma que se origine
un evento cada que se encuentre una secuencia. Estos eventos son recibidos por el observador quien lleva un conteo del
numero de secuencias encontradas. Una vez se complete un mínimo de dos secuencias se eliminan los procesos de búsqueda
y se retorna el resultado (esto para cuando es un mutante), si no se completan las dos secuencias espera a terminar 
procesos de búsqueda y retorna falso para indicar que no es mutante. Esta implementación reduce tiempo en las búsquedas

## Documentación ##

La documentación del programa se puede encontrar en base_path\rest-mutant-meli\documentation
En esta carpeta encontramos los siguientes archivos:
- Documento de evidencias de pruebas realizadas en postman
- Documento con las especificaciones y recursos utilizados para el desarrollo, asi como los reportes de coverage
- Colección de postman para realizar las pruebas del programa

#### Mejoras ####

* Para dar cumplimiento al requerimiento que permita realizar mas de 1 millon de peticiones por segundo se sugiere 
la configuración de un grupo de auto scalling para administrar las instancias segun demanda



