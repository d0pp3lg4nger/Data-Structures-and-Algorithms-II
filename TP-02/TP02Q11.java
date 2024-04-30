
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
 * 
    ██╗     ██╗███████╗████████╗ █████╗      █████╗ ██╗     ████████╗
    ██║     ██║██╔════╝╚══██╔══╝██╔══██╗    ██╔══██╗██║     ╚══██╔══╝
    ██║     ██║███████╗   ██║   ███████║    ███████║██║        ██║   
    ██║     ██║╚════██║   ██║   ██╔══██║    ██╔══██║██║        ██║   
    ███████╗██║███████║   ██║   ██║  ██║    ██║  ██║███████╗   ██║   
    ╚══════╝╚═╝╚══════╝   ╚═╝   ╚═╝  ╚═╝    ╚═╝  ╚═╝╚══════╝   ╚═╝   
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

    public void inserir(String element) throws Exception {
        if (n < array.length) {
            this.array[this.n] = element;
            this.n++;
        } else {
            System.out.println("Lista cheia!");
        }
    }

    public void mostrar() {
        System.out.print("[ ");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("]");
    }

    public boolean pesquisar(String element) {
        for (int i = 0; i < n; i++) {
            if (array[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    public void remover(int pos) throws Exception {
        if (n == 0 || pos < 0 || pos >= n) {
            throw new Exception("Erro ao remover!");
        }

        n--;

        for (int i = pos; i < n; i++) {
            array[i] = array[i + 1];
        }
    }

    public String getElement(int pos) {
        return array[pos];
    }
} // end ListaAlternate

/*
 * 
 * ██████╗███████╗██╗ ██╗ ██╗██╗ █████╗
 * ██╔════╝██╔════╝██║ ██║ ██║██║ ██╔══██╗
 * ██║ █████╗ ██║ ██║ ██║██║ ███████║
 * ██║ ██╔══╝ ██║ ██║ ██║██║ ██╔══██║
 * ╚██████╗███████╗███████╗╚██████╔╝███████╗██║ ██║
 * ╚═════╝╚══════╝╚══════╝ ╚═════╝ ╚══════╝╚═╝ ╚═╝
 */
class Celula {
    public Personagem personagem; // Elemento inserido na celula.
    public Celula prox; // Aponta a celula prox.

    /**
     * Construtor da classe.
     */
    public Celula() {
        this.personagem = new Personagem();
    }

    /**
     * Construtor da classe.
     * 
     * @param personagem Personagem inserido na celula.
     */
    public Celula(Personagem personagem) {
        this.personagem = personagem;
        this.prox = null;
    }
}

/*
 * 
 * ██╗ ██╗███████╗████████╗ █████╗
 * ██║ ██║██╔════╝╚══██╔══╝██╔══██╗
 * ██║ ██║███████╗ ██║ ███████║
 * ██║ ██║╚════██║ ██║ ██╔══██║
 * ███████╗██║███████║ ██║ ██║ ██║
 * ╚══════╝╚═╝╚══════╝ ╚═╝ ╚═╝ ╚═╝
 */
class Lista {
    private Celula primeiro;
    private Celula ultimo;

    /**
     * Construtor da classe que cria uma lista sem elementos (somente no cabeca).
     */
    public Lista() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    /**
     * Insere um elemento na primeira posicao da lista.
     * 
     * @param x Personagem elemento a ser inserido.
     */
    public void inserirInicio(Personagem personagem) {
        Celula tmp = new Celula(personagem);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        }
        tmp = null;
    }

    /**
     * Insere um elemento na ultima posicao da lista.
     * 
     * @param personagem Personagem elemento a ser inserido.
     */
    public void inserirFim(Personagem personagem) {
        ultimo.prox = new Celula(personagem);
        ultimo = ultimo.prox;
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

        int tamanho = tamanho();

        if (pos < 0 || pos > tamanho) {
            throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
        } else if (pos == 0) {
            inserirInicio(personagem);
        } else if (pos == tamanho) {
            inserirFim(personagem);
        } else {
            // Caminhar ate a posicao anterior a insercao
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox)
                ;

            Celula tmp = new Celula(personagem);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
        }
    }

    /**
     * Mostra os elementos da lista separados por espacos.
     */
    public void mostrar() {
        System.out.print("[ ");
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            System.out.print(i.personagem.getName() + " ");
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
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            if (i.personagem == x) {
                resp = true;
                i = ultimo;
            }
        }
        return resp;
    }

    /**
     * Calcula e retorna o tamanho, em numero de elementos, da lista.
     * 
     * @return resp int tamanho
     */
    public int tamanho() {
        int tamanho = 0;
        for (Celula i = primeiro; i != ultimo; i = i.prox, tamanho++)
            ;
        return tamanho;
    }

    /**
     * Pegar o objeto referente ao id
     * 
     * @param id - identificador do objeto
     * @return Objeto procurado
     */
    public Personagem getCharacterByID(String id) {
        for (Celula cell = primeiro.prox; cell != null; cell = cell.prox) {
            if (cell.personagem.getId().equals(id)) {
                return cell.personagem;
            }
        }
        return new Personagem();
    }
}

