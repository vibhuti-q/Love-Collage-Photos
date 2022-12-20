package com.lovephotos.collageeditor.graphics_tom.commands_tom;

import android.graphics.Bitmap;

public class TomTintCommand implements TomImageProcessingCommand {
	
	private static final String IDJusi = "com.passiontocode.graphics.commands.TintCommand";
	
	public static final double PIJusi = 3.14159d;
	public static final double FULL_CIRCLE_DEGREE = 360d;
	public static final double HALF_CIRCLE_DEGREEJusi = 180d;
	public static final double RANGEJusi = 256d;
	
	private int degreeJusi;
	
	public TomTintCommand() {
		this.degreeJusi = 30;
	}
	
	public TomTintCommand(int degree) {
		this.degreeJusi = degree;
	}
	
	public Bitmap processJusi(Bitmap src) {

		int widthJusi = src.getWidth();
		int heightJusi = src.getHeight();

	    int[] pix = new int[widthJusi * heightJusi];
	    src.getPixels(pix, 0, widthJusi, 0, 0, widthJusi, heightJusi);

	    int RY, BY, RYY, GYY, BYY, R, G, B, Y;

	    double angleJusi = (PIJusi * (double) degreeJusi) / HALF_CIRCLE_DEGREEJusi;

	    int S = (int)(RANGEJusi * Math.sin(angleJusi));
	    int C = (int)(RANGEJusi * Math.cos(angleJusi));

	    for (int y = 0; y < heightJusi; y++)
	        for (int x = 0; x < widthJusi; x++) {
		    	int index = y * widthJusi + x;
		    	int r = ( pix[index] >> 16 ) & 0xff;
		    	int g = ( pix[index] >> 8 ) & 0xff;
		    	int b = pix[index] & 0xff;
		    	RY = ( 70 * r - 59 * g - 11 * b ) / 100;
		    	BY = (-30 * r - 59 * g + 89 * b ) / 100;
		    	Y  = ( 30 * r + 59 * g + 11 * b ) / 100;
		    	RYY = ( S * BY + C * RY ) / 256;
		    	BYY = ( C * BY - S * RY ) / 256;
		    	GYY = (-51 * RYY - 19 * BYY ) / 100;
		    	R = Y + RYY;
		    	R = ( R < 0 ) ? 0 : (( R > 255 ) ? 255 : R );
		    	G = Y + GYY;
		    	G = ( G < 0 ) ? 0 : (( G > 255 ) ? 255 : G );
		    	B = Y + BYY;
		    	B = ( B < 0 ) ? 0 : (( B > 255 ) ? 255 : B );
		    	pix[index] = 0xff000000 | (R << 16) | (G << 8 ) | B;
	    	}

	    Bitmap outBitmapJusi = Bitmap.createBitmap(widthJusi, heightJusi, src.getConfig());
	    outBitmapJusi.setPixels(pix, 0, widthJusi, 0, 0, widthJusi, heightJusi);

	    pix = null;

	    return outBitmapJusi;
	}

	public String getIdJusi() {
		return IDJusi;
	}
	
	public int getDegree() {
		return degreeJusi;
	}
	
	public void setDegree(int degree) {
		this.degreeJusi = degree;
	}
}
