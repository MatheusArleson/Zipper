package br.com.xavier.zipper.interfaces.decompression;

import java.io.Serializable;

import br.com.xavier.zipper.interfaces.io.IZipperInput;
import br.com.xavier.zipper.interfaces.io.IZipperOutput;

public interface IZipperDecompresser extends Serializable {
	
	IZipperDecompresser from( IZipperInput input );
	IZipperOutput output();

}
