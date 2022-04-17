package org.highj.data.predicates;

import org.highj.hkt.__;

public interface Pred1<M> {

    <A> Pred<__<M, A>> pred1(Pred<A> pred);

}
