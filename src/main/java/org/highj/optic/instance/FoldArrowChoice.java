package org.highj.optic.instance;

import org.highj.data.Either;
import org.highj.data.tuple.T2;
import org.highj.hkt.__2;
import org.highj.optic.Fold;
import org.highj.optic.Getter;
import org.highj.typeclass2.arrow.ArrowChoice;

import java.util.function.Function;

import static org.highj.Hkt.asFold;

public interface FoldArrowChoice extends ArrowChoice<Fold.µ> {

    @Override
    default  <B, C, D> __2<Fold.µ, Either<B, C>, D> fanin(final __2<Fold.µ, B, D> f,
                                                        final __2<Fold.µ, C, D> g) {
        return asFold(f).sum(asFold(g));
    }

    @Override
    default <B, C, D> __2<Fold.µ, B, D> dot(final __2<Fold.µ, C, D> cd, final __2<Fold.µ, B, C> bc) {
        return asFold(bc).composeFold(asFold(cd));
    }

    @Override
    default <B> __2<Fold.µ, B, B> identity() {
        return Fold.id();
    }

    @Override
    default <B, C, D> __2<Fold.µ, T2<B, D>, T2<C, D>> first(final __2<Fold.µ, B, C> arrow) {
        return asFold(arrow).first();
    }

    @Override
    default <B, C, D> __2<Fold.µ, T2<D, B>, T2<D, C>> second(final __2<Fold.µ, B, C> arrow) {
        return asFold(arrow).second();
    }

    @Override
    default <B, C> __2<Fold.µ, B, C> arr(final Function<B, C> fn) {
        return Getter.getter(fn).asFold();
    }

    @Override
    default <B, C, D> __2<Fold.µ, Either<B, D>, Either<C, D>> left(final __2<Fold.µ, B, C> arrow) {
        return asFold(arrow).left();
    }

    @Override
    default <B, C, D> __2<Fold.µ, Either<D, B>, Either<D, C>> right(final __2<Fold.µ, B, C> arrow) {
        return asFold(arrow).right();
    }
}
