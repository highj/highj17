package org.highj.data.transformer;

import org.highj.hkt.TypeEq;
import org.highj.hkt.__2;
import org.highj.data.Either;
import org.highj.data.Maybe;
import org.highj.function.F1;

import java.util.function.Supplier;

final class FreeArrowImpl {
  @SuppressWarnings("rawtypes")
  private static FreeArrow Id;

  private static final FreeArrow.Cases<Maybe<FreeArrow.Compose<Object, Object, Object, ?, Object>>, Object, Object, Object, Object> composeGetter = FreeArrowImpl.cases((idTypeEq) -> Maybe.Nothing(),
  (compose) -> Maybe.Just(compose),
  (arrF) -> Maybe.Nothing(),
  (first) -> Maybe.Nothing(),
  (liftFf) -> Maybe.Nothing(),
  (liftAArr) -> Maybe.Nothing());

  private static final FreeArrow.Cases<Maybe<F1<Object, Object>>, Object, Object, Object, Object> arrFGetter = FreeArrowImpl.cases((idTypeEq) -> Maybe.Nothing(),
  (compose) -> Maybe.Nothing(),
  (arrF) -> Maybe.Just(arrF),
  (first) -> Maybe.Nothing(),
  (liftFf) -> Maybe.Nothing(),
  (liftAArr) -> Maybe.Nothing());

  private static final FreeArrow.Cases<Maybe<FreeArrow.First<Object, Object, ?, ?, ?, Object, Object>>, Object, Object, Object, Object> firstGetter = FreeArrowImpl.cases((idTypeEq) -> Maybe.Nothing(),
  (compose) -> Maybe.Nothing(),
  (arrF) -> Maybe.Nothing(),
  (first) -> Maybe.Just(first),
  (liftFf) -> Maybe.Nothing(),
  (liftAArr) -> Maybe.Nothing());

  private static final FreeArrow.Cases<Maybe<__2<Object, Object, Object>>, Object, Object, Object, Object> liftFfGetter = FreeArrowImpl.cases((idTypeEq) -> Maybe.Nothing(),
  (compose) -> Maybe.Nothing(),
  (arrF) -> Maybe.Nothing(),
  (first) -> Maybe.Nothing(),
  (liftFf) -> Maybe.Just(liftFf),
  (liftAArr) -> Maybe.Nothing());

  private static final FreeArrow.Cases<Maybe<__2<Object, Object, Object>>, Object, Object, Object, Object> liftAArrGetter = FreeArrowImpl.cases((idTypeEq) -> Maybe.Nothing(),
  (compose) -> Maybe.Nothing(),
  (arrF) -> Maybe.Nothing(),
  (first) -> Maybe.Nothing(),
  (liftFf) -> Maybe.Nothing(),
  (liftAArr) -> Maybe.Just(liftAArr));

  private FreeArrowImpl() {
  }

