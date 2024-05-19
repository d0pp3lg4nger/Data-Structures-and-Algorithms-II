import java.util.Scanner;

/**
 * No
 */
class No {
    public char letra;
    public No esq, dir;
    public subNo outro;

    public No(char letra_param){
        this.letra = letra_param;
        this.esq = this.dir = null;
        this.outro = null;
    }

    public No(char letra_param, No esq_param, No dir_param){
        this.letra = letra_param;
        this.esq = esq_param;
        this.dir = dir_param;
        this.outro = null;
    }
}

/**
 * subNo
 */
class subNo {
    public String palavra;
    public subNo dir, esq;
    

    public subNo(String palavra_param){
        this.palavra = palavra_param;
        dir = esq = null;
    }

    public subNo(String palavra_param, subNo dir_param, subNo esq_param){
        this.palavra = palavra_param;
        this.dir = dir_param;
        this.esq = esq_param;
    }
}

public class Arvore_de_Arvore {
    No raiz;

    public Arvore_de_Arvore(){
        raiz = null;
    }

    public void inserir(String palavra) throws Exception{
        raiz = inserir(palavra, raiz);
    }

    private No inserir(String palavra, No no) throws Exception{
        if (no == null) {
            no = new No(Character.toUpperCase(palavra.charAt(0)));
            no.outro = new subNo(palavra);
        }else if (Character.toUpperCase(palavra.charAt(0)) == no.letra) {
            no.outro = inserirSub(palavra, no.outro);
        }else if (Character.toUpperCase(palavra.charAt(0)) < no.letra) {
            no.esq = inserir(palavra, no.esq);
        }else{
            no.dir = inserir(palavra, no.dir);
        }
        return no;
    }

    private subNo inserirSub(String palavra, subNo i) throws Exception{
        if (i == null) {
            i = new subNo(palavra);
        }else if (i.palavra.compareTo(palavra) > 0) {
            i.esq = inserirSub(palavra, i.esq);
        }else if (i.palavra.compareTo(palavra) < 0) {
            i.dir = inserirSub(palavra, i.dir);
        }else{
            throw new Exception("Erro ao inserir na subArvore!");
        }
        return i;
    }

    public boolean pesquisar(String palavra){
        boolean resp;
        resp = pesquisar(palavra, raiz);
        return resp;
    }

    private boolean pesquisar(String palavra, No no){
        boolean resp = false;
        if (no == null) {
            resp = false;
        }else if (Character.toUpperCase(palavra.charAt(0)) == no.letra) {
            resp = pesquisarSub(palavra, no.outro);
        }else if (Character.toUpperCase(palavra.charAt(0)) < no.letra) {
            pesquisar(palavra, no.esq);
        }else{
            pesquisar(palavra, no.dir);
        }
        return resp;
    }

    private boolean pesquisarSub(String palavra, subNo i){
        boolean resp = false;
        if (i == null) {
            resp = false;
        }else if (palavra.compareTo(i.palavra) == 0) {
            resp = true;
        }else if (resp == false && palavra.compareTo(i.palavra) > 0) {
            resp = pesquisarSub(palavra, i.dir);
        }
        else if (resp == false && palavra.compareTo(i.palavra) < 0) {
            resp = pesquisarSub(palavra, i.esq);
        }
        return resp;
    }

    public void mostrar(){
        mostrar(raiz);
    }

    private void mostrar(No no){
        if (no != null) {
            mostrar(no.esq);
            System.out.println("No: " + no.letra);
            mostrarSub(no.outro);
            System.out.println();
            mostrar(no.dir);
        }
    }

    private void mostrarSub(subNo i){
        if (i != null) {
            mostrarSub(i.esq);
            System.out.print(i.palavra + " ");
            mostrarSub(i.dir);
        }
    }

    public int contarPalavras(char primeiro, char ultimo) throws Exception{
        int count = 0;
        count = contarPalavras(primeiro, ultimo, raiz);
        return count;
    }

    private int contarPalavras(char primeiro, char ultimo, No no) throws Exception{
        int count = 0;
        if (no == null) {
            throw new Exception("Erro ao contar palavras!");
        }else if (no.letra == primeiro) {
            count += contarPalavrasSub(ultimo, no.outro);
        }else if (primeiro > no.letra) {
            count += contarPalavras(primeiro, ultimo, no.dir);
        }else{
            count += contarPalavras(primeiro, ultimo, no.esq);
        }
        return count;
    }

    private int contarPalavrasSub(char ultimo, subNo i) throws Exception{
        if (i == null) {
            return 0;
        }
        int count = 0;
        if (i.palavra.charAt(i.palavra.length()-1) == ultimo) {
            count++;
        }
        count = count + contarPalavrasSub(ultimo, i.esq);
        count = count + contarPalavrasSub(ultimo, i.dir);
        
        return count;
    }

    public int contar_palavras_padrao(String padrao) throws Exception{
        int count = 0;
        count = contar_palavras_padrao(padrao, raiz);
        return count;
    }

    private int contar_palavras_padrao(String padrao, No no) throws Exception{
        int count = 0;
        if (no == null) {
            throw new Exception("Erro ao contar palavras!");
        }else if (padrao.charAt(0) == no.letra) {
            count = contar_palavras_padrao_AUX(padrao, no.outro);
        }else if (padrao.charAt(0) > no.letra) {
            count = contar_palavras_padrao(padrao, no.dir);
        }else{
            count = contar_palavras_padrao(padrao, no.esq);
        }
        return count;
    }

    private int contar_palavras_padrao_AUX(String padrao, subNo i){
        int count = 0;
        if (i == null) {
            return 0;
        }
        if (padrao.length() == i.palavra.length()) {
            count++;
        }
        count += contar_palavras_padrao_AUX(padrao, i.esq);
        count += contar_palavras_padrao_AUX(padrao, i.dir);
        return count;
    }

    public static void main(String[] args)throws Exception {
        Arvore_de_Arvore arvore = new Arvore_de_Arvore();

        System.out.println("Insira a opcao: ");
        Scanner scanner = new Scanner(System.in);
        int x = 0;
        System.out.println("0 - Encerrar");
        System.out.println("1 - Inserir");
        System.out.println("2 - Pesquisar");
        System.out.println("3 - Contar Palavras");
        System.out.println("4 - Contar Palavras com Padrao");
        System.out.println("5 - Mostrar");
        x = scanner.nextInt();

        String palavra;
        while (x != 0) {
            switch (x) {
                case 0:
                    break;
                case 1:
                    System.out.print("Palavra: ");
                    palavra = scanner.next();
                    arvore.inserir(palavra);
                    break;
                case 2:
                    System.out.println("Palavra: ");
                    palavra = scanner.next();
                    boolean resp = arvore.pesquisar(palavra);
                    System.out.println(resp);
                    break;
                case 3:
                    System.out.println("Primeiro e ultimo char: ");
                    String first_tmp = scanner.next();
                    String last_tmp = scanner.next();
                    char first_char = first_tmp.charAt(0);
                    char last_char = last_tmp.charAt(0);
                    int count = arvore.contarPalavras(first_char, last_char);
                    System.out.println("Numero de palavras: " + count);
                    break;
                case 4:
                    System.out.println("Insira uma string: ");
                    String str = scanner.next();
                    int count_padrao = arvore.contar_palavras_padrao(str);
                    System.out.println("Numero de palavras: " + count_padrao);
                    break;
                case 5:
                    arvore.mostrar();
                    break;
                default:
                    break;
            }
            System.out.println("0 - Encerrar");
            System.out.println("1 - Inserir");
            System.out.println("2 - Pesquisar");
            System.out.println("3 - Contar Palavras");
            System.out.println("4 - Contar Palavras com Padrao");
            System.out.println("5 - Mostrar");
            x = scanner.nextInt();
            scanner.nextLine();  // Consumir a nova linha
        }


        scanner.close();
    }
}
