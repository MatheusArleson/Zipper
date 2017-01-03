package br.com.xavier.zipper.interfaces.compression;

import java.io.Serializable;

import br.com.xavier.zipper.enums.BufferMode;
import br.com.xavier.zipper.enums.CompressStrategy;

public interface ICompresserConfig extends Serializable {
	
	static final BufferMode DEFAULT_BUFFER_MODE = BufferMode.DISK;
	static final int DEFAULT_BYTES_PER_READ = 4096;
	static final CompressStrategy DEFAULT_COMPRESS_STRATEGY = CompressStrategy.LAZY;
	
	BufferMode getBufferMode();
	Integer getBytesPerRead();
	CompressStrategy getCompressStrategy();

}
