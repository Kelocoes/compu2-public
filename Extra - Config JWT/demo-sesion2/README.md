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

#### 4.1. Dependencias necesarias
