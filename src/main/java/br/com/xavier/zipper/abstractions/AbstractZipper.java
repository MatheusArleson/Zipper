package br.com.xavier.zipper.abstractions;

import br.com.xavier.zipper.interfaces.Zipper;
import br.com.xavier.zipper.interfaces.ZipperCompresser;
import br.com.xavier.zipper.interfaces.ZipperDecompresser;

public abstract class AbstractZipper implements Zipper {

	private static final long serialVersionUID = 8681918294833841166L;
	
	//XXX PROPERTIES
	private Long bufferBytesSize;
	
	//XXX CONSTRUCTOR
	public AbstractZipper() {
		this.bufferBytesSize = Zipper.DEFAULT_BUFFER_SIZE;
	}
	
	//XXX ABSTRACT METHODS
	protected abstract ZipperCompresser getZipperCompresserInstance(Long bufferBytesSize2);
	protected abstract ZipperDecompresser getZipperDecompresserInstance(Long bufferBytesSize);
	
	//XXX OVERRIDE METHODS
	public Zipper setBufferBytesSize(Long bufferBytesSize) {
		if(bufferBytesSize == null || bufferBytesSize < 1){
			this.bufferBytesSize = Zipper.DEFAULT_BUFFER_SIZE;
		} else {
			this.bufferBytesSize = bufferBytesSize;
		}
		
		return this;
	}
	
	public ZipperCompresser compress() {
		return getZipperCompresserInstance( bufferBytesSize );
	}
	
	public ZipperDecompresser decompress() {
		return getZipperDecompresserInstance( bufferBytesSize );
	}
	
}
