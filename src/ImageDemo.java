

import java.io.File;

/**
 * 
 * @author Pandurang Kamath
 * This class serves as a demo for the Encryption Algorithm Based on Rubik's Cube Principle by Khaled Loukhaoukha, Jean-Yves Chouinard, and Abdellah Berdai
 * Vectors kR, kC and the max iteration number ITER_MAX are considered as secret keys in the proposed encryption algorithm
 *
 */
public class ImageDemo {
	
	static final String filePath = "/home/pandu/Downloads/";
	static final String fileExt = ".jpg";
	static final String inputFileName = "dandelion";
	static final String inputFile = filePath + inputFileName + fileExt;

	/**
	 * PIXEL_SIZE is the size of each pixel of the image 
	 * PIXEL_MAX_VALUE is the number of possible values we could have with a pixel of PIXEL_SIZE
	 */
	public static final int PIXEL_SIZE = 32;
	public static final int PIXEL_MAX_VALUE = (int) Math.pow(2, PIXEL_SIZE);

	/**
	 * ITER_MAX is the number of iterations to be used by the algorithm. The more the number of iterations, the more secure the data becomes.
	 */
	public static final int ITER_MAX = 1;

	public static void main(String[] args) {
		
		int[][] inputImage = ImageUtils.compute(new File(inputFile));

		int M = inputImage.length;
		int N = inputImage[0].length;

		// Generate vectors of size M and N
		int[] kR = Utils.generateRandomArray(M, PIXEL_MAX_VALUE);
		int[] kC = Utils.generateRandomArray(N, PIXEL_MAX_VALUE);

		// Encryption is done by scrambling and XORing the image ITER_MAX times
		for (int i = 0; i < ITER_MAX; i++) {
			inputImage = new Scrambler(inputImage, kR, kC).scramble();
			inputImage = new Encrypt().encrypt(inputImage, kR, kC);
		}

		File encFile = new File(filePath+inputFileName+"-enc"+fileExt);
		ImageUtils.saveImage(inputImage, encFile);

		// Decryption is done by XORing and unscrambling the image ITER_MAX times
		for (int i = 0; i < ITER_MAX; i++) {
			inputImage = new Decrypt().decrypt(inputImage, kR, kC);
			inputImage = new Scrambler(inputImage, kR, kC).unscramble();
		}

		File decFile = new File(filePath+inputFileName+"-dec"+fileExt);
		ImageUtils.saveImage(inputImage, decFile);

	}
}
