package com.dstch.util;

import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageUtil
{

	public static String IMGPATH_ICON = "/com/dstch/image/icon.png";
	public static String IMGPATH_POT = "/com/dstch/image/pot.png";
	
	public static ImageIcon loadImage(InputStream in) {
		if(in == null) {
			return new ImageIcon();
		}
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
	    int len = 0;
	    try {
			while ((len = in.read(buffer)) != -1) {
			    output.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return new ImageIcon(output.toByteArray());
	}

	public static Image getIconImage() {
		InputStream ips = ImageUtil.class.getResourceAsStream(ImageUtil.IMGPATH_ICON);
		Image imgIcon = null;
		try {
			imgIcon = ImageIO.read(ips);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return imgIcon;
	}
	
	public static ImageIcon getPotImage() {
		InputStream ips = ImageUtil.class.getResourceAsStream(ImageUtil.IMGPATH_POT);
		ImageIcon potIcon = loadImage(ips);
		return potIcon;
	}

}