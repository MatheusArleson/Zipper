package br.com.xavier.zipper.impl.io;

import java.io.InputStream;

import br.com.xavier.zipper.abstractions.io.AbstractZipEntryInput;

public final class ZipperInput extends AbstractZipEntryInput {

	private static final long serialVersionUID = -2833661483982467571L;
	
	//XXX CONSTRUCTOR
	public ZipperInput( String zipEntryName, InputStream zipEntryContentStream ) {
		super( zipEntryName, zipEntryContentStream );
	}
	
}
