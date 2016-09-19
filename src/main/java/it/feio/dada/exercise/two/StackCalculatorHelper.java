package it.feio.dada.exercise.two;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Created by Federico Iosue on 16/09/2016.
 */
public class StackCalculatorHelper {

	/**
	 * Calculates product of an arbitrary number of multiplicand by converting them to integer arrays and using
	 * addition operator only.
	 *
	 * @param multiplicand Arbitrary number of number to operate on
	 * @return product as integers array
	 */
	public static Integer[] calculateProduct(Integer... multiplicand) {
		List<Integer[]> multiplicandArrays = numbersToArray(multiplicand);
		Integer[] product = new Integer[]{1};
		for (Integer[] multiplicandArray : multiplicandArrays) {
			product = multiplyArrays(product, multiplicandArray);
		}
		return product;
	}


	static List<Integer[]> numbersToArray(Integer... numbers) {
		List<Integer[]> numberArray = new ArrayList<Integer[]>();
		for (int number : numbers) {
			numberArray.add(numberToArray(number));
		}
		return numberArray;
	}


	static Integer[] numberToArray(Integer number) {
		List<Integer> listNumber = new ArrayList<Integer>();
		for (String s : String.valueOf(number).split("")) {
			listNumber.add(Integer.valueOf(s));
		}
		Collections.reverse(listNumber);
		return listNumber.toArray(new Integer[listNumber.size()]);
	}


	static Integer[] multiplyArrays(Integer[] addendOne, Integer[] addendTwo) {
		Integer[] totalSum = new Integer[]{};
		for (int i = 0; i < addendOne.length; i++) {
			Integer[] sum = new Integer[]{};
			for (Integer j = 0; j < addendOne[i]; j++) {
				sum = sumArrays(sum, addendTwo);
			}
			if (i > 0) {
				Integer[] newArray = new Integer[sum.length + i];
				System.arraycopy(sum, 0, newArray, i, sum.length);
				Arrays.fill(newArray, 0, i, 0);
				totalSum = sumArrays(newArray, totalSum);
			} else {
				totalSum = sum;
			}
		}
		return totalSum;
	}


	static Integer[] sumArrays(Integer[] addendOne, Integer[] addendTwo) {
		List<Integer> result = new ArrayList<Integer>();
		Integer[] shorter;
		Integer[] longer;
		if (addendOne.length <= addendTwo.length) {
			shorter = Arrays.copyOf(addendOne, addendTwo.length);
			longer = addendTwo;
		} else {
			shorter = Arrays.copyOf(addendTwo, addendOne.length);
			longer = addendOne;
		}
		Arrays.fill(shorter, shorter.length - Math.abs(addendOne.length - addendTwo.length), longer.length, 0);
		int carry = 0;
		for (int i = 0; i < shorter.length; i++) {
			int sum = shorter[i] + longer[i];
			result.add((sum + carry) % 10);
			carry = (sum + carry) / 10;
		}
		if (carry > 0) {
			result.add(carry);
		}
		return result.toArray(new Integer[result.size()]);
	}
}
