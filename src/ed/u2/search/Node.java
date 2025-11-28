package ed.u2.search;

/**
 * Nodo para listas enlazadas, adaptado para trabajar solamente con int.
 * Archivo: Node.java
 */
public class Node {
    public int value;
    public Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }

    public int getValue() {
        return value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node(" + value + ")";
    }
}
