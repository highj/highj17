package org.highj;

import org.highj.hkt.*;
import org.highj.control.arrow.Cokleisli;
import org.highj.control.arrow.Kleisli;
import org.highj.data.*;
import org.highj.data.continuations.Cont;
import org.highj.data.coroutine.ProducerT;
import org.highj.data.coroutine.YieldF;
import org.highj.data.eq.Eq;
import org.highj.data.eq.Eq1;
import org.highj.data.instance.list.Zipper;
import org.highj.data.ord.Ord;
import org.highj.data.ord.Ord1;
import org.highj.data.predicates.Pred;
import org.highj.data.stateful.*;
import org.highj.data.structural.*;
import org.highj.data.transformer.*;
import org.highj.data.tuple.T1;
import org.highj.data.tuple.T2;
import org.highj.data.tuple.T3;
import org.highj.data.tuple.T4;
import org.highj.function.*;
import org.highj.optic.*;

public final class Hkt {
  private Hkt() {}

  public static <A> List<A> asList(__<List.µ,A> hkt) {
    return (List<A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A> TypeEq<__<List.µ,A>, List<A>> list(){
    return (TypeEq) TypeEq.refl();
  }

  public static <F,G,A> Compose<F,G,A> asCompose(__<__<__<Compose.µ,F>,G>,A> hkt) {
    return (Compose<F,G,A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <F,G,A> TypeEq<__<__<__<Compose.µ,F>,G>,A>, Compose<F,G,A>> compose(){
    return (TypeEq) TypeEq.refl();
  }

  public static <EX,A,B,C> ErrorArrow<EX,A,B,C> asErrorArrow(__<__<__<__<ErrorArrow.µ,EX>,A>,B>,C> hkt) {
    return (ErrorArrow<EX,A,B,C>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <EX,A,B,C> TypeEq<__<__<__<__<ErrorArrow.µ,EX>,A>,B>,C>, ErrorArrow<EX,A,B,C>> errorArrow(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A,B,C> F2<A,B,C> asF2(__<__<__<F2.µ,A>,B>,C> hkt) {
    return (F2<A,B,C>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A,B,C> TypeEq<__<__<__<F2.µ,A>,B>,C>, F2<A,B,C>> f2(){
    return (TypeEq) TypeEq.refl();
  }

  public static <M,A> ListT<M,A> asListT(__<__<ListT.µ,M>,A> hkt) {
    return (ListT<M,A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <M,A> TypeEq<__<__<ListT.µ,M>,A>, ListT<M,A>> listT(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A,B,C> StreamArrow<A,B,C> asStreamArrow(__<__<__<StreamArrow.µ,A>,B>,C> hkt) {
    return (StreamArrow<A,B,C>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A,B,C> TypeEq<__<__<__<StreamArrow.µ,A>,B>,C>, StreamArrow<A,B,C>> streamArrow(){
    return (TypeEq) TypeEq.refl();
  }

  public static <E,M,A> ErrorT<E,M,A> asErrorT(__<__<__<ErrorT.µ,E>,M>,A> hkt) {
    return (ErrorT<E,M,A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <E,M,A> TypeEq<__<__<__<ErrorT.µ,E>,M>,A>, ErrorT<E,M,A>> errorT(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A,B,C> Automaton<A,B,C> asAutomaton(__<__<__<Automaton.µ,A>,B>,C> hkt) {
    return (Automaton<A,B,C>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A,B,C> TypeEq<__<__<__<Automaton.µ,A>,B>,C>, Automaton<A,B,C>> automaton(){
    return (TypeEq) TypeEq.refl();
  }

  public static <W,M,A> WriterT<W,M,A> asWriterT(__<__<__<WriterT.µ,W>,M>,A> hkt) {
    return (WriterT<W,M,A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <W,M,A> TypeEq<__<__<__<WriterT.µ,W>,M>,A>, WriterT<W,M,A>> writerT(){
    return (TypeEq) TypeEq.refl();
  }

  public static <S,A> Fold<S,A> asFold(__<__<Fold.µ,S>,A> hkt) {
    return (Fold<S,A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <S,A> TypeEq<__<__<Fold.µ,S>,A>, Fold<S,A>> fold(){
    return (TypeEq) TypeEq.refl();
  }

  public static <R,A> Coreader<R,A> asCoreader(__<__<Coreader.µ,R>,A> hkt) {
    return (Coreader<R,A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <R,A> TypeEq<__<__<Coreader.µ,R>,A>, Coreader<R,A>> coreader(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A,B> These<A,B> asThese(__<__<These.µ,A>,B> hkt) {
    return (These<A,B>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A,B> TypeEq<__<__<These.µ,A>,B>, These<A,B>> these(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A> Dequeue<A> asDequeue(__<Dequeue.µ,A> hkt) {
    return (Dequeue<A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A> TypeEq<__<Dequeue.µ,A>, Dequeue<A>> dequeue(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A> MultiSet<A> asMultiSet(__<MultiSet.µ,A> hkt) {
    return (MultiSet<A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A> TypeEq<__<MultiSet.µ,A>, MultiSet<A>> multiSet(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A> PriorityQueue<A> asPriorityQueue(__<PriorityQueue.µ,A> hkt) {
    return (PriorityQueue<A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A> TypeEq<__<PriorityQueue.µ,A>, PriorityQueue<A>> priorityQueue(){
    return (TypeEq) TypeEq.refl();
  }

  public static <V,A> YieldF<V,A> asYieldF(__<__<YieldF.µ,V>,A> hkt) {
    return (YieldF<V,A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <V,A> TypeEq<__<__<YieldF.µ,V>,A>, YieldF<V,A>> yieldF(){
    return (TypeEq) TypeEq.refl();
  }

  public static <E,M,A> ProducerT<E,M,A> asProducerT(__<__<__<ProducerT.µ,E>,M>,A> hkt) {
    return (ProducerT<E,M,A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <E,M,A> TypeEq<__<__<__<ProducerT.µ,E>,M>,A>, ProducerT<E,M,A>> producerT(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A> Zipper<A> asZipper(__<Zipper.µ,A> hkt) {
    return (Zipper<A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A> TypeEq<__<Zipper.µ,A>, Zipper<A>> zipper(){
    return (TypeEq) TypeEq.refl();
  }

  public static <S,A> Traversal<S,A> asTraversal(__<__<Traversal.µ,S>,A> hkt) {
    return (Traversal<S,A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <S,A> TypeEq<__<__<Traversal.µ,S>,A>, Traversal<S,A>> traversal(){
    return (TypeEq) TypeEq.refl();
  }

  public static <F,G> NF2<F,G> asNF2(__<__<NF2.µ,F>,G> hkt) {
    return (NF2<F,G>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <F,G> TypeEq<__<__<NF2.µ,F>,G>, NF2<F,G>> nF2(){
    return (TypeEq) TypeEq.refl();
  }

  public static <F,ARR,B,C> FreeArrow<F,ARR,B,C> asFreeArrow(__<__<__<__<FreeArrow.µ,F>,ARR>,B>,C> hkt) {
    return (FreeArrow<F,ARR,B,C>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <F,ARR,B,C> TypeEq<__<__<__<__<FreeArrow.µ,F>,ARR>,B>,C>, FreeArrow<F,ARR,B,C>> freeArrow(){
    return (TypeEq) TypeEq.refl();
  }

  public static <F,G> NF<F,G> asNF(__<__<NF.µ,F>,G> hkt) {
    return (NF<F,G>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <F,G> TypeEq<__<__<NF.µ,F>,G>, NF<F,G>> nF(){
    return (TypeEq) TypeEq.refl();
  }

  public static <R,W,S,M,A> RWST<R,W,S,M,A> asRWST(__<__<__<__<__<RWST.µ,R>,W>,S>,M>,A> hkt) {
    return (RWST<R,W,S,M,A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <R,W,S,M,A> TypeEq<__<__<__<__<__<RWST.µ,R>,W>,S>,M>,A>, RWST<R,W,S,M,A>> rWST(){
    return (TypeEq) TypeEq.refl();
  }

  public static <M,A> IdentityT<M,A> asIdentityT(__<__<IdentityT.µ,M>,A> hkt) {
    return (IdentityT<M,A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <M,A> TypeEq<__<__<IdentityT.µ,M>,A>, IdentityT<M,A>> identityT(){
    return (TypeEq) TypeEq.refl();
  }

  public static <R,A> Cont<R,A> asCont(__<__<Cont.µ,R>,A> hkt) {
    return (Cont<R,A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <R,A> TypeEq<__<__<Cont.µ,R>,A>, Cont<R,A>> cont(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A> SafeIO<A> asSafeIO(__<SafeIO.µ,A> hkt) {
    return (SafeIO<A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A> TypeEq<__<SafeIO.µ,A>, SafeIO<A>> safeIO(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A> Stream<A> asStream(__<Stream.µ,A> hkt) {
    return (Stream<A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A> TypeEq<__<Stream.µ,A>, Stream<A>> stream(){
    return (TypeEq) TypeEq.refl();
  }

  public static <F> Eq1<F> asEq1(__<Eq1.µ,F> hkt) {
    return (Eq1<F>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <F> TypeEq<__<Eq1.µ,F>, Eq1<F>> eq1(){
    return (TypeEq) TypeEq.refl();
  }

  public static <R,A,B,C> ReaderArrow<R,A,B,C> asReaderArrow(__<__<__<__<ReaderArrow.µ,R>,A>,B>,C> hkt) {
    return (ReaderArrow<R,A,B,C>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <R,A,B,C> TypeEq<__<__<__<__<ReaderArrow.µ,R>,A>,B>,C>, ReaderArrow<R,A,B,C>> readerArrow(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A,B,C,D> T4<A,B,C,D> asT4(__<__<__<__<T4.µ,A>,B>,C>,D> hkt) {
    return (T4<A,B,C,D>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A,B,C,D> TypeEq<__<__<__<__<T4.µ,A>,B>,C>,D>, T4<A,B,C,D>> t4(){
    return (TypeEq) TypeEq.refl();
  }

  public static <S,A> Setter<S,A> asSetter(__<__<Setter.µ,S>,A> hkt) {
    return (Setter<S,A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <S,A> TypeEq<__<__<Setter.µ,S>,A>, Setter<S,A>> setter(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A> Endo<A> asEndo(__<Endo.µ,A> hkt) {
    return (Endo<A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A> TypeEq<__<Endo.µ,A>, Endo<A>> endo(){
    return (TypeEq) TypeEq.refl();
  }

  public static <S,A> Iso<S,A> asIso(__<__<Iso.µ,S>,A> hkt) {
    return (Iso<S,A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <S,A> TypeEq<__<__<Iso.µ,S>,A>, Iso<S,A>> iso(){
    return (TypeEq) TypeEq.refl();
  }

  public static <S,A> Getter<S,A> asGetter(__<__<Getter.µ,S>,A> hkt) {
    return (Getter<S,A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <S,A> TypeEq<__<__<Getter.µ,S>,A>, Getter<S,A>> getter(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A> Pred<A> asPred(__<Pred.µ,A> hkt) {
    return (Pred<A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A> TypeEq<__<Pred.µ,A>, Pred<A>> pred(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A> Memo<A> asMemo(__<Memo.µ,A> hkt) {
    return (Memo<A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A> TypeEq<__<Memo.µ,A>, Memo<A>> memo(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A> IO<A> asIO(__<IO.µ,A> hkt) {
    return (IO<A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A> TypeEq<__<IO.µ,A>, IO<A>> iO(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A> Effect1<A> asEffect1(__<Effect1.µ,A> hkt) {
    return (Effect1<A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A> TypeEq<__<Effect1.µ,A>, Effect1<A>> effect1(){
    return (TypeEq) TypeEq.refl();
  }

  public static <S,A> Optional<S,A> asOptional(__<__<Optional.µ,S>,A> hkt) {
    return (Optional<S,A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <S,A> TypeEq<__<__<Optional.µ,S>,A>, Optional<S,A>> optional(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A,B,C,D> Either4<A,B,C,D> asEither4(__<__<__<__<Either4.µ,A>,B>,C>,D> hkt) {
    return (Either4<A,B,C,D>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A,B,C,D> TypeEq<__<__<__<__<Either4.µ,A>,B>,C>,D>, Either4<A,B,C,D>> either4(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A,B,C,D,E> F4<A,B,C,D,E> asF4(__<__<__<__<__<F4.µ,A>,B>,C>,D>,E> hkt) {
    return (F4<A,B,C,D,E>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A,B,C,D,E> TypeEq<__<__<__<__<__<F4.µ,A>,B>,C>,D>,E>, F4<A,B,C,D,E>> f4(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A> AsyncIO<A> asAsyncIO(__<AsyncIO.µ,A> hkt) {
    return (AsyncIO<A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A> TypeEq<__<AsyncIO.µ,A>, AsyncIO<A>> asyncIO(){
    return (TypeEq) TypeEq.refl();
  }

  public static <F,M,A> FreeT<F,M,A> asFreeT(__<__<__<FreeT.µ,F>,M>,A> hkt) {
    return (FreeT<F,M,A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <F,M,A> TypeEq<__<__<__<FreeT.µ,F>,M>,A>, FreeT<F,M,A>> freeT(){
    return (TypeEq) TypeEq.refl();
  }

  public static <M,A,B> Dual<M,A,B> asDual(__<__<__<Dual.µ,M>,A>,B> hkt) {
    return (Dual<M,A,B>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <M,A,B> TypeEq<__<__<__<Dual.µ,M>,A>,B>, Dual<M,A,B>> dual(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A> Tree<A> asTree(__<Tree.µ,A> hkt) {
    return (Tree<A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A> TypeEq<__<Tree.µ,A>, Tree<A>> tree(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A,B> T2<A,B> asT2(__<__<T2.µ,A>,B> hkt) {
    return (T2<A,B>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A,B> TypeEq<__<__<T2.µ,A>,B>, T2<A,B>> t2(){
    return (TypeEq) TypeEq.refl();
  }

  public static <W,A,B,C> WriterArrow<W,A,B,C> asWriterArrow(__<__<__<__<WriterArrow.µ,W>,A>,B>,C> hkt) {
    return (WriterArrow<W,A,B,C>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <W,A,B,C> TypeEq<__<__<__<__<WriterArrow.µ,W>,A>,B>,C>, WriterArrow<W,A,B,C>> writerArrow(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A,B> Map<A,B> asMap(__<__<Map.µ,A>,B> hkt) {
    return (Map<A,B>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A,B> TypeEq<__<__<Map.µ,A>,B>, Map<A,B>> map(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A,B> Either<A,B> asEither(__<__<Either.µ,A>,B> hkt) {
    return (Either<A,B>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A,B> TypeEq<__<__<Either.µ,A>,B>, Either<A,B>> either(){
    return (TypeEq) TypeEq.refl();
  }

  public static <F> Ord1<F> asOrd1(__<Ord1.µ,F> hkt) {
    return (Ord1<F>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <F> TypeEq<__<Ord1.µ,F>, Ord1<F>> ord1(){
    return (TypeEq) TypeEq.refl();
  }

  public static <S,A> Prism<S,A> asPrism(__<__<Prism.µ,S>,A> hkt) {
    return (Prism<S,A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <S,A> TypeEq<__<__<Prism.µ,S>,A>, Prism<S,A>> prism(){
    return (TypeEq) TypeEq.refl();
  }

  public static <E> TreeSet<E> asTreeSet(__<TreeSet.µ,E> hkt) {
    return (TreeSet<E>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <E> TypeEq<__<TreeSet.µ,E>, TreeSet<E>> treeSet(){
    return (TypeEq) TypeEq.refl();
  }

  public static <K,V> TreeMap<K,V> asTreeMap(__<__<TreeMap.µ,K>,V> hkt) {
    return (TreeMap<K,V>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <K,V> TypeEq<__<__<TreeMap.µ,K>,V>, TreeMap<K,V>> treeMap(){
    return (TypeEq) TypeEq.refl();
  }

  public static <T> Ord<T> asOrd(__<Ord.µ,T> hkt) {
    return (Ord<T>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <T> TypeEq<__<Ord.µ,T>, Ord<T>> ord(){
    return (TypeEq) TypeEq.refl();
  }

  public static <S,A> Const<S,A> asConst(__<__<Const.µ,S>,A> hkt) {
    return (Const<S,A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <S,A> TypeEq<__<__<Const.µ,S>,A>, Const<S,A>> const_(){
    return (TypeEq) TypeEq.refl();
  }

  public static <R,M,A> ReaderT<R,M,A> asReaderT(__<__<__<ReaderT.µ,R>,M>,A> hkt) {
    return (ReaderT<R,M,A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <R,M,A> TypeEq<__<__<__<ReaderT.µ,R>,M>,A>, ReaderT<R,M,A>> readerT(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A> T1<A> asT1(__<T1.µ,A> hkt) {
    return (T1<A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A> TypeEq<__<T1.µ,A>, T1<A>> t1(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A,B,C,D> F3<A,B,C,D> asF3(__<__<__<__<F3.µ,A>,B>,C>,D> hkt) {
    return (F3<A,B,C,D>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A,B,C,D> TypeEq<__<__<__<__<F3.µ,A>,B>,C>,D>, F3<A,B,C,D>> f3(){
    return (TypeEq) TypeEq.refl();
  }

  public static <S,A> Tagged<S,A> asTagged(__<__<Tagged.µ,S>,A> hkt) {
    return (Tagged<S,A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <S,A> TypeEq<__<__<Tagged.µ,S>,A>, Tagged<S,A>> tagged(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A> Maybe<A> asMaybe(__<Maybe.µ,A> hkt) {
    return (Maybe<A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A> TypeEq<__<Maybe.µ,A>, Maybe<A>> maybe(){
    return (TypeEq) TypeEq.refl();
  }

  public static <W,A,B> Cokleisli<W,A,B> asCokleisli(__<__<__<Cokleisli.µ,W>,A>,B> hkt) {
    return (Cokleisli<W,A,B>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <W,A,B> TypeEq<__<__<__<Cokleisli.µ,W>,A>,B>, Cokleisli<W,A,B>> cokleisli(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A,B> F1<A,B> asF1(__<__<F1.µ,A>,B> hkt) {
    return (F1<A,B>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A,B> TypeEq<__<__<F1.µ,A>,B>, F1<A,B>> f1(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A,B,C> T3<A,B,C> asT3(__<__<__<T3.µ,A>,B>,C> hkt) {
    return (T3<A,B,C>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A,B,C> TypeEq<__<__<__<T3.µ,A>,B>,C>, T3<A,B,C>> t3(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A> IntMap<A> asIntMap(__<IntMap.µ,A> hkt) {
    return (IntMap<A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A> TypeEq<__<IntMap.µ,A>, IntMap<A>> intMap(){
    return (TypeEq) TypeEq.refl();
  }

  public static <T> Eq<T> asEq(__<Eq.µ,T> hkt) {
    return (Eq<T>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <T> TypeEq<__<Eq.µ,T>, Eq<T>> eq(){
    return (TypeEq) TypeEq.refl();
  }

  public static <S,M,A> StateT<S,M,A> asStateT(__<__<__<StateT.µ,S>,M>,A> hkt) {
    return (StateT<S,M,A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <S,M,A> TypeEq<__<__<__<StateT.µ,S>,M>,A>, StateT<S,M,A>> stateT(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A,B,C> Either3<A,B,C> asEither3(__<__<__<Either3.µ,A>,B>,C> hkt) {
    return (Either3<A,B,C>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A,B,C> TypeEq<__<__<__<Either3.µ,A>,B>,C>, Either3<A,B,C>> either3(){
    return (TypeEq) TypeEq.refl();
  }

  public static <S,A,B,C> StateArrow<S,A,B,C> asStateArrow(__<__<__<__<StateArrow.µ,S>,A>,B>,C> hkt) {
    return (StateArrow<S,A,B,C>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <S,A,B,C> TypeEq<__<__<__<__<StateArrow.µ,S>,A>,B>,C>, StateArrow<S,A,B,C>> stateArrow(){
    return (TypeEq) TypeEq.refl();
  }

  public static <M,A,B> Kleisli<M,A,B> asKleisli(__<__<__<Kleisli.µ,M>,A>,B> hkt) {
    return (Kleisli<M,A,B>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <M,A,B> TypeEq<__<__<__<Kleisli.µ,M>,A>,B>, Kleisli<M,A,B>> kleisli(){
    return (TypeEq) TypeEq.refl();
  }

  public static <A> Set<A> asSet(__<Set.µ,A> hkt) {
    return (Set<A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <A> TypeEq<__<Set.µ,A>, Set<A>> set(){
    return (TypeEq) TypeEq.refl();
  }

  public static <M,A> MaybeT<M,A> asMaybeT(__<__<MaybeT.µ,M>,A> hkt) {
    return (MaybeT<M,A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <M,A> TypeEq<__<__<MaybeT.µ,M>,A>, MaybeT<M,A>> maybeT(){
    return (TypeEq) TypeEq.refl();
  }

  public static <S,A> Lens<S,A> asLens(__<__<Lens.µ,S>,A> hkt) {
    return (Lens<S,A>) hkt;
  }

  @SuppressWarnings("unchecked")
  public static <S,A> TypeEq<__<__<Lens.µ,S>,A>, Lens<S,A>> lens(){
    return (TypeEq) TypeEq.refl();
  }
}