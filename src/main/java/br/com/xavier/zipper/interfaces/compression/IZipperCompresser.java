package br.com.xavier.zipper.interfaces;

import java.io.InputStream;
import java.io.Serializable;

public interface ZipperCompresser extends Serializable {

	ZipperCompresser add(String zipEntryName, InputStream zipEntryContentStream);
	ZipperOutput output();
	
}
