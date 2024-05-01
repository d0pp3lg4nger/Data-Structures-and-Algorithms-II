#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <stdbool.h>
#include <string.h>
#include <ctype.h>
#include <time.h>

#define true 1
#define false 0
typedef char *String;
typedef bool Boolean;

// -------- Contadores ---------
int countMov = 0;
int countComp = 0;

/*
    ██╗     ██╗███████╗████████╗ █████╗
    ██║     ██║██╔════╝╚══██╔══╝██╔══██╗
    ██║     ██║███████╗   ██║   ███████║
    ██║     ██║╚════██║   ██║   ██╔══██║
    ███████╗██║███████║   ██║   ██║  ██║
    ╚══════╝╚═╝╚══════╝   ╚═╝   ╚═╝  ╚═╝
*/

typedef struct Lista
{
    String *elemento;
    int tamanho;
} Lista;

void iniciar(Lista *lista, size_t tamanho)
{
    if (tamanho > 0)
    {
        lista->elemento = (String *)malloc(tamanho * sizeof(String));
        for (size_t i = 0; i < tamanho; i++)
        {
            lista->elemento[i] = (String)malloc(500 * sizeof(char));
        }
    }

    lista->tamanho = 0;
}

/**
 * Insere um elemento na ultima posicao da
 * @param x int elemento a ser inserido.
 */
void inserirFim(String elemento, Lista *lista, int tamanho)
{

    // validar insercao
    if (lista->tamanho >= tamanho)
    {
        printf("Erro ao inserir!");
        exit(1);
    }

    strcpy(lista->elemento[lista->tamanho], elemento);
    lista->tamanho++;
}

/**
 * Mostra os alternate separados por espacos.
 */
void mostrar(Lista *lista)
{
    int i;

    for (i = 0; i < lista->tamanho; i++)
    {
        printf("%s ", lista->elemento[i]);
    }
}

void freeList(Lista *lista)
{
    free(lista->elemento);
    lista->tamanho = 0;
}

/*
    ██████╗ ███████╗██████╗ ███████╗ ██████╗ ███╗   ██╗ █████╗  ██████╗ ███████╗███╗   ███╗
    ██╔══██╗██╔════╝██╔══██╗██╔════╝██╔═══██╗████╗  ██║██╔══██╗██╔════╝ ██╔════╝████╗ ████║
    ██████╔╝█████╗  ██████╔╝███████╗██║   ██║██╔██╗ ██║███████║██║  ███╗█████╗  ██╔████╔██║
    ██╔═══╝ ██╔══╝  ██╔══██╗╚════██║██║   ██║██║╚██╗██║██╔══██║██║   ██║██╔══╝  ██║╚██╔╝██║
    ██║     ███████╗██║  ██║███████║╚██████╔╝██║ ╚████║██║  ██║╚██████╔╝███████╗██║ ╚═╝ ██║
    ╚═╝     ╚══════╝╚═╝  ╚═╝╚══════╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚═╝     ╚═╝
*/

typedef struct Personagem
{
    char id[40];              // 0
    char name[80];            // 1
    Lista *alternate_names;   // 2
    char house[50];           // 3
    char ancestry[50];        // 4
    char species[50];         // 5
    char patronus[50];        // 6
    Boolean hogwartsStaff;    // 7
    char hogwartsStudent[50]; // 8
    char actorName[80];       // 9
    Boolean alive;            // 10
    Lista *alternate_actors;  // 11
    char dateOfBirth[15];     // 12
    int yearOfBirth;          // 13
    char eyeColour[15];       // 14
    char gender[15];          // 15
    char hairColour[15];      // 16
    Boolean wizard;           // 17
} Personagem;

void reset(Personagem *personagem)
{
    personagem->hogwartsStaff = false;
    personagem->alive = false;
    personagem->yearOfBirth = 0;
    personagem->wizard = false;
    personagem->alternate_names = NULL;
    personagem->alternate_actors = NULL;

    personagem->alternate_names = (Lista *)malloc(sizeof(Lista));
    iniciar(personagem->alternate_names, 50);
    personagem->alternate_actors = (Lista *)malloc(sizeof(Lista));
    iniciar(personagem->alternate_actors, 50);
}
// ------------------------- CELULA ------------------------
typedef struct Cell
{
    Personagem personagem;
    struct Cell *prox;
} Cell;

