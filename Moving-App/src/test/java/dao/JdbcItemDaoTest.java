package dao;

import com.application.Moving.App.dao.JdbcItemDao;
import com.application.Moving.App.model.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class JdbcItemDaoTest extends BaseDaoTests {
    private static final Item ITEM_1 = new Item
            (1, 1, 3, "Toaster", 1, "Storage Unit", "Black toaster");
    private static final Item ITEM_2 = new Item
            (2, 1, 6, "Side Table", 1, "Basement", "Black with tan insert");
    private static final Item ITEM_3 = new Item
            (5, 2, 6,"DVDs", 20, "Basement", "Assorted DVDs");

    private JdbcItemDao dao;

    private Item testItem;

    @Before
    public void setup() {
        dao = new JdbcItemDao(dataSource);
        testItem = new Item(99, 2, 1,"Test", 3, "TestLocation", "Test description");
    }

    @Test
    public void get_returns_expected_item() {
        Item result = dao.get(ITEM_1.getItemId());
        Assert.assertNotNull("get returned null", result);
        assertItemsMatch("get returned wrong or partial data", ITEM_1, result);
    }

    @Test
    public void get_all_returns_list_of_all_items() {
        List<Item> items = dao.getAll();

        Assert.assertEquals("getAll failed to return all items", 9, items.size());
        assertItemsMatch("getAll returned wrong or partial data", ITEM_1, items.get(0));
        assertItemsMatch("getAll returned wrong or partial data", ITEM_3, items.get(4));
    }

    @Test
    public void get_returns_null_when_id_not_found() {
        Item item = dao.get(99);
        Assert.assertNull("get failed to return null for id not in database", item);
    }

    @Test
    public void create_returns_item_with_id_and_expected_values() {
        Item createdItem = dao.create(testItem);
        Assert.assertNotNull("create returned null", createdItem);

        Integer newId = createdItem.getItemId();
        Assert.assertTrue("create failed to return an item with an id", newId > 0);

        testItem.setItemId(newId);
        assertItemsMatch("create returned item with wrong or partial data", testItem, createdItem);
    }

    @Test
    public void getByCategory_returns_list_of_items_for_category() {
        List<Item> items = dao.getByCategory(ITEM_1.getCategoryId());
        Assert.assertEquals("getByCategory failed to return all items", 1, items.size());
        assertItemsMatch("getByCategory returned wrong or partial data", ITEM_1, items.get(0));
    }

    @Test
    public void getByUserId_returns_list_of_items_for_user() {
        List<Item> items = dao.getByUserId(ITEM_1.getUserId());
        Assert.assertEquals("getByUserId failed to return all items", 5, items.size());
        assertItemsMatch("getByUserId returned wrong or partial data", ITEM_1, items.get(0));
    }

    @Test
    public void searchByLocation_returns_expected_items() {
        List<Item> items = dao.searchByLocation(ITEM_1.getStorageLocation());
        Assert.assertEquals("searchByLocation failed to return all items", 4, items.size());
        assertItemsMatch("searchByLocation returned wrong or partial data", ITEM_1, items.get(0));
    }

    @Test
    public void searchByName_returns_expected_items() {
        List<Item> items = dao.searchByName(ITEM_1.getName());
        Assert.assertEquals("searchByName failed to return all items", 1, items.size());
        assertItemsMatch("searchByName returned wrong or partial data", ITEM_1, items.get(0));
    }

    @Test
    public void update_returns_updated_item() {
        Item item = new Item();
        item.setItemId(ITEM_1.getItemId());

        item.setDescription("Description");
        item.setQuantity(3);
        item.setName("Name");
        item.setUserId(1);

        Item udpatedItem = dao.update(item);
        int newId = udpatedItem.getItemId();
        Item retrievedItem = dao.get(newId);

        assertItemsMatch("update did not save correct data", udpatedItem, retrievedItem);
    }

    private void assertItemsMatch(String message, Item expected, Item actual) {
        Assert.assertEquals("ItemId is incorrect", expected.getItemId(), actual.getItemId());
        Assert.assertEquals("UserId is incorrect", expected.getUserId(), actual.getUserId());
        Assert.assertEquals("Name is incorrect", expected.getName(), actual.getName());
        Assert.assertEquals("Quantity is incorrect", expected.getQuantity(), actual.getQuantity());
        Assert.assertEquals("Description is incorrect", expected.getDescription(), actual.getDescription());
        Assert.assertEquals("Storage Location is incorrect", expected.getStorageLocation(), actual.getStorageLocation());
        Assert.assertEquals("CategoryId is incorrect", expected.getCategoryId(), actual.getCategoryId());
    }
 }
