# Microservicio basado en arquitectura hexagonal con OpenAPI

## Estructura de proyecto

Proyecto Maven por módulos
- api-specs
- application
- boot
- domain (api, common, model, spi)
- infrastructure

### HOW-TO ##

* En el módulo api-specs **importaremos** el artefacto que contiene la definición de nuestro contrato. En este caso no es un repositorio externo si no que añadimos directamente el yaml en nuestro proyecto.

* En el módulo application irán todos aquellos actores primarios que hacen que se ejecute nuestra lógica de negocio, es decir, un API Rest, un consumidor de un servicio de colas, una interfaz gráfica, etc...Este módulo hará uso de las interfaces de servicio definidas en la capa de dominio.

* El módulo boot contiene la clase que permite ejecutar nuestro proyecto SpringBoot.

* El módulo domain contiene toda la lógica de negocio y está totalmente aislado del resto de módulos. Expone su funcionalidad mediante interfaces de servicio que usará la capa de aplicación y puertos que deberán ser "adaptados" por la capa de infraestructura". **Es completamente agnóstico de la forma y con qué se llevan a cabo las implementaciones de los puertos**.

* El módulo infrastructure contendrá todos aquellos actores secundarios (sistemas de persistencia, conectividad con APIS externas, publicar mensajes en servicios de colas, etc...) que serán ejecutados desde los servicios de la capa de dominio mediante los adaptadores que implementan los puertos expuestos por ésta. También podremos importar el contrato de otro servicio para autogenerar el código cliente automáticamente.

## Requisitos ##

* Spring Tool Suite 4 aka STS versión 4.13.1.RELEASE 
* JDK 17 (17.0.2-open)
* Maven 3.8.x
* Docker
* Docker-compose

## Cómo trabajar en localhost ##

* Carpeta del proyecto:

```bash
mvn clean install
```

* Ir a a carpeta docker-compose y ejecutar:

```bash
docker-compose up -d --remove-orphans
```
* Se levantarán todos los servicios necesarios para el correcto funcionamiento del proyecto

* Para pararlo:

```bash
docker-compose stop
```

Si queremos eliminar el servicio y eliminar los volumenes persistentes:  


```bash
docker-compose down -v
```

## Swagger ##

Se encuentra accesible desde la ruta:

```
http://localhost:8080/swagger-ui/index.html
```