Cell *newCell(Personagem personagem)
{
    Cell *new = (Cell *)malloc(sizeof(Cell));
    new->personagem = personagem;
    new->prox = NULL;
    return new;
}

Cell *primeiro;
Cell *ultimo;

void start(Personagem personagem)
{
    primeiro = newCell(personagem);
    ultimo = primeiro;
}

/**
 * Insere um elemento na ultima posicao da lista.
 * @param x int elemento a ser inserido.
 */
void addEnd(Personagem personagem)
{
    ultimo->prox = newCell(personagem);
    ultimo = ultimo->prox;
}

/**
 * Calcula e retorna o length, em numero de elementos, da lista.
 * @return resp int length
 */
int length()
{
    int length = 0;
    Cell *i;
    for (i = primeiro; i != ultimo; i = i->prox, length++)
        ;
    return length;
}

/**
 * Mostra os elementos da lista separados por espacos.
 */
void printCell()
{
    printf("[ ");
    Cell *i;
    for (i = primeiro->prox; i != NULL; i = i->prox)
    {
        printf("%s ", i->personagem.id);
    }
    printf("] \n");
}

/**
 * Procura um elemento por id e o retorna.
 * @param id Elemento a pesquisar.
 * @return <code>true</code> se o elemento existir,
 * <code>false</code> em caso contrario.
 */
Personagem getElementByID(String id)
{
    Cell *i;

    for (i = primeiro->prox; i != NULL; i = i->prox)
    {
        if (strcmp(i->personagem.id, id) == 0)
        {
            return i->personagem;
        }
    }
    Personagem personagem;
    return personagem;
}

void freeCell()
{
    Cell *aux = primeiro;
    Cell *prox;

    while (aux != NULL)
    {
        primeiro = aux->prox;
        free(aux);
        aux = prox;
    }
    primeiro = NULL;
    ultimo = NULL;
}

/*
    ███╗   ███╗███████╗████████╗██╗  ██╗ ██████╗ ██████╗ ███████╗
    ████╗ ████║██╔════╝╚══██╔══╝██║  ██║██╔═══██╗██╔══██╗██╔════╝
    ██╔████╔██║█████╗     ██║   ███████║██║   ██║██║  ██║███████╗
    ██║╚██╔╝██║██╔══╝     ██║   ██╔══██║██║   ██║██║  ██║╚════██║
    ██║ ╚═╝ ██║███████╗   ██║   ██║  ██║╚██████╔╝██████╔╝███████║
    ╚═╝     ╚═╝╚══════╝   ╚═╝   ╚═╝  ╚═╝ ╚═════╝ ╚═════╝ ╚══════╝
*/

size_t numOfSpaces(char regex, String str)
{
    size_t x = 0, y = 0;
    size_t size = strlen(str);
    for (x = 0; x < size; x++)
    {
        if (str[x] == regex)
        {
            y++;
        }
    }
    return y + 1;
}

// --------- separar a string com base em um caractere ----------
String *split(char regex, String str)
{
    size_t size = numOfSpaces(regex, str);
    String *output = (String *)malloc(size * sizeof(String));
    for (size_t i = 0; i < size; i++)
    {
        output[i] = (String)malloc(200 * sizeof(char));
    }

    int x = 0, y = 0, z = 0;

    while (str[x] != '\0')
    {
        if (str[x] == regex)
        {
            output[y][z] = '\0';

            y++;
            z = 0;
            if ((str[x + 1] == regex) || (str[x + 1] == '\0') || (str[x + 1] == '\n'))
            {
                x++;
                y++;
            }
        }
        else
        {
            if (str[x] != '\n')
            {
                output[y][z] = str[x];
                z++;
            }
        }
        x++;
    }

    return output;
} // end split ( )

