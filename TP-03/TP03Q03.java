import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Pilha sequencial
 */
class Pilha {
    private Personagem[] personagens;
    private int tamanho;

    /**
     * Construtor da classe que cria uma Pilha sem elementos (somente no cabeca).
     */
    public Pilha() {
        this.tamanho = 0;
    }

    public Pilha(int tamanho){
        personagens = new Personagem[tamanho];
        this.tamanho = 0;
    }
    /**
     * Insere um elemento na Pilha.
     * 
     * @param x Personagem elemento a ser inserido.
     */
    public void inserir(Personagem personagem) throws Exception{
        if (tamanho >= personagens.length) {
            throw new Exception("Erro ao inserir na pilha!");
        }

        for (int i = tamanho; i > 0; i--) {
            personagens[i] = personagens[i-1];
        }
        personagens[0] = personagem;
        tamanho++;
    }

    /**
     * Remover na Pilha
     */
    public Personagem remover() throws Exception {
        if (tamanho == 0) {
            throw new Exception("Erro ao remover na pilha!");
        }

        Personagem resp = personagens[0];
        tamanho--;

        for (int i = 0; i < tamanho; i++) {
            personagens[i] = personagens[i+1];
        }
        return resp;
    }

    /**
     * Mostra os elementos da Pilha separados por espacos.
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

    public int getTamanho(){
        return this.tamanho;
    }

    public String getIdPilha(int pos){
        return this.personagens[pos].getId();
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
        String[] info = TP03Q03.cutter(';', line);

        setId(info[0]);
        setName(info[1]);

        // alternate names
        String tmp = TP03Q03.shambles("[", "", info[2]);
        tmp = TP03Q03.shambles("]", "", tmp);
        String[] alternateNamesArray = TP03Q03.cutter(',', tmp);

        for (int i = 0; i < alternateNamesArray.length; i++) {
            String alternateSTR = alternateNamesArray[i].trim();
            alternateSTR = TP03Q03.shambles("'", "", alternateSTR);
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
        String tmp2 = TP03Q03.shambles("[", "", info[11]);
        tmp2 = TP03Q03.shambles("]", "", tmp2);
        String[] alternateActorsArray = TP03Q03.cutter(',', tmp2);
        for (int i = 0; i < alternateActorsArray.length; i++) {
            String alternateSTR = alternateActorsArray[i].trim();
            alternateSTR = TP03Q03.shambles("^'|'$", "", alternateSTR);
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
    public void print(Pilha personagens, String id, int index) {
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
            String[] dateCut = TP03Q03.cutter('-', date);
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
 * TP03Q03
 */
public class TP03Q03{

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

        Pilha personagens = new Pilha(406);

        String charset = "UTF-8";

        // -------------------- Ler o Arquivo .csv ---------------
        try (BufferedReader buffer = new BufferedReader(new FileReader("/tmp/characters.csv", Charset.forName(charset)))) {

            String characterLine = buffer.readLine();
            while ((characterLine = buffer.readLine()) != null) {
                Personagem personagem = new Personagem(characterLine);
                personagens.inserir(personagem);
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
        Pilha pilhaDePersonagens = new Pilha(100);
        Personagem[] removidos = new Personagem[50];
        int i = 0;
        while (!line.equalsIgnoreCase("FIM") && scanner.hasNext()) {
            line = scanner.nextLine();
            if(!line.equalsIgnoreCase("FIM")){
                pilhaDePersonagens.inserir(personagens.getCharacterByID(line));
            }
        }

        int num = scanner.nextInt();
        for (int j = 0; j < num; j++) {
            String action = scanner.next();

            if (action.equals("I")) {
                String id = scanner.next();
                pilhaDePersonagens.inserir(personagens.getCharacterByID(id));
            }

            if (action.equals("R")) {
                removidos[i++] = pilhaDePersonagens.remover();
            }
        }

        for (int j = 0; j < i; j++) {
            System.out.println("(R) " + removidos[j].getName());
        }
        Personagem personagem = new Personagem();
        int size = pilhaDePersonagens.getTamanho();
        System.out.println("[ Top ]");
        for (int j = 0; j < size; j++) {
            personagem.print(pilhaDePersonagens, pilhaDePersonagens.getIdPilha(j), j);
        }
        System.out.println("[ Bottom ]");

        scanner.close();
    }
} // end TP02Q01