# Guía de REST

## ¿Qué es REST?
REST (Representational State Transfer) es un estilo arquitectónico para diseñar servicios web. Fue introducido en el año 2000. REST se basa en un conjunto de principios que permiten la comunicación entre sistemas distribuidos utilizando HTTP como protocolo principal.

### Principios de REST:
1. **Cliente-Servidor**: Separación de responsabilidades entre cliente y servidor.
2. **Sin estado (Stateless)**: Cada solicitud del cliente al servidor debe contener toda la información necesaria para procesarla.
3. **Cacheable**: Las respuestas deben indicar si son cacheables o no para mejorar el rendimiento.
4. **Interfaz uniforme**: Uso de métodos HTTP estándar (GET, POST, PUT, DELETE, etc.) y recursos identificados por URIs.
5. **Sistema en capas**: La arquitectura puede estar compuesta por capas que interactúan entre sí.
6. **Código bajo demanda (opcional)**: El servidor puede enviar código ejecutable al cliente (como scripts).

---

## Stateless vs Stateful

### **Stateless (Sin estado)**
Un sistema o servicio es stateless cuando no guarda información sobre el estado de las interacciones previas entre el cliente y el servidor. Cada solicitud es independiente.

#### Ventajas:
- Escalabilidad: Es más fácil escalar porque no se necesita mantener sesiones.
- Simplicidad: Menor complejidad al no gestionar estados.
- Tolerancia a fallos: Si un servidor falla, otro puede manejar la solicitud sin problemas.

#### Desventajas:
- Mayor carga en las solicitudes: Cada solicitud debe incluir toda la información necesaria.
- Menor eficiencia en interacciones complejas: Puede requerir más datos redundantes.

---

### **Stateful (Con estado)**
Un sistema o servicio es stateful cuando guarda información sobre el estado de las interacciones previas. Esto permite que las solicitudes dependan de un contexto compartido.

#### Ventajas:
- Interacciones más eficientes: No es necesario enviar toda la información en cada solicitud.
- Experiencia personalizada: Permite mantener sesiones y estados específicos para cada usuario.

#### Desventajas:
- Menor escalabilidad: Requiere más recursos para mantener estados.
- Complejidad: Es más difícil de implementar y mantener.
- Dependencia del servidor: Si un servidor falla, el estado puede perderse.

---

## Diferencias clave entre Stateless y Stateful
| Característica       | Stateless                     | Stateful                      |
|----------------------|-------------------------------|-------------------------------|
| **Estado**           | No guarda estado             | Guarda estado                 |
| **Escalabilidad**    | Alta                          | Baja                          |
| **Complejidad**      | Baja                          | Alta                          |
| **Tolerancia a fallos** | Alta                       | Baja                          |
| **Eficiencia**       | Menor en interacciones complejas | Mayor en interacciones complejas |

---