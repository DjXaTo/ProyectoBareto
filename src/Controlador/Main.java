package Controlador;

import Controlador.Controlador;
import Vista.Interfaz;

public class Main {

    public static void main(String[] args) {
        new Controlador(new Interfaz()).iniciar();
    }

}