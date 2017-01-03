package br.com.xavier.zipper.impl.compression;

import java.io.IOException;

import br.com.xavier.zipper.abstractions.compression.AbstractCompresserBuilder;
import br.com.xavier.zipper.enums.BufferMode;
import br.com.xavier.zipper.enums.CompressStrategy;
import br.com.xavier.zipper.interfaces.compression.ICompresser;
import br.com.xavier.zipper.interfaces.compression.ICompresserConfig;

public final class CompresserBuilder extends AbstractCompresserBuilder {
	
	private static final long serialVersionUID = -915464407027095310L;

	//XXX CONSTRUCTOR
	public CompresserBuilder() {	}
	
	//XXX OVERRIDE METHODS
	@Override
	protected ICompresserConfig getCompresserConfigInstance(BufferMode bufferMode, Integer bytesPerRead, CompressStrategy compressStrategy) {
		return new CompresserConfiguration( bufferMode, bytesPerRead, compressStrategy );
	}
	
	@Override
	protected ICompresser getCompresserInstance( ICompresserConfig configuration ) throws IOException {
		return new Compresser( configuration );
	}

}
