package service;

import com.application.Moving.App.dao.BoxDao;
import com.application.Moving.App.dao.CategoryDao;
import com.application.Moving.App.dao.ItemDao;
import com.application.Moving.App.dao.UserDao;
import com.application.Moving.App.model.Box;
import com.application.Moving.App.model.User;
import com.application.Moving.App.service.BoxService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BoxServiceTest {

    private static final User USER_1 = new User
            (1, "dgress", "Diane", "Gress", "password1");

    private static final User USER_2 = new User
            (1, "rkuth", "Ruth", "Keysor", "password2");

    @Mock
    private Principal principal;

    @Mock
    private BoxDao mockBoxDao;
    @Mock
    private ItemDao mockItemDao;
    @Mock
    private UserDao userDao;
    @Mock
    private CategoryDao mockCategoryDao;

    @InjectMocks
    private BoxService boxService;

    @Test
    public void getBoxesForUser_returns_correct_list_for_user() {
        setupMocksForUser(USER_1);
        List<Box> expected = new ArrayList<>();
        when(mockBoxDao.getByUserId(USER_1.getUserId())).thenReturn(expected);

        List<Box> actual = boxService.getBoxesForUser(principal);

        assertEquals("getCartItemsForUser did not return correct list for user", expected, actual);
    }

    private void setupMocksForUser(User user) {
        String username = user.getUsername();
        when(principal.getName()).thenReturn(username);
        when(userDao.getByUsername(username)).thenReturn(user);
    }

  }
