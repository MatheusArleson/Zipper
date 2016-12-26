package br.com.xavier.zipper.interfaces.io;

import java.io.Serializable;

public interface IZipperInput extends Serializable {

	String getZipEntryName();
	byte[] getZipEntryContent();

}
