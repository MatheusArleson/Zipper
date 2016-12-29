package br.com.xavier.zipper.interfaces.io;

import java.io.InputStream;
import java.io.Serializable;

public interface IZipEntryInput extends Serializable {

	String getZipEntryName();
	void setZipEntryName( String zipEntryName );
	
	InputStream getZipEntryContentStream();
	void setZipEntryContentStream( InputStream zipEntryContentStream );
	
}
