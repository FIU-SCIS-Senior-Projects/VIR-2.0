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
		Imgcodecs.imencode(".jpg", matrix, matOfBytes);
		ByteArrayInputStream io = new ByteArrayInputStream(matOfBytes.toArray());
		return io;
	}

	/**
	 * Converts from a Matrix to a Buffered Image.
	 * 
	 * @param matrix the Mat object with the data.
	 * @return an instance of an BufferedImage.
	 * @throws Exception
	 */
	public static BufferedImage matToBufferedImage(Mat matrix) throws Exception {
		BufferedImage image = ImageIO.read(IOHelper.matToInputStream(matrix));
		return image;
	}

	/**
	 * Converts from a Buffered Image to a Mat.
	 * 
	 * @param image the BufferedImage to convert.
	 * @return an instance of an Mat.
	 * @throws Exception
	 */
	public static Mat bufferedImageToMat(BufferedImage image) throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", os);
		InputStream stream = new ByteArrayInputStream(os.toByteArray());
		Mat matrix = IOHelper.inputStreamToMat(stream);
		return matrix;
	}

	/**
	 * Converts from an InputStream to a Mat object.
	 * 
	 * @param stream the stream to read
	 * @return an instance of a Mat
	 * @throws Exception
	 */
	public static Mat inputStreamToMat(InputStream stream) throws Exception {

		int nRead;
		byte[] data = new byte[16 * 1024];
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		while ((nRead = stream.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, nRead);
		}
		byte[] bytes = buffer.toByteArray();

		Mat mat = Imgcodecs.imdecode(new MatOfByte(bytes), CV_LOAD_IMAGE_UNCHANGED);
		return mat;
	}
}
