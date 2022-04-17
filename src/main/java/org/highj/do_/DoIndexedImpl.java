package org.highj.do_;

import org.highj.hkt.__;
import org.highj.data.Either;
import org.highj.data.Maybe;
import org.highj.function.F1;
import org.highj.function.F2;
import org.highj.typeclass1.monad.Monad;

import java.util.function.Supplier;

final class DoIndexedImpl {
  private static final DoIndexed.Cases<Object, Object, Object, Maybe<F2<Monad<Object>, Object, __<Object, Object>>>> effectGetter = DoIndexedImpl.cases((effect) -> Maybe.Just(effect),
  (sequence) -> Maybe.Nothing());

  private static final DoIndexed.Cases<Object, Object, Object, Maybe<DoIndexed.Sequence<Object, Object, ?, Object>>> sequenceGetter = DoIndexedImpl.cases((effect) -> Maybe.Nothing(),
      Maybe::Just);

  private DoIndexedImpl() {
  }

  public static <M, S1, S2, R> DoIndexed.Cases<M, S1, S2, R> cases(F1<F2<Monad<M>, S1, __<M, S2>>, R> Effect,
      F1<DoIndexed.Sequence<M, S1, ?, S2>, R> Sequence) {
    return new LambdaCases<>(Effect, Sequence);
  }

  public static <M, S1, S2> DoIndexed<M, S1, S2> Effect(F2<Monad<M>, S1, __<M, S2>> effect) {
    return new Effect<>(effect);
  }

  public static <M, S1, S2> DoIndexed<M, S1, S2> Sequence(DoIndexed.Sequence<M, S1, ?, S2> sequence) {
    return new Sequence_<>(sequence);
  }

  public static <M, S1, S2> DoIndexed<M, S1, S2> lazy(Supplier<DoIndexed<M, S1, S2>> doIndexed) {
    return new Lazy<>(doIndexed);
  }

  @SuppressWarnings("unchecked")
  public static <M, S1, S2> CasesMatchers.TotalMatcher_Effect<M, S1, S2> cases() {
    return (CasesMatchers.TotalMatcher_Effect<M, S1, S2>) CasesMatchers.totalMatcher_Effect;
  }

  public static <M, S1, S2> CaseOfMatchers.TotalMatcher_Effect<M, S1, S2> caseOf(DoIndexed<M, S1, S2> doIndexed) {
    return new CaseOfMatchers.TotalMatcher_Effect<M, S1, S2>(doIndexed);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public static <M, S1, S2> Maybe<F2<Monad<M>, S1, __<M, S2>>> getEffect(DoIndexed<M, S1, S2> doIndexed) {
    return (Maybe<F2<Monad<M>, S1, __<M, S2>>>) doIndexed.match((DoIndexed.Cases) effectGetter);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public static <M, S1, S2> Maybe<DoIndexed.Sequence<M, S1, ?, S2>> getSequence(DoIndexed<M, S1, S2> doIndexed) {
    return (Maybe<DoIndexed.Sequence<M, S1, ?, S2>>) doIndexed.match((DoIndexed.Cases) sequenceGetter);
  }

  public static <M, S1, S2> F1<DoIndexed<M, S1, S2>, DoIndexed<M, S1, S2>> setEffect(F2<Monad<M>, S1, __<M, S2>> newEffect) {
    return modEffect(__ -> newEffect);
  }

  public static <M, S1, S2> F1<DoIndexed<M, S1, S2>, DoIndexed<M, S1, S2>> modEffect(F1<F2<Monad<M>, S1, __<M, S2>>, F2<Monad<M>, S1, __<M, S2>>> effectMod) {
    DoIndexed.Cases<M, S1, S2, DoIndexed<M, S1, S2>> cases = DoIndexedImpl.cases((effect) -> Effect(effectMod.apply(effect)),
        DoIndexedImpl::Sequence);
    return doIndexed -> doIndexed.match(cases);
  }

  public static <M, S1, S2> F1<DoIndexed<M, S1, S2>, DoIndexed<M, S1, S2>> setSequence(DoIndexed.Sequence<M, S1, ?, S2> newSequence) {
    return modSequence(__ -> newSequence);
  }

  public static <M, S1, S2> F1<DoIndexed<M, S1, S2>, DoIndexed<M, S1, S2>> modSequence(F1<DoIndexed.Sequence<M, S1, ?, S2>, DoIndexed.Sequence<M, S1, ?, S2>> sequenceMod) {
    DoIndexed.Cases<M, S1, S2, DoIndexed<M, S1, S2>> cases = DoIndexedImpl.cases(DoIndexedImpl::Effect,
        (sequence) -> Sequence(sequenceMod.apply(sequence)));
    return doIndexed -> doIndexed.match(cases);
  }

  private static final class LambdaCases<M, S1, S2, R> implements DoIndexed.Cases<M, S1, S2, R> {
    private final F1<F2<Monad<M>, S1, __<M, S2>>, R> Effect;

    private final F1<DoIndexed.Sequence<M, S1, ?, S2>, R> Sequence;

    LambdaCases(F1<F2<Monad<M>, S1, __<M, S2>>, R> Effect,
        F1<DoIndexed.Sequence<M, S1, ?, S2>, R> Sequence) {
      this.Effect = Effect;
      this.Sequence = Sequence;
    }

    @Override
    public R Effect(F2<Monad<M>, S1, __<M, S2>> effect) {
      return this.Effect.apply(effect);
    }

    @Override
    public R Sequence(DoIndexed.Sequence<M, S1, ?, S2> sequence) {
      return this.Sequence.apply(sequence);
    }
  }

  private static final class Effect<M, S1, S2> extends DoIndexed<M, S1, S2> {
    private final F2<Monad<M>, S1, __<M, S2>> effect;

    Effect(F2<Monad<M>, S1, __<M, S2>> effect) {
      this.effect = effect;
    }

    @Override
    public <R> R match(Cases<M, S1, S2, R> cases) {
      return cases.Effect(this.effect);
    }
  }

  private static final class Sequence_<M, S1, S2> extends DoIndexed<M, S1, S2> {
    private final Sequence<M, S1, ?, S2> sequence;

    Sequence_(Sequence<M, S1, ?, S2> sequence) {
      this.sequence = sequence;
    }

    @Override
    public <R> R match(Cases<M, S1, S2, R> cases) {
      return cases.Sequence(this.sequence);
    }
  }

  private static final class Lazy<M, S1, S2> extends DoIndexed<M, S1, S2> {
    private volatile Supplier<DoIndexed<M, S1, S2>> expression;

    private DoIndexed<M, S1, S2> evaluation;

    Lazy(Supplier<DoIndexed<M, S1, S2>> doIndexed) {
      this.expression = doIndexed;
    }

    private synchronized DoIndexed<M, S1, S2> _evaluate() {
      Supplier<DoIndexed<M, S1, S2>> e = expression;
      if (e != null) {
        evaluation = e.get();
        expression = null;
      }
      return evaluation;
    }

    @Override
    public <R> R match(Cases<M, S1, S2, R> cases) {
      return (this.expression == null ? this.evaluation : _evaluate()).match(cases);
    }
  }

  public static class CasesMatchers {
    private static final TotalMatcher_Effect<?, ?, ?> totalMatcher_Effect = new TotalMatcher_Effect<>();

    private CasesMatchers() {
    }

    public static final class TotalMatcher_Effect<M, S1, S2> {
      TotalMatcher_Effect() {
      }

      public <R> TotalMatcher_Sequence<M, S1, S2, R> Effect(F1<F2<Monad<M>, S1, __<M, S2>>, R> Effect) {
        return new TotalMatcher_Sequence<>(Effect);
      }

      public <R> TotalMatcher_Sequence<M, S1, S2, R> Effect_(R r) {
        return this.Effect((effect) -> r);
      }

      public <R> PartialMatcher<M, S1, S2, R> Sequence(F1<DoIndexed.Sequence<M, S1, ?, S2>, R> Sequence) {
        return new PartialMatcher<>(null, Sequence);
      }

      public <R> PartialMatcher<M, S1, S2, R> Sequence_(R r) {
        return this.Sequence((sequence) -> r);
      }
    }

    public static final class TotalMatcher_Sequence<M, S1, S2, R> extends PartialMatcher<M, S1, S2, R> {
      TotalMatcher_Sequence(F1<F2<Monad<M>, S1, __<M, S2>>, R> Effect) {
        super(Effect, null);
      }

      public F1<DoIndexed<M, S1, S2>, R> Sequence(F1<DoIndexed.Sequence<M, S1, ?, S2>, R> Sequence) {
        DoIndexed.Cases<M, S1, S2, R> cases = DoIndexedImpl.cases(((PartialMatcher<M, S1, S2, R>) this).Effect, Sequence);
        return doIndexed -> doIndexed.match(cases);
      }

      public F1<DoIndexed<M, S1, S2>, R> Sequence_(R r) {
        return this.Sequence((sequence) -> r);
      }
    }

    public static class PartialMatcher<M, S1, S2, R> {
      private final F1<F2<Monad<M>, S1, __<M, S2>>, R> Effect;

      private final F1<DoIndexed.Sequence<M, S1, ?, S2>, R> Sequence;

      PartialMatcher(F1<F2<Monad<M>, S1, __<M, S2>>, R> Effect,
          F1<DoIndexed.Sequence<M, S1, ?, S2>, R> Sequence) {
        this.Effect = Effect;
        this.Sequence = Sequence;
      }

      public final F1<DoIndexed<M, S1, S2>, R> otherwise(Supplier<R> otherwise) {
        DoIndexed.Cases<M, S1, S2, R> cases = DoIndexedImpl.<M, S1, S2, R>cases(this.Effect != null ? this.Effect : (effect) -> otherwise.get(),
            this.Sequence != null ? this.Sequence : (sequence) -> otherwise.get());
        return doIndexed -> doIndexed.match(cases);
      }

      public final F1<DoIndexed<M, S1, S2>, R> otherwise_(R r) {
        return this.otherwise(() -> r);
      }

      public final F1<DoIndexed<M, S1, S2>, Maybe<R>> otherwiseNothing() {
        DoIndexed.Cases<M, S1, S2, Maybe<R>> cases = DoIndexedImpl.cases((this.Effect != null) ? (effect) -> Maybe.Just(this.Effect.apply(effect))
            : (effect) -> Maybe.Nothing(),
            (this.Sequence != null) ? (sequence) -> Maybe.Just(this.Sequence.apply(sequence))
            : (sequence) -> Maybe.Nothing());
        return doIndexed -> doIndexed.match(cases);
      }

      public final <A> F1<DoIndexed<M, S1, S2>, Either<A, R>> otherwiseLeft(Supplier<A> left) {
        DoIndexed.Cases<M, S1, S2, Either<A, R>> cases = DoIndexedImpl.cases((this.Effect != null) ? (effect) -> Either.Right(this.Effect.apply(effect))
            : (effect) -> Either.Left(left.get()),
            (this.Sequence != null) ? (sequence) -> Either.Right(this.Sequence.apply(sequence))
            : (sequence) -> Either.Left(left.get()));
        return doIndexed -> doIndexed.match(cases);
      }

      public final <A> F1<DoIndexed<M, S1, S2>, Either<A, R>> otherwiseLeft_(A left) {
        return this.otherwiseLeft(() -> left);
      }
    }
  }

  public static class CaseOfMatchers {
    private CaseOfMatchers() {
    }

    public static final class TotalMatcher_Effect<M, S1, S2> {
      private final DoIndexed<M, S1, S2> _doIndexed;

      TotalMatcher_Effect(DoIndexed<M, S1, S2> _doIndexed) {
        this._doIndexed = _doIndexed;
      }

      public <R> TotalMatcher_Sequence<M, S1, S2, R> Effect(F1<F2<Monad<M>, S1, __<M, S2>>, R> Effect) {
        return new TotalMatcher_Sequence<>(this._doIndexed, Effect);
      }

      public <R> TotalMatcher_Sequence<M, S1, S2, R> Effect_(R r) {
        return this.Effect((effect) -> r);
      }

      public <R> PartialMatcher<M, S1, S2, R> Sequence(F1<DoIndexed.Sequence<M, S1, ?, S2>, R> Sequence) {
        return new PartialMatcher<>(this._doIndexed, null, Sequence);
      }

      public <R> PartialMatcher<M, S1, S2, R> Sequence_(R r) {
        return this.Sequence((sequence) -> r);
      }
    }

    public static final class TotalMatcher_Sequence<M, S1, S2, R> extends PartialMatcher<M, S1, S2, R> {
      TotalMatcher_Sequence(DoIndexed<M, S1, S2> _doIndexed,
          F1<F2<Monad<M>, S1, __<M, S2>>, R> Effect) {
        super(_doIndexed, Effect, null);
      }

      public final R Sequence(F1<DoIndexed.Sequence<M, S1, ?, S2>, R> Sequence) {
        DoIndexed.Cases<M, S1, S2, R> cases = DoIndexedImpl.cases(((PartialMatcher<M, S1, S2, R>) this).Effect, Sequence);
        return ((PartialMatcher<M, S1, S2, R>) this)._doIndexed.match(cases);
      }

      public R Sequence_(R r) {
        return this.Sequence((sequence) -> r);
      }
    }

    public static class PartialMatcher<M, S1, S2, R> {
      private final DoIndexed<M, S1, S2> _doIndexed;

      private final F1<F2<Monad<M>, S1, __<M, S2>>, R> Effect;

      private final F1<DoIndexed.Sequence<M, S1, ?, S2>, R> Sequence;

      PartialMatcher(DoIndexed<M, S1, S2> _doIndexed, F1<F2<Monad<M>, S1, __<M, S2>>, R> Effect,
          F1<DoIndexed.Sequence<M, S1, ?, S2>, R> Sequence) {
        this._doIndexed = _doIndexed;
        this.Effect = Effect;
        this.Sequence = Sequence;
      }

      public final R otherwise(Supplier<R> otherwise) {
        DoIndexed.Cases<M, S1, S2, R> cases = DoIndexedImpl.<M, S1, S2, R>cases(this.Effect != null ? this.Effect : (effect) -> otherwise.get(),
            this.Sequence != null ? this.Sequence : (sequence) -> otherwise.get());
        return this._doIndexed.match(cases);
      }

      public final R otherwise_(R r) {
        return this.otherwise(() -> r);
      }

      public final Maybe<R> otherwiseNothing() {
        DoIndexed.Cases<M, S1, S2, Maybe<R>> cases = DoIndexedImpl.cases((this.Effect != null) ? (effect) -> Maybe.Just(this.Effect.apply(effect))
            : (effect) -> Maybe.Nothing(),
            (this.Sequence != null) ? (sequence) -> Maybe.Just(this.Sequence.apply(sequence))
            : (sequence) -> Maybe.Nothing());
        return this._doIndexed.match(cases);
      }

      public final <A> Either<A, R> otherwiseLeft(Supplier<A> left) {
        DoIndexed.Cases<M, S1, S2, Either<A, R>> cases = DoIndexedImpl.cases((this.Effect != null) ? (effect) -> Either.Right(this.Effect.apply(effect))
            : (effect) -> Either.Left(left.get()),
            (this.Sequence != null) ? (sequence) -> Either.Right(this.Sequence.apply(sequence))
            : (sequence) -> Either.Left(left.get()));
        return this._doIndexed.match(cases);
      }

      public final <A> Either<A, R> otherwiseLeft_(A left) {
        return this.otherwiseLeft(() -> left);
      }
    }
  }
}
