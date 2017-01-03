package br.com.xavier.zipper.abstractions.compression;

import java.io.IOException;

import br.com.xavier.zipper.enums.BufferMode;
import br.com.xavier.zipper.enums.CompressStrategy;
import br.com.xavier.zipper.interfaces.compression.ICompresser;
import br.com.xavier.zipper.interfaces.compression.ICompresserBuilder;
import br.com.xavier.zipper.interfaces.compression.ICompresserConfig;

public abstract class AbstractCompresserBuilder implements ICompresserBuilder {

	private static final long serialVersionUID = 6038018187329066597L;
	
	//XXX PROPERTIES
	private BufferMode bufferMode;
	private Integer bytesPerRead;
	private CompressStrategy compressStrategy;
	
	//XXX CONSTRUCTOR
	public AbstractCompresserBuilder() { }
	
	//XXX ABSTRACT METHODS
	protected abstract ICompresserConfig getCompresserConfigInstance( BufferMode bufferMode, Integer bytesPerRead, CompressStrategy compressStrategy );
	protected abstract ICompresser getCompresserInstance( ICompresserConfig config ) throws IOException;
	
	//XXX OVERRIDE METHODS
	@Override
	public ICompresserBuilder bufferMode( BufferMode bufferMode ) {
		this.bufferMode = bufferMode; 
		return this;
	}
	
	@Override
	public ICompresserBuilder bytesPerRead( Integer bytesPerRead ) {
		this.bytesPerRead = bytesPerRead;
		return this;
	}
	
	@Override
	public ICompresserBuilder strategy( CompressStrategy compressStrategy ) {
		this.compressStrategy = compressStrategy;
		return this;
	}
	
	@Override
	public ICompresser build() throws IOException {
		preventNullConfigProperties();
		
		ICompresserConfig config = getCompresserConfigInstance( bufferMode, bytesPerRead, compressStrategy );
		ICompresser compresserInstance = getCompresserInstance( config );
		return compresserInstance;
	}

	//XXX PRIVATE METHODS
	private void preventNullConfigProperties() {
		if( bytesPerRead == null ){
			bytesPerRead = ICompresserConfig.DEFAULT_BYTES_PER_READ;
		}
		
		if( bufferMode == null ){
			bufferMode = ICompresserConfig.DEFAULT_BUFFER_MODE;
		}
		
		if( compressStrategy == null ){
			compressStrategy = ICompresserConfig.DEFAULT_COMPRESS_STRATEGY;
		}
	}

}
