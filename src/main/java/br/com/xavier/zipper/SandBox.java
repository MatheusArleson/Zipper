package br.com.xavier.zipper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Checksum;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class SandBox {

	static final int BUFFER = 2048;

	public static void main(String[] args) throws Exception {
		try {

			FileOutputStream fos = new FileOutputStream("test.zip");

			Checksum outputStreamChecksum = new Adler32();
			CheckedOutputStream checksumOutputStream = new CheckedOutputStream(fos, outputStreamChecksum);

			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checksumOutputStream);
			ZipOutputStream out = new ZipOutputStream(bufferedOutputStream);
			out.setMethod(ZipOutputStream.DEFLATED);

			BufferedInputStream bis = null;
			byte data[] = new byte[BUFFER];

			// get a list of files from current directory
			File f = new File(".");
			String filesNames[] = f.list();

			for (int i = 0; i < filesNames.length; i++) {
				System.out.println("##> CREATING ZIP ENTRY FOR > " + filesNames[i]);

				FileInputStream fis = new FileInputStream(filesNames[i]);

				Checksum inputStreamChecksum = new Adler32();
				CheckedInputStream cis = new CheckedInputStream(fis, inputStreamChecksum);
				bis = new BufferedInputStream(cis, BUFFER);

				ZipEntry entry = new ZipEntry(filesNames[i]);
				entry.setMethod(ZipEntry.DEFLATED);

				out.putNextEntry(entry);
				int count;
				while ((count = bis.read(data, 0, BUFFER)) != -1) {
					out.write(data, 0, count);
				}

				Long zipEntryChecksum = inputStreamChecksum.getValue();
				System.out.println("##> ZIP ENTRY CHECKSUM > " + zipEntryChecksum);

				entry.setCrc(zipEntryChecksum);
				entry.setCreationTime(FileTime.from(Instant.now()));
				entry.setLastModifiedTime(FileTime.from(Instant.now()));

				bis.close();
			}

			out.flush();
			out.close();

			System.out.println("#> ZIP FILE CHECSUM > " + outputStreamChecksum.getValue());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
