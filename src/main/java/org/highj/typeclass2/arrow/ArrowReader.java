/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.highj.typeclass2.arrow;

import org.highj.hkt.__2;
import org.highj.data.tuple.T2;

/**
 *
 * @author clintonselke
 */
public interface ArrowReader<R,A> extends Arrow<A> {
    
    <B> __2<A,B,R> readState();
    
    <B,C> __2<A,T2<B,R>,C> newReader(__2<A,B,C> a);
}
