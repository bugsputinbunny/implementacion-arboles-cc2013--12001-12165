/**
 * Mario Roberto Gómez Flores (12165)
 * 
 *
 */

public class SplayTree<V extends Comparable> extends BST<V>{
    /*
     * Constructor de un SplayTree Vacio
     */
    public SplayTree(){
        super();
    }

    /*
     * Eliminar el valor comparable value del arbol.
     * Si no esta, devuelve void.
    */
    @Override
    public V eliminar(V value){
        return null;
    }

    /*
     * Mediante operaciones de zig, zag busca el valor en el arbol
     * y lo coloca en la raiz
     */
    private boolean splay(V value){
        return false;
    }
    
    /*
     * Metodo toString que muestra los valores de los nodos preOrder
     */
    @Override
    public String toString(){
        String mensaje;
        //Si el arbol tiene al menos un nodo
        if(head != null)
            //Llama al metodo recursivo para mostrar el toString de todos los nodos
            mensaje = recursiveToString(head);
        //Si no tiene nodos, muestra que esta vacio
        else
            mensaje = "El árbol está vacío.";
        return mensaje;
    }

    /*
     * Metodo que recorre el arbol preOrder adjuntando el toString de cada nodo
     */
    private String recursiveToString(BSNode<V> nodo){
        boolean left, right;
        String mensaje = "";

        //Adjunta su propio valor
        mensaje += nodo.getValue();
        mensaje += ": ";

        //Busca si tiene hijos
        left = nodo.getMenor() != null;
        right = nodo.getMayor() != null;

        //Si tiene hijos, adjunta su informacion
        mensaje += (left) ? nodo.getMenor().getValue() : null;
        mensaje += "; ";
        mensaje += (right) ? nodo.getMayor().getValue() : null;
        mensaje += "\n";

        //Si tiene hijos, busca la información de los nietos
        if(left)
            mensaje += recursiveToString(nodo.getMenor());
        if(right)
            mensaje += recursiveToString(nodo.getMayor());

        return mensaje;
    }
}
