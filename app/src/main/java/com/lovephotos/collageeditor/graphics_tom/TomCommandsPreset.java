package com.lovephotos.collageeditor.graphics_tom;

import android.graphics.Color;

import com.lovephotos.collageeditor.R;
import com.lovephotos.collageeditor.graphics_tom.commands_tom.TomBlackFrameCommandtom;
import com.lovephotos.collageeditor.graphics_tom.commands_tom.TomColorBoostCommandtom;
import com.lovephotos.collageeditor.graphics_tom.commands_tom.TomColorFilterCommand;
import com.lovephotos.collageeditor.graphics_tom.commands_tom.TomDecreaseColorDepthCommand;
import com.lovephotos.collageeditor.graphics_tom.commands_tom.TomEmptyCommand;
import com.lovephotos.collageeditor.graphics_tom.commands_tom.TomGammaCorrectionCommand;
import com.lovephotos.collageeditor.graphics_tom.commands_tom.TomGrayscaleCommand;
import com.lovephotos.collageeditor.graphics_tom.commands_tom.TomImageProcessingCommand;
import com.lovephotos.collageeditor.graphics_tom.commands_tom.TomInvertColorCommand;
import com.lovephotos.collageeditor.graphics_tom.commands_tom.TomMirrorCommand;
import com.lovephotos.collageeditor.graphics_tom.commands_tom.TomSepiaCommand;
import com.lovephotos.collageeditor.graphics_tom.commands_tom.TomTintCommand;

import java.util.ArrayList;

public class TomCommandsPreset {

	public static final ArrayList<TomImageProcessingCommand> Presettom = new ArrayList<TomImageProcessingCommand>();
	public static final ArrayList<String> Namestom = new ArrayList<String>();

	static {
		Presettom.add(new TomEmptyCommand());
		Namestom.add("No Filter");
		Presettom.add(new TomGrayscaleCommand());
		Namestom.add("Grayscale");
		Presettom.add(new TomTintCommand(30));
		Namestom.add("Tint 1");
		Presettom.add(new TomTintCommand(70));
		Namestom.add("Tint 2");
		Presettom.add(new TomBlackFrameCommandtom());
		Namestom.add("Black Frame");
		Presettom.add(new TomColorBoostCommandtom(Color.RED, 20));
		Namestom.add("Red Boost");
		Presettom.add(new TomColorBoostCommandtom(Color.GREEN, 20));
		Namestom.add("Green Boost");
		Presettom.add(new TomColorBoostCommandtom(Color.BLUE, 20));
		Namestom.add("Blue Boost");
		Presettom.add(new TomColorFilterCommand(1.1, 0.7, 0.7));
		Namestom.add("Color Filter 1");
		Presettom.add(new TomColorFilterCommand(0.7, 1.1, 0.7));
		Namestom.add("Color Filter 2");
		Presettom.add(new TomColorFilterCommand(0.7, 0.7, 1.1));
		Namestom.add("Color Filter 3");
		Presettom.add(new TomColorFilterCommand(1.3, 1.1, 0.8));
		Namestom.add("Color Filter 4");
		Presettom.add(new TomDecreaseColorDepthCommand(128));
		Namestom.add("Decrease Color Depth");
		Presettom.add(new TomGammaCorrectionCommand(0.6, 0.5, 0.7));
		Namestom.add("Gamma Correction");
		Presettom.add(new TomInvertColorCommand());
		Namestom.add("Invert Color");
		Presettom.add(new TomMirrorCommand());
		Namestom.add("Mirror");
		Presettom.add(new TomSepiaCommand(2, 1, 0, 20));
		Namestom.add("Sepia");
		Presettom.add(new TomSepiaCommand(2, 2, 0, 20));
		Namestom.add("Sepia 2");
		Presettom.add(new TomSepiaCommand(1.62, 0.78, 1.21, 20));
		Namestom.add("Sepia 3");
		Presettom.add(new TomSepiaCommand(1.62, 1.28, 1.01, 45));
		Namestom.add("Sepia 4");
	}

	public static final Integer[] ImageIdstom = new Integer[]
			{
			R.drawable.sample_00, R.drawable.sample_02,
			R.drawable.sample_03, R.drawable.sample_04, R.drawable.sample_05,
			R.drawable.sample_06, R.drawable.sample_07, R.drawable.sample_08,
			R.drawable.sample_09, R.drawable.sample_10, R.drawable.sample_11,
			R.drawable.sample_12, R.drawable.sample_13, R.drawable.sample_14,
			R.drawable.sample_15, R.drawable.sample_16, R.drawable.sample_17,
			R.drawable.sample_18, R.drawable.sample_19, R.drawable.sample_20,
            };
}
