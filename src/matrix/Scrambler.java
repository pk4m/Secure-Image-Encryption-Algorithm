package matrix;
/**
 * 
 * @author Pandurang Kamath
 * Scrambles or unscrambles the input matrix using the principles of a Rubik's Cube
 *
 */
public class Scrambler {

	private byte[][] inputImage;
	private byte[] kR;
	private byte[] kC;
	private int M, N;

	/**
	 * 
	 * @param iImage input matrix
	 * @param kR secret key for rows
	 * @param kC secret key for columns
	 */
	public Scrambler(byte[][] iImage, byte[] kR, byte[] kC) {
		inputImage = iImage;
		this.kR = kR;
		this.kC = kC;
		M = inputImage.length;
		N = inputImage[0].length;
	}

	/**
	 * Scrambles the input matrix
	 * @return the scrambled matrix
	 */
	public byte[][] scramble() {

		// scramble rows
		for (int i = 0; i < M; i++) {
			int sumI = 0;
			for (int j = 0; j < N; j++) {
				sumI += inputImage[i][j];
			}
			if (sumI % 2 == 0) {
				shiftRowRight(i);
			} else {
				shiftRowLeft(i);
			}
		}

		// scramble columns
		for (int j = 0; j < N; j++) {
			int sumJ = 0;
			for (int i = 0; i < M; i++) {
				sumJ += inputImage[i][j];
			}
			if (sumJ % 2 == 0) {
				shiftColumnUp(j);
			} else {
				shiftColumnDown(j);
			}
		}

		return inputImage;
	}
	
	/**
	 * Unscrambles the input matrix
	 * @return the unscrambled matrix
	 */
	public byte[][] unscramble() {

		// unscramble columns
		for (int j = 0; j < N; j++) {
			int sumJ = 0;
			for (int i = 0; i < M; i++) {
				sumJ += inputImage[i][j];
			}
			if (sumJ % 2 == 0) {
				shiftColumnDown(j);
			} else {
				shiftColumnUp(j);
			}
		}

		// unscramble rows
		for (int i = 0; i < M; i++) {
			int sumI = 0;
			for (int j = 0; j < N; j++) {
				sumI += inputImage[i][j];
			}
			if (sumI % 2 == 0) {
				shiftRowLeft(i);
			} else {
				shiftRowRight(i);
			}
		}

		return inputImage;
	}

	/**
	 * Shifts the elements column wise down by kC positions 
	 * @param colNum The column to shift down
	 */
	private void shiftColumnDown(int colNum) {
		int shiftBy = Math.abs(kC[colNum] % M);
		byte[] temp = new byte[M];
		for (int i = 0; i < M; i++) {
			temp[i] = inputImage[(i - shiftBy + M) % M][colNum];
		}
		// copy
		for (int i = 0; i < M; i++) {
			inputImage[i][colNum] = temp[i];
		}
	}

	/**
	 * Shifts the elements column wise up by kC positions 
	 * @param colNum The column to shift up
	 */
	private void shiftColumnUp(int colNum) {
		int shiftBy = Math.abs(kC[colNum] % M);
		byte[] temp = new byte[M];
		for (int i = 0; i < M; i++) {
			temp[i] = inputImage[(i + shiftBy) % M][colNum];
		}
		// copy
		for (int i = 0; i < M; i++) {
			inputImage[i][colNum] = temp[i];
		}
	}

	/**
	 * Shifts the elements row wise left by kR positions 
	 * @param rowNum The row to shift left
	 */
	private void shiftRowLeft(int rowNum) {
		int shiftBy = Math.abs(kR[rowNum] % N);
		byte[] temp = new byte[N];
		for (int i = 0; i < N; i++) {
			temp[i] = inputImage[rowNum][(i + shiftBy) % N];
		}
		// copy
		for (int i = 0; i < N; i++) {
			inputImage[rowNum][i] = temp[i];
		}
	}

	/**
	 * Shifts the elements row wise right by kR positions 
	 * @param rowNum The row to shift right
	 */
	private void shiftRowRight(int rowNum) {
		int shiftBy = Math.abs(kR[rowNum] % N);
		byte[] temp = new byte[N];
		for (int i = 0; i < N; i++) {
			temp[i] = inputImage[rowNum][(i - shiftBy + N) % N];
		}
		// copy
		for (int i = 0; i < N; i++) {
			inputImage[rowNum][i] = temp[i];
		}
	}

}
