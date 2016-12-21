package br.com.xavier.zipper.interfaces;

import java.io.File;
import java.io.Serializable;

import javax.ws.rs.core.Response;

public interface ZipperOutput extends Serializable {
	
	byte[] toByteArray();
	String toBase64();
	File toFile(File outputFile);
	Response toResponse();

}
