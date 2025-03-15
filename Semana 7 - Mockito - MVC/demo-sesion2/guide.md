# Guía de Etiquetas Thymeleaf y Manipulación de Información en el Controller

## Etiquetas Thymeleaf más Utilizadas en HTML

1. **th:text**
    ```html
    <p th:text="${variable}">Texto por defecto</p>
    ```
    - Muestra el contenido de una variable en el HTML.

2. **th:if / th:unless**
    ```html
    <div th:if="${condition}">Contenido si la condición es verdadera</div>
    <div th:unless="${condition}">Contenido si la condición es falsa</div>
    ```
    - Renderiza el contenido basado en una condición.

3. **th:each**
    ```html
    <ul>
         <li th:each="item : ${items}" th:text="${item}">Elemento</li>
    </ul>
    ```
    - Itera sobre una colección de elementos.

4. **th:href**
    ```html
    <a th:href="@{/ruta}">Enlace</a>
    ```
    - Genera enlaces dinámicos.

5. **th:src**
    ```html
    <img th:src="@{/imagenes/logo.png}" alt="Logo">
    ```
    - Establece la fuente de una imagen.

6. **th:action**
    ```html
    <form th:action="@{/submit}" method="post">
         <!-- Campos del formulario -->
    </form>
    ```
    - Define la acción de un formulario.

7. **th:value**
    ```html
    <input type="text" th:value="${variable}">
    ```
    - Establece el valor de un campo de entrada.

## Manipulación de Información en el Controller

1. **Recibir Datos del Formulario**
    ```java
    @PostMapping("/submit")
    public String submitForm(@ModelAttribute("formData") FormData formData, Model model) {
         // Procesar datos del formulario
         model.addAttribute("message", "Datos recibidos correctamente");
         return "resultado";
    }
    ```

2. **Enviar Datos a la Vista**
    ```java
    @GetMapping("/datos")
    public String enviarDatos(Model model) {
         model.addAttribute("variable", "Valor de ejemplo");
         return "vista";
    }
    ```

3. **Redirigir a Otra Página**
    ```java
    @PostMapping("/redirigir")
    public String redirigir() {
         return "redirect:/nuevaRuta";
    }
    ```

4. **Manejo de Listas**
    ```java
    @GetMapping("/lista")
    public String mostrarLista(Model model) {
         List<String> items = Arrays.asList("Item 1", "Item 2", "Item 3");
         model.addAttribute("items", items);
         return "lista";
    }
    ```

5. **Manejo de Condiciones**
    ```java
    @GetMapping("/condicion")
    public String mostrarCondicion(Model model) {
         boolean condition = true;
         model.addAttribute("condition", condition);
         return "condicion";
    }
    ```


## Ejemplo de Formulario con Mensaje de Confirmación

1. **Template con Formulario**
    ```html
    <!DOCTYPE html>
    <html xmlns:th="http://www.thymeleaf.org">
    <head>
        <title>Formulario de Ejemplo</title>
    </head>
    <body>
        <form th:action="@{/guardar}" th:object="${elemento}" method="post">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" th:field="*{nombre}" />
            <button type="submit">Guardar</button>
        </form>
        <p th:if="${mensaje}" th:text="${mensaje}"></p>
    </body>
    </html>
    ```

2. **Controller para Manejar el Formulario**
    ```java
    @Controller
    public class FormularioController {

        @GetMapping("/formulario")
        public String mostrarFormulario(Model model) {
            model.addAttribute("elemento", new Elemento());
            return "formulario";
        }

        @PostMapping("/guardar")
        public String guardarElemento(@ModelAttribute("elemento") Elemento elemento, Model model) {
            try {
                model.addAttribute("mensaje", "Elemento creado correctamente");
            } catch (Exception e) {
                model.addAttribute("mensaje", "Ha pasado un error");
            }
            return "formulario";
        }
    }
    ```

3. **Clase Elemento**
    ```java
    public class Elemento {
        private String nombre;
        
        ...
    }
    ```