package dev.snipme.highlights.internal

val longJavaCode = """
         //
         // Source code recreated from a .class file by IntelliJ IDEA
         // (powered by FernFlower decompiler)
         //

         package java.util;

         import java.io.IOException;
         import java.io.InvalidObjectException;
         import java.io.ObjectInputStream;
         import java.io.ObjectOutputStream;
         import java.io.Serializable;
         import java.util.function.Consumer;
         import java.util.function.Predicate;
         import java.util.function.UnaryOperator;
         import jdk.internal.access.SharedSecrets;
         import jdk.internal.util.ArraysSupport;

         public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, Serializable {
             private static final long serialVersionUID = 8683452581122892189L;
             private static final int DEFAULT_CAPACITY = 10;
             private static final Object[] EMPTY_ELEMENTDATA = new Object[0];
             private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = new Object[0];
             transient Object[] elementData;
             private int size;

             public ArrayList(int var1) {
                 if (var1 > 0) {
                     this.elementData = new Object[var1];
                 } else {
                     if (var1 != 0) {
                         throw new IllegalArgumentException("Illegal Capacity: " + var1);
                     }

                     this.elementData = EMPTY_ELEMENTDATA;
                 }

             }

             public ArrayList() {
                 this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
             }

             public ArrayList(Collection<? extends E> var1) {
                 Object[] var2 = var1.toArray();
                 if ((this.size = var2.length) != 0) {
                     if (var1.getClass() == ArrayList.class) {
                         this.elementData = var2;
                     } else {
                         this.elementData = Arrays.copyOf(var2, this.size, Object[].class);
                     }
                 } else {
                     this.elementData = EMPTY_ELEMENTDATA;
                 }

             }

             public void trimToSize() {
                 ++this.modCount;
                 if (this.size < this.elementData.length) {
                     this.elementData = this.size == 0 ? EMPTY_ELEMENTDATA : Arrays.copyOf(this.elementData, this.size);
                 }

             }

             public void ensureCapacity(int var1) {
                 if (var1 > this.elementData.length && (this.elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA || var1 > 10)) {
                     ++this.modCount;
                     this.grow(var1);
                 }

             }

             private Object[] grow(int var1) {
                 int var2 = this.elementData.length;
                 if (var2 <= 0 && this.elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
                     return this.elementData = new Object[Math.max(10, var1)];
                 } else {
                     int var3 = ArraysSupport.newLength(var2, var1 - var2, var2 >> 1);
                     return this.elementData = Arrays.copyOf(this.elementData, var3);
                 }
             }

             private Object[] grow() {
                 return this.grow(this.size + 1);
             }

             public int size() {
                 return this.size;
             }

             public boolean isEmpty() {
                 return this.size == 0;
             }

             public boolean contains(Object var1) {
                 return this.indexOf(var1) >= 0;
             }

             public int indexOf(Object var1) {
                 return this.indexOfRange(var1, 0, this.size);
             }

             int indexOfRange(Object var1, int var2, int var3) {
                 Object[] var4 = this.elementData;
                 int var5;
                 if (var1 == null) {
                     for(var5 = var2; var5 < var3; ++var5) {
                         if (var4[var5] == null) {
                             return var5;
                         }
                     }
                 } else {
                     for(var5 = var2; var5 < var3; ++var5) {
                         if (var1.equals(var4[var5])) {
                             return var5;
                         }
                     }
                 }

                 return -1;
             }

             public int lastIndexOf(Object var1) {
                 return this.lastIndexOfRange(var1, 0, this.size);
             }

             int lastIndexOfRange(Object var1, int var2, int var3) {
                 Object[] var4 = this.elementData;
                 int var5;
                 if (var1 == null) {
                     for(var5 = var3 - 1; var5 >= var2; --var5) {
                         if (var4[var5] == null) {
                             return var5;
                         }
                     }
                 } else {
                     for(var5 = var3 - 1; var5 >= var2; --var5) {
                         if (var1.equals(var4[var5])) {
                             return var5;
                         }
                     }
                 }

                 return -1;
             }

             public Object clone() {
                 try {
                     ArrayList var1 = (ArrayList)super.clone();
                     var1.elementData = Arrays.copyOf(this.elementData, this.size);
                     var1.modCount = 0;
                     return var1;
                 } catch (CloneNotSupportedException var2) {
                     throw new InternalError(var2);
                 }
             }

             public Object[] toArray() {
                 return Arrays.copyOf(this.elementData, this.size);
             }

             public <T> T[] toArray(T[] var1) {
                 if (var1.length < this.size) {
                     return Arrays.copyOf(this.elementData, this.size, var1.getClass());
                 } else {
                     System.arraycopy(this.elementData, 0, var1, 0, this.size);
                     if (var1.length > this.size) {
                         var1[this.size] = null;
                     }

                     return var1;
                 }
             }

             E elementData(int var1) {
                 return this.elementData[var1];
             }

             static <E> E elementAt(Object[] var0, int var1) {
                 return var0[var1];
             }

             public E get(int var1) {
                 Objects.checkIndex(var1, this.size);
                 return this.elementData(var1);
             }

             public E set(int var1, E var2) {
                 Objects.checkIndex(var1, this.size);
                 Object var3 = this.elementData(var1);
                 this.elementData[var1] = var2;
                 return var3;
             }

             private void add(E var1, Object[] var2, int var3) {
                 if (var3 == var2.length) {
                     var2 = this.grow();
                 }

                 var2[var3] = var1;
                 this.size = var3 + 1;
             }

             public boolean add(E var1) {
                 ++this.modCount;
                 this.add(var1, this.elementData, this.size);
                 return true;
             }

             public void add(int var1, E var2) {
                 this.rangeCheckForAdd(var1);
                 ++this.modCount;
                 int var3;
                 Object[] var4;
                 if ((var3 = this.size) == (var4 = this.elementData).length) {
                     var4 = this.grow();
                 }

                 System.arraycopy(var4, var1, var4, var1 + 1, var3 - var1);
                 var4[var1] = var2;
                 this.size = var3 + 1;
             }

             public E remove(int var1) {
                 Objects.checkIndex(var1, this.size);
                 Object[] var2 = this.elementData;
                 Object var3 = var2[var1];
                 this.fastRemove(var2, var1);
                 return var3;
             }

             public boolean equals(Object var1) {
                 if (var1 == this) {
                     return true;
                 } else if (!(var1 instanceof List)) {
                     return false;
                 } else {
                     int var2 = this.modCount;
                     boolean var3 = var1.getClass() == ArrayList.class ? this.equalsArrayList((ArrayList)var1) : this.equalsRange((List)var1, 0, this.size);
                     this.checkForComodification(var2);
                     return var3;
                 }
             }

             boolean equalsRange(List<?> var1, int var2, int var3) {
                 Object[] var4 = this.elementData;
                 if (var3 > var4.length) {
                     throw new ConcurrentModificationException();
                 } else {
                     Iterator var5;
                     for(var5 = var1.iterator(); var2 < var3; ++var2) {
                         if (!var5.hasNext() || !Objects.equals(var4[var2], var5.next())) {
                             return false;
                         }
                     }

                     return !var5.hasNext();
                 }
             }

             private boolean equalsArrayList(ArrayList<?> var1) {
                 int var2 = var1.modCount;
                 int var3 = this.size;
                 boolean var4;
                 if (var4 = var3 == var1.size) {
                     Object[] var5 = var1.elementData;
                     Object[] var6 = this.elementData;
                     if (var3 > var6.length || var3 > var5.length) {
                         throw new ConcurrentModificationException();
                     }

                     for(int var7 = 0; var7 < var3; ++var7) {
                         if (!Objects.equals(var6[var7], var5[var7])) {
                             var4 = false;
                             break;
                         }
                     }
                 }

                 var1.checkForComodification(var2);
                 return var4;
             }

             private void checkForComodification(int var1) {
                 if (this.modCount != var1) {
                     throw new ConcurrentModificationException();
                 }
             }

             public int hashCode() {
                 int var1 = this.modCount;
                 int var2 = this.hashCodeRange(0, this.size);
                 this.checkForComodification(var1);
                 return var2;
             }

             int hashCodeRange(int var1, int var2) {
                 Object[] var3 = this.elementData;
                 if (var2 > var3.length) {
                     throw new ConcurrentModificationException();
                 } else {
                     int var4 = 1;

                     for(int var5 = var1; var5 < var2; ++var5) {
                         Object var6 = var3[var5];
                         var4 = 31 * var4 + (var6 == null ? 0 : var6.hashCode());
                     }

                     return var4;
                 }
             }

             public boolean remove(Object var1) {
                 Object[] var2 = this.elementData;
                 int var3 = this.size;
                 int var4 = 0;
                 if (var1 == null) {
                     while(true) {
                         if (var4 >= var3) {
                             return false;
                         }

                         if (var2[var4] == null) {
                             break;
                         }

                         ++var4;
                     }
                 } else {
                     while(true) {
                         if (var4 >= var3) {
                             return false;
                         }

                         if (var1.equals(var2[var4])) {
                             break;
                         }

                         ++var4;
                     }
                 }

                 this.fastRemove(var2, var4);
                 return true;
             }

             private void fastRemove(Object[] var1, int var2) {
                 ++this.modCount;
                 int var3;
                 if ((var3 = this.size - 1) > var2) {
                     System.arraycopy(var1, var2 + 1, var1, var2, var3 - var2);
                 }

                 var1[this.size = var3] = null;
             }

             public void clear() {
                 ++this.modCount;
                 Object[] var1 = this.elementData;
                 int var2 = this.size;

                 for(int var3 = this.size = 0; var3 < var2; ++var3) {
                     var1[var3] = null;
                 }

             }

             public boolean addAll(Collection<? extends E> var1) {
                 Object[] var2 = var1.toArray();
                 ++this.modCount;
                 int var3 = var2.length;
                 if (var3 == 0) {
                     return false;
                 } else {
                     Object[] var4;
                     int var5;
                     if (var3 > (var4 = this.elementData).length - (var5 = this.size)) {
                         var4 = this.grow(var5 + var3);
                     }

                     System.arraycopy(var2, 0, var4, var5, var3);
                     this.size = var5 + var3;
                     return true;
                 }
             }

             public boolean addAll(int var1, Collection<? extends E> var2) {
                 this.rangeCheckForAdd(var1);
                 Object[] var3 = var2.toArray();
                 ++this.modCount;
                 int var4 = var3.length;
                 if (var4 == 0) {
                     return false;
                 } else {
                     Object[] var5;
                     int var6;
                     if (var4 > (var5 = this.elementData).length - (var6 = this.size)) {
                         var5 = this.grow(var6 + var4);
                     }

                     int var7 = var6 - var1;
                     if (var7 > 0) {
                         System.arraycopy(var5, var1, var5, var1 + var4, var7);
                     }

                     System.arraycopy(var3, 0, var5, var1, var4);
                     this.size = var6 + var4;
                     return true;
                 }
             }

             protected void removeRange(int var1, int var2) {
                 if (var1 > var2) {
                     throw new IndexOutOfBoundsException(outOfBoundsMsg(var1, var2));
                 } else {
                     ++this.modCount;
                     this.shiftTailOverGap(this.elementData, var1, var2);
                 }
             }

             private void shiftTailOverGap(Object[] var1, int var2, int var3) {
                 System.arraycopy(var1, var3, var1, var2, this.size - var3);
                 int var4 = this.size;

                 for(int var5 = this.size -= var3 - var2; var5 < var4; ++var5) {
                     var1[var5] = null;
                 }

             }

             private void rangeCheckForAdd(int var1) {
                 if (var1 > this.size || var1 < 0) {
                     throw new IndexOutOfBoundsException(this.outOfBoundsMsg(var1));
                 }
             }

             private String outOfBoundsMsg(int var1) {
                 return "Index: " + var1 + ", Size: " + this.size;
             }

             private static String outOfBoundsMsg(int var0, int var1) {
                 return "From Index: " + var0 + " > To Index: " + var1;
             }

             public boolean removeAll(Collection<?> var1) {
                 return this.batchRemove(var1, false, 0, this.size);
             }

             public boolean retainAll(Collection<?> var1) {
                 return this.batchRemove(var1, true, 0, this.size);
             }

             boolean batchRemove(Collection<?> var1, boolean var2, int var3, int var4) {
                 Objects.requireNonNull(var1);
                 Object[] var5 = this.elementData;

                 for(int var6 = var3; var6 != var4; ++var6) {
                     if (var1.contains(var5[var6]) != var2) {
                         int var7 = var6++;

                         try {
                             for(; var6 < var4; ++var6) {
                                 Object var8;
                                 if (var1.contains(var8 = var5[var6]) == var2) {
                                     var5[var7++] = var8;
                                 }
                             }
                         } catch (Throwable var12) {
                             System.arraycopy(var5, var6, var5, var7, var4 - var6);
                             var7 += var4 - var6;
                             throw var12;
                         } finally {
                             this.modCount += var4 - var7;
                             this.shiftTailOverGap(var5, var7, var4);
                         }

                         return true;
                     }
                 }

                 return false;
             }

             private void writeObject(ObjectOutputStream var1) throws IOException {
                 int var2 = this.modCount;
                 var1.defaultWriteObject();
                 var1.writeInt(this.size);

                 for(int var3 = 0; var3 < this.size; ++var3) {
                     var1.writeObject(this.elementData[var3]);
                 }

                 if (this.modCount != var2) {
                     throw new ConcurrentModificationException();
                 }
             }

             private void readObject(ObjectInputStream var1) throws IOException, ClassNotFoundException {
                 var1.defaultReadObject();
                 var1.readInt();
                 if (this.size > 0) {
                     SharedSecrets.getJavaObjectInputStreamAccess().checkArray(var1, Object[].class, this.size);
                     Object[] var2 = new Object[this.size];

                     for(int var3 = 0; var3 < this.size; ++var3) {
                         var2[var3] = var1.readObject();
                     }

                     this.elementData = var2;
                 } else {
                     if (this.size != 0) {
                         throw new InvalidObjectException("Invalid size: " + this.size);
                     }

                     this.elementData = EMPTY_ELEMENTDATA;
                 }

             }

             public ListIterator<E> listIterator(int var1) {
                 this.rangeCheckForAdd(var1);
                 return new ListItr(var1);
             }

             public ListIterator<E> listIterator() {
                 return new ListItr(0);
             }

             public Iterator<E> iterator() {
                 return new Itr();
             }

             public List<E> subList(int var1, int var2) {
                 subListRangeCheck(var1, var2, this.size);
                 return new SubList(this, var1, var2);
             }

             public void forEach(Consumer<? super E> var1) {
                 Objects.requireNonNull(var1);
                 int var2 = this.modCount;
                 Object[] var3 = this.elementData;
                 int var4 = this.size;

                 for(int var5 = 0; this.modCount == var2 && var5 < var4; ++var5) {
                     var1.accept(elementAt(var3, var5));
                 }

                 if (this.modCount != var2) {
                     throw new ConcurrentModificationException();
                 }
             }

             public Spliterator<E> spliterator() {
                 return new ArrayListSpliterator(0, -1, 0);
             }

             private static long[] nBits(int var0) {
                 return new long[(var0 - 1 >> 6) + 1];
             }

             private static void setBit(long[] var0, int var1) {
                 var0[var1 >> 6] |= 1L << var1;
             }

             private static boolean isClear(long[] var0, int var1) {
                 return (var0[var1 >> 6] & 1L << var1) == 0L;
             }

             public boolean removeIf(Predicate<? super E> var1) {
                 return this.removeIf(var1, 0, this.size);
             }

             boolean removeIf(Predicate<? super E> var1, int var2, int var3) {
                 Objects.requireNonNull(var1);
                 int var4 = this.modCount;

                 Object[] var5;
                 for(var5 = this.elementData; var2 < var3 && !var1.test(elementAt(var5, var2)); ++var2) {
                 }

                 if (var2 < var3) {
                     int var6 = var2;
                     long[] var7 = nBits(var3 - var2);
                     var7[0] = 1L;
                     ++var2;

                     for(; var2 < var3; ++var2) {
                         if (var1.test(elementAt(var5, var2))) {
                             setBit(var7, var2 - var6);
                         }
                     }

                     if (this.modCount != var4) {
                         throw new ConcurrentModificationException();
                     } else {
                         ++this.modCount;
                         int var8 = var6;

                         for(var2 = var6; var2 < var3; ++var2) {
                             if (isClear(var7, var2 - var6)) {
                                 var5[var8++] = var5[var2];
                             }
                         }

                         this.shiftTailOverGap(var5, var8, var3);
                         return true;
                     }
                 } else if (this.modCount != var4) {
                     throw new ConcurrentModificationException();
                 } else {
                     return false;
                 }
             }

             public void replaceAll(UnaryOperator<E> var1) {
                 this.replaceAllRange(var1, 0, this.size);
                 ++this.modCount;
             }

             private void replaceAllRange(UnaryOperator<E> var1, int var2, int var3) {
                 Objects.requireNonNull(var1);
                 int var4 = this.modCount;

                 for(Object[] var5 = this.elementData; this.modCount == var4 && var2 < var3; ++var2) {
                     var5[var2] = var1.apply(elementAt(var5, var2));
                 }

                 if (this.modCount != var4) {
                     throw new ConcurrentModificationException();
                 }
             }

             public void sort(Comparator<? super E> var1) {
                 int var2 = this.modCount;
                 Arrays.sort(this.elementData, 0, this.size, var1);
                 if (this.modCount != var2) {
                     throw new ConcurrentModificationException();
                 } else {
                     ++this.modCount;
                 }
             }

             void checkInvariants() {
             }

             private class ListItr extends ArrayList<E>.Itr implements ListIterator<E> {
                 ListItr(int var2) {
                     super();
                     this.cursor = var2;
                 }

                 public boolean hasPrevious() {
                     return this.cursor != 0;
                 }

                 public int nextIndex() {
                     return this.cursor;
                 }

                 public int previousIndex() {
                     return this.cursor - 1;
                 }

                 public E previous() {
                     this.checkForComodification();
                     int var1 = this.cursor - 1;
                     if (var1 < 0) {
                         throw new NoSuchElementException();
                     } else {
                         Object[] var2 = ArrayList.this.elementData;
                         if (var1 >= var2.length) {
                             throw new ConcurrentModificationException();
                         } else {
                             this.cursor = var1;
                             return var2[this.lastRet = var1];
                         }
                     }
                 }

                 public void set(E var1) {
                     if (this.lastRet < 0) {
                         throw new IllegalStateException();
                     } else {
                         this.checkForComodification();

                         try {
                             ArrayList.this.set(this.lastRet, var1);
                         } catch (IndexOutOfBoundsException var3) {
                             throw new ConcurrentModificationException();
                         }
                     }
                 }

                 public void add(E var1) {
                     this.checkForComodification();

                     try {
                         int var2 = this.cursor;
                         ArrayList.this.add(var2, var1);
                         this.cursor = var2 + 1;
                         this.lastRet = -1;
                         this.expectedModCount = ArrayList.this.modCount;
                     } catch (IndexOutOfBoundsException var3) {
                         throw new ConcurrentModificationException();
                     }
                 }
             }

             private class Itr implements Iterator<E> {
                 int cursor;
                 int lastRet = -1;
                 int expectedModCount;

                 Itr() {
                     this.expectedModCount = ArrayList.this.modCount;
                 }

                 public boolean hasNext() {
                     return this.cursor != ArrayList.this.size;
                 }

                 public E next() {
                     this.checkForComodification();
                     int var1 = this.cursor;
                     if (var1 >= ArrayList.this.size) {
                         throw new NoSuchElementException();
                     } else {
                         Object[] var2 = ArrayList.this.elementData;
                         if (var1 >= var2.length) {
                             throw new ConcurrentModificationException();
                         } else {
                             this.cursor = var1 + 1;
                             return var2[this.lastRet = var1];
                         }
                     }
                 }

                 public void remove() {
                     if (this.lastRet < 0) {
                         throw new IllegalStateException();
                     } else {
                         this.checkForComodification();

                         try {
                             ArrayList.this.remove(this.lastRet);
                             this.cursor = this.lastRet;
                             this.lastRet = -1;
                             this.expectedModCount = ArrayList.this.modCount;
                         } catch (IndexOutOfBoundsException var2) {
                             throw new ConcurrentModificationException();
                         }
                     }
                 }

                 public void forEachRemaining(Consumer<? super E> var1) {
                     Objects.requireNonNull(var1);
                     int var2 = ArrayList.this.size;
                     int var3 = this.cursor;
                     if (var3 < var2) {
                         Object[] var4 = ArrayList.this.elementData;
                         if (var3 >= var4.length) {
                             throw new ConcurrentModificationException();
                         }

                         while(var3 < var2 && ArrayList.this.modCount == this.expectedModCount) {
                             var1.accept(ArrayList.elementAt(var4, var3));
                             ++var3;
                         }

                         this.cursor = var3;
                         this.lastRet = var3 - 1;
                         this.checkForComodification();
                     }

                 }

                 final void checkForComodification() {
                     if (ArrayList.this.modCount != this.expectedModCount) {
                         throw new ConcurrentModificationException();
                     }
                 }
             }

             private static class SubList<E> extends AbstractList<E> implements RandomAccess {
                 private final ArrayList<E> root;
                 private final SubList<E> parent;
                 private final int offset;
                 private int size;

                 public SubList(ArrayList<E> var1, int var2, int var3) {
                     this.root = var1;
                     this.parent = null;
                     this.offset = var2;
                     this.size = var3 - var2;
                     this.modCount = var1.modCount;
                 }

                 private SubList(SubList<E> var1, int var2, int var3) {
                     this.root = var1.root;
                     this.parent = var1;
                     this.offset = var1.offset + var2;
                     this.size = var3 - var2;
                     this.modCount = var1.modCount;
                 }

                 public E set(int var1, E var2) {
                     Objects.checkIndex(var1, this.size);
                     this.checkForComodification();
                     Object var3 = this.root.elementData(this.offset + var1);
                     this.root.elementData[this.offset + var1] = var2;
                     return var3;
                 }

                 public E get(int var1) {
                     Objects.checkIndex(var1, this.size);
                     this.checkForComodification();
                     return this.root.elementData(this.offset + var1);
                 }

                 public int size() {
                     this.checkForComodification();
                     return this.size;
                 }

                 public void add(int var1, E var2) {
                     this.rangeCheckForAdd(var1);
                     this.checkForComodification();
                     this.root.add(this.offset + var1, var2);
                     this.updateSizeAndModCount(1);
                 }

                 public E remove(int var1) {
                     Objects.checkIndex(var1, this.size);
                     this.checkForComodification();
                     Object var2 = this.root.remove(this.offset + var1);
                     this.updateSizeAndModCount(-1);
                     return var2;
                 }

                 protected void removeRange(int var1, int var2) {
                     this.checkForComodification();
                     this.root.removeRange(this.offset + var1, this.offset + var2);
                     this.updateSizeAndModCount(var1 - var2);
                 }

                 public boolean addAll(Collection<? extends E> var1) {
                     return this.addAll(this.size, var1);
                 }

                 public boolean addAll(int var1, Collection<? extends E> var2) {
                     this.rangeCheckForAdd(var1);
                     int var3 = var2.size();
                     if (var3 == 0) {
                         return false;
                     } else {
                         this.checkForComodification();
                         this.root.addAll(this.offset + var1, var2);
                         this.updateSizeAndModCount(var3);
                         return true;
                     }
                 }

                 public void replaceAll(UnaryOperator<E> var1) {
                     this.root.replaceAllRange(var1, this.offset, this.offset + this.size);
                 }

                 public boolean removeAll(Collection<?> var1) {
                     return this.batchRemove(var1, false);
                 }

                 public boolean retainAll(Collection<?> var1) {
                     return this.batchRemove(var1, true);
                 }

                 private boolean batchRemove(Collection<?> var1, boolean var2) {
                     this.checkForComodification();
                     int var3 = this.root.size;
                     boolean var4 = this.root.batchRemove(var1, var2, this.offset, this.offset + this.size);
                     if (var4) {
                         this.updateSizeAndModCount(this.root.size - var3);
                     }

                     return var4;
                 }

                 public boolean removeIf(Predicate<? super E> var1) {
                     this.checkForComodification();
                     int var2 = this.root.size;
                     boolean var3 = this.root.removeIf(var1, this.offset, this.offset + this.size);
                     if (var3) {
                         this.updateSizeAndModCount(this.root.size - var2);
                     }

                     return var3;
                 }

                 public Object[] toArray() {
                     this.checkForComodification();
                     return Arrays.copyOfRange(this.root.elementData, this.offset, this.offset + this.size);
                 }

                 public <T> T[] toArray(T[] var1) {
                     this.checkForComodification();
                     if (var1.length < this.size) {
                         return Arrays.copyOfRange(this.root.elementData, this.offset, this.offset + this.size, var1.getClass());
                     } else {
                         System.arraycopy(this.root.elementData, this.offset, var1, 0, this.size);
                         if (var1.length > this.size) {
                             var1[this.size] = null;
                         }

                         return var1;
                     }
                 }

                 public boolean equals(Object var1) {
                     if (var1 == this) {
                         return true;
                     } else if (!(var1 instanceof List)) {
                         return false;
                     } else {
                         boolean var2 = this.root.equalsRange((List)var1, this.offset, this.offset + this.size);
                         this.checkForComodification();
                         return var2;
                     }
                 }

                 public int hashCode() {
                     int var1 = this.root.hashCodeRange(this.offset, this.offset + this.size);
                     this.checkForComodification();
                     return var1;
                 }

                 public int indexOf(Object var1) {
                     int var2 = this.root.indexOfRange(var1, this.offset, this.offset + this.size);
                     this.checkForComodification();
                     return var2 >= 0 ? var2 - this.offset : -1;
                 }

                 public int lastIndexOf(Object var1) {
                     int var2 = this.root.lastIndexOfRange(var1, this.offset, this.offset + this.size);
                     this.checkForComodification();
                     return var2 >= 0 ? var2 - this.offset : -1;
                 }

                 public boolean contains(Object var1) {
                     return this.indexOf(var1) >= 0;
                 }

                 public Iterator<E> iterator() {
                     return this.listIterator();
                 }

                 public ListIterator<E> listIterator(final int var1) {
                     this.checkForComodification();
                     this.rangeCheckForAdd(var1);
                     return new ListIterator<E>() {
                         int cursor = var1;
                         int lastRet = -1;
                         int expectedModCount;

                         {
                             this.expectedModCount = SubList.this.modCount;
                         }

                         public boolean hasNext() {
                             return this.cursor != SubList.this.size;
                         }

                         public E next() {
                             this.checkForComodification();
                             int var1x = this.cursor;
                             if (var1x >= SubList.this.size) {
                                 throw new NoSuchElementException();
                             } else {
                                 Object[] var2 = SubList.this.root.elementData;
                                 if (SubList.this.offset + var1x >= var2.length) {
                                     throw new ConcurrentModificationException();
                                 } else {
                                     this.cursor = var1x + 1;
                                     return var2[SubList.this.offset + (this.lastRet = var1x)];
                                 }
                             }
                         }

                         public boolean hasPrevious() {
                             return this.cursor != 0;
                         }

                         public E previous() {
                             this.checkForComodification();
                             int var1x = this.cursor - 1;
                             if (var1x < 0) {
                                 throw new NoSuchElementException();
                             } else {
                                 Object[] var2 = SubList.this.root.elementData;
                                 if (SubList.this.offset + var1x >= var2.length) {
                                     throw new ConcurrentModificationException();
                                 } else {
                                     this.cursor = var1x;
                                     return var2[SubList.this.offset + (this.lastRet = var1x)];
                                 }
                             }
                         }

                         public void forEachRemaining(Consumer<? super E> var1x) {
                             Objects.requireNonNull(var1x);
                             int var2 = SubList.this.size;
                             int var3 = this.cursor;
                             if (var3 < var2) {
                                 Object[] var4 = SubList.this.root.elementData;
                                 if (SubList.this.offset + var3 >= var4.length) {
                                     throw new ConcurrentModificationException();
                                 }

                                 while(var3 < var2 && SubList.this.root.modCount == this.expectedModCount) {
                                     var1x.accept(ArrayList.elementAt(var4, SubList.this.offset + var3));
                                     ++var3;
                                 }

                                 this.cursor = var3;
                                 this.lastRet = var3 - 1;
                                 this.checkForComodification();
                             }

                         }

                         public int nextIndex() {
                             return this.cursor;
                         }

                         public int previousIndex() {
                             return this.cursor - 1;
                         }

                         public void remove() {
                             if (this.lastRet < 0) {
                                 throw new IllegalStateException();
                             } else {
                                 this.checkForComodification();

                                 try {
                                     SubList.this.remove(this.lastRet);
                                     this.cursor = this.lastRet;
                                     this.lastRet = -1;
                                     this.expectedModCount = SubList.this.modCount;
                                 } catch (IndexOutOfBoundsException var2) {
                                     throw new ConcurrentModificationException();
                                 }
                             }
                         }

                         public void set(E var1x) {
                             if (this.lastRet < 0) {
                                 throw new IllegalStateException();
                             } else {
                                 this.checkForComodification();

                                 try {
                                     SubList.this.root.set(SubList.this.offset + this.lastRet, var1x);
                                 } catch (IndexOutOfBoundsException var3) {
                                     throw new ConcurrentModificationException();
                                 }
                             }
                         }

                         public void add(E var1x) {
                             this.checkForComodification();

                             try {
                                 int var2 = this.cursor;
                                 SubList.this.add(var2, var1x);
                                 this.cursor = var2 + 1;
                                 this.lastRet = -1;
                                 this.expectedModCount = SubList.this.modCount;
                             } catch (IndexOutOfBoundsException var3) {
                                 throw new ConcurrentModificationException();
                             }
                         }

                         final void checkForComodification() {
                             if (SubList.this.root.modCount != this.expectedModCount) {
                                 throw new ConcurrentModificationException();
                             }
                         }
                     };
                 }

                 public List<E> subList(int var1, int var2) {
                     subListRangeCheck(var1, var2, this.size);
                     return new SubList(this, var1, var2);
                 }

                 private void rangeCheckForAdd(int var1) {
                     if (var1 < 0 || var1 > this.size) {
                         throw new IndexOutOfBoundsException(this.outOfBoundsMsg(var1));
                     }
                 }

                 private String outOfBoundsMsg(int var1) {
                     return "Index: " + var1 + ", Size: " + this.size;
                 }

                 private void checkForComodification() {
                     if (this.root.modCount != this.modCount) {
                         throw new ConcurrentModificationException();
                     }
                 }

                 private void updateSizeAndModCount(int var1) {
                     SubList var2 = this;

                     do {
                         var2.size += var1;
                         var2.modCount = this.root.modCount;
                         var2 = var2.parent;
                     } while(var2 != null);

                 }

                 public Spliterator<E> spliterator() {
                     this.checkForComodification();
                     return new Spliterator<E>() {
                         private int index;
                         private int fence;
                         private int expectedModCount;

                         {
                             this.index = SubList.this.offset;
                             this.fence = -1;
                         }

                         private int getFence() {
                             int var1;
                             if ((var1 = this.fence) < 0) {
                                 this.expectedModCount = SubList.this.modCount;
                                 var1 = this.fence = SubList.this.offset + SubList.this.size;
                             }

                             return var1;
                         }

                         public ArrayList<E>.ArrayListSpliterator trySplit() {
                             int var1 = this.getFence();
                             int var2 = this.index;
                             int var3 = var2 + var1 >>> 1;
                             ArrayListSpliterator var10000;
                             if (var2 >= var3) {
                                 var10000 = null;
                             } else {
                                 ArrayList var10002 = SubList.this.root;
                                 Objects.requireNonNull(var10002);
                                 var10000 = var10002.new ArrayListSpliterator(var2, this.index = var3, this.expectedModCount);
                             }

                             return var10000;
                         }

                         public boolean tryAdvance(Consumer<? super E> var1) {
                             Objects.requireNonNull(var1);
                             int var2 = this.getFence();
                             int var3 = this.index;
                             if (var3 < var2) {
                                 this.index = var3 + 1;
                                 Object var4 = SubList.this.root.elementData[var3];
                                 var1.accept(var4);
                                 if (SubList.this.root.modCount != this.expectedModCount) {
                                     throw new ConcurrentModificationException();
                                 } else {
                                     return true;
                                 }
                             } else {
                                 return false;
                             }
                         }

                         public void forEachRemaining(Consumer<? super E> var1) {
                             Objects.requireNonNull(var1);
                             ArrayList var5 = SubList.this.root;
                             Object[] var6;
                             if ((var6 = var5.elementData) != null) {
                                 int var3;
                                 int var4;
                                 if ((var3 = this.fence) < 0) {
                                     var4 = SubList.this.modCount;
                                     var3 = SubList.this.offset + SubList.this.size;
                                 } else {
                                     var4 = this.expectedModCount;
                                 }

                                 int var2;
                                 if ((var2 = this.index) >= 0 && (this.index = var3) <= var6.length) {
                                     while(var2 < var3) {
                                         Object var7 = var6[var2];
                                         var1.accept(var7);
                                         ++var2;
                                     }

                                     if (var5.modCount == var4) {
                                         return;
                                     }
                                 }
                             }

                             throw new ConcurrentModificationException();
                         }

                         public long estimateSize() {
                             return (long)(this.getFence() - this.index);
                         }

                         public int characteristics() {
                             return 16464;
                         }
                     };
                 }
             }

             final class ArrayListSpliterator implements Spliterator<E> {
                 private int index;
                 private int fence;
                 private int expectedModCount;

                 ArrayListSpliterator(int var2, int var3, int var4) {
                     this.index = var2;
                     this.fence = var3;
                     this.expectedModCount = var4;
                 }

                 private int getFence() {
                     int var1;
                     if ((var1 = this.fence) < 0) {
                         this.expectedModCount = ArrayList.this.modCount;
                         var1 = this.fence = ArrayList.this.size;
                     }

                     return var1;
                 }

                 public ArrayList<E>.ArrayListSpliterator trySplit() {
                     int var1 = this.getFence();
                     int var2 = this.index;
                     int var3 = var2 + var1 >>> 1;
                     return var2 >= var3 ? null : ArrayList.this.new ArrayListSpliterator(var2, this.index = var3, this.expectedModCount);
                 }

                 public boolean tryAdvance(Consumer<? super E> var1) {
                     if (var1 == null) {
                         throw new NullPointerException();
                     } else {
                         int var2 = this.getFence();
                         int var3 = this.index;
                         if (var3 < var2) {
                             this.index = var3 + 1;
                             Object var4 = ArrayList.this.elementData[var3];
                             var1.accept(var4);
                             if (ArrayList.this.modCount != this.expectedModCount) {
                                 throw new ConcurrentModificationException();
                             } else {
                                 return true;
                             }
                         } else {
                             return false;
                         }
                     }
                 }

                 public void forEachRemaining(Consumer<? super E> var1) {
                     if (var1 == null) {
                         throw new NullPointerException();
                     } else {
                         Object[] var5;
                         if ((var5 = ArrayList.this.elementData) != null) {
                             int var3;
                             int var4;
                             if ((var3 = this.fence) < 0) {
                                 var4 = ArrayList.this.modCount;
                                 var3 = ArrayList.this.size;
                             } else {
                                 var4 = this.expectedModCount;
                             }

                             int var2;
                             if ((var2 = this.index) >= 0 && (this.index = var3) <= var5.length) {
                                 while(var2 < var3) {
                                     Object var6 = var5[var2];
                                     var1.accept(var6);
                                     ++var2;
                                 }

                                 if (ArrayList.this.modCount == var4) {
                                     return;
                                 }
                             }
                         }

                         throw new ConcurrentModificationException();
                     }
                 }

                 public long estimateSize() {
                     return (long)(this.getFence() - this.index);
                 }

                 public int characteristics() {
                     return 16464;
                 }
             }
         }
    """.trimIndent()
