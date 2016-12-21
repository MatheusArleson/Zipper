package br.com.xavier.zipper.interfaces;

import java.io.Serializable;

public interface Zipper extends Serializable {
	
	static final Long DEFAULT_BUFFER_SIZE = 1024L; 
	
	Zipper setBufferBytesSize( Long bufferBytesSize );
	ZipperCompresser  compress();
	ZipperDecompresser decompress();
	
}
