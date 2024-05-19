/**
 * ordenar_vetores
 */
public class ordenar_vetores {

    public static int[] vetorOrdenado(int[] vetA, int[] vetB){
        int[]resp = new int[vetA.length + vetB.length];
        int i = 0, j = vetA.length-1, k = vetB.length-1;
        for (; i < resp.length && j >= 0 && k >= 0; i++) {
            if (vetA[j] > vetB[k]) {
                resp[i] = vetB[k];
                k--;
            }else{
                resp[i] = vetA[j];
                j--;
            }
        }

        while (k >= 0) {
            resp[i++] = vetB[k--];
        }

        while (j >= 0) {
            resp[i++] = vetA[j--];
        }
        return resp;
    }
    public static void main(String[] args) {
        int[] vetA = {46, 38, 22, 10};
        int[] vetB = {57, 33, 21};

        int[] resp = vetorOrdenado(vetA, vetB);

        for (int i = 0; i < resp.length; i++) {
            System.out.print(resp[i] + " ");
        }
    }
}