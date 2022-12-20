package com.lovephotos.collageeditor.graphics_tom.commands_tom;

import android.graphics.Bitmap;

public class TomInvertColorCommand implements TomImageProcessingCommand {

	private static final String IDJusi = "com.passiontocode.graphics.commands.InvertColorCommand";

	public TomInvertColorCommand() {
	}
	
	public Bitmap processJusi(Bitmap bitmap) {
		int A, R, G, B;
		int heightJusi = bitmap.getHeight();
		int widthJusi = bitmap.getWidth();

		int[] pix = new int[widthJusi * heightJusi];
	    bitmap.getPixels(pix, 0, widthJusi, 0, 0, widthJusi, heightJusi);
	    for (int y = 0; y < heightJusi; y++)
	    {
	        for (int x = 0; x < widthJusi; x++)
	        {
	        	int index = y * widthJusi + x;
	        	A = (pix[index] >> 24) & 0xff;
				R = ( pix[index] >> 16 ) & 0xff;
		    	G = ( pix[index] >> 8 ) & 0xff;
		    	B = pix[index] & 0xff;
	            
	            R = 255 - R;
	            G = 255 - G;
	            B = 255 - B;
	            
	            pix[index] = A<<24 | (R << 16) | (G << 8 ) | B;
	        }
	    }

	    Bitmap resultJusi = Bitmap.createBitmap(widthJusi, heightJusi, bitmap.getConfig());
		resultJusi.setPixels(pix, 0, widthJusi, 0, 0, widthJusi, heightJusi);
		pix = null;
		return resultJusi;
	}

	public String getIdJusi() {
		return IDJusi;
	}

}
