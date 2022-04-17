package org.highj.control.arrow.cokleisli;

import org.highj.hkt.__;
import org.highj.hkt.__2;
import org.highj.control.arrow.Cokleisli;
import org.highj.data.tuple.T2;
import org.highj.function.F1;
import org.highj.typeclass1.comonad.Comonad;
import org.highj.typeclass2.arrow.Arrow;

import java.util.function.Function;

import static org.highj.Hkt.asCokleisli;

public interface CokleisliArrow<W> extends Arrow<__<Cokleisli.µ, W>> {

    Comonad<W> getW();

    @Override
    default <A, B> Cokleisli<W, A, B> arr(Function<A, B> fn) {
        return new Cokleisli<>(wa -> fn.apply(getW().extract(wa)));
    }

    @Override
    default <A, B, C> Cokleisli<W, T2<A, C>, T2<B, C>> first(__2<__<Cokleisli.µ, W>, A, B> arrow) {
        return split(arrow, new Cokleisli<W, C, C>(getW()::extract));
    }

    @Override
    default <A, B, C> Cokleisli<W, T2<C, A>, T2<C, B>> second(__2<__<Cokleisli.µ, W>, A, B> arrow) {
        return split(new Cokleisli<W, C, C>(getW()::extract), arrow);
    }

    @Override
    default <A, B, AA, BB> Cokleisli<W, T2<A, AA>, T2<B, BB>> split(__2<__<Cokleisli.µ, W>, A, B> arr1, __2<__<Cokleisli.µ, W>, AA, BB> arr2) {
        F1<__<W, A>, B> wab = asCokleisli(arr1)::apply;
        F1<__<W, AA>, BB> waabb = asCokleisli(arr2)::apply;
        F1<__<W, T2<A, AA>>, __<W, A>> fst = wp -> getW().map(T2::_1, wp);
        var one = F1.arrow.dot(wab, fst);
        F1<__<W, T2<A, AA>>, __<W, AA>> snd = wp -> getW().map(T2::_2, wp);
        var two = F1.arrow.dot(waabb, snd);
        return new Cokleisli<>(F1.arrow.fanout(one, two));
    }

    @Override
    default <A, B, C> Cokleisli<W, A, T2<B, C>> fanout(__2<__<Cokleisli.µ, W>, A, B> arr1, __2<__<Cokleisli.µ, W>, A, C> arr2) {
        F1<__<W, A>, B> wab = asCokleisli(arr1)::apply;
        F1<__<W, A>, C> wac = asCokleisli(arr2)::apply;
        return new Cokleisli<>(F1.arrow.fanout(wab, wac));
    }

    @Override
    default <A> Cokleisli<W, A, A> identity() {
        return new Cokleisli<>(getW()::<A>extract);
    }

    @Override
    default <A, B, C> Cokleisli<W, A, C> dot(__2<__<Cokleisli.µ, W>, B, C> bc, __2<__<Cokleisli.µ, W>, A, B> ab) {
        Cokleisli<W, B, C> wbc = asCokleisli(bc);
        Cokleisli<W, A, B> wab = asCokleisli(ab);
        return new Cokleisli<>(wa -> wbc.apply(getW().map(wab, getW().duplicate(wa))));
    }
}
