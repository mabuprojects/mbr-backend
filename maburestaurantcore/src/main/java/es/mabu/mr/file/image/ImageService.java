package es.mabu.mr.file.image;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import es.mabu.mr.file.StorageException;
import es.mabu.mr.file.StorageFileNotFoundException;

public interface ImageService {

	void saveImage(MultipartFile file, String relativePath, String name) throws StorageException, NotAnImageException;

	Resource getImageByPath(String path) throws StorageFileNotFoundException;

	void update(MultipartFile file, String relativePath, String name) throws StorageException, NotAnImageException;

}
