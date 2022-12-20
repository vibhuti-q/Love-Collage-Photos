package com.lovephotos.collageeditor.graphics_tom.commands_tom;

import android.graphics.Bitmap;


public class TomSepiaCommand implements TomImageProcessingCommand {

	private static final String IDJusi = "com.passiontocode.graphics.commands.SepiaCommand";

	final double RED_FACTORJusi = 0.299;
	final double GREEN_FACTORJusi = 0.587;
	final double BLUE_FACTORJusi = 0.114;
	
	private double redJusi;
	private double greenJusi;
	private double blueJusi;
	private int depthJusi;
	
	public TomSepiaCommand() {
		this.redJusi = 2;
		this.greenJusi = 1;
		this.blueJusi = 0;
		this.depthJusi = 20;
	}
	
	public TomSepiaCommand(double red, double green, double blue, int depth) {
		this.redJusi = red;
		this.greenJusi = green;
		this.blueJusi = blue;
		this.depthJusi = depth;
	}
	
	
	public Bitmap processJusi(Bitmap bitmap) {
		int widthJusi = bitmap.getWidth();
		int heightJusi = bitmap.getHeight();
		
		int A, R, G, B;
		int[] pix = new int[widthJusi * heightJusi];
	    bitmap.getPixels(pix, 0, widthJusi, 0, 0, widthJusi, heightJusi);
		
		for(int x = 0; x < widthJusi; ++x) {
			for(int y = 0; y < heightJusi; ++y) {
				int index = y * widthJusi + x;
				A = (pix[index] >> 24) & 0xff;
				R = ( pix[index] >> 16 ) & 0xff;
		    	G = ( pix[index] >> 8 ) & 0xff;
		    	B = pix[index] & 0xff;
		    	
				B = G = R = (int)(RED_FACTORJusi * R + GREEN_FACTORJusi * G + BLUE_FACTORJusi * B);

				R += (depthJusi * redJusi);
				if(R > 255) { R = 255; }

				G += (depthJusi * greenJusi);
				if(G > 255) { G = 255; }

				B += (depthJusi * blueJusi);
				if(B > 255) { B = 255; }

				pix[index] = A<<24 | (R << 16) | (G << 8 ) | B;
			}
		}

		Bitmap result = Bitmap.createBitmap(widthJusi, heightJusi, bitmap.getConfig());
		result.setPixels(pix, 0, widthJusi, 0, 0, widthJusi, heightJusi);
		pix = null;
		return result;
	}

	public String getIdJusi() {
		return IDJusi;
	}

	public double getRed() {
		return redJusi;
	}

	public void setRed(double red) {
		this.redJusi = red;
	}

	public double getGreen() {
		return greenJusi;
	}

	public void setGreen(double green) {
		this.greenJusi = green;
	}

	public double getBlue() {
		return blueJusi;
	}

	public void setBlue(double blue) {
		this.blueJusi = blue;
	}

	public int getDepth() {
		return depthJusi;
	}

	public void setDepth(int depth) {
		this.depthJusi = depth;
	}

	
}
