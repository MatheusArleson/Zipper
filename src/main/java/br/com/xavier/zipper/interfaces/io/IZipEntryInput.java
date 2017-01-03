package br.com.xavier.zipper.interfaces.io;

import java.io.InputStream;

public interface IZipEntryInput extends IZipEntry {

	InputStream getZipEntryInputStream();
	
}
