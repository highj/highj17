package org.highj.typeclass1.monad;

import java.util.function.Function;
import org.highj.hkt.__;

/**
 *
 * @author Clinton Selke
 */
public interface MonadReader<R,M> extends Monad<M> {

    __<M,R> ask();

    <A> __<M,A> local(Function<R,R> modFn, __<M,A> nestedA);
}
