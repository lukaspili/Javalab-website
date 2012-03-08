package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import play.db.jpa.Blob;
import play.libs.MimeTypes;

import models.users.Picture;

public class PictureService {
	
	public Picture createPicture(File file) {
		Picture picture = new Picture();
		picture.name = file.getName();
		picture.file = new Blob();
		try {
			picture.file.set(new FileInputStream(file), MimeTypes.getContentType(file.getName()));
		} catch (FileNotFoundException e) {
			return null;
		}
		
		picture.save();
		
		return picture;
	}

}
