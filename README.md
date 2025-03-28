# Sistema Bancario - Microservicios

Este proyecto implementa un sistema bancario basado en microservicios utilizando Spring Boot.

## Arquitectura

El sistema está compuesto por dos microservicios:

1. **personas-clientes-service**: Gestiona la información de clientes
2. **cuentas-movimientos-service**: Gestiona cuentas y movimientos bancarios

## Tecnologías Utilizadas

- Java 21
- Spring Boot 3.x
- PostgreSQL
- RabbitMQ
- Docker
- Maven

## Requisitos

- Java 21
- Docker y Docker Compose
- Maven

## Instalación y Ejecución

1. Clonar el repositorio:
```bash
git clone https://github.com/tu-usuario/bank-system.git
cd bank-system
```

2. Construir y ejecutar con Docker Compose:
```bash
docker-compose up --build
```

## Endpoints

### Servicio de Clientes (puerto 8081)
- POST /clientes - Crear cliente
- GET /clientes - Listar clientes
- GET /clientes/{id} - Obtener cliente
- PUT /clientes/{id} - Actualizar cliente
- DELETE /clientes/{id} - Eliminar cliente

### Servicio de Cuentas y Movimientos (puerto 8082)
- POST /cuentas - Crear cuenta
- GET /cuentas - Listar cuentas
- GET /cuentas/{id} - Obtener cuenta
- PUT /cuentas/{id} - Actualizar cuenta
- DELETE /cuentas/{id} - Eliminar cuenta

- POST /movimientos - Registrar movimiento
- GET /movimientos/cuenta/{cuentaId} - Listar movimientos por cuenta
- GET /movimientos/{id} - Obtener movimiento
- DELETE /movimientos/{id} - Eliminar movimiento

- GET /reportes - Generar reporte de estado de cuenta

## Pruebas

### Pruebas Unitarias
```bash
mvn test
```

### Pruebas de Integración
```bash
mvn verify
```

## Estructura del Proyecto

```
bank-system/
├── personas-clientes-service/
│   ├── src/
│   └── pom.xml
├── cuentas-movimientos-service/
│   ├── src/
│   └── pom.xml
├── docker-compose.yml
├── Dockerfile-personas
├── Dockerfile-cuentas
└── pom.xml
```

---

## 🐳 Despliegue con Docker

1. Clona el repositorio:

```bash
git clone https://github.com/tu-usuario/bank-system.git
cd bank-system
