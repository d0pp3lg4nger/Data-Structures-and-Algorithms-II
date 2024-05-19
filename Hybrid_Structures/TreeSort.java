import java.util.ArrayList;
import java.util.Scanner;

/**
 * Node
 */
class Node {
    int element;
    Node right, left;

    public Node(int element_param){
        this.element = element_param;
        right = left = null;
    }

    public Node(){
        this(0);
    }
}

/**
 * Tree
 */
class Tree {
    Node root;
    
    public Tree(){
        root = null;
    }

    public void inserir(int element_param){
        root = inserir(element_param, root);
    }

    private Node inserir(int element_param, Node node){
        if (node == null) {
            node = new Node(element_param);
        }else if (element_param > node.element) {
            node.right = inserir(element_param, node.right);
        }else{
            node.left = inserir(element_param, node.left);
        }
        return node;
    }

    public ArrayList<Integer> tree_sort(ArrayList<Integer>arr){
        arr.clear();
        arr = tree_sort(arr, root);
        return arr;
    }

    private ArrayList<Integer> tree_sort(ArrayList<Integer> arr, Node node){
        if (node != null) {
            arr = tree_sort(arr, node.left);
            arr.add(node.element);
            arr = tree_sort(arr, node.right);
        }
        return arr;
    }
}

public class TreeSort {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        Tree tree = new Tree();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insira valores para o array (-1 para finalizar): ");
        int x = scanner.nextInt();
        while (x != -1) {
            arr.add(x);
            x = scanner.nextInt();
            scanner.nextLine();
        }

        for (int i = 0; i < arr.size(); i++) {
            tree.inserir(arr.get(i));
        }

        // ordenar
        arr = tree.tree_sort(arr);

        System.out.println("Array ordenado: ");
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i) + " ");
        }
        scanner.close();
    }
}
