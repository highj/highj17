package org.highj.typeclass1.monad;

import org.highj.hkt.__;
import org.highj.hkt.__2;

public interface MonadTrans<T, M> extends Monad<__<T, M>> {

    <A> __2<T, M, A> lift(__<M, A> nestedA);
}
