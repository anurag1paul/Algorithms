package graphs.minCut;

public class Edge implements Comparable<Edge>{

    public final int src;
    public final int dst;

    Edge(int src, int dst){
        this.src = src;
        this.dst = dst;
    }

    @Override
    public int compareTo(Edge edge) {
        if(this.equals(edge))
            return 0;
        else
            return 1;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)",src, dst);
    }
}
