# Taller 7 — Implementación de algoritmos de búsqueda

**Autor:** Jostin Vasquez, Miguel Veintimilla, Darwin Correa, Wilson Palma.  
**Asignatura:** Estructura de Datos  
**Fecha de entrega:** 28/11/2025

---

## Objetivo
El presente trabajo tiene por objetivo implementar y evaluar distintos algoritmos de búsqueda sobre estructuras de datos lineales: arreglos y listas simplemente enlazadas. Se implementan variantes de búsqueda secuencial (primera y última ocurrencia), búsqueda secuencial con centinela, búsqueda binaria iterativa y utilidades `lowerBound` / `upperBound`. Además, se adapta una implementación de `SimpleLinkedList` y `Node` para operar exclusivamente con valores de tipo `int`.

---

## Resumen de la metodología
1. Se desarrollaron utilidades de búsqueda sobre arreglos (`SearchUtils`) que devuelven resultados junto con el número de comparaciones realizadas para permitir un análisis empírico de eficiencia.  
2. Se adaptó la implementación de la lista simplemente enlazada para trabajar con `int` y se añadieron operaciones de búsqueda que retornan nodos o colecciones de nodos según el caso.  
3. Se ejecutaron pruebas sobre los datasets proporcionados y sobre una lista enlazada de ejemplo; la salida se volcó a un fichero `salida.txt` y, a partir de él, se generó la tabla de evidencias en CSV y en Markdown.

---

## Estructura del repositorio / paquete
Los archivos Java deben colocarse bajo el paquete `ed.u2.search` (ruta `ed/u2/search/`):

```
ed/u2/search/
├─ Node.java
├─ SimpleLinkedList.java
├─ SearchUtils.java
├─ SearchDemo.java
├─ SearchResults.java
```

Archivos auxiliares generados (entorno de trabajo):
```
salida.txt
tablas evidencias.md
informe.pdf
Comparacion secuencial vs sentinela.md
```

---

## Requisitos
- JDK 8 o superior.  
- No se requieren librerías externas.

---

## Compilación y ejecución
Desde la raíz del proyecto (directorio `src` que contiene la carpeta `ed`):

```bash
# Compilar
javac ed/u2/search/*.java

# Ejecutar demo (salida por consola)
java ed.u2.search.SearchDemo

# Volcar salida a archivo
java ed.u2.search.SearchDemo > salida.txt
```

---

## Datasets usados
Los experimentos se realizaron con los siguientes conjuntos de datos:

- **A:** `[8, 3, 6, 3, 9]`  
- **B:** `[5, 4, 3, 2, 1]` (inverso)  
- **C:** `[1, 2, 3, 4, 5]` (ordenado)  
- **D:** `[2, 2, 2, 2]` (duplicados)

Claves probadas: `{7, 5, 2, 42}`.  
Lista enlazada de prueba: `3 -> 1 -> 3 -> 2`, sobre la cual se ejecutaron `findNode(3)`, `findLastNode(3)` y `findAll(val < 3)`.

---

## Funcionalidades implementadas
### SearchUtils.java
- `indexOfFirst(int[] a, int key)` — retorna índice de la primera ocurrencia o `-1`.  
- `indexOfLast(int[] a, int key)` — retorna índice de la última ocurrencia o `-1`.  
- `findAll(int[] a, IntPredicate p)` — retorna lista de índices que cumplen el predicado.  
- `sequentialClassic(int[] a, int key)` — búsqueda secuencial que además devuelve el conteo de comparaciones.  
- `sequentialWithSentinel(int[] a, int key)` — versión con centinela (restaura el último elemento tras la búsqueda).  
- `binarySearch(int[] a, int key)` — búsqueda binaria iterativa con conteo de comparaciones.  
- `lowerBound(int[] a, int key)` y `upperBound(int[] a, int key)`.

### SimpleLinkedList.java y Node.java
- `Node`: nodo con campo `int value` y referencia `Node next`.  
- `SimpleLinkedList`: implementación no genérica basada en `int` con operaciones: `pushFront`, `pushBack`, `findNode`, `findLastNode`, `findAll(Predicate<Node>)`, `remove`, `size`, `toArray`, `fromArray`, `getHead`, etc.

### SearchDemo.java
- Script de demostración que ejecuta las búsquedas sobre cada dataset y sobre la lista enlazada de prueba.  
- Formato de salida pensado para facilitar la generación automática de tablas de evidencias.

---

## Evidencias entregadas
Se adjunta la tabla de evidencias generada a partir de la ejecución y del fichero `salida.txt`. Dicha tabla está disponible en formato Markdown en las rutas indicadas más arriba. La tabla incluye, para cada combinación (dataset, clave): primera/última ocurrencia, índice obtenido por las variantes secuenciales, y número de comparaciones para las variantes que lo reportan. En los casos en que la búsqueda binaria no es aplicable (arreglo no ordenado) se indica `N/A`.

---

## Interpretación y conclusiones
1. **Eficiencia según la estructura de datos:** la búsqueda binaria presenta un número de comparaciones significativamente menor que la búsqueda secuencial cuando se aplica sobre arreglos ordenados (complejidad O(log n)). Sin embargo, su aplicabilidad depende de la precondición de ordenación del arreglo.  
2. **Secuencial clásico vs centinela:** la variante con centinela simplifica el bucle de búsqueda y reduce, en muchos casos, el número de comparaciones al eliminar la comprobación de límites en cada iteración. Los resultados empíricos en la tabla de evidencias permiten observar dicha diferencia de forma cuantitativa.  
3. **Listas enlazadas:** la búsqueda binaria no es adecuada para listas simplemente enlazadas porque el acceso a una posición intermedia no se realiza en tiempo constante; por tanto, la opción práctica es la búsqueda secuencial.  
4. **Duplicados:** cuando existen valores duplicados, las funciones `lowerBound` y `upperBound` permiten localizar con precisión los extremos del rango de ocurrencias usando variantes basadas en búsqueda binaria.

---

## Procedimiento para regenerar las evidencias
1. Compilar y ejecutar `SearchDemo`:  
   ```bash
   java ed.u2.search.SearchDemo > salida.txt
   ```

---

## Observaciones y recomendaciones
- Para experimentos adicionales se recomienda aumentar el tamaño de los datasets y medir tiempos además de comparaciones, con el fin de contrastar comportamiento teórico y empírico.  
- Para presentaciones académicas conviene incluir gráficos que muestren comparaciones promedio de número de comparaciones por algoritmo y por tamaño de entrada.

---

## Licencia
El trabajo y el código pueden utilizarse con fines académicos, citando la autoría correspondiente.
