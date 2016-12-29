package br.com.xavier.zipper.interfaces;

import java.io.Serializable;

import br.com.xavier.zipper.interfaces.compression.ICompresserBuilder;
import br.com.xavier.zipper.interfaces.decompression.IDecompresserBuilder;

public interface IZipper extends Serializable {
	
	ICompresserBuilder prepareCompresser();
	IDecompresserBuilder prepareDecompress();
	
}
