package br.com.xavier.zipper.impl.compression;

import br.com.xavier.zipper.abstractions.compression.AbstractCompresserConfiguration;
import br.com.xavier.zipper.enums.BufferMode;
import br.com.xavier.zipper.enums.CompressStrategy;

public final class CompresserConfiguration extends AbstractCompresserConfiguration {

	private static final long serialVersionUID = -2083319721315429176L;
	
	//XXX CONSTRUCTORS
	public CompresserConfiguration() {
		super();
	}

	public CompresserConfiguration(BufferMode bufferMode, CompressStrategy compressStrategy) {
		super(bufferMode, compressStrategy);
	}

	public CompresserConfiguration(BufferMode bufferMode, Integer bytesPerRead, CompressStrategy compressStrategy) {
		super(bufferMode, bytesPerRead, compressStrategy);
	}

	public CompresserConfiguration(BufferMode bufferMode, Integer bytesPerread) {
		super(bufferMode, bytesPerread);
	}

	public CompresserConfiguration(BufferMode bufferMode) {
		super(bufferMode);
	}

	public CompresserConfiguration(CompressStrategy compressStrategy) {
		super(compressStrategy);
	}

	public CompresserConfiguration(Integer bytesPerRead, CompressStrategy compressStrategy) {
		super(bytesPerRead, compressStrategy);
	}

	public CompresserConfiguration(Integer bytesPerRead) {
		super(bytesPerRead);
	}

}
