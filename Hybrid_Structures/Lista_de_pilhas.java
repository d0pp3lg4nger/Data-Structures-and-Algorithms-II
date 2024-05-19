import java.util.HashMap;
import java.util.Map;

import javax.swing.RowFilter.Entry;

class Cell_stack {
    int element;
    Cell_stack next;

    public Cell_stack(int element_param){
        this.element = element_param;
        this.next = null;
    }
}

class Cell_list {
    Cell_stack top;
    Cell_list next;

    public Cell_list(){
        this.top = null;
        this.next = null;
    }

    public Cell_list(int element_param){
        this.top = new Cell_stack(element_param);
        this.next = null;
    }

    public void insert_stack(int element_param){
        Cell_stack tmp = new Cell_stack(element_param);
        tmp.next = top;
        top = tmp;
        tmp = null;
    }

    public void mostrar_stack(Cell_list list){
        System.out.print("[ ");
        for (Cell_stack i = list.top; i != null; i = i.next) {
            System.out.print(i.element + " ");
        }
        System.out.println(" ]");
    }
}

class List {
    Cell_list start;
    Cell_list end;

    public List(){
        this.start = new Cell_list();
        this.end = this.start;
    }

    public void insert_list(int element_param){
        Cell_list tmp = new Cell_list(element_param);
        if (start == null) {
            start = tmp;
            end = tmp;
        }else{
            end.next = tmp;
            end = end.next;
        }
    }

    public void mostrar(){
        int j = 0;
        for (Cell_list i = start; i != null; i = i.next) {
            System.out.println("Pilha " + (j++) + ":");
            i.mostrar_stack(i);
        }
    }

    Cell_list maiorPilha(){
        int maior = 0;
        Cell_list resp = start;
        for (Cell_list i = start; i != null; i = i.next) {
            int count = 0;
            for (Cell_stack j = i.top; j != null; j = j.next) {
                count++;
            }
            if (count > maior) {
                maior = count;
                resp = i;
            }
        }
        return resp;
    }
}

/**
 * lista_de_pilhas
 */
public class Lista_de_pilhas {

    public static void main(String[] args) {
        List list = new List();

        // Inserir elementos nas listas
        list.insert_list(10);
        list.insert_list(20);
        list.insert_list(30);

        // Inserir elementos nas pilhas
        list.start.next.insert_stack(11);
        list.start.next.insert_stack(12);

        list.start.next.next.insert_stack(21);
        list.start.next.next.insert_stack(22);
        list.start.next.next.insert_stack(23);

        list.start.next.next.next.insert_stack(31);
        list.start.next.next.next.insert_stack(32);

        

        // Mostrar a lista de pilhas
        list.mostrar();

        Cell_list list_cell = list.maiorPilha();

        System.out.println("Maior pilha: ");
        list_cell.mostrar_stack(list_cell);
    }
}