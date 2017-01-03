package br.com.xavier.zipper.interfaces.compression;

import java.io.IOException;
import java.io.Serializable;

import br.com.xavier.zipper.enums.BufferMode;
import br.com.xavier.zipper.enums.CompressStrategy;

public interface ICompresserBuilder extends Serializable {
	
	ICompresserBuilder bufferMode( BufferMode bufferMode );
	ICompresserBuilder bytesPerRead( Integer bytesPerRead );
	ICompresserBuilder strategy( CompressStrategy compressStrategy );
	
	ICompresser build() throws IOException;

}
