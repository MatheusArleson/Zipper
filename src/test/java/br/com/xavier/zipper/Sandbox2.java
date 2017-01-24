package br.com.xavier.zipper;

import java.io.IOException;
import java.io.InputStream;

import br.com.xavier.zipper.enums.BufferLocation;
import br.com.xavier.zipper.enums.ExecutionStrategy;
import br.com.xavier.zipper.enums.StorageMode;
import br.com.xavier.zipper.impl.Zipper;
import br.com.xavier.zipper.impl.io.Base64Transformer;
import br.com.xavier.zipper.impl.io.ZipperInput;
import br.com.xavier.zipper.interfaces.compression.ICompresser;
import br.com.xavier.zipper.interfaces.io.IZipEntryInput;

public class Sandbox2 {

	public static void main(String[] args) throws IOException {
		Zipper zipper = new Zipper();

		ICompresser compresser = zipper.prepareCompresser()
										.bytesPerRead(8096)
										.bufferLocation(BufferLocation.DISK)
										.storageMode(StorageMode.COMPRESSED)
										.executionStrategy(ExecutionStrategy.LAZY)
										.build();
		
		String txtFileName = "lorem.txt";
		InputStream txtStream = Sandbox2.class.getResourceAsStream("/" + txtFileName);
		
		IZipEntryInput zipperInput = new ZipperInput(txtFileName, txtStream);
		compresser.add(zipperInput);
		
		Base64Transformer base64Transformer = new Base64Transformer();
		String output = compresser.output(base64Transformer);
		
		System.out.println(output);

	}

}
