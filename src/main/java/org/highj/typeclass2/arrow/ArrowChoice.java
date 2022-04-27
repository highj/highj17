package org.highj.typeclass2.arrow;

import org.highj.hkt.__2;
import org.highj.data.Either;

public interface ArrowChoice<A> extends Arrow<A> {
    public <B, C, D> __2<A, Either<B, D>, Either<C, D>> left(__2<A, B, C> arrow);

    //The default definition should be overridden with a more efficient version if possible
    default <B, C, D> __2<A, Either<D, B>, Either<D, C>> right(__2<A, B, C> arrow) {
        __2<A, Either<D, B>, Either<B, D>> swapDB = arr(Either::swap);
        __2<A, Either<B, D>, Either<C, D>> leftArrow = left(arrow);
        __2<A, Either<C, D>, Either<D, C>> swapCD = arr(Either::swap);
        return then(swapDB, leftArrow, swapCD);
    }

    // (+++) :: a b c -> a b' c' -> a (Either b b') (Either c c')
    default <B, C, BB, CC> __2<A, Either<B, BB>, Either<C, CC>> merge(__2<A, B, C> f, __2<A, BB, CC> g) {
        //  f +++ g = left f >>> right g
        __2<A, Either<B, BB>, Either<C, BB>> leftF = left(f);
        __2<A, Either<C, BB>, Either<C, CC>> rightG = right(g);
        return then(leftF, rightG);
    }

    // (|||) :: a b d -> a c d -> a (Either b c) d
    default <B, C, D> __2<A, Either<B, C>, D> fanin(__2<A, B, D> f, __2<A, C, D> g) {
        // f ||| g = f +++ g >>> arr untag
        __2<A, Either<B, C>, Either<D, D>> fg = merge(f, g);
        __2<A, Either<D, D>, D> untag = arr(Either::unify);
        return then(fg, untag);
    }
}

