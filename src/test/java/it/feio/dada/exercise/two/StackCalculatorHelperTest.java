package it.feio.dada.exercise.two;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Created by Federico Iosue on 16/09/2016.
 */
public class StackCalculatorHelperTest {

	@Test
	public void testCalculateProduct() {
		assertArrayEquals(StackCalculatorHelper.calculateProduct(13, 2), new Integer[]{6, 2});
		assertArrayEquals(StackCalculatorHelper.calculateProduct(137, 1232), new Integer[]{4, 8, 7, 8, 6, 1});
		assertArrayEquals(StackCalculatorHelper.calculateProduct(137, 12, 7), new Integer[]{8, 0, 5, 1, 1});
	}


	@Test
	public void testNumberToArray() {
		Integer[] result = StackCalculatorHelper.numberToArray(42);
		assertNotNull(result);
		assertArrayEquals(result, new Integer[]{2, 4});
	}


	@Test
	public void testSumArrays() {
		Integer[] result = StackCalculatorHelper.sumArrays(new Integer[]{1, 8, 9, 1}, new Integer[]{5, 3});
		assertNotNull(result);
		assertArrayEquals(result, new Integer[]{6, 1, 0, 2});

		result = StackCalculatorHelper.sumArrays(new Integer[]{1, 3, 6}, new Integer[]{1, 7});
		assertNotNull(result);
		assertArrayEquals(result, new Integer[]{2, 0, 7});
	}


	@Test
	public void testMultiplyArrays() {
		Integer[] result = StackCalculatorHelper.multiplyArrays(new Integer[]{3}, new Integer[]{5});
		assertNotNull(result);
		assertArrayEquals(result, new Integer[]{5, 1});

		result = StackCalculatorHelper.multiplyArrays(new Integer[]{8, 1}, new Integer[]{4});
		assertNotNull(result);
		assertArrayEquals(result, new Integer[]{2, 7});

		result = StackCalculatorHelper.multiplyArrays(new Integer[]{8, 1}, new Integer[]{4, 1, 1});
		assertNotNull(result);
		assertArrayEquals(result, new Integer[]{2, 5, 0, 2});

		result = StackCalculatorHelper.multiplyArrays(new Integer[]{7, 3, 1}, new Integer[]{2, 3, 2, 1});
		assertNotNull(result);
		assertArrayEquals(result, new Integer[]{4, 8, 7, 8, 6, 1});
	}

}
