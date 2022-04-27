package org.highj.optic.instance;

import org.highj.data.tuple.T2;
import org.highj.hkt.__2;
import org.highj.optic.Getter;
import org.highj.typeclass2.arrow.Arrow;

import java.util.function.Function;

import static org.highj.Hkt.asGetter;
import static org.highj.optic.Getter.getter;
import static org.highj.optic.Getter.id;

public interface GetterArrow extends Arrow<Getter.µ> {
    @Override
    default  <B, C, D> __2<Getter.µ, B, D> dot(final __2<Getter.µ, C, D> cd, final __2<Getter.µ, B, C> bc) {
        return asGetter(bc).composeGetter(asGetter(cd));
    }

    @Override
    default <B> __2<Getter.µ, B, B> identity() {
        return id();
    }

    @Override
    default <B, C, D> __2<Getter.µ, T2<B, D>, T2<C, D>> first(final __2<Getter.µ, B, C> arrow) {
        return asGetter(arrow).first();
    }

    @Override
    default <B, C, D> __2<Getter.µ, T2<D, B>, T2<D, C>> second(final __2<Getter.µ, B, C> arrow) {
        return asGetter(arrow).second();
    }

    @Override
    default <B, C> __2<Getter.µ, B, C> arr(final Function<B, C> fn) {
        return getter(fn);
    }

    @Override
    default <B, C, BB, CC> __2<Getter.µ, T2<B, BB>, T2<C, CC>> split(final __2<Getter.µ, B, C> arr1,
                                                                    final __2<Getter.µ, BB, CC> arr2) {
        return asGetter(arr1).product(asGetter(arr2));
    }
}
