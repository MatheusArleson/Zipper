package br.com.xavier.zipper.interfaces.compression;

import java.io.IOException;
import java.io.Serializable;

import br.com.xavier.zipper.enums.BufferLocation;
import br.com.xavier.zipper.enums.ExecutionStrategy;
import br.com.xavier.zipper.enums.StorageMode;

public interface ICompresserBuilder extends Serializable {

	static final int DEFAULT_BYTES_PER_READ = 4096;
	static final BufferLocation DEFAULT_BUFFER_LOCATION = BufferLocation.DISK;
	static final StorageMode DEFAULT_STORAGE_MODE = StorageMode.COMPRESSED;
	static final ExecutionStrategy DEFAULT_EXECUTION_STRATEGY = ExecutionStrategy.LAZY;

	ICompresserBuilder bytesPerRead(Integer bytesPerRead);
	ICompresserBuilder bufferLocation(BufferLocation bufferLocation);
	ICompresserBuilder storageMode(StorageMode storageMode);
	ICompresserBuilder executionStrategy(ExecutionStrategy executionStrategy);
	
	ICompresser build() throws IOException;

}
