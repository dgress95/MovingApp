package dao;

import com.application.Moving.App.dao.JdbcCategoryDao;
import com.application.Moving.App.model.Category;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JdbcCategoryDaoTest extends BaseDaoTests {

    private static final Category CATEGORY_1 = new Category
        (1, "Bedroom");
    private static final Category CATEGORY_2 = new Category
        (2, "Bathroom");
    private static final Category CATEGORY_3 = new Category
            (3, "Kitchen");
    private static final Category CATEGORY_4 = new Category
            (4, "Laundry");

    private JdbcCategoryDao dao;

    private Category testCategory;

    @Before
    public void setup() {
        dao = new JdbcCategoryDao(dataSource);
        testCategory = new Category(99, "Locker");
    }

    @Test
    public void get_returns_correct_Category() {
        Category result = dao.get(CATEGORY_1.getCategoryId());
        Assert.assertNotNull("get returned null", result);
        assertCategoriesMatch("get returned wrong or partial data", CATEGORY_1, result);
    }

    @Test
    public void create_returns_category_with_id_and_expected_values() {
        Category created = dao.create(testCategory);
        Assert.assertNotNull("create returned null", created);

        Integer newId = created.getCategoryId();
        Assert.assertTrue("create failed to return a product with an id", newId > 0);

        testCategory.setCategoryId(newId);
        assertCategoriesMatch("create returned category with wrong or partial data", testCategory, created);
    }

    @Test
    public void update_returns_category_with_expected_values() {
        Category category = new Category();
        category.setCategoryId(CATEGORY_1.getCategoryId());

        category.setName("Hideout");

        Category updatedCategory = dao.update(category);
        int newId = updatedCategory.getCategoryId();
        Category retrievedCategory = dao.get(newId);

        assertCategoriesMatch("update did not save correct data", updatedCategory, retrievedCategory);
    }

    @Test
    public void delete_removes_category_from_database() {
        dao.delete(CATEGORY_1.getCategoryId());
        Category category = dao.get(CATEGORY_1.getCategoryId());
        Assert.assertNull(category);
    }


    private void assertCategoriesMatch(String message, Category expected, Category actual) {
        Assert.assertEquals("CategoryId is incorrect", expected.getCategoryId(), actual.getCategoryId());
        Assert.assertEquals("Category name is inccorect", expected.getName(), actual.getName());
    }
}
