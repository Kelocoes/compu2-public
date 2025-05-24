# Semana 14: Side Effects en React

Los **side effects** (efectos secundarios) en React se refieren a cualquier operación que afecte algo fuera del alcance de la función que se está ejecutando. Esto incluye tareas como:

- Llamadas a APIs.
- Manipulación directa del DOM.
- Subscripciones a eventos.
- Configuración de temporizadores.

En React, los side effects se manejan principalmente utilizando el hook `useEffect`. Este hook permite ejecutar código después de que el componente se haya renderizado. Ejemplo básico:

```jsx
import React, { useEffect, useState } from 'react';

function EjemploSideEffect() {
    const [contador, setContador] = useState(0);

    useEffect(() => {
        console.log(`El contador cambió a: ${contador}`);
    }, [contador]); // Se ejecuta solo cuando 'contador' cambia.

    return (
        <div>
            <p>Contador: {contador}</p>
            <button onClick={() => setContador(contador + 1)}>Incrementar</button>
        </div>
    );
}

export default EjemploSideEffect;
```

### Consideraciones importantes

1. **Dependencias del efecto:** Especificar correctamente las dependencias en el array del segundo argumento de `useEffect` es crucial para evitar comportamientos inesperados.
2. **Limpieza de efectos:** Si el efecto crea recursos (como subscripciones o temporizadores), es importante limpiarlos para evitar fugas de memoria. Ejemplo:

```jsx
useEffect(() => {
    const id = setInterval(() => {
        console.log('Intervalo activo');
    }, 1000);

    return () => clearInterval(id); // Limpieza del intervalo.
}, []);
```

Los side effects son esenciales para manejar interacciones con el mundo exterior en aplicaciones React, pero deben usarse con cuidado para mantener el código predecible y eficiente.

### Comandos y enlaces relevantes

https://reactrouter.com/home
https://mui.com

```bash
    npm i react-router
    npm i @mui/material @emotion/react @emotion/styled @mui/icons-material
```