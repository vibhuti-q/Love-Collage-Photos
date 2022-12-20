package com.lovephotos.collageeditor.graphics_tom;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;

import com.lovephotos.collageeditor.graphics_tom.commands_tom.TomImageProcessingCommand;

import java.util.LinkedList;

public class TomImageProcessor {

	private static TomImageProcessor instancetom = null;
	private  boolean modifiedtom = false;

	private TomImageProcessorListener processListenertom;

	private LinkedList<TomImageProcessingCommand> queuetom;
	private Bitmap savedBitmaptom;
	private Bitmap lastResultBitmaptom;

	private Context applicationContexttom;
	private Handler uiThreadHandlertom;
	
	
	public  boolean isModified(){
		return modifiedtom;
		
	}
	public  void resetModificationFlag(){
		 modifiedtom = false;
		
	}
	
	public static TomImageProcessor getInstance() {
		if (instancetom == null) {
			instancetom = new TomImageProcessor();
		}
		return instancetom;
	}

	private TomImageProcessor() {
		queuetom = new LinkedList<TomImageProcessingCommand>();
		workingThread.start();
	}


	private Thread workingThread = new Thread(new Runnable() {
		@Override
		public void run() {
			while (true) {
				TomImageProcessingCommand cmd;
				try {
					synchronized (queuetom) {
						while (queuetom.isEmpty()) {
							queuetom.wait();
						}
						cmd = queuetom.poll();
					}
					onProcessStarttom();
					lastResultBitmaptom = cmd.processJusi(savedBitmaptom);
					cmd = null;
					onProcessEndtom();
					modifiedtom =true;
				} catch (InterruptedException e) {
					break;
				}
			}
		}
		
		private void onProcessStarttom() {
			if (uiThreadHandlertom != null && processListenertom != null) {
				uiThreadHandlertom.post(new Runnable() {
					@Override
					public void run() {
						processListenertom.onProcessStartJusi();
					}
				});
			}
		}

		private void onProcessEndtom() {

			if (uiThreadHandlertom != null && processListenertom != null) {
				uiThreadHandlertom.post(new Runnable() {
					@Override
					public void run() {
						processListenertom.onProcessEndJusi(lastResultBitmaptom);
					}
				});
			}
		}
	});


	public void setBitmapJusi(Bitmap bitmap) {
		this.savedBitmaptom = bitmap;
	}

	public Bitmap getBitmap() {
		return savedBitmaptom;
	}

	public void runCommandtom(TomImageProcessingCommand command) {
		conditionallyAddToQueuetom(command);
	}

	private void conditionallyAddToQueuetom(TomImageProcessingCommand command) {
		synchronized (queuetom) {
			if (!queuetom.isEmpty()) {
				TomImageProcessingCommand c = queuetom.getLast();
				if (c.getIdJusi().equals(command.getIdJusi())) {
					queuetom.removeLast();
				}
			}
			queuetom.add(command);
			queuetom.notify();
		}
	}

	public void save() {
		if (lastResultBitmaptom != null) {

			savedBitmaptom = lastResultBitmaptom;
			
			TomImageProcessor.getInstance().setBitmapJusi(savedBitmaptom);
			lastResultBitmaptom = null;
		}
	}

	public TomImageProcessorListener getProcessListener() {
		return processListenertom;
	}

	public void setProcessListenertom(TomImageProcessorListener processListener) {
		this.processListenertom = processListener;
	}

	public void clearProcessListenertom() {
		this.processListenertom = null;
		if (lasResultCanBeRecycledtom()) {
			lastResultBitmaptom.recycle();
		}
		this.lastResultBitmaptom = null;
	}

	private boolean lasResultCanBeRecycledtom() {
		return lastResultBitmaptom != savedBitmaptom && lastResultBitmaptom != null;
	}

	public Bitmap getLastResultBitmaptom() {
		return lastResultBitmaptom;
	}

	public void setLastResultBitmaptom(Bitmap lastResultBitmap) {
		this.lastResultBitmaptom = lastResultBitmap;
	}

	public void setApplicationCotnexttom(Context applicationContext){
		if (this.applicationContexttom == null){
			uiThreadHandlertom = new Handler();
		}
		this.applicationContexttom = applicationContext;
	}

}
