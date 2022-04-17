package org.highj.typeclass2.arrow;

import org.highj.hkt.__2;

public interface Category<A> extends Semigroupoid<A> {

    // id (Control.Category)
    <B> __2<A, B, B> identity();

}