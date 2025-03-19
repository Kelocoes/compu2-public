# Guía de Etiquetas Thymeleaf y Manipulación de Información en el Controller

## Etiquetas Thymeleaf más Utilizadas en HTML
En este pequeño diccionario podrás encontrar algunas de las anotaciones, configuraciones y etiquetas más utilizadas al trabajar con Thymeleaf.

1. **th:text**: Muestra el contenido de una variable en el HTML.
    ```html
    <p th:text="${variable}">Texto por defecto</p>
    <p>
    ```

2. **th:if / th:unless**: Renderiza el contenido basado en una condición.
    ```html
    <div th:if="${condition}">Contenido si la condición es verdadera</div>
    <div th:unless="${condition}">Contenido si la condición es falsa</div>
    ```

3. **th:each**: Itera sobre una colección de elementos.
    ```html
    <ul>
         <li th:each="item : ${items}" th:text="${item}">Elemento</li>
    </ul>
    ```

4. **th:href**: Genera enlaces dinámicos.
    ```html
    <a th:href="@{/ruta}">Enlace</a>
    ```

5. **th:src**: Establece la fuente de una imagen.
    ```html
    <img th:src="@{/imagenes/logo.png}" alt="Logo">
    ```

6. **th:action**: Define la acción de un formulario.
    ```html
    <form th:action="@{/submit}" method="post">
        <input type="text" name="nombre">
        <button type="submit">Enviar</button>
    </form>
    ``` 

7. **th:value**: Establece el valor de un campo de entrada.
    ```html
    <input type="text" th:value="${variable}">
    ```

## Manipulación de Información en el Controller

Aquí encontrarás algunos ejemplos de cómo manipular información en el controlador de Spring Boot. Recuerda anotar el controlador con la anotación `@Controller` y devolver el nombre de la vista en el método.

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


## Explicación de las diferentes anotaciones que puedes encontrar en Spring MVC

1. **@Controller**: Anotación que indica que la clase es un controlador de Spring MVC, uno de sus parámetros es `value` que permite asignar un nombre al controlador.
    ```java
    @Controller
    public class Controlador {
        ...
    }
    ```

2. **@GetMapping**: Anotación que mapea una petición GET a un método del controlador, uno de sus parámetros es `value` que permite asignar la ruta de la petición.
    ```java
    @GetMapping("/ruta")
    public String metodo() {
        ...
    }
    ```

3. **@PostMapping**: Anotación que mapea una petición POST a un método del controlador, uno de sus parámetros es `value` que permite asignar la ruta de la petición.
    ```java
    @PostMapping("/ruta")
    public String metodo() {
        ...
    }
    ```

4. **@ModelAttribute**: Anotación que enlaza un método con un atributo del modelo, uno de sus parámetros es `value` que permite asignar un nombre al atributo.
    ```java
    @PostMapping("/guardar")
    public String guardarElemento(@ModelAttribute("elemento") Elemento elemento, Model model) {
        ...
    }
    ```
    
    4.1 Si no especificas el nombre del atributo, se utilizará el nombre de la clase en minúsculas.
    ```java
    @PostMapping("/guardar")
    public String guardarElemento(@ModelAttribute Elemento elemento, Model model) {
        ...
    }
    ```

5. **@RequestParam**: Anotación que enlaza un parámetro de la petición con un parámetro del método, uno de sus parámetros es `value` que permite asignar el nombre del parámetro.
    ```java
    @GetMapping("/ruta")
    public String metodo(@RequestParam("parametro") String parametro) {
        ...
    }
    ```

6. **@PathVariable**: Anotación que enlaza una variable de la ruta con un parámetro del método, uno de sus parámetros es `value` que permite asignar el nombre de la variable.
    ```java
    @GetMapping("/ruta/{variable}")
    public String metodo(@PathVariable("variable") String variable) {
        ...
    }
    ```

