package org.highj.data;

import org.highj.data.instance.map.MapApply;
import org.highj.data.ord.Ordering;
import org.highj.data.tuple.T2;
import org.highj.hkt.__2;
import org.highj.util.Iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

/**
 * A crude, hash-based Map implementation.
 * <p>
 * Note that the provided monadTrans instance could be considered a hack, based on the fact that every
 * Java Object has a hashCode and an equals implementation, which might be rather useless in some cases.
 * </p>
 *
 * @param <A> The element type.
 */
public class Map<A, B> implements __2<Map.µ, A, B>, Iterable<T2<A, B>>, Function<A, Maybe<B>> {

    public interface µ {
    }

    private final static Map<?, ?> EMPTY = new Map<>(Integer.MIN_VALUE, List.Nil(), null, null) {
    };

    private final Map<A, B> left;
    private final Map<A, B> right;
    private final List<T2<A, B>> bucket;
    private final int hc;

    private Map(int hc, List<T2<A, B>> bucket, Map<A, B> left, Map<A, B> right) {
        this.hc = hc;
        this.bucket = bucket;
        this.right = right;
        this.left = left;
    }

    public Maybe<B> apply(A key) {
        if (isEmpty()) {
            return Maybe.Nothing();
        }

        int khc = key.hashCode();
        return switch (Ordering.compare(khc, hc)) {
            case LT -> left.apply(key);
            case GT -> right.apply(key);
            case EQ -> {
                for (T2<A, B> t2 : bucket) {
                    if (key.equals(t2._1())) {
                        yield  Maybe.Just(t2._2());
                    }
                }
                yield  Maybe.Nothing();
            }
        };
    }

    public B getOrElse(A key, B defaultValue) {
        return apply(key).getOrElse(defaultValue);
    }

    public B get(A key) throws NoSuchElementException {
        return apply(key).get();
    }

    public Map<A, B> plus(final A a, final B b) {
        if (isEmpty()) {
            return new Map<>(a.hashCode(), List.of(T2.of(a, b)), Map.empty(), Map.empty());
        }
        int ahc = a.hashCode();
        return switch (Ordering.compare(ahc, hc)) {
            case EQ -> new Map<>(hc, bucket.filter((T2<A, B> ab) -> !ab._1().equals(a)).plus(T2.of(a, b)), left, right);
            case LT -> {
                Map<A, B> newLeft = left.plus(a, b);
                yield left == newLeft ? this : new Map<>(hc, bucket, newLeft, right);
            }
            case GT -> {
                Map<A, B> newRight = right.plus(a, b);
                yield right == newRight ? this : new Map<>(hc, bucket, left, newRight);
            }
        };
    }

    public Map<A, B> plus(T2<A, B> ab) {
        return plus(ab._1(), ab._2());
    }


    public Map<A, B> minus(final A a) {
        if (isEmpty()) {
            return this;
        }
        int ahc = a.hashCode();
        return switch (Ordering.compare(ahc, hc)) {
            case EQ -> {
                List<T2<A, B>> newBucket = bucket.filter((T2<A, B> ab) -> ab._1() != a);
                if (bucket == newBucket) {
                    yield this;
                } else if (!newBucket.isEmpty()) {
                    yield new Map<>(hc, newBucket, left, right);
                } else if (left.isEmpty()) {
                    yield right;
                } else if (right.isEmpty()) {
                    yield left;
                } else {
                    T2<Map<A, B>, Map<A, B>> pair = right.removeMin();
                    yield new Map<>(pair._1().hc, pair._1().bucket, left, pair._2());
                }
            }
            case LT -> {
                Map<A, B> newLeft = left.minus(a);
                yield left == newLeft ? this : new Map<>(hc, bucket, newLeft, right);
            }
            case GT -> {
                Map<A, B> newRight = right.minus(a);
                yield right == newRight ? this : new Map<>(hc, bucket, left, newRight);
            }
        };
    }

    private T2<Map<A, B>, Map<A, B>> removeMin() {
        if (left.isEmpty()) {
            return T2.of(this, right);
        } else {
            T2<Map<A, B>, Map<A, B>> pair = left.removeMin();
            return T2.<Map<A, B>, Map<A, B>>of(pair._1(), new Map<>(hc, bucket, pair._2(), right));
        }
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }

    @SuppressWarnings("unchecked")
    public static <A, B> Map<A, B> empty() {
        return (Map<A, B>) EMPTY;
    }

    public static <A, B> Map<A, B> of() {
        return empty();
    }

    @SafeVarargs
    public static <A, B> Map<A, B> of(T2<A, B>... abs) {
        return Map.<A, B>empty().plus(abs);
    }

    public static <A, B> Map<A, B> of(Iterable<T2<A, B>> abs) {
        return Map.<A, B>empty().plus(abs);
    }

    @SafeVarargs
    public static <A, B> Map<A, B> ofKeys(Function<A, B> fn, A... keys) {
        Map<A, B> result = empty();
        for (A key : keys) {
            result = result.plus(key, fn.apply(key));
        }
        return result;
    }

    @SafeVarargs
    public static <A, B> Map<A, B> ofValues(Function<B, A> fn, B... values) {
        Map<A, B> result = empty();
        for (B value : values) {
            result = result.plus(fn.apply(value), value);
        }
        return result;
    }

    @SafeVarargs
    public static <A, B, C> Map<A, B> of(Function<C, T2<A, B>> fn, C... cs) {
        Map<A, B> result = empty();
        for (C c : cs) {
            result = result.plus(fn.apply(c));
        }
        return result;
    }


    @SafeVarargs
    public final Map<A, B> plus(T2<A, B>... abs) {
        Map<A, B> result = this;
        for (T2<A, B> ab : abs) {
            result = result.plus(ab);
        }
        return result;
    }

    @SafeVarargs
    public final Map<A, B> minus(A... as) {
        Map<A, B> result = this;
        for (A a : as) {
            result = result.minus(a);
        }
        return result;
    }

    public Map<A, B> plus(Iterable<T2<A, B>> abs) {
        Map<A, B> result = this;
        for (T2<A, B> ab : abs) {
            result = result.plus(ab);
        }
        return result;
    }

    public Map<A, B> minus(Iterable<A> as) {
        Map<A, B> result = this;
        for (A a : as) {
            result = result.minus(a);
        }
        return result;
    }

    public int size() {
        return isEmpty() ? 0 : 1 + left.size() + right.size();
    }

    public Function<A, Maybe<B>> apply() {
        return Map.this;
    }

    public Iterator<T2<A, B>> iterator() {
        return isEmpty() ? Iterators.emptyIterator() : Iterators.concat(left.iterator(), bucket.iterator(), right.iterator());
    }

    public String toString() {
        if (isEmpty()) return "Map()";
        StringBuilder sb = new StringBuilder();
        for (T2<A, B> ab : this) {
            sb.append(sb.length() == 0 ? "Map(" : ",").append(ab._1()).append("->").append(ab._2());
        }
        return sb.append(')').toString();
    }

    public Set<A> keys() {
        Set<A> keySet = Set.empty();
        for (T2<A, B> t2 : this) {
            keySet = keySet.plus(t2._1());
        }
        return keySet;
    }

    public Set<B> values() {
        Set<B> valueSet = Set.empty();
        for (T2<A, B> t2 : this) {
            valueSet = valueSet.plus(t2._2());
        }
        return valueSet;
    }

    public <C> Map<A, C> map(Function<? super B, ? extends C> fn) {
        if (isEmpty()) {
            return Map.empty();
        } else {
            List<T2<A, C>> newBucket = List.of();
            for (T2<A, B> t2 : bucket) {
                newBucket = newBucket.plus(t2.map_2(fn));
            }
            return new Map<A, C>(hc, newBucket, left.map(fn), right.map(fn));
        }
    }

    private static <S> MapApply<S> mapApply() {
        return new MapApply<>() {
        };
    }
}
