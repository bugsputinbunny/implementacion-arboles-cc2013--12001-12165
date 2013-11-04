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
        //Si pudo colocar value en la cabeza del arbol
        if(splay(value)){
            //Si la raiz tiene hijo menor
            if(head.getMenor() != null){
                BSNode<V> mayor = head.getMayor();

                //Busca el mayor elemento del hijo menor de la raiz
                BSNode<V> newHead = buscarMayor(head.getMenor());

                //Lo coloca hasta arriba
                splay(newHead.getValue());

                //Coloca al mayor elemento de la izquierda como raiz y le coloca
                //mayor a la derecha
                head.setMayor(mayor);
            }

            //Si no tiene hijo menor
            else{
                //Coloca al hijo mayor como raiz
                head = head.getMayor();
            }
            return value;
        }
        //Si no pudo, retorna null
        return null;
    }

    private BSNode<V> buscarMayor(BSNode<V> root){
        //Busca si root tiene elemento mayor
        if(root.getMayor() != null)
            //Si tiene, busca si ese tiene mayor
            return buscarMayor(root.getMayor());
        //Si no tiene mayor, retorna root
        else
            return root;
    }

    /*
     * Mediante operaciones de zig, zag busca el valor en el arbol
     * y lo coloca en la raiz
     */
    public boolean splay(V value){
        //Si el arbol esta no vacio
        if(head != null){
            //Llama a recursiveSplay
            BSNode<V> respuesta = recursiveSplay(null, null, head, value);

            //Si respuesta es null, el algoritmo no encontro value en el arbol
            if(respuesta == null)
                return false;
            //Si no, termino bien
            else
                return true;
        }
        //Si el arbol esta vacio, no puede hacer la busqueda
        return false;
    }

    private BSNode<V> recursiveSplay(BSNode<V> grandParent, BSNode<V> parent,
                                        BSNode<V> nodo, V value){
        //Si el valor es menor que el valor del nodo
        if(value.compareTo(nodo.getValue()) < 0){
            //Si el nodo tiene hijo a la izquierda
            if(nodo.getMenor() != null){
                //Se mueven las generaciones y el splay continua
                nodo = recursiveSplay(parent, nodo, nodo.getMenor(), value);

                //Pero si nodo es null, lo retorna de una vez
                if(nodo==null){return nodo;}
            }
            //Si no tiene hijo, retorna null
            else
                return null;
        }
        //Si el valor es mayor que el valor del nodo
        else if(value.compareTo(nodo.getValue()) > 0){
            //Si el nodo tiene hijo a la derecha
            if(nodo.getMayor() != null){
                //El splay continua
                nodo = recursiveSplay(parent, nodo, nodo.getMayor(), value);

                //Pero si nodo es null, lo retorna de una vez
                if(nodo==null){return nodo;}
            }
            //Si no tiene hijo, retorna null
            else
                return null;
        }
        /*
         * Si sale de las otras condiciones, o viene de regreso de recursiveSplay
         * o nunca entro a los otros if y el valor buscado esta en el nodo actual
         * y los tiene que rotar
         */
        
        //Si parent es null, el algoritmo termino
        if(parent == null)
            head = nodo;

        //Si grandParent es null, parent es la raiz y solo hay que rotar dos
        else if(grandParent == null)
            rotateParent(parent, nodo);

        //Si grandParent no es null, hay que rotarlos a todos
        else
            rotate(grandParent, parent, nodo);

        //Devuelve el nodo que se esta rotando
        return nodo;
    }
    
    private void rotate(BSNode<V> grandParent, BSNode<V> parent, BSNode<V> nodo){
        //Si el nodo es menor que el abuelo
        if(nodo.getValue().compareTo(grandParent.getValue()) < 0)
            //Lo coloca a la izquierda
            grandParent.setMenor(nodo);
        //Si el nodo es mayor que el abuelo
        else
            grandParent.setMayor(nodo);

        //Y rota al padre
        rotateParent(parent, nodo);
    }
    
    private void rotateParent(BSNode<V> parent, BSNode<V> nodo){
        //Si el nodo es menor que el padre
        if(nodo.getValue().compareTo(parent.getValue()) < 0){
            //La parte derecha del nodo la coloca a la izquierda del padre
            parent.setMenor(nodo.getMayor());

            //Coloca al padre como hijo mayor del nodo
            nodo.setMayor(parent);
        }
        //Si el nodo es mayor que el padre
        else{
            //La parte izquierda del nodo la coloca a la derecha del padre
            parent.setMayor(nodo.getMenor());

            //Coloca al padre como hijo menor del nodo
            nodo.setMenor(parent);
        }
    }
}
