package dao;

import com.application.Moving.App.dao.JdbcBoxDao;
import com.application.Moving.App.model.Box;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcBoxDaoTest extends BaseDaoTests {

    private static final Box BOX_1 = new Box
            (1, "Storage Unit", 1, 1);

    private static final Box BOX_2 = new Box
            (2, "Storage Unit", 2, 1);
    private static final Box BOX_3 = new Box
            (3, "Storage Unit", 3, 2);
    private static final Box BOX_4 = new Box
            (4, "Moving Truck", 4, 2);

    private JdbcBoxDao dao;

    private Box testBox;

    @Before
    public void setup() {
        dao = new JdbcBoxDao(dataSource);
        testBox = new Box(99, "Basement", 3, 2);
    }

    @Test
    public void get_returns_correct_box() {
        Box result = dao.get(BOX_1.getBoxId());
        Assert.assertNotNull("get returned null", result);
        assertBoxesMatch("get returned wrong or partial data", BOX_1, result);
    }

    @Test
    public void getAll_returns_list_of_all_boxes() {
        List<Box> boxes = dao.getAll();

        Assert.assertEquals("getAll failed to return all boxes", 7, boxes.size());
        assertBoxesMatch("getAll returned wrong or partial data", BOX_1, boxes.get(0));
        assertBoxesMatch("getAll returned wrong or partial data", BOX_2, boxes.get(1));
    }

    @Test
    public void get_returns_null_when_id_not_found() {
        Box box = dao.get(99);
        Assert.assertNull("get failed to return null for id not in database", box);
    }

    @Test
    public void create_returns_box_with_id_and_expected_values() {
        Box createdBox = dao.create(testBox);
        Assert.assertNotNull("create returned null", createdBox);

        Integer newId = createdBox.getBoxId();
        Assert.assertTrue("create failed to return a product with an id", newId > 0);

        testBox.setBoxId(newId);
        assertBoxesMatch("create returned product with wrong or partial data", testBox, createdBox);
    }

    @Test
    public void getByCategory_returns_list_of_boxes_for_category() {
        List<Box> boxes = dao.getByCategory(BOX_1.getCategoryId());
        Assert.assertEquals("getByCategory failed to return all boxes", 1, boxes.size());
        assertBoxesMatch("getByCategory returned wrong or partial data", BOX_1, boxes.get(0));
    }

    @Test
    public void getByUserId_returns_list_of_boxes_for_user() {
        List<Box> boxes = dao.getByUserId(BOX_1.getUserId());
        Assert.assertEquals("getByUserId failed to return all boxes", 3, boxes.size());
        assertBoxesMatch("getByUserId returned wrong or partial data", BOX_1, boxes.get(0));
    }

    @Test
    public void searchByLocation_returns_expected_boxes() {
        List<Box> boxes = dao.searchByLocation(BOX_1.getStorageLocation());
        Assert.assertEquals("searchByLocation failed to return all boxes", 3, boxes.size());
        assertBoxesMatch("searchByLocation returned wrong or partial data", BOX_1, boxes.get(0));
        assertBoxesMatch("searchByLocation returned wrong or partial data", BOX_3, boxes.get(2));
    }


    private void assertBoxesMatch(String message, Box expected, Box actual) {
        Assert.assertEquals("BoxId is incorrect", expected.getBoxId(), actual.getBoxId());
        Assert.assertEquals("CategoryId is incorrect", expected.getCategoryId(), actual.getCategoryId());
        Assert.assertEquals("UserId is incorrect", expected.getUserId(), actual.getUserId());
        Assert.assertEquals("Storage Location is incorrect", expected.getStorageLocation(), actual.getStorageLocation());
    }
}
