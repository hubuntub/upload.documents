package com.mycompany.example.client;

import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.MultiUploader;
import gwtupload.client.PreloadedImage;
import gwtupload.client.PreloadedImage.OnLoadPreloadedImageHandler;
import jsupload.client.ChismesUploadProgress;
import jsupload.client.IncubatorUploadProgress;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import gwtupload.client.IDropZone;
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
		// Attach the image viewer to the document
	    RootPanel.get("thumbnails").add(panelImages);
	    
	    // Create a new uploader panel and attach it to the document
	    MultiUploader defaultUploader = new MultiUploader();
	    RootPanel.get("default").add(defaultUploader);
	    
	    
	    // Add a finish handler which will load the image once the upload finishes
	    defaultUploader.addOnFinishUploadHandler(onFinishUploaderHandler);
	    defaultUploader.setMaximumFiles(3);
	    defaultUploader.setFileInputPrefix("default");
	    // You can add customized parameters to servlet call 
	    defaultUploader.setServletPath(defaultUploader.getServletPath() + "?foo=bar");
	    defaultUploader.avoidRepeatFiles(true);
	    // This enables php apc progress mechanism
	    defaultUploader.add(new Hidden("APC_UPLOAD_PROGRESS", defaultUploader.getInputName()));

	    MultiUploader incubatorUploader = new MultiUploader(FileInputType.ANCHOR, new IncubatorUploadProgress());
	    incubatorUploader.addOnFinishUploadHandler(onFinishUploaderHandler);
	    incubatorUploader.setValidExtensions("jpg", "jpeg", "png", "gif");
	    RootPanel.get("incubator").add(incubatorUploader);


	    MultiUploader chismesUploader = new MultiUploader(FileInputType.BUTTON, new ChismesUploadProgress(false));
	    chismesUploader.addOnFinishUploadHandler(onFinishUploaderHandler);
	    RootPanel.get("chismes").add(chismesUploader);
	    
	    MUpld m = new MUpld();
	    RootPanel.get("uibinder").add(m);

	    MultiUploader dragDropUploader = new MultiUploader(FileInputType.DROPZONE);
	    dragDropUploader.addOnFinishUploadHandler(onFinishUploaderHandler);
	    RootPanel.get("dropzone").add(dragDropUploader);

	    class DropZoneLabel extends Label implements IDropZone {
	    }

	    DropZoneLabel externalDropZone = new DropZoneLabel();
	    externalDropZone.setText("");
	    externalDropZone.setSize("180px", "40px");
	    externalDropZone.getElement().getStyle().setBorderStyle(Style.BorderStyle.DASHED);
	    externalDropZone.getElement().getStyle().setBorderWidth(1, Style.Unit.PX);
	    externalDropZone.getElement().getStyle().setPadding(10, Unit.PX);

	    MultiUploader externalDragDropUploader = new MultiUploader(FileInputType.DROPZONE.with((IDropZone) externalDropZone));
	    externalDragDropUploader.addOnFinishUploadHandler(onFinishUploaderHandler);
	    RootPanel.get("external_dropzone").add(externalDragDropUploader);
	    RootPanel.get("external_dropzone").add(externalDropZone);

	    MultiUploader customDragDropUploader = new MultiUploader(
	            FileInputType.CUSTOM.with(new Label("Click me or drag and drop files on me")).with((IDropZone) null));
	    customDragDropUploader.addOnFinishUploadHandler(onFinishUploaderHandler);
	    RootPanel.get("custom_dropzone").add(customDragDropUploader);
	    
	    DropZoneLabel customExternalDropZone = new DropZoneLabel();
	    customExternalDropZone.setText("Drop files here");
	    customExternalDropZone.setSize("180px", "40px");
	    customExternalDropZone.getElement().getStyle().setBorderStyle(Style.BorderStyle.DASHED);
	    customExternalDropZone.getElement().getStyle().setBorderWidth(1, Style.Unit.PX);
	    customExternalDropZone.getElement().getStyle().setPadding(10, Unit.PX);
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
