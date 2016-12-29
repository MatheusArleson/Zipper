package br.com.xavier.zipper.interfaces.compression;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.zip.Checksum;

import br.com.xavier.zipper.enums.CompressStrategy;

public interface ICompresserBuilder extends Serializable {
	
	ICompresserBuilder buffer( OutputStream os );
	ICompresserBuilder bytesPerRead( Integer bytesPerRead );
	ICompresserBuilder checksum( Checksum checksum );
	ICompresserBuilder strategy( CompressStrategy compressStrategy );
	
	ICompresser build();

}
