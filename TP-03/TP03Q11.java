
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

/*
 * 
 ██████╗███████╗██╗     ██╗   ██╗██╗      █████╗ 
██╔════╝██╔════╝██║     ██║   ██║██║     ██╔══██╗
██║     █████╗  ██║     ██║   ██║██║     ███████║
██║     ██╔══╝  ██║     ██║   ██║██║     ██╔══██║
╚██████╗███████╗███████╗╚██████╔╝███████╗██║  ██║
 ╚═════╝╚══════╝╚══════╝ ╚═════╝ ╚══════╝╚═╝  ╚═╝
 */
class Celula {
    public Personagem personagem; // Elemento inserido na celula.
    public Celula prox; // Aponta a celula prox.
    public Celula ant;

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
        this.prox = this.ant = null;
    }
}

/*
 * 
    ██╗     ██╗███████╗████████╗ █████╗ 
    ██║     ██║██╔════╝╚══██╔══╝██╔══██╗
    ██║     ██║███████╗   ██║   ███████║
    ██║     ██║╚════██║   ██║   ██╔══██║
    ███████╗██║███████║   ██║   ██║  ██║
    ╚══════╝╚═╝╚══════╝   ╚═╝   ╚═╝  ╚═╝
 */
class ListaDupla {
    private Celula primeiro;
    private Celula ultimo;

    /**
     * Construtor da classe que cria uma lista sem elementos (somente no cabeca).
     */
    public ListaDupla() {
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
        tmp.ant = primeiro;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        }else{
            tmp.prox.ant = tmp;
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
        ultimo.prox.ant = ultimo;
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
            tmp.ant = i;
            i.prox.ant = tmp;
            i.prox = tmp;
            tmp = i = null;
        }
    }

    /**
     * Remover no inicio da lista
     */
    Personagem removerInicio() throws Exception{
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover no inicio da lista!");
        }
        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        Personagem resp = primeiro.personagem;
        primeiro.ant = null;
        tmp.prox = null;
        tmp = null;
        return resp;
    }

    /**
     * Remover no fim da lista
     */
    Personagem removerFim() throws Exception{
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover no fim da lista!");
        }

        Personagem resp = ultimo.personagem;
        ultimo = ultimo.ant;
        ultimo.prox.ant = null;
        ultimo.prox = null;

        return resp;
    }

    /**
     * Remover em uma posicao da lista
     */
    Personagem remover(int pos) throws Exception{
        int tam = tamanho();
        Personagem resp;
        if (pos < 0 || pos > tam) {
            throw new Exception("Erro ao remover na posicao " + pos);
        }else if (pos == 0) {
            resp = removerInicio();
        }else if (pos == tam-1) {
            resp = removerFim();
        }else{
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox);
            resp = i.personagem;
            i.ant.prox = i.prox;
            i.prox.ant = i.ant;
            i.ant = i.prox = null;
            i = null;

        }
        return resp;
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

    String getPivo(int esq, int dir){
        Celula i = primeiro.prox;
        int mid = (esq+dir)/2;
        for (int j = 0; j < mid; j++, i = i.prox);
        return i.personagem.getId();
    }

    String getId(int pos){
        Celula i = primeiro.prox;
        for (int j = 0; j < pos; j++, i = i.prox);
        return i.personagem.getId();
    }

    // ---------------- Swap ------------------
    public void swap(int index_I, int index_J) {
        Celula first_cell = primeiro.prox;    
        Celula second_cell = primeiro.prox;
        
        for (int i = 0; i < index_I; i++, first_cell = first_cell.prox);
        for (int j = 0; j < index_J; j++, second_cell = second_cell.prox);

        Personagem tmp = first_cell.personagem;
        first_cell.personagem = second_cell.personagem;
        second_cell.personagem = tmp;
    }
}

