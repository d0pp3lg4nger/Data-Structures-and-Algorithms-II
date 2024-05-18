#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <stdbool.h>
#include <string.h>
#include <ctype.h>

#define true 1
#define false 0
typedef char *String;
typedef bool Boolean;

// ----------------------- LISTA ---------------------

typedef struct ListaAlt
{
    String *elemento;
    int tamanho;
} ListaAlt;

void iniciar(ListaAlt *lista, size_t tamanho)
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
void inserirFimAlt(String elemento, ListaAlt *lista, int tamanho)
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
void mostrarAlt(ListaAlt *lista)
{
    int i;

    for (i = 0; i < lista->tamanho; i++)
    {
        printf("%s ", lista->elemento[i]);
    }
}

void freeList(ListaAlt *lista)
{
    free(lista->elemento);
    lista->tamanho = 0;
}
// ------------------------- PERSONAGEM ---------------------
typedef struct Personagem
{
    char id[40];                // 0
    char name[80];              // 1
    ListaAlt *alternate_names;  // 2
    char house[50];             // 3
    char ancestry[50];          // 4
    char species[50];           // 5
    char patronus[50];          // 6
    Boolean hogwartsStaff;      // 7
    char hogwartsStudent[50];   // 8
    char actorName[80];         // 9
    Boolean alive;              // 10
    ListaAlt *alternate_actors; // 11
    char dateOfBirth[15];       // 12
    int yearOfBirth;            // 13
    char eyeColour[15];         // 14
    char gender[15];            // 15
    char hairColour[15];        // 16
    Boolean wizard;             // 17
} Personagem;

void reset(Personagem *personagem)
{
    personagem->hogwartsStaff = false;
    personagem->alive = false;
    personagem->yearOfBirth = 0;
    personagem->wizard = false;
    personagem->alternate_names = NULL;
    personagem->alternate_actors = NULL;

    personagem->alternate_names = (ListaAlt *)malloc(sizeof(ListaAlt));
    iniciar(personagem->alternate_names, 50);
    personagem->alternate_actors = (ListaAlt *)malloc(sizeof(ListaAlt));
    iniciar(personagem->alternate_actors, 50);
}

typedef struct Lista
{
    Personagem *personagem;
    int tamanho;
    int personagemLength;
} Lista;

Lista *newLista(int tamanho)
{
    Lista *new = (Lista *)malloc(sizeof(Lista));
    new->personagem = (Personagem *)malloc(tamanho * sizeof(Personagem));
    new->personagemLength = tamanho;
    new->tamanho = 0;
    return new;
}

/**
 * Inserir no fim da lista
 */
void inserirFim(Personagem x, Lista *lista)
{
    if (lista->tamanho >= lista->personagemLength)
    {
        printf("Erro ao inserir no fim!");
        exit(1);
    }

    lista->personagem[lista->tamanho] = x;
    lista->tamanho++;
}

/**
 * Procura um elemento por id e o retorna.
 * @param id Elemento a pesquisar.
 * @return <code>true</code> se o elemento existir,
 * <code>false</code> em caso contrario.
 */
Personagem getElementByID(String id, Lista *lista)
{
    for (int i = 0; i < lista->tamanho; i++)
    {
        if (strcmp(lista->personagem[i].id, id) == 0)
        {
            return lista->personagem[i];
        }
    }
    Personagem personagem;
    return personagem;
}
// ------------------------- CELULA ------------------------
typedef struct Celula
{
    Personagem personagem;
    struct Celula* prox;
}Celula;

Celula *newCelula(Personagem personagem){
    Celula* nova = (Celula*)malloc(sizeof(Celula));
    nova->prox = NULL;
    nova->personagem = personagem;
    return nova;
}

#define TAM 5
typedef struct Fila
{
    Celula *primeiro;
    Celula *ultimo;
    int tamanho;
} Fila;

Fila *newFila()
{
    Fila *new = (Fila *)malloc(sizeof(Fila));
    new->primeiro = new->ultimo = NULL;
    new->tamanho = 0;
    return new;
}

/**
 * Remover no inicio da fila
 */
Personagem remover(Fila *fila)
{
    if (fila->primeiro == NULL)
    {
        printf("Erro ao remover no inicio da fila!");
        exit(1);
    }

    Celula*tmp = fila->primeiro;
    Personagem resp = tmp->personagem;
    if (fila->primeiro == fila->ultimo)
    {
        fila->primeiro = fila->ultimo = NULL;
    }
    else
    {
        fila->primeiro = fila->primeiro->prox;
    }
    tmp->prox = NULL;
    free(tmp);
    tmp = NULL;
    fila->tamanho--;
    return resp;
}
/**
 * Inserir no fim da Fila
 */
void inserir(Personagem x, Fila *fila)
{
    Personagem personagem;
    Celula* new = newCelula(x);
    if (fila->tamanho == TAM)
    {
        personagem = remover(fila);
    }

    if (fila->ultimo == NULL)
    {
        fila->primeiro = fila->ultimo = new;
    }else{
        fila->ultimo->prox = new;
        fila->ultimo = new;
    }
    
    fila->ultimo->prox = fila->primeiro;
    fila->tamanho++;
}

