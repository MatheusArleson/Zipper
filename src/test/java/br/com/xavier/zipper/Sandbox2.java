package br.com.xavier.zipper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import br.com.xavier.zipper.enums.BufferLocation;
import br.com.xavier.zipper.enums.ExecutionStrategy;
import br.com.xavier.zipper.enums.StorageMode;
import br.com.xavier.zipper.impl.Zipper;
import br.com.xavier.zipper.impl.io.ZipperInput;
import br.com.xavier.zipper.impl.io.transform.FileTransformer;
import br.com.xavier.zipper.interfaces.compression.ICompresser;
import br.com.xavier.zipper.interfaces.io.IZipEntryInput;

public class Sandbox2 {

	public static void main(String[] args) throws IOException {
		Zipper zipper = new Zipper();

		ICompresser compresser = zipper.prepareCompresser()
										.bytesPerRead(8096)
										.bufferLocation(BufferLocation.MEMORY)
										.storageMode(StorageMode.COMPRESSED)
										.executionStrategy(ExecutionStrategy.EAGER)
										.build();
		
		String txtFileName = "lorem.txt";
		InputStream txtStream = Sandbox2.class.getResourceAsStream("/" + txtFileName);
		
		IZipEntryInput zipperInput = new ZipperInput(txtFileName, txtStream);
		compresser.add(zipperInput);
		
		//Base64Transformer transformer = new Base64Transformer();
		//String output = compresser.output(transformer);
		//System.out.println(output);
		
		FileTransformer transformer = new FileTransformer("out.zip");
		File outputFile = compresser.output(transformer);
		System.out.println(outputFile.getAbsolutePath());

	}

}
