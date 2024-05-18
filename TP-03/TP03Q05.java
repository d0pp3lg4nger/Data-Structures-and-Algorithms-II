import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

// CELULA
/**
 * Celula
 */
class Celula {
    public Personagem personagem;
    public Celula prox;

    public Celula(){
        personagem = new Personagem();
        prox = null;
    }

    public Celula(Personagem personagem){
        this.personagem = personagem;
        prox = null;
    }
}

/**
 * Lista sequencial
 */
class Lista {
    private Celula primeiro;
    private Celula ultimo;

    /**
     * Construtor da classe que cria uma Lista sem elementos (somente no cabeca).
     */
    public Lista() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    /**
     * Insere um elemento na Lista.
     * 
     * @param x Personagem elemento a ser inserido.
     */
    public void inserirInicio(Personagem personagem) throws Exception{
        Celula tmp = new Celula(personagem);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        }
        tmp = null;
    }

    /**
     * Inserir no fim da lista
     * @return
     * @throws Exception
     */
    public void inserirFim(Personagem personagem) throws Exception{
        ultimo.prox = new Celula(personagem);
        ultimo = ultimo.prox;
    }

    /**
     * Inserir em uma posicao da lista
     * @return
     * @throws Exception
     */
    public void inserir(int pos, Personagem personagem) throws Exception{
        int tamanho = tamanho();
        if (pos < 0 || pos > tamanho) {
            throw new Exception("Erro ao inserir na posicao " + pos);
        }else if (pos == 0) {
            inserirInicio(personagem);
        }else if (pos == tamanho){
            inserirFim(personagem);
        }else{
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox);
            Celula tmp = new Celula(personagem);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
        }
    }

    /**
     * Remover no inicio da Lista
     */
    public Personagem removerInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover no inicio!");
        }

        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        Personagem resp = primeiro.personagem;
        tmp.prox = null;
        tmp = null;
        return resp;
    }

    /**
     * Remover no fim da lista
     */
    public Personagem removerFim() throws Exception{
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover no fim!");
        }

        Celula i;
        for (i = primeiro; i.prox != ultimo; i = i.prox);
        Personagem resp = ultimo.personagem;
        ultimo = i;
        i = ultimo.prox = null;
        return resp;
    }

    /**
     * Remover em uma posicao da lista
     */
    public Personagem remover(int pos) throws Exception{
        Personagem resp;
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover na posicao " + pos);
        }
        if (pos < 0 || pos > tamanho()) {
            throw new Exception("Erro ao remover na posicao " + pos);
        }else if (pos == 0) {
            resp = removerInicio();
        }else if (pos == tamanho()) {
            resp = removerFim();
        }else{
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox);
            Celula tmp = i.prox;
            resp = tmp.personagem;
            i.prox = tmp.prox;
            tmp.prox = null;
            i = tmp = null;
        }
        return resp;
    }
    /**
     * Mostra os elementos da Lista separados por espacos.
     */
    public void mostrar() {
        System.out.print("[ ");
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            System.out.print(i.personagem + " ");
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
            if (i.personagem.getId().equals(x.getId())) {
                resp = true;
                i = null;
            }
        }
        return resp;
    }

    /**
     * Pegar o objeto referente ao id
     * @param id - identificador do objeto
     * @return Objeto procurado
     */
    public Personagem getCharacterByID(String id) {
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            if (i.personagem.getId().equals(id)) {
                return i.personagem;
            }
        }
        return new Personagem();
    }

    public int tamanho(){
        int resp = 0;
        for (Celula i = primeiro; i != ultimo; i = i.prox) {
            resp++;
        }
        return resp;
    }

    public String getIdLista(int pos){
        Celula i = primeiro;
        for (int j = 0; j <= pos; j++) {
            i = i.prox;
        }
        return i.personagem.getId();
    }

}

/*
 * Personagem
 */
class Personagem {
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
    public Object Clone(){
        Personagem personagem = new Personagem();
        Object personagemClone = new Object();
        try {
            personagemClone = personagem.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return personagemClone;
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
        String[] info = TP03Q05.cutter(';', line);

        setId(info[0]);
        setName(info[1]);

        // alternate names
        String tmp = TP03Q05.shambles("[", "", info[2]);
        tmp = TP03Q05.shambles("]", "", tmp);
        String[] alternateNamesArray = TP03Q05.cutter(',', tmp);

        for (int i = 0; i < alternateNamesArray.length; i++) {
            String alternateSTR = alternateNamesArray[i].trim();
            alternateSTR = TP03Q05.shambles("'", "", alternateSTR);
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
        String tmp2 = TP03Q05.shambles("[", "", info[11]);
        tmp2 = TP03Q05.shambles("]", "", tmp2);
        String[] alternateActorsArray = TP03Q05.cutter(',', tmp2);
        for (int i = 0; i < alternateActorsArray.length; i++) {
            String alternateSTR = alternateActorsArray[i].trim();
            alternateSTR = TP03Q05.shambles("^'|'$", "", alternateSTR);
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
    public void print(Lista personagens, String id, int index) {
        Personagem personagem;
        personagem = personagens.getCharacterByID(id);

        // ------------------- Alternate Names --------------------
        System.out.print("[" + index + " ## " + personagem.getId() + " ## " + personagem.getName() + " ## " + "{");
        if (personagem.getAlternate_names().length() > 0) {
            for (int i = 0; i < personagem.getAlternate_names().length() - 1; i++) {
                System.out.print(personagem.getAlternate_names().getElement(i) + ", ");
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
            String[] dateCut = TP03Q05.cutter('-', date);
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
 * TP03Q05
 */
public class TP03Q05{

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

        Lista personagens = new Lista();

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
        Lista listaDePersonagens = new Lista();
        Personagem[] removidos = new Personagem[50];
        int i = 0;
        while (!line.equalsIgnoreCase("FIM") && scanner.hasNext()) {
            line = scanner.nextLine();
            if(!line.equalsIgnoreCase("FIM")){
                listaDePersonagens.inserirFim((personagens.getCharacterByID(line)));
            }
        }

        int num = scanner.nextInt();
        for (int j = 0; j < num; j++) {
            String action = scanner.next();

            if (action.equals("II")) {
                String id = scanner.next();
                listaDePersonagens.inserirInicio(personagens.getCharacterByID(id));
            }

            if (action.equals("IF")) {
                String id = scanner.next();
                listaDePersonagens.inserirFim(personagens.getCharacterByID(id));
            }

            if (action.equals("I*")) {
                int pos = scanner.nextInt();
                String id = scanner.next();
                listaDePersonagens.inserir(pos, personagens.getCharacterByID(id));
            }

            if (action.equals("RI")) {
                removidos[i++] = listaDePersonagens.removerInicio();
            }
            if (action.equals("RF")) {
                removidos[i++] = listaDePersonagens.removerFim();
            }
            if (action.equals("R*")) {
                int pos = scanner.nextInt();
                removidos[i++] = listaDePersonagens.remover(pos);
            }
        }

        for (int j = 0; j < i; j++) {
            System.out.println("(R) " + removidos[j].getName());
        }
        Personagem personagem = new Personagem();
        int size = listaDePersonagens.tamanho();
        for (int j = 0; j < size; j++) {
            personagem.print(listaDePersonagens, listaDePersonagens.getIdLista(j), j);
        }

        scanner.close();
    }
} // end TP02Q01