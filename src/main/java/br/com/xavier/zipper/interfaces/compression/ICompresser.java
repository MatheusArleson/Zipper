package br.com.xavier.zipper.interfaces.compression;

import java.io.IOException;
import java.io.Serializable;

import br.com.xavier.zipper.interfaces.io.IZipEntryInput;
import br.com.xavier.zipper.interfaces.io.IZipEntryOutput;

public interface ICompresser extends Serializable {
	ICompresser add( IZipEntryInput zipperInput ) throws IOException;
	IZipEntryOutput output( ) throws IOException;
	
}
