package br.com.xavier.zipper.abstractions.compression;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Queue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import br.com.xavier.zipper.enums.BufferMode;
import br.com.xavier.zipper.enums.CompressStrategy;
import br.com.xavier.zipper.interfaces.compression.ICompresser;
import br.com.xavier.zipper.interfaces.compression.ICompresserConfig;
import br.com.xavier.zipper.interfaces.io.ITransform;
import br.com.xavier.zipper.interfaces.io.IZipEntryInput;

public abstract class AbstractCompresser implements ICompresser {

	private static final long serialVersionUID = -1279268509840118053L;

	// XXX PROPERTIES
	private ICompresserConfig compresserConfiguration;
	private OutputStream bufferOutputStream;
	private ZipOutputStream zipOutputStream;
	
	private Queue<IZipEntryInput> zipEntriesInputs;

	// XXX CONSTRUCTOR
	public AbstractCompresser( ICompresserConfig compresserConfiguration ) throws IOException {
		this.compresserConfiguration = Objects.requireNonNull( compresserConfiguration );
		processConfiguration( compresserConfiguration );
	}

	//XXX CONSTRUCTOR METHODS
	private void processConfiguration( ICompresserConfig compresserConfiguration ) throws IOException {
		generateBufferOutputStreamInstance(compresserConfiguration);
		generateZipOutputStream( compresserConfiguration );
	}

	private void generateBufferOutputStreamInstance( ICompresserConfig compresserConfiguration ) throws IOException {
		BufferMode bufferMode = compresserConfiguration.getBufferMode();
		switch ( bufferMode ) {
		case DISK:
			bufferOutputStream = new ByteArrayOutputStream();
			return;
		case MEMORY:
			bufferOutputStream = new FileOutputStream( File.createTempFile("Zipper", "temp") );
			return;
		default:
			throw new IllegalArgumentException( "Unknow buffer mode." );
		}
	}
	
	private void generateZipOutputStream( ICompresserConfig compresserConfiguration ){
		Integer bytesPerRead = compresserConfiguration.getBytesPerRead();
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream( bufferOutputStream, bytesPerRead );
		
		ZipOutputStream zipOutputStream = new ZipOutputStream( bufferedOutputStream );
		zipOutputStream.setMethod( ZipOutputStream.DEFLATED );
		
		this.zipOutputStream = zipOutputStream;
	}
	
	//XXX OVERRIDE METHODS
	@Override
	public ICompresser add( IZipEntryInput zipEntryInput ) throws IOException {
		return add( zipEntryInput, compresserConfiguration.getCompressStrategy() );
	}
	
	private ICompresser add( IZipEntryInput zipEntryInput, CompressStrategy compressStrategy ) throws IOException {
		if( zipEntryInput == null ){
			throw new IllegalArgumentException( "Zip entry input cannot be null." );
		}
		
		switch ( compressStrategy ) {
		case LAZY:
			zipEntriesInputs.add( zipEntryInput );
			return this;
			
		case EAGER:
			compress( zipEntryInput );
			return this;
			
		default:
			throw new IllegalAccessError( "Unknow compress strategy." );
		}
	}

	@Override
	public <T> T output( ITransform<T> transformer ) throws IOException {
		PipedOutputStream pos = new PipedOutputStream();
		PipedInputStream pis = new PipedInputStream( pos );
		
		
		
		InputStream inputStream = new Bytearra;
		return transformer.transform( inputStream  );
	}

	// XXX COMPRESS METHODS
	protected void compress( Collection<IZipEntryInput> zipEntriesInputs ) throws IOException {
		if ( zipEntriesInputs == null || zipEntriesInputs.isEmpty() ) {
			throw new IllegalArgumentException("Zip entries inputs cannot be null or empty.");
		}

		Iterator<IZipEntryInput> iterator = zipEntriesInputs.iterator();
		while ( iterator.hasNext() ) {
			compress( iterator.next() );
		}
	}

	protected void compress( IZipEntryInput zipEntryInput ) throws IOException {
		writeZipEntry( zipEntryInput, compresserConfiguration, zipOutputStream );
	}
	
	private void writeZipEntry( IZipEntryInput zipEntryInput, ICompresserConfig compresserConfiguration, ZipOutputStream zipOutputStream ) throws IOException {
		ZipEntry zipEntry = generateZipEnty(zipEntryInput);
		zipOutputStream.putNextEntry(zipEntry);
		
		Integer bytesPerRead = compresserConfiguration.getBytesPerRead();
		InputStream zipEntryContentStream = generateZipEntryInputStream( zipEntryInput, bytesPerRead );
		byte contentBuffer[] = new byte[ bytesPerRead ];
		
		int count;
		while ( (count = zipEntryContentStream.read( contentBuffer, 0, bytesPerRead ) ) != -1) {
			zipOutputStream.write(contentBuffer, 0, count);
		}
		
		zipEntryContentStream.close();
		zipOutputStream.closeEntry();
	}
	
	private ZipEntry generateZipEnty( IZipEntryInput zipEntryInput ) {
		ZipEntry zipEntry = new ZipEntry( zipEntryInput.getZipEntryName() );
		zipEntry.setMethod( ZipEntry.DEFLATED );
		zipEntry.setCreationTime( FileTime.from(Instant.now()) );
		zipEntry.setLastModifiedTime( FileTime.from(Instant.now()) );
		return zipEntry;
	}

	private InputStream generateZipEntryInputStream(IZipEntryInput zipEntryInput, Integer bytesPerRead) {
		InputStream zipEntryContentStream = zipEntryInput.getZipEntryInputStream();
		BufferedInputStream bis = new BufferedInputStream( zipEntryContentStream, bytesPerRead  );
		return bis;
	}

}
