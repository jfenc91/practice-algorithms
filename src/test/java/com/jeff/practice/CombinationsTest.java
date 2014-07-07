package com.jeff.practice;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class CombinationsTest {
  @Test
  public void BinomialCoefficient_N15K5_3003() {
    assertEquals(3003, Combinations.binomialCoefficient(15, 5), 1e-5);
  }

  @Test(expected = InvalidParameterException.class)
  public void Combinations_ElementsSize0_InvalidParameter() {
    testGenerateCombinations(0, 66);
  }
  
  @Test(expected = InvalidParameterException.class)
  public void Combinations_Kis0_InvalidParameter() {
    testGenerateCombinations(66, 0);
  }
  
  @Test(expected = InvalidParameterException.class)
  public void Combinations_NegativeR_InvalidParameter() {
    testGenerateCombinations(66, -5);
  }
  
  @Test
  public void Combinations_N20K1_Unique() {
    testGenerateCombinations(20, 1);
  }
  
  @Test
  public void Combinations_N15K5_Unique() {
    testGenerateCombinations(15, 5);
  }

  @Test
  public void Combinations_N20K3_Unique() {
    testGenerateCombinations(20, 3);
  }

  private void testGenerateCombinations(int n, int k) {
    double expected = Combinations.binomialCoefficient(n, k);

    //generate elements specified by n
    LinkedList<Integer> elements = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      elements.add(i);
    }

    Collection<List<Integer>> combos = Combinations
        .getAllCombinations(elements, k);

    assertEquals("The number of combinations produced should be equal"
        + " to the binomial coefficient", expected, combos.size(), 1e-2);

    Set<Set<Integer>> combosSet = new HashSet<Set<Integer>>();
    for (List<Integer> entry : combos) {
      Set<Integer> set = new HashSet<Integer>();
      for (Integer i : entry) {
        set.add(i);
      }
      assertEquals("All elements in each combination should be unique",
          set.size(), entry.size());
      combosSet.add(set);
    }
    
    //if combinations are unique the list of lists will be the 
    //same size as the set of sets
    assertEquals("All lists should be unique", combosSet.size(),
        combos.size());
  }
}
