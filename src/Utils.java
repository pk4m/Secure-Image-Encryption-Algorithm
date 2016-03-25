
import java.security.SecureRandom;

/**
 * 
 * @author Pandurang Kamath
 * Commonly used utilities
 *
 */
public class Utils {
	public static SecureRandom rand = new SecureRandom();

	/**
	 * Rotates(flips) the array elements 
	 * @param array The array to rotate
	 * @return The rotated array
	 */
	public static int[] rotate(int[] array) {
		int len = array.length;
		int[] r = new int[len];
		for (int i = 0; i < len; i++) {
			r[len - i - 1] = array[i];
		}
		return r;
	}

	/**
	 * Makes a copy of the input array
	 * @param inputImage The array to be copied
	 * @return The copied array
	 */
	public static int[][] copy(int[][] inputImage) {
		int[][] newArray = new int[inputImage.length][];
		for (int i = 0; i < inputImage.length; i++) {
			newArray[i] = new int[inputImage[i].length];
			for (int j = 0; j < inputImage[i].length; j++) {
				newArray[i][j] = inputImage[i][j];
			}
		}
		return newArray;
	}
	
	/**
	 * Generates random one dimensional array
	 * @param size The size of the array
	 * @param bound Max value
	 * @return Randomly generated one dimensional array
	 */
	public static int[] generateRandomArray(int size, int bound) {
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = rand.nextInt(bound);
		}
		return array;
	}
	
	
}
