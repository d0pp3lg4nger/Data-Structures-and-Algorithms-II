import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class subNode {
    public String name;
    public subNode right;
    public subNode left;

    public subNode(String element) {
        this.name = element;
        this.right = this.left = null;
    }

}

class Node {
    public int yearOfBirth;
    public Node RIGHT;
    public Node LEFT;
    public subNode outro;

    public Node(int element) {
        this.yearOfBirth = element;
        this.RIGHT = this.LEFT = null;
        this.outro = null;
    }
}

class BinaryTree {
    Node root;
    public int countComp;

    public BinaryTree() {
        root = null;
        countComp = 0;
    }

    public void base_insert(int x){
        root = base_insert(x, root);
    }

    private Node base_insert(int x, Node no){
        if (no == null) {
            no = new Node(x);
        }else if (x > no.yearOfBirth) {
            no.RIGHT = base_insert(x, no.RIGHT);
        }else{
            no.LEFT = base_insert(x, no.LEFT);
        }
        return no;
    }

    public void insert(Personagem personagem) {
        root = insert(personagem, this.root);
    }

    private Node insert(Personagem element, Node node) {
        if (node == null) {
            node = new Node(element.getYearOfBirth() % 15);
        } else if ((element.getYearOfBirth() % 15) == node.yearOfBirth) {
            node.outro = insertSubNode(element, node.outro);
        } else if (countComp++ >= 0 && (element.getYearOfBirth() % 15) < node.yearOfBirth) {
            node.LEFT = insert(element, node.LEFT);
        } else if (countComp++ >= 0 && (element.getYearOfBirth() % 15) > node.yearOfBirth) {
            node.RIGHT = insert(element, node.RIGHT);
        }
        return node;
    }

    private subNode insertSubNode(Personagem element, subNode subNode) {
        if (subNode == null) {
            subNode = new subNode(element.getName());
        } else if (element.getName().compareTo(subNode.name) > 0) {
            subNode.right = insertSubNode(element, subNode.right);
        } else {
            subNode.left = insertSubNode(element, subNode.left);
        }
        return subNode;
    }

    public boolean search(Personagem element) {
        boolean resp;
        resp = search(element, this.root);
        return resp;
    }

    private boolean search(Personagem element, Node node) {
        boolean resp = false;
        if (node != null) {
            resp = searchSubNode(element, node.outro);
            if (!resp) {
                System.out.print(" ESQ");
                resp = search(element, node.LEFT);
            }
            if (!resp) {
                System.out.print(" DIR");
                resp = search(element, node.RIGHT);
            }
        }
        return resp;
    }

    private boolean searchSubNode(Personagem element, subNode subNode) {
        boolean resp = false;
        if (subNode == null) {
            resp = false;
        }
        else if (element.getName().equals(subNode.name)) {
            resp = true;
        }
        else if (element.getName().compareTo(subNode.name) > 0) {
            System.out.print("->dir");
            resp = searchSubNode(element, subNode.right);
        }
        else if (element.getName().compareTo(subNode.name) < 0) {
            System.out.print("->esq");
            resp = searchSubNode(element, subNode.left);
        }

        return resp;
    }
}

/**
 * Lista sequencial
 */
class Lista {
    private Personagem[] personagens;
    private int tamanho;

    /**
     * Construtor da classe que cria uma lista sem elementos (somente no cabeca).
     */
    public Lista() {
        this.tamanho = 0;
    }

    public Lista(int tamanho) {
        personagens = new Personagem[tamanho];
        this.tamanho = 0;
    }

    /**
     * Insere um elemento na primeira posicao da lista.
     * 
     * @param x Personagem elemento a ser inserido.
     */
    public void inserirInicio(Personagem personagem) throws Exception {
        if (tamanho >= personagens.length) {
            throw new Exception("Erro ao inserir no inicio!");
        }

        for (int i = tamanho; i > 0; i--) {
            personagens[i] = personagens[i - 1];
        }
        personagens[0] = personagem;
        tamanho++;
    }

    /**
     * Insere um elemento na ultima posicao da lista.
     * 
     * @param personagem Personagem elemento a ser inserido.
     */
    public void inserirFim(Personagem personagem) throws Exception {
        if (tamanho >= personagens.length) {
            throw new Exception("Erro ao inserir no fim!");
        }
        personagens[tamanho] = personagem;
        tamanho++;
    }

    /**
     * Insere um elemento em uma posicao especifica considerando que o
     * primeiro elemento valido esta na posicao 0.
     * 
     * @param x   int elemento a ser inserido.
     * @param pos int posicao da insercao.
     * @throws Exception Se <code>posicao</code> invalida.
     */
    public void inserir(Personagem personagem, int pos) throws Exception {
        if (pos < 0 || pos > tamanho) {
            throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
        } else if (pos == 0) {
            inserirInicio(personagem);
        } else if (pos == tamanho) {
            inserirFim(personagem);
        } else {
            // Caminhar ate a posicao anterior a insercao
            for (int j = tamanho; j > pos; j--) {
                personagens[j] = personagens[j - 1];
            }
            personagens[pos] = personagem;
            tamanho++;
        }
    }

    /**
     * Remover no inicio da lista
     */
    public Personagem removerInicio() throws Exception {
        if (tamanho == 0) {
            throw new Exception("Erro ao remover no incio!");
        }

        Personagem resp = personagens[0];
        tamanho--;

        for (int i = 0; i < tamanho; i++) {
            personagens[i] = personagens[i + 1];
        }
        return resp;
    }

    /**
     * Remover no fim da lista
     */
    public Personagem removerFim() throws Exception {
        if (tamanho == 0) {
            throw new Exception("Erro ao remover no fim!");
        }

        return personagens[--tamanho];
    }

    /**
     * Remover em uma posicao da lista
     */
    public Personagem remover(int pos) throws Exception {
        if (pos < 0 || pos > tamanho || tamanho == 0) {
            throw new Exception("Erro ao remover posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
        } else if (pos == 0) {
            removerInicio();
        } else if (pos == tamanho) {
            removerFim();
        }

        Personagem resp = personagens[pos];
        tamanho--;

        for (int i = pos; i < tamanho; i++) {
            personagens[i] = personagens[i + 1];
        }
        return resp;
    }

    /**
     * Mostra os elementos da lista separados por espacos.
     */
    public void mostrar() {
        System.out.print("[ ");
        for (int i = 0; i < tamanho; i++) {
            System.out.print(personagens[i].getName() + " ");
        }
        System.out.println("] ");
    }

    /**
     * Procura um elemento e retorna se ele existe.
     * 
     * @param x Elemento a pesquisar.
     * @return <code>true</code> se o elemento existir,
     *         <code>false</code> em caso contrario.
     */
    public boolean pesquisar(Personagem x) {
        boolean resp = false;
        for (int i = 0; i < tamanho; i++) {
            if (personagens[i].getId().equals(x.getId())) {
                resp = true;
                i = tamanho;
            }
        }
        return resp;
    }

    /**
     * Pegar o objeto referente ao id
     * 
     * @param id - identificador do objeto
     * @return Objeto procurado
     */
    public Personagem getCharacterByID(String id) {
        for (int i = 0; i < tamanho; i++) {
            if (personagens[i].getId().equals(id)) {
                return personagens[i];
            }
        }
        return new Personagem();
    }

    public Personagem getCharacterByName(String name) {
        for (int i = 0; i < tamanho; i++) {
            if (personagens[i].getName().equals(name)) {
                return personagens[i];
            }
        }
        return new Personagem();
    }

    public int getTamanho() {
        return this.tamanho;
    }

    public String getIdLista(int pos) {
        return this.personagens[pos].getId();
    }
}

/*
 * Personagem
 */
class Personagem implements Comparable<Personagem> {
    private String id; // 0
    private String name; // 1
    private ListaAlternate alternate_names; // 2
    private String house; // 3
    private String ancestry; // 4
    private String species; // 5
    private String patronus; // 6
    private Boolean hogwartsStaff; // 7
    private String hogwartsStudent; // 8
    private String actorName; // 9
    private Boolean alive; // 10
    private ListaAlternate alternate_actors; // 11
    private LocalDate dateOfBirth; // 12
    private int yearOfBirth; // 13
    private String eyeColour; // 14
    private String gender; // 15
    private String hairColour; // 16
    private Boolean wizard; // 17

