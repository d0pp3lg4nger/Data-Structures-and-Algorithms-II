import java.util.Scanner;

/**
 * Celula
 */
class Celula {
    public int elemento;
    public Celula inf, sup, dir, esq;

    public Celula() {
        elemento = 0;
        inf = sup = dir = esq = null;
    }

    public Celula(int elemento, Celula inf, Celula sup, Celula dir, Celula esq) {
        this.elemento = elemento;
        this.inf = inf;
        this.sup = sup;
        this.dir = dir;
        this.esq = esq;
    }
}

/**
 * Matriz
 */
class Matriz {
    Celula inicio;
    int linha, coluna;

    public Matriz() {
        this.inicio = new Celula();
        linha = coluna = 3;
    }

    public Matriz(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        this.inicio = new Celula(1, null, null, null, null);
        Celula tmp = inicio;
        Celula i = inicio;
        Celula j = inicio;
        int count = 2;
        for (int k = 0; k < linha; k++) {
            for (int l = 0; l < coluna - 1; l++) {
                i.dir = new Celula(count++, null, null, null, null);
                i.dir.esq = i;
                i = i.dir;
                if (k > 0) {
                    tmp.inf = i;
                    i.sup = tmp;
                    tmp = tmp.dir;
                }
            }
            if (k < linha - 1) {
                j.inf = new Celula(count++, null, null, null, null);
                j.inf.sup = j;
                tmp = j.dir;
                j = j.inf;
                i = j;
            }
        }
    }

    public void mostrar() {
        for (Celula i = inicio; i != null; i = i.inf) {
            for (Celula j = i; j != null; j = j.dir) {
                System.out.print(j.elemento + " ");
            }
            System.out.println();
        }
    }

    public void mostrarDiagonalPrincipal() {
        Celula i = inicio;
        while (i != null) {
            System.out.print(i.elemento + " ");
            i = (i.inf != null) ? i.inf.dir : null;
        }
        System.out.println();
        // i = i.inf.dir;
        // System.out.println(i.elemento);
        // i = i.inf;
        // System.out.println(i.elemento);
    }

    public void mostrarDiagonalSecundaria() {
        Celula i;
        for (i = inicio; i.dir != null; i = i.dir)
            ;
        while (i != null) {
            System.out.print(i.elemento + " ");
            i = (i.inf != null) ? i.inf.esq : null;
        }
        System.out.println();
    }

    public Matriz soma(Matriz m2) {
        Matriz resp = new Matriz(m2.linha, m2.coluna);
        // index para linhas
        Celula indexLinha = resp.inicio;
        Celula linha1 = inicio;
        Celula linha2 = m2.inicio;

        while (linha1 != null && linha2 != null) {
            // index para colunas
            Celula indexCol = indexLinha;
            Celula col1 = linha1;
            Celula col2 = linha2;
            while (col1 != null && col2 != null) {
                // somar
                indexCol.elemento = col1.elemento + col2.elemento;
                // ir para direita
                col1 = col1.dir;
                col2 = col2.dir;
                indexCol = indexCol.dir;
            }
            // ir para baixo
            linha1 = linha1.inf;
            linha2 = linha2.inf;
            indexLinha = indexLinha.inf;
        }

        return resp;
    }

    public Matriz multiplicacao(Matriz m2) {
        Matriz resp = new Matriz(this.linha, m2.coluna);
        if (this.linha == m2.linha && this.coluna == m2.coluna) {
            Celula resp_index = resp.inicio;
            Celula resp_index_AUX = resp.inicio;
            Celula index_I = this.inicio; // celula que aponta para o inicio da primeira matriz
            Celula index_J = m2.inicio; // celula que aponta para o inicio da segunda matriz
            Celula index_AUX_I = this.inicio; // auxiliar da i
            Celula index_AUX_J = m2.inicio; // auxiliar da j
            resp_index.elemento = 0;
            while (resp_index != null && index_I != null) {
                while (index_J != null) {
                    resp_index.elemento = 0;
                    while (index_J != null) {
                        resp_index.elemento += (index_I.elemento * index_J.elemento); // calculo da multipicacao
                        index_I = index_I.dir; 
                        index_J = index_J.inf;
                    }
                    // avancar resp e J para a direita até J ser null
                    resp_index = resp_index.dir;
                    index_AUX_J = index_AUX_J.dir;
                    index_J = index_AUX_J;
                    index_I = index_AUX_I;
                }
                // avancar resp e I para baixo até resp ou I ser null
                resp_index_AUX = resp_index_AUX.inf;
                resp_index = resp_index_AUX;
                index_AUX_I = index_AUX_I.inf;
                index_AUX_J = m2.inicio;
                index_J = index_AUX_J;
                index_I = index_AUX_I;
            }
        }
        return resp;
    }

    public void inserir(int elemento_x, int index_i, int index_j) {
        Celula tmp = inicio;
        for (int i = 0; i < index_i; i++) {
            tmp = tmp.inf;
        }
        for (int i = 0; i < index_j; i++) {
            tmp = tmp.dir;
        }
        tmp.elemento = elemento_x;
    }
}

public class TP03Q09 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int case_nums = scanner.nextInt();

        Matriz matriz;
        Matriz matriz2;
        int rows, columns;
        for (int i = 0; i < case_nums; i++) {
            // first matrix
            rows = scanner.nextInt();
            columns = scanner.nextInt();
            matriz = new Matriz(rows, columns);
            for (int j = 0; j < matriz.linha; j++) {
                for (int j2 = 0; j2 < matriz.coluna; j2++) {
                    matriz.inserir(scanner.nextInt(), j, j2);
                }
            }
            // second matrix
            rows = scanner.nextInt();
            columns = scanner.nextInt();
            matriz2 = new Matriz(rows, columns);
            for (int j = 0; j < matriz2.linha; j++) {
                for (int j2 = 0; j2 < matriz2.coluna; j2++) {
                    matriz2.inserir(scanner.nextInt(), j, j2);
                }
            }

            // mostrar as matrizes
            Matriz result = new Matriz(rows, columns);
            matriz.mostrarDiagonalPrincipal();
            matriz.mostrarDiagonalSecundaria();
            result = matriz.soma(matriz2);
            result.mostrar();
            result = matriz.multiplicacao(matriz2);
            result.mostrar();
        }

        scanner.close();
    }
}
