package org.highj.data.coroutine.producer;

import org.highj.hkt.__;
import org.highj.data.coroutine.ProducerT;
import org.highj.typeclass1.monad.Applicative;

public interface ProducerTApplicative<E,M> extends ProducerTApply<E,M>, Applicative<__<__<ProducerT.µ,E>,M>> {

    @Override
    default <A> __<__<__<ProducerT.µ, E>, M>, A> pure(A a) {
        return ProducerT.done(a);
    }
}
