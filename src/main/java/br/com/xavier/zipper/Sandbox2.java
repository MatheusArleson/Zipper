package br.com.xavier.zipper;

import java.io.IOException;
import java.io.InputStream;

import org.omg.CORBA.portable.OutputStream;

import br.com.xavier.zipper.abstractions.io.stream.AbstractReadableOutputStream;
import br.com.xavier.zipper.enums.BufferLocation;
import br.com.xavier.zipper.enums.ExecutionStrategy;
import br.com.xavier.zipper.enums.StorageMode;
import br.com.xavier.zipper.impl.Zipper;
import br.com.xavier.zipper.impl.io.stream.ReadableByteArrayOutputStream;
import br.com.xavier.zipper.interfaces.compression.ICompresser;
import br.com.xavier.zipper.interfaces.io.IZipEntryInput;

public class Sandbox2 {

	public static void main(String[] args) throws IOException {
		// Zipper zipper = new Zipper();
		//
		// ICompresser compresser = zipper.prepareCompresser()
		// .bytesPerRead( 8096 )
		// .bufferLocation( BufferLocation.DISK )
		// .storageMode( StorageMode.COMPRESSED )
		// .executionStrategy( ExecutionStrategy.LAZY )
		// .build();
		//
		// IZipEntryInput zipperInput = null;
		// compresser.add( zipperInput );

		// compresser.output( );

		String str = "teste";

		AbstractReadableOutputStream aros = new ReadableByteArrayOutputStream();
		aros.write(str.getBytes());

		InputStream is = aros.toInputStream();
		byte[] isBuffer = new byte[is.available()];

		is.read(isBuffer);

		String str2 = new String(isBuffer);
		System.out.println(str2);

	}

}
