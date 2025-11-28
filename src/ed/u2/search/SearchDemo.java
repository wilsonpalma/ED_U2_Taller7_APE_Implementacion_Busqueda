package ed.u2.search;

import java.util.Arrays;
import java.util.List;

public class SearchDemo {

    public static void main(String[] args) {

        // ================== DATASETS ==================
        int[][] datasets = {
                {8, 3, 6, 3, 9},   // A
                {5, 4, 3, 2, 1},   // B (inverso)
                {1, 2, 3, 4, 5},   // C (ordenado)
                {2, 2, 2, 2}        // D (duplicados)
        };

        String[] names = {"A", "B", "C", "D"};
        int[] keys = {7, 5, 2, 42};

        System.out.println("========== PRUEBAS SOBRE ARREGLOS ==========");

        for (int d = 0; d < datasets.length; d++) {
            int[] arr = datasets[d];
            System.out.println("\nDataset " + names[d] + ": " + Arrays.toString(arr));

            for (int key : keys) {
                System.out.println("Clave = " + key);

                System.out.println("  Primera ocurrencia: "
                        + SearchUtils.indexOfFirst(arr, key));

                System.out.println("  Última ocurrencia: "
                        + SearchUtils.indexOfLast(arr, key));

                System.out.println("  Secuencial clásica: "
                        + SearchUtils.sequentialClassic(arr, key));

                System.out.println("  Secuencial centinela: "
                        + SearchUtils.sequentialWithSentinel(Arrays.copyOf(arr, arr.length), key));

                boolean sorted = isSorted(arr);
                if (sorted) {
                    System.out.println("  Búsqueda binaria: "
                            + SearchUtils.binarySearch(arr, key));
                } else {
                    System.out.println("  Búsqueda binaria: (no aplicable - arreglo no ordenado)");
                }
            }
        }

        // ================== PRUEBAS SOBRE SLL ==================
        System.out.println("\n========== PRUEBAS SOBRE SIMPLELINKEDLIST ==========");

        SimpleLinkedList list = SimpleLinkedList.fromArray(new int[]{3, 1, 3, 2});
        System.out.println("Lista: " + list);

        // Primera ocurrencia de 3
        Node first = list.findNode(3);
        System.out.println("findNode(3) -> " + (first != null ? first : "null"));

        // Última ocurrencia de 3
        Node last = list.findLastNode(3);
        System.out.println("findLastNode(3) -> " + (last != null ? last : "null"));

        // findAll nodos < 3
        List<Node> lessThan3 = list.findAll(n -> n.getValue() < 3);
        System.out.println("findAll(val < 3) -> " + lessThan3);

        System.out.println("\n========== FIN DE LAS PRUEBAS ==========");
    }

    // Método auxiliar para verificar si un arreglo está ordenado
    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }
}
