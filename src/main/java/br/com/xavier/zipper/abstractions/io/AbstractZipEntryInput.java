package br.com.xavier.zipper.abstractions.io;

import java.io.InputStream;

import br.com.xavier.zipper.interfaces.io.IZipEntryInput;

public abstract class AbstractZipEntryInput extends AbstractZipEntry implements IZipEntryInput {

	private static final long serialVersionUID = 6774319452516118286L;
	
	//XXX PROPERTIES
	private final InputStream zipEntryContentStream;
	
	//XXX CONSTRUCTOR
	public AbstractZipEntryInput( String zipEntryName, InputStream zipEntryContentStream ) {
		super( zipEntryName );
		
		if( zipEntryContentStream == null ){
			throw new IllegalArgumentException( "Zip entry content stream cannot be null." );
		}
		
		this.zipEntryContentStream = zipEntryContentStream ;
	}
	
	//XXX OVERRIDE METHODS
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return "AbstractZipperInput ["
			+ super.toString() 
		+ "]";
	}

	@Override
	public InputStream getZipEntryInputStream() {
		return zipEntryContentStream;
	}
	
}
