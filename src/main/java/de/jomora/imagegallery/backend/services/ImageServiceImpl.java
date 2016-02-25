package de.jomora.imagegallery.backend.services;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jomora.imagegallery.persistence.Customer;
import de.jomora.imagegallery.persistence.Image;
import de.jomora.imagegallery.persistence.ImageRepository;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepository imageRepo;

	@Override
	public void add(Image image) {
		checkNotNull(image);
		imageRepo.save(image);
	}

	@Override
	public List<Image> findAll() {
		return imageRepo.findAll();
	}


}
