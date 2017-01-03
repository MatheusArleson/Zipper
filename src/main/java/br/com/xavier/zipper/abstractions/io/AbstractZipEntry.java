package br.com.xavier.zipper.abstractions.io;

import br.com.xavier.zipper.interfaces.io.IZipEntry;

public abstract class AbstractZipEntry implements IZipEntry {

	private static final long serialVersionUID = 1128507403725953815L;
	
	//XXX PROPERTIES
	private final String zipEntryName;
	
	//XXX CONSTRUCTOR
	public AbstractZipEntry( String zipEntryName ) {
		if( zipEntryName == null ){
			throw new IllegalArgumentException( "Zip entry name cannot be null." );
		}
		
		String zipEntryNameTrimmed = zipEntryName.trim();
		if( zipEntryNameTrimmed.isEmpty() ){
			throw new IllegalArgumentException( "Zip entry name cannot be empty." );
		}
		
		this.zipEntryName = zipEntryName;
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
		AbstractZipEntry other = (AbstractZipEntry) obj;
		if (zipEntryName == null) {
			if (other.zipEntryName != null)
				return false;
		} else if (!zipEntryName.equals(other.zipEntryName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "AbstractZipEntry [" 
			+ "zipEntryName=" + zipEntryName 
		+ "]";
	}

	@Override
	public String getZipEntryName() {
		return zipEntryName;
	}
	
}