/*
 * 
 * ██████╗ ███████╗██████╗ ███████╗ ██████╗ ███╗ ██╗ █████╗ ██████╗ ███████╗███╗
 * ███╗
 * ██╔══██╗██╔════╝██╔══██╗██╔════╝██╔═══██╗████╗ ██║██╔══██╗██╔════╝
 * ██╔════╝████╗ ████║
 * ██████╔╝█████╗ ██████╔╝███████╗██║ ██║██╔██╗ ██║███████║██║ ███╗█████╗
 * ██╔████╔██║
 * ██╔═══╝ ██╔══╝ ██╔══██╗╚════██║██║ ██║██║╚██╗██║██╔══██║██║ ██║██╔══╝
 * ██║╚██╔╝██║
 * ██║ ███████╗██║ ██║███████║╚██████╔╝██║ ╚████║██║ ██║╚██████╔╝███████╗██║ ╚═╝
 * ██║
 * ╚═╝ ╚══════╝╚═╝ ╚═╝╚══════╝ ╚═════╝ ╚═╝ ╚═══╝╚═╝ ╚═╝ ╚═════╝ ╚══════╝╚═╝ ╚═╝
 */
class Personagem {
    private String id;
    private String name;
    private ListaAlternate alternate_names;
    private String house;
    private String ancestry;
    private String species;
    private String patronus;
    private Boolean hogwartsStaff;
    private String hogwartsStudent;
    private String actorName;
    private Boolean alive;
    private ListaAlternate alternate_actors;
    private LocalDate dateOfBirth;
    private int yearOfBirth;
    private String eyeColour;
    private String gender;
    private String hairColour;
    private Boolean wizard;

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

    /*
     * 
     * ███████╗███████╗████████╗████████╗███████╗██████╗ ███████╗ ██╗ ██████╗
     * ███████╗████████╗████████╗███████╗██████╗ ███████╗
     * ██╔════╝██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝ ██║ ██╔════╝
     * ██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝
     * ███████╗█████╗ ██║ ██║ █████╗ ██████╔╝███████╗ ████████╗ ██║ ███╗█████╗ ██║
     * ██║ █████╗ ██████╔╝███████╗
     * ╚════██║██╔══╝ ██║ ██║ ██╔══╝ ██╔══██╗╚════██║ ██╔═██╔═╝ ██║ ██║██╔══╝ ██║
     * ██║ ██╔══╝ ██╔══██╗╚════██║
     * ███████║███████╗ ██║ ██║ ███████╗██║ ██║███████║ ██████║ ╚██████╔╝███████╗
     * ██║ ██║ ███████╗██║ ██║███████║
     * ╚══════╝╚══════╝ ╚═╝ ╚═╝ ╚══════╝╚═╝ ╚═╝╚══════╝ ╚═════╝ ╚═════╝ ╚══════╝ ╚═╝
     * ╚═╝ ╚══════╝╚═╝ ╚═╝╚══════╝
     * 
     */

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

