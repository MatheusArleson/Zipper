package br.com.xavier.zipper.interfaces.decompression;

import java.io.Serializable;

import br.com.xavier.zipper.interfaces.io.IZipEntryInput;

public interface IDecompresser extends Serializable {
	
	IDecompresser from( IZipEntryInput input );
	//IZipEntryOutput output();

}
