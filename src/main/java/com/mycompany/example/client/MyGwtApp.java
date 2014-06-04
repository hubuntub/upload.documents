package com.mycompany.example.client;

import gwtupload.client.IDropZone;
import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.MultiUploader;
import gwtupload.client.PreloadedImage;
import gwtupload.client.PreloadedImage.OnLoadPreloadedImageHandler;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MyGwtApp implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";


	// A panel where the thumbnails of uploaded images will be shown
	private FlowPanel panelImages = new FlowPanel();

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
	    class DropZoneLabel extends Label implements IDropZone {
	    }

	    DropZoneLabel customExternalDropZone = new DropZoneLabel();
	    customExternalDropZone.setText("Drop files here");
	    customExternalDropZone.setSize("300px", "40px");
	    customExternalDropZone.getElement().getStyle().setBorderStyle(Style.BorderStyle.DASHED);
	    customExternalDropZone.getElement().getStyle().setBorderWidth(1, Style.Unit.PX);
	    customExternalDropZone.getElement().getStyle().setPadding(10, Unit.PX);
	    customExternalDropZone.getElement().getStyle().setBorderColor("#4B7195");
	    MultiUploader customExternalDragDropUploader = new MultiUploader(
	            FileInputType.CUSTOM.with(new Button("Click me or drag and drop files below")).with((IDropZone) customExternalDropZone));
	    customExternalDragDropUploader.addOnFinishUploadHandler(onFinishUploaderHandler);
	    RootPanel.get("custom_external_dropzone").add(customExternalDragDropUploader);
	    RootPanel.get("custom_external_dropzone").add(customExternalDropZone);
	}
	 // Load the image in the document and in the case of success attach it to the viewer
	  private IUploader.OnFinishUploaderHandler onFinishUploaderHandler = new IUploader.OnFinishUploaderHandler() {
	    public void onFinish(IUploader uploader) {
	      if (uploader.getStatus() == Status.SUCCESS) {

	        new PreloadedImage(uploader.fileUrl(), showImage);

	        // The server sends useful information to the client by default
	        UploadedInfo info = uploader.getServerInfo();
	        System.out.println("File name " + info.name);
	        System.out.println("File content-type " + info.ctype);
	        System.out.println("File size " + info.size);

	        // You can send any customized message and parse it 
	        System.out.println("Server message " + info.message);
	      }
	    }
	  };

	  // Attach an image to the pictures viewer
	  private OnLoadPreloadedImageHandler showImage = new OnLoadPreloadedImageHandler() {
	    public void onLoad(PreloadedImage image) {
	      image.setWidth("75px");
	      panelImages.add(image);
	    }
	  };
}