/**
 * Mostra os elementos da fila separados por espacos.
 */
void mostrar(Fila *fila)
{
    Celula* i = fila->primeiro;
    printf("[ ");
    do
    {
        printf("%s\n", i->personagem.name);
        i = i->prox;
    } while (i != fila->primeiro);
    
    printf("] \n");
}

String getId(Fila *fila, int pos)
{
    Celula* i = fila->primeiro;
    for (int j = 0; j < pos; j++, i = i->prox);
    
    return i->personagem.id;
}

int calcMedia(Fila *fila)
{
    Celula* i = fila->primeiro;
    int resp = 0, count = 0;
    do
    {
        resp += i->personagem.yearOfBirth;
        count++;
        i = i->prox;
    } while (i != fila->primeiro);
    if (count == 0)
    {
        printf("Erro: Divisão por zero.\n");
        exit(1);
    }
    return (resp / count);
}

// -------- Numero de intervalos na String ----------
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
    if (info[8][0] == 'V')
    {
        strcpy(personagem->hogwartsStudent, "true");
    }
    else
    {
        strcpy(personagem->hogwartsStudent, "false");
    }

    personagem->hogwartsStaff = (info[7][0] == 'F') ? false : true;
    personagem->alive = (info[10][0] == 'F') ? false : true;
    personagem->wizard = (info[17][0] == 'F') ? false : true;

    // Alternate names
    personagem->alternate_names = (ListaAlt *)malloc(sizeof(ListaAlt));
    iniciar(personagem->alternate_names, 15);

    String tmp = replaceAll("[", "", info[2]);
    tmp = replaceAll("]", "", tmp);
    size_t sizeAltNames = numOfSpaces(',', tmp);
    String *alternateNamesArray = split(',', tmp);
    for (size_t i = 0; i < sizeAltNames; i++)
    {
        String tmp2Alt = (String)malloc(300 * sizeof(char));
        String tmpAlt = trimWhiteSpace(alternateNamesArray[i]);
        tmpAlt = replaceAll("'", "", tmpAlt);
        if (tmpAlt != NULL)
        {
            inserirFimAlt(tmpAlt, personagem->alternate_names, sizeAltNames);
        }
        free(tmpAlt);
    }

    // Alternate actors
    personagem->alternate_actors = (ListaAlt *)malloc(sizeof(ListaAlt));
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
            inserirFimAlt(tmp2Alt, personagem->alternate_actors, sizeAltActors);
        }
        free(tmp2Alt);
    }

    free(tmp);
    free(tmp2);
}

void print(Personagem personagem, int index)
{
    printf("[%d ## %s ## %s ## {", index, personagem.id, personagem.name);
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

int main(int argc, char const *argv[])
{
    // ----------------- ler aquivo characters.csv --------------------
    FILE *arq = fopen("/tmp/characters.csv", "r");
    if (arq == NULL)
    {
        printf("Erro ao abrir o arquivo.");
    }

    char line[1000];
    fgets(line, sizeof(line), arq);
    Personagem personagem;
    reset(&personagem);

    Lista *lista = newLista(406);

    while (fgets(line, sizeof(line), arq) != NULL)
    {
        reset(&personagem);            // resetar as variaveis
        read(line, &personagem);       // ler do arquivo e armazenar as variaveis
        inserirFim(personagem, lista); // adicionar na lista encadeada
        // mostrar(&lista);
        // print(&personagem);
    }

    fclose(arq);
    // ----------------- Ler Entradas ate FIM ---------------
    Personagem personagemOut;
    Fila *personagens = newFila();
    char input[100];
    scanf("%99s", input);
    int media = 0;
    while (strcmp(input, "FIM") != 0)
    {
        if (strcmp(input, "FIM") != 0)
        {
            inserir(getElementByID(input, lista), personagens);
            media = calcMedia(personagens);
            printf(">> Year Birthday Average: %d\n", media);
        }
        scanf("%s", input);
    }

    int num = 0;
    scanf("%d", &num);
    Personagem removidos[50];
    int j = 0;
    for (size_t i = 0; i < num; i++)
    {
        char action[10];
        scanf("%s", action);
        if (strcasecmp(action, "I") == 0)
        {
            char id[50];
            scanf("%s", id);
            inserir(getElementByID(id, lista), personagens);
            media = calcMedia(personagens);
            printf(">> Year Birthday Average: %d\n", media);
        }
        if (strcasecmp(action, "R") == 0)
        {
            removidos[j++] = remover(personagens);
            printf("(R) %s\n", removidos[j - 1].name);
        }
    }

    printf("[ Head ]\n");
    int k = 0, m = 0;
    Celula* i = personagens->primeiro;
    do
    {
        print(getElementByID(getId(personagens, m++), lista), k++);
        i = i->prox;
    } while (i != personagens->primeiro);
    
    printf("[ Tail ]\n");

    free(personagens);
    free(lista);
    return 0;
}
