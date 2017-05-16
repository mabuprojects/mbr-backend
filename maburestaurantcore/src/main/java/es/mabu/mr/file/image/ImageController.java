package es.mabu.mr.file.image;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.mabu.mr.file.StorageFileNotFoundException;

@RestController
@RequestMapping("/public/image")
public class ImageController {

	@Autowired
	ImageService imageService;

	@GetMapping("/product/{id:.+}")
	public ResponseEntity<Resource> serveFile(@PathVariable String id)
			throws StorageFileNotFoundException, IOException {
		Resource file = imageService.getImageByPath("product" + File.separator + id);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.lastModified(file.lastModified()).body(file);
	}

}
