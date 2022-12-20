package com.lovephotos.collageeditor.graphics_tom.commands_tom;

import android.graphics.Bitmap;
import android.graphics.Color;

public class TomColorBoostCommandtom implements TomImageProcessingCommand {

	private static final String IDtom = "com.passiontocode.graphics.commands.ColorBoostCommand";

	private int colortom;
	private int percenttom;

	public TomColorBoostCommandtom(int color, int percent) {
		this.colortom = color;
		this.percenttom = percent;
	}

	public Bitmap processJusi(Bitmap bitmap) {
		int widthtom = bitmap.getWidth();
		int heighttom = bitmap.getHeight();
		int[] pixtom = new int[widthtom * heighttom];
		bitmap.getPixels(pixtom, 0, widthtom, 0, 0, widthtom, heighttom);

		int Atom, Rtom, Gtom, Btom;

		for (int xtom = 0; xtom < widthtom; ++xtom) {
			for (int ytom = 0; ytom < heighttom; ++ytom) {
				int index = ytom * widthtom + xtom;

				Atom = (pixtom[index] >> 24) & 0xff;
				Rtom = (pixtom[index] >> 16) & 0xff;
				Gtom = (pixtom[index] >> 8) & 0xff;
				Btom = pixtom[index] & 0xff;

				if (colortom == Color.RED) {
					Rtom = (int) (Rtom * (1 + percenttom));
					Rtom = (Rtom < 0) ? 0 : ((Rtom > 255) ? 255 : Rtom);
				} else if (colortom == Color.GREEN) {
					Gtom = (int) (Gtom * (1 + percenttom));
					Gtom = (Gtom < 0) ? 0 : ((Gtom > 255) ? 255 : Gtom);
				} else if (colortom == Color.BLUE) {
					Btom = (int) (Btom * (1 + percenttom));
					Btom = (Btom < 0) ? 0 : ((Btom > 255) ? 255 : Btom);
				}
				pixtom[index] = Atom << 24 | (Rtom << 16) | (Gtom << 8) | Btom;
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

	public int getPercettom() {
		return percenttom;
	}

	public void setPercenttom(int percentOfBoost) {
		this.percenttom = percentOfBoost;
	}

	public int getPrimitiveColortom() {
		return colortom;
	}

	public void setPrimitiveColor(int colortom) {
		if (colortom == Color.RED || colortom == Color.GREEN || colortom == Color.BLUE) {
			this.colortom = colortom;
		} else {
			this.colortom = 0;
		}
	}

}
