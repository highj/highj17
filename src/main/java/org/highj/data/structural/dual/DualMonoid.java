package org.highj.data.structural.dual;

import org.highj.hkt.__2;
import org.highj.hkt.__3;
import org.highj.data.structural.Dual;
import org.highj.typeclass0.group.Monoid;

public interface DualMonoid<M, A, B> extends DualSemigroup<M, A, B>, Monoid<__3<Dual.µ, M, A, B>> {

    @Override
    Monoid<__2<M, B, A>> get();

    @Override
    default __3<Dual.µ, M, A, B> identity() {
        return Dual.of(get().identity());
    }
}
