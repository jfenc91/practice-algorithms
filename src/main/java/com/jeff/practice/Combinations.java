package com.jeff.practice;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * These methods relate to finding all combinations of size k in a 
 * set of n elements 
 */
public class Combinations {

  /**
   * Binomial Coefficient
   * 
   * @param n The total number of elements
   * @param k the number of elements to choose
   * @return the value
   */
  public static double binomialCoefficient(int n, int k) {
    double ret = 1;
    for (int i = n - k + 1; i <= n; i++) {
      ret *= i;
    }
    ret /= factorial(k);
    return ret;
  }


  /**
   * Enumerates the k-combinations of the provided elements
   * @param elements A collection of elements
   * @param k The number of elements to choose for each combination
   * @return A list of all the possible combinations of the given length
   */
  public static <T> Collection<List<T>> getAllCombinations(
      Collection<T> elements, int k) {
    if (elements.size() < k) {
      throw new InvalidParameterException(
          "Elements must have a size greater than k");
    }

    List<List<T>> allCombinations = new ArrayList<List<T>>();
    if (k < 1) {
      throw new InvalidParameterException("k cannot be less than 1");
    } else if (k == 1) {
      //There are size(elements) number of 1-combinations
      for (T item : elements) {
        LinkedList<T> combinations = new LinkedList<T>();
        combinations.add(item);
        allCombinations.add(combinations);
      }
    } else {
      //make sure there are still enough elements to make a k-combination
      while (elements.size() > k - 1) {      
        LinkedList<T> elementsCopy = new LinkedList<>(elements);
        T selectedElement = elementsCopy.pop();
        elements = elementsCopy;

        Collection<List<T>> combinations = getAllCombinations(
            elementsCopy, k - 1);

        for (List<T> list : combinations) {
          list.add(selectedElement);
        }
        allCombinations.addAll(combinations);
      }
    }

    return allCombinations;
  }

  /**
   * Factorial function. This will not handle big numbers well.
   * 
   * @param n the number
   * @return the result
   */
  public static long factorial(long n) {
    long ret = 1;
    for (long i = 2; i <= n; i++) {
      ret *= i;
    }
    return ret;
  }
}
