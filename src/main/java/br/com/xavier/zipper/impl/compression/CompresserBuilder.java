package br.com.xavier.zipper.impl.compression;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import br.com.xavier.zipper.abstractions.compression.AbstractCompresserBuilder;
import br.com.xavier.zipper.abstractions.io.stream.AbstractReadableOutputStream;
import br.com.xavier.zipper.enums.BufferLocation;
import br.com.xavier.zipper.enums.ExecutionStrategy;
import br.com.xavier.zipper.enums.StorageMode;
import br.com.xavier.zipper.impl.io.stream.ReadableByteArrayOutputStream;
import br.com.xavier.zipper.impl.io.stream.ReadableFileOutputStream;
import br.com.xavier.zipper.interfaces.compression.ICompresser;

public final class CompresserBuilder extends AbstractCompresserBuilder {
	
	private static final long serialVersionUID = -915464407027095310L;

	//XXX CONSTRUCTOR
	public CompresserBuilder() {}
	
	//XXX OVERRIDE METHODS
	@Override
	protected AbstractReadableOutputStream<? extends OutputStream> processBufferLocation(BufferLocation bufferLocation) throws IOException {
		switch (bufferLocation) {
		case DISK:
			return new ReadableFileOutputStream(File.createTempFile("Zipper", "temp"));
		case MEMORY:
			return new ReadableByteArrayOutputStream();
		default:
			throw new IllegalArgumentException("Unknow buffer location");
		}
	}

	@Override
	protected ICompresser generateCompresserInstance(
		AbstractReadableOutputStream<? extends OutputStream> readableStream, 
		Integer bytesPerRead,
		StorageMode storageMode, 
		ExecutionStrategy executionStrategy
	) throws IOException {
		return new Compresser(readableStream, bytesPerRead, storageMode, executionStrategy);
	}

}
