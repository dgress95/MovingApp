package com.application.Moving.App.service;

import com.application.Moving.App.dao.BoxDao;
import com.application.Moving.App.dao.CategoryDao;
import com.application.Moving.App.dao.ItemDao;
import com.application.Moving.App.dao.UserDao;
import com.application.Moving.App.model.Box;
import com.application.Moving.App.model.User;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class BoxService {

    private BoxDao boxDao;
    private UserDao userDao;
    private CategoryDao categoryDao;
    private ItemDao itemDao;

    public BoxService(BoxDao boxDao, UserDao userDao, CategoryDao categoryDao, ItemDao itemDao) {
        this.boxDao = boxDao;
        this.userDao = userDao;
        this.categoryDao = categoryDao;
        this.itemDao = itemDao;
    }

    public List<Box> getBoxesForUser(Principal principal) {
        User user = getUser(principal);
        List<Box> results = new ArrayList<>();
        results = boxDao.getByUserId(user.getUserId());
        return results;
    }

    private User getUser(Principal principal) {
        String username = principal.getName();
        User user = userDao.getByUsername(username);
        return user;
    }

    // need to create methods to:
    // add items to box (do same as cart service method)
    // remove items from box
    // add items to category
    // remove items from category
    // probably need to move add box to category methods here

    // may need to consider creating an Item Service

}
