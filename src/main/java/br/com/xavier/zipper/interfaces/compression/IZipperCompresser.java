package br.com.xavier.zipper.interfaces.compression;

import java.io.Serializable;

import br.com.xavier.zipper.interfaces.io.IZipperInput;
import br.com.xavier.zipper.interfaces.io.IZipperOutput;

public interface IZipperCompresser extends Serializable {
	IZipperCompresser add( IZipperInput zipperInput );
	IZipperOutput output( );
	
}
