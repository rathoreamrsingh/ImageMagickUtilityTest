package com.testing.imageMagic;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ImageMagickUtilityTesting {
	/**
	 * imagemagic command to convert file : imconvert.exe jpg:
	 * "E:\testingImageMagic\images-dir\a b.jpg" -intent relative -sample
	 * 1024x768> -quality 95 -colorspace sRGB -strip -auto-orient
	 * E:\testingImageMagic\output-dir\asd-S.jpg
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("=========================================");
		System.out.println("Running command line utility ImageMagic");
		System.out.println("=========================================");

		Runtime runtime = Runtime.getRuntime();

		/*
		 * Target directory path
		 */
		File directory = new File("E:\\testingImageMagic\\images-dir");

		/*
		 * Getting list of files inside directory
		 */
		File[] listOfFiles = directory.listFiles();

		try {
			if (listOfFiles != null && listOfFiles.length > 0) {
				for (File file : listOfFiles) {
					/*
					 * processing only JPG files
					 */
					if (file.getName().contains(".jpg")) {
						/*
						 * Running command line utility
						 */
						Process pr = runtime.exec("imconvert.exe jpg:" + "\"" + "E:\\testingImageMagic\\images-dir\\"
								+ file.getName() + "\""
								+ " -intent relative -sample 100x100> -quality 95 -colorspace sRGB -strip -auto-orient "
								+ "\"" + "E:\\testingImageMagic\\output-dir\\"
								+ file.getName().substring(0, file.getName().lastIndexOf(".")) + "n.jpg" + "\"");
						BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

						String output = null;

						while ((output = input.readLine()) != null) {
							System.out.println(output);
						}

						int exitVal = pr.waitFor();
						System.out.println("--------------------------------------------------------------");
						System.out.println("Original File : " + file.getAbsolutePath());
						System.out.println("\nConverted File : " + "E:\\testingImageMagic\\output-dir\\"
								+ file.getName().substring(0, file.getName().lastIndexOf(".")) + "n.jpg");
						System.out.println("\nExited with error code : " + exitVal);
						System.out.println("--------------------------------------------------------------");

					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("=========================================");
		System.out.println("Ending command line utility ImageMagic");
		System.out.println("=========================================");
	}
}
