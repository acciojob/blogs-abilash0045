package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
//        try {
            User user = userRepository1.findById(userId).get();
            Blog blog = new Blog();
            blog.setTitle(title);
            blog.setContent(content);
            blog.setUser(user);
            List<Blog> blogList = user.getBlogList();
            blogList.add(blog);
            userRepository1.save(user);
            return blog;
//        }
//        catch(NoSuchElementException e){
//            return null;
//        }
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        try {
            Blog blog = blogRepository1.findById(blogId).get();

            if (blog != null) {
                User user = blog.getUser();
                List<Blog> blogList = user.getBlogList();
                blogList.remove(blog);
                userRepository1.save(user);
                blogRepository1.deleteById(blogId);
            }
        }
        catch (Exception e){
        }
    }
}
