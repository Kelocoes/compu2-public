### Taller: Gestión de Usuarios, Roles y Permisos en Spring Boot con Spring Data JPA
#### Objetivo
En este taller, se busca que el estudiante aprenda e implemente la gestión de usuarios, roles y permisos usando Spring Boot y Spring Data JPA. Crea las entidades necesarias, los repositorios correspondientes con métodos de consulta personalizados (query methods), y los servicios que manejarán la lógica de negocio.

#### Requerimientos
- Crear las entidades Usuario, Trabajador, Rol, y Permiso.
- Crear la interfaz los repositorios para cada entidad.
- Crear servicios que realicen las siguientes operaciones:
  - Guardar trabajadores tanto en la tabla de Usuarios como en la de Trabajadores.
  - Buscar todos los usuarios por un rol específico.
  - Buscar usuarios por email o por Id.
  - Borrar usuarios por Id.
  - Encontrar todos los roles que tienen un permiso en específico.
