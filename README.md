## LibreriaGenerarPassword
integrantes:
Nuñez Reyes Jorge Emilio 
Antonio Contreras Alan
Cruz Gallegos Julio Gabriel



# bibliotecacontrasena

Librería Java para la generación y validación de contraseñas seguras. 
Proporciona funciones útiles para generar claves aleatorias, verificar su seguridad, eliminar espacios innecesarios y mostrar resultados visuales mediante colores (útil para interfaces gráficas).


## Propósito

Esta librería está diseñada para:

- Generar contraseñas seguras y aleatorias.
- Validar el nivel de seguridad de contraseñas introducidas por el usuario.
- Limpiar espacios en blanco que puedan alterar la validación.
- Integrarse fácilmente en aplicaciones con interfaz gráfica gracias al uso de mensajes y colores.

Es ideal para ser utilizado en sistemas de login, gestores de contraseñas o cualquier aplicación donde la protección de información sensible sea una prioridad.

---

##Explicación del Código

### Clase principal: `generarContrasena`

Contiene todos los métodos estáticos para generar, validar y limpiar contraseñas.

---

### 🔧 Métodos

## Método `generaPassword(int length)`

Este método genera una contraseña segura y aleatoria, compuesta por letras (mayúsculas y minúsculas), números y símbolos especiales.


**private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";**

- Define una **constante de clase** con todos los caracteres posibles que puede contener una contraseña.
- Incluye:
  - Letras mayúsculas (`A-Z`)
  - Letras minúsculas (`a-z`)
  - Dígitos (`0-9`)
  - Símbolos especiales (`!@#$%^&*()`)


**private static final Random random = new Random();**

- Crea un generador de números aleatorios de tipo `Random`, usado para seleccionar caracteres al azar de la cadena `CHARACTERS`.


**public static String generaPassword(int length) {**

- Define el método `generaPassword`, que recibe como parámetro un número entero `length` (longitud deseada de la contraseña).
- Es `public` (accesible desde fuera de la clase) y `static` (no requiere crear una instancia para llamarlo).
- Retorna un `String` que representa la contraseña generada.


    **if (length < 8) { length = 8; }**

- Verifica si la longitud deseada es menor a 8.
- Si es así, la ajusta automáticamente a 8 para garantizar una seguridad mínima.


    **StringBuilder password = new StringBuilder(length);**

- Crea un `StringBuilder` optimizado para construir cadenas de texto dinámicamente.
- Se inicializa con capacidad para `length` caracteres, lo cual mejora el rendimiento al evitar redimensionamientos innecesarios.


    **for (int i = 0; i < length; i++) {**
        **password.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))); }**

- Bucle que se ejecuta `length` veces.
- En cada iteración:
  - `random.nextInt(CHARACTERS.length())` genera un índice aleatorio dentro del rango de la cadena `CHARACTERS`.
  - `CHARACTERS.charAt(...)` obtiene el carácter en esa posición.
  - `password.append(...)` agrega ese carácter al final del `StringBuilder`.


    **return password.toString(); }**

- Convierte el contenido del `StringBuilder` en un `String`.
- Devuelve la contraseña final generada.

---
##️ Método `seguridadContraseña(String password)`

Este método evalúa la seguridad de una contraseña proporcionada por el usuario y devuelve un objeto `resultadoValidacion`, que incluye un mensaje y un color visual (útil para interfaces gráficas).

**public static resultadoValidacion seguridadContraseña(String password) {**
y retornar un objeto `Método público y estático que recibe una cadena password y retorna un objeto resultadoValidacion.


**if (password.length() > 8) {**
    **return new resultadoValidacion("Contraseña INVÁLIDA: Solo 8 caracteres", Color.RED);}**
Si la contraseña tiene más de 8 caracteres, se considera inválida (por regla de negocio interna).

Devuelve un mensaje de advertencia con color rojo.


**else if (password.length() < 4) {**
   **return new resultadoValidacion("Contraseña insegura", Color.RED);}**
Si tiene menos de 4 caracteres, se considera muy insegura.

Devuelve un mensaje rojo de alerta.

**else if (password.length() < 8) {**
    **return new resultadoValidacion("Contraseña poco segura", Color.ORANGE);**}
Si tiene entre 4 y 7 caracteres, se considera poco seguro.

Devuelve el mensaje naranja.

**else {**
   **if (cumpleRequisitos(password)) {**
        **return new resultadoValidacion("Contraseña segura", Color.GREEN);**
    **} else {**
       *** return new resultadoValidacion("Contraseña insegura: le faltan requisitos", Color.ORANGE); }}**
Si tiene exactamente 8 caracteres, se valida si cumple con los requisitos (mayúscula, minúscula, número y símbolo).

Si cumple: contraseña segura (verde).

Si no cumple: insegura, faltan requisitos (naranja).
---

## Método `cumpleRequisitos(String contraseña)`

Este método verifica si una contraseña contiene al menos:

- Una letra mayúscula
- Una letra minúscula
- Un número
- Un símbolo especial (`!@#$%^&*()`)

**public static boolean cumpleRequisitos(String contraseña) {**
( si cumplesi no ) .Método público y estático que recibe una cadena y retorna un boolean( truesi cumple todos los requisitos, falsesi no).

boolean tieneMayus = false;
boolean tieneMinus = false;
boolean tieneNumero = false;
boolean tieneCaracter = false;
Variables auxiliares para verificar si la contraseña contiene al menos un carácter de cada tipo.


for (int i = 0; i < contraseña.length(); i++) {
    char c = contraseña.charAt(i);
Registre carácter por carácter de la contraseña y guarde cada uno en la variable c.


if (Character.isUpperCase(c)) {
    tieneMayus = true;
} else if (Character.isLowerCase(c)) {
    tieneMinus = true;
} else if (Character.isDigit(c)) {
    tieneNumero = true;
} else if ("!@#$%^&*()".indexOf(c) >= 0) {
    tieneCaracter = true;
}
Verifica el tipo de cada carácter:

Si es mayúscula →tieneMayus = true

Si es minúscula →tieneMinus = true

Si es número →tieneNumero = true

Si es un símbolo especial →tieneCaracter = true


if (tieneMayus && tieneMinus && tieneNumero && tieneCaracter) {
    return true;
}
Si ya se han encontrado todos los requisitos, retorna trueinmediatamente (optimización).


} return tieneMayus && tieneMinus && tieneNumero && tieneCaracter;
Si el bucle termina, verifica si efectivamente todos los requisitos están presentes.

Devuelve truesi se cumplen todos; de lo contrario, false.

---

## MétodolimpiarEspacios(String contraseña)
Este método elimina los espacios en blanco de una contraseña para asegurar que no afecte la validación de seguridad.


public static String limpiarEspacios(String contraseña) {
    if (contraseña.contains(" ")) {
        return contraseña.replace(" ", "");
    }
    return contraseña;
}

**public static String limpiarEspacios(String contraseña)**
Defina un método público y estático que recibe como parámetro una cadena de texto ( contraseña) y devuelve otra cadena sin espacios.

**if (contraseña.contains(" "))**
Verifica si la contraseña contiene al menos un espacio en blanco.

**return contraseña.replace(" ", "");**
Si contiene espacios, los elimina utilizando replace(" ", ""), reemplazándolos por nada (es decir, los borra).

**return contraseña;**
Si no contiene espacios, simplemente devuelve la misma contraseña sin modificar.

Este método es útil para asegurar que los espacios accidentales (como los que pueden agregarse al copiar y pegar) no afectan la validación de seguridad.

---

### Clase interna: `resultadoValidacion`

Encapsula los resultados de la validación de seguridad de contraseñas.

- **Atributos:**
  - `mensaje`: Describe el nivel de seguridad.
  - `color`: Objeto `Color` (de `java.awt`) para representar visualmente el resultado.
- **Métodos:**
  - `getMensaje()`
  - `getColor()`

**public static class resultadoValidacion**
Defina una clase interna y estática que se puede utilizar sin necesidad de crear una instancia de la clase externa.

**private String mensaje;**
Variable privada que almacena el mensaje de validación (por ejemplo: "Contraseña segura").

**, etcprivate Color color;**
Variable privada que almacena un color asociado al resultado (como Color.RED, Color.GREEN, etc.).

**public resultadoValidacion(String mensaje, Color color)**
Constructor que recibe el mensaje y el color, y los asigna a las variables internas.

**public String getMensaje()**
Método que devuelve el mensaje de validación.

**public Color getColor()**
Método que devuelve el color correspondiente al resultado de la validación.

Esta clase es una forma práctica de agrupar varios valores relacionados con el resultado de una validación de estructura manerada y clara.

----
#### Eventos de la Interfaz Gráfica

Estos métodos controlan el comportamiento de los botones y eventos del campo de texto en una interfaz gráfica que utiliza la librería bibliotecacontrasena.

**import bibliotecacontrasena.generarContrasena;**

Importa la clase principal que contiene los métodos para generar y validar contraseñas.

**Método btnGeneraSeguraActionPerformed**

Este método se ejecuta cuando el usuario hace clic en el botón "Generar Contraseña Segura".

private void btnGeneraSeguraActionPerformed(java.awt.event.ActionEvent evt) {
    String password = generarContrasena.generaPassword(8);
    while (!generarContrasena.cumpleRequisitos(password)) {
        password = generarContrasena.generaPassword(8);
    }
    pswContra.setText(password);

    generarContrasena.resultadoValidacion resultado = generarContrasena.seguridadContraseña(password);
    lblVerificacion.setText(resultado.getMensaje());
    lblVerificacion.setForeground((resultado.getColor()));
}

Explicación:

Se genera una contraseña de 8 caracteres.

Si no cumple con los requisitos mínimos de seguridad, se vuelve a generar hasta que sí los cumpla.

Se muestra la contraseña en un campo de texto (pswContra).

Se valida la seguridad de la contraseña usando el método seguridadContraseña(...).

Se muestra un mensaje en lblVerificacion con el nivel de seguridad y se cambia su color según el resultado.

**Método btnLimpiarActionPerformed**

Este método se activa al hacer clic en el botón "Limpiar".

private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
    lblVerificacion.setText(" ");
    pswContra.setText(" ");
}

Explicación:

Limpia tanto la etiqueta de verificación como el campo de contraseña, dejando ambos vacíos.

**Método pswContraKeyReleased**

Este método se ejecuta cada vez que el usuario suelta una tecla dentro del campo de contraseña.

private void pswContraKeyReleased(java.awt.event.KeyEvent evt) {
    String password = new String(pswContra.getPassword());
    String passwordLimpia = generarContrasena.limpiarEspacios(password);

    pswContra.setText(passwordLimpia);

    generarContrasena.resultadoValidacion resultado = generarContrasena.seguridadContraseña(passwordLimpia);
    lblVerificacion.setText(resultado.getMensaje());
    lblVerificacion.setForeground(resultado.getColor());
}

Explicación:

Obtiene el texto actual del campo de contraseña.

Elimina los espacios usando limpiarEspacios(...).

Actualiza el campo con la contraseña limpia.

Evalúa la seguridad de la contraseña ingresada.

Muestra el resultado en la interfaz con mensaje y color correspondientes.

---
![image](https://github.com/user-attachments/assets/3b802d51-cf7c-4e46-b186-3eec5eb72319)


LINK VIDEO:
https://youtu.be/XBAtezCmiSA

