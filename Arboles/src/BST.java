/**
 * Mario Roberto Gómez Flores (12165)
 * 
 *
 */
import java.util.ArrayList;
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
        //Buscar el nodo que se quiere eliminar
        ArrayList<BSNode<V>> pareja = search(value);

        //Si el valor esta en el arbol
        if(pareja != null){
            BSNode<V> parent = pareja.get(0);
            BSNode<V> nodo = pareja.get(1);

            //Si parent es null, se va a borrar la raiz del arbol
            if(parent == null){
                if(head.getMenor() != null){
                    BSNode<V> maximo = getMax(head);
                    if(! maximo.equals(head.getMenor()))
                        maximo.setMenor(head.getMenor());
                    maximo.setMayor(head.getMayor());
                    head = maximo;
                }
                else{
                    head = head.getMayor();
                }
            }
            //Si el nodo no tiene hijos, lo borra
            else if(nodo.getMayor()==null && nodo.getMenor()==null)
                sustituir(parent, nodo, null);
            //Si no tiene hijo menor
            else if(nodo.getMenor()==null)
                sustituir(parent, nodo, nodo.getMayor());
            //Si no tiene hijo mayor
            else if(nodo.getMayor()==null)
                sustituir(parent, nodo, nodo.getMenor());
            //Si tiene dos hijos
            else{
                BSNode<V> maximo = getMax(nodo);
                sustituir(parent, nodo, maximo);
                maximo.setMenor(nodo.getMenor());
                maximo.setMayor(nodo.getMayor());
            }
            return value;
        }
        else
            return null;
    }
    
    public void sustituir(BSNode<V> parent, BSNode<V> prevNode, BSNode<V> newNode){
        if(parent.getMenor().equals(prevNode))
            parent.setMenor(newNode);
        else
            parent.setMayor(newNode);
    }

    protected ArrayList<BSNode<V>> search(V value){
        if(head != null)
            if(value.compareTo(head.getValue()) < 0)
                return recursiveSearch(head, head.getMenor(), value);
            else if(value.compareTo(head.getValue()) > 0)
                return recursiveSearch(head, head.getMayor(), value);
            else{
                ArrayList<BSNode<V>> lista = new ArrayList(2);
                lista.add(null);
                lista.add(head);
                return lista;
            }
        else
            return null;
    }
    protected ArrayList<BSNode<V>> recursiveSearch(BSNode<V> parent, BSNode<V> nodo, V value){
        if(nodo.getValue().compareTo(value) < 0){
            if(nodo.getMayor() != null)
                return recursiveSearch(nodo, nodo.getMayor(), value);
            else
                return null;
        }
        else if(nodo.getValue().compareTo(value) > 0){
            if(nodo.getMenor() != null)
                return recursiveSearch(nodo, nodo.getMenor(), value);
            else
                return null;
        }
        else{
            ArrayList<BSNode<V>> lista = new ArrayList(2);
            lista.add(parent);
            lista.add(nodo);
            return lista;
        }
    }

    protected BSNode<V> getMax(BSNode<V> nodo){
        if(nodo.getMenor() != null){
            BSNode<V> maximo = recursiveMax(nodo, nodo.getMayor());
            return maximo;
        }
        return null;
    }
    protected BSNode<V> recursiveMax(BSNode<V> parent, BSNode<V> nodo){
        if(nodo.getMayor() != null)
            return recursiveMax(nodo, nodo.getMayor());
        else{
            parent.setMayor(nodo.getMenor());
            return nodo;
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
    protected String recursiveToString(BSNode<V> nodo){
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
