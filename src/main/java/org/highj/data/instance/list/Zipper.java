package org.highj.data.instance.list;

import org.highj.hkt.__;
import org.highj.Hkt;
import org.highj.data.List;
import org.highj.data.Maybe;
import org.highj.data.tuple.T2;
import org.highj.typeclass1.functor.Functor;

import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import static org.highj.Hkt.asZipper;

/**
 * A list zipper allows to have a focus on a certain position in a {@link List}, and to read and
 * edit at this position.
 * <p>
 * A focus position is considered to be <strong>before</strong> the element with the corresponding
 * list index. The focus position can be also at <code>list.size()</code>, which is after the last
 * element of <code>list</code>.
 * <p>
 * Example with | marking the position of the focus in a list containing three elements:
 * <ul>
 * <li><code>| foo bar baz</code> would be position 0</li>
 * <li><code>foo | bar baz</code> would be position 1</li>
 * <li><code>foo bar baz |</code> would be position 3</li>
 * </ul>
 * <p>
 * It is possible to create a {@link Zipper} from an infinite list, but then all diverging
 * operations need to be avoided.
 *
 * @param <A> element type of the {@link Zipper}
 */
public interface Zipper<A> extends __<Zipper.µ, A> {

    interface µ {
    }

    /**
     * The reversed list before the focus position of the  {@link Zipper}.
     *
     * @return the front list
     */
    List<A> front();

    /**
     * The list after the focus position of the  {@link Zipper}.
     *
     * @return the front list
     */
    List<A> back();

    /**
     * The focus position of the  {@link Zipper}.
     *
     * @return the focus position
     */
    int position();


    /**
     * Creates an empty {@link Zipper}.
     *
     * @param <A> element type
     * @return an empty {@link Zipper}
     */
    static <A> Zipper<A> empty() {
        return fromList(List.empty());
    }

    /**
     * Constructs a {@link Zipper} from a {@link List}, focusing on position 0.
     * The given list may be infinite, but only non-diverging operations may be executed.
     *
     * @param list the {@link List}
     * @param <A>  element type
     * @return an new {@link Zipper}
     */
    static <A> Zipper<A> fromList(List<A> list) {
        return new ZipperImpl<>(List.empty(), list, 0);
    }

    /**
     * Constructs a {@link Zipper} from a {@link List}, focusing after the last list element.
     * Will diverge if the given list is infinite.
     *
     * @param list the {@link List}
     * @param <A>  element type
     * @return a new {@link Zipper}
     */
    static <A> Zipper<A> fromListEnd(List<A> list) {
        return fromList(list).toEnd();
    }

    /**
     * Converts the {@link Zipper} back to a {@link List}.
     *
     * @return the {@link List}
     */
    default List<A> toList() {
        return toStart().back();
    }

    /**
     * Checks whether the {@link Zipper} contains no elements.
     *
     * @return <code>true</code> if empty
     */
    default boolean isEmpty() {
        return isStart() && isEnd();
    }

    /**
     * Checks whether the position of the {@link Zipper} is before the first element.
     *
     * @return <code>true</code> if at position 0
     */
    default boolean isStart() {
        return position() == 0;
    }

    /**
     * Checks whether the position of the {@link Zipper} is after the last element.
     *
     * @return <code>true</code> if at position {@link Zipper#size}
     */
    default boolean isEnd() {
        return back().isEmpty();
    }

    /**
     * Calculates the number of elements of the {@link Zipper}.
     * Will diverge for a {@link Zipper} created from an infinite list.
     *
     * @return the size
     */
    default int size() {
        return position() + back().size();
    }

    /**
     * Sets the focus before the first element.
     *
     * @return the {@link Zipper}
     */
    default Zipper<A> toStart() {
        Zipper<A> result = this;
        while (!result.isStart()) {
            result = result.backwards();
        }
        return result;
    }

