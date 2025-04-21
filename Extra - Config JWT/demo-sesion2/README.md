### Guía para de implementación de JWT en Spring Boot

En esta guía podrás encontrar diferentes pasos que te serán útiles en el proceso de implementación de JWT en tu proyecto de Spring Boot. Adicionalmente a esto, se formalizaran algunos aspectos que son necesarios para tener una seguridad adecuada dentro de nuestra aplicación.

### 1. ¿Qué es JWT?

JWT (JSON Web Token) es un estándar abierto (RFC 7519) que define un formato compacto y autónomo para transmitir información de forma segura entre las partes como un objeto JSON. Esta información puede ser verificada y confiable porque está firmada digitalmente. JWT se puede firmar utilizando un secreto (con el algoritmo HMAC) o un par de claves pública/privada utilizando RSA o ECDSA.<br>
JWT se utiliza comúnmente para la autenticación y autorización en aplicaciones web y móviles. Permite a los servidores generar tokens que pueden ser enviados al cliente y luego utilizados para autenticar solicitudes posteriores. Esto elimina la necesidad de enviar credenciales de usuario en cada solicitud, lo que mejora la seguridad y la eficiencia.<br>

### 2. ¿Por qué usar JWT?

- **Autenticación**: JWT es una forma de autenticar a los usuarios en una aplicación. Una vez que el usuario inicia sesión, el servidor genera un token JWT y lo envía al cliente. El cliente almacena este token y lo envía en cada solicitud posterior para acceder a recursos protegidos.

- **Autorización**: JWT también se utiliza para autorizar el acceso a recursos específicos. El token puede contener información sobre los permisos del usuario, lo que permite al servidor verificar si el usuario tiene acceso a un recurso determinado.

- **Interoperabilidad**: JWT es un estándar abierto y se puede utilizar en diferentes plataformas y lenguajes de programación. Esto facilita la integración entre diferentes sistemas y aplicaciones.

- **Compacto**: JWT es un formato compacto que se puede enviar a través de URL, encabezados HTTP o en el cuerpo de una solicitud. Esto lo hace ideal para aplicaciones móviles y web donde el tamaño del token es importante.

- **Autónomo**: JWT es autónomo, lo que significa que contiene toda la información necesaria para verificar su validez. Esto reduce la necesidad de realizar consultas a una base de datos para verificar la autenticidad del token.

### 3. Partes de un token JWT

Un token JWT consta de tres partes separadas por puntos (.):
1. **Header (Encabezado)**: El encabezado generalmente consta de dos partes: el tipo de token, que es JWT, y el algoritmo de firma que se utiliza, como HMAC SHA256 o RSA.

2. **Payload (Carga útil)**: La carga útil contiene las afirmaciones (claims) que son declaraciones sobre una entidad (generalmente, el usuario) y datos adicionales. Existen tres tipos de afirmaciones:
   - **Registered claims**: Son un conjunto de afirmaciones predefinidas que no son obligatorias pero recomendadas, como `iss` (emisor), `exp` (fecha de expiración), `sub` (sujeto), etc.
   - **Public claims**: Son afirmaciones definidas por los usuarios que pueden ser utilizadas por diferentes partes. Para evitar colisiones, deben ser definidas en el IANA JSON Web Token Registry o ser definidas como un URI.
   - **Private claims**: Son afirmaciones personalizadas creadas para compartir información entre partes que acuerdan usarlas.

3. **Signature (Firma)**: Para crear la firma, tomamos el encabezado codificado en Base64Url y la carga útil codificada en Base64Url, los unimos con un punto (.) y firmamos el resultado utilizando el algoritmo especificado en el encabezado y una clave secreta. La firma se utiliza para verificar que el remitente del JWT es quien dice ser y para garantizar que el mensaje no haya sido alterado.


### 4. Implementación de JWT en Spring Boot

Esta guía se enfocará en el proceso de generación y verificación de un accesToken usando JWT, por lo que aspectos de token de refresco no serán tratados en esta guía, sin embargo, su implementación no es complicada y puede ser realizada de forma similar a la implementación del accesToken.

### 4.0 Proyecto base

Si deseas comenzar con el proyecto trabajado en este curso, puedes descargarlo por medio de este [enlace](https://drive.google.com/file/d/17hJJ6hep5PHRg8BF6Dxmkakgk7-Pbbqw/view?usp=sharing).

#### 4.1. Dependencias necesarias

Las siguientes dependencias son necesarias para la implementación de JWT en Spring Boot:

```xml
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>0.12.3</version>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-impl</artifactId>
        <version>0.12.6</version>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-jackson</artifactId>
        <version>0.12.6</version>
    </dependency>
```

Recuerda que si deseas instalar dichas dependencias en el contexto de maven puedes hacerlo por medio de la siguiente línea de comandos:

```bash
    mvn clean install
```

#### 4.2. Adición de variables de entorno propias de JWT

Para la implementación de JWT es necesario contar con una clave secreta, la cual será utilizada para la firma del token. Adicionalmente, es recomendable contar con un tiempo de expiración para el token, el cual puede ser configurado en milisegundos. Para esto, se recomienda crear un archivo `application.properties` o `application.yml` en la carpeta `src/main/resources` y agregar las siguientes propiedades:

```properties
    ### JWT example
    app.security.jwt.secret-key = mySecretKey
    # 1 Day
    app.security.jwt.expiration-time = 86400000
    app.security.jwt.token-prefix = Bearer
    app.security.jwt.header-string = Authorization
```

#### 4.3 Creación de controller para procesos de autenticación usando JWT

Los endpoints principales para obtener un token de autenticación serán de inicio de sesión y registro, por lo que es importante crear un controlador público (exento de los filtros de security) que permita la creación de un token JWT. Para esto, se recomienda crear un controlador `AuthController` que contenga los siguientes métodos:

```java
    @RestController
    @RequestMapping("/customAuth/api/public/auth")
    public class AuthController {
        
        @Autowired
        private IAuthService authService;

        @PostMapping("/register")
        public ResponseEntity<TokenResponseDTO> register(@RequestBody RegisterRequestDTO request) {
            TokenResponseDTO token = authService.register(request);
            return ResponseEntity.ok(token);
        }

        @PostMapping("/login")
        public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginRequestDTO request) {
            TokenResponseDTO token = authService.login(request);
            return ResponseEntity.ok(token);
        }
    }
```

#### 4.4 Creación de DTOs para el proceso de autenticación

Para el proceso de autenticación, es necesario crear dos DTOs que permitan la creación de un token JWT. Estos DTOs son `LoginRequestDTO`, `TokenResponseDTO` y `RegisterRequestDTO`. A continuación se presentan los DTOs necesarios para el proceso de autenticación:

```java
    @Getter
    @Setter
    @AllArgsConstructor
    public class TokenResponseDTO{
        
        String accessToken;
        String tokenType;
    }

    @Getter
    @Setter
    public class LoginRequestDto {
        private String username;
        private String password;
    }

    @Getter
    @Setter
    public class RegisterRequestDTO {
    
        private String username;
        private String email;
        private String password;
        private String name;
        private String lastname;
        private String documentId;
    }
```

#### 4.5 Creación de servicio para el proceso de autenticación

Posterior a especificar una base de controladores y respuestas esperadas, es necesario crear el servicio de autenticación, el cual se encargará de realizar los procesos de generación de tokens, guardado de información en base de datos y validación de campos.

```java
    public interface IAuthService {

        TokenResponseDTO register(RegisterRequestDTO request);

        TokenResponseDTO login(LoginRequestDTO request);
    }

    @Service
    public class AuthService implements IAuthService {

        @Override
        public TokenResponseDTO register(RegisterRequestDTO request) {
            // Logic for user registration
            return new TokenResponseDTO();
        }

        @Override
        public TokenResponseDTO login(LoginRequestDTO request) {
            // Logic for user login
            return new TokenResponseDTO();
        }
        
    }
```

##### 4.5.1 Implementación de la lógica de registro

Primero empezaremos por el apartado de registro, en el cual se validará la información del usuario y se generará un token JWT. Para esto, es necesario crear un método `register` que realice las siguientes acciones:
- Validar la información del usuario. (By yourself)
- Crear un nuevo usuario en la base de datos.
- Generar un token JWT.

```java
    @Service
    @AllArgsConstructor
    public class AuthService implements IAuthService {

        private final IUserService userService;

        private final IWorkerService workerService;

        private final JwtService jwtService;

        private final PasswordEncoder passwordEncoder;

        private final AuthenticationManager authenticationManager;

        @Override
        public TokenResponseDTO register(RegisterRequestDTO request) {
            User user = new User();
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));

            Worker worker = new Worker();
            worker.setName(request.getName());
            worker.setLastname(request.getLastname());
            worker.setDocumentId(request.getDocumentId());
            worker.setUser(user);

            User userSaved = userService.save(user);
            Worker workerSaved = workerService.save(worker);

            String accessToken = jwtService.generateAccessToken(userSaved, workerSaved);

            return new TokenResponseDTO(accessToken, "Bearer");
        }
    }
```

Por supuesto que es necesario realizar el almacenamiento de la información de registro en la base de datos, por lo que usaremos las dependencias de `UserService` y `WorkerService`. Adicional a esto, es un buen momento de empezar a guardar las contraseñas encriptadas, por lo que la dependencia de `PasswordEncoder` será utilizada al momento de asignar la contraseña del usuario a crear. Para esto, es necesario crear un `@Bean` en la clase de configuración de seguridad que permita la creación del `PasswordEncoder`:

```java
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
``` 

##### 4.5.2 Implementación de la lógica de inicio de sesión
Para el inicio de sesión, es necesario crear un método `login` que realice las siguientes acciones:
- Validar la información del usuario. (By yourself)
- Verificar la contraseña del usuario.
- Generar un token JWT.

```java
    public TokenResponseDTO login(LoginRequestDTO request) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userService.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

        Worker worker = workerService.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Worker not found"));
        String accessToken = jwtService.generateAccessToken(user, worker, auth);

        return new TokenResponseDTO(accessToken, "Bearer");
    }
```

#### 4.6 Creación del servicio de JWT
El servicio de JWT se encargará de la generación y validación del token JWT. Para esto, es necesario crear una interfaz `IJwtService` y una implementación `JwtService` que contenga los siguientes métodos:
```java
    public interface IJwtService {
        String generateAccessToken(User user, Worker worker, Authentication auth);
        String extractUsername(String token);
        boolean isTokenExpired(String token);
    }

    @Service
    public class JwtService implements IJwtService {

        @Value("${app.security.jwt.secret-key}")
        private String secretKey;

        @Value("${app.security.jwt.expiration-time}")
        private long expirationTime;

        @Override
        public String generateAccessToken(User user, Worker worker, Authentication auth) {
            return buildToken(user, worker, expirationTime, auth);
        }

        private String buildToken(User user, Worker worker, long expirationTime, Authentication auth) {
            return Jwts.builder()
                .id(user.getId().toString())
                .claims(Map.of(
                    "username", user.getUsername(),
                    "email", user.getEmail(),
                    "name", worker.getName(),
                    "lastname", worker.getLastname(),
                    "documentId", worker.getDocumentId(),
                    "userId", user.getId().toString(),
                    "workerId", worker.getId().toString(),
                    "authorities", auth != null && auth.getAuthorities() != null ? auth.getAuthorities().stream()
                        .map(ga -> ga.getAuthority())
                        .toList() : List.of()
                ))
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSignInKey())
                .compact();
        }

        private SecretKey getSignInKey() {
            byte[] keyBytes = Decoders.BASE64.decode(secretKey);
            return Keys.hmacShaKeyFor(keyBytes);
        }

        @Override
        public String extractUsername(String token) {
            return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        }

        @Override
        public boolean isTokenExpired(String token) {
            return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
        }
    }
```

#### 4.7 Modificación de los filtros de seguridad para enpoints públicos

Para que los endpoints de autenticación sean públicos, es necesario modificar la configuración de seguridad para permitir el acceso a estos endpoints sin necesidad de autenticación. Para esto, es necesario modificar la clase `SecurityConfig` y agregar los endpoints públicos en el método `authorizeHttpRequests`:

Ejemplo:
```java
    .authorizeHttpRequests(auth -> auth
        .requestMatchers("/public/**").permitAll() // Permitir acceso a rutas públicas
        .anyRequest().authenticated() // Todas las rutas bajo /customAuth/** requieren autenticación
    )
```

#### 4.8 Prueba de los endpoints de autenticación
Para probar los endpoints de autenticación, puedes usar Postman o cualquier otra herramienta de prueba de API. A continuación se presentan ejemplos de cómo realizar las pruebas:

```curl
    curl --location 'http://localhost:8080/customAuth/api/public/auth/register' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "username": "exampleUser",
    "email": "example@example.com",
    "password": "password",
    "name": "Kevin",
    "lastname": "Test",
    "documentId": "123456789"
    }'
```

Y la respuesta nos genera este JSON: 

```json
    {
        "accessToken": "eyJhbGciOiJIUzM4NCJ9.eyJqdGkiOiIzIiwid29ya2VySWQiOiIzIiwibGFzdG5hbWUiOiJUZXN0IiwidXNlcklkIjoiMyIsInVzZXJuYW1lIjoiZXhhbXBsZVVzZXIiLCJkb2N1bWVudElkIjoiMTIzNDU2Nzg5IiwibmFtZSI6IktldmluIiwiZW1haWwiOiJleGFtcGxlQGV4YW1wbGUuY29tIiwic3ViIjoiZXhhbXBsZVVzZXIiLCJpYXQiOjE3NDQ0OTE2OTMsImV4cCI6MTc0NDU3ODA5M30.sIq7iII6SsBIWl6M8fEWvry3XBh2k1bdHpBEphiRyVD7HwOypF9S3uPdvZvK7Smj",
        "tokenType": "Bearer"
    }
```

Para tener un mayor orden y actualizar las contraseñas de admin y user iniciales, en el archivo `data.sql` vamos a cambiarlo: 

```sql
    -- Inserts for user
    INSERT INTO users(username, password, email) VALUES ('user1', '$2a$12$mZiXumOgpAwjveMC6o2iy.rn31Tr9GJ5w2ErRa9My7kZzZa/2fo1y', 'email@email.com');
    INSERT INTO users(username, password, email) VALUES ('admin1', '$2a$12$mZiXumOgpAwjveMC6o2iy.rn31Tr9GJ5w2ErRa9My7kZzZa/2fo1y', 'emailuser@email.com');
```

Si deseas validar el endpoint de Login, puedes usar el siguiente comando:

```curl
    curl --location 'http://localhost:8080/customAuth/api/public/auth/login' \
    --header 'Content-Type: application/json' \
    --data '{
        "username": "exampleUser",
        "password": "password"
    }'
```

De esta manera si el usuario ingresa correctamente, se le generará un token JWT que podrá ser utilizado para acceder a los recursos protegidos de la aplicación. De otro modo recibirá un error 401 (Unauthorized) indicando que las credenciales son incorrectas.

#### 4.9 Uso de token JWT en endpoints restringidos

Combinando los filtros que actualmente existen en la aplicación, vamos a proteger los endpoints de la aplicación para que solo puedan ser accedidos por usuarios autenticados. Anteriormente ya teníamos 3 formas de autenticarnos: Por BasicAuth, por un formulario y por medio de una api key, ahora agregaremos un provider para que pueda autenticarse por medio de un token JWT.

Si a este punto tenemos la creación de nuestro propio authentication manager y los respectivos providers, solamente necesitaremos modificar dicha lógica y agregar el nuevo método de autenticación.

##### 4.9.1 Modificación del authenticationFilter actual

```java
    @Component
    public class CustomAuthenticationFilter extends OncePerRequestFilter {

        @Autowired
        private CustomAuthenticationManager customAuthenticationManager;

        @Value("${app.security.jwt.token-prefix}")
        private String tokenPrefix;

        @Value("${app.security.jwt.header-string}")
        private String headerString;

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {

            Authentication authentication = null;

            String key = request.getHeader("key");
            if (key != null) {
                // Custom authentication logic with api key
                CustomAuthentication customAuthentication = new CustomAuthentication(false, key);
                authentication = customAuthenticationManager.authenticate(customAuthentication);
            } else if (request.getHeader(headerString) != null && request.getHeader(headerString).startsWith(tokenPrefix)) {
                // Custom authentication logic with JWT
                String token = request.getHeader(headerString).substring(tokenPrefix.length());
                CustomAuthenticationJwt customAuthentication = new CustomAuthenticationJwt(true, token);
                authentication = customAuthenticationManager.authenticate(customAuthentication);
            }

            if (authentication != null && authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);
        }
    }
```

##### 4.9.2 Modificación del authenticationManager actual

```java
    @Component
    @AllArgsConstructor
    public class CustomAuthenticationManager implements AuthenticationManager {

        private final CustomAuthenticationProvider customAuthenticationProvider;
        private final DaoAuthenticationProvider daoAuthenticationProvider;
        private final CustomAuthenticationJwtProvider customAuthenticationJwtProvider;

        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            // Si el tipo de autenticación es compatible con el CustomAuthenticationProvider, lo utilizamos
            if (customAuthenticationProvider.supports(authentication.getClass())) {
                return customAuthenticationProvider.authenticate(authentication);
            }
            // Si es compatible con el DaoAuthenticationProvider, lo utilizamos
            else if (daoAuthenticationProvider.supports(authentication.getClass())) {
                return daoAuthenticationProvider.authenticate(authentication);
            }
            // Si es compatible con el CustomAuthenticationJwtProvider, lo utilizamos
            else if (customAuthenticationJwtProvider.supports(authentication.getClass())) {
                return customAuthenticationJwtProvider.authenticate(authentication);
            }
            // Si ningún proveedor soporta la autenticación, lanzamos una excepción
            throw new BadCredentialsException("No se pudo autenticar");
        }
    }
```

##### 4.9.3 Creación de clase Authentication para JWT

Este apartado es opcional en muchas de las configuraciones de seguridad en JWT, pero en nuestro caso es viable ya que hicimos nuestro propio AuthenticationManager con sus respectivos AuthenticationProviders.

```java
    @AllArgsConstructor
    @Setter
    @Getter
    public class CustomAuthenticationJwt implements Authentication{

        private final boolean authentication;
        private final String token;

        @Override
        public boolean isAuthenticated() {
            return authentication;
        }
        

        @Override
        public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
            
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public Object getCredentials() {
            return null;
        }

        @Override
        public Object getDetails() {
            return null;
        }

        @Override
        public Object getPrincipal() {
            return null;
        }

    }
```

##### 4.9.4 Creación del authenticationProvider

```java
    @Component
    public class CustomAuthenticationJwtProvider implements AuthenticationProvider{
        
        @Autowired
        private IJwtService jwtService;

        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException{
            // Verificamos si el token es válido
            String token = ((CustomAuthenticationJwt) authentication).getToken().replace("Bearer ", "").trim();


            String username = jwtService.extractUsername(token);
            if (username != null && !jwtService.isTokenExpired(token)) {
                // Si el token es válido, devolvemos la autenticación
                return new CustomAuthenticationJwt(true, token);
            }

            throw new BadCredentialsException("Malas credenciales en JWT!!");
        }
        
        @Override 
        public boolean supports(Class<?> authentication) {
            // De esta manera el CustomAuthenticationManager sabrá cuál AuthProvider usar
            return CustomAuthenticationJwt.class.equals(authentication); 
        }
        
    }
```

Con lo anterior, ya es posible probar los endpoints que anteriormente requerían una API Key, utilizando ahora un token JWT válido y no expirado.

Es importante tener en cuenta que, en escenarios donde no se implementan múltiples métodos de autenticación, pueden omitirse varios de los pasos descritos en esta y en la guía anterior.

Finalmente, queda como responsabilidad del estudiante implementar los apartados restantes relacionados con la autorización y la restricción de rutas con base en los roles o authorities definidos.