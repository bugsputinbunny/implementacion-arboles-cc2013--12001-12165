/**
 * Mario Roberto Gómez Flores (12165)
 * 
 *
 */

public class TreeFactory{
    public static ITree<Integer> getTree(int type){
        switch(type){
            case 0:
                return new BST<>();
            case 1:
                return new SplayTree<>();
            case 2:
                return new RBTree<>();
            case 3:
                return new BiTree<>();
            default:
                throw new IllegalArgumentException();
        }
    }
}
