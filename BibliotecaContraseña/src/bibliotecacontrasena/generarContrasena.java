
package bibliotecacontrasena;

import java.awt.Color;
import java.util.Random;

public class generarContrasena {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
    private static final Random random = new Random();

    public static String generaPassword(int length) {
        if (length < 8) {
            length = 8;
        }
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return password.toString();
    }

    public static resultadoValidacion seguridadContraseña(String password) {
        if (password.length() > 8) {
            return new resultadoValidacion("Contraseña INVÁLIDA: Solo 8 caracteres", Color.RED);
        } else if (password.length() < 4) {
            return new resultadoValidacion("Contraseña insegura", Color.RED);
        } else if (password.length() < 8) {
            return new resultadoValidacion("Contraseña poco segura", Color.ORANGE);
        } else {
            if (cumpleRequisitos(password)) {
                return new resultadoValidacion("Contraseña segura", Color.GREEN);
            } else {
                return new resultadoValidacion("Contraseña insegura: le faltan requisitos", Color.ORANGE);
            }
        }
    }

    public static boolean cumpleRequisitos(String contraseña) {
        boolean tieneMayus = false;
        boolean tieneMinus = false;
        boolean tieneNumero = false;
        boolean tieneCaracter = false;

        for (int i = 0; i < contraseña.length(); i++) {
            char c = contraseña.charAt(i);

            if (Character.isUpperCase(c)) {
                tieneMayus = true;
            } else if (Character.isLowerCase(c)) {
                tieneMinus = true;
            } else if (Character.isDigit(c)) {
                tieneNumero = true;
            } else if ("!@#$%^&*()".indexOf(c) >= 0) {
                tieneCaracter = true;
            }
            if (tieneMayus && tieneMinus && tieneNumero && tieneCaracter) {
                return true;
            }
        }
        return tieneMayus && tieneMinus && tieneNumero && tieneCaracter;
    }

    public static String limpiarEspacios(String contraseña) {
        if (contraseña.contains(" ")) {
            return contraseña.replace(" ", "");
        }
        return contraseña;
    }

    public static class resultadoValidacion {

        private String mensaje;
        private Color color;

        public resultadoValidacion(String mensaje, Color color) {
            this.mensaje = mensaje;
            this.color = color;
        }

        public String getMensaje() {
            return mensaje;
        }

        public Color getColor() {
            return color;
        }
    }
}
