package org.highj.control.arrow;

import org.highj.hkt.__;
import org.highj.hkt.__3;
import org.highj.control.arrow.kleisli.KleisliArrow;
import org.highj.control.arrow.kleisli.KleisliProfunctor;
import org.highj.typeclass1.monad.Monad;

import java.util.function.Function;

public record Kleisli<M, A, B>(
    Function<A, __<M, B>> fn) implements __3<Kleisli.µ, M, A, B>, Function<A, __<M, B>> {

    public interface µ {
    }

    @Override
    public __<M, B> apply(A a) {
        return fn.apply(a);
    }

    public <C> Kleisli<M, C, B> lmap(Function<C, A> f) {
        return new Kleisli<>(fn.compose(f));
    }

    public static <M> KleisliArrow<M> arrow(Monad<M> monad) {
        return () -> monad;
    }

    public static <M> KleisliProfunctor<M> profunctor(Monad<M> monad) {
        return () -> monad;
    }
}

