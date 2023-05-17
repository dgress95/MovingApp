package dao;

import com.application.Moving.App.dao.JdbcBoxDao;
import com.application.Moving.App.model.Box;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;

public class JdbcBoxDaoTest {

    private static final Box BOX_1 = new Box
            (1, "Basement", 1, 1);

    private static final Box BOX_2 = new Box
            (2, "Storage Unit", 2, 1);
    private static final Box BOX_3 = new Box
            (3, "Mom's House", 2, 2);
    private static final Box BOX_4 = new Box
            (4, "Basement", 3, 2);

    private JdbcBoxDao dao;

//    @Before
//    public void setup() {
//        dao = new JdbcBoxDao();
//    }
    // need to figure out the whole database thing

    @Test
    public void getById_returns_correct_box() {
        Box result = dao.get(BOX_1.getBoxId());
        Assert.assertNotNull("get returned null", result);
        assertBoxesMatch("get returned wrong or partial data", BOX_1, result);
    }

    private void assertBoxesMatch(String message, Box expected, Box actual) {
        Assert.assertEquals("BoxId is incorrect", expected.getBoxId(), actual.getBoxId());
        Assert.assertEquals("CategoryId is incorrect", expected.getCategoryId(), actual.getCategoryId());
        Assert.assertEquals("UserId is incorrect", expected.getUserId(), actual.getUserId());
        Assert.assertEquals("Storage Location is incorrect", expected.getStorageLocation(), actual.getStorageLocation());
    }
}
