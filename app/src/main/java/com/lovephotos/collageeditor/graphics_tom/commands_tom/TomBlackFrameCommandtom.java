package com.lovephotos.collageeditor.graphics_tom.commands_tom;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

public class TomBlackFrameCommandtom implements TomImageProcessingCommand {

	private static final String IDtom = "com.passiontocode.graphics.commands.BlackFrameCommand";
	
	private float roundtom = -1;
	private float bordertom = -1;

	public TomBlackFrameCommandtom(float round, float border) {
		this.setRoundtom(round);
		this.setBordertom(border);
	}

	public TomBlackFrameCommandtom() {

	}

	public Bitmap processJusi(Bitmap bitmap) {
		int widthtom = bitmap.getWidth();
		int heighttom = bitmap.getHeight();

		int rtom = (int) (roundtom > 0 ? roundtom : getProportionalRoundtom(widthtom, heighttom));
		int btom = (int) (bordertom > 0 ? bordertom : getProportionalBordertom(widthtom,
				heighttom));
		Bitmap resulttom = Bitmap.createBitmap(widthtom, heighttom, Config.ARGB_8888);

		Canvas canvastom = new Canvas(resulttom);
		canvastom.drawARGB(0, 0, 0, 0);

		final Paint painttom = new Paint();
		painttom.setAntiAlias(true);
		painttom.setColor(Color.BLACK);

		final Rect recttom = new Rect(btom, btom, widthtom - btom, heighttom - btom);
		final RectF rectFtom = new RectF(recttom);

		canvastom.drawRoundRect(rectFtom, rtom, rtom, painttom);

		painttom.setXfermode(new PorterDuffXfermode(
				android.graphics.PorterDuff.Mode.SRC_IN));

		canvastom.drawBitmap(bitmap, recttom, recttom, painttom);

		return resulttom;
	}

	private float getProportionalRoundtom(int width, int height) {
		int mintom = Math.min(width, height);

		return (float) (mintom / 10.0);
	}

	private float getProportionalBordertom(int width, int height) {
		int mintom = Math.min(width, height);
		return (float) (mintom / 50.0);
	}

	public float getRound() {
		return roundtom;
	}

	public void setRoundtom(float round) {
		this.roundtom = round;
	}

	public float getBorder() {
		return bordertom;
	}

	public void setBordertom(float border) {
		this.bordertom = border;
	}

	public String getIdJusi() {
		return IDtom;
	}
}
