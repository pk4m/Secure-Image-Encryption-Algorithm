package matrix;
/**
 * 
 * @author Pandurang Kamath
 * This class serves as a demo for the Encryption Algorithm Based on Rubik's Cube Principle by Khaled Loukhaoukha, Jean-Yves Chouinard, and Abdellah Berdai
 * Vectors kR, kC and the max iteration number ITER_MAX are considered as secret keys in the proposed encryption algorithm
 *
 */
public class Main {

	/**
	 * M x N specify the size of the image (here we represent an image as a matrix)
	 */
	public static final int M = 5;
	public static final int N = 10;

	/**
	 * PIXEL_SIZE is the size of each pixel of the image (8-bit pixel in the demo)
	 * PIXEL_MAX_VALUE is the number of possible values we could have with a pixel of PIXEL_SIZE 
	 */
	public static final int PIXEL_SIZE = 8;
	public static final int PIXEL_MAX_VALUE = (int) Math.pow(2, PIXEL_SIZE);
	
	/**
	 * ITER_MAX is the number of iterations to be used by the algorithm. The more the number of iterations, the more secure the data becomes. 
	 */
	public static final int ITER_MAX = 10;
	
	/**
	 * 
	 * @param args
	 * Main method
	 * 
	 */
	public static void main(String[] args) {
		// Generate a random number matrix
		byte[][] inputImage = Utils.generateImage(M, N, PIXEL_MAX_VALUE);
		
		// Generate vectors of size M and N
		byte[] kR = Utils.generateRandomArray(M, PIXEL_MAX_VALUE);
		byte[] kC = Utils.generateRandomArray(N, PIXEL_MAX_VALUE);

		// Print the input array
		System.out.println("Original Image");
		System.out.println("--------------");
		Utils.printArray(inputImage);System.out.println();
		
		// Encryption is done by scrambling and XORing the image ITER_MAX times
		for (int i = 0; i < ITER_MAX; i++) {
			inputImage = new Scrambler(inputImage, kR, kC).scramble();
			inputImage = new Encrypt().encrypt(inputImage, kR, kC);
		}
		System.out.println("Encrypted Image");
		System.out.println("---------------");
		Utils.printArray(inputImage);System.out.println();
		
		// Decryption is done by XORing and unscrambling the image ITER_MAX times
		for (int i = 0; i < ITER_MAX; i++) {
			inputImage = new Decrypt().decrypt(inputImage, kR, kC);
			inputImage = new Scrambler(inputImage, kR, kC).unscramble();
		}
		System.out.println("Decrypted Image");
		System.out.println("---------------");
		Utils.printArray(inputImage);System.out.println();

	}

}
