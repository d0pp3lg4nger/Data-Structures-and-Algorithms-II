
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
    ██╗     ██╗███████╗████████╗ █████╗ 
    ██║     ██║██╔════╝╚══██╔══╝██╔══██╗
    ██║     ██║███████╗   ██║   ███████║
    ██║     ██║╚════██║   ██║   ██╔══██║
    ███████╗██║███████║   ██║   ██║  ██║
    ╚══════╝╚═╝╚══════╝   ╚═╝   ╚═╝  ╚═╝
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


        /*
     * 
███████╗███████╗████████╗████████╗███████╗██████╗ ███████╗       ██╗        ██████╗ ███████╗████████╗████████╗███████╗██████╗ ███████╗
██╔════╝██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝       ██║       ██╔════╝ ██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝
███████╗█████╗     ██║      ██║   █████╗  ██████╔╝███████╗    ████████╗    ██║  ███╗█████╗     ██║      ██║   █████╗  ██████╔╝███████╗
╚════██║██╔══╝     ██║      ██║   ██╔══╝  ██╔══██╗╚════██║    ██╔═██╔═╝    ██║   ██║██╔══╝     ██║      ██║   ██╔══╝  ██╔══██╗╚════██║
███████║███████╗   ██║      ██║   ███████╗██║  ██║███████║    ██████║      ╚██████╔╝███████╗   ██║      ██║   ███████╗██║  ██║███████║
╚══════╝╚══════╝   ╚═╝      ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝    ╚═════╝       ╚═════╝ ╚══════╝   ╚═╝      ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝
                                                                                                                                      
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
        String[] info = TP02Q03.cutter(';', line);

        setId(info[0]);
        setName(info[1]);

        // alternate names
        String tmp = TP02Q03.shambles("[", "", info[2]);
        tmp = TP02Q03.shambles("]", "", tmp);
        String[] alternateNamesArray = TP02Q03.cutter(',', tmp);

        for (int i = 0; i < alternateNamesArray.length; i++) {
            String alternateSTR = alternateNamesArray[i].trim();
            alternateSTR = TP02Q03.shambles("'", "", alternateSTR);
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
        String tmp2 = TP02Q03.shambles("[", "", info[11]);
        tmp2 = TP02Q03.shambles("]", "", tmp2);
        String[] alternateActorsArray = TP02Q03.cutter(',', tmp2);
        for (int i = 0; i < alternateActorsArray.length; i++) {
            String alternateSTR = alternateActorsArray[i].trim();
            alternateSTR = TP02Q03.shambles("^'|'$", "", alternateSTR);
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
    public void print(Lista personagens, String id) {
        Personagem personagem;
        personagem = personagens.getCharacterByID(id);

        // ------------------- Alternate Names --------------------
        System.out.print("[" + personagem.getId() + " ## " + personagem.getName() + " ## " + "{");
        if (personagem.getAlternate_names().length() > 0) {
            for (int i = 0; i < personagem.getAlternate_names().length() - 1; i++) {
                System.out.print(personagem.getAlternate_names().getElement(i) + ", ");
            }
            System.out.print(personagem.getAlternate_names().getElement(personagem.getAlternate_names().length() - 1));
        }
        System.out.print("}");

        System.out.print(" ## " + personagem.getHouse() + " ## " + personagem.getAncestry() + " ## "
                + personagem.getSpecies() + " ## "
                + personagem.getPatronus() + " ## " + false + " ## "
                + false + " ## "
                + personagem.getActorName());

        // ---------- Formatar a data -----------
        String date = "";
        if (personagem.getDateOfBirth() != null) {
            date = personagem.getDateOfBirth().toString();
            String[] dateCut = TP02Q03.cutter('-', date);
            date = dateCut[2];
            date += "-";
            date += dateCut[1];
            date += "-";
            date += dateCut[0];
        }

        System.out.print(" ## " + false + " ## "
                + date
                + " ## " + personagem.getYearOfBirth() + " ## " + personagem.getEyeColour() + " ## "
                + personagem.getGender() + " ## "
                + personagem.getHairColour() + " ## " + personagem.getWizard());
        System.out.println("]");
    }

    /*
     * 
    ██████╗ ███████╗███████╗ ██████╗     ███████╗███████╗ ██████╗ 
    ██╔══██╗██╔════╝██╔════╝██╔═══██╗    ██╔════╝██╔════╝██╔═══██╗
    ██████╔╝█████╗  ███████╗██║   ██║    ███████╗█████╗  ██║   ██║
    ██╔═══╝ ██╔══╝  ╚════██║██║▄▄ ██║    ╚════██║██╔══╝  ██║▄▄ ██║
    ██║     ███████╗███████║╚██████╔╝    ███████║███████╗╚██████╔╝
    ╚═╝     ╚══════╝╚══════╝ ╚══▀▀═╝     ╚══════╝╚══════╝ ╚══▀▀═╝ 
     */

    // ------------- Pesquisa Sequencial ---------------------
    boolean sequencialSearch(String[] id, Lista personagens, String name){
        long startTime = System.nanoTime(); // comecar tempo inicial
        int count = 0;
        for (int i = 0; id[i] != null; i++) {
            Personagem personagem = personagens.getCharacterByID(id[i]);
            if (count++ >= 0 && personagem.name.equals(name)) {
                long endTime = System.nanoTime(); // pegar tempo final
                long executionTime = endTime - startTime; // calcular o tempo de execucao
                double msExecTime  =executionTime/1000000.0;
                writeLog("matricula_sequencial.txt", count, msExecTime);
                return true;
            }
        }
        return false;
    }

    // --------------- Escrever aquivo log ---------------
    public static void writeLog(String fileName, int numComp, double execTime){
        try (BufferedWriter BW = new BufferedWriter(new FileWriter(fileName))) {
            BW.write("815373" + "\t" + numComp + "\t" + execTime + "ms");
        } catch (Exception e) {
            e.getStackTrace();
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
public class TP02Q03 {

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

        String charset = "UTF-8";

        // -------------------- Ler o Arquivo .csv ---------------
        try (BufferedReader buffer = new BufferedReader(new FileReader("tmp/characters.csv", Charset.forName(charset)))) {

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
            if(!line.equalsIgnoreCase("FIM")){
                idArray[x] = line; x++;
            }
            line = scanner.nextLine();
        }

        // ------------- Ler as segundas entradas ---------
        line = scanner.nextLine();
        while (!line.equalsIgnoreCase("FIM") && scanner.hasNext()) {
            if(!line.equalsIgnoreCase("FIM")){
                boolean found = personagem.sequencialSearch(idArray, personagens, line);
                if (found) {
                    MyIO.println("SIM");
                }else{
                    MyIO.println("NAO");
                }
            }
            line = scanner.nextLine();
        }


        scanner.close();
    }
} // end TP02Q03
