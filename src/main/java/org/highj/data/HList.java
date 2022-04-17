package org.highj.data;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.highj.function.F3;
import org.highj.data.tuple.T0;
import org.highj.data.tuple.T2;

/**
 * Translation of https://github.com/functionaljava/functionaljava/blob/master/core/src/main/java/fj/data/hlist/HList.java
 *
 * @param <A> the element type
 */

public interface HList<A extends HList<A>> {

    <E> HCons<E, A> extend(E e);

    <E> Apply<T0, T2<E, A>, HCons<E, A>> extender();

    int size();

    HNil nil = new HNil();

    static HNil nil() {
        return nil;
    }

    static <E, L extends HList<L>> HCons<E, L> cons(final E e, final L l) {
        return new HCons<>(e, l);
    }

    static <E> HCons<E, HNil> single(final E e) {
        return cons(e, nil());
    }

    @FunctionalInterface
    interface HAppend<A, B, C> {
        BiFunction<A, B, C> appendFn();

        default C append(final A a, final B b) {
            return appendFn().apply(a, b);
        }

        static <L extends HList<L>> HAppend<HNil, L, L> append() {
            return () -> (hNil, l) -> l;
        }

        static <X, A extends HList<A>, B, C extends HList<C>, H extends HAppend<A, B, C>>
        HAppend<HCons<X, A>, B, HCons<X, C>> append(final H h) {
            return () -> (HCons<X, A> c, B l) -> cons(c.head(), h.append(c.tail(), l));
        }
    }

    @FunctionalInterface
    interface Apply<F$, A, R> {
        R apply(F$ f, A a);

        static <X> Apply<T0, X, X> id() {
            return (f, x) -> x;
        }

        static <X, Y, Z> Apply<T0, T2<Function<X, Y>, Function<Y, Z>>, Function<X, Z>> comp() {
            return (f, fs) -> fs._1().andThen(fs._2());
        }

        static <E, L extends HList<L>> Apply<T0, T2<E, L>, HCons<E, L>> cons() {
            return (f, p) -> HList.cons(p._1(), p._2());
        }

        static <A, B, C> Apply<HAppend<A, B, C>, T2<A, B>, C> append() {
            return (f, p) -> f.append(p._1(), p._2());
        }
    }

    @FunctionalInterface
    interface HFoldr<G, V, L, R> {

        F3<G, V, L, R> foldRightFn();

        static <G, V> HFoldr<G, V, HNil, V> hFoldr() {
            return () -> (f, v, hNil) -> v;
        }

        static <E, G, V, L extends HList<L>, R, RR,
                H extends HFoldr<G, V, L, R>,
                PP extends Apply<G, T2<E, R>, RR>>
        HFoldr<G, V, HCons<E, L>, RR> hFoldr(final PP p, final H h) {
            return () -> (f, v, c) -> p.apply(f, T2.of(c.head(), h.foldRight(f, v, c.tail())));
        }

        default R foldRight(final G f, final V v, final L l) {
            return foldRightFn().apply(f, v, l);
        }

    }

    /**
     * The nonempty list
     */
    record HCons<H, T extends HList<T>>(H head, T tail) implements HList<HCons<H, T>> {

        public <X> Apply<T0, T2<X, HCons<H, T>>, HCons<X, HCons<H, T>>> extender() {
            return Apply.cons();
        }

        @Override
        public int size() {
            return 1 + tail.size();
        }

        public <X> HCons<X, HCons<H, T>> extend(final X e) {
            return cons(e, this);
        }
    }

    /**
     * The empty list
     */
    record HNil() implements HList<HNil> {

        public <E> HCons<E, HNil> extend(final E e) {
            return cons(e, this);
        }

        public <E> Apply<T0, T2<E, HNil>, HCons<E, HNil>> extender() {
            return Apply.cons();
        }

        @Override
        public int size() {
            return 0;
        }
    }
}
