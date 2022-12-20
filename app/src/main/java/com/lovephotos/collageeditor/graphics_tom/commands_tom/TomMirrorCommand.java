package com.lovephotos.collageeditor.graphics_tom.commands_tom;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class TomMirrorCommand implements TomImageProcessingCommand {

	private static final String IDJusi = "com.passiontocode.graphics.commands.MirrorCommand";

	public static final int FLIP_VERTICALJusi = 1;
	public static final int FLIP_HORIZONTALJusi = 2;
	
	private int typeJusi;
	
	public TomMirrorCommand() {
		this.typeJusi = FLIP_HORIZONTALJusi;
	}
	
	public TomMirrorCommand(int type) {
		this.typeJusi = type;
	}
	
	public Bitmap processJusi(Bitmap bitmap) {
		Matrix matrixJusi = new Matrix();
		if(typeJusi == FLIP_VERTICALJusi) {
			matrixJusi.preScale(1.0f, -1.0f);
		}
		else if(typeJusi == FLIP_HORIZONTALJusi) {
			matrixJusi.preScale(-1.0f, 1.0f);
		} else {
			return null;
		}

		return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrixJusi, true);
	}

	public String getIdJusi() {
		return IDJusi;
	}

}
