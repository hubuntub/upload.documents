package com.mycompany.example.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;

public class GEUploadButton extends Composite implements HasClickHandlers {

	public GEUploadButton(){
		DecoratorPanel panel = new DecoratorPanel();
		initWidget(panel);
		panel.setWidget(new HTML("uploadbutton"));
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}
	
}
