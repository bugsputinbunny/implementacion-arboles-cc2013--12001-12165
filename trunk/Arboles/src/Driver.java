/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Monica Castellanos 12001
 */
import java.util.*;
public class Driver {

    /**
     * Menu principal
     */
    public static void main(String[] args) {
       System.out.println("Escriba una palabra, Cada caracter se almacenera en un arbol");
       Scanner s = new Scanner(System.in);
       String letras = s.nextLine();
       //Pidiendo implementacion
       System.out.println("Ingrese un numero para seleccionar la implementacion");
       System.out.println("1. Binary BST");
       System.out.println("2. Splay Tree");
       System.out.println("3. Red Black BST");
       System.out.println("4. 2-3 Tree");
       boolean correcto = false;
       int input=0;
       while(correcto==false){
            try{
                String opcion = s.nextLine();
                input = Integer.parseInt(opcion);
                if(input<1||input>4){
                    correcto = false;
                    System.out.println("No es una opcion valida");
                    
                }
                else{
                    correcto = true;
                    int num = input;
                }

            }
            catch(NumberFormatException e){
                System.out.println("No es una opcion valida");
                correcto = false;
               
            }
       }
       if(input==1){
           
       }
       else if(input==2){
           
       }
       else if(input==3){
          //Implementacion Red Black Tree 
                  
           RBTree arbol = new RBTree<Integer>();
           //Agregando letras
           System.out.println("Palabra ingresada :"+letras);
           for(int i =0;i<letras.length();i++){
               arbol.agregar(letras.charAt(i),i);
           }
           System.out.println("Ingrese caracter que desea eliminar");
           String elim = s.nextLine();
           //Si se ingresa mas de un caracter, solo tomamos el primero
           Character caracter = elim.charAt(0);
           arbol.eliminar(caracter);
           System.out.println("Caracteres en el Arbol despues de eliminar:");
           for(int i =0;i<letras.length();i++){
               Character letra = (Character)arbol.get(letras.charAt(i));
        
               System.out.print(letra+"  ");
           }
       }   
          
       else if(input == 4){    
    }
}
}
