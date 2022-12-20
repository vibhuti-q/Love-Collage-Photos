package com.lovephotos.collageeditor.graphics_tom.commands_tom;

import android.graphics.Bitmap;

public class TomGammaCorrectionCommand implements TomImageProcessingCommand {
	private static final String IDtom = "com.passiontocode.graphics.commands.GammaCorrectionCommand";
	
	private double redtom;
	private double greentom;
	private double bluetom;
	
	final int MAX_SIZEtom = 256;
	final double MAX_VALUE_DBLtom = 255.0;
	final int MAX_VALUE_INTtom = 255;
	final double REVERSEtom = 1.0;

	public TomGammaCorrectionCommand() {
		redtom = 0.6;
		greentom = 0.6;
		bluetom = 0.6;
	}
	
	public TomGammaCorrectionCommand(double red, double green, double blue){
		this.redtom = red;
		this.greentom = green;
		this.bluetom = blue;
	}
	
	public Bitmap processJusi(Bitmap bitmap) {
		int widthtom = bitmap.getWidth();
		int heighttom = bitmap.getHeight();

		int Atom, Rtom, Gtom, Btom;
		int[] pixtom = new int[widthtom * heighttom];
	    bitmap.getPixels(pixtom, 0, widthtom, 0, 0, widthtom, heighttom);

		int[] gammaRtom = new int[MAX_SIZEtom];
		int[] gammaGtom = new int[MAX_SIZEtom];
		int[] gammaBtom = new int[MAX_SIZEtom];

		for(int itom = 0; itom < MAX_SIZEtom; ++itom) {
			gammaRtom[itom] = (int) Math.min(MAX_VALUE_INTtom,
					(int)((MAX_VALUE_DBLtom * Math.pow(itom / MAX_VALUE_DBLtom, REVERSEtom / redtom)) + 0.5));
			gammaGtom[itom] = (int) Math.min(MAX_VALUE_INTtom,
					(int)((MAX_VALUE_DBLtom * Math.pow(itom / MAX_VALUE_DBLtom, REVERSEtom / greentom)) + 0.5));
			gammaBtom[itom] = (int) Math.min(MAX_VALUE_INTtom,
					(int)((MAX_VALUE_DBLtom * Math.pow(itom / MAX_VALUE_DBLtom, REVERSEtom / bluetom)) + 0.5));
		}

		for(int xtom = 0; xtom < widthtom; ++xtom) {
			for(int ytom = 0; ytom < heighttom; ++ytom) {
				int index = ytom * widthtom + xtom;
			
				Atom = (pixtom[index] >> 24) & 0xff;
				Rtom = ( pixtom[index] >> 16 ) & 0xff;
		    	Gtom = ( pixtom[index] >> 8 ) & 0xff;
		    	Btom = pixtom[index] & 0xff;

				Rtom = gammaRtom[Rtom];
				Gtom = gammaGtom[Gtom];
				Btom = gammaBtom[Btom];

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

	public double getRedtom() {
		return redtom;
	}

	public void setRedtom(double red) {
		this.redtom = red;
	}

	public double getGreentom() {
		return greentom;
	}

	public void setGreentom(double green) {
		this.greentom = green;
	}

	public double getBluetom() {
		return bluetom;
	}

	public void setBluetom(double blue) {
		this.bluetom = blue;
	}
}