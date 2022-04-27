package org.highj.typeclass1.monad;


import org.highj.hkt.__;
import org.highj.data.tuple.T0;

import java.util.function.Function;

public interface MonadZero<M> extends Monad<M> {
    //MonadPlus.mzero (Control.Monad)
    <A> __<M, A> mzero();

    //guard (Control.Monad)
    //for MonadZero
    default __<M, T0> guard(boolean condition) {
        return condition ? pure(T0.unit) : MonadZero.this.mzero();
    }

    //mfilter (Control.Monad)
    //for MonadZero
    default <A> __<M, A> mfilter(Function<A, Boolean> condition, final __<M, A> target) {
        return bind(map(condition, target), b -> b ? target : MonadZero.this.mzero());
    }

}
