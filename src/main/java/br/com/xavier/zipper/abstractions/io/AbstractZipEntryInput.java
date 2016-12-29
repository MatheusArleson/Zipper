package br.com.xavier.zipper.abstractions.io;

import java.io.InputStream;
import java.util.Objects;

import br.com.xavier.zipper.interfaces.io.IZipEntryInput;

public abstract class AbstractZipEntryInput implements IZipEntryInput {

	private static final long serialVersionUID = 6774319452516118286L;
	
	//XXX PROPERTIES
	private String zipEntryName;
	private InputStream zipEntryContentStream;
	
	//XXX CONSTRUCTOR
	public AbstractZipEntryInput(String zipEntryName, InputStream zipEntryContentStream) {
		super();
		setZipEntryName( zipEntryName );
		setZipEntryContentStream( zipEntryContentStream );
	}
	
	//XXX OVERRIDE METHODS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((zipEntryName == null) ? 0 : zipEntryName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractZipEntryInput other = (AbstractZipEntryInput) obj;
		if (zipEntryName == null) {
			if (other.zipEntryName != null)
				return false;
		} else if (!zipEntryName.equals(other.zipEntryName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "AbstractZipperInput ["
			+ "zipEntryName=" + zipEntryName 
		+ "]";
	}

	//XXX GETTERS/SETTERS
	@Override
	public String getZipEntryName() {
		return zipEntryName;
	}
	
	@Override
	public void setZipEntryName(String zipEntryName) {
		if( zipEntryName == null ){
			throw new IllegalArgumentException( "Zip entry name cannot be null." );
		}
		
		String zipEntryNameTrimmed = zipEntryName.trim();
		if( zipEntryNameTrimmed.isEmpty() ){
			throw new IllegalArgumentException( "Zip entry name cannot be empty." );
		}
		
		this.zipEntryName = zipEntryNameTrimmed;
	}
	
	@Override
	public InputStream getZipEntryContentStream() {
		return zipEntryContentStream;
	}
	
	@Override
	public void setZipEntryContentStream(InputStream zipEntryContentStream) {
		this.zipEntryContentStream = Objects.requireNonNull( zipEntryContentStream );
	}
}
