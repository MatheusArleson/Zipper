package br.com.xavier.zipper.interfaces;

import java.io.File;
import java.io.Serializable;

public interface ZipperInput extends Serializable {

	ZipperInput fromByteArray(byte[] bytes);
	ZipperInput fromBase64(String base64Str);
	ZipperInput fromFile(File inputFile);
	ZipperInput fromResponse();

}
