/**
 * Mario Roberto Gómez Flores (12165)
 * 
 *
 */

public class BST<V extends Comparable> implements ITree<V>{
    protected BSNode<V> head;

    /*
     * Insertar un valor comparable e en el arbol
     */
    @Override
    public void insertar(V value){
        
    }
    
    /*
     * Eliminar el valor comparable e del arbol.
     * Si no esta, devuelve void.
    */
    @Override
    public V eliminar(V value){
        return null;
    }

    protected BSNode<V> search(V value){
        if(head != null)
            return recursiveSearch(head, value);
        else
            return null;
    }
    protected BSNode<V> recursiveSearch(BSNode<V> start, V value){
        if(start.getValue().compareTo(value) < 0){
            if(start.getMayor() != null)
                return recursiveSearch(start.getMayor(), value);
            else
                return null;
        }
        else if(start.getValue().compareTo(value) > 0){
            if(start.getMenor() != null)
                return recursiveSearch(start.getMenor(), value);
            else
                return null;
        }
        else{
            return start;
        }
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
