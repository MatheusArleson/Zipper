package br.com.xavier.zipper.abstractions.compression;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import br.com.xavier.zipper.interfaces.IZipper;
import br.com.xavier.zipper.interfaces.compression.IZipperCompresser;
import br.com.xavier.zipper.interfaces.io.IZipperInput;
import br.com.xavier.zipper.interfaces.io.IZipperOutput;

public class AbstractZipperCompresser implements IZipperCompresser {

	private static final long serialVersionUID = -1279268509840118053L;
	
	//XXX PROPERTIES
	private Long bufferBytesSize;
	private ByteArrayOutputStream compressionOutputStream;
	
	//XXX CONSTRUCTOR
	public AbstractZipperCompresser( ) {
		
	}

//	private void setBufferSize( Long bufferBytesSize ) {
//		if (bufferBytesSize == null || bufferBytesSize < 1) {
//			this.bufferBytesSize = IZipper.DEFAULT_BUFFER_SIZE;
//		} else {
//			this.bufferBytesSize = bufferBytesSize;
//		}
//	}
	
	//XXX OVERRIDE METHODS
	public IZipperCompresser add( IZipperInput zipperInput ) {
		if( zipperInput == null ){
			throw new IllegalArgumentException( "Input cannot be null." );
		}
		
		if( compressionOutputStream == null ){
		}
		
		return this;
	}

	public IZipperOutput output() {
		// TODO Auto-generated method stub
		return null;
	}

}
