/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.highj.typeclass2.arrow;

import org.highj.hkt.__2;

/**
 *
 * @author clintonselke
 */
public interface ArrowZero<A> extends Arrow<A> {
    
    <B,C> __2<A,B,C> zero();
}
