### Postman
#### Aspectos importantes para una mejor creación de pruebas

En este documento se describen algunos aspectos importantes a tener en cuenta al momento de crear pruebas en Postman. Esto será util para la mayoría de estudiantes ya que es muy común usar la herramienta de Postman y similares en sus capacidades más básicas. Sin embargo, estas herramientas poseen funcionalidades avanzadas que permiten crear pruebas más complejas, robustas y eficientes. A continuación se describen algunos de estos aspectos:

1. **Uso de variables**: 
   - Postman permite el uso de variables para almacenar valores que pueden ser utilizados en diferentes partes de la colección. Esto es útil para evitar la repetición de valores y facilitar la actualización de los mismos. Por ejemplo, si se tiene una URL base que se utiliza en varias peticiones, se puede almacenar en una variable y utilizarla en lugar de escribirla cada vez.
   - Las variables pueden ser definidas a nivel de colección, carpeta o petición, lo que permite un control más granular sobre su alcance y uso.

2. **Ejecución de una colección**:
   - Postman permite la ejecución de una colección completa de peticiones de forma secuencial. Esto es útil para probar un flujo completo de una API y verificar que todas las peticiones funcionan correctamente en conjunto.
   - Al ejecutar una colección, se pueden utilizar variables para almacenar los resultados de las peticiones y utilizarlos en las siguientes peticiones. Esto permite crear pruebas más complejas y realistas.

3. **Lógica de pre-scripts**:
   - Postman permite la ejecución de scripts antes de enviar una petición. Esto es útil para realizar tareas como la configuración de variables, la manipulación de datos o la validación de condiciones antes de enviar la petición.
   - Los pre-scripts se pueden utilizar para realizar tareas como la autenticación, la generación de tokens o la configuración de headers personalizados.

Ejemplos de uso de pre-scripts:
```javascript
// Ejemplo de pre-script para generar un token de autenticación
pm.environment.set("authToken", pm.response.json().token);

// Ejemplo de pre-script para configurar un header personalizado
pm.request.headers.add({key: "Authorization", value: "Bearer " + pm.environment.get("authToken")});

// Ejemplo de pre-script para validar una condición antes de enviar la petición
if (pm.environment.get("authToken") === undefined) {
    throw new Error("El token de autenticación no está definido");
}
```

4. **Logica de post-scripts**:
   - Postman permite la ejecución de scripts después de recibir una respuesta. Esto es útil para realizar tareas como la validación de respuestas, la manipulación de datos o el almacenamiento de resultados.
    - Los post-scripts se pueden utilizar para realizar tareas como la validación de códigos de estado, la verificación de valores en el cuerpo de la respuesta o el almacenamiento de datos en variables para su uso posterior.
Ejemplos de uso de post-scripts:
```javascript
// Ejemplo de post-script para validar el código de estado de la respuesta
pm.test("El código de estado es 200", function () {
    pm.response.to.have.status(200);
});

// Ejemplo de post-script para validar un valor en el cuerpo de la respuesta
pm.test("El valor de 'id' es 123", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.id).to.eql(123);
});

// Ejemplo de post-script para almacenar un valor en una variable
pm.environment.set("userId", pm.response.json().id);

// Ejemplo de post-script para validar el tiempo de respuesta
pm.test("El tiempo de respuesta es menor a 200ms", function () {
    pm.expect(pm.response.responseTime).to.be.below(200);
});
```