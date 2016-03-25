package matrix;
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
	public static byte[] rotate(byte[] array) {
		int len = array.length;
		byte[] r = new byte[len];
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
	public static byte[][] copy(byte[][] inputImage) {
		byte[][] newArray = new byte[inputImage.length][];
		for (int i = 0; i < inputImage.length; i++) {
			newArray[i] = new byte[inputImage[i].length];
			for (int j = 0; j < inputImage[i].length; j++) {
				newArray[i][j] = inputImage[i][j];
			}
		}
		return newArray;
	}

	/**
	 * Generates a random two dimensional matrix (to serve as an input image)
	 * @param rows Number of rows
	 * @param cols Number of columns
	 * @param bound Max value 
	 * @return Randomly generated two dimensional matrix
	 */
	public static byte[][] generateImage(int rows, int cols, int bound) {
		byte[][] image = new byte[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				image[i][j] = (byte) rand.nextInt(bound);
			}
		}
		return image;
	}

	/**
	 * Generates random one dimensional array
	 * @param size The size of the array
	 * @param bound Max value
	 * @return Randomly generated one dimensional array
	 */
	public static byte[] generateRandomArray(int size, int bound) {
		byte[] array = new byte[size];
		for (int i = 0; i < size; i++) {
			array[i] = (byte) rand.nextInt(bound);
		}
		return array;
	}
	
	/**
	 * Prints a two dimensional matrix
	 * @param inputImage 
	 */
	public static void printArray(byte[][] inputImage) {
		for (int i = 0; i < inputImage.length; i++) {
			for (int j = 0; j < inputImage[i].length; j++) {
				System.out.print(inputImage[i][j] + " ");
			}
			System.out.println();
		}
	}
}
