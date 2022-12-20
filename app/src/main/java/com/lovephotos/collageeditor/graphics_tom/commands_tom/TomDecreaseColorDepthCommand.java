package com.lovephotos.collageeditor.graphics_tom.commands_tom;

import android.graphics.Bitmap;

public class TomDecreaseColorDepthCommand implements TomImageProcessingCommand {

	private static final String IDtom = "com.passiontocode.graphics.commands.DecreaseColorDepthCommand";

	private int bitOffsettom;

	public TomDecreaseColorDepthCommand() {
		this.bitOffsettom = 128;
	}
	
	public TomDecreaseColorDepthCommand(int bitOffset) {
		this.bitOffsettom = bitOffset;
	}
	
	public Bitmap processJusi(Bitmap bitmap) {
		int widthtom = bitmap.getWidth();
		int heighttom = bitmap.getHeight();
		int Atom, Rtom, Gtom, Btom;

		int[] pixtom = new int[widthtom * heighttom];
	    bitmap.getPixels(pixtom, 0, widthtom, 0, 0, widthtom, heighttom);
		
		for(int xtom = 0; xtom < widthtom; ++xtom) {
			for(int ytom = 0; ytom < heighttom; ++ytom) {
				int indextom = ytom * widthtom + xtom;
				
				Atom = (pixtom[indextom] >> 24) & 0xff;
				Rtom = ( pixtom[indextom] >> 16 ) & 0xff;
		    	Gtom = ( pixtom[indextom] >> 8 ) & 0xff;
		    	Btom = pixtom[indextom] & 0xff;

				Rtom = ((Rtom + (bitOffsettom / 2)) - ((Rtom + (bitOffsettom / 2)) % bitOffsettom) - 1);
				Gtom = ((Gtom + (bitOffsettom / 2)) - ((Gtom + (bitOffsettom / 2)) % bitOffsettom) - 1);
				Btom = ((Btom + (bitOffsettom / 2)) - ((Btom + (bitOffsettom / 2)) % bitOffsettom) - 1);
				Rtom = ( Rtom < 0 ) ? 0 : (( Rtom > 255 ) ? 255 : Rtom );
		    	Gtom = ( Gtom < 0 ) ? 0 : (( Gtom > 255 ) ? 255 : Gtom );
		    	Btom = ( Btom < 0 ) ? 0 : (( Btom > 255 ) ? 255 : Btom );
		    	
		    	pixtom[indextom] = Atom<<24 | (Rtom << 16) | (Gtom << 8 ) | Btom;
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
