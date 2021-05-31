package fr.guihardbastien.boilerplate;

import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorMask;
import jdk.incubator.vector.VectorSpecies;

import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
        VectorSpecies<Integer> species = IntVector.SPECIES_PREFERRED;
        var arr = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
        var b = new int[8];

        IntVector vectorizedArray = IntVector.fromArray(species, arr, 0);
        IntVector conditionVector = IntVector.broadcast(species, 6);
        IntVector addTermVector = IntVector.broadcast(species, 10);
        VectorMask<Integer> mask =  vectorizedArray.lt(conditionVector); // lt -> lower than
        IntVector vb = vectorizedArray.add(addTermVector, mask);
        vb.intoArray(b, 0);
        System.out.println(Arrays.toString(b));

    }
}

