/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.highj.typeclass2.arrow;

import org.highj.hkt.__2;
import org.highj.data.tuple.T0;

/**
 *
 * @author clintonselke
 */
public interface ArrowState<S,A> extends Arrow<A> {
    
    <B> __2<A,B,S> fetch();
    
    __2<A,S,T0> store();
}
