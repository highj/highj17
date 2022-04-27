package org.highj.optic;

import java.util.function.Function;

import org.highj.hkt.__;
import org.highj.hkt.__2;
import org.highj.data.Either;
import org.highj.function.F1;
import org.highj.typeclass1.monad.Applicative;

/**
 * {@link PLens} with a monomorphic set function
 */
public final class Lens<S, A> extends PLens<S, S, A, A> implements __2<Lens.µ, S, A> {

    public interface µ {
    }

    final PLens<S, S, A, A> pLens;

    public Lens(final PLens<S, S, A, A> pLens) {
        this.pLens = pLens;
    }

    @Override
    public A get(final S s) {
        return pLens.get(s);
    }

    @Override
    public F1<S, S> set(final A a) {
        return pLens.set(a);
    }

    @Override
    public <X> F1<S, __<X, S>> modifyF(final Applicative<X> applicative, final Function<A, __<X, A>> f) {
        return pLens.modifyF(applicative, f);
    }

    @Override
    public F1<S, S> modify(final Function<A, A> f) {
        return pLens.modify(f);
    }

    /** Join two {@link Lens} with the same target
     * @param other the second {@link Lens}
     * @param <S1> the source type of the second {@link Lens}
     * @return the combined {@link Lens}
     */
    public <S1> Lens<Either<S, S1>, A> sum(final Lens<S1, A> other) {
        return new Lens<>(pLens.sum(other.pLens));
    }

    /* *************************************************************/
    /* * Compose methods between a {@link Lens} and another Optics */
    /* *************************************************************/

    /** Compose a {@link Lens} with a {@link Setter}
     * @param other the  {@link Setter}
     * @param <C> the target type of the {@link Setter}
     * @return the composed {@link Setter}
     */
    public <C> Setter<S, C> composeSetter(final Setter<A, C> other) {
        return new Setter<>(pLens.composeSetter(other.pSetter));
    }

    /** Compose a {@link Lens} with a {@link Traversal}
     * @param other the  {@link Traversal}
     * @param <C> the target type of the {@link Traversal}
     * @return the composed {@link Traversal}
     */
    public <C> Traversal<S, C> composeTraversal(final Traversal<A, C> other) {
        return new Traversal<>(pLens.composeTraversal(other.pTraversal));
    }

    /** Compose a {@link Lens} with an {@link Optional}
     * @param other the  {@link Optional}
     * @param <C> the target type of the {@link Optional}
     * @return the composed {@link Optional}
     */
    public <C> Optional<S, C> composeOptional(final Optional<A, C> other) {
        return new Optional<>(pLens.composeOptional(other.pOptional));
    }

    /** Compose a {@link Lens} with a {@link Prism}
     * @param other the  {@link Prism}
     * @param <C> the target type of the {@link Prism}
     * @return the composed {@link Optional}
     */
    public <C> Optional<S, C> composePrism(final Prism<A, C> other) {
        return new Optional<>(pLens.composePrism(other.pPrism));
    }

    /** Compose a {@link Lens} with a {@link Lens}
     * @param other the second {@link Lens}
     * @param <C> the target type of the second {@link Lens}
     * @return the composed {@link Lens}
     */
    public <C> Lens<S, C> composeLens(final Lens<A, C> other) {
        return new Lens<>(pLens.composeLens(other.pLens));
    }

    /** Compose a {@link Lens} with an {@link Iso}
     * @param other the  {@link Iso}
     * @param <C> the modified target type of the {@link Iso}
     * @return the composed {@link Lens}
     */
    public <C> Lens<S, C> composeIso(final Iso<A, C> other) {
        return new Lens<>(pLens.composeIso(other.pIso));
    }

    /* *******************************************************************/
    /* * Transformation methods to view a {@link Lens} as another Optics */
    /* *******************************************************************/

    /** View a {@link Lens} as a {@link Setter}
     * @return the {@link Setter}
     */
    @Override
    public Setter<S, A> asSetter() {
        return new Setter<>(pLens.asSetter());
    }

    /** View a {@link Lens} as a {@link Traversal}
     * @return the {@link Traversal}
     */
    @Override
    public Traversal<S, A> asTraversal() {
        return new Traversal<>(pLens.asTraversal());
    }

    /** View a {@link Lens} as an {@link Optional}
     * @return the {@link Optional}
     */
    @Override
    public Optional<S, A> asOptional() {
        return new Optional<>(pLens.asOptional());
    }

    public static <S> Lens<S, S> id() {
        return new Lens<>(PLens.pId());
    }

    /** Create a {@link Lens} using a pair of functions: one to get the target, one to set the target.
     * @param get the get function
     * @param set the set function
     * @param <S> the source type
     * @param <A> the target type
     * @return the {@link Lens}
     */
    public static <S, A> Lens<S, A> lens(final Function<S, A> get, final Function<A, F1<S, S>> set) {
        return new Lens<>(PLens.pLens(get, set));
    }

}