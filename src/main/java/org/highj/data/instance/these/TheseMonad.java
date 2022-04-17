package org.highj.data.instance.these;

import org.highj.hkt.__;
import org.highj.data.These;
import org.highj.typeclass1.monad.Monad;

public interface TheseMonad<F> extends TheseApplicative<F>, TheseBind<F>, Monad<__<These.µ, F>> {

}
