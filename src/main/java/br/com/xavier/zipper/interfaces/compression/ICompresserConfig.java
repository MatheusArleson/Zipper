package br.com.xavier.zipper.interfaces.compression;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.zip.Checksum;

import br.com.xavier.zipper.enums.CompressStrategy;

public interface ICompresserConfig extends Serializable {
	
	OutputStream getBufferOutputStream();
	void setBufferOutputStream( OutputStream os);
	
	Integer getBytesPerRead();
	void setBytesPerRead( Integer bytesPerRead );
	
	Checksum getChecksumGenerator();
	void setChecksumGenerator( Checksum checksumGenerator );
	
	CompressStrategy getCompressStrategy();
	void setCompressStrategy( CompressStrategy compressStrategy);

}
