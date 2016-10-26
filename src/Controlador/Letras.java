package Controlador;

import static java.lang.Character.isLetter;

/**
 * Metodos de para controlar caracteres
 * @author Manuel Guillermo Aguilar Blanco
 * @version 3.1
 */

public class Letras {

	/**
	 * Metodo para que solo acepte letras
	 * @param a: palabra que  escribes
	 * @return true si hay algun numero
	 */
	public static boolean soloLetras(String a){
		for(int i=0; i<a.replace(" ", "").length(); i++){
			if(isLetter(a.replace(" ", "").charAt(i))){
				
			}else{
                            return false;
                        }
		}
                return true;
	}

	/**
	 * Metodo para introducir un dni
	 * @return numero de DNI o NIF con letra incluida 
	 */
	public static boolean dni(String dn){
			if(dn.length()==9 && isLetter(dn.charAt(8)) && soloNumeros(dn.substring(1, 8)))return true;
			return false;
	}
	
	/**
	 * Metodo para que solo acepte letras
	 * @param a: palabra que  escribes
	 * @return true si hay alguna letra
	 */
	public static boolean soloNumeros(String a){	
			for(int i=0; i<a.replace(",", ".").replace(".", "").length(); i++){
			if(a.replace(",", ".").replace(".", "").charAt(i)>'9'){
				return false;
			}
		}
                return true;
        }
        
        

}
