package ed.u2.search;

public class SearchResult {
    public final int index;      // índice encontrado o -1
    public final long compares;  // comparaciones realizadas

    public SearchResult(int index, long compares) {
        this.index = index;
        this.compares = compares;
    }

    @Override
    public String toString() {
        return String.format("index=%d, compares=%d", index, compares);
    }
}