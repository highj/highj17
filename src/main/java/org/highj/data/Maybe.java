package org.highj.data;

import org.highj.hkt.__;
import org.highj.data.eq.Eq;
import org.highj.data.eq.Eq1;
import org.highj.data.instance.maybe.*;
import org.highj.data.ord.Ord1;
import org.highj.data.ord.Ordering;
import org.highj.data.tuple.T2;
import org.highj.data.tuple.T3;
import org.highj.data.tuple.T4;
import org.highj.function.F3;
import org.highj.function.F4;
import org.highj.function.Functions;
import org.highj.typeclass0.group.Monoid;
import org.highj.typeclass1.comonad.Extend;
import org.highj.typeclass1.foldable.Traversable;
import org.highj.typeclass1.monad.MonadPlus;
import org.highj.typeclass1.zip.Zip;

import java.util.*;
import java.util.function.*;

/**
 * A data type which may or may not hold a value.
 * <p>
 * A.k.a. "Option", "Optional" or "Box".
 */
public abstract class Maybe<A> implements __<Maybe.µ, A>, Iterable<A> {
    private static final String SHOW_NOTHING = "Nothing";
    private static final String SHOW_JUST = "Just(%s)";

    public interface µ {
    }

    private Maybe() {
    }

    private final static Maybe<?> NOTHING = new Maybe<>() {

        @Override
        public <B> B cata(B defaultValue, Function<Object, B> fn) {
            return defaultValue;
        }

        @Override
        public <B> B cata$(Supplier<B> defaultThunk, Function<Object, B> fn) {
            return defaultThunk.get();
        }
    };

    /**
     * Creates a {@link Maybe} holding no value.
     * <p>
     * This method returns always the same NOTHING instance. As no actual A values are
     * involved, the cast is safe.
     *
     * @param <A> the element type
     * @return the empty {@link Maybe} instance
     */
    @SuppressWarnings("unchecked")
    public static <A> Maybe<A> Nothing() {
        return (Maybe<A>) NOTHING;
    }

    /**
     * Creates a {@link Maybe} holding a value (a.k.a. "Just").
     * <p>
     * The value needs to be non-null.
     *
     * @param value the value to be stored in the Maybe
     * @param <A>   the element type
     * @return a Maybe holding the value
     * @throws NullPointerException when the value is null
     */
    public static <A> Maybe<A> Just(final A value) throws NullPointerException {
        Objects.requireNonNull(value, "Just() can't take null argument");
        return new Maybe<A>() {
            public <B> B cata$(Supplier<B> defaultThunk, Function<A, B> fn) {
                return fn.apply(value);
            }

            @Override
            public <B> B cata(B defaultValue, Function<A, B> fn) {
                return fn.apply(value);
            }
        };
    }

    /**
     * Creates a {@link Maybe} holding a value (a.k.a. "Just") in a lazy fashion.
     *
     * @param thunk the {@link Supplier} generating the value to be stored in the Maybe
     * @param <A>   the element type
     * @return a {@link Maybe} holding the value
     */
    public static <A> Maybe<A> Just$(final Supplier<A> thunk) {
        return new Maybe<>() {

            public <B> B cata$(Supplier<B> defaultThunk, Function<A, B> fn) {
                return fn.apply(thunk.get());
            }

            @Override
            public <B> B cata(B defaultValue, Function<A, B> fn) {
                return fn.apply(thunk.get());
            }
        };
    }

    /**
     * Creates a {@link Maybe} which contains a given value ("Just") when the condition is true,
     * or an empty on ("Nothing") otherwise.
     *
     * @param condition the condition
     * @param thunk     the {@link Supplier} generating the value to be wrapped
     * @param <A>       the element type
     * @return a {@link Maybe}
     */
    public static <A> Maybe<A> JustWhenTrue(boolean condition, Supplier<A> thunk) {
        return condition ? Just(thunk.get()) : Nothing();
    }

    /**
     * The catamorphism of {@link Maybe}, which collapses it to a certain type.
     *
     * @param defaultValue the value to be returned when the {@link Maybe} is empty
     * @param fn           the function to be applied when the {@link Maybe} is not empty
     * @param <B>          the result type
     * @return the result generated by the function or the default value
     */
    public abstract <B> B cata(B defaultValue, Function<A, B> fn);

