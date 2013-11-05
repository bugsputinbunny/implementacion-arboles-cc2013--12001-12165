/*
 * Mario Roberto Gómez Flores (12165)
 * Mónica Lorena Castellanos Pellecer (12001)
 * Sección 40
 * ITree.java 
 * Interfaz de los arboles  con los metodos a implementar
 * 04 de Noviembre de 2013
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
