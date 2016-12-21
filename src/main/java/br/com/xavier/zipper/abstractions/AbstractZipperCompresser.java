package br.com.xavier.zipper.abstractions;

import java.io.InputStream;

import br.com.xavier.zipper.interfaces.ZipperCompresser;
import br.com.xavier.zipper.interfaces.ZipperOutput;

public class AbstractZipperCompresser implements ZipperCompresser {

	private static final long serialVersionUID = -1279268509840118053L;
	
	//XXX PROPERTIES
	private Long bufferBytesSize;
	
	//XXX CONSTRUCTOR
	public AbstractZipperCompresser( Long bufferBytesSize ) {
		
	}
	
	//XXX OVERRIDE METHODS
	public ZipperCompresser add(String zipEntryName, InputStream zipEntryContentStream) {
		// TODO Auto-generated method stub
		return null;
	}

	public ZipperOutput output() {
		// TODO Auto-generated method stub
		return null;
	}

}
