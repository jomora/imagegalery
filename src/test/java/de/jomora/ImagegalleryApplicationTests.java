package de.jomora;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import de.jomora.imagegallery.ImagegalleryApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ImagegalleryApplication.class)
@WebAppConfiguration
public class ImagegalleryApplicationTests {

	@Test
	public void contextLoads() {
	}

}
