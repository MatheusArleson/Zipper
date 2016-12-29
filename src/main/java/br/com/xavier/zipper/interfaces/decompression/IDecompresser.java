package br.com.xavier.zipper.interfaces.decompression;

import java.io.Serializable;

import br.com.xavier.zipper.interfaces.io.IZipEntryInput;
import br.com.xavier.zipper.interfaces.io.IZipEntryOutput;

public interface IDecompresser extends Serializable {
	
	IDecompresser from( IZipEntryInput input );
	IZipEntryOutput output();

}
