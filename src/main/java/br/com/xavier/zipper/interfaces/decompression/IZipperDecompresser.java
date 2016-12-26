package br.com.xavier.zipper.interfaces;

import java.io.Serializable;

public interface ZipperDecompresser extends Serializable {
	
	ZipperDecompresser from( ZipperInput input );
	ZipperOutput output();

}