    /**
     * Sets the focus after the last element.
     * Will diverge for a {@link Zipper} created from an infinite list.
     *
     * @return the {@link Zipper}
     */
    default Zipper<A> toEnd() {
        Zipper<A> result = this;
        while (!result.isEnd()) {
            result = result.forwards();
        }
        return result;
    }

    /**
     * Sets the focus to the given position.
     *
     * @param index the position
     * @return the {@link Zipper}
     * @throws NoSuchElementException when index is out of bounds
     */
    default Zipper<A> toPosition(int index) throws NoSuchElementException {
        Zipper<A> result = this;
        while (index != result.position()) {
            result = result.position() < index ? result.forwards() : result.backwards();
        }
        return result;
    }

    /**
     * Sets the focus to the given position, if possible.
     *
     * @param index the position
     * @return a {@link Maybe} of {@link Zipper} 
     */
    default Maybe<Zipper<A>> maybeToPosition(int index) {
        Zipper<A> result = this;
        while (index != result.position()) {
            if (result.position() < index && !result.isEnd()) {
                result = result.forwards();
            } else if (result.position() > index && !result.isStart()) {
                result = result.backwards();
            } else {
                return Maybe.Nothing();
            }
        }
        return Maybe.Just(result);
    }

    /**
     * Increases the focus position by one.
     *
     * @return the {@link Zipper}
     * @throws NoSuchElementException when position is out of bounds
     */
    default Zipper<A> forwards() throws NoSuchElementException {
        return new ZipperImpl<>(front().plus(back().head()), back().tail(), position() + 1);
    }

    /**
     * Increases the focus position by the given number of steps.
     * The number of steps can be zero or negative.
     *
     * @param steps number of steps
     * @return the {@link Zipper}
     * @throws NoSuchElementException when position is out of bounds
     */
    default Zipper<A> forwards(int steps) throws NoSuchElementException {
        return toPosition(position() + steps);
    }

    /**
     * Decreases the focus position by one.
     *
     * @return the {@link Zipper}
     * @throws NoSuchElementException when position is out of bounds
     */
    default Zipper<A> backwards() throws NoSuchElementException {
        return new ZipperImpl<>(front().tail(), back().plus(front().head()), position() - 1);
    }

    /**
     * Decreases the focus position by the given number of steps.
     * The number of steps can be zero or negative.
     *
     * @param steps number of steps
     * @return the {@link Zipper}
     * @throws NoSuchElementException when position is out of bounds
     */
    default Zipper<A> backwards(int steps) throws NoSuchElementException {
        return toPosition(position() - steps);
    }

    /**
     * Increases the focus position by one, if possible.
     *
     * @return a {@link Maybe} of {@link Zipper} 
     */
    default Maybe<Zipper<A>> maybeForwards() {
        return Maybe.JustWhenTrue(!isEnd(), this::forwards);
    }

    /**
     * Increases the focus position by the given number of steps, if possible.
     * The number of steps can be zero or negative.
     *
     * @param steps number of steps
     * @return a {@link Maybe} of {@link Zipper} 
     */
    default Maybe<Zipper<A>> maybeForwards(int steps) {
        return maybeToPosition(position() + steps);
    }

    /**
     * Decreases the focus position by one, if possible.
     *
     * @return a {@link Maybe} of {@link Zipper} 
     */
    default Maybe<Zipper<A>> maybeBackwards() {
        return Maybe.JustWhenTrue(!isStart(), this::backwards);
    }

    /**
     * Decreases the focus position by the given number of steps, if possible.
     * The number of steps can be zero or negative.
     *
     * @param steps number of steps
     * @return a {@link Maybe} of {@link Zipper} 
     */
    default Maybe<Zipper<A>> maybeBackwards(int steps) {
        return maybeToPosition(position() - steps);
    }

    /**
     * Reads the element after the focus position.
     *
     * @return the element
     * @throws NoSuchElementException when focus position is after the last element
     */
    default A readNext() throws NoSuchElementException {
        return back().head();
    }

