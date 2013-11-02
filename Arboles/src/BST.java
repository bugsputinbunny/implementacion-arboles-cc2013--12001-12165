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
}