// ----------------------- Replace All -------------------------
char *replaceAll(const char *oldString, const char *newString, const char *str)
{
    size_t oldLen = strlen(oldString);
    size_t newLen = strlen(newString);
    size_t strLen = strlen(str);
    size_t count = 0;

    const char *tmp = str;
    while ((tmp = strstr(tmp, oldString)))
    { // somar todas ocorrencias de oldString em str
        count++;
        tmp += oldLen;
    }

    size_t resultLen = strLen + count * (newLen - oldLen) + 1; // calcular tamanho do resultado
    char *result = (char *)malloc(resultLen);

    char *out = result;
    const char *in = str;
    while (*in)
    {
        // trocar oldString por newString em str
        if (strncmp(in, oldString, oldLen) == 0)
        {
            strcpy(out, newString);
            in += oldLen;
            out += newLen;
        }
        else
        {
            *out++ = *in++; // copiar str para o ponteiro de resultado
        }
    }
    *out = '\0';

    return result;
} // end replaceAll ( )

// ---------------------------- Tirar espacos da string --------------------
char *trimWhiteSpace(char *str)
{
    char *end;

    while (isspace((unsigned char)*str))
        str++;

    if (*str == 0)
        return str;

    end = str + strlen(str) - 1;
    while (end > str && isspace((unsigned char)*end))
        end--;

    end[1] = '\0';

    return str;
}

// ---------------------------- Ler do arquivo
void read(String line, Personagem *personagem)
{

    String *info = split(';', line);

    strcpy(personagem->id, info[0]);
    strcpy(personagem->name, info[1]);
    strcpy(personagem->house, info[3]);
    strcpy(personagem->ancestry, info[4]);
    strcpy(personagem->species, info[5]);
    strcpy(personagem->patronus, info[6]);
    if (info[8][0] == 'V')
    {
        strcpy(personagem->hogwartsStudent, "true");
    }
    else
    {
        strcpy(personagem->hogwartsStudent, "false");
    }

    strcpy(personagem->actorName, info[9]);
    personagem->yearOfBirth = atoi(info[13]);
    strcpy(personagem->eyeColour, info[14]);
    strcpy(personagem->gender, info[15]);
    strcpy(personagem->hairColour, info[16]);

    strcpy(personagem->dateOfBirth, info[12]);
    int day, mouth, year;
    sscanf(personagem->dateOfBirth, "%d-%d-%d", &day, &mouth, &year);
    sprintf(personagem->dateOfBirth, "%02d-%02d-%d", day, mouth, year);

    // Booleans
    personagem->hogwartsStaff = (info[7][0] == 'F') ? false : true;
    personagem->alive = (info[10][0] == 'F') ? false : true;
    personagem->wizard = (info[17][0] == 'F') ? false : true;

    // Alternate names
    personagem->alternate_names = (Lista *)malloc(sizeof(Lista));
    iniciar(personagem->alternate_names, 15);

    String tmp = replaceAll("[", "", info[2]);
    tmp = replaceAll("]", "", tmp);
    size_t sizeAltNames = numOfSpaces(',', tmp);
    String *alternateNamesArray = split(',', tmp);
    for (size_t i = 0; i < sizeAltNames; i++)
    {
        String tmpAlt = trimWhiteSpace(alternateNamesArray[i]);
        tmpAlt = replaceAll("'", "", tmpAlt);
        if (tmpAlt != NULL)
        {
            inserirFim(tmpAlt, personagem->alternate_names, sizeAltNames);
        }
        free(tmpAlt);
    }

    // Alternate actors
    personagem->alternate_actors = (Lista *)malloc(sizeof(Lista));
    iniciar(personagem->alternate_actors, 15);

    String tmp2 = replaceAll("[", "", info[11]);
    tmp2 = replaceAll("]", "", tmp2);
    size_t sizeAltActors = numOfSpaces(',', tmp2);
    String *alternateActorsArray = split(',', tmp2);

    for (size_t i = 0; i < sizeAltActors; i++)
    {
        String tmp2Alt = (String)malloc(300 * sizeof(char));
        alternateActorsArray[i] = trimWhiteSpace(alternateActorsArray[i]);
        strcpy(tmp2Alt, alternateActorsArray[i]);

        tmp2Alt = replaceAll("'", "", tmp2Alt);
        if (tmp2Alt != NULL)
        {
            inserirFim(tmp2Alt, personagem->alternate_actors, sizeAltActors);
        }
        free(tmp2Alt);
    }

    free(tmp);
    free(tmp2);
}

