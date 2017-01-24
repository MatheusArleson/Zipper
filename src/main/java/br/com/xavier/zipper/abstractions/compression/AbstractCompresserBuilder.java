package br.com.xavier.zipper.abstractions.compression;

import java.io.IOException;
import java.io.OutputStream;

import br.com.xavier.zipper.abstractions.io.stream.AbstractReadableOutputStream;
import br.com.xavier.zipper.enums.BufferLocation;
import br.com.xavier.zipper.enums.ExecutionStrategy;
import br.com.xavier.zipper.enums.StorageMode;
import br.com.xavier.zipper.interfaces.compression.ICompresser;
import br.com.xavier.zipper.interfaces.compression.ICompresserBuilder;

public abstract class AbstractCompresserBuilder implements ICompresserBuilder {

	private static final long serialVersionUID = 6038018187329066597L;
	
	//XXX PROPERTIES
	private Integer bytesPerRead;
	private BufferLocation bufferLocation;
	private StorageMode storageMode;
	private ExecutionStrategy executionStrategy;
	
	//XXX CONSTRUCTOR
	public AbstractCompresserBuilder() { }
	
	//XXX OVERRIDE METHODS
	@Override
	public ICompresserBuilder bytesPerRead(Integer bytesPerRead) {
		this.bytesPerRead = new Integer(bytesPerRead);
		return this;
	}
	
	@Override
	public ICompresserBuilder bufferLocation(BufferLocation bufferLocation) {
		this.bufferLocation = bufferLocation;
		return this;
	}
	
	@Override
	public ICompresserBuilder storageMode(StorageMode storageMode) {
		this.storageMode = storageMode;
		return null;
	}
	
	@Override
	public ICompresserBuilder executionStrategy(ExecutionStrategy executionStrategy) {
		this.executionStrategy = executionStrategy;
		return this;
	}
	
	@Override
	public ICompresser build() throws IOException {
		preventNullConfigProperties();
		
		AbstractReadableOutputStream<? extends OutputStream> readableStream = processBufferLocation(bufferLocation);
		ICompresser compresserInstance = generateCompresserInstance(readableStream, this.bytesPerRead, this.storageMode, this.executionStrategy);
		
		return compresserInstance;
	}

	//XXX ABSTRACT METHODS
	protected abstract AbstractReadableOutputStream<? extends OutputStream> processBufferLocation(BufferLocation bufferLocation) throws IOException;
	
	protected abstract ICompresser generateCompresserInstance(
		AbstractReadableOutputStream<? extends OutputStream> readableStream,
		Integer bytesPerRead, StorageMode storageMode, ExecutionStrategy executionStrategy
	) throws IOException;

	//XXX PRIVATE METHODS
	private void preventNullConfigProperties() {
		if (bytesPerRead == null) {
			bytesPerRead = ICompresserBuilder.DEFAULT_BYTES_PER_READ;
		}

		if (bufferLocation == null) {
			bufferLocation = ICompresserBuilder.DEFAULT_BUFFER_LOCATION;
		}

		if (storageMode == null) {
			storageMode = ICompresserBuilder.DEFAULT_STORAGE_MODE;
		}

		if (executionStrategy == null) {
			executionStrategy = ICompresserBuilder.DEFAULT_EXECUTION_STRATEGY;
		}
	}

}
