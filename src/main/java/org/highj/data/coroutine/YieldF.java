package org.highj.data.coroutine;

import org.highj.hkt.__2;
import org.highj.data.coroutine.yieldf.YieldFFunctor;

public record YieldF<V, A>(V value, A next) implements __2<YieldF.µ, V, A> {

    public interface µ {
    }

    public static <V, A> YieldF<V, A> yield(V value, A next) {
        return new YieldF<>(value, next);
    }

    public static <V> YieldFFunctor<V> functor() {
        return new YieldFFunctor<>() {
        };
    }
}
