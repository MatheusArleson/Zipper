package br.com.xavier.zipper.interfaces;

import java.io.Serializable;

import br.com.xavier.zipper.interfaces.compression.IZipperCompresserBuilder;
import br.com.xavier.zipper.interfaces.decompression.IZipperDecompresserBuilder;

public interface IZipper extends Serializable {
	
	IZipperCompresserBuilder prepareCompresser();
	IZipperDecompresserBuilder prepareDecompress();
	
}
