package com.mycompany.example.client;

import gwtupload.client.BaseUploadStatus;

public class GEUploadStatus extends BaseUploadStatus {

	
	public GEUploadStatus(){
		setProgressWidget(new GEProgressBar());
	}
}
