package br.com.xavier.zipper.interfaces.compression;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.zip.Checksum;

public interface IZipperCompresserBuilder extends Serializable {
	
	IZipperCompresserBuilder buffer(OutputStream os);
	IZipperCompresserBuilder bytesPerRead( Integer bytesPerRead );
	IZipperCompresserBuilder checksum(Checksum checksum);
	
	IZipperCompresser build();

}
