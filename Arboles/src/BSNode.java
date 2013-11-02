/**
 * Mario Roberto Gómez Flores (12165)
 * 
 *
 */

public class BSNode<V extends Comparable> {
    protected V value;
    protected BSNode<V> child;

    public BSNode(){
        value = null;
        child = null;
    }
    public BSNode(V value){
        this.value = value;
        child = null;
    }

    public void setValue(V value){
        this.value = value;
    }
    public void setChild(BSNode<V> child){
        this.child = child;
    }

    public V getValue(){
        return value;
    }
    public BSNode<V> getChild(){
        return child;
    }
}
