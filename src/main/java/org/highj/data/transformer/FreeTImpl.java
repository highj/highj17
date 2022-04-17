package org.highj.data.transformer;

import org.highj.hkt.__;
import org.highj.data.Either;
import org.highj.data.Maybe;
import org.highj.function.F1;

import java.util.function.Supplier;

final class FreeTImpl {
  private static final FreeT.Cases<Maybe<__<Object, Object>>, Object, Object, Object> fGetter = FreeTImpl.cases((f) -> Maybe.Just(f),
  (m) -> Maybe.Nothing(),
  (done) -> Maybe.Nothing(),
  (bound) -> Maybe.Nothing(),
  (suspend) -> Maybe.Nothing());

  private static final FreeT.Cases<Maybe<__<Object, Object>>, Object, Object, Object> mGetter = FreeTImpl.cases((f) -> Maybe.Nothing(),
  (m) -> Maybe.Just(m),
  (done) -> Maybe.Nothing(),
  (bound) -> Maybe.Nothing(),
  (suspend) -> Maybe.Nothing());

  private static final FreeT.Cases<Maybe<Object>, Object, Object, Object> doneGetter = FreeTImpl.cases((f) -> Maybe.Nothing(),
  (m) -> Maybe.Nothing(),
  (done) -> Maybe.Just(done),
  (bound) -> Maybe.Nothing(),
  (suspend) -> Maybe.Nothing());

  private static final FreeT.Cases<Maybe<FreeT.Bound<Object, Object, ?, Object>>, Object, Object, Object> boundGetter = FreeTImpl.cases((f) -> Maybe.Nothing(),
  (m) -> Maybe.Nothing(),
  (done) -> Maybe.Nothing(),
  (bound) -> Maybe.Just(bound),
  (suspend) -> Maybe.Nothing());

  private static final FreeT.Cases<Maybe<Supplier<FreeT<Object, Object, Object>>>, Object, Object, Object> suspendGetter = FreeTImpl.cases((f) -> Maybe.Nothing(),
  (m) -> Maybe.Nothing(),
  (done) -> Maybe.Nothing(),
  (bound) -> Maybe.Nothing(),
  (suspend) -> Maybe.Just(suspend));

  private FreeTImpl() {
  }

  public static <F, M, A, R> FreeT.Cases<R, F, M, A> cases(F1<__<F, A>, R> liftF,
      F1<__<M, A>, R> liftM, F1<A, R> done, F1<FreeT.Bound<F, M, ?, A>, R> bind,
      F1<Supplier<FreeT<F, M, A>>, R> suspend) {
    return new LambdaCases<>(liftF, liftM, done, bind, suspend);
  }

  public static <F, M, A> FreeT<F, M, A> liftF(__<F, A> f) {
    return new LiftF<>(f);
  }

  public static <F, M, A> FreeT<F, M, A> liftM(__<M, A> m) {
    return new LiftM<>(m);
  }

  public static <F, M, A> FreeT<F, M, A> done(A done) {
    return new Done<>(done);
  }

  public static <F, M, A> FreeT<F, M, A> bind(FreeT.Bound<F, M, ?, A> bound) {
    return new Bind<>(bound);
  }

  public static <F, M, A> FreeT<F, M, A> suspend(Supplier<FreeT<F, M, A>> suspend) {
    return new Suspend<>(suspend);
  }

  public static <F, M, A> FreeT<F, M, A> lazy(Supplier<FreeT<F, M, A>> freeT) {
    return new Lazy<>(freeT);
  }

  @SuppressWarnings("unchecked")
  public static <F, M, A> CasesMatchers.TotalMatcher_LiftF<F, M, A> cases() {
    return (CasesMatchers.TotalMatcher_LiftF<F, M, A>) CasesMatchers.totalMatcher_LiftF;
  }

  public static <F, M, A> CaseOfMatchers.TotalMatcher_LiftF<F, M, A> caseOf(FreeT<F, M, A> freeT) {
    return new CaseOfMatchers.TotalMatcher_LiftF<F, M, A>(freeT);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public static <F, M, A> Maybe<__<F, A>> getF(FreeT<F, M, A> freeT) {
    return (Maybe<__<F, A>>) freeT.match((FreeT.Cases) fGetter);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public static <F, M, A> Maybe<__<M, A>> getM(FreeT<F, M, A> freeT) {
    return (Maybe<__<M, A>>) freeT.match((FreeT.Cases) mGetter);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public static <F, M, A> Maybe<A> getDone(FreeT<F, M, A> freeT) {
    return (Maybe<A>) freeT.match((FreeT.Cases) doneGetter);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public static <F, M, A> Maybe<FreeT.Bound<F, M, ?, A>> getBound(FreeT<F, M, A> freeT) {
    return (Maybe<FreeT.Bound<F, M, ?, A>>) freeT.match((FreeT.Cases) boundGetter);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public static <F, M, A> Maybe<Supplier<FreeT<F, M, A>>> getSuspend(FreeT<F, M, A> freeT) {
    return (Maybe<Supplier<FreeT<F, M, A>>>) freeT.match((FreeT.Cases) suspendGetter);
  }

  public static <F, M, A> F1<FreeT<F, M, A>, FreeT<F, M, A>> setF(__<F, A> newF) {
    return modF(__ -> newF);
  }

  public static <F, M, A> F1<FreeT<F, M, A>, FreeT<F, M, A>> modF(F1<__<F, A>, __<F, A>> fMod) {
    FreeT.Cases<FreeT<F, M, A>, F, M, A> cases = cases((f) -> liftF(fMod.apply(f)),
        FreeTImpl::liftM,
        FreeTImpl::done,
        FreeTImpl::bind,
        FreeTImpl::suspend);
    return freeT -> freeT.match(cases);
  }

  public static <F, M, A> F1<FreeT<F, M, A>, FreeT<F, M, A>> setM(__<M, A> newM) {
    return modM(__ -> newM);
  }

  public static <F, M, A> F1<FreeT<F, M, A>, FreeT<F, M, A>> modM(F1<__<M, A>, __<M, A>> mMod) {
    FreeT.Cases<FreeT<F, M, A>, F, M, A> cases = cases(FreeTImpl::liftF,
        (m) -> liftM(mMod.apply(m)),
        FreeTImpl::done,
        FreeTImpl::bind,
        FreeTImpl::suspend);
    return freeT -> freeT.match(cases);
  }

  public static <F, M, A> F1<FreeT<F, M, A>, FreeT<F, M, A>> setDone(A newDone) {
    return modDone(__ -> newDone);
  }

  public static <F, M, A> F1<FreeT<F, M, A>, FreeT<F, M, A>> modDone(F1<A, A> doneMod) {
    FreeT.Cases<FreeT<F, M, A>, F, M, A> cases = cases(FreeTImpl::liftF,
        FreeTImpl::liftM,
        (done) -> done(doneMod.apply(done)),
        FreeTImpl::bind,
        FreeTImpl::suspend);
    return freeT -> freeT.match(cases);
  }

  public static <F, M, A> F1<FreeT<F, M, A>, FreeT<F, M, A>> setBound(FreeT.Bound<F, M, ?, A> newBound) {
    return modBound(__ -> newBound);
  }

  public static <F, M, A> F1<FreeT<F, M, A>, FreeT<F, M, A>> modBound(F1<FreeT.Bound<F, M, ?, A>, FreeT.Bound<F, M, ?, A>> boundMod) {
    FreeT.Cases<FreeT<F, M, A>, F, M, A> cases = cases(FreeTImpl::liftF,
        FreeTImpl::liftM,
        FreeTImpl::done,
        (bound) -> bind(boundMod.apply(bound)),
        FreeTImpl::suspend);
    return freeT -> freeT.match(cases);
  }

  public static <F, M, A> F1<FreeT<F, M, A>, FreeT<F, M, A>> setSuspend(Supplier<FreeT<F, M, A>> newSuspend) {
    return modSuspend(__ -> newSuspend);
  }

  public static <F, M, A> F1<FreeT<F, M, A>, FreeT<F, M, A>> modSuspend(F1<Supplier<FreeT<F, M, A>>, Supplier<FreeT<F, M, A>>> suspendMod) {
    FreeT.Cases<FreeT<F, M, A>, F, M, A> cases = cases(FreeTImpl::liftF,
        FreeTImpl::liftM,
        FreeTImpl::done,
        FreeTImpl::bind,
        (suspend) -> suspend(suspendMod.apply(suspend)));
    return freeT -> freeT.match(cases);
  }

  private static final class LambdaCases<F, M, A, R> implements FreeT.Cases<R, F, M, A> {
    private final F1<__<F, A>, R> liftF;

    private final F1<__<M, A>, R> liftM;

    private final F1<A, R> done;

    private final F1<FreeT.Bound<F, M, ?, A>, R> bind;

    private final F1<Supplier<FreeT<F, M, A>>, R> suspend;

    LambdaCases(F1<__<F, A>, R> liftF, F1<__<M, A>, R> liftM, F1<A, R> done,
        F1<FreeT.Bound<F, M, ?, A>, R> bind, F1<Supplier<FreeT<F, M, A>>, R> suspend) {
      this.liftF = liftF;
      this.liftM = liftM;
      this.done = done;
      this.bind = bind;
      this.suspend = suspend;
    }

    @Override
    public R liftF(__<F, A> f) {
      return this.liftF.apply(f);
    }

    @Override
    public R liftM(__<M, A> m) {
      return this.liftM.apply(m);
    }

    @Override
    public R done(A done) {
      return this.done.apply(done);
    }

    @Override
    public R bind(FreeT.Bound<F, M, ?, A> bound) {
      return this.bind.apply(bound);
    }

    @Override
    public R suspend(Supplier<FreeT<F, M, A>> suspend) {
      return this.suspend.apply(suspend);
    }
  }

  private static final class LiftF<F, M, A> extends FreeT<F, M, A> {
    private final __<F, A> f;

    LiftF(__<F, A> f) {
      this.f = f;
    }

    @Override
    public <R> R match(Cases<R, F, M, A> cases) {
      return cases.liftF(this.f);
    }
  }

  private static final class LiftM<F, M, A> extends FreeT<F, M, A> {
    private final __<M, A> m;

    LiftM(__<M, A> m) {
      this.m = m;
    }

    @Override
    public <R> R match(Cases<R, F, M, A> cases) {
      return cases.liftM(this.m);
    }
  }

  private static final class Done<F, M, A> extends FreeT<F, M, A> {
    private final A done;

    Done(A done) {
      this.done = done;
    }

    @Override
    public <R> R match(Cases<R, F, M, A> cases) {
      return cases.done(this.done);
    }
  }

  private static final class Bind<F, M, A> extends FreeT<F, M, A> {
    private final Bound<F, M, ?, A> bound;

    Bind(Bound<F, M, ?, A> bound) {
      this.bound = bound;
    }

    @Override
    public <R> R match(Cases<R, F, M, A> cases) {
      return cases.bind(this.bound);
    }
  }

  private static final class Suspend<F, M, A> extends FreeT<F, M, A> {
    private final Supplier<FreeT<F, M, A>> suspend;

    Suspend(Supplier<FreeT<F, M, A>> suspend) {
      this.suspend = suspend;
    }

    @Override
    public <R> R match(Cases<R, F, M, A> cases) {
      return cases.suspend(this.suspend);
    }
  }

  private static final class Lazy<F, M, A> extends FreeT<F, M, A> {
    private volatile Supplier<FreeT<F, M, A>> expression;

    private FreeT<F, M, A> evaluation;

    Lazy(Supplier<FreeT<F, M, A>> freeT) {
      this.expression = freeT;
    }

    private synchronized FreeT<F, M, A> _evaluate() {
      Supplier<FreeT<F, M, A>> e = expression;
      if (e != null) {
        evaluation = e.get();
        expression = null;
      }
      return evaluation;
    }

    @Override
    public <R> R match(Cases<R, F, M, A> cases) {
      return (this.expression == null ? this.evaluation : _evaluate()).match(cases);
    }
  }

  public static class CasesMatchers {
    private static final TotalMatcher_LiftF<?, ?, ?> totalMatcher_LiftF = new TotalMatcher_LiftF<>();

    private CasesMatchers() {
    }

    public static final class TotalMatcher_LiftF<F, M, A> {
      TotalMatcher_LiftF() {
      }

      public final <R> TotalMatcher_LiftM<F, M, A, R> liftF(F1<__<F, A>, R> liftF) {
        return new TotalMatcher_LiftM<>(liftF);
      }

      public final <R> TotalMatcher_LiftM<F, M, A, R> liftF_(R r) {
        return this.liftF((f) -> r);
      }

      public final <R> PartialMatcher_Done<F, M, A, R> liftM(F1<__<M, A>, R> liftM) {
        return new PartialMatcher_Done<>(null, liftM);
      }

      public final <R> PartialMatcher_Done<F, M, A, R> liftM_(R r) {
        return this.liftM((m) -> r);
      }

      public final <R> PartialMatcher_Bind<F, M, A, R> done(F1<A, R> done) {
        return new PartialMatcher_Bind<>(null, null, done);
      }

      public final <R> PartialMatcher_Bind<F, M, A, R> done_(R r) {
        return this.done((done) -> r);
      }

      public final <R> PartialMatcher_Suspend<F, M, A, R> bind(F1<FreeT.Bound<F, M, ?, A>, R> bind) {
        return new PartialMatcher_Suspend<>(null, null, null, bind);
      }

      public final <R> PartialMatcher_Suspend<F, M, A, R> bind_(R r) {
        return this.bind((bound) -> r);
      }

      public final <R> PartialMatcher<F, M, A, R> suspend(F1<Supplier<FreeT<F, M, A>>, R> suspend) {
        return new PartialMatcher<>(null, null, null, null, suspend);
      }

      public final <R> PartialMatcher<F, M, A, R> suspend_(R r) {
        return this.suspend((suspend) -> r);
      }
    }

    public static final class TotalMatcher_LiftM<F, M, A, R> extends PartialMatcher_Done<F, M, A, R> {
      TotalMatcher_LiftM(F1<__<F, A>, R> liftF) {
        super(liftF, null);
      }

      public final TotalMatcher_Done<F, M, A, R> liftM(F1<__<M, A>, R> liftM) {
        return new TotalMatcher_Done<>(((PartialMatcher<F, M, A, R>) this).liftF, liftM);
      }

      public final TotalMatcher_Done<F, M, A, R> liftM_(R r) {
        return this.liftM((m) -> r);
      }
    }

    public static final class TotalMatcher_Done<F, M, A, R> extends PartialMatcher_Bind<F, M, A, R> {
      TotalMatcher_Done(F1<__<F, A>, R> liftF, F1<__<M, A>, R> liftM) {
        super(liftF, liftM, null);
      }

      public final TotalMatcher_Bind<F, M, A, R> done(F1<A, R> done) {
        return new TotalMatcher_Bind<>(((PartialMatcher<F, M, A, R>) this).liftF, ((PartialMatcher<F, M, A, R>) this).liftM, done);
      }

      public final TotalMatcher_Bind<F, M, A, R> done_(R r) {
        return this.done((done) -> r);
      }
    }

    public static final class TotalMatcher_Bind<F, M, A, R> extends PartialMatcher_Suspend<F, M, A, R> {
      TotalMatcher_Bind(F1<__<F, A>, R> liftF, F1<__<M, A>, R> liftM, F1<A, R> done) {
        super(liftF, liftM, done, null);
      }

      public final TotalMatcher_Suspend<F, M, A, R> bind(F1<FreeT.Bound<F, M, ?, A>, R> bind) {
        return new TotalMatcher_Suspend<>(((PartialMatcher<F, M, A, R>) this).liftF, ((PartialMatcher<F, M, A, R>) this).liftM, ((PartialMatcher<F, M, A, R>) this).done, bind);
      }

      public final TotalMatcher_Suspend<F, M, A, R> bind_(R r) {
        return this.bind((bound) -> r);
      }
    }

    public static final class TotalMatcher_Suspend<F, M, A, R> extends PartialMatcher<F, M, A, R> {
      TotalMatcher_Suspend(F1<__<F, A>, R> liftF, F1<__<M, A>, R> liftM, F1<A, R> done,
          F1<FreeT.Bound<F, M, ?, A>, R> bind) {
        super(liftF, liftM, done, bind, null);
      }

      public final F1<FreeT<F, M, A>, R> suspend(F1<Supplier<FreeT<F, M, A>>, R> suspend) {
        FreeT.Cases<R, F, M, A> cases = FreeTImpl.cases(((PartialMatcher<F, M, A, R>) this).liftF, ((PartialMatcher<F, M, A, R>) this).liftM, ((PartialMatcher<F, M, A, R>) this).done, ((PartialMatcher<F, M, A, R>) this).bind, suspend);
        return freeT -> freeT.match(cases);
      }

      public final F1<FreeT<F, M, A>, R> suspend_(R r) {
        return this.suspend((suspend) -> r);
      }
    }

    public static class PartialMatcher_Done<F, M, A, R> extends PartialMatcher_Bind<F, M, A, R> {
      PartialMatcher_Done(F1<__<F, A>, R> liftF, F1<__<M, A>, R> liftM) {
        super(liftF, liftM, null);
      }

      public final PartialMatcher_Bind<F, M, A, R> done(F1<A, R> done) {
        return new PartialMatcher_Bind<>(((PartialMatcher<F, M, A, R>) this).liftF, ((PartialMatcher<F, M, A, R>) this).liftM, done);
      }

      public final PartialMatcher_Bind<F, M, A, R> done_(R r) {
        return this.done((done) -> r);
      }
    }

    public static class PartialMatcher_Bind<F, M, A, R> extends PartialMatcher_Suspend<F, M, A, R> {
      PartialMatcher_Bind(F1<__<F, A>, R> liftF, F1<__<M, A>, R> liftM, F1<A, R> done) {
        super(liftF, liftM, done, null);
      }

      public final PartialMatcher_Suspend<F, M, A, R> bind(F1<FreeT.Bound<F, M, ?, A>, R> bind) {
        return new PartialMatcher_Suspend<>(((PartialMatcher<F, M, A, R>) this).liftF, ((PartialMatcher<F, M, A, R>) this).liftM, ((PartialMatcher<F, M, A, R>) this).done, bind);
      }

      public final PartialMatcher_Suspend<F, M, A, R> bind_(R r) {
        return this.bind((bound) -> r);
      }
    }

    public static class PartialMatcher_Suspend<F, M, A, R> extends PartialMatcher<F, M, A, R> {
      PartialMatcher_Suspend(F1<__<F, A>, R> liftF, F1<__<M, A>, R> liftM, F1<A, R> done,
          F1<FreeT.Bound<F, M, ?, A>, R> bind) {
        super(liftF, liftM, done, bind, null);
      }

      public final PartialMatcher<F, M, A, R> suspend(F1<Supplier<FreeT<F, M, A>>, R> suspend) {
        return new PartialMatcher<>(((PartialMatcher<F, M, A, R>) this).liftF, ((PartialMatcher<F, M, A, R>) this).liftM, ((PartialMatcher<F, M, A, R>) this).done, ((PartialMatcher<F, M, A, R>) this).bind, suspend);
      }

      public final PartialMatcher<F, M, A, R> suspend_(R r) {
        return this.suspend((suspend) -> r);
      }
    }

    public static class PartialMatcher<F, M, A, R> {
      private final F1<__<F, A>, R> liftF;

      private final F1<__<M, A>, R> liftM;

      private final F1<A, R> done;

      private final F1<FreeT.Bound<F, M, ?, A>, R> bind;

      private final F1<Supplier<FreeT<F, M, A>>, R> suspend;

      PartialMatcher(F1<__<F, A>, R> liftF, F1<__<M, A>, R> liftM, F1<A, R> done,
          F1<FreeT.Bound<F, M, ?, A>, R> bind, F1<Supplier<FreeT<F, M, A>>, R> suspend) {
        this.liftF = liftF;
        this.liftM = liftM;
        this.done = done;
        this.bind = bind;
        this.suspend = suspend;
      }

      public final F1<FreeT<F, M, A>, R> otherwise(Supplier<R> otherwise) {
        FreeT.Cases<R, F, M, A> cases = FreeTImpl.<F, M, A, R>cases(this.liftF != null ? this.liftF : (f) -> otherwise.get(),
            this.liftM != null ? this.liftM : (m) -> otherwise.get(),
            this.done != null ? this.done : (done) -> otherwise.get(),
            this.bind != null ? this.bind : (bound) -> otherwise.get(),
            this.suspend != null ? this.suspend : (suspend) -> otherwise.get());
        return freeT -> freeT.match(cases);
      }

      public final F1<FreeT<F, M, A>, R> otherwise_(R r) {
        return this.otherwise(() -> r);
      }

      public final F1<FreeT<F, M, A>, Maybe<R>> otherwiseNothing() {
        FreeT.Cases<Maybe<R>, F, M, A> cases = FreeTImpl.cases((this.liftF != null) ? (f) -> Maybe.Just(this.liftF.apply(f))
            : (f) -> Maybe.Nothing(),
            (this.liftM != null) ? (m) -> Maybe.Just(this.liftM.apply(m))
            : (m) -> Maybe.Nothing(),
            (this.done != null) ? (done) -> Maybe.Just(this.done.apply(done))
            : (done) -> Maybe.Nothing(),
            (this.bind != null) ? (bound) -> Maybe.Just(this.bind.apply(bound))
            : (bound) -> Maybe.Nothing(),
            (this.suspend != null) ? (suspend) -> Maybe.Just(this.suspend.apply(suspend))
            : (suspend) -> Maybe.Nothing());
        return freeT -> freeT.match(cases);
      }

      public final <A_> F1<FreeT<F, M, A>, Either<A_, R>> otherwiseLeft(Supplier<A_> left) {
        FreeT.Cases<Either<A_, R>, F, M, A> cases = FreeTImpl.cases((this.liftF != null) ? (f) -> Either.Right(this.liftF.apply(f))
            : (f) -> Either.Left(left.get()),
            (this.liftM != null) ? (m) -> Either.Right(this.liftM.apply(m))
            : (m) -> Either.Left(left.get()),
            (this.done != null) ? (done) -> Either.Right(this.done.apply(done))
            : (done) -> Either.Left(left.get()),
            (this.bind != null) ? (bound) -> Either.Right(this.bind.apply(bound))
            : (bound) -> Either.Left(left.get()),
            (this.suspend != null) ? (suspend) -> Either.Right(this.suspend.apply(suspend))
            : (suspend) -> Either.Left(left.get()));
        return freeT -> freeT.match(cases);
      }

      public final <A_> F1<FreeT<F, M, A>, Either<A_, R>> otherwiseLeft_(A_ left) {
        return this.otherwiseLeft(() -> left);
      }
    }
  }

  public static class CaseOfMatchers {
    private CaseOfMatchers() {
    }

    public static final class TotalMatcher_LiftF<F, M, A> {
      private final FreeT<F, M, A> _freeT;

      TotalMatcher_LiftF(FreeT<F, M, A> _freeT) {
        this._freeT = _freeT;
      }

      public final <R> TotalMatcher_LiftM<F, M, A, R> liftF(F1<__<F, A>, R> liftF) {
        return new TotalMatcher_LiftM<>(this._freeT, liftF);
      }

      public final <R> TotalMatcher_LiftM<F, M, A, R> liftF_(R r) {
        return this.liftF((f) -> r);
      }

      public final <R> PartialMatcher_Done<F, M, A, R> liftM(F1<__<M, A>, R> liftM) {
        return new PartialMatcher_Done<>(this._freeT, null, liftM);
      }

      public final <R> PartialMatcher_Done<F, M, A, R> liftM_(R r) {
        return this.liftM((m) -> r);
      }

      public final <R> PartialMatcher_Bind<F, M, A, R> done(F1<A, R> done) {
        return new PartialMatcher_Bind<>(this._freeT, null, null, done);
      }

      public final <R> PartialMatcher_Bind<F, M, A, R> done_(R r) {
        return this.done((done) -> r);
      }

      public final <R> PartialMatcher_Suspend<F, M, A, R> bind(F1<FreeT.Bound<F, M, ?, A>, R> bind) {
        return new PartialMatcher_Suspend<>(this._freeT, null, null, null, bind);
      }

      public final <R> PartialMatcher_Suspend<F, M, A, R> bind_(R r) {
        return this.bind((bound) -> r);
      }

      public final <R> PartialMatcher<F, M, A, R> suspend(F1<Supplier<FreeT<F, M, A>>, R> suspend) {
        return new PartialMatcher<>(this._freeT, null, null, null, null, suspend);
      }

      public final <R> PartialMatcher<F, M, A, R> suspend_(R r) {
        return this.suspend((suspend) -> r);
      }
    }

    public static final class TotalMatcher_LiftM<F, M, A, R> extends PartialMatcher_Done<F, M, A, R> {
      TotalMatcher_LiftM(FreeT<F, M, A> _freeT, F1<__<F, A>, R> liftF) {
        super(_freeT, liftF, null);
      }

      public final TotalMatcher_Done<F, M, A, R> liftM(F1<__<M, A>, R> liftM) {
        return new TotalMatcher_Done<>(((PartialMatcher<F, M, A, R>) this)._freeT, ((PartialMatcher<F, M, A, R>) this).liftF, liftM);
      }

      public final TotalMatcher_Done<F, M, A, R> liftM_(R r) {
        return this.liftM((m) -> r);
      }
    }

    public static final class TotalMatcher_Done<F, M, A, R> extends PartialMatcher_Bind<F, M, A, R> {
      TotalMatcher_Done(FreeT<F, M, A> _freeT, F1<__<F, A>, R> liftF, F1<__<M, A>, R> liftM) {
        super(_freeT, liftF, liftM, null);
      }

      public final TotalMatcher_Bind<F, M, A, R> done(F1<A, R> done) {
        return new TotalMatcher_Bind<>(((PartialMatcher<F, M, A, R>) this)._freeT, ((PartialMatcher<F, M, A, R>) this).liftF, ((PartialMatcher<F, M, A, R>) this).liftM, done);
      }

      public final TotalMatcher_Bind<F, M, A, R> done_(R r) {
        return this.done((done) -> r);
      }
    }

    public static final class TotalMatcher_Bind<F, M, A, R> extends PartialMatcher_Suspend<F, M, A, R> {
      TotalMatcher_Bind(FreeT<F, M, A> _freeT, F1<__<F, A>, R> liftF, F1<__<M, A>, R> liftM,
          F1<A, R> done) {
        super(_freeT, liftF, liftM, done, null);
      }

      public final TotalMatcher_Suspend<F, M, A, R> bind(F1<FreeT.Bound<F, M, ?, A>, R> bind) {
        return new TotalMatcher_Suspend<>(((PartialMatcher<F, M, A, R>) this)._freeT, ((PartialMatcher<F, M, A, R>) this).liftF, ((PartialMatcher<F, M, A, R>) this).liftM, ((PartialMatcher<F, M, A, R>) this).done, bind);
      }

      public final TotalMatcher_Suspend<F, M, A, R> bind_(R r) {
        return this.bind((bound) -> r);
      }
    }

    public static final class TotalMatcher_Suspend<F, M, A, R> extends PartialMatcher<F, M, A, R> {
      TotalMatcher_Suspend(FreeT<F, M, A> _freeT, F1<__<F, A>, R> liftF, F1<__<M, A>, R> liftM,
          F1<A, R> done, F1<FreeT.Bound<F, M, ?, A>, R> bind) {
        super(_freeT, liftF, liftM, done, bind, null);
      }

      public final R suspend(F1<Supplier<FreeT<F, M, A>>, R> suspend) {
        FreeT.Cases<R, F, M, A> cases = FreeTImpl.cases(((PartialMatcher<F, M, A, R>) this).liftF, ((PartialMatcher<F, M, A, R>) this).liftM, ((PartialMatcher<F, M, A, R>) this).done, ((PartialMatcher<F, M, A, R>) this).bind, suspend);
        return ((PartialMatcher<F, M, A, R>) this)._freeT.match(cases);
      }

      public final R suspend_(R r) {
        return this.suspend((suspend) -> r);
      }
    }

    public static class PartialMatcher_Done<F, M, A, R> extends PartialMatcher_Bind<F, M, A, R> {
      PartialMatcher_Done(FreeT<F, M, A> _freeT, F1<__<F, A>, R> liftF, F1<__<M, A>, R> liftM) {
        super(_freeT, liftF, liftM, null);
      }

      public final PartialMatcher_Bind<F, M, A, R> done(F1<A, R> done) {
        return new PartialMatcher_Bind<>(((PartialMatcher<F, M, A, R>) this)._freeT, ((PartialMatcher<F, M, A, R>) this).liftF, ((PartialMatcher<F, M, A, R>) this).liftM, done);
      }

      public final PartialMatcher_Bind<F, M, A, R> done_(R r) {
        return this.done((done) -> r);
      }
    }

    public static class PartialMatcher_Bind<F, M, A, R> extends PartialMatcher_Suspend<F, M, A, R> {
      PartialMatcher_Bind(FreeT<F, M, A> _freeT, F1<__<F, A>, R> liftF, F1<__<M, A>, R> liftM,
          F1<A, R> done) {
        super(_freeT, liftF, liftM, done, null);
      }

      public final PartialMatcher_Suspend<F, M, A, R> bind(F1<FreeT.Bound<F, M, ?, A>, R> bind) {
        return new PartialMatcher_Suspend<>(((PartialMatcher<F, M, A, R>) this)._freeT, ((PartialMatcher<F, M, A, R>) this).liftF, ((PartialMatcher<F, M, A, R>) this).liftM, ((PartialMatcher<F, M, A, R>) this).done, bind);
      }

      public final PartialMatcher_Suspend<F, M, A, R> bind_(R r) {
        return this.bind((bound) -> r);
      }
    }

    public static class PartialMatcher_Suspend<F, M, A, R> extends PartialMatcher<F, M, A, R> {
      PartialMatcher_Suspend(FreeT<F, M, A> _freeT, F1<__<F, A>, R> liftF, F1<__<M, A>, R> liftM,
          F1<A, R> done, F1<FreeT.Bound<F, M, ?, A>, R> bind) {
        super(_freeT, liftF, liftM, done, bind, null);
      }

      public final PartialMatcher<F, M, A, R> suspend(F1<Supplier<FreeT<F, M, A>>, R> suspend) {
        return new PartialMatcher<>(((PartialMatcher<F, M, A, R>) this)._freeT, ((PartialMatcher<F, M, A, R>) this).liftF, ((PartialMatcher<F, M, A, R>) this).liftM, ((PartialMatcher<F, M, A, R>) this).done, ((PartialMatcher<F, M, A, R>) this).bind, suspend);
      }

      public final PartialMatcher<F, M, A, R> suspend_(R r) {
        return this.suspend((suspend) -> r);
      }
    }

    public static class PartialMatcher<F, M, A, R> {
      private final FreeT<F, M, A> _freeT;

      private final F1<__<F, A>, R> liftF;

      private final F1<__<M, A>, R> liftM;

      private final F1<A, R> done;

      private final F1<FreeT.Bound<F, M, ?, A>, R> bind;

      private final F1<Supplier<FreeT<F, M, A>>, R> suspend;

      PartialMatcher(FreeT<F, M, A> _freeT, F1<__<F, A>, R> liftF, F1<__<M, A>, R> liftM,
          F1<A, R> done, F1<FreeT.Bound<F, M, ?, A>, R> bind,
          F1<Supplier<FreeT<F, M, A>>, R> suspend) {
        this._freeT = _freeT;
        this.liftF = liftF;
        this.liftM = liftM;
        this.done = done;
        this.bind = bind;
        this.suspend = suspend;
      }

      public final R otherwise(Supplier<R> otherwise) {
        FreeT.Cases<R, F, M, A> cases = FreeTImpl.<F, M, A, R>cases(this.liftF != null ? this.liftF : (f) -> otherwise.get(),
            this.liftM != null ? this.liftM : (m) -> otherwise.get(),
            this.done != null ? this.done : (done) -> otherwise.get(),
            this.bind != null ? this.bind : (bound) -> otherwise.get(),
            this.suspend != null ? this.suspend : (suspend) -> otherwise.get());
        return this._freeT.match(cases);
      }

      public final R otherwise_(R r) {
        return this.otherwise(() -> r);
      }

      public final Maybe<R> otherwiseNothing() {
        FreeT.Cases<Maybe<R>, F, M, A> cases = FreeTImpl.cases((this.liftF != null) ? (f) -> Maybe.Just(this.liftF.apply(f))
            : (f) -> Maybe.Nothing(),
            (this.liftM != null) ? (m) -> Maybe.Just(this.liftM.apply(m))
            : (m) -> Maybe.Nothing(),
            (this.done != null) ? (done) -> Maybe.Just(this.done.apply(done))
            : (done) -> Maybe.Nothing(),
            (this.bind != null) ? (bound) -> Maybe.Just(this.bind.apply(bound))
            : (bound) -> Maybe.Nothing(),
            (this.suspend != null) ? (suspend) -> Maybe.Just(this.suspend.apply(suspend))
            : (suspend) -> Maybe.Nothing());
        return this._freeT.match(cases);
      }

      public final <A_> Either<A_, R> otherwiseLeft(Supplier<A_> left) {
        FreeT.Cases<Either<A_, R>, F, M, A> cases = FreeTImpl.cases((this.liftF != null) ? (f) -> Either.Right(this.liftF.apply(f))
            : (f) -> Either.Left(left.get()),
            (this.liftM != null) ? (m) -> Either.Right(this.liftM.apply(m))
            : (m) -> Either.Left(left.get()),
            (this.done != null) ? (done) -> Either.Right(this.done.apply(done))
            : (done) -> Either.Left(left.get()),
            (this.bind != null) ? (bound) -> Either.Right(this.bind.apply(bound))
            : (bound) -> Either.Left(left.get()),
            (this.suspend != null) ? (suspend) -> Either.Right(this.suspend.apply(suspend))
            : (suspend) -> Either.Left(left.get()));
        return this._freeT.match(cases);
      }

      public final <A_> Either<A_, R> otherwiseLeft_(A_ left) {
        return this.otherwiseLeft(() -> left);
      }
    }
  }
}
