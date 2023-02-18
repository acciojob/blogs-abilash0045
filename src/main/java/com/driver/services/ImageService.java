package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);

        List<Image> images = blog.getImageList();
        images.add(image);

        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        Image image = imageRepository2.findById(id).get();
        Blog blog = image.getBlog();
        List<Image> images = blog.getImageList();
        images.remove(image);

        blogRepository2.save(blog);
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image = imageRepository2.findById(id).get();

        String imageDimension = image.getDimensions();
        int imgDimension = Integer.parseInt(imageDimension.substring(0,2))*Integer.parseInt(imageDimension.substring(2,3));

        int screenDimension = Integer.parseInt(screenDimensions.substring(0,2))*Integer.parseInt(screenDimensions.substring(2,3));

        int maxImages = screenDimension/imgDimension;
        return maxImages;
    }
}