    /*
     * 
     * ███╗ ███╗███████╗████████╗██╗ ██╗ ██████╗ ██████╗ ███████╗
     * ████╗ ████║██╔════╝╚══██╔══╝██║ ██║██╔═══██╗██╔══██╗██╔════╝
     * ██╔████╔██║█████╗ ██║ ███████║██║ ██║██║ ██║███████╗
     * ██║╚██╔╝██║██╔══╝ ██║ ██╔══██║██║ ██║██║ ██║╚════██║
     * ██║ ╚═╝ ██║███████╗ ██║ ██║ ██║╚██████╔╝██████╔╝███████║
     * ╚═╝ ╚═╝╚══════╝ ╚═╝ ╚═╝ ╚═╝ ╚═════╝ ╚═════╝ ╚══════╝
     */

    // ------------------------ Ler do Arquivo ---------------------------------
    public void read(String line) throws Exception {
        String[] info = TP02Q11.cutter(';', line);

        setId(info[0]);
        setName(info[1]);

        // alternate names
        String tmp = TP02Q11.shambles("[", "", info[2]);
        tmp = TP02Q11.shambles("]", "", tmp);
        String[] alternateNamesArray = TP02Q11.cutter(',', tmp);

        for (int i = 0; i < alternateNamesArray.length; i++) {
            String alternateSTR = alternateNamesArray[i].trim();
            alternateSTR = TP02Q11.shambles("'", "", alternateSTR);
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
        String tmp2 = TP02Q11.shambles("[", "", info[11]);
        tmp2 = TP02Q11.shambles("]", "", tmp2);
        String[] alternateActorsArray = TP02Q11.cutter(',', tmp2);
        for (int i = 0; i < alternateActorsArray.length; i++) {
            String alternateSTR = alternateActorsArray[i].trim();
            alternateSTR = TP02Q11.shambles("^'|'$", "", alternateSTR);
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
    public void print(Lista personagens, int i) {
        Personagem personagem;
        personagem = personagens.getCharacterByID(idArray[i]);

        // ------------------- Alternate Names --------------------
        System.out.print("[" + personagem.getId() + " ## " + personagem.getName() + " ## " + "{");
        if (personagem.getAlternate_names().length() > 0) {
            for (int j = 0; j < personagem.getAlternate_names().length() - 1; j++) {
                System.out.print(personagem.getAlternate_names().getElement(j) + ", ");
            }
            System.out.print(personagem.getAlternate_names().getElement(personagem.getAlternate_names().length() - 1));
        }
        System.out.print("}");

        System.out.print(" ## " + personagem.getHouse() + " ## " + personagem.getAncestry() + " ## "
                + personagem.getSpecies() + " ## "
                + personagem.getPatronus() + " ## " + personagem.getHogwartsStaff() + " ## "
                + personagem.getHogwartsStudent() + " ## "
                + personagem.getActorName());

        // ---------- Formatar a data -----------
        String date = "";
        if (personagem.getDateOfBirth() != null) {
            date = personagem.getDateOfBirth().toString();
            String[] dateCut = TP02Q11.cutter('-', date);
            date = dateCut[2];
            date += "-";
            date += dateCut[1];
            date += "-";
            date += dateCut[0];
        }

        System.out.print(" ## " + personagem.getAlive() + " ## "
                + date
                + " ## " + personagem.getYearOfBirth() + " ## " + personagem.getEyeColour() + " ## "
                + personagem.getGender() + " ## "
                + personagem.getHairColour() + " ## " + personagem.getWizard());
        System.out.println("]");
    }

    // Contadores
    private int countComp;
    private int countMov;

    public int getCountComp() {
        return countComp;
    }

    // Getters e Setters dos contadores
    public void setCountComp(int countComp) {
        this.countComp = countComp;
    }

    public int getCountMov() {
        return countMov;
    }

    public void setCountMov(int countMov) {
        this.countMov = countMov;
    }

    /*
     * 
     * ██████╗ ██████╗ ██╗ ██╗███╗ ██╗████████╗██╗███╗ ██╗ ██████╗ ███████╗ ██████╗
     * ██████╗ ████████╗
     * ██╔════╝██╔═══██╗██║ ██║████╗ ██║╚══██╔══╝██║████╗ ██║██╔════╝
     * ██╔════╝██╔═══██╗██╔══██╗╚══██╔══╝
     * ██║ ██║ ██║██║ ██║██╔██╗ ██║ ██║ ██║██╔██╗ ██║██║ ███╗███████╗██║ ██║██████╔╝
     * ██║
     * ██║ ██║ ██║██║ ██║██║╚██╗██║ ██║ ██║██║╚██╗██║██║ ██║╚════██║██║ ██║██╔══██╗
     * ██║
     * ╚██████╗╚██████╔╝╚██████╔╝██║ ╚████║ ██║ ██║██║
     * ╚████║╚██████╔╝███████║╚██████╔╝██║ ██║ ██║
     * ╚═════╝ ╚═════╝ ╚═════╝ ╚═╝ ╚═══╝ ╚═╝ ╚═╝╚═╝ ╚═══╝ ╚═════╝ ╚══════╝ ╚═════╝
     * ╚═╝ ╚═╝ ╚═╝
     */

    public String[] idArray;
    public int[] array;
    public int n;

    public void setArray(int[] array) {
        this.array = array;
    }

    public int[] getArray() {
        return array;
    }

    public void setIdArray(String[] idArray, int n) {
        this.idArray = idArray;
        this.n = n;
    }

    public String[] getIdArray() {
        return idArray;
    }

    public int getIdArrayLength() {
        return this.n;
    }

    // ---------------- Swap ------------------
    public void swap(int i, int j) {
        countMov += 3;
        String tmp = idArray[i];
        idArray[i] = idArray[j];
        idArray[j] = tmp;
    }

    public boolean draw(String name1, String name2) {
        for (int i = 0; i < name1.length() && i < name2.length(); i++) {
            // contar, comparar as letras dos nomes e ignorar espacos
            if ((countComp++ >= 0) && (name1.charAt(i) > name2.charAt(i))
                    && (name1.charAt(i) != ' ' && name2.charAt(i) != ' ')) {
                return true;
            } else if ((countComp++ >= 0) && (name1.charAt(i) < name2.charAt(i))) {
                return false;
            }
        }
        return false;
    }

    // ------------------ Ver se e maior ou menor na tabela ASCII
    // --------------------
    public boolean isBigger(String str1, String str2, Personagem p1, Personagem p2) {
        for (int i = 0; i < str1.length() && i < str2.length(); i++) {
            // contar, comparar as letras dos nomes e ignorar espacos
            if ((countComp++ >= 0) && (str1.charAt(i) > str2.charAt(i))
                    && (str1.charAt(i) != ' ' && str2.charAt(i) != ' ')) {
                return true;
            } else if ((countComp++ >= 0) && (str1.charAt(i) < str2.charAt(i))) {
                return false;
            }
        }
        return draw(p1.name, p2.name);
    }

    public void countingsort(Lista l) {
        int[] count = new int[getMaior(l) + 1];
        Personagem[] ordenado = new Personagem[n];

        // inicializar o array
        for (int i = 0; i < count.length; count[i] = 0, i++);

        // adicionar o numero de elementos do array a count[i]
        for (int i = 0; i < n; i++){
            int index = l.getCharacterByID(idArray[i]).yearOfBirth;
            count[index]++;
        }

        // adicionar os numeros menores ou iguais a i
        for (int i = 1; i < count.length; i++){
            count[i] += count[i - 1];
        }

        // ordenar
        for (int i = n - 1; i >= 0; i--){
            int index = l.getCharacterByID(idArray[i]).yearOfBirth;
            countMov++;
            ordenado[count[index] - 1] = l.getCharacterByID(idArray[i]);
            count[index]--;
        }
        
        // desempate por nome
        for (int i = 1; i < ordenado.length; i++) {
            int j = i - 1;
            while (j>=0 && ordenado[j].getYearOfBirth() == ordenado[j + 1].getYearOfBirth() &&
            isBigger(ordenado[j].getName(), ordenado[j+1].getName(), ordenado[j], ordenado[j+1])) {
                countMov+=3;
                Personagem tmp = ordenado[j];
                ordenado[j] = ordenado[j+1];
                ordenado[j+1] = tmp;
                j--;
            }
        }

        for (int i = 0; i < ordenado.length; i++) {
            countMov++;
            idArray[i] = ordenado[i].getId();
        }
    }
    public int getMaior(Lista l) {
        int maior = l.getCharacterByID(idArray[0]).yearOfBirth;

        for (int i = 0; i < n; i++) {
            if (maior < l.getCharacterByID(idArray[i]).yearOfBirth) {
                maior = l.getCharacterByID(idArray[i]).yearOfBirth;
            }
        }
        return maior;
    }

} // end Personagem

/*
 *
 * ███╗ ███╗ █████╗ ██╗███╗ ██╗
 * ████╗ ████║██╔══██╗██║████╗ ██║
 * ██╔████╔██║███████║██║██╔██╗ ██║
 * ██║╚██╔╝██║██╔══██║██║██║╚██╗██║
 * ██║ ╚═╝ ██║██║ ██║██║██║ ╚████║
 * ╚═╝ ╚═╝╚═╝ ╚═╝╚═╝╚═╝ ╚═══╝
 */

/**
 * TP02Q11
 */
public class TP02Q11 {

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

    public static void main(String[] args) {

        Lista personagens = new Lista();

        // -------------------- Ler o Arquivo .csv ---------------
        try (BufferedReader buffer = new BufferedReader(new FileReader("tmp/characters.csv"))) {

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

        // ------------ Ler Entradas ---------------
        Scanner scanner = new Scanner(System.in);
        String line = "";
        Personagem personagem = new Personagem();

        String[] idArray = new String[30];
        int x = 0;
        line = scanner.nextLine();
        while (!line.equalsIgnoreCase("FIM") && scanner.hasNext()) {
            if (!line.equalsIgnoreCase("FIM")) {
                idArray[x] = line;
                x++;
            }
            line = scanner.nextLine();
        }

        long startTime = System.currentTimeMillis();
        personagem.setIdArray(idArray, x);

        // int[] array = new int[30];
        // for (int i = 0; i < x; i++) {
        // array[i] = personagens.getCharacterByID(idArray[i]).getYearOfBirth();
        // }
        // personagem.setArray(array);

        // ------------- Ler as segundas entradas ---------

        personagem.countingsort(personagens);

        // ------------------ Escrever a saida ---------------
        for (int i = 0; i < x; i++) {
            personagem.print(personagens, i);
        }
        long endTime = System.currentTimeMillis();
        long execTime = endTime - startTime;

        try (BufferedWriter BW = new BufferedWriter(new FileWriter("matricula_countingsort.txt"))) {
            BW.write("815373" + "\t" + personagem.getCountComp() + "\t" + personagem.getCountMov() + "\t" + execTime
                    + "ms");
        } catch (Exception e) {
            e.getStackTrace();
        }

        scanner.close();
    }
} // end TP02Q03
