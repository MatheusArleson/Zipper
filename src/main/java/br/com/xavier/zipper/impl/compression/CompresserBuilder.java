package br.com.xavier.zipper.impl.compression;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;

import br.com.xavier.zipper.abstractions.compression.AbstractCompresserBuilder;
import br.com.xavier.zipper.abstractions.io.stream.AbstractReadableOutputStream;
import br.com.xavier.zipper.enums.BufferLocation;
import br.com.xavier.zipper.enums.StorageMode;
import br.com.xavier.zipper.impl.io.stream.ReadableByteArrayOutputStream;
import br.com.xavier.zipper.impl.io.stream.ReadableFileOutputStream;

public final class CompresserBuilder extends AbstractCompresserBuilder {
	
	private static final long serialVersionUID = -915464407027095310L;

	//XXX CONSTRUCTOR
	public CompresserBuilder() {}
	
	//XXX OVERRIDE METHODS
	@Override
	protected AbstractReadableOutputStream<? extends OutputStream> processBufferLocation(BufferLocation bufferLocation) throws IOException {
		switch (bufferLocation) {
		case DISK:
			return new ReadableByteArrayOutputStream();
		case MEMORY:
			return new ReadableFileOutputStream(File.createTempFile("Zipper", "temp"));

		default:
			throw new IllegalArgumentException("Unknow buffer location");
		}
	}
	
	@Override
	protected ZipOutputStream processStorageMode(StorageMode storageMode, Integer bytesPerRead, AbstractReadableOutputStream<? extends OutputStream> readableStream){
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(readableStream, bytesPerRead);
		ZipOutputStream zipOutputStream = new ZipOutputStream( bufferedOutputStream );
		
		switch (storageMode) {
		case COMPRESSED:
			zipOutputStream.setMethod( ZipOutputStream.DEFLATED );
			break;
		case UNCOMPRESSED:
			zipOutputStream.setMethod( ZipOutputStream.STORED );
			
		default:
			throw new IllegalArgumentException("Unknow storage mode.");
		}
		
		
		return zipOutputStream;
	}

}
