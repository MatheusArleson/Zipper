package br.com.xavier.zipper.abstractions.compression;

import java.io.OutputStream;
import java.util.zip.Checksum;

import br.com.xavier.zipper.interfaces.compression.IZipperCompresser;
import br.com.xavier.zipper.interfaces.compression.IZipperCompresserBuilder;

public abstract class AbstractZipperCompresserBuilder implements IZipperCompresserBuilder {

	private static final long serialVersionUID = 6038018187329066597L;
	
	//XXX CONSTRUCTOR
	public AbstractZipperCompresserBuilder() {	}
	
	//XXX ABSTRACT METHODS
	protected abstract IZipperCompresser getZipperCompresserInstance( );
	
	//XXX OVERRIDE METHODS
	public IZipperCompresserBuilder buffer(OutputStream os) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public IZipperCompresserBuilder bytesPerRead(Long bytesPerRead) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public IZipperCompresserBuilder checksum(Checksum checksum) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public IZipperCompresser build() {
		// TODO Auto-generated method stub
		return null;
	}

}
