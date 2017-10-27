package com.vir.helpers;

import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_UNCHANGED;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * Class to help with the conveertion between the different file formats.
 * 
 * @author Alfredo Lopez
 *
 */
public class IOHelper {

	private static final String IMAGE_ENCODE = ".jpg";
	private static final String IMAGE_ENCODE_WRITE = "jpg";

	/**
	 * Small method to write an input stream to a file.
	 * 
	 * @param path The path where to write to.
	 * @param image The inputstream of the image
	 * @throws Exception
	 */
	public static void writeToPath(String path, InputStream image) throws Exception {

		byte[] buffer = new byte[image.available()];
		image.read(buffer);
		File targetFile = new File(path);
		try (OutputStream outStream = new FileOutputStream(targetFile)) {
			outStream.write(buffer);
		}
	}

	/**
	 * Converts from a Mat object to an InputStream.
	 * 
	 * @param matrix the Mat object with the data.
	 * @return an instance of an InputStream.
	 * @throws Exception
	 */
	public static InputStream matToInputStream(Mat matrix) throws Exception {
		MatOfByte matOfBytes = new MatOfByte();
		Imgcodecs.imencode(IMAGE_ENCODE, matrix, matOfBytes);
		return new ByteArrayInputStream(matOfBytes.toArray());
	}

	/**
	 * Converts from a Matrix to a Buffered Image.
	 * 
	 * @param matrix the Mat object with the data.
	 * @return an instance of an BufferedImage.
	 * @throws Exception
	 */
	public static BufferedImage matToBufferedImage(Mat matrix) throws Exception {
		return ImageIO.read(IOHelper.matToInputStream(matrix));
	}

	/**
	 * Converts from a Buffered Image to a Mat.
	 * 
	 * @param image the BufferedImage to convert.
	 * @return an instance of an Mat.
	 * @throws Exception
	 */
	public static Mat bufferedImageToMat(BufferedImage image) throws Exception {
		try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
			ImageIO.write(image, IMAGE_ENCODE_WRITE, buffer);
			return Imgcodecs.imdecode(new MatOfByte(buffer.toByteArray()), CV_LOAD_IMAGE_UNCHANGED);
		}
	}

	/**
	 * Converts from an InputStream to a Mat object.
	 * 
	 * @param stream the stream to read
	 * @return an instance of a Mat
	 * @throws Exception
	 */
	public static Mat inputStreamToMat(InputStream stream) throws Exception {
		try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
			int nRead;
			byte[] data = new byte[16 * 1024];
			while ((nRead = stream.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
			return Imgcodecs.imdecode(new MatOfByte(buffer.toByteArray()), CV_LOAD_IMAGE_UNCHANGED);
		}
	}
}
