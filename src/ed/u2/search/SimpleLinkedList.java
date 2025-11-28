package ed.u2.search;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

/**
 * SimpleLinkedList adaptada para trabajar únicamente con int (no genéricos).
 * Archivo: SimpleLinkedList.java
 *
 * Mantiene las operaciones que tenías: pushFront, pushBack, find (node/value),
 * remove, size, isEmpty, clear, findByIndex, getHead, toArray, fromArray, etc.
 */
public class SimpleLinkedList {
    public Node head;

    public SimpleLinkedList() {
        this.head = null;
    }

    // Inserta al inicio
    public void pushFront(int newData) {
        Node newNode = new Node(newData);
        newNode.next = head;
        head = newNode;
    }

    // Inserta al final
    public void pushBack(int newData) {
        Node newNode = new Node(newData);
        if (head == null) {
            head = newNode;
            return;
        }
        Node last = head;
        while (last.next != null) {
            last = last.next;
        }
        last.next = newNode;
    }

    // ---------- BÚSQUEDAS ----------
    // Devuelve el primer nodo cuyo value == key, o null si no existe.
    public Node findNode(int key) {
        Node current = head;
        while (current != null) {
            if (current.value == key) return current;
            current = current.next;
        }
        return null;
    }

    // Devuelve el último nodo cuyo value == key, o null si no existe.
    public Node findLastNode(int key) {
        Node current = head;
        Node last = null;
        while (current != null) {
            if (current.value == key) last = current;
            current = current.next;
        }
        return last;
    }

    // Alternativa compatibilidad: devuelve el value si lo encuentra,
    // lanza NoSuchElementException si no.
    public int findValue(int key) {
        Node n = findNode(key);
        if (n == null) throw new NoSuchElementException();
        return n.value;
    }

    // findAll: devuelve lista de NODES que cumplen el predicado
    // (por ejemplo node -> node.getValue() < 3)
    public List<Node> findAll(Predicate<Node> pred) {
        List<Node> out = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            if (pred.test(cur)) out.add(cur);
            cur = cur.next;
        }
        return out;
    }

    // ---------- REMOVER ----------
    // Elimina el primer nodo cuyo value == key y devuelve su value.
    // Lanza NoSuchElementException si no lo encuentra.
    public int remove(int key) {
        Node current = head;
        Node prev = null;

        if (current != null && current.value == key) {
            head = current.next;
            return current.value;
        }

        while (current != null && current.value != key) {
            prev = current;
            current = current.next;
        }

        if (current == null) {
            throw new NoSuchElementException();
        }

        prev.next = current.next;
        return current.value;
    }

    // ---------- UTILIDADES ----------
    // tamaño (recorrido)
    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {
        head = null;
    }

    // devuelve el valor en la posición index (0-based)
    public int findByIndex(int index) {
        if (index < 0) throw new IndexOutOfBoundsException();
        Node current = head;
        int count = 0;
        while (current != null) {
            if (count == index) return current.value;
            count++;
            current = current.next;
        }
        throw new IndexOutOfBoundsException();
    }

    // Devuelve la cabeza (útil para los algoritmos externos)
    public Node getHead() {
        return head;
    }

    // Convierte la SLL a un arreglo int[]
    public int[] toArray() {
        int n = size();
        int[] a = new int[n];
        Node cur = getHead();
        int i = 0;
        while (cur != null) {
            a[i++] = cur.getValue();
            cur = cur.getNext();
        }
        return a;
    }

    // Construye una lista desde un arreglo int[]
    public static SimpleLinkedList fromArray(int[] a) {
        SimpleLinkedList list = new SimpleLinkedList();
        if (a == null || a.length == 0) return list;
        for (int v : a) list.pushBack(v);
        return list;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = head;
        while (cur != null) {
            sb.append(cur.value);
            if (cur.next != null) sb.append(" -> ");
            cur = cur.next;
        }
        return sb.toString();
    }
}
