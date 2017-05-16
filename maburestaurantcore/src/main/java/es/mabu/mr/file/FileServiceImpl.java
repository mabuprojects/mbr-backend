package es.mabu.mr.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

	private Path rootLocation;

	@Value("${es.mabu.mr.file.root}")
	private String rootDir;

	@Autowired
	public FileServiceImpl() {

	}

	@PostConstruct
	private void init() {
		this.rootLocation = createFolderIfNotExists(Paths.get(rootDir));
	}

	private static Path createFolderIfNotExists(Path path) {
		if (Files.notExists(path)) {
			try {
				path = Files.createDirectory(path);
			} catch (IOException e) {
				throw new RuntimeException("Cant initialize file system for path " + path);
			}
		}
		return path;
	}

	@Override
	public Path init(String path) {
		Path location = rootLocation.resolve(path);
		return createFolderIfNotExists(location);

	}

	@Override
	public boolean store(MultipartFile file, String relativePath, String name) throws StorageException {
		try {
			if (file == null) {
				throw new StorageException("Failed to store empty file is null");
			}
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
			}
			Path finalPath = this.rootLocation.resolve(relativePath);
			Files.copy(file.getInputStream(), finalPath.resolve(name));
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + name, e);
		}
		return true;
	}

	@Override
	public Stream<Path> loadAll() throws StorageException {
		try {
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(path -> this.rootLocation.relativize(path));
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	@Override
	public Path load(String filename, String relativePath) {
		return rootLocation.resolve(relativePath + File.separator + filename);
	}

	@Override
	public Resource loadAsResource(String filename, String relativePath) throws StorageFileNotFoundException {
		Path file = load(filename, relativePath);
		Resource resource = new FileSystemResource(file.toFile());
		if (resource.exists() || resource.isReadable()) {
			return resource;
		} else {
			throw new StorageFileNotFoundException("Could not read file: " + file.toString());

		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void deleteFile(String filename, String relativePath) throws IOException {
		Files.delete(rootLocation.resolve(relativePath + File.separator + filename));

	}

	@Override
	public boolean existsFile(String file) {
		return Files.exists(rootLocation.resolve(file));
	}

	@Override
	public Path resolve(String path) {
		return rootLocation.resolve(path);
	}

}
