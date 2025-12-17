
# 🐉 Microservicio de Gestión de Entrenadores y Pokémon (MongoDB)

Este microservicio proporciona una API RESTful para gestionar **Entrenadores** y **Pokémon**, utilizando una base de datos **MongoDB**. 

El sistema gestiona dos colecciones principales (`entrenadores` y `pokemons`). La relación se establece mediante referencia: cada Pokémon almacena el ID de su entrenador (`entrenador_id`). El proyecto está implementado con **Spring Boot**, **Spring Data MongoDB** y documentación **OpenAPI (Swagger)**.

---

## ✅ Funcionalidades Principales

La API permite realizar las siguientes operaciones:

### Gestión de Entrenadores
- ✅ Crear un nuevo entrenador.
- ✅ Listar todos los entrenadores.
- ✅ Actualizar un entrenador existente.
- ✅ Eliminar un entrenador por su ID.
- ✅ **Serialización/Deserialización manual**: Endpoints específicos para guardar/leer objetos en archivos JSON locales usando Jackson.

### Gestión de Pokémon
- ✅ Registrar un nuevo Pokémon.
- ✅ Listar todos los Pokémon.
- ✅ Obtener el **Entrenador asociado** a un Pokémon específico.
- ✅ Actualizar y eliminar Pokémon por ID.
- ✅ Serialización manual a fichero local.

> Todos los endpoints consumen y devuelven respuestas en formato **JSON**.

---

## 📦 Modelo de Datos

A diferencia de un modelo embebido, aquí se utilizan **referencias por ID**.

### 1. Colección `entrenadores`
Estructura del documento (observa el mapeo del nombre):

```json
{
  "_id": "650c...",
  "nombre_completo": "Ash Ketchum",
  "ciudad": "Pueblo Paleta"
}

```

### 2. Colección `pokemons`

Estructura del documento (incluye la referencia al entrenador):

```json
{
  "_id": "650d...",
  "nombre": "Pikachu",
  "tipos": ["Eléctrico"],
  "nivel": 25,
  "habilidades": ["Impactrueno", "Cola Férrea"],
  "entrenador_id": "650c..." 
}

```

> **Nota:** El campo `entrenador_id` vincula al Pokémon con su Entrenador.

---

## 🧪 Endpoints de la API

### 👤 Entrenadores (`/mongodb/entrenador`)

| Método | Ruta | Descripción |
| --- | --- | --- |
| `POST` | `/mongodb/entrenador/guardar` | Guarda un entrenador en BDD |
| `GET` | `/mongodb/entrenador/listarTodos` | Obtiene la lista completa |
| `POST` | `/mongodb/entrenador/actualizar` | Actualiza un entrenador (requiere ID en body) |
| `DELETE` | `/mongodb/entrenador/eliminar/{id}` | Elimina un entrenador por ID |
| `POST` | `/mongodb/entrenador/serializar/{id}` | Guarda el objeto en un fichero `entrenador.json` |
| `GET` | `/mongodb/entrenador/deserializar` | Lee de `entrenador.json` y actualiza la BDD |

### 🐾 Pokémon (`/mongodb/pokemon`)

| Método | Ruta | Descripción |
| --- | --- | --- |
| `POST` | `/mongodb/pokemon/guardar` | Guarda un pokémon en BDD |
| `GET` | `/mongodb/pokemon/listarTodos` | Obtiene la lista completa |
| `GET` | `/mongodb/pokemon/getEntrenadorDePokemon/{id}` | Devuelve el objeto Entrenador dueño del Pokémon |
| `POST` | `/mongodb/pokemon/actualizar` | Actualiza un pokémon |
| `DELETE` | `/mongodb/pokemon/eliminar/{id}` | Elimina un pokémon por ID |
| `POST` | `/mongodb/pokemon/serializar/{id}` | Guarda el objeto en un fichero `pokemon.json` |
| `GET` | `/mongodb/pokemon/deserializar` | Lee de `pokemon.json` y actualiza la BDD |

---

## 🧭 Ejemplo de uso (JSON Body)

### Crear un Entrenador

**POST** `/mongodb/entrenador/guardar`

```json
{
  "nombre_completo": "Misty",
  "ciudad": "Ciudad Celeste"
}

```

### Crear un Pokémon (Asociado a un Entrenador)

**POST** `/mongodb/pokemon/guardar`

> Primero necesitas el `id` generado del entrenador creado anteriormente.

```json
{
  "nombre": "Starmie",
  "tipos": ["Agua", "Psíquico"],
  "nivel": 45,
  "habilidades": ["Hidrobomba", "Giro Rápido"],
  "entrenador_id": "ID_DEL_ENTRENADOR_AQUI"
}

```

---

## 📚 Documentación API (Swagger)

La API incluye documentación interactiva mediante **Swagger UI**.

Accede directamente desde tu navegador:

🔗 [http://localhost:8080/swagger-ui/swagger-ui/index.html](https://www.google.com/search?q=http://localhost:8080/swagger-ui/swagger-ui/index.html)

> **Nota:** La ruta configurada en este proyecto es ligeramente distinta a la estándar (`/swagger-ui/swagger-ui/index.html`).

---

## 🛠️ Configuración

Archivo `application.properties` incluido en el proyecto:

```properties
app.version=1.0.0
spring.application.nome=PracticaMongoPokemon
spring.data.mongodb.uri=mongodb://10.0.9.118:27017/probas
springdoc.api-docs.enabled=true
springdoc.swagger-ui.enabled=true
springdoc.swagger-ui.path=/swagger-ui/index.html
server.port=8080

```

> La base de datos MongoDB apunta a la IP `10.0.9.118`, base de datos `probas`.

---

## 🧰 Tecnologías utilizadas

* **Lenguaje**: Java 17+
* **Framework**: Spring Boot
* **Base de datos**: MongoDB
* **Persistencia**: Spring Data MongoDB
* **Serialización**: Jackson (Databind & Annotations)
* **Documentación**: Swagger/OpenAPI 3
* **Arquitectura**: MVC (Controlador, Servicio, Repositorio, Modelo)

---

## ▶️ Ejecución

El servidor se iniciará en el puerto **8080**:

```
http://localhost:8080

```

## Mongo DB Compass & Pruebas

### 📊 Capturas de pantalla

*Añade aquí tus capturas de pantalla de la estructura del proyecto o de Compass*

---

> 💡 **Consejo**: Prueba el endpoint `/getEntrenadorDePokemon/{id}` para verificar que la relación entre colecciones funciona correctamente.

```dotenv
http://localhost:8080/swagger-ui/swagger-ui/index.html#/

```
