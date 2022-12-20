package com.lovephotos.collageeditor.graphics_tom.commands_tom;

import android.graphics.Bitmap;

public class TomEmptyCommand implements TomImageProcessingCommand {
	
	public static final String IDtom = "com.passiontocode.graphics.commands.EmptyCommand";
	
	public Bitmap processJusi(Bitmap bitmap) {
		return bitmap;
	}

	public String getIdJusi() {
		return IDtom;
	}

}
