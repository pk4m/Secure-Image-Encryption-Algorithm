import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Utility to read and write key
 * 
 * @author Pandurang Kamath
 *
 */
public class KeyUtils {
	public static int readKey(File keyFile, int[] kR, int[] kC) throws Exception {
		Scanner sc = new Scanner(keyFile);
		String kRLine = sc.nextLine();
		String[] kArray = kRLine.split(" ");
		if ((kR.length+kC.length+1) != kArray.length) {
			sc.close();
			throw new Exception("Invalid key file");
		}
		int j = 0;
		for (int i = 0; i < kR.length; i++, j++) {
			kR[i] = Integer.parseInt(kArray[j]);
		}
		for (int i = 0; i < kC.length; i++, j++) {
			kC[i] = Integer.parseInt(kArray[j]);
		}
		int iter = Integer.parseInt(kArray[j]);
		sc.close();
		return iter;
	}

	public static void writeKey(File keyFile, int[] kR, int[] kC, int iterations) throws IOException {
		FileOutputStream fos = new FileOutputStream(keyFile);
		StringBuilder s = new StringBuilder();
		for (int i: kR) {
			s.append(i + " ");
		}
		for (int i: kC) {
			s.append(i + " ");
		}
		s.append(iterations);
		fos.write(new String(s).getBytes(), 0, s.length());
		fos.close();
	}
}
