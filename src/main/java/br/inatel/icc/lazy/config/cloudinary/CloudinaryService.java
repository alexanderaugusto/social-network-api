package br.inatel.icc.lazy.config.cloudinary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryService {

	private Cloudinary cloudinary;
	
	@Value("${lazy.cloudinary.default}")
	private String cloudinaryDefault;
	
	public CloudinaryService() {
		Map<String, String> valuesMap = new HashMap<>();
		
		valuesMap.put("cloud_name", "alexanderaugusto");
		valuesMap.put("api_key", "424573627157699");
		valuesMap.put("api_secret", "3qyzJoneW9dmUI-yQODv1AN7sZg");
		
		cloudinary = new Cloudinary(valuesMap);
	}

	@SuppressWarnings("rawtypes")
	public Map upload(MultipartFile multipartFile, String folder) throws IOException {
		File file = convert(multipartFile);
		Map params = ObjectUtils.asMap(
		    "folder", cloudinaryDefault + "/" + folder,
		    "unique_filename", true,
		    "overwrite", true,
		    "resource_type", "image"         
		);
		Map result = cloudinary.uploader().upload(file, params);
		file.delete();
		return result;
	}

	@SuppressWarnings("rawtypes")
	public Map delete(String id) throws IOException {
		Map result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
		return result;
	}

	private File convert(MultipartFile multipartFile) throws IOException {
		File file = new File(multipartFile.getOriginalFilename());
		FileOutputStream fo = new FileOutputStream(file);
		fo.write(multipartFile.getBytes());
		fo.close();
		return file;
	}
}
