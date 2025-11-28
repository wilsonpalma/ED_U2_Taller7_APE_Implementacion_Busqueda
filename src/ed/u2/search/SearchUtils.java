package ed.u2.search;

import java.util.*;
import java.util.function.IntPredicate;

/**
 * Utilidades de búsqueda sobre arrays.
 * Incluye instrumentación para contar comparaciones (útil para comparar
 * secuencial clásica vs centinela).
 */
public class SearchUtils {

    // ---------- Paso 1: Primera ocurrencia (array) ----------
    public static int indexOfFirst(int[] a, int key) {
        if (a == null) return -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == key) return i;
        }
        return -1;
    }

    // ---------- Paso 2: Última ocurrencia (array) ----------
    // Variante: de atrás hacia adelante
    public static int indexOfLast(int[] a, int key) {
        if (a == null) return -1;
        for (int i = a.length - 1; i >= 0; i--) {
            if (a[i] == key) return i;
        }
        return -1;
    }

    // Alternativa: una sola pasada guardando last
    public static int indexOfLast_singlePass(int[] a, int key) {
        if (a == null) return -1;
        int last = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == key) last = i;
        }
        return last;
    }

    // ---------- Paso 3: findAll por predicado (array) ----------
    public static List<Integer> findAll(int[] a, IntPredicate p) {
        List<Integer> res = new ArrayList<>();
        if (a == null || p == null) return res;
        for (int i = 0; i < a.length; i++) {
            if (p.test(a[i])) res.add(i);
        }
        return res;
    }

    // ---------- Paso 4: Secuencial con centinela (solo arrays) ----------
    // Devuelve SearchResult con índice y #comparaciones.
    // index = -1 si no encontrado.
    // variante clásica con conteo:
    public static SearchResult sequentialClassic(int[] a, int key) {
        long compares = 0;
        if (a == null) return new SearchResult(-1, compares);
        for (int i = 0; i < a.length; i++) {
            compares++; // otra comparacion i < a.length
            compares++; // una comparación a[i] == key
            if (a[i] == key) return new SearchResult(i, compares);
        }
        return new SearchResult(-1, compares);
    }

    // variante centinela (colocamos key al final, iteramos sin chequear límites)
    // nota: realiza al menos 1 comparación.
    public static SearchResult sequentialWithSentinel(int[] a, int key) {
        long compares = 0;
        if (a == null) return new SearchResult(-1, compares);
        int n = a.length;
        if (n == 0) return new SearchResult(-1, compares);

        int last = a[n - 1];
        a[n - 1] = key;         // colocar centinela
        int i = 0;
        while (true) {
            compares++;
            if (a[i] == key) break;
            i++;
        }
        a[n - 1] = last;        // restaurar

        if (i < n - 1 || last == key) {
            return new SearchResult(i, compares); // hallazgo real
        } else {
            return new SearchResult(-1, compares); // sólo centinela
        }
    }

    // ---------- Paso 5: Búsqueda binaria (arrays ordenados) ----------
    // Iterativa, devuelve índice o -1; también cuenta comparaciones.
    public static SearchResult binarySearch(int[] a, int key) {
        long compares = 0;
        if (a == null || a.length == 0) return new SearchResult(-1, compares);
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            compares++;
            if (a[mid] == key) return new SearchResult(mid, compares);
            compares++;
            if (a[mid] < key) low = mid + 1;
            else high = mid - 1;
        }
        return new SearchResult(-1, compares);
    }

    // Optional: lowerBound (first >= key) and upperBound (first > key)
    // Devuelven índice en rango [0..n] (n significa "al final")
    public static int lowerBound(int[] a, int key) {
        if (a == null) return 0;
        int low = 0, high = a.length; // búsqueda en [low, high)
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (a[mid] < key) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    public static int upperBound(int[] a, int key) {
        if (a == null) return 0;
        int low = 0, high = a.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (a[mid] <= key) low = mid + 1;
            else high = mid;
        }
        return low;
    }
}
