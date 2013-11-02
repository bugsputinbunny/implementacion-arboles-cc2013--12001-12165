/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MRGomez
 */
public interface ITree<V extends Comparable> {
    /*
     * Insertar un valor comparable e en el arbol
     */
    public void insertar(V value);
    
    /*
     * Eliminar el valor comparable e del arbol.
     * Si no esta, devuelve void.
    */
    public V eliminar(V value);
}
