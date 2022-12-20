package com.lovephotos.collageeditor.graphics_tom.commands_tom;

import android.graphics.Bitmap;

public class TomGrayscaleCommand implements TomImageProcessingCommand {

	private static final String IDtom = "com.passiontocode.graphics.commands.GrayscaleCommand";

	final double RED_FACTORtom = 0.299;
	final double GREEN_FACTORtom = 0.587;
	final double BLUE_FACTORtom = 0.114;
	
	public Bitmap processJusi(Bitmap bitmap) {
		int Atom, Rtom, Gtom, Btom;
		
		int widthtom = bitmap.getWidth();
		int heighttom = bitmap.getHeight();

		int[] pixtom = new int[widthtom * heighttom];
	    bitmap.getPixels(pixtom, 0, widthtom, 0, 0, widthtom, heighttom);
		
		for(int xtom = 0; xtom < widthtom; ++xtom) {
			for(int ytom = 0; ytom < heighttom; ++ytom) {
				int index = ytom * widthtom + xtom;

				Atom = (pixtom[index] >> 24) & 0xff;
				Rtom = ( pixtom[index] >> 16 ) & 0xff;
		    	Gtom = ( pixtom[index] >> 8 ) & 0xff;
		    	Btom = pixtom[index] & 0xff;

				Rtom = Gtom = Btom = (int)(RED_FACTORtom * Rtom + GREEN_FACTORtom * Gtom + BLUE_FACTORtom * Btom);

				pixtom[index] = Atom<<24 | (Rtom << 16) | (Gtom << 8 ) | Btom;
			}
		}

		Bitmap resulttom = Bitmap.createBitmap(widthtom, heighttom, bitmap.getConfig());
		resulttom.setPixels(pixtom, 0, widthtom, 0, 0, widthtom, heighttom);
		pixtom = null;
		return resulttom;
	}

	public String getIdJusi() {
		return IDtom;
	}

}
