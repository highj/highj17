package org.highj.data.transformer.free;

import org.highj.hkt.__;
import org.highj.data.transformer.FreeT;
import org.highj.typeclass1.monad.Monad;

public interface FreeTMonad<F,M> extends FreeTApplicative<F,M>, FreeTBind<F,M>, Monad<__<__<FreeT.µ,F>,M>> {}
