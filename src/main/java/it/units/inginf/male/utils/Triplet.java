package it.units.inginf.male.utils;

/**
 *
 * @author nvhuy9527
 */
public class Triplet<F, S, T> {
    private F first;
    private S second;
    private T third;

    public Triplet(F first, S second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }
    
    public T getThird() {
        return third;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Triplet<F, S, T> other = (Triplet<F, S, T>) obj;
        if (this.first != other.first && (this.first == null || !this.first.equals(other.first))) {
            return false;
        }
        if (this.second != other.second && (this.second == null || !this.second.equals(other.second))) {
            return false;
        }
        if (this.third != other.third && (this.third == null || !this.third.equals(other.third))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.first != null ? this.first.hashCode() : 0);
        hash = 29 * hash + (this.second != null ? this.second.hashCode() : 0);
        hash = 29 * hash + (this.third != null ? this.third.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Triplet{" + "first=" + first + " second=" + second + " third= "+third+'}';
    }

}