void print(Personagem personagem)
{
    printf("[%s ## %s ## {", personagem.id, personagem.name);
    for (int i = 0; i < personagem.alternate_names->tamanho; i++)
    {
        if (i == (personagem.alternate_names->tamanho - 1))
        {
            printf("%s", personagem.alternate_names->elemento[i]);
        }
        else
        {
            printf("%s, ", personagem.alternate_names->elemento[i]);
        }
    }

    printf("} ## %s ## %s ## %s ## %s ## ",
           personagem.house, personagem.ancestry, personagem.species, personagem.patronus);
    if (personagem.hogwartsStaff)
    {
        printf("true ## ");
    }
    else
    {
        printf("false ## ");
    }
    printf("%s ## %s ## ", personagem.hogwartsStudent, personagem.actorName);
    if (personagem.alive)
    {
        printf("true ## ");
    }
    else
    {
        printf("false ## ");
    }
    printf("%s ## %d ## %s ## %s ## %s ## ", personagem.dateOfBirth, personagem.yearOfBirth, personagem.eyeColour,
           personagem.gender, personagem.hairColour);

    if (personagem.wizard)
    {
        printf("true]\n");
    }
    else
    {
        printf("false]\n");
    }
}

/*
    ██████╗  █████╗ ██████╗ ██╗██╗  ██╗███████╗ ██████╗ ██████╗ ████████╗
    ██╔══██╗██╔══██╗██╔══██╗██║╚██╗██╔╝██╔════╝██╔═══██╗██╔══██╗╚══██╔══╝
    ██████╔╝███████║██║  ██║██║ ╚███╔╝ ███████╗██║   ██║██████╔╝   ██║   
    ██╔══██╗██╔══██║██║  ██║██║ ██╔██╗ ╚════██║██║   ██║██╔══██╗   ██║   
    ██║  ██║██║  ██║██████╔╝██║██╔╝ ██╗███████║╚██████╔╝██║  ██║   ██║   
    ╚═╝  ╚═╝╚═╝  ╚═╝╚═════╝ ╚═╝╚═╝  ╚═╝╚══════╝ ╚═════╝ ╚═╝  ╚═╝   ╚═╝   
*/

// ---------------- Comparar Strings em uma Matriz -------------
bool strCompare(String str1, String str2)
{
    for (size_t i = 0; i < strlen(str1) && i < strlen(str2); i++)
    {
        if (str1[i] != str2[i])
        {
            return false;
        }
    }
    return true;
}

// ----------------- Qual o maior -----------------------
bool draw(String str1, String str2)
{
    for (size_t i = 0; i < strlen(str1) && i < strlen(str2); i++)
    {
        if ((countComp++ >= 0) && (str1[i] > str2[i]))
        {
            return true;
        }
        else if ((countComp++ >= 0) && (str1[i] < str2[i]))
        {
            return false;
        }
    }
    return false;
}

bool isBigger(String str1, String str2, Personagem p1, Personagem p2)
{
    for (size_t i = 0; i < strlen(str1) && i < strlen(str2); i++)
    {
        if ((countComp++ >= 0) && (str1[i] > str2[i]))
        {
            return true;
        }
        else if ((countComp++ >= 0) && (str1[i] < str2[i]))
        {
            return false;
        }
    }
    return draw(p1.name, p2.name);
}

// -------------- Swap -------------------
void swap(String *idArray, int i, int j)
{
    countMov += 3;
    char tmp[300];
    strcpy(tmp, idArray[i]);
    strcpy(idArray[i], idArray[j]);
    strcpy(idArray[j], tmp);
}

// ------------ pegar o maior tamanho de string ---------------
size_t getMaximiliam(char *arr[], int n) {
    size_t maximiliam = strlen(arr[0]);
    for (int i = 1; i < n; i++) {
        countComp++;
        if (strlen(arr[i]) > maximiliam)
            maximiliam = strlen(arr[i]);
    }
    return maximiliam;
}

void countSort(char *arr[], int n, size_t exp) {
    // alocar variaveis
    char **tmp = NULL;
    int *count = NULL;
    tmp = (char **)malloc(n * sizeof(char *));
    count = (int *)malloc(257 * sizeof(int));

    // inicializar vetor de count com 0
    for (int i = 0; i < 257; i++) {
        count[i] = 0;
    }

    // adicionar elementos iguais a i
    for (int j = 0; j < n; j++) {
        countComp++;
        count[exp < strlen(arr[j]) ? (int)(unsigned char)arr[j][exp] + 1 : 0]++;
    }

    // adicionar o numero de elementos em count
    for (int f = 1; f < 257; f++) {
        count[f] += count[f - 1];
    }

    // ordenar
    for (int r = n - 1; r >= 0; r--) {
        countMov++;
        countComp++;
        tmp[count[exp < strlen(arr[r]) ? (int)(unsigned char)arr[r][exp] + 1 : 0] - 1] = arr[r];
        count[exp < strlen(arr[r]) ? (int)(unsigned char)arr[r][exp] + 1 : 0]--;
    }

    // voltar ao array original
    for (int l = 0; l < n; l++) {
        countMov++;
        arr[l] = tmp[l];
    }

    // desalocar memoria
    free(tmp);
    free(count);
}

// --------------------- radixsort --------------------------
void radixSort(char *arr[], int n) {
    size_t maximiliam = getMaximiliam(arr, n);
    for (size_t digit = maximiliam; digit > 0; digit--) {
        countSort(arr, n, digit - 1);
    }
}

/*
    ███╗   ███╗ █████╗ ██╗███╗   ██╗
    ████╗ ████║██╔══██╗██║████╗  ██║
    ██╔████╔██║███████║██║██╔██╗ ██║
    ██║╚██╔╝██║██╔══██║██║██║╚██╗██║
    ██║ ╚═╝ ██║██║  ██║██║██║ ╚████║
    ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝
*/

int main(int argc, char const *argv[])
{
    // ----------------- ler aquivo characters.csv --------------------
    FILE *arq = fopen("tmp/characters.csv", "r");
    if (arq == NULL)
    {
        printf("Erro ao abrir o arquivo.");
    }

    char line[1000];
    fgets(line, sizeof(line), arq);
    Personagem personagem;
    reset(&personagem);

    start(personagem);

    while (fgets(line, sizeof(line), arq) != NULL)
    {
        reset(&personagem);      // resetar as variaveis
        read(line, &personagem); // ler do arquivo e armazenar as variaveis
        addEnd(personagem);      // adicionar na lista encadeada
        // mostrar(&lista);
        // print(&personagem);
    }

    fclose(arq);

    // ----------------- Ler Entradas ate FIM ---------------
    clock_t startTime = clock();
    char input[300];
    scanf("%99s", input);

    String *idArray = (String *)malloc(28 * sizeof(String));
    for (size_t i = 0; i < 28; i++)
    {
        idArray[i] = (char *)malloc(300 * sizeof(char));
    }

    int x = 0;
    while (strcmp(input, "FIM") != 0 && x < 28)
    {
        if (strcmp(input, "FIM") != 0 && x < 28)
        {
            strcpy(idArray[x], input);
            x++;
        }
        scanf("%s", input);
    }

    // ----------------- Realizar a ordenacao --------------------------
    radixSort(idArray, x);

    // --------------- Exibir saida ----------------------
    Personagem personagemOut;
    for (int i = 0; i < x; i++)
    {
        personagemOut = getElementByID(idArray[i]);
        print(personagemOut);
    }

    clock_t endTime = clock();
    double execTime = (double)(endTime - startTime) / CLOCKS_PER_SEC;
    execTime *= 1000;

    // ------------ Escrever no arquivo --------------
    arq = fopen("matricula_radixsort.txt", "wt");
    fprintf(arq, "815373\t %d \t %d \t%fms", countComp, countMov, execTime);

    // -------------------- Desalocar memoria ---------------------
    for (size_t i = 0; i < 28; i++)
    {
        free(idArray[i]);
    }
    free(idArray);
    return 0;
}
