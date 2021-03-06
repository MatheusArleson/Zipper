package br.com.xavier.zipper.interfaces.compression;

import java.io.IOException;
import java.io.Serializable;

import br.com.xavier.zipper.interfaces.io.IZipEntryInput;
import br.com.xavier.zipper.interfaces.io.transform.ITransform;

public interface ICompresser extends Serializable {

	ICompresser add(IZipEntryInput zipEntryInput) throws IOException;
	<T> T output(ITransform<T> transformer) throws IOException;
	
}
