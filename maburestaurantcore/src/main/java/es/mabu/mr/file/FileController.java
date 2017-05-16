package es.mabu.mr.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/public/file")
public class FileController {

	private String commonStorage = "CommonStorage";

	@Autowired
	FileService fileService;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public boolean handleFileUpload(@RequestBody MultipartFile file) throws StorageException {
		return fileService.store(file, commonStorage, file.getOriginalFilename());
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public boolean handleFileDownload(@RequestBody MultipartFile file) {
		return false;
	}

	@GetMapping("/{filename:.+}")
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws StorageFileNotFoundException {

		Resource file = fileService.loadAsResource(filename, commonStorage);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

}
