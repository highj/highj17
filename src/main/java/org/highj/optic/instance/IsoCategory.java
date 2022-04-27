package org.highj.optic.instance;

import org.highj.hkt.__2;
import org.highj.optic.Iso;
import org.highj.typeclass2.arrow.Category;

import static org.highj.Hkt.asIso;
import static org.highj.optic.Iso.id;

public interface IsoCategory extends Category<Iso.µ> {

    @Override
    default  <B, C, D> __2<Iso.µ, B, D> dot(final __2<Iso.µ, C, D> cd, final __2<Iso.µ, B, C> bc) {
        return asIso(bc).composeIso(asIso(cd));
    }

    @Override
    default  <B> __2<Iso.µ, B, B> identity() {
        return id();
    }
}
