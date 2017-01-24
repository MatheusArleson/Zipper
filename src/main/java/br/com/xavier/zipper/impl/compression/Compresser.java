package br.com.xavier.zipper.impl.compression;

import java.io.IOException;
import java.io.OutputStream;

import br.com.xavier.zipper.abstractions.compression.AbstractCompresser;
import br.com.xavier.zipper.abstractions.io.stream.AbstractReadableOutputStream;
import br.com.xavier.zipper.enums.ExecutionStrategy;
import br.com.xavier.zipper.enums.StorageMode;

public final class Compresser extends AbstractCompresser {

	private static final long serialVersionUID = -3577494747036581968L;
	
	//XXX CONSTRUCTOR
	public Compresser(
		AbstractReadableOutputStream<? extends OutputStream> readableOutputStream, 
		Integer bytesPerRead, 
		StorageMode storageMode,
		ExecutionStrategy executionStrategy
	) throws IOException {
		super(readableOutputStream, bytesPerRead, storageMode, executionStrategy);
	}

}