    // ---------------------- Construtor padrao ------------------
    Personagem() {
        id = "";
        name = "";
        alternate_names = new ListaAlternate(10);
        house = "";
        ancestry = "";
        species = "";
        patronus = "";
        hogwartsStaff = false;
        hogwartsStudent = "";
        actorName = "";
        alive = false;
        alternate_actors = new ListaAlternate(10);
        dateOfBirth = null;
        yearOfBirth = 0;
        eyeColour = "";
        gender = "";
        hairColour = "";
        wizard = false;
    }

    // ---------------------- Construtor para ler do arquivo ---------------------
    Personagem(String line) throws Exception {
        alternate_names = new ListaAlternate(10);
        alternate_actors = new ListaAlternate(10);
        read(line);
    }

    // ------------------------ Clone ----------------------------
    public Object Clone() {
        Personagem personagem = new Personagem();
        Object personagemClone = new Object();
        try {
            personagemClone = personagem.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return personagemClone;
    }

    @Override
    public int compareTo(Personagem o) {
        int _aux = (yearOfBirth % 15);
        int _aux2 = (o.yearOfBirth % 15);
        return (_aux > _aux2 ? 1 : (_aux < _aux2 ? -1 : 0));
    }

    // ---------------------- SETTERS ----------------------
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlternate_names(ListaAlternate alternate_names) {
        this.alternate_names = alternate_names;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setPatronus(String patronus) {
        this.patronus = patronus;
    }

    public void setHogwartsStaff(Boolean hogwartsStaff) {
        this.hogwartsStaff = hogwartsStaff;
    }

    public void setHogwartsStudent(String hogwartsStudent) {
        this.hogwartsStudent = hogwartsStudent;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public void setAlternate_actors(ListaAlternate alternate_actors) {
        this.alternate_actors = alternate_actors;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public void setEyeColour(String eyeColour) {
        this.eyeColour = eyeColour;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }

    public void setWizard(Boolean wizard) {
        this.wizard = wizard;
    }

    // ----------------------- GETTERS ----------------------
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ListaAlternate getAlternate_names() {
        return alternate_names;
    }

    public String getHouse() {
        return house;
    }

    public String getAncestry() {
        return ancestry;
    }

    public String getSpecies() {
        return species;
    }

    public String getPatronus() {
        return patronus;
    }

    public Boolean getHogwartsStaff() {
        return hogwartsStaff;
    }

    public String getHogwartsStudent() {
        return hogwartsStudent;
    }

    public String getActorName() {
        return actorName;
    }

    public Boolean getAlive() {
        return alive;
    }

    public ListaAlternate getAlternate_actors() {
        return alternate_actors;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public String getEyeColour() {
        return eyeColour;
    }

    public String getGender() {
        return gender;
    }

    public String getHairColour() {
        return hairColour;
    }

    public Boolean getWizard() {
        return wizard;
    }

    // ------------------------ Ler do Arquivo ---------------------------------
    public void read(String line) throws Exception {
        String[] info = TP04Q02.cutter(';', line);

        setId(info[0]);
        setName(info[1]);

        // alternate names
        String tmp = TP04Q02.shambles("[", "", info[2]);
        tmp = TP04Q02.shambles("]", "", tmp);
        String[] alternateNamesArray = TP04Q02.cutter(',', tmp);

        for (int i = 0; i < alternateNamesArray.length; i++) {
            String alternateSTR = alternateNamesArray[i].trim();
            alternateSTR = TP04Q02.shambles("'", "", alternateSTR);
            if (!alternateSTR.isEmpty()) {
                alternate_names.inserir(alternateSTR);
            }
        }

        setHouse(info[3]);
        setAncestry(info[4]);
        setSpecies(info[5]);
        setPatronus(info[6]);

        if (info[7].equalsIgnoreCase("FALSO")) {
            setHogwartsStaff(false);
        } else if (info[7].equalsIgnoreCase("VERDADEIRO")) {
            setHogwartsStaff(true);
        }

        if (info[8].equalsIgnoreCase("FALSO")) {
            setHogwartsStudent("false");
        } else if (info[8].equalsIgnoreCase("VERDADEIRO")) {
            setHogwartsStudent("true");
        }

        setActorName(info[9]);

        if (info[10].equalsIgnoreCase("FALSO")) {
            setAlive(false);
        } else if (info[10].equalsIgnoreCase("VERDADEIRO")) {
            setAlive(true);
        }

        // alternate actors
        String tmp2 = TP04Q02.shambles("[", "", info[11]);
        tmp2 = TP04Q02.shambles("]", "", tmp2);
        String[] alternateActorsArray = TP04Q02.cutter(',', tmp2);
        for (int i = 0; i < alternateActorsArray.length; i++) {
            String alternateSTR = alternateActorsArray[i].trim();
            alternateSTR = TP04Q02.shambles("^'|'$", "", alternateSTR);
            if (!alternateSTR.isEmpty()) {
                alternate_actors.inserir(alternateSTR);
            }
        }

        // date of birth
        DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd-M-yyyy");
        LocalDate date = LocalDate.parse(info[12].trim(), DTF);
        setDateOfBirth(date);

        // year of birth
        setYearOfBirth(Integer.parseInt(info[13]));

        setEyeColour(info[14]);
        setGender(info[15]);
        setHairColour(info[16]);

        if (info[17].equals("FALSO")) {
            setWizard(false);
        } else {
            setWizard(true);
        }
    }

    // ------------------ imprimir dados -----------------------
    // public void print(BinaryTree personagens, String id, int index) {
    // Personagem personagem;
    // personagem = personagens.getCharacterById(id);

    // // ------------------- Alternate Names --------------------
    // System.out.print("[" + index + " ## " + personagem.getId() + " ## " +
    // personagem.getName() + " ## " + "{");
    // if (personagem.getAlternate_names().length() > 0) {
    // for (int i = 0; i < personagem.getAlternate_names().length() - 1; i++) {
    // System.out.print(personagem.getAlternate_names().getElement(i) + ", ");
    // }
    // System.out.print(personagem.getAlternate_names().getElement(personagem.getAlternate_names().length()
    // - 1));
    // }
    // System.out.print("}");

    // System.out.print(" ## " + personagem.getHouse() + " ## " +
    // personagem.getAncestry() + " ## "
    // + personagem.getSpecies() + " ## "
    // + personagem.getPatronus() + " ## " + personagem.getHogwartsStaff() + " ## "
    // + personagem.getHogwartsStudent() + " ## "
    // + personagem.getActorName());

    // // ---------- Formatar a data -----------
    // String date = "";
    // if (personagem.getDateOfBirth() != null) {
    // date = personagem.getDateOfBirth().toString();
    // String[] dateCut = TP04Q02.cutter('-', date);
    // date = dateCut[2];
    // date += "-";
    // date += dateCut[1];
    // date += "-";
    // date += dateCut[0];
    // }

    // System.out.print(" ## " + personagem.getAlive() + " ## "
    // + date
    // + " ## " + personagem.getYearOfBirth() + " ## " + personagem.getEyeColour() +
    // " ## "
    // + personagem.getGender() + " ## "
    // + personagem.getHairColour() + " ## " + personagem.getWizard());
    // System.out.println("]");
    // }
} // end Personagem

/**
 * ListaAlternate
 */
class ListaAlternate {
    private String[] array;
    private int n;

    public ListaAlternate(int tamanho) {
        array = new String[tamanho];
        n = 0;
    }

    public int length() {
        return n;
    }

    public void inserir(String str) throws Exception {
        this.array[this.n] = str;
        this.n++;
    }

    public void mostrar() {
        System.out.print("[ ");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("]");
    }

    public boolean pesquisar(String x) {
        boolean retorno = false;
        for (int i = 0; i < n && retorno == false; i++) {
            retorno = (array[i] == x);
        }
        return retorno;
    }

    public String getElement(int pos) {
        return array[pos];
    }
} // end ListaAlternate

/**
 * TP02Q01
 */
public class TP04Q02 {

    // -------- Numero de intervalos na String ----------
    public static int numOfSpaces(String str, char regex) {
        int x = 0, y = 0;

        for (x = 0; x < str.length(); x++) {
            if (str.charAt(x) == regex) {
                y++;
            }
        }

        return y + 1;
    }

    // --------- separar a string com base em um caractere ----------
    public static String[] cutter(char regex, String line) {
        StringBuilder SB = new StringBuilder();
        int tam = numOfSpaces(line, regex);
        String[] output = new String[tam];
        int j = 0;
        for (int i = 0; i < tam; i++) {
            SB = new StringBuilder();
            while (j < line.length() && line.charAt(j) != regex) {
                SB.append(line.charAt(j));
                j++;
            }
            output[i] = SB.toString();
            j++;
        }

        return output;
    }

    // ------------------ trocar todas ocorrencias de uma String por outra em uma
    // String original --------------
    public static String shambles(String oldString, String newString, String str) {
        StringBuilder result = new StringBuilder(str);
        int index = 0;
        while ((index = result.indexOf(oldString, index)) != -1) {
            result.replace(index, index + oldString.length(), newString);
            index += newString.length();
        }
        return result.toString();
    }

    public static void main(String[] args) throws Exception {

        Lista personagens = new Lista(406);

        String charset = "UTF-8";

        // -------------------- Ler o Arquivo .csv ---------------
        try (BufferedReader buffer = new BufferedReader(
                new FileReader("/tmp/characters.csv", Charset.forName(charset)))) {

            String characterLine = buffer.readLine();
            while ((characterLine = buffer.readLine()) != null) {
                Personagem personagem = new Personagem(characterLine);
                personagens.inserirFim(personagem);
                // personagem.print();
                // personagens.mostrar();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo nao encontrado!");
        } catch (IOException e) {
            e.getStackTrace();
        } catch (Exception e) {
            e.getStackTrace();
        }

        long startTime = System.currentTimeMillis();

        // ------------ Ler Entradas ---------------
        Scanner scanner = new Scanner(System.in);
        String line = "";
        BinaryTree chararcter = new BinaryTree();
        // 7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12 e 14
        chararcter.base_insert(7);
        chararcter.base_insert(3);
        chararcter.base_insert(11);
        chararcter.base_insert(1);
        chararcter.base_insert(5);
        chararcter.base_insert(9);
        chararcter.base_insert(13);
        chararcter.base_insert(0);
        chararcter.base_insert(2);
        chararcter.base_insert(4);
        chararcter.base_insert(6);
        chararcter.base_insert(8);
        chararcter.base_insert(10);
        chararcter.base_insert(12);
        chararcter.base_insert(14);
        while (!line.equalsIgnoreCase("FIM") && scanner.hasNext()) {
            line = scanner.nextLine();
            if (!line.equalsIgnoreCase("FIM")) {
                chararcter.insert(personagens.getCharacterByID(line));
            }
        }

        line = "";

        while (!line.equalsIgnoreCase("FIM") && scanner.hasNext()) {
            line = scanner.nextLine();
            if (!line.equalsIgnoreCase("FIM")) {
                System.out.print(line + " => raiz ");
                Personagem personagem = personagens.getCharacterByName(line);
                boolean resp = chararcter.search(personagem);
                if (resp) {
                    System.out.println(" SIM");
                } else {
                    System.out.println(" NAO");
                }
            }
        }

        long endTime = System.currentTimeMillis();
        long execTime = endTime - startTime;
        try (BufferedWriter BW = new BufferedWriter(new FileWriter("matricula_arvoreArvore.txt"))) {
            BW.write("815373" + "\t" + chararcter.countComp + "\t" + execTime + "ms");
        } catch (Exception e) {
            e.getStackTrace();
        }

        scanner.close();
    }
} // end TP02Q01