  public static <F, ARR, B, C, R> FreeArrow.Cases<R, F, ARR, B, C> cases(F1<TypeEq<B, C>, R> Id,
      F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose, F1<F1<B, C>, R> Arr,
      F1<FreeArrow.First<F, ARR, ?, ?, ?, B, C>, R> First, F1<__2<F, B, C>, R> LiftF,
      F1<__2<ARR, B, C>, R> LiftA) {
    return new LambdaCases<>(Id, Compose, Arr, First, LiftF, LiftA);
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  public static <F, ARR, B> FreeArrow<F, ARR, B, B> Id() {
    FreeArrow<F, ARR, B, B> _Id = Id;
    if (_Id == null) {
      Id = _Id = new Id();
    }
    return _Id;
  }

  @SuppressWarnings("unchecked")
  public static <F, ARR, B, C> FreeArrow<F, ARR, B, C> Id(TypeEq<B, C> idTypeEq) {
    return (FreeArrow<F, ARR, B, C>) Id();
  }

  public static <F, ARR, B, C> FreeArrow<F, ARR, B, C> Compose(FreeArrow.Compose<F, ARR, B, ?, C> compose) {
    return new Compose_<>(compose);
  }

  public static <F, ARR, B, C> FreeArrow<F, ARR, B, C> Arr(F1<B, C> arrF) {
    return new Arr<>(arrF);
  }

  public static <F, ARR, B, C> FreeArrow<F, ARR, B, C> First(FreeArrow.First<F, ARR, ?, ?, ?, B, C> first) {
    return new First_<>(first);
  }

  public static <F, ARR, B, C> FreeArrow<F, ARR, B, C> LiftF(__2<F, B, C> liftFf) {
    return new LiftF<>(liftFf);
  }

  public static <F, ARR, B, C> FreeArrow<F, ARR, B, C> LiftA(__2<ARR, B, C> liftAArr) {
    return new LiftA<>(liftAArr);
  }

  public static <F, ARR, B, C> FreeArrow<F, ARR, B, C> lazy(Supplier<FreeArrow<F, ARR, B, C>> freeArrow) {
    return new Lazy<>(freeArrow);
  }

  @SuppressWarnings("unchecked")
  public static <F, ARR, B, C> CasesMatchers.TotalMatcher_Id<F, ARR, B, C> cases() {
    return (CasesMatchers.TotalMatcher_Id<F, ARR, B, C>) CasesMatchers.totalMatcher_Id;
  }

  public static <F, ARR, B, C> CaseOfMatchers.TotalMatcher_Id<F, ARR, B, C> caseOf(FreeArrow<F, ARR, B, C> freeArrow) {
    return new CaseOfMatchers.TotalMatcher_Id<F, ARR, B, C>(freeArrow);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public static <F, ARR, B, C> Maybe<FreeArrow.Compose<F, ARR, B, ?, C>> getCompose(FreeArrow<F, ARR, B, C> freeArrow) {
    return (Maybe<FreeArrow.Compose<F, ARR, B, ?, C>>) freeArrow.match((FreeArrow.Cases) composeGetter);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public static <F, ARR, B, C> Maybe<F1<B, C>> getArrF(FreeArrow<F, ARR, B, C> freeArrow) {
    return (Maybe<F1<B, C>>) freeArrow.match((FreeArrow.Cases) arrFGetter);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public static <F, ARR, B, C> Maybe<FreeArrow.First<F, ARR, ?, ?, ?, B, C>> getFirst(FreeArrow<F, ARR, B, C> freeArrow) {
    return (Maybe<FreeArrow.First<F, ARR, ?, ?, ?, B, C>>) freeArrow.match((FreeArrow.Cases) firstGetter);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public static <F, ARR, B, C> Maybe<__2<F, B, C>> getLiftFf(FreeArrow<F, ARR, B, C> freeArrow) {
    return (Maybe<__2<F, B, C>>) freeArrow.match((FreeArrow.Cases) liftFfGetter);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public static <F, ARR, B, C> Maybe<__2<ARR, B, C>> getLiftAArr(FreeArrow<F, ARR, B, C> freeArrow) {
    return (Maybe<__2<ARR, B, C>>) freeArrow.match((FreeArrow.Cases) liftAArrGetter);
  }

  public static <F, ARR, B, C> F1<FreeArrow<F, ARR, B, C>, FreeArrow<F, ARR, B, C>> setCompose(FreeArrow.Compose<F, ARR, B, ?, C> newCompose) {
    return modCompose(__ -> newCompose);
  }

  @SuppressWarnings("unchecked")
  public static <F, ARR, B, C> F1<FreeArrow<F, ARR, B, C>, FreeArrow<F, ARR, B, C>> modCompose(F1<FreeArrow.Compose<F, ARR, B, ?, C>, FreeArrow.Compose<F, ARR, B, ?, C>> composeMod) {
    FreeArrow.Cases<FreeArrow<F, ARR, B, C>, F, ARR, B, C> cases = cases(FreeArrowImpl::Id,
        (compose) -> Compose(composeMod.apply(compose)),
        FreeArrowImpl::Arr,
        FreeArrowImpl::First,
        FreeArrowImpl::LiftF,
        FreeArrowImpl::LiftA);
    return freeArrow -> freeArrow.match(cases);
  }

  public static <F, ARR, B, C> F1<FreeArrow<F, ARR, B, C>, FreeArrow<F, ARR, B, C>> setArrF(F1<B, C> newArrF) {
    return modArrF(__ -> newArrF);
  }

  @SuppressWarnings("unchecked")
  public static <F, ARR, B, C> F1<FreeArrow<F, ARR, B, C>, FreeArrow<F, ARR, B, C>> modArrF(F1<F1<B, C>, F1<B, C>> arrFMod) {
    FreeArrow.Cases<FreeArrow<F, ARR, B, C>, F, ARR, B, C> cases = cases(FreeArrowImpl::Id,
        FreeArrowImpl::Compose,
        (arrF) -> Arr(arrFMod.apply(arrF)),
        FreeArrowImpl::First,
        FreeArrowImpl::LiftF,
        FreeArrowImpl::LiftA);
    return freeArrow -> freeArrow.match(cases);
  }

  public static <F, ARR, B, C> F1<FreeArrow<F, ARR, B, C>, FreeArrow<F, ARR, B, C>> setFirst(FreeArrow.First<F, ARR, ?, ?, ?, B, C> newFirst) {
    return modFirst(__ -> newFirst);
  }

  @SuppressWarnings("unchecked")
  public static <F, ARR, B, C> F1<FreeArrow<F, ARR, B, C>, FreeArrow<F, ARR, B, C>> modFirst(F1<FreeArrow.First<F, ARR, ?, ?, ?, B, C>, FreeArrow.First<F, ARR, ?, ?, ?, B, C>> firstMod) {
    FreeArrow.Cases<FreeArrow<F, ARR, B, C>, F, ARR, B, C> cases = cases(FreeArrowImpl::Id,
        FreeArrowImpl::Compose,
        FreeArrowImpl::Arr,
        (first) -> First(firstMod.apply(first)),
        FreeArrowImpl::LiftF,
        FreeArrowImpl::LiftA);
    return freeArrow -> freeArrow.match(cases);
  }

  public static <F, ARR, B, C> F1<FreeArrow<F, ARR, B, C>, FreeArrow<F, ARR, B, C>> setLiftFf(__2<F, B, C> newLiftFf) {
    return modLiftFf(__ -> newLiftFf);
  }

  @SuppressWarnings("unchecked")
  public static <F, ARR, B, C> F1<FreeArrow<F, ARR, B, C>, FreeArrow<F, ARR, B, C>> modLiftFf(F1<__2<F, B, C>, __2<F, B, C>> liftFfMod) {
    FreeArrow.Cases<FreeArrow<F, ARR, B, C>, F, ARR, B, C> cases = cases(FreeArrowImpl::Id,
        FreeArrowImpl::Compose,
        FreeArrowImpl::Arr,
        FreeArrowImpl::First,
        (liftFf) -> LiftF(liftFfMod.apply(liftFf)),
        FreeArrowImpl::LiftA);
    return freeArrow -> freeArrow.match(cases);
  }

  public static <F, ARR, B, C> F1<FreeArrow<F, ARR, B, C>, FreeArrow<F, ARR, B, C>> setLiftAArr(__2<ARR, B, C> newLiftAArr) {
    return modLiftAArr(__ -> newLiftAArr);
  }

  @SuppressWarnings("unchecked")
  public static <F, ARR, B, C> F1<FreeArrow<F, ARR, B, C>, FreeArrow<F, ARR, B, C>> modLiftAArr(F1<__2<ARR, B, C>, __2<ARR, B, C>> liftAArrMod) {
    FreeArrow.Cases<FreeArrow<F, ARR, B, C>, F, ARR, B, C> cases = cases(FreeArrowImpl::Id,
        FreeArrowImpl::Compose,
        FreeArrowImpl::Arr,
        FreeArrowImpl::First,
        FreeArrowImpl::LiftF,
        (liftAArr) -> LiftA(liftAArrMod.apply(liftAArr)));
    return freeArrow -> freeArrow.match(cases);
  }

  private static final class LambdaCases<F, ARR, B, C, R> implements FreeArrow.Cases<R, F, ARR, B, C> {
    private final F1<TypeEq<B, C>, R> Id;

    private final F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose;

    private final F1<F1<B, C>, R> Arr;

    private final F1<FreeArrow.First<F, ARR, ?, ?, ?, B, C>, R> First;

    private final F1<__2<F, B, C>, R> LiftF;

    private final F1<__2<ARR, B, C>, R> LiftA;

    LambdaCases(F1<TypeEq<B, C>, R> Id, F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose,
        F1<F1<B, C>, R> Arr, F1<FreeArrow.First<F, ARR, ?, ?, ?, B, C>, R> First,
        F1<__2<F, B, C>, R> LiftF, F1<__2<ARR, B, C>, R> LiftA) {
      this.Id = Id;
      this.Compose = Compose;
      this.Arr = Arr;
      this.First = First;
      this.LiftF = LiftF;
      this.LiftA = LiftA;
    }

    @Override
    public R Id(TypeEq<B, C> idTypeEq) {
      return this.Id.apply(idTypeEq);
    }

    @Override
    public R Compose(FreeArrow.Compose<F, ARR, B, ?, C> compose) {
      return this.Compose.apply(compose);
    }

    @Override
    public R Arr(F1<B, C> arrF) {
      return this.Arr.apply(arrF);
    }

    @Override
    public R First(FreeArrow.First<F, ARR, ?, ?, ?, B, C> first) {
      return this.First.apply(first);
    }

    @Override
    public R LiftF(__2<F, B, C> liftFf) {
      return this.LiftF.apply(liftFf);
    }

    @Override
    public R LiftA(__2<ARR, B, C> liftAArr) {
      return this.LiftA.apply(liftAArr);
    }
  }

  private static final class Id<F, ARR, B> extends FreeArrow<F, ARR, B, B> {
    Id() {
    }

    @Override
    public <R> R match(Cases<R, F, ARR, B, B> cases) {
      return cases.Id(TypeEq.refl());
    }
  }

  private static final class Compose_<F, ARR, B, C> extends FreeArrow<F, ARR, B, C> {
    private final Compose<F, ARR, B, ?, C> compose;

    Compose_(Compose<F, ARR, B, ?, C> compose) {
      this.compose = compose;
    }

    @Override
    public <R> R match(Cases<R, F, ARR, B, C> cases) {
      return cases.Compose(this.compose);
    }
  }

  private static final class Arr<F, ARR, B, C> extends FreeArrow<F, ARR, B, C> {
    private final F1<B, C> arrF;

    Arr(F1<B, C> arrF) {
      this.arrF = arrF;
    }

    @Override
    public <R> R match(Cases<R, F, ARR, B, C> cases) {
      return cases.Arr(this.arrF);
    }
  }

  private static final class First_<F, ARR, B, C> extends FreeArrow<F, ARR, B, C> {
    private final First<F, ARR, ?, ?, ?, B, C> first;

    First_(First<F, ARR, ?, ?, ?, B, C> first) {
      this.first = first;
    }

    @Override
    public <R> R match(Cases<R, F, ARR, B, C> cases) {
      return cases.First(this.first);
    }
  }

  private static final class LiftF<F, ARR, B, C> extends FreeArrow<F, ARR, B, C> {
    private final __2<F, B, C> liftFf;

    LiftF(__2<F, B, C> liftFf) {
      this.liftFf = liftFf;
    }

    @Override
    public <R> R match(Cases<R, F, ARR, B, C> cases) {
      return cases.LiftF(this.liftFf);
    }
  }

  private static final class LiftA<F, ARR, B, C> extends FreeArrow<F, ARR, B, C> {
    private final __2<ARR, B, C> liftAArr;

    LiftA(__2<ARR, B, C> liftAArr) {
      this.liftAArr = liftAArr;
    }

    @Override
    public <R> R match(Cases<R, F, ARR, B, C> cases) {
      return cases.LiftA(this.liftAArr);
    }
  }

  private static final class Lazy<F, ARR, B, C> extends FreeArrow<F, ARR, B, C> {
    private volatile Supplier<FreeArrow<F, ARR, B, C>> expression;

    private FreeArrow<F, ARR, B, C> evaluation;

    Lazy(Supplier<FreeArrow<F, ARR, B, C>> freeArrow) {
      this.expression = freeArrow;
    }

    private synchronized FreeArrow<F, ARR, B, C> _evaluate() {
      Supplier<FreeArrow<F, ARR, B, C>> e = expression;
      if (e != null) {
        evaluation = e.get();
        expression = null;
      }
      return evaluation;
    }

    @Override
    public <R> R match(Cases<R, F, ARR, B, C> cases) {
      return (this.expression == null ? this.evaluation : _evaluate()).match(cases);
    }
  }

  public static class CasesMatchers {
    private static final TotalMatcher_Id<?, ?, ?, ?> totalMatcher_Id = new TotalMatcher_Id<>();

    private CasesMatchers() {
    }

    public static final class TotalMatcher_Id<F, ARR, B, C> {
      TotalMatcher_Id() {
      }

      public final <R> TotalMatcher_Compose<F, ARR, B, C, R> Id(F1<TypeEq<B, C>, R> Id) {
        return new TotalMatcher_Compose<>(Id);
      }

      public final <R> TotalMatcher_Compose<F, ARR, B, C, R> Id_(R r) {
        return this.Id((idTypeEq) -> r);
      }

      public final <R> PartialMatcher_Arr<F, ARR, B, C, R> Compose(F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose) {
        return new PartialMatcher_Arr<>(null, Compose);
      }

      public final <R> PartialMatcher_Arr<F, ARR, B, C, R> Compose_(R r) {
        return this.Compose((compose) -> r);
      }

      public final <R> PartialMatcher_First<F, ARR, B, C, R> Arr(F1<F1<B, C>, R> Arr) {
        return new PartialMatcher_First<>(null, null, Arr);
      }

      public final <R> PartialMatcher_First<F, ARR, B, C, R> Arr_(R r) {
        return this.Arr((arrF) -> r);
      }

      public final <R> PartialMatcher_LiftF<F, ARR, B, C, R> First(F1<FreeArrow.First<F, ARR, ?, ?, ?, B, C>, R> First) {
        return new PartialMatcher_LiftF<>(null, null, null, First);
      }

      public final <R> PartialMatcher_LiftF<F, ARR, B, C, R> First_(R r) {
        return this.First((first) -> r);
      }

      public final <R> PartialMatcher_LiftA<F, ARR, B, C, R> LiftF(F1<__2<F, B, C>, R> LiftF) {
        return new PartialMatcher_LiftA<>(null, null, null, null, LiftF);
      }

      public final <R> PartialMatcher_LiftA<F, ARR, B, C, R> LiftF_(R r) {
        return this.LiftF((liftFf) -> r);
      }

      public final <R> PartialMatcher<F, ARR, B, C, R> LiftA(F1<__2<ARR, B, C>, R> LiftA) {
        return new PartialMatcher<>(null, null, null, null, null, LiftA);
      }

      public final <R> PartialMatcher<F, ARR, B, C, R> LiftA_(R r) {
        return this.LiftA((liftAArr) -> r);
      }
    }

    public static final class TotalMatcher_Compose<F, ARR, B, C, R> extends PartialMatcher_Arr<F, ARR, B, C, R> {
      TotalMatcher_Compose(F1<TypeEq<B, C>, R> Id) {
        super(Id, null);
      }

      public final TotalMatcher_Arr<F, ARR, B, C, R> Compose(F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose) {
        return new TotalMatcher_Arr<>(((PartialMatcher<F, ARR, B, C, R>) this).Id, Compose);
      }

      public final TotalMatcher_Arr<F, ARR, B, C, R> Compose_(R r) {
        return this.Compose((compose) -> r);
      }
    }

    public static final class TotalMatcher_Arr<F, ARR, B, C, R> extends PartialMatcher_First<F, ARR, B, C, R> {
      TotalMatcher_Arr(F1<TypeEq<B, C>, R> Id, F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose) {
        super(Id, Compose, null);
      }

      public final TotalMatcher_First<F, ARR, B, C, R> Arr(F1<F1<B, C>, R> Arr) {
        return new TotalMatcher_First<>(((PartialMatcher<F, ARR, B, C, R>) this).Id, ((PartialMatcher<F, ARR, B, C, R>) this).Compose, Arr);
      }

      public final TotalMatcher_First<F, ARR, B, C, R> Arr_(R r) {
        return this.Arr((arrF) -> r);
      }
    }

    public static final class TotalMatcher_First<F, ARR, B, C, R> extends PartialMatcher_LiftF<F, ARR, B, C, R> {
      TotalMatcher_First(F1<TypeEq<B, C>, R> Id, F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose,
          F1<F1<B, C>, R> Arr) {
        super(Id, Compose, Arr, null);
      }

      public final TotalMatcher_LiftF<F, ARR, B, C, R> First(F1<FreeArrow.First<F, ARR, ?, ?, ?, B, C>, R> First) {
        return new TotalMatcher_LiftF<>(((PartialMatcher<F, ARR, B, C, R>) this).Id, ((PartialMatcher<F, ARR, B, C, R>) this).Compose, ((PartialMatcher<F, ARR, B, C, R>) this).Arr, First);
      }

      public final TotalMatcher_LiftF<F, ARR, B, C, R> First_(R r) {
        return this.First((first) -> r);
      }
    }

    public static final class TotalMatcher_LiftF<F, ARR, B, C, R> extends PartialMatcher_LiftA<F, ARR, B, C, R> {
      TotalMatcher_LiftF(F1<TypeEq<B, C>, R> Id, F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose,
          F1<F1<B, C>, R> Arr, F1<FreeArrow.First<F, ARR, ?, ?, ?, B, C>, R> First) {
        super(Id, Compose, Arr, First, null);
      }

      public final TotalMatcher_LiftA<F, ARR, B, C, R> LiftF(F1<__2<F, B, C>, R> LiftF) {
        return new TotalMatcher_LiftA<>(((PartialMatcher<F, ARR, B, C, R>) this).Id, ((PartialMatcher<F, ARR, B, C, R>) this).Compose, ((PartialMatcher<F, ARR, B, C, R>) this).Arr, ((PartialMatcher<F, ARR, B, C, R>) this).First, LiftF);
      }

      public final TotalMatcher_LiftA<F, ARR, B, C, R> LiftF_(R r) {
        return this.LiftF((liftFf) -> r);
      }
    }

    public static final class TotalMatcher_LiftA<F, ARR, B, C, R> extends PartialMatcher<F, ARR, B, C, R> {
      TotalMatcher_LiftA(F1<TypeEq<B, C>, R> Id, F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose,
          F1<F1<B, C>, R> Arr, F1<FreeArrow.First<F, ARR, ?, ?, ?, B, C>, R> First,
          F1<__2<F, B, C>, R> LiftF) {
        super(Id, Compose, Arr, First, LiftF, null);
      }

      public final F1<FreeArrow<F, ARR, B, C>, R> LiftA(F1<__2<ARR, B, C>, R> LiftA) {
        FreeArrow.Cases<R, F, ARR, B, C> cases = FreeArrowImpl.cases(((PartialMatcher<F, ARR, B, C, R>) this).Id, ((PartialMatcher<F, ARR, B, C, R>) this).Compose, ((PartialMatcher<F, ARR, B, C, R>) this).Arr, ((PartialMatcher<F, ARR, B, C, R>) this).First, ((PartialMatcher<F, ARR, B, C, R>) this).LiftF, LiftA);
        return freeArrow -> freeArrow.match(cases);
      }

      public final F1<FreeArrow<F, ARR, B, C>, R> LiftA_(R r) {
        return this.LiftA((liftAArr) -> r);
      }
    }

    public static class PartialMatcher_Arr<F, ARR, B, C, R> extends PartialMatcher_First<F, ARR, B, C, R> {
      PartialMatcher_Arr(F1<TypeEq<B, C>, R> Id,
          F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose) {
        super(Id, Compose, null);
      }

      public final PartialMatcher_First<F, ARR, B, C, R> Arr(F1<F1<B, C>, R> Arr) {
        return new PartialMatcher_First<>(((PartialMatcher<F, ARR, B, C, R>) this).Id, ((PartialMatcher<F, ARR, B, C, R>) this).Compose, Arr);
      }

      public final PartialMatcher_First<F, ARR, B, C, R> Arr_(R r) {
        return this.Arr((arrF) -> r);
      }
    }

    public static class PartialMatcher_First<F, ARR, B, C, R> extends PartialMatcher_LiftF<F, ARR, B, C, R> {
      PartialMatcher_First(F1<TypeEq<B, C>, R> Id,
          F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose, F1<F1<B, C>, R> Arr) {
        super(Id, Compose, Arr, null);
      }

      public final PartialMatcher_LiftF<F, ARR, B, C, R> First(F1<FreeArrow.First<F, ARR, ?, ?, ?, B, C>, R> First) {
        return new PartialMatcher_LiftF<>(((PartialMatcher<F, ARR, B, C, R>) this).Id, ((PartialMatcher<F, ARR, B, C, R>) this).Compose, ((PartialMatcher<F, ARR, B, C, R>) this).Arr, First);
      }

      public final PartialMatcher_LiftF<F, ARR, B, C, R> First_(R r) {
        return this.First((first) -> r);
      }
    }

    public static class PartialMatcher_LiftF<F, ARR, B, C, R> extends PartialMatcher_LiftA<F, ARR, B, C, R> {
      PartialMatcher_LiftF(F1<TypeEq<B, C>, R> Id,
          F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose, F1<F1<B, C>, R> Arr,
          F1<FreeArrow.First<F, ARR, ?, ?, ?, B, C>, R> First) {
        super(Id, Compose, Arr, First, null);
      }

      public final PartialMatcher_LiftA<F, ARR, B, C, R> LiftF(F1<__2<F, B, C>, R> LiftF) {
        return new PartialMatcher_LiftA<>(((PartialMatcher<F, ARR, B, C, R>) this).Id, ((PartialMatcher<F, ARR, B, C, R>) this).Compose, ((PartialMatcher<F, ARR, B, C, R>) this).Arr, ((PartialMatcher<F, ARR, B, C, R>) this).First, LiftF);
      }

      public final PartialMatcher_LiftA<F, ARR, B, C, R> LiftF_(R r) {
        return this.LiftF((liftFf) -> r);
      }
    }

    public static class PartialMatcher_LiftA<F, ARR, B, C, R> extends PartialMatcher<F, ARR, B, C, R> {
      PartialMatcher_LiftA(F1<TypeEq<B, C>, R> Id,
          F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose, F1<F1<B, C>, R> Arr,
          F1<FreeArrow.First<F, ARR, ?, ?, ?, B, C>, R> First, F1<__2<F, B, C>, R> LiftF) {
        super(Id, Compose, Arr, First, LiftF, null);
      }

      public final PartialMatcher<F, ARR, B, C, R> LiftA(F1<__2<ARR, B, C>, R> LiftA) {
        return new PartialMatcher<>(((PartialMatcher<F, ARR, B, C, R>) this).Id, ((PartialMatcher<F, ARR, B, C, R>) this).Compose, ((PartialMatcher<F, ARR, B, C, R>) this).Arr, ((PartialMatcher<F, ARR, B, C, R>) this).First, ((PartialMatcher<F, ARR, B, C, R>) this).LiftF, LiftA);
      }

      public final PartialMatcher<F, ARR, B, C, R> LiftA_(R r) {
        return this.LiftA((liftAArr) -> r);
      }
    }

    public static class PartialMatcher<F, ARR, B, C, R> {
      private final F1<TypeEq<B, C>, R> Id;

      private final F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose;

      private final F1<F1<B, C>, R> Arr;

      private final F1<FreeArrow.First<F, ARR, ?, ?, ?, B, C>, R> First;

      private final F1<__2<F, B, C>, R> LiftF;

      private final F1<__2<ARR, B, C>, R> LiftA;

      PartialMatcher(F1<TypeEq<B, C>, R> Id, F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose,
          F1<F1<B, C>, R> Arr, F1<FreeArrow.First<F, ARR, ?, ?, ?, B, C>, R> First,
          F1<__2<F, B, C>, R> LiftF, F1<__2<ARR, B, C>, R> LiftA) {
        this.Id = Id;
        this.Compose = Compose;
        this.Arr = Arr;
        this.First = First;
        this.LiftF = LiftF;
        this.LiftA = LiftA;
      }

      public final F1<FreeArrow<F, ARR, B, C>, R> otherwise(Supplier<R> otherwise) {
        FreeArrow.Cases<R, F, ARR, B, C> cases = FreeArrowImpl.<F, ARR, B, C, R>cases(this.Id != null ? this.Id : (idTypeEq) -> otherwise.get(),
            this.Compose != null ? this.Compose : (compose) -> otherwise.get(),
            this.Arr != null ? this.Arr : (arrF) -> otherwise.get(),
            this.First != null ? this.First : (first) -> otherwise.get(),
            this.LiftF != null ? this.LiftF : (liftFf) -> otherwise.get(),
            this.LiftA != null ? this.LiftA : (liftAArr) -> otherwise.get());
        return freeArrow -> freeArrow.match(cases);
      }

      public final F1<FreeArrow<F, ARR, B, C>, R> otherwise_(R r) {
        return this.otherwise(() -> r);
      }

      public final F1<FreeArrow<F, ARR, B, C>, Maybe<R>> otherwiseNothing() {
        FreeArrow.Cases<Maybe<R>, F, ARR, B, C> cases = FreeArrowImpl.cases((this.Id != null) ? (idTypeEq) -> Maybe.Just(this.Id.apply(idTypeEq))
            : (idTypeEq) -> Maybe.Nothing(),
            (this.Compose != null) ? (compose) -> Maybe.Just(this.Compose.apply(compose))
            : (compose) -> Maybe.Nothing(),
            (this.Arr != null) ? (arrF) -> Maybe.Just(this.Arr.apply(arrF))
            : (arrF) -> Maybe.Nothing(),
            (this.First != null) ? (first) -> Maybe.Just(this.First.apply(first))
            : (first) -> Maybe.Nothing(),
            (this.LiftF != null) ? (liftFf) -> Maybe.Just(this.LiftF.apply(liftFf))
            : (liftFf) -> Maybe.Nothing(),
            (this.LiftA != null) ? (liftAArr) -> Maybe.Just(this.LiftA.apply(liftAArr))
            : (liftAArr) -> Maybe.Nothing());
        return freeArrow -> freeArrow.match(cases);
      }

      public final <A> F1<FreeArrow<F, ARR, B, C>, Either<A, R>> otherwiseLeft(Supplier<A> left) {
        FreeArrow.Cases<Either<A, R>, F, ARR, B, C> cases = FreeArrowImpl.cases((this.Id != null) ? (idTypeEq) -> Either.Right(this.Id.apply(idTypeEq))
            : (idTypeEq) -> Either.Left(left.get()),
            (this.Compose != null) ? (compose) -> Either.Right(this.Compose.apply(compose))
            : (compose) -> Either.Left(left.get()),
            (this.Arr != null) ? (arrF) -> Either.Right(this.Arr.apply(arrF))
            : (arrF) -> Either.Left(left.get()),
            (this.First != null) ? (first) -> Either.Right(this.First.apply(first))
            : (first) -> Either.Left(left.get()),
            (this.LiftF != null) ? (liftFf) -> Either.Right(this.LiftF.apply(liftFf))
            : (liftFf) -> Either.Left(left.get()),
            (this.LiftA != null) ? (liftAArr) -> Either.Right(this.LiftA.apply(liftAArr))
            : (liftAArr) -> Either.Left(left.get()));
        return freeArrow -> freeArrow.match(cases);
      }

      public final <A> F1<FreeArrow<F, ARR, B, C>, Either<A, R>> otherwiseLeft_(A left) {
        return this.otherwiseLeft(() -> left);
      }
    }
  }

  public static class CaseOfMatchers {
    private CaseOfMatchers() {
    }

    public static final class TotalMatcher_Id<F, ARR, B, C> {
      private final FreeArrow<F, ARR, B, C> _freeArrow;

      TotalMatcher_Id(FreeArrow<F, ARR, B, C> _freeArrow) {
        this._freeArrow = _freeArrow;
      }

      public final <R> TotalMatcher_Compose<F, ARR, B, C, R> Id(F1<TypeEq<B, C>, R> Id) {
        return new TotalMatcher_Compose<>(this._freeArrow, Id);
      }

      public final <R> TotalMatcher_Compose<F, ARR, B, C, R> Id_(R r) {
        return this.Id((idTypeEq) -> r);
      }

      public final <R> PartialMatcher_Arr<F, ARR, B, C, R> Compose(F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose) {
        return new PartialMatcher_Arr<>(this._freeArrow, null, Compose);
      }

      public final <R> PartialMatcher_Arr<F, ARR, B, C, R> Compose_(R r) {
        return this.Compose((compose) -> r);
      }

      public final <R> PartialMatcher_First<F, ARR, B, C, R> Arr(F1<F1<B, C>, R> Arr) {
        return new PartialMatcher_First<>(this._freeArrow, null, null, Arr);
      }

      public final <R> PartialMatcher_First<F, ARR, B, C, R> Arr_(R r) {
        return this.Arr((arrF) -> r);
      }

      public final <R> PartialMatcher_LiftF<F, ARR, B, C, R> First(F1<FreeArrow.First<F, ARR, ?, ?, ?, B, C>, R> First) {
        return new PartialMatcher_LiftF<>(this._freeArrow, null, null, null, First);
      }

      public final <R> PartialMatcher_LiftF<F, ARR, B, C, R> First_(R r) {
        return this.First((first) -> r);
      }

      public final <R> PartialMatcher_LiftA<F, ARR, B, C, R> LiftF(F1<__2<F, B, C>, R> LiftF) {
        return new PartialMatcher_LiftA<>(this._freeArrow, null, null, null, null, LiftF);
      }

      public final <R> PartialMatcher_LiftA<F, ARR, B, C, R> LiftF_(R r) {
        return this.LiftF((liftFf) -> r);
      }

      public final <R> PartialMatcher<F, ARR, B, C, R> LiftA(F1<__2<ARR, B, C>, R> LiftA) {
        return new PartialMatcher<>(this._freeArrow, null, null, null, null, null, LiftA);
      }

      public final <R> PartialMatcher<F, ARR, B, C, R> LiftA_(R r) {
        return this.LiftA((liftAArr) -> r);
      }
    }

    public static final class TotalMatcher_Compose<F, ARR, B, C, R> extends PartialMatcher_Arr<F, ARR, B, C, R> {
      TotalMatcher_Compose(FreeArrow<F, ARR, B, C> _freeArrow, F1<TypeEq<B, C>, R> Id) {
        super(_freeArrow, Id, null);
      }

      public final TotalMatcher_Arr<F, ARR, B, C, R> Compose(F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose) {
        return new TotalMatcher_Arr<>(((PartialMatcher<F, ARR, B, C, R>) this)._freeArrow, ((PartialMatcher<F, ARR, B, C, R>) this).Id, Compose);
      }

      public final TotalMatcher_Arr<F, ARR, B, C, R> Compose_(R r) {
        return this.Compose((compose) -> r);
      }
    }

    public static final class TotalMatcher_Arr<F, ARR, B, C, R> extends PartialMatcher_First<F, ARR, B, C, R> {
      TotalMatcher_Arr(FreeArrow<F, ARR, B, C> _freeArrow, F1<TypeEq<B, C>, R> Id,
          F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose) {
        super(_freeArrow, Id, Compose, null);
      }

      public final TotalMatcher_First<F, ARR, B, C, R> Arr(F1<F1<B, C>, R> Arr) {
        return new TotalMatcher_First<>(((PartialMatcher<F, ARR, B, C, R>) this)._freeArrow, ((PartialMatcher<F, ARR, B, C, R>) this).Id, ((PartialMatcher<F, ARR, B, C, R>) this).Compose, Arr);
      }

      public final TotalMatcher_First<F, ARR, B, C, R> Arr_(R r) {
        return this.Arr((arrF) -> r);
      }
    }

    public static final class TotalMatcher_First<F, ARR, B, C, R> extends PartialMatcher_LiftF<F, ARR, B, C, R> {
      TotalMatcher_First(FreeArrow<F, ARR, B, C> _freeArrow, F1<TypeEq<B, C>, R> Id,
          F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose, F1<F1<B, C>, R> Arr) {
        super(_freeArrow, Id, Compose, Arr, null);
      }

      public final TotalMatcher_LiftF<F, ARR, B, C, R> First(F1<FreeArrow.First<F, ARR, ?, ?, ?, B, C>, R> First) {
        return new TotalMatcher_LiftF<>(((PartialMatcher<F, ARR, B, C, R>) this)._freeArrow, ((PartialMatcher<F, ARR, B, C, R>) this).Id, ((PartialMatcher<F, ARR, B, C, R>) this).Compose, ((PartialMatcher<F, ARR, B, C, R>) this).Arr, First);
      }

      public final TotalMatcher_LiftF<F, ARR, B, C, R> First_(R r) {
        return this.First((first) -> r);
      }
    }

    public static final class TotalMatcher_LiftF<F, ARR, B, C, R> extends PartialMatcher_LiftA<F, ARR, B, C, R> {
      TotalMatcher_LiftF(FreeArrow<F, ARR, B, C> _freeArrow, F1<TypeEq<B, C>, R> Id,
          F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose, F1<F1<B, C>, R> Arr,
          F1<FreeArrow.First<F, ARR, ?, ?, ?, B, C>, R> First) {
        super(_freeArrow, Id, Compose, Arr, First, null);
      }

      public final TotalMatcher_LiftA<F, ARR, B, C, R> LiftF(F1<__2<F, B, C>, R> LiftF) {
        return new TotalMatcher_LiftA<>(((PartialMatcher<F, ARR, B, C, R>) this)._freeArrow, ((PartialMatcher<F, ARR, B, C, R>) this).Id, ((PartialMatcher<F, ARR, B, C, R>) this).Compose, ((PartialMatcher<F, ARR, B, C, R>) this).Arr, ((PartialMatcher<F, ARR, B, C, R>) this).First, LiftF);
      }

      public final TotalMatcher_LiftA<F, ARR, B, C, R> LiftF_(R r) {
        return this.LiftF((liftFf) -> r);
      }
    }

    public static final class TotalMatcher_LiftA<F, ARR, B, C, R> extends PartialMatcher<F, ARR, B, C, R> {
      TotalMatcher_LiftA(FreeArrow<F, ARR, B, C> _freeArrow, F1<TypeEq<B, C>, R> Id,
          F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose, F1<F1<B, C>, R> Arr,
          F1<FreeArrow.First<F, ARR, ?, ?, ?, B, C>, R> First, F1<__2<F, B, C>, R> LiftF) {
        super(_freeArrow, Id, Compose, Arr, First, LiftF, null);
      }

      public final R LiftA(F1<__2<ARR, B, C>, R> LiftA) {
        FreeArrow.Cases<R, F, ARR, B, C> cases = FreeArrowImpl.cases(((PartialMatcher<F, ARR, B, C, R>) this).Id, ((PartialMatcher<F, ARR, B, C, R>) this).Compose, ((PartialMatcher<F, ARR, B, C, R>) this).Arr, ((PartialMatcher<F, ARR, B, C, R>) this).First, ((PartialMatcher<F, ARR, B, C, R>) this).LiftF, LiftA);
        return ((PartialMatcher<F, ARR, B, C, R>) this)._freeArrow.match(cases);
      }

      public final R LiftA_(R r) {
        return this.LiftA((liftAArr) -> r);
      }
    }

    public static class PartialMatcher_Arr<F, ARR, B, C, R> extends PartialMatcher_First<F, ARR, B, C, R> {
      PartialMatcher_Arr(FreeArrow<F, ARR, B, C> _freeArrow, F1<TypeEq<B, C>, R> Id,
          F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose) {
        super(_freeArrow, Id, Compose, null);
      }

      public final PartialMatcher_First<F, ARR, B, C, R> Arr(F1<F1<B, C>, R> Arr) {
        return new PartialMatcher_First<>(((PartialMatcher<F, ARR, B, C, R>) this)._freeArrow, ((PartialMatcher<F, ARR, B, C, R>) this).Id, ((PartialMatcher<F, ARR, B, C, R>) this).Compose, Arr);
      }

      public final PartialMatcher_First<F, ARR, B, C, R> Arr_(R r) {
        return this.Arr((arrF) -> r);
      }
    }

    public static class PartialMatcher_First<F, ARR, B, C, R> extends PartialMatcher_LiftF<F, ARR, B, C, R> {
      PartialMatcher_First(FreeArrow<F, ARR, B, C> _freeArrow, F1<TypeEq<B, C>, R> Id,
          F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose, F1<F1<B, C>, R> Arr) {
        super(_freeArrow, Id, Compose, Arr, null);
      }

      public final PartialMatcher_LiftF<F, ARR, B, C, R> First(F1<FreeArrow.First<F, ARR, ?, ?, ?, B, C>, R> First) {
        return new PartialMatcher_LiftF<>(((PartialMatcher<F, ARR, B, C, R>) this)._freeArrow, ((PartialMatcher<F, ARR, B, C, R>) this).Id, ((PartialMatcher<F, ARR, B, C, R>) this).Compose, ((PartialMatcher<F, ARR, B, C, R>) this).Arr, First);
      }

      public final PartialMatcher_LiftF<F, ARR, B, C, R> First_(R r) {
        return this.First((first) -> r);
      }
    }

    public static class PartialMatcher_LiftF<F, ARR, B, C, R> extends PartialMatcher_LiftA<F, ARR, B, C, R> {
      PartialMatcher_LiftF(FreeArrow<F, ARR, B, C> _freeArrow, F1<TypeEq<B, C>, R> Id,
          F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose, F1<F1<B, C>, R> Arr,
          F1<FreeArrow.First<F, ARR, ?, ?, ?, B, C>, R> First) {
        super(_freeArrow, Id, Compose, Arr, First, null);
      }

      public final PartialMatcher_LiftA<F, ARR, B, C, R> LiftF(F1<__2<F, B, C>, R> LiftF) {
        return new PartialMatcher_LiftA<>(((PartialMatcher<F, ARR, B, C, R>) this)._freeArrow, ((PartialMatcher<F, ARR, B, C, R>) this).Id, ((PartialMatcher<F, ARR, B, C, R>) this).Compose, ((PartialMatcher<F, ARR, B, C, R>) this).Arr, ((PartialMatcher<F, ARR, B, C, R>) this).First, LiftF);
      }

      public final PartialMatcher_LiftA<F, ARR, B, C, R> LiftF_(R r) {
        return this.LiftF((liftFf) -> r);
      }
    }

    public static class PartialMatcher_LiftA<F, ARR, B, C, R> extends PartialMatcher<F, ARR, B, C, R> {
      PartialMatcher_LiftA(FreeArrow<F, ARR, B, C> _freeArrow, F1<TypeEq<B, C>, R> Id,
          F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose, F1<F1<B, C>, R> Arr,
          F1<FreeArrow.First<F, ARR, ?, ?, ?, B, C>, R> First, F1<__2<F, B, C>, R> LiftF) {
        super(_freeArrow, Id, Compose, Arr, First, LiftF, null);
      }

      public final PartialMatcher<F, ARR, B, C, R> LiftA(F1<__2<ARR, B, C>, R> LiftA) {
        return new PartialMatcher<>(((PartialMatcher<F, ARR, B, C, R>) this)._freeArrow, ((PartialMatcher<F, ARR, B, C, R>) this).Id, ((PartialMatcher<F, ARR, B, C, R>) this).Compose, ((PartialMatcher<F, ARR, B, C, R>) this).Arr, ((PartialMatcher<F, ARR, B, C, R>) this).First, ((PartialMatcher<F, ARR, B, C, R>) this).LiftF, LiftA);
      }

      public final PartialMatcher<F, ARR, B, C, R> LiftA_(R r) {
        return this.LiftA((liftAArr) -> r);
      }
    }

    public static class PartialMatcher<F, ARR, B, C, R> {
      private final FreeArrow<F, ARR, B, C> _freeArrow;

      private final F1<TypeEq<B, C>, R> Id;

      private final F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose;

      private final F1<F1<B, C>, R> Arr;

      private final F1<FreeArrow.First<F, ARR, ?, ?, ?, B, C>, R> First;

      private final F1<__2<F, B, C>, R> LiftF;

      private final F1<__2<ARR, B, C>, R> LiftA;

      PartialMatcher(FreeArrow<F, ARR, B, C> _freeArrow, F1<TypeEq<B, C>, R> Id,
          F1<FreeArrow.Compose<F, ARR, B, ?, C>, R> Compose, F1<F1<B, C>, R> Arr,
          F1<FreeArrow.First<F, ARR, ?, ?, ?, B, C>, R> First, F1<__2<F, B, C>, R> LiftF,
          F1<__2<ARR, B, C>, R> LiftA) {
        this._freeArrow = _freeArrow;
        this.Id = Id;
        this.Compose = Compose;
        this.Arr = Arr;
        this.First = First;
        this.LiftF = LiftF;
        this.LiftA = LiftA;
      }

      public final R otherwise(Supplier<R> otherwise) {
        FreeArrow.Cases<R, F, ARR, B, C> cases = FreeArrowImpl.<F, ARR, B, C, R>cases(this.Id != null ? this.Id : (idTypeEq) -> otherwise.get(),
            this.Compose != null ? this.Compose : (compose) -> otherwise.get(),
            this.Arr != null ? this.Arr : (arrF) -> otherwise.get(),
            this.First != null ? this.First : (first) -> otherwise.get(),
            this.LiftF != null ? this.LiftF : (liftFf) -> otherwise.get(),
            this.LiftA != null ? this.LiftA : (liftAArr) -> otherwise.get());
        return this._freeArrow.match(cases);
      }

      public final R otherwise_(R r) {
        return this.otherwise(() -> r);
      }

      public final Maybe<R> otherwiseNothing() {
        FreeArrow.Cases<Maybe<R>, F, ARR, B, C> cases = FreeArrowImpl.cases((this.Id != null) ? (idTypeEq) -> Maybe.Just(this.Id.apply(idTypeEq))
            : (idTypeEq) -> Maybe.Nothing(),
            (this.Compose != null) ? (compose) -> Maybe.Just(this.Compose.apply(compose))
            : (compose) -> Maybe.Nothing(),
            (this.Arr != null) ? (arrF) -> Maybe.Just(this.Arr.apply(arrF))
            : (arrF) -> Maybe.Nothing(),
            (this.First != null) ? (first) -> Maybe.Just(this.First.apply(first))
            : (first) -> Maybe.Nothing(),
            (this.LiftF != null) ? (liftFf) -> Maybe.Just(this.LiftF.apply(liftFf))
            : (liftFf) -> Maybe.Nothing(),
            (this.LiftA != null) ? (liftAArr) -> Maybe.Just(this.LiftA.apply(liftAArr))
            : (liftAArr) -> Maybe.Nothing());
        return this._freeArrow.match(cases);
      }

      public final <A> Either<A, R> otherwiseLeft(Supplier<A> left) {
        FreeArrow.Cases<Either<A, R>, F, ARR, B, C> cases = FreeArrowImpl.cases((this.Id != null) ? (idTypeEq) -> Either.Right(this.Id.apply(idTypeEq))
            : (idTypeEq) -> Either.Left(left.get()),
            (this.Compose != null) ? (compose) -> Either.Right(this.Compose.apply(compose))
            : (compose) -> Either.Left(left.get()),
            (this.Arr != null) ? (arrF) -> Either.Right(this.Arr.apply(arrF))
            : (arrF) -> Either.Left(left.get()),
            (this.First != null) ? (first) -> Either.Right(this.First.apply(first))
            : (first) -> Either.Left(left.get()),
            (this.LiftF != null) ? (liftFf) -> Either.Right(this.LiftF.apply(liftFf))
            : (liftFf) -> Either.Left(left.get()),
            (this.LiftA != null) ? (liftAArr) -> Either.Right(this.LiftA.apply(liftAArr))
            : (liftAArr) -> Either.Left(left.get()));
        return this._freeArrow.match(cases);
      }

      public final <A> Either<A, R> otherwiseLeft_(A left) {
        return this.otherwiseLeft(() -> left);
      }
    }
  }
}
