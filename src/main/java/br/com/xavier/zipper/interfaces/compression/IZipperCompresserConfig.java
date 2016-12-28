package br.com.xavier.zipper.interfaces.compression;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.zip.Checksum;

public interface IZipperCompresserConfig extends Serializable {
	
	OutputStream getBufferOutputStream();
	void setBufferOutputStream( OutputStream os);
	
	Integer getBytesPerRead();
	void setBytesPerRead( Integer bytesPerRead );
	
	Checksum getChecksumGenerator();
	void setChecksumGenerator( Checksum checksumGenerator );
	
	

}