/*
 * 
    ██████╗ ███████╗██████╗ ███████╗ ██████╗ ███╗   ██╗ █████╗  ██████╗ ███████╗███╗   ███╗
    ██╔══██╗██╔════╝██╔══██╗██╔════╝██╔═══██╗████╗  ██║██╔══██╗██╔════╝ ██╔════╝████╗ ████║
    ██████╔╝█████╗  ██████╔╝███████╗██║   ██║██╔██╗ ██║███████║██║  ███╗█████╗  ██╔████╔██║
    ██╔═══╝ ██╔══╝  ██╔══██╗╚════██║██║   ██║██║╚██╗██║██╔══██║██║   ██║██╔══╝  ██║╚██╔╝██║
    ██║     ███████╗██║  ██║███████║╚██████╔╝██║ ╚████║██║  ██║╚██████╔╝███████╗██║ ╚═╝ ██║
    ╚═╝     ╚══════╝╚═╝  ╚═╝╚══════╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚═╝     ╚═╝
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
███████╗███████╗████████╗████████╗███████╗██████╗ ███████╗       ██╗        ██████╗ ███████╗████████╗████████╗███████╗██████╗ ███████╗
██╔════╝██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝       ██║       ██╔════╝ ██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝
███████╗█████╗     ██║      ██║   █████╗  ██████╔╝███████╗    ████████╗    ██║  ███╗█████╗     ██║      ██║   █████╗  ██████╔╝███████╗
╚════██║██╔══╝     ██║      ██║   ██╔══╝  ██╔══██╗╚════██║    ██╔═██╔═╝    ██║   ██║██╔══╝     ██║      ██║   ██╔══╝  ██╔══██╗╚════██║
███████║███████╗   ██║      ██║   ███████╗██║  ██║███████║    ██████║      ╚██████╔╝███████╗   ██║      ██║   ███████╗██║  ██║███████║
╚══════╝╚══════╝   ╚═╝      ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝    ╚═════╝       ╚═════╝ ╚══════╝   ╚═╝      ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝
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
    ███╗   ███╗███████╗████████╗██╗  ██╗ ██████╗ ██████╗ ███████╗
    ████╗ ████║██╔════╝╚══██╔══╝██║  ██║██╔═══██╗██╔══██╗██╔════╝
    ██╔████╔██║█████╗     ██║   ███████║██║   ██║██║  ██║███████╗
    ██║╚██╔╝██║██╔══╝     ██║   ██╔══██║██║   ██║██║  ██║╚════██║
    ██║ ╚═╝ ██║███████╗   ██║   ██║  ██║╚██████╔╝██████╔╝███████║
    ╚═╝     ╚═╝╚══════╝   ╚═╝   ╚═╝  ╚═╝ ╚═════╝ ╚═════╝ ╚══════╝
     */

    // ------------------------ Ler do Arquivo ---------------------------------
    public void read(String line) throws Exception {
        String[] info = TP03Q11.cutter(';', line);

        setId(info[0]);
        setName(info[1]);

        // alternate names
        String tmp = TP03Q11.shambles("[", "", info[2]);
        tmp = TP03Q11.shambles("]", "", tmp);
        String[] alternateNamesArray = TP03Q11.cutter(',', tmp);

        for (int i = 0; i < alternateNamesArray.length; i++) {
            String alternateSTR = alternateNamesArray[i].trim();
            alternateSTR = TP03Q11.shambles("'", "", alternateSTR);
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
        String tmp2 = TP03Q11.shambles("[", "", info[11]);
        tmp2 = TP03Q11.shambles("]", "", tmp2);
        String[] alternateActorsArray = TP03Q11.cutter(',', tmp2);
        for (int i = 0; i < alternateActorsArray.length; i++) {
            String alternateSTR = alternateActorsArray[i].trim();
            alternateSTR = TP03Q11.shambles("^'|'$", "", alternateSTR);
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
    public void print(ListaDupla personagens, int i) {
        Personagem personagem;
        personagem = personagens.getCharacterByID(personagens.getId(i));

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
            String[] dateCut = TP03Q11.cutter('-', date);
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

 ██████╗ ██╗   ██╗██╗ ██████╗██╗  ██╗███████╗ ██████╗ ██████╗ ████████╗
██╔═══██╗██║   ██║██║██╔════╝██║ ██╔╝██╔════╝██╔═══██╗██╔══██╗╚══██╔══╝
██║   ██║██║   ██║██║██║     █████╔╝ ███████╗██║   ██║██████╔╝   ██║   
██║▄▄ ██║██║   ██║██║██║     ██╔═██╗ ╚════██║██║   ██║██╔══██╗   ██║   
╚██████╔╝╚██████╔╝██║╚██████╗██║  ██╗███████║╚██████╔╝██║  ██║   ██║   
 ╚══▀▀═╝  ╚═════╝ ╚═╝ ╚═════╝╚═╝  ╚═╝╚══════╝ ╚═════╝ ╚═╝  ╚═╝   ╚═╝   
                                                                       

     */

    public String[] idArray;
    public int n;

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

    void quicksort(ListaDupla l, int esq, int dir)
    {
        int i = esq, j = dir;
        Personagem personagemPivo, personagem;
        personagemPivo = l.getCharacterByID(l.getPivo(i, j));
    
        while (i <= j)
        {
            personagem = l.getCharacterByID(l.getId(i));
            while (isBigger(personagemPivo.house, personagem.house, personagemPivo, personagem))
            {
                i++;
                personagem = l.getCharacterByID(l.getId(i));
            }
    
            personagem = l.getCharacterByID(l.getId(j));
            while (isBigger(personagem.house, personagemPivo.house, personagem, personagemPivo))
            {
                j--;
                personagem = l.getCharacterByID(l.getId(j));
            }
            if (i <= j)
            {
                countMov+=3;
                l.swap(i, j);
                i++;
                j--;
            }
        }
        if (esq < j)
        {
            quicksort(l, esq, j);
        }
        if (i < dir)
        {
            quicksort(l, i, dir);
        }
    }

} // end Personagem

/*
 *
    ███╗   ███╗ █████╗ ██╗███╗   ██╗
    ████╗ ████║██╔══██╗██║████╗  ██║
    ██╔████╔██║███████║██║██╔██╗ ██║
    ██║╚██╔╝██║██╔══██║██║██║╚██╗██║
    ██║ ╚═╝ ██║██║  ██║██║██║ ╚████║
    ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝
 */

/**
 * TP03Q11
 */
public class TP03Q11 {

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

        ListaDupla personagens = new ListaDupla();

        String charset = "UTF-8";

        // -------------------- Ler o Arquivo .csv ---------------
        try (BufferedReader buffer = new BufferedReader(new FileReader("/tmp/characters.csv", Charset.forName(charset)))) {

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

        ListaDupla personagens_resp = new ListaDupla();
        int x = 0;
        line = scanner.nextLine();
        while (!line.equalsIgnoreCase("FIM") && scanner.hasNext()) {
            if (!line.equalsIgnoreCase("FIM")) {
                personagens_resp.inserirFim(personagens.getCharacterByID(line));
                x++;
            }
            line = scanner.nextLine();
        }
        long startTime = System.currentTimeMillis();

        // ------------- Ler as segundas entradas ---------

        personagem.quicksort(personagens_resp, 0, x-1);
        // ------------------ Escrever a saida ---------------
        for (int i = 0; i < x; i++) {
            personagem.print(personagens_resp, i);
        }
        long endTime = System.currentTimeMillis();
        long execTime = endTime - startTime;
        try (BufferedWriter BW = new BufferedWriter(new FileWriter("matricula_quicksort2_java.txt"))) {
            BW.write("815373" + "\t" + personagem.getCountComp() + "\t" + personagem.getCountMov() + "\t" + execTime + "ms");
        } catch (Exception e) {
            e.getStackTrace();
        }

        scanner.close();
    }
} // end TP02Q03