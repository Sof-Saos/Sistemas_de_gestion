# Proyecto de Sistema de Gestión de Hoteles en Spring Boot

## Descripción del Proyecto

El proyecto de Sistema de Gestión de Hoteles en Spring Boot es una aplicación que permite a los hoteles gestionar eficientemente las reservas, clientes y habitaciones. Utiliza una base de datos H2 integrada para almacenar la información relevante, ofreciendo dos formas de implementación: con microservicios conectados mediante Feign o sin microservicios.

## Funcionalidades Clave

1. **Gestión de Reservas:** Los hoteles pueden registrar y administrar reservas de habitaciones para sus clientes. Esto incluye la capacidad de verificar la disponibilidad de habitaciones en un rango de fechas específico y asignar habitaciones a clientes.

2. **Gestión de Clientes:** Los datos de los clientes se almacenan y pueden ser administrados, lo que incluye la posibilidad de agregar, modificar o eliminar información de clientes. La información del cliente incluye detalles personales, información de contacto y registros de reserva.

3. **Gestión de Habitaciones:** Los hoteles pueden administrar la disponibilidad y características de las habitaciones. Esto incluye agregar nuevas habitaciones, modificar detalles de las habitaciones (como tipo, capacidad, tarifa, etc.) y eliminar habitaciones que no estén disponibles.

## Implementación con Microservicios y Feign

En esta implementación, el proyecto utiliza la arquitectura de microservicios, lo que significa que cada funcionalidad clave (reservas, clientes y habitaciones) se implementa como un servicio independiente. Se utiliza Feign para la comunicación entre microservicios, lo que permite la interacción sin problemas entre los diferentes componentes del sistema.

## Implementación sin Microservicios

En esta implementación, el proyecto se desarrolla como una única aplicación monolítica. Todas las funcionalidades (reservas, clientes y habitaciones) se gestionan dentro de la misma aplicación de Spring Boot sin divisiones en microservicios. Esto puede ser adecuado para aplicaciones más pequeñas o para escenarios en los que la complejidad de microservicios no es necesaria.

## Tecnologías Utilizadas

- Spring Boot: Framework de desarrollo de aplicaciones Java.
- H2 Database: Base de datos embebida para el almacenamiento de datos.
- Feign: Cliente HTTP declarativo para comunicación entre microservicios.
- REST API: Comunicación basada en API RESTful para interactuar con los servicios.
- Hibernate: Mapeo objeto-relacional para la gestión de datos.

## Beneficios

- Flexibilidad para elegir la implementación que mejor se adapte a las necesidades del hotel.
- Escalabilidad al permitir la adición de nuevos microservicios según sea necesario.
- Facilidad de uso y mantenimiento gracias a Spring Boot y H2 Database.
- Mayor eficiencia en la gestión de reservas, clientes y habitaciones.

Este proyecto ofrece una solución versátil para la gestión de hoteles, con la posibilidad de adaptarse tanto a pequeños hoteles que buscan simplicidad como a hoteles más grandes que necesitan una arquitectura de microservicios para gestionar sus operaciones.
