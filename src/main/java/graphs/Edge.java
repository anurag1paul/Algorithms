package graphs;

import java.util.Objects;

public class Edge {

    public final int src;
    public final int dst;
    private final double weight;

    Edge(int src, int dst, double weight){
        this.src = src;
        this.dst = dst;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Edge){
            Edge edge = (Edge)o;
            if(((this.src == edge.src) && (this.dst == edge.dst)) || ((this.src == edge.dst) && (this.dst == edge.src)))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        if(src <= dst)
            return Objects.hash(src, dst);
        else
            return Objects.hash(dst, src);
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)",src, dst);
    }
}
