
/**
 * Cell
 */
class Cell {
    int element;
    Cell prox;

    public Cell(int element_param){
        this.element = element_param;
        this.prox = null;
    }

    public Cell(){
        this(0);
    }
    
}

/**
 * Matrix_Cell
 */
class Matrix_Cell {
    Cell first, last;
    Matrix_Cell right, left, up, down;

    public Matrix_Cell(){
        first = last = new Cell();
        right = left = up = down = null;
    }

    public Matrix_Cell(int element_param, Matrix_Cell right_param, Matrix_Cell left_param, Matrix_Cell up_param,
    Matrix_Cell down_param){
        first = last = new Cell();
        for (int i = element_param; i < 10; i++) {
            insertList(i);
        }
        this.right = right_param;
        this.left = left_param;
        this.up = up_param;
        this.down = down_param;
    }

    public void insertList(int x){
        last.prox = new Cell(x);
        last = last.prox;
    }
}
/**
 * Matrix
 */
class Matrix {
    Matrix_Cell start;
    int row, column;

    public Matrix(){
        start = null;
        row = column = 0;
    }

    public Matrix(int row_param, int column_param){
        this.row = row_param;
        this.column = column_param;
        start = new Matrix_Cell(1, null, null, null, null);
        Matrix_Cell tmp = start;
        Matrix_Cell index_I = start;
        Matrix_Cell index_J = start;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                index_J.right = new Matrix_Cell(j, null, index_J, null, null);
                index_J.right.left = index_J;
                index_J = index_J.right;
                if (i > 0) {
                    tmp.down = index_J;
                    index_J.up = tmp;
                    tmp = tmp.right;
                }
            }
            if (i < row-1) {
                index_I.down = new Matrix_Cell(i, null, null, index_I, null);
                index_I.down.up = index_I;
                tmp = index_I.right;
                index_I = index_I.down;
                index_I = index_J;
            }
        }
    }

    public void mostrar(){
        Matrix_Cell index_I = start;
        Matrix_Cell index_J = index_I;
        int integer_index_J = 0;
        while (index_I != null) {
            while (index_J != null) {
                System.out.println("Lista da celula " + integer_index_J++ + ": ");
                for (Cell i = index_J.first.prox; i != null; i = i.prox) {
                    System.out.print(i.element + " ");
                }
                System.out.println();
                index_J = index_J.right;
            }
            index_I = index_I.down;
        }
    }

    public void remove_odd_numbers(){
        Matrix_Cell index_I = start;
        Matrix_Cell index_J = index_I;
        while (index_I != null) {
            while (index_J != null) {
                for (Cell i = index_J.first; i != null; i = i.prox) {
                    if (i.prox.element % 2 != 0) {
                        Cell tmp = i.prox;
                        i.prox = tmp.prox;
                        tmp.prox = null;
                        tmp = null;
                    }
                }
                index_J = index_J.right;
            }
            index_I = index_I.down;
        }
    }
    
}


public class Matrix_list {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(3, 3);

        System.out.println("Antes:");
        matrix.mostrar();
        matrix.remove_odd_numbers();
        System.out.println("Depois:");
        matrix.mostrar();
    }
}
