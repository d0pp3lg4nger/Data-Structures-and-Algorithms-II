import java.util.Scanner;

/**
 * Contato
 */
class Contato {
    String nome;
    String telefone;
    String email;
    int CPF;

    public Contato() {
        nome = "";
        telefone = "";
        email = "";
        CPF = -1;
    }

    public Contato(String nome_param, String telefone_param, String email_param, int CPF_param) {
        this.nome = nome_param;
        this.telefone = telefone_param;
        this.email = email_param;
        this.CPF = CPF_param;
    }
}

/**
 * Celula
 */
class Celula {
    Contato contato;
    Celula prox;

    public Celula() {
        this.contato = null;
        this.prox = null;
    }

    public Celula(Contato contato_param) {
        this.contato = contato_param;
        this.prox = null;
    }

    public Celula(Contato contato_param, Celula prox_param) {
        this.contato = contato_param;
        this.prox = prox_param;
    }
}

/**
 * No
 */
class No {
    char letra;
    No esq;
    No dir;
    Celula primeiro;
    Celula ultimo;

    public No() {
        letra = '\0';
        esq = dir = null;
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public No(char letra_param) {
        this.letra = letra_param;
        this.esq = this.dir = null;
        primeiro = new Celula();
        ultimo = primeiro;
    }

}

/**
 * Agenda
 */
public class Agenda {
    private No raiz;

    public Agenda() {
        raiz = null;
    }

    public void inserir(Contato contato) throws Exception {
        if (Character.isLetter(contato.nome.charAt(0))) {
            raiz = inserir(contato, raiz);
        } else {
            throw new Exception("Erro ao inserir!");
        }
    }

    private No inserir(Contato contato, No no) {
        if (no == null) {
            no = new No(Character.toUpperCase(contato.nome.charAt(0)));
            no.ultimo.prox = new Celula(contato);
            no.ultimo = no.ultimo.prox;
        } else if (Character.toUpperCase(contato.nome.charAt(0)) == no.letra) {
            no.ultimo.prox = new Celula(contato);
            no.ultimo = no.ultimo.prox;
        } else if (Character.toUpperCase(contato.nome.charAt(0)) > no.letra) {
            no.dir = inserir(contato, no.dir);
        } else {
            no.esq = inserir(contato, no.esq);
        }
        return no;
    }

    public Contato remover(String nome) throws Exception {
        Contato resp;
        resp = remover(nome, raiz);
        return resp;
    }

    private Contato remover(String nome, No no) throws Exception {
        Contato resp;
        if (no == null) {
            throw new Exception("Erro ao remover o contato de " + nome);
        } else if (Character.toUpperCase(nome.charAt(0)) == no.letra) {
            Celula i;
            for (i = no.primeiro; i.prox != null && i.prox.contato.nome.equals(nome) == false; i = i.prox)
                ;
            if (i.prox == null) {
                throw new Exception("Erro ao remover o contato de " + nome); // Contato não encontrado
            }
            resp = i.prox.contato;
            Celula tmp = i.prox;
            i.prox = tmp.prox;
            if (tmp == no.ultimo) {
                no.ultimo = i; // Atualiza o último se necessário
            }
            tmp.prox = null;
            tmp = null;
        } else if (Character.toUpperCase(nome.charAt(0)) > no.letra) {
            resp = remover(nome, no.dir);
        } else {
            resp = remover(nome, no.esq);
        }
        return resp;
    }

    public boolean pesquisarPorNome(String nome) {
        return pesquisarPorNome(nome, raiz);
    }

    private boolean pesquisarPorNome(String nome, No no) {
        boolean resp;
        if (no == null) {
            resp = false;
        } else if (Character.toUpperCase(nome.charAt(0)) == no.letra) {
            resp = false;
            for (Celula i = no.primeiro.prox; !resp && i != null; i = i.prox) {
                if (i.contato.nome.equals(nome) == true) {
                    resp = true;
                }
            }
        } else if (Character.toUpperCase(nome.charAt(0)) > no.letra) {
            resp = pesquisarPorNome(nome, no.dir);
        } else {
            resp = pesquisarPorNome(nome, no.esq);
        }
        return resp;
    }

    public boolean pesquisarPorCPF(int CPF) {
        return pesquisarPorCPF(CPF, raiz);
    }

    private boolean pesquisarPorCPF(int CPF, No no) {
        boolean resp = false;
        if (no != null) {
            resp = pesquisarPorCPF(CPF, no.primeiro.prox);
            if (resp == false) {
                resp = pesquisarPorCPF(CPF, no.esq);
                if (resp == false) {
                    resp = pesquisarPorCPF(CPF, no.dir);
                }
            }
        }
        return resp;
    }

    private boolean pesquisarPorCPF(int CPF, Celula i) {
        boolean resp;
        if (i != null) {
            if (i.contato.CPF == CPF) {
                resp = true;
            } else {
                resp = pesquisarPorCPF(CPF, i.prox);
            }
        } else {
            resp = false;
        }
        return resp;
    }

    public void mostrar() {
        mostrar(raiz);
    }

    private void mostrar(No no) {
        if (no != null) {
            mostrar(no.esq);
            System.out.println("No: " + no.letra);
            mostrarLista(no.primeiro.prox);
            mostrar(no.dir);
        }
    }

    private void mostrarLista(Celula i) {
        if (i != null) {
            for (; i != null; i = i.prox) {
                System.out.print(i.contato.nome + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        Agenda agenda = new Agenda();
        System.out.println("Insira a opcao: ");
        Scanner scanner = new Scanner(System.in);
        int x = 0;
        System.out.println("0 - Encerrar");
        System.out.println("1 - Inserir");
        System.out.println("2 - Remover");
        System.out.println("3 - Pesquisar");
        System.out.println("4 - Mostrar");
        x = scanner.nextInt();

        String nome, email, telefone;
        int CPF = 0;
        while (x != 0) {
            switch (x) {
                case 0:
                    break;
                case 1:
                    System.out.print("Nome: ");
                    nome = scanner.next();
                    System.out.print("Telefone: ");
                    telefone = scanner.next();
                    System.out.print("Email: ");
                    email = scanner.next();
                    System.out.print("CPF: ");
                    CPF = scanner.nextInt();
                    Contato contato = new Contato(nome, telefone, email, CPF);
                    agenda.inserir(contato);
                    break;
                case 2:
                    System.out.print("Nome: ");
                    nome = scanner.next();
                    Contato removido = agenda.remover(nome);
                    System.out.println(removido.nome + " Removido!");
                    break;
                case 3:
                    System.out.print("Nome: ");
                    nome = scanner.next();
                    agenda.pesquisarPorNome(nome);
                    break;
                case 4:
                    agenda.mostrar();
                    break;
                default:
                    break;

            }
            System.out.println("Insira a opcao: ");
            System.out.println("0 - Encerrar");
            System.out.println("1 - Inserir");
            System.out.println("2 - Remover");
            System.out.println("3 - Pesquisar");
            System.out.println("4 - Mostrar");
            x = scanner.nextInt();

        }
        scanner.close();
    }
}