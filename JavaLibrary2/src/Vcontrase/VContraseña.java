/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vcontrase;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author emili
 */
public class VContraseña {
    
    
    
     public static String checarcontrase(String password) {
         int mayusculas=0,minusculas=0,numeros=0,simbolos=0;
        int puntuacion = 0;

        if (password.length() != 8) {
            return "Debe tener 8 caracteres";
        }

        
        if (password.matches(".*[a-z].*")) mayusculas=1;          
        if (password.matches(".*[A-Z].*"))minusculas=1;         
        if (password.matches(".*\\d.*")) numeros=1;           
        if (password.matches(".*[!@#$%^&*()_+=\\-{}\\[\\]:;\"'<>,.?/].*")) simbolos=1; 

      puntuacion=mayusculas+minusculas+numeros+simbolos;
       
        switch (puntuacion) {
            case 1: return "Debil";
            case 2: return "Media";
            case 3: return "Fuerte";
            default: return "Muy fuerte";
        }
    }
     
    public static String generateStrongPassword() {
         String may = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
     String min = "abcdefghijklmnopqrstuvwxyz";
     String num = "0123456789";
    String cars = "!@#$%^&*()-_=+<>?";
     String todos = may + min + num + cars;
    SecureRandom random = new SecureRandom();
    StringBuilder password = new StringBuilder(8);
    password.append(may.charAt(random.nextInt(may.length())));
    password.append(min.charAt(random.nextInt(min.length())));
    password.append(num.charAt(random.nextInt(num.length())));
    password.append(cars.charAt(random.nextInt(cars.length())));

    for (int i = 4; i < 8; i++) {
        password.append(todos.charAt(random.nextInt(todos.length())));
    }

    
    return mezclar(password.toString());
}

private static String mezclar(String entrada) {
     SecureRandom random = new SecureRandom();
    List<Character> characters = new ArrayList<>();
    for (char c : entrada.toCharArray()) {
        characters.add(c);
    }
    Collections.shuffle(characters, random);
    StringBuilder salida = new StringBuilder();
    for (char c : characters) {
        salida.append(c);
    }
    return salida.toString();
}
}