    /**
     * A lazy version of {@link Maybe#cata}.
     *
     * @param defaultThunk the {@link Supplier} generating the default value when the {@link Maybe} is empty
     * @param fn           the function to be applied when the {@link Maybe} is not empty
     * @param <B>          the result type
     * @return the result generated by the function or the default value
     */
    public abstract <B> B cata$(Supplier<B> defaultThunk, Function<A, B> fn);

    /**
     * Changes the element type of a {@link Maybe} to a super type.
     * <p>
     * This is safe, as {@link Maybe} is a read-only class.
     *
     * @param maybe     a {@link Maybe} instance
     * @param <Super_A> the expected super type
     * @param <A>       the current element type
     * @return a reference to the given {@link Maybe} with a more general type
     */
    @SuppressWarnings("unchecked")
    public static <Super_A, A extends Super_A> Maybe<Super_A> contravariant(Maybe<A> maybe) {
        return (Maybe) maybe;
    }

    /**
     * Checks whether the {@link Maybe} is empty ("Nothing") or not ("Just").
     *
     * @return true if the {@link Maybe} is empty, false otherwise
     */
    public boolean isNothing() {
        return this == NOTHING;
    }

    /**
     * Checks whether the {@link Maybe} is non-empty ("Just") or not ("Nothing").
     *
     * @return true if the {@link Maybe} is non-empty, false otherwise
     */
    public boolean isJust() {
        return this != NOTHING;
    }

    /**
     * Returns the value stored in the {@link Maybe}, or a default value, if it is empty.
     *
     * @param defaultValue the value used if the {@link Maybe} is empty
     * @return the wrapped value, or a default
     */
    public A getOrElse(A defaultValue) {
        return cata(defaultValue, x -> x);
    }

    /**
     * Returns the value stored in the {@link Maybe}, or a lazy default value, if it is empty.
     *
     * @param defaultThunk the {@link Supplier} to generate the value used if the {@link Maybe} is empty
     * @return the wrapped value, or a default
     */
    public A getOrElse(Supplier<A> defaultThunk) {
        return cata$(defaultThunk, x -> x);
    }

    /**
     * Returns the value stored in the {@link Maybe}, or throws an Exception if it is empty.
     * <p>
     * Note that the specified exception class needs to have a default constructor.
     *
     * @param exClass the Class of the Exception to be thrown if the {@link Maybe} is empty
     * @return the wrapped value
     * @throws RuntimeException when called on Nothing
     */
    public A getOrException(Class<? extends RuntimeException> exClass) {
        return getOrElse(Functions.error(exClass));
    }

    /**
     * Returns the value stored in the {@link Maybe}, or throws an Exception if it is empty.
     * <p>
     * Note that the specified exception class needs to have a constructor taking a String argument.
     *
     * @param exClass the Class of the Exception to be thrown if the {@link Maybe} is empty
     * @param message the error message
     * @return the wrapped value
     * @throws RuntimeException of type exClass when called on Nothing
     */
    public A getOrException(Class<? extends RuntimeException> exClass, String message) {
        return getOrElse(Functions.error(exClass, message));
    }

    /**
     * Returns the value stored in the {@link Maybe}, or throws a {@link RuntimeException} if it is empty.
     *
     * @param message the message of the {@link RuntimeException}
     * @return the wrapped value
     * @throws RuntimeException when called on Nothing
     */
    public A getOrException(String message) {
        return getOrElse(Functions.<A>error(message));
    }

    /**
     * Returns the value stored in the {@link Maybe}, or throws a {@link NoSuchElementException} if it is empty.
     *
     * @return the wrapped value
     * @throws NoSuchElementException when called on Nothing
     */
    public A get() throws NoSuchElementException {
        return getOrException(NoSuchElementException.class);
    }

    /**
     * Replaces the the current {@link Maybe} when it is empty.
     *
     * @param that the {@link Maybe} to replace the current one.
     * @return the current {@link Maybe} if it is non-empty, else its replacement
     */
    public Maybe<A> orElse(Maybe<A> that) {
        return this.isJust() ? this : that;
    }

    @Override
    public void forEach(Consumer<? super A> consumer) {
        if (isJust()) consumer.accept(get());
    }

    /**
     * Checks the current value against a condition, and returns an empty {@link Maybe} when it fails.
     *
     * @param predicate the condition to be applied
     * @return the current {@link Maybe} if it passes, else an empty one
     */
    public Maybe<A> filter(Predicate<? super A> predicate) {
        return isJust() && predicate.test(get()) ? this : Nothing();
    }

    /**
     * Converts the {@link Maybe} to a {@link List}
     *
     * @return a list containg zero or one element.
     */
    public List<A> asList() {
        return cata(List.Nil(), List::of);
    }

