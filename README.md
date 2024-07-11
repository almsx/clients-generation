## 🚀 Clients Generation - API

## Instrucciones de Ejecución.

1. Clonar el Repositorio

```bash
git clone git@github.com:almsx/clients-generation.git
```

2. Ejecutar el siguiente comando para poder levantar el API.

```bash
docker-compose up --build
```

## 🚀 En caso de error ...

En caso de que haya un error con MySQL, comparto las siguientes instrucciones.

1. Ingresar a la Consola de MySQL usando Docker

```bash
docker exec -it mysql mysql -u root -p
```

Y se ejecutan los siguientes comandos:

```bash

ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'appbuttons';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';
FLUSH PRIVILEGES;
exit;
```

2. Reiniciar el Contenedor de MySQL

```bash
docker-compose down
docker-compose up -d
```

## 🌱 API Documentation Endpoints

### 👋 Crear nuevo Cliente

**URL**: `http://localhost:8080/api/clients`

**Method**: `POST`

**Content-Type**: `application/json`

**Description**: Este endpoint permite registrar un nuevo cliente en el sistema y devuelve la información del cliente registrada.

**Request Body**:

```json
{
  "name": "Alberto",
  "lastName": "Luebbert",
  "email": "alberto@ideashappy.com",
  "phone": "5511121314"
}
```

### 👋 Obtener todos los Clientes

**URL**: `http://localhost:8080/api/clients`

**Method**: `GET`

**Content-Type**: `application/json`

**Description**: Este endpoint permite obtener todos los registros de clientes almacenados en BD.

### 👋 Obtener un Cliente por número de id

**URL**: `http://localhost:8080/api/clients/${id}`

**Method**: `GET`

**Content-Type**: `application/json`

**Description**: Este endpoint permite obtener un cliente registrado en la base de datos.

### 👋 Actualizar un Cliente existente

**URL**: `http://localhost:8080/api/clients/${id}`

**Method**: `PUT`

**Content-Type**: `application/json`

**Description**: Este endpoint permite actualizar parcialmente un registro de un cliente existente.

**Request Body**:

```json
{
  "name": "Pedro",
  "lastName": "López",
  "email": "pedro@ideashappy.com",
  "phone": "5512131415"
}
```

### 👋 Eliminar un Cliente existente

**URL**: `http://localhost:8080/api/clients/${id}`

**Method**: `DELETE`

**Content-Type**: `application/json`

**Description**: Este endpoint permite eliminar el registro de un cliente existente.

Happy Code 📬