    /**
     * Reads the element before the focus position.
     *
     * @return the element
     * @throws NoSuchElementException when focus position is before the first element
     */
    default A readBefore() throws NoSuchElementException {
        return front().head();
    }

    /**
     * Reads the element after the focus position, if possible.
     *
     * @return a {@link Maybe} of en element
     */
    default Maybe<A> maybeReadNext() {
        return Maybe.JustWhenTrue(!isEnd(), () -> back().head());
    }

    /**
     * Reads the element before the focus position, if possible.
     *
     * @return a {@link Maybe} of en element
     */
    default Maybe<A> maybeReadBefore() {
        return Maybe.JustWhenTrue(!isStart(), () -> front().head());
    }

    /**
     * Removes the element after the focus position.
     *
     * @return the {@link Zipper}
     * @throws NoSuchElementException when position is out of bounds
     */
    default Zipper<A> removeNext() throws NoSuchElementException {
        return new ZipperImpl<>(front(), back().tail(), position());
    }

    /**
     * Removes the element before the focus position.
     *
     * @return the {@link Zipper}
     * @throws NoSuchElementException when position is out of bounds
     */
    default Zipper<A> removeBefore() throws NoSuchElementException {
        return new ZipperImpl<>(front().tail(), back(), position() - 1);
    }

    /**
     * Removes the element after the focus position, if possible.
     *
     * @return a {@link Maybe} of {@link Zipper} 
     */
    default Maybe<Zipper<A>> maybeRemoveNext() {
        return Maybe.JustWhenTrue(!isEnd(), this::removeNext);
    }

    /**
     * Removes the element before the focus position, if possible.
     *
     * @return a {@link Maybe} of {@link Zipper} 
     */
    default Maybe<Zipper<A>> maybeRemoveBefore() {
        return Maybe.JustWhenTrue(!isStart(), this::removeBefore);
    }

    /**
     * Inserts the given elements after the focus position.
     *
     * @param as the new elements
     * @return the {@link Zipper}
     */
    default Zipper<A> insertNext(A... as) {
        return new ZipperImpl<>(front(), back().plus(as), position());
    }

    /**
     * Inserts the given elements before the focus position in reverse order.
     *
     * @param as the new elements
     * @return the {@link Zipper}
     */
    default Zipper<A> insertBefore(A... as) {
        return new ZipperImpl<>(front().plus(as), back(), position() + as.length);
    }

    /**
     * Replaces the element after the focus position.
     *
     * @param a the new value
     * @return the {@link Zipper}
     * @throws NoSuchElementException when position is out of bounds
     */
    default Zipper<A> replaceNext(A a) throws NoSuchElementException {
        return new ZipperImpl<>(front(), back().tail().plus(a), position());
    }

    /**
     * Replaces the element before the focus position.
     *
     * @param a the new value
     * @return the {@link Zipper}
     * @throws NoSuchElementException when position is out of bounds
     */
    default Zipper<A> replaceBefore(A a) throws NoSuchElementException {
        return new ZipperImpl<>(front().tail().plus(a), back(), position());
    }

    /**
     * Replaces the element after the focus position, if possible.
     *
     * @param a the new value
     * @return the {@link Maybe} of {@link Zipper} 
     */
    default Maybe<Zipper<A>> maybeReplaceNext(A a) {
        return Maybe.JustWhenTrue(!isEnd(), () -> replaceNext(a));
    }

    /**
     * Replaces the element before the focus position, if possible.
     *
     * @param a the new value
     * @return the {@link Maybe} of {@link Zipper} 
     */
    default Maybe<Zipper<A>> maybeReplaceBefore(A a) {
        return Maybe.JustWhenTrue(!isStart(), () -> replaceBefore(a));
    }

    /**
     * Modifies the element after the focus position.
     *
     * @param operator the modification operation
     * @return the {@link Zipper}
     * @throws NoSuchElementException when position is out of bounds
     */
    default Zipper<A> modifyNext(UnaryOperator<A> operator) throws NoSuchElementException {
        return new ZipperImpl<>(front(), back().tail().plus(operator.apply(back().head())), position());
    }

    /**
     * Modifies the element before the focus position.
     *
     * @param operator the modification operation
     * @return the {@link Zipper}
     * @throws NoSuchElementException when position is out of bounds
     */
    default Zipper<A> modifyBefore(UnaryOperator<A> operator) throws NoSuchElementException {
        return new ZipperImpl<>(front().tail().plus(operator.apply(front().head())), back(), position());
    }

    /**
     * Modifies the element after the focus position, if possible.
     *
     * @param operator the modification operation
     * @return the {@link Maybe} of {@link Zipper} 
     */
    default Maybe<Zipper<A>> maybeModifyNext(UnaryOperator<A> operator) {
        return Maybe.JustWhenTrue(!isEnd(), () -> modifyNext(operator));
    }

    /**
     * Modifies the element before the focus position, if possible.
     *
     * @param operator the modification operation
     * @return the {@link Maybe} of {@link Zipper} 
     */
    default Maybe<Zipper<A>> maybeModifyBefore(UnaryOperator<A> operator) {
        return Maybe.JustWhenTrue(!isStart(), () -> modifyBefore(operator));
    }

    /**
     * Drops all elements after the focus position.
     *
     * @return the {@link Zipper}
     */
    default Zipper<A> dropAfter() {
        return new ZipperImpl<>(front(), List.empty(), position());
    }

    /**
     * Drops all elements before the focus position.
     *
     * @return the {@link Zipper}
     */
    default Zipper<A> dropBefore() {
        return new ZipperImpl<>(List.empty(), back(), 0);
    }

    /**
     * Reverses the elements of the Zipper.
     * The focus position remains relatively between the same elements it was before.
     * Will diverge for a {@link Zipper} created from an infinite list.
     *
     * @return the {@link Zipper}
     */
    default Zipper<A> reverse() {
        return new ZipperImpl<>(back(), front(), back().size());
    }

    /**
     * Transforms all elements of the {@link Zipper}.
     * The focus position stays the same.
     *
     * @param fn  the transformation function
     * @param <B> the new element type
     * @return the {@link Zipper}
     */
    default  <B> Zipper<B> map(Function<? super A, ? extends B> fn) {
        return new ZipperImpl<>(front().map(fn), back().map(fn), position());
    }

    /**
     * Removes all elements which don't pass a given test.
     *
     * @param predicate the test predicate
     * @return the {@link Zipper}
     */
    default Zipper<A> filter(Predicate<? super A> predicate) {
        List<A> front2 = front().filter(predicate);
        return new ZipperImpl<>(front2, back().filter(predicate), front2.size());
    }

    /**
     * Removes all elements before the focus position which don't pass a given test.
     *
     * @param predicate the test predicate
     * @return the {@link Zipper}
     */
    default Zipper<A> filterBefore(Predicate<? super A> predicate) {
        List<A> front2 = front().filter(predicate);
        return new ZipperImpl<>(front2, back(), front2.size());
    }

    /**
     * Removes all elements after the focus position which don't pass a given test.
     *
     * @param predicate the test predicate
     * @return the {@link Zipper}
     */
    default Zipper<A> filterAfter(Predicate<? super A> predicate) {
        return new ZipperImpl<>(front(), back().filter(predicate), position());
    }

    /**
     * Splits the {@link Zipper} in the elements before and after the focus position.
     * Note that the "before" list is reversed.
     *
     * @return the {@link T2}
     */
    default T2<List<A>, List<A>> split() {
        return T2.of(front(), back());
    }

    /**
     * The {@link Functor} instance for {@link Zipper}s.
     */
    Functor<µ> functor = new Functor<µ>() {
        @Override
        public <A, B> Zipper<B> map(Function<A, B> fn, __<µ, A> nestedA) {
            return asZipper(nestedA).map(fn);
        }
    };
}

record ZipperImpl<A>(List<A> front, List<A> back, int position) implements Zipper<A> {
}



