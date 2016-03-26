
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * 
 * @author Pandurang Kamath
 * Image utilty class
 *
 */
public class ImageUtils {

	/**
	 * 
	 * @param file File to be read
	 * @return the pixels as integer matrix
	 */
	public static int[][] compute(File file) {
		try {
			BufferedImage img = ImageIO.read(file);
			int w = img.getWidth(), h = img.getHeight();
			int pixels[][] = new int[w][h];
			for (int x = 0; x < w; x++) {
				for (int y = 0; y < h; y++) {
					pixels[x][y] = img.getRGB(x, y);
				}
			}

			return pixels;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Save the image
	 * @param pixels The integer matrix representing the data to be written
	 * @param output File to be saved to
	 */
	public static void saveImage(int pixels[][], File output) {
		int w = pixels.length;
		int h = pixels[0].length;

		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				image.setRGB(i, j, pixels[i][j]);
			}
		}
		try {
			ImageIO.write(image, "png", output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
