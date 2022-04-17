package org.highj.typeclass1.monad;

import org.highj.hkt.__;

public interface Applicative<M> extends Apply<M> {

    // pure (Data.Pointed, Control.Applicative)
    <A> __<M, A> pure(A a);

}
