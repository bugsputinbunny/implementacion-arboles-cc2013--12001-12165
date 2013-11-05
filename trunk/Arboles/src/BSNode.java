/*
 * Mario Roberto Gómez Flores (12165)
 * Mónica Lorena Castellanos Pellecer (12001)
 * Sección 40
 * BSNode.java 
 * 04 de Noviembre de 2013
 */

public class BSNode<V extends Comparable> {
    protected V value;
    protected BSNode<V> menor;
    protected BSNode<V> mayor;

    public BSNode(){
        value = null;
        menor = null;
        mayor = null;
    }
    public BSNode(V value){
        this.value = value;
        menor = null;
        mayor = null;
    }

    public void setValue(V value){
        this.value = value;
    }
    public void setMenor(BSNode<V> child){
        menor = child;
    }
    public void setMayor(BSNode<V> child){
        mayor = child;
    }

    public V getValue(){
        return value;
    }
    public BSNode<V> getMenor(){
        return menor;
    }
    public BSNode<V> getMayor(){
        return mayor;
    }
}
