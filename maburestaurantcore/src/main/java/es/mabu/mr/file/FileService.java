package es.mabu.mr.file;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	Path init(String path);

	boolean store(MultipartFile file, String relativePath, String name) throws StorageException;

	Stream<Path> loadAll() throws StorageException;

	Path load(String filename, String relativePath);

	Resource loadAsResource(String filename, String relativePath) throws StorageFileNotFoundException;

	void deleteAll();

	void deleteFile(String filename, String relativePath) throws IOException;

	boolean existsFile(String file);

	Path resolve(String path);
}
