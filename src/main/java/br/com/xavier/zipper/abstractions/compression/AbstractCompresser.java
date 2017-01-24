package br.com.xavier.zipper.abstractions.compression;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import br.com.xavier.zipper.abstractions.io.stream.AbstractReadableOutputStream;
import br.com.xavier.zipper.enums.ExecutionStrategy;
import br.com.xavier.zipper.enums.StorageMode;
import br.com.xavier.zipper.interfaces.compression.ICompresser;
import br.com.xavier.zipper.interfaces.io.IZipEntryInput;
import br.com.xavier.zipper.interfaces.io.stream.IReadable;
import br.com.xavier.zipper.interfaces.io.transform.ITransform;

public abstract class AbstractCompresser implements ICompresser {

	private static final long serialVersionUID = -1279268509840118053L;

	//XXX PROPERTIES
	private IReadable readableOutputStream;
	private ZipOutputStream zipOutputStream;
	
	private Integer bytesPerRead;
	private ExecutionStrategy executionStrategy;
	
	private Queue<IZipEntryInput> zipEntries;
	
	//XXX CONSTRUCTOR
	public AbstractCompresser(
		AbstractReadableOutputStream<? extends OutputStream> readableOutputStream, 
		Integer bytesPerRead,
		StorageMode storageMode,
		ExecutionStrategy executionStrategy
	) throws IOException {
		this.readableOutputStream = Objects.requireNonNull(readableOutputStream);
		this.executionStrategy = Objects.requireNonNull(executionStrategy);
		this.bytesPerRead = Objects.requireNonNull(bytesPerRead);
		
		this.zipOutputStream = generateZipOutputStreamInstance(readableOutputStream, bytesPerRead, storageMode);
		this.zipEntries = new LinkedList<>();
	}
	
	private ZipOutputStream generateZipOutputStreamInstance(AbstractReadableOutputStream<? extends OutputStream> readableStream, Integer bytesPerRead, StorageMode storageMode){
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(readableStream, bytesPerRead);
		ZipOutputStream zipOutputStream = new ZipOutputStream( bufferedOutputStream );
		
		switch (storageMode) {
		case COMPRESSED:
			zipOutputStream.setMethod( ZipOutputStream.DEFLATED );
			break;
		case UNCOMPRESSED:
			zipOutputStream.setMethod( ZipOutputStream.STORED );
			
		default:
			throw new IllegalArgumentException("Unknow storage mode.");
		}
		
		return zipOutputStream;
	}
	
	//XXX OVERRIDE METHODS
	@Override
	public ICompresser add(IZipEntryInput zipEntryInput) throws IOException {
		if(zipEntryInput == null){
			return this;
		}
		
		switch (this.executionStrategy) {
		case EAGER:
			compress(zipEntryInput);
			break;
			
		case LAZY:
			this.zipEntries.add(zipEntryInput);
			break;
			
		default:
			throw new UnsupportedOperationException("Unknow execution strategy.");
		}
		
		return this;
	}
	
	@Override
	public <T> T output(ITransform<T> transformer) throws IOException {
		if(transformer == null){
			throw new IllegalArgumentException("Transformer cannot be null.");
		}
		
		if(ExecutionStrategy.isLazy(this.executionStrategy)){
			compress(this.zipEntries);
		}
		
		this.zipOutputStream.flush();
		this.zipOutputStream.close();
		
		InputStream bufferedZipContent = this.readableOutputStream.toInputStream();
		return transformer.transform(bufferedZipContent);
	}

	//XXX COMPRESS METHODS
	protected void compress(Collection<IZipEntryInput> zipEntriesInputs) throws IOException {
		if (zipEntriesInputs == null || zipEntriesInputs.isEmpty()) {
			throw new IllegalArgumentException("Zip entries inputs cannot be null or empty.");
		}

		Iterator<IZipEntryInput> iterator = zipEntriesInputs.iterator();
		while (iterator.hasNext()) {
			compress(iterator.next());
		}
	}

	protected void compress(IZipEntryInput zipEntryInput) throws IOException {
		writeZipEntry(this.bytesPerRead, this.zipOutputStream, zipEntryInput);
	}

	private void writeZipEntry(Integer bytesPerRead, ZipOutputStream zipOutputStream, IZipEntryInput zipEntryInput) throws IOException {
		ZipEntry zipEntry = generateZipEnty(zipEntryInput);
		zipOutputStream.putNextEntry(zipEntry);

		InputStream zipEntryContentStream = generateZipEntryInputStream(zipEntryInput, bytesPerRead);
		byte contentBuffer[] = new byte[bytesPerRead];

		int count;
		while ((count = zipEntryContentStream.read(contentBuffer, 0, bytesPerRead)) != -1) {
			zipOutputStream.write(contentBuffer, 0, count);
		}

		zipEntryContentStream.close();
		zipOutputStream.closeEntry();
	}

	private ZipEntry generateZipEnty(IZipEntryInput zipEntryInput) {
		ZipEntry zipEntry = new ZipEntry(zipEntryInput.getZipEntryName());
		zipEntry.setMethod(ZipEntry.DEFLATED);
		zipEntry.setCreationTime(FileTime.from(Instant.now()));
		zipEntry.setLastModifiedTime(FileTime.from(Instant.now()));
		return zipEntry;
	}

	private InputStream generateZipEntryInputStream(IZipEntryInput zipEntryInput, Integer bytesPerRead) {
		InputStream zipEntryContentStream = zipEntryInput.getZipEntryInputStream();
		BufferedInputStream bis = new BufferedInputStream(zipEntryContentStream, bytesPerRead);
		return bis;
	}

}
