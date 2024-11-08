# Proyecto de Gestión de Facturación M6 - Persistencia

Este proyecto es una aplicación en Java diseñada para gestionar facturas, clientes, líneas de productos y productos. Utiliza un diseño basado en el patrón DAO (Data Access Object) para acceder a la base de datos, separando la lógica de negocio de la lógica de persistencia.
Se usa Hibernate como implementador del JPA para el mapeo de las clases.

## Estructura del Proyecto

El proyecto sigue una estructura de paquetes bien definida, organizada en capas lógicas:

### Paquetes

- **logica**: Contiene las clases de los modelos de negocio que representan las entidades de la aplicación:
  - `Client`: Clase que representa un cliente.
  - `Factura`: Clase que representa una factura.
  - `Linia`: Clase que representa una línea de factura (detalle de la factura).
  - `Producte`: Clase que representa un producto.

- **main**: Contiene la clase principal de la aplicación.
  - `Main`: Punto de entrada de la aplicación.

- **persistencia**: Maneja la lógica de acceso a datos y persistencia.
  - **dao**: Contiene las interfaces DAO que definen las operaciones de acceso a datos.
    - `ClientDAO`: Interface para las operaciones CRUD de la entidad `Client`.
    - `FacturaDAO`: Interface para las operaciones CRUD de la entidad `Factura`.
    - `LiniaDAO`: Interface para las operaciones CRUD de la entidad `Linia`.
    - `ProducteDAO`: Interface para las operaciones CRUD de la entidad `Producte`.
  - **daoImpl**: Contiene las implementaciones de las interfaces DAO.
    - `ClientDAOImpl`: Implementación de `ClientDAO`.
    - `FacturaDAOImpl`: Implementación de `FacturaDAO`.
    - `LiniaDAOImpl`: Implementación de `LiniaDAO`.
    - `ProducteDAOImpl`: Implementación de `ProducteDAO`.
  - **exceptions**: Paquete donde se gestionan las excepciones personalizadas.

- **services**: Contiene los servicios que manejan la lógica de negocio y operaciones complejas.
  - `ClientService`: Servicio que maneja la lógica de negocio para los clientes.
  - `FacturaService`: Servicio que maneja la lógica de negocio para las facturas.

- **resources**: Contiene archivos de configuración.
  - **META-INF/persistence.xml**: Archivo de configuración para la conexión a la base de datos usando JPA.

## Requisitos

- **Java**: JDK 8 o superior.
- **Maven**: Para la gestión de dependencias.
- **Base de datos**: Asegúrate de configurar la base de datos correctamente en `persistence.xml`.

## Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/SebastianARG/Practica2Evaluable.git
