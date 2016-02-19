package de.jomora.imagegallery.backend.services;

import java.util.List;

import de.jomora.imagegallery.persistence.Image;

public interface ImageService {
	void add(Image image);
	List<Image> findAll();

	
}
