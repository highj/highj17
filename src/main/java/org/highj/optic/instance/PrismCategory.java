package org.highj.optic.instance;

import org.highj.hkt.__2;
import org.highj.optic.Prism;
import org.highj.typeclass2.arrow.Category;

import static org.highj.Hkt.asPrism;

public interface PrismCategory extends Category<Prism.µ> {

    @Override
    default  <B, C, D> __2<Prism.µ, B, D> dot(final __2<Prism.µ, C, D> cd, final __2<Prism.µ, B, C> bc) {
        return asPrism(bc).composePrism(asPrism(cd));
    }

    @Override
    default  <B> __2<Prism.µ, B, B> identity() {
        return Prism.id();
    }
}
