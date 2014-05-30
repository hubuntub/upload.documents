package com.mycompany.example.server;

import gwtupload.server.UploadAction;
import gwtupload.server.exceptions.UploadActionException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

public class SampleUploadServlet extends UploadAction {
	public String executeAction(HttpServletRequest request,
			List<FileItem> sessionFiles) throws UploadActionException {
		String ret = "";
		for (FileItem item : getSessionItems(request)) {
			if (!item.isFormField()) {
				// Do anything with the file.

				// Update the string to return;
				ret += "server message";
			}
		}
		super.removeSessionFileItems(request);
		return ret;
	}
}
