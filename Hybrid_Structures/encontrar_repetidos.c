#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct Celula
{
    int numero;
    struct Celula*prox;
}Celula;

Celula* newCelula(int element_param){
    Celula* new = (Celula*)malloc(sizeof(Celula));
    new->numero = element_param;
    new->prox = NULL;
}

Celula* primeiro;
Celula* ultimo;

void start(){
    primeiro = newCelula(-1);
    ultimo = primeiro;
}

void inserirFim_lista(int element_param){
    ultimo->prox = newCelula(element_param);
    ultimo = ultimo->prox;
}

void mostrar_lista(){
    for (Celula* i = primeiro->prox; i != NULL; i = i->prox)
    {
        printf("%d ", i->numero);
    }
    printf("\n");
}

int lista_tam(){
    int count = 0;
    for (Celula* i = primeiro->prox; i != NULL; i = i->prox)
    {
        count++;
    }
    return count;
}

typedef struct CelulaMatriz
{
    int numero;
    struct CelulaMatriz* prox, *ant;
    struct CelulaMatriz* sup, *inf;
}CelulaMatriz;

CelulaMatriz* newCelula_matriz(int numero_param){
    CelulaMatriz* newMatrix = (CelulaMatriz*)malloc(sizeof(CelulaMatriz));
    if (newMatrix == NULL) {
        perror("Falha na alocação de memória");
        exit(EXIT_FAILURE);
    }
    newMatrix->numero = numero_param;
    newMatrix->ant = newMatrix->inf = newMatrix->prox = newMatrix->sup = NULL;
    return newMatrix;
}

CelulaMatriz*inicio;
int row, column;

void start_matriz(int row_param, int column_param){
    row = row_param;
    column = column_param;
    inicio = newCelula_matriz(1);
    CelulaMatriz* tmp = inicio;
    CelulaMatriz* index_I = inicio;
    CelulaMatriz* index_J = inicio;

    for (size_t i = 0; i < row; i++)
    {
        for (size_t j = 0; j < column-1; j++)
        {
            index_J->prox = newCelula_matriz(j+2);
            index_J->prox->ant = index_J;
            index_J = index_J->prox;
            if (i > 0)
            {
                tmp->inf = index_J;
                index_J->sup = tmp;
                tmp = tmp->prox;
            }
        }
        if (i < row-1)
        {
            index_I->inf = newCelula_matriz(i+3);
            index_I->inf->sup = index_I;
            tmp = index_I->prox;
            index_I = index_I->inf;
            index_J = index_I;
        }
        
    }
}

CelulaMatriz* inserir_matriz_AUX(int x_param, int i_param, int j_param, CelulaMatriz*inicio){
    CelulaMatriz* tmp = inicio;
    for (size_t i = 0; i < i_param; i++)
    {
        tmp = tmp->inf;
    }
    for (size_t j = 0; j < j_param; j++)
    {
        tmp = tmp->prox;
    }
    tmp->numero = x_param;
    return tmp;
}

void inserir_matriz(int x_param, int i_param, int j_param){
    inicio = inserir_matriz_AUX(x_param, i_param, j_param, inicio);
}

void mostrar_matriz(){
    CelulaMatriz* index_I = inicio;
    int i = 0, j = 0;
    while (index_I != NULL)
    {
        CelulaMatriz* index_J = index_I;
        printf("Linha %d: ", i++);
        while (index_J != NULL)
        {
            printf("%d ", index_J->numero);
            index_J = index_J->prox;
        }
        printf("\n");
        index_I = index_I->inf;
    }
}

typedef struct No
{
    int numero;
    struct No* dir, *esq;
}No;

No* newNo(int numero_param){
    No* new = (No*)malloc(sizeof(No));
    new->numero = numero_param;
    new->dir = new->esq = NULL;
}

No* raiz;

void start_tree(){
    raiz = NULL;
}

No* inserir_tree_AUX(int element_param, No* no){
    if (no == NULL)
    {
        no = newNo(element_param);
    }else if (element_param > no->numero)
    {
        no->dir = inserir_tree_AUX(element_param, no->dir);
    }else{
        no->esq = inserir_tree_AUX(element_param, no->esq);
    }
    return no;
}

void inserir_tree(int element_param){
    raiz = inserir_tree_AUX(element_param, raiz);
}

void mostrar_tree_AUX(No* no){
    if (no != NULL)
    {
        mostrar_tree_AUX(no->esq);
        printf("%d ", no->numero);
        mostrar_tree_AUX(no->dir);
    }
}

void mostrar_tree(){
    mostrar_tree_AUX(raiz);
    printf("\n");
}

bool encontrar_repetido_tree(No* no, int number){
    if (no == NULL)
    {
        return false;
    }
    if (number == no->numero)
    {
        return true;
    }
    return encontrar_repetido_tree(no->esq, number) || encontrar_repetido_tree(no->dir, number);
}
Celula* encontrarRepetidos(No* raiz, CelulaMatriz*inicio){
    start();
    Celula* lista = NULL;
    CelulaMatriz* index_I = inicio;
    while (index_I != NULL)
    {
        CelulaMatriz* index_J = index_I;
        while (index_J != NULL)
        {
            bool resp = encontrar_repetido_tree(raiz, index_J->numero);
            if (resp)
            {
                inserirFim_lista(index_J->numero);
            }
            index_J = index_J->prox;
        }
        index_I = index_I->inf;
    }
    return lista;
}

void selecao(){
    for (Celula* i = primeiro->prox; i != NULL; i = i->prox)
    {
        Celula* menor = i;
        for (Celula* j = i->prox; j != NULL; j = j->prox)
        {
            if (menor->numero > j->numero)
            {
                menor = j;
            }
        }
        if (i != menor)
        {
            int tmp = i->numero;
            i->numero = menor->numero;
            menor->numero = tmp;
        }
        
    }
}

int main(void){
    start_tree();
    start_matriz(3, 3);
    inserir_tree(2);
    inserir_tree(3);
    inserir_tree(1);
    inserir_tree(7);
    inserir_tree(6);
    inserir_tree(9);

    mostrar_matriz();
    mostrar_tree();

    Celula *lista = encontrarRepetidos(raiz, inicio);
    int size = lista_tam();

    selecao();
    mostrar_lista();
    printf("teste");

    free(inicio);
    free(raiz);
    return 0;
}