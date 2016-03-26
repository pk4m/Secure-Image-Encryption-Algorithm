

import java.io.File;

/**
 * 
 * @author Pandurang Kamath
 * This class serves as a demo for the Encryption Algorithm Based on Rubik's Cube Principle by Khaled Loukhaoukha, Jean-Yves Chouinard, and Abdellah Berdai
 * Vectors kR, kC and the max iteration number ITER_MAX are considered as secret keys in the proposed encryption algorithm
 *
 */
public class ImageDemo {
	
	static final String FILE_EXT = ".png";
	static final String KEY_EXT = ".key";
	static final String ENC = "enc";
	static final String DEC = "dec";
	/**
	 * PIXEL_SIZE is the size of each pixel of the image 
	 * PIXEL_MAX_VALUE is the number of possible values we could have with a pixel of PIXEL_SIZE
	 */
	public static final int PIXEL_SIZE = 32;
	public static final int PIXEL_MAX_VALUE = (int) Math.pow(2, PIXEL_SIZE);

	/**
	 * maxIterations is the number of iterations to be used by the algorithm. The more the number of iterations, the more secure the data becomes.
	 */
	public static int maxIterations = 1;
	
	public static void main(String[] args) throws Exception {
		checkCommandValid(args);
		String command = args[0];
		checkInputFile(new File(args[1]));
		File inputFile = new File(new File(args[1]).getAbsolutePath());
		
		String fileDir = inputFile.getParent() + File.separator;
		
		int[][] inputImage = ImageUtils.compute(inputFile);
		int M = inputImage.length;
		int N = inputImage[0].length;
		int[] kR;
		int[] kC;
		
		File keyFile;
		
		if(command.equals(DEC)) {
			keyFile = new File(args[2]);
			checkInputFile(keyFile);
			kR = new int[M];
			kC = new int[N];
			maxIterations = KeyUtils.readKey(keyFile, kR, kC);
			for (int i = 0; i < maxIterations; i++) {
				inputImage = new Decrypt().decrypt(inputImage, kR, kC);
				inputImage = new Scrambler(inputImage, kR, kC).unscramble();
			}
			File decFile = new File(fileDir + inputFile.getName() + DEC + FILE_EXT);
			ImageUtils.saveImage(inputImage, decFile);
		} else {
			keyFile = new File(fileDir + inputFile.getName() + KEY_EXT);
			kR = Utils.generateRandomArray(M, PIXEL_MAX_VALUE);
			kC = Utils.generateRandomArray(N, PIXEL_MAX_VALUE);
			KeyUtils.writeKey(keyFile, kR, kC, maxIterations);
			for (int i = 0; i < maxIterations; i++) {
				inputImage = new Scrambler(inputImage, kR, kC).scramble();
				inputImage = new Encrypt().encrypt(inputImage, kR, kC);
			}
			File encFile = new File(fileDir + inputFile.getName() + ENC + FILE_EXT);
			ImageUtils.saveImage(inputImage, encFile);
		}
		
	}
	
	private static void checkCommandValid(String[] args) {
		if(args.length == 0) {
			printUsage();
		}
		String command = args[0];
		if (command.equals(ENC)) {
			if (args.length < 2) {
				printUsage();
			}
		} else if (command.equals(DEC)) {
			if (args.length < 3) {
				printUsage();
			}
		} else {
			printUsage();
		}
	}
	
	private static void checkInputFile(File file) {
		if(file.exists() && file.isFile()) {
			return;
		}
		System.out.println("Invalid file: " + file.getName());
	}
	
	private static void printUsage() {
		System.out.println("Usage: [enc filename|dec filename keyfile]");
		System.exit(1);
	}
}
