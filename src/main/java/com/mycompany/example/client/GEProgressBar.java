package com.mycompany.example.client;

import gwtupload.client.HasProgress;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;

public class GEProgressBar extends Widget implements HasProgress {
	
	public GEProgressBar(){
		setElement(Document.get().createElement("progress"));
	}

	@Override
	public void setProgress(long done, long total) {

		getElement().setAttribute("max", "" + total);
		getElement().setAttribute("value", "" + done);
	}

}