    /**
     * Calculates a new {@link Maybe} from the actual value, or returns an empty one if there is none.
     * <p>
     * Corresponds to {@link org.highj.typeclass1.monad.Monad#bind}.
     *
     * @param fn  the function to be applied
     * @param <B> the element type of the result
     * @return the calculated {@link Maybe}, or an empty one
     */
    public <B> Maybe<B> bind(Function<A, Maybe<B>> fn) {
        return cata(Maybe.<B>Nothing(), fn);
    }

    /**
     * Calculates a new value from the actual value, or returns an empty {@link Maybe} if there is none.
     * <p>
     * Corresponds to {@link org.highj.typeclass1.functor.Functor#map}.
     *
     * @param fn  the function to be applied
     * @param <B> the element type of the result
     * @return the calculated {@link Maybe}, or an empty one
     */
    public <B> Maybe<B> map(Function<? super A, ? extends B> fn) {
        return bind(a -> Maybe.<B>Just(fn.apply(a)));
    }

    /**
     * Lifts a unary function to the {@link Maybe} context.
     * <p>
     * Corresponds to {@link org.highj.typeclass1.functor.Functor#lift}.
     *
     * @param fn  the function
     * @param <A> the argument type
     * @param <B> the result type
     * @return a {@link Function} operating on {@link Maybe} values.
     */
    public static <A, B> Function<Maybe<A>, Maybe<B>> lift(Function<A, B> fn) {
        return maybeA -> maybeA.map(fn);
    }

    /**
     * Lifts a binary function to the {@link Maybe} context.
     * <p>
     * Corresponds to {@link org.highj.typeclass1.monad.Apply#lift2}.
     *
     * @param fn  the function
     * @param <A> the first argument type
     * @param <B> the second argument type
     * @param <C> the result type
     * @return a {@link BiFunction} operating on {@link Maybe} values.
     */
    public static <A, B, C> BiFunction<Maybe<A>, Maybe<B>, Maybe<C>> lift2(BiFunction<A, B, C> fn) {
        return (maybeA, maybeB) -> maybeA.bind(
                a -> maybeB.map(
                        b -> fn.apply(a, b)));
    }

    /**
     * Lifts a ternary function to the {@link Maybe} context.
     * <p>
     * Corresponds to {@link org.highj.typeclass1.monad.Apply#lift3}.
     *
     * @param fn  the function
     * @param <A> the first argument type
     * @param <B> the second argument type
     * @param <C> the third argument type
     * @param <D> the result type
     * @return a {@link F3} operating on {@link Maybe} values.
     */
    public static <A, B, C, D> F3<Maybe<A>, Maybe<B>, Maybe<C>, Maybe<D>> lift3(F3<A, B, C, D> fn) {
        return (maybeA, maybeB, maybeC) -> maybeA.bind(
                a -> maybeB.bind(
                        b -> maybeC.map(
                                c -> fn.apply(a, b, c))));
    }

    @Override
    public Iterator<A> iterator() {
        return asList().iterator();
    }

    @Override
    public String toString() {
        return cata(SHOW_NOTHING, a -> String.format(SHOW_JUST, a));
    }

    public Optional<A> toOptional() {
        return cata(Optional.empty(), Optional::of);
    }

    public static <A> Maybe<A> fromOptional(Optional<A> optional) {
        return optional.map(Maybe::Just).orElse(Nothing());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Maybe<?> that) {
            return cata(that.isNothing(), x -> that.<Boolean>cata(false, x::equals));
        } else return false;
    }

    /**
     * Generates an {@link Eq} instance for {@link Maybe}.
     *
     * @param eqA an {@link Eq} instance for the element type
     * @param <A> the element type
     * @return the {@link Eq} instance
     */
    public static <A> Eq<Maybe<A>> eq(final Eq<? super A> eqA) {
        return (one, two) -> one.cata(two.isNothing(),
                x -> two.<Boolean>cata(false, y -> eqA.eq(x, y)));
    }

    @Override
    public int hashCode() {
        return cata(0, Object::hashCode);
    }

    /**
     * Collects all values from non-empty {@link Maybe} elements in a {@link List}.
     *
     * @param iterable a {@link Iterable} of {@link Maybe} elements
     * @param <A>      the element type
     * @return a {@link List} of all values contained in non-empty {@link Maybe}s.
     */
    public static <A> List<A> justs(Iterable<Maybe<A>> iterable) {
        Stack<A> result = new Stack<>();
        for (Maybe<A> maybe : iterable) {
            maybe.forEach(result::push);
        }
        return List.buildFromStack(result);
    }

    /**
     * Combines two {@link Maybe} objects to a  {@link Maybe} containing a pair.
     * <p>
     * If one of the arguments is {@link Maybe#Nothing()}, the result is {@link Maybe#Nothing()}, too.
     *
     * @param maybeA the first {@link Maybe}
     * @param maybeB the second {@link Maybe}
     * @param <A>    the element type of the first {@link Maybe}
     * @param <B>    the element type of the second {@link Maybe}
     * @return the combined {@link Maybe}
     */
    public static <A, B> Maybe<T2<A, B>> zip(Maybe<A> maybeA, Maybe<B> maybeB) {
        return zipWith(maybeA, maybeB, T2::of);
    }

    /**
     * Combines three {@link Maybe} objects to a  {@link Maybe} containing a triple.
     * <p>
     * If one of the arguments is {@link Maybe#Nothing()}, the result is {@link Maybe#Nothing()}, too.
     *
     * @param maybeA the first {@link Maybe}
     * @param maybeB the second {@link Maybe}
     * @param maybeC the third {@link Maybe}
     * @param <A>    the element type of the first {@link Maybe}
     * @param <B>    the element type of the second {@link Maybe}
     * @param <C>    the element type of the third {@link Maybe}
     * @return the combined {@link Maybe}
     */
    public static <A, B, C> Maybe<T3<A, B, C>> zip(Maybe<A> maybeA, Maybe<B> maybeB, Maybe<C> maybeC) {
        return zipWith(maybeA, maybeB, maybeC, T3::of);
    }

    /**
     * Combines three {@link Maybe} objects to a {@link Maybe} containing a quadruple.
     * <p>
     * If one of the arguments is {@link Maybe#Nothing()}, the result is {@link Maybe#Nothing()}, too.
     *
     * @param maybeA the first {@link Maybe}
     * @param maybeB the second {@link Maybe}
     * @param maybeC the third {@link Maybe}
     * @param maybeD the fourth {@link Maybe}
     * @param <A>    the element type of the first {@link Maybe}
     * @param <B>    the element type of the second {@link Maybe}
     * @param <C>    the element type of the third {@link Maybe}
     * @param <D>    the element type of the fourth {@link Maybe}
     * @return the combined {@link Maybe}
     */
    public static <A, B, C, D> Maybe<T4<A, B, C, D>> zip(Maybe<A> maybeA, Maybe<B> maybeB, Maybe<C> maybeC, Maybe<D> maybeD) {
        return zipWith(maybeA, maybeB, maybeC, maybeD,  T4::of);
    }

    /**
     * Combines two {@link Maybe} objects using a combination function.
     * <p>
     * If one of the arguments is {@link Maybe#Nothing()}, the result is {@link Maybe#Nothing()}, too.
     *
     * @param maybeA the first {@link Maybe}
     * @param maybeB the second {@link Maybe}
     * @param fn     the combination function
     * @param <A>    the element type of the first {@link Maybe}
     * @param <B>    the element type of the second {@link Maybe}
     * @param <C>    the element type of the result
     * @return the combined {@link Maybe}
     */
    public static <A, B, C> Maybe<C> zipWith(Maybe<A> maybeA, Maybe<B> maybeB, BiFunction<A, B, C> fn) {
        return maybeA.isJust() && maybeB.isJust()
                ? Just(fn.apply(maybeA.get(), maybeB.get()))
                : Nothing();
    }

    /**
     * Combines three {@link Maybe} objects using a combination function.
     * <p>
     * If one of the arguments is {@link Maybe#Nothing()}, the result is {@link Maybe#Nothing()}, too.
     *
     * @param maybeA the first {@link Maybe}
     * @param maybeB the second {@link Maybe}
     * @param maybeC the third {@link Maybe}
     * @param fn     the combination function
     * @param <A>    the element type of the first {@link Maybe}
     * @param <B>    the element type of the second {@link Maybe}
     * @param <C>    the element type of the third {@link Maybe}
     * @param <D>    the element type of the result
     * @return the combined {@link Maybe}
     */
    public static <A, B, C, D> Maybe<D> zipWith(Maybe<A> maybeA, Maybe<B> maybeB, Maybe<C> maybeC,
                                                F3<A, B, C, D> fn) {
        return maybeA.isJust() && maybeB.isJust() && maybeC.isJust()
                ? Just(fn.apply(maybeA.get(), maybeB.get(), maybeC.get()))
                : Nothing();
    }

    /**
     * Combines four {@link Maybe} objects using a combination function.
     * <p>
     * If one of the arguments is {@link Maybe#Nothing()}, the result is {@link Maybe#Nothing()}, too.
     *
     * @param maybeA the first {@link Maybe}
     * @param maybeB the second {@link Maybe}
     * @param maybeC the third {@link Maybe}
     * @param maybeD the fourth {@link Maybe}
     * @param fn     the combination function
     * @param <A>    the element type of the first {@link Maybe}
     * @param <B>    the element type of the second {@link Maybe}
     * @param <C>    the element type of the third {@link Maybe}
     * @param <D>    the element type of the fourth {@link Maybe}
     * @param <E>    the element type of the result
     * @return the combined {@link Maybe}
     */
    public static <A, B, C, D, E> Maybe<E> zipWith(Maybe<A> maybeA, Maybe<B> maybeB, Maybe<C> maybeC, Maybe<D> maybeD,
                                                F4<A, B, C, D, E> fn) {
        return maybeA.isJust() && maybeB.isJust() && maybeC.isJust() && maybeD.isJust()
                ? Just(fn.apply(maybeA.get(), maybeB.get(), maybeC.get(), maybeD.get()))
                : Nothing();
    }

    /**
     * Returns the {@link MaybeMonad}.
     * <p>
     * Note that it implements some subinterfaces of {@link org.highj.typeclass1.monad.Monad} as well
     */
    public static final MaybeMonad monad = new MaybeMonad() {
    };

    /**
     * Returns the {@link MaybeMonadPlus} which chooses the first non-empty {@link Maybe}.
     */
    public static final MaybeMonadPlus firstBiasedMonadPlus = () -> MonadPlus.Bias.FIRST;

    /**
     * Returns the {@link MaybeMonadPlus} which chooses the last non-empty {@link Maybe}.
     */
    public static final MaybeMonadPlus lastBiasedMonadPlus = () -> MonadPlus.Bias.LAST;


    /**
     * Returns the {@link Traversable} instance of {@link Maybe}.
     */
    public static final Traversable<µ> traversable = new MaybeTraversable() {
    };

    /**
     * Returns the {@link Extend} instance of {@link Maybe}.
     */
    public static final Extend<µ> extend = new MaybeExtend() {
    };

    /**
     * Returns the {@link Monoid} which chooses the first non-empty {@link Maybe}.
     *
     * @param <A> the element type
     * @return the {@link Monoid} instance
     */
    public static <A> Monoid<Maybe<A>> firstMonoid() {
        return Monoid.create(Maybe.Nothing(), Maybe::orElse);
    }

    /**
     * Returns the {@link Monoid} which chooses the last non-empty {@link Maybe}.
     *
     * @param <A> the element type
     * @return the {@link Monoid} instance
     */
    public static <A> Monoid<Maybe<A>> lastMonoid() {
        return Monoid.create(Maybe.Nothing(), (x, y) -> y.orElse(x));
    }

    /**
     * Returns the {@link Monoid} for {@link Maybe} given a {@link Monoid} for the element type.
     *
     * @param semigroup the semigroup of the element type
     * @param <A>       the element type
     * @return the {@link Monoid} instance
     */
    public static <A> Monoid<Maybe<A>> monoid(final BinaryOperator<A> semigroup) {
        BinaryOperator<Maybe<A>> op = (mx, my) ->
                mx.map(x -> my.map(y -> semigroup.apply(x, y)).getOrElse(x)).orElse(my);
        return Monoid.create(Maybe.Nothing(), op);
    }

    /**
     * Returns the {@link Eq1} instance of {@link Maybe}.
     */
    public static MaybeEq1 eq1 = new MaybeEq1() {
    };

    /**
     * Returns the {@link Ord1} instance of {@link Maybe} where {@link Maybe#Nothing()} comes first.
     */
    public static MaybeOrd1 ord1withNothingFirst = () -> Ordering.LT;

    /**
     * Returns the {@link Ord1} instance of {@link Maybe} where {@link Maybe#Nothing()} comes last.
     */
    public static MaybeOrd1 ord1withNothingLast = () -> Ordering.GT;

    /**
     * returns the {@link Zip} instance of {@link Maybe}.
     */
    public static MaybeZip zip = new MaybeZip() {
    };
}
