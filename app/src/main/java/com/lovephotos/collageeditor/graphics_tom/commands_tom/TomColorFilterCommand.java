package com.lovephotos.collageeditor.graphics_tom.commands_tom;

import android.graphics.Bitmap;

public class TomColorFilterCommand implements TomImageProcessingCommand {

	private static final String IDtom = "com.passiontocode.graphics.commands.ColorFilter.Command";
	private double redFiltertom;
	private double greenFiltertom;
	private double blueFiltertom;

	public TomColorFilterCommand() {
		redFiltertom = 1;
		greenFiltertom = 1;
		blueFiltertom = 1;
	}

	public TomColorFilterCommand(double red, double green, double blue) {
		redFiltertom = red;
		greenFiltertom = green;
		blueFiltertom = blue;
	}

	public Bitmap processJusi(Bitmap bitmap) {
		int widthtom = bitmap.getWidth();
		int heighttom = bitmap.getHeight();
		int Atom, Rtom, Gtom, Btom;

		int[] pixtom = new int[widthtom * heighttom];
	    bitmap.getPixels(pixtom, 0, widthtom, 0, 0, widthtom, heighttom);
		
		for (int xtom = 0; xtom < widthtom; ++xtom) {
			for (int ytom = 0; ytom < heighttom; ++ytom) {
				int index = ytom * widthtom + xtom;

				Atom = (pixtom[index] >> 24) & 0xff;
				Rtom = ( pixtom[index] >> 16 ) & 0xff;
		    	Gtom = ( pixtom[index] >> 8 ) & 0xff;
		    	Btom = pixtom[index] & 0xff;

				Rtom = (int) (Rtom * redFiltertom);
				Gtom = (int) (Gtom * greenFiltertom);
				Btom = (int) (Btom * blueFiltertom);

				Rtom = ( Rtom < 0 ) ? 0 : (( Rtom > 255 ) ? 255 : Rtom );
		    	Gtom = ( Gtom < 0 ) ? 0 : (( Gtom > 255 ) ? 255 : Gtom );
		    	Btom = ( Btom < 0 ) ? 0 : (( Btom > 255 ) ? 255 : Btom );
				
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
