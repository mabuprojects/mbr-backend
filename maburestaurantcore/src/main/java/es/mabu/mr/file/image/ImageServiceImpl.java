package es.mabu.mr.file.image;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import es.mabu.mr.file.FileService;
import es.mabu.mr.file.StorageException;
import es.mabu.mr.file.StorageFileNotFoundException;

@Service
public class ImageServiceImpl implements ImageService {

	private String ImageDir = "Images";
	@Autowired
	FileService fileService;

	@PostConstruct
	void init() {
		fileService.init(ImageDir);

	}

	@Override
	public void saveImage(MultipartFile file, String relativePath, String name)
			throws StorageException, NotAnImageException {
		checkImage(file);
		fileService.store(file, ImageDir + File.separator + relativePath, name);

	}

	@Override
	public Resource getImageByPath(String path) throws StorageFileNotFoundException {
		return fileService.loadAsResource(path, ImageDir);
	}

	private void checkImage(MultipartFile file) throws NotAnImageException {
		try (InputStream input = new BufferedInputStream(file.getInputStream())) {
			try {
				ImageIO.read(input).toString();
			} catch (Exception e) {
				throw new NotAnImageException("The file is not an image", e.getCause());
			}
		} catch (Exception e) {
			throw new NotAnImageException("The file is not readeable", e.getCause());
		}
	}

	@Override
	public void update(MultipartFile file, String relativePath, String name)
			throws StorageException, NotAnImageException {
		String currentFile = ImageDir + File.separator + relativePath + File.separator + name;
		if (fileService.existsFile(currentFile)) {
			try {
				fileService.deleteFile(name, ImageDir + File.separator + relativePath);
			} catch (IOException e) {
				throw new StorageException("Cant delete file" + name);
			}
		}
		saveImage(file, relativePath, name);
	}

}
