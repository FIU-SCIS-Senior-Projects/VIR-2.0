package com.vir.helpers;

import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_UNCHANGED;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import nu.pattern.OpenCV;

/**
 * Class to help with the conveertion between the different file formats.
 * 
 * @author Alfredo Lopez
 *
 */
public class IOHelper {
	
	/**
	 * Small method to write an input stream to a file.
	 * @param path The path where to write to.
	 * @param image The inputstream of the image
	 * @throws Exception
	 */
	public static void writeToPath(String path, InputStream image) throws Exception {
		byte[] buffer = new byte[image.available()];
		image.read(buffer);
		File targetFile = new File(path);
		OutputStream outStream = new FileOutputStream(targetFile);
		outStream.write(buffer);
	}

	/**
	 * Converts from a Mat object to an InputStream.
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
	 * Converts from an InputStream to a Mat object.
	 * @param stream the stream to read
	 * @return an instance of a Mat
	 * @throws Exception 
	 */
	public static Mat inputStreamToMat(InputStream stream) throws Exception {
		
		OpenCV.loadShared();
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
