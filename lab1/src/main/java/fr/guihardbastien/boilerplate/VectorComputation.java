package fr.guihardbastien.boilerplate;


import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;

public class VectorComputation {
    private static final VectorSpecies<Integer> SPECIES = IntVector.SPECIES_PREFERRED;

    public static int sum(int[] array) {
        var length = array.length;
        var loopBound = length - length % SPECIES.length();
        var v0 = IntVector.zero(SPECIES);
        var i = 0;
        for (; i < loopBound; i += SPECIES.length()) {
            var v = IntVector.fromArray(SPECIES, array, i);
            v0 = v0.add(v);
        }
        int sum = v0.reduceLanes(VectorOperators.ADD);
        for (; i < length; i++) {
            sum += array[i];
        }
        return sum;
    }

    public static int sumMask(int[] array) {
        var length = array.length;
        var loopBound = length - length % SPECIES.length();
        var v0 = IntVector.zero(SPECIES);
        var i = 0;
        for (; i < loopBound; i += SPECIES.length()) {
            var v = IntVector.fromArray(SPECIES, array, i);
            v0 = v0.add(v);
        }
        int sum = v0.reduceLanes(VectorOperators.ADD);

        var mask = SPECIES.indexInRange(i, length);
        var vMask = IntVector.fromArray(SPECIES, array, i, mask);
        int sumMask = vMask.reduceLanes(VectorOperators.ADD);

        return sum + sumMask;
    }

    public static int min(int[] array) {
        //TOTO
        var length = array.length;
        var loopBound = length - length % SPECIES.length();

        var v0 = IntVector.zero(SPECIES); //fixme

        var i = 0;
        for (; i < loopBound; i += SPECIES.length()) {
            var v = IntVector.fromArray(SPECIES, array, i);
            v0.min(v);
        }
        int min = v0.reduceLanes(VectorOperators.MIN);

        for (; i < length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }

    public static int minMask(int[] array) {
        //TODO
        var length = array.length;
        var loopBound = length - length % SPECIES.length();

        var v0 = IntVector.zero(SPECIES); //fixme

        var i = 0;
        for (; i < loopBound; i += SPECIES.length()) {
            var v = IntVector.fromArray(SPECIES, array, i);
            v0.min(v);
        }
        int min = v0.reduceLanes(VectorOperators.MIN);

        var mask = SPECIES.indexInRange(i, length);
        var vMask = IntVector.fromArray(SPECIES, array, i, mask);
        int minMask = vMask.reduceLanes(VectorOperators.MIN);

        return min + minMask;
    }
}

