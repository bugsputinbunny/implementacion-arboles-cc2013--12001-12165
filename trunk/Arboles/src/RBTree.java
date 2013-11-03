
import java.util.LinkedList;

/**
 * Mario Roberto Gómez Flores (12165)
 * Monica Castellanos 12001
 *
 */

public class RBTree<V extends Comparable> implements ITree<V>{
    
    /**
     * Se definen atributos para el arbol
     */
    private final boolean rojo=true;
    private final boolean negro=false;// se definen constantes para identificar los nodos rojos y negros
    private NodoRedBlack root;
    private NodoRedBlack x;

    private int tamanio(NodoRedBlack root) {
        return 1;
    }

   
    
    /**
     * se crea clase interna para definir un nodo del arbol red black
     */
    private class NodoRedBlack{
        private V id;
        private V valor;
        private NodoRedBlack izq,der;
        private boolean color;
        private int SubArboles;
        
        /**
         * constructor
         */
        public NodoRedBlack(V id, V valor, boolean color, int SubArboles){
            this.id=id;
            this.valor=valor;
            this.color=color;
            this.SubArboles = SubArboles;
            
        }
    }
    /**
     * 
     * constructor arbol
     */
    public RBTree(){
        root = null;
    }
    /**
     * 
     * Metodos para los datos del nodo
     */
    
    /**
     * 
     * Si tomamos el nodo enviado como parametro  como nueva root regresa el tamanio del arbol
     */
    
    public int size(NodoRedBlack m){
        if (m==null){
            return 0;
        }
        return m.SubArboles;
    }
    /**
     * 
     * Regresa true si el color de un nodo es rojo
     */
    public boolean esRojo(NodoRedBlack m){
        if (m==null){
            return false;
        }
        return (m.color=rojo);
    }
    /**
     * 
     * Metodo que regresa el tamanio total del arbol
     */
    public int tamanio(){
        return tamanio(root);
    }
    /**
     * 
     * Metodo para saber si un arbol esta vacio
     */
    public boolean Empty(){
        return root==null;
    }
    /**
     * 
     * Se crean los metodos de obtencion
     */
    public V get(V ide){
        return get(root, ide);
    }
    public V get(NodoRedBlack x, V ide){
        while(x!=null){
            int comp = ide.compareTo(x.id);
            if (comp<0) x=x.izq;
            else if (comp>0)x=x.der;
            else return x.id;
            
        }
        return null;
    }
    /**
     * 
     * Metodo que compara el identificador del nodo que se da en todo el arbol
     */
    
    public boolean contains(V ide){
        return (get(ide)!=null);
    }
    //Buscal identificador dado a partir de la root enviada como parametro
    private boolean contains (NodoRedBlack x,V ide){
        return (get(x,ide)!=null);
    }
    /**
     * 
     * Metodos que sirven para insertar elementos.
     */

    
    /**
     * 
     * Si el identificador ya existe agrega la pareja de valor-identificador,
     * sobreescribiendo el valor viejo.
     */
    
     @Override
    public void insertar(V value) {
        
    }
    
    public void agregar(V ide,V val){
        root =agregar(root,ide,val);
        root.color=negro;
        assert verificarCondiciones();
    }
    public NodoRedBlack agregar(NodoRedBlack x,V ide,V valor){
        if(x==null) return new NodoRedBlack(ide,valor,rojo,1);
        int comp =ide.compareTo(x.id);
        if(comp<0) x.izq =agregar(x.izq,ide,valor);
        else if(comp>0) x.der = agregar(x.der,ide,valor);
        else x.valor = valor;
        //Verificando posibles desbalances
        if(esRojo(x.der)&& !esRojo(x.izq)) x=rotacionIzq(x);
        if(esRojo(x.izq)&& esRojo(x.izq.izq)) x=rotacionDer(x);
        if(esRojo(x.izq)&& esRojo(x.der)) cambiarColores(x);
        x.SubArboles=tamanio(x.izq)+tamanio(x.der)+1;
        return x;
    }
    /**
     * 
     * Metodo para eliminar un nodo.
     */
    
    // Eliminar el nodo cono el menor identificador a partir de la root h enviada como paramtetro
    private NodoRedBlack eliminarMin(NodoRedBlack h) { 
        if (h.izq == null)
            return null;

        if (!esRojo(h.izq) && !esRojo(h.izq.izq))
            h = moverRojoIzq(h);

        h.izq = eliminarMin(h.izq);
        return balancear(h);
    }
    
    
    // Eliminar un nodo con el identificado enviado como parametro
    public V eliminar(V ide) { 
        if (!contains(ide)) {
            System.err.println("El arbol no contiene:" + ide);
            return null;
        }

        // Si ambos hijos de la root son negros, convertimos la root a roja
        if (!esRojo(root.izq) && !esRojo(root.der))
            root.color = rojo;

        root = eliminar(root, ide);
        if (!Empty()) root.color = negro;
        assert verificarCondiciones();
        return null;
    }
/**
 * 
 * Elimina la key-value con la key dada con el root a h
 */
    
    private NodoRedBlack eliminar(NodoRedBlack h, V ide) { 
        assert contains(h, ide);

        if (ide.compareTo(h.id) < 0)  {
            //verificando unbalance LLX
            if (!esRojo(h.izq) && !esRojo(h.izq.izq))
                h = moverRojoIzq(h);
            h.izq = eliminar(h.izq, ide);
        }
        else {
            if (esRojo(h.izq))
                h = rotacionDer(h);
            if (ide.compareTo(h.id) == 0 && (h.der == null))
                return null;
            if (!esRojo(h.der) && !esRojo(h.der.izq))
                h = moverRojoDer(h);
            if (ide.compareTo(h.id) == 0) {
                NodoRedBlack x = min(h.der);
                h.id = x.id;
                h.valor = x.valor;
                h.der = eliminarMin(h.der);
            }
            else h.der = eliminar(h.der, ide);
        }
        return balancear(h);
    }        
    
    /**
     * Se crean metodos de ayuda
     */
   
    /**
     * 
     * se mueve el hijo izq a la root enviada como parametro
     * si la root enviada como parametro es roja y los dos hijos son negros
     */
    
    private NodoRedBlack moverRojoIzq(NodoRedBlack h) {
        assert (h != null);
        assert esRojo(h) && !esRojo(h.izq) && !esRojo(h.izq.izq);

        cambiarColores(h);
        if (esRojo(h.der.izq)) { 
            h.der = rotacionDer(h.der);
            h = rotacionIzq(h);
        }
        return h;
    }
    
    /**
     * 
     * se mueve el hijo izq a la root enviada como parametro 
     * si la root enviada como parametro es roja y los dos hijos son negros
     */
   
    private NodoRedBlack moverRojoDer(NodoRedBlack h) {
        assert (h != null);
        assert esRojo(h) && !esRojo(h.der) && !esRojo(h.der.izq);
        cambiarColores(h);
        if (esRojo(h.izq.izq)) { 
            h = rotacionDer(h);
        }
        return h;
    }
    /**
     * 
     * Se balancea el arbol a partir del nodo enviado como parametro
     */
    
    private NodoRedBlack balancear(NodoRedBlack h) {
        assert (h != null);
        if (esRojo(h.der)) h = rotacionIzq(h);
        if (esRojo(h.izq) && esRojo(h.izq.izq)) h = rotacionDer(h);
        if (esRojo(h.izq) && esRojo(h.der)) cambiarColores(h);
        h.SubArboles = tamanio(h.izq) + tamanio(h.der) + 1;
        return h;
    }
    /**
     * se rotan los nodos a la derecha si se encuentra un desbalance
     */
   
    public NodoRedBlack rotacionDer(NodoRedBlack n) {
        assert (n != null) && esRojo(n.izq);
        NodoRedBlack x = n.izq;
        n.izq = x.der;
        x.der = n;
        x.color = x.der.color;
        x.der.color = rojo;
        x.SubArboles = n.SubArboles;
        n.SubArboles = tamanio(n.izq) + tamanio(n.der) + 1;
        return x;
    }
     /**
     * se rotan los nodos a la izquierda si se encuentra un desbalance
     */
    public NodoRedBlack rotacionIzq(NodoRedBlack n) {
        assert (n != null) && esRojo(n.der);
        NodoRedBlack x = n.der;
        n.der = x.izq;
        x.izq = n;
        x.color = x.izq.color;
        x.izq.color = rojo;
        x.SubArboles = n.SubArboles;
        n.SubArboles = tamanio(n.izq) + tamanio(n.der) + 1;
        return x;
    }
    /**
     * 
     * Metodo para cambiar el color de los nodos
     */
    
    public void cambiarColores(NodoRedBlack h) {
        //Vericiamos antes que el nodo tenga el color opuesto a sus hijos
        assert (h!=null)&&(h.izq !=null)&&(h.der!=null);
        assert (!esRojo(h)&&esRojo(h.izq)&&esRojo(h.der)||esRojo(h)&&!esRojo(h.izq)&&!esRojo(h.der));
        h.color =!h.color;
        h.izq.color = !h.izq.color;
        h.der.color =!h.der.color;
    
    }
    /**
     * Retorna el identificador del rango n
     */
    
    public V seleccionar(int n){
        if(n<0||n>=tamanio(x.der))return null;
        NodoRedBlack x = seleccionar(root,n);
        return x.id;
    }
    /**
     * 
     * Retorna el nodo con rango K a partir de la root x enviada como parametro. 
     */
   
    public NodoRedBlack seleccionar(NodoRedBlack x,int k){
        assert x != null;
        assert k>=0 && k<tamanio(x);
        int p = tamanio(x.izq);
        if(p>k) return seleccionar(x.izq,k);
        else if (p<k) return seleccionar(x.der,k-p-1);
        else return x;
    }
    /**
     * Metodo que regresa el numero de identificadors que son menores que el identificador del nodo que llama al metodo
     */
    
    public int rango(V ide){
        return rango(ide,root);
    }
    /**
     * 
     * Regresa el numero de identificadores menores que el identificador a partir del nodo enviado como parametro.
     */
    
    public int rango(V ide,NodoRedBlack x){
        if(x==null) return 0;
        int comp = ide.compareTo(x.id);
        if(comp<0) return rango(ide,x.izq);
        else if(comp>0) return 1+tamanio(x.izq)+rango(ide,x.der);
        else return tamanio(x.izq);
    
    }
    public V min(){
        if(Empty()) return null;
        return min(root).id;
    }
    public NodoRedBlack min(NodoRedBlack x){
        assert x!= null;
        if(x.izq==null) return x;
        else return min(x.izq);
    }
    public V max(){
        if(Empty()) return null;
        return max(root).id;
    }
    public NodoRedBlack max(NodoRedBlack x){
        assert x !=null;
        if(x.der==null) return x;
        else return max(x.der);
    }
    /**
     * 
     * Iterador que recorre todos los identificadores del arbol
     */
 
    public Iterable<V> identificadores(){
        return identificadores(min(),max());
    }
    public Iterable<V> identificadores(V peq,V grande){
        LinkedList<V> cola =new LinkedList<V>();
        identificadores(root,cola,peq,grande);
        return cola;
    }
    /**
     * 
     * Metodo para agregar a la cola identificadores entre pequeños y grandes en los subarboles que tienen como raiz a x
     */
   
    public void identificadores(NodoRedBlack x,LinkedList<V> cola,V peq,V grande){
        if(x==null)return;
        int compeq = peq.compareTo(x.id);
        int compgrande = grande.compareTo(x.id);
        if(compeq<0) identificadores(x.izq,cola,peq,grande);
        if(compeq<=0&&compgrande>=0) cola.add(x.id);
        if (compgrande>0) identificadores(x.der,cola,peq,grande);
    }
    
    //Se verifican las condiciones con el arbol red blac.
    
    /**
     * Metodo que verifica que se cumplan todas las condiciones correspondientes a un arbol red blac.
     */
 
    public boolean verificarCondiciones(){
        if(!esOrdenBinario()) System.out.println("El arbol no esta ordenado correctamente");
        if(!hayTamanioCorrecto()) System.out.println("El conteo de los sub-arboles del Red Black Tree no es consistente");
        if(!rangoConsistente()) System.out.println("El rango del arbol no es consistente");
        if(!estructura23()) System.out.println("El arbol Red Black no cumple la estructura 2-3");
        if(!estaBalanceado()) System.out.println("Arbol no balanceado");
        return esOrdenBinario()&&hayTamanioCorrecto()&&rangoConsistente()&&estructura23()&&estaBalanceado();
    }
    /**
     * 
     * Metodo que verifica si el arbol esta balanceado contando el numero de nodos negros.
     */
    
    public boolean estaBalanceado(){
        int negros =0;
        NodoRedBlack x= root;
        while(x!=null){
            if(!esRojo(x)) negros++;
            x = x.izq;
        }
        return estaBalanceado(root,negros);
    }
    
    /**
     * Metodo que cuenta el numero de nodos negros a partir de el numero enviado como parametro y el nodo
     */
    
    public boolean estaBalanceado(NodoRedBlack x,int num){
        if(x==null) return num==0;
        if(!esRojo(x)) num--;
        return estaBalanceado(x.izq,num)&&estaBalanceado(x.der,num);
        
    }
    /**
     * 
     * Metodo que sirve para verificar la estrucura del arbol (que sea 2-3)
     */
    
    public boolean estructura23(){
        return estructura23(root);
    }
    /**
     * 
     * Metodo que verifica si la estructura del arbol es 2-3 pero tomando como root el nodo enviado como parametro.
     */
    
    public boolean estructura23(NodoRedBlack x){
        if(x==null) return true; 
        //Un nodo rojo significa la union de dos nodos en la estructura 2-3
        if(esRojo(x.der)) return false;
        if(x!= root && esRojo(x) && esRojo(x.izq)){
            return false;
        }
        return estructura23(x.izq)&&estructura23(x.der);
    }
    /**
     * Metodo que sirve para verificar el orden de un sub arbol con la root indicada en el parametro x
     */
    
    public boolean esOrdenBinario(NodoRedBlack x,V min,V max){
        if(x==null) return true;
        if(min!=null&& x.id.compareTo(min)<= 0) return false;
        if(max != null&&x.id.compareTo(max)>=0) return false;
        return esOrdenBinario(x.izq,min,x.id )&& esOrdenBinario(x.der,x.id,max);
    }
    /**
     * Metodo que verifica el orden de todo el arbol
     */
    public boolean esOrdenBinario(){
        return esOrdenBinario(root,null,null);
    }
    /**
     * Metodo que Verifica el tamanio de todo el arbol
     */
    public boolean hayTamanioCorrecto(){
        return hayTamanioCorrecto(root);
    }
    /**
     * 
     * Metodo que verifica el tamanio a partir del nodo enviado como parametro en x
     */
    public boolean hayTamanioCorrecto(NodoRedBlack x){
        if(x==null)return true;
        if(x.SubArboles!= tamanio(x.izq)+tamanio(x.der)+1)return false;
        return hayTamanioCorrecto(x.izq)&&hayTamanioCorrecto(x.der);
    }
    /**
     * Metodo que verifica si el rango del arbol es consistente
     */
    public boolean rangoConsistente(){
        for(int i=0;i<tamanio(x.der);i++){
            if(i!= rango(seleccionar(i))) return false;
            
        }
        for(V ide:identificadores()){
            if(ide.compareTo(seleccionar(rango(ide)))!= 0) return false;
        }
        return true;
    }

    
}
