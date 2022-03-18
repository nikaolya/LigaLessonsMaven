package homework10;


import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Suite.SuiteClasses({
        AddObjectTest.class,
        AddToExistObjectTest.class,
        RemoveObjectTest.class,
        IsInStockTest.class,
        GetProductAmountTest.class,
        GetFreePlacesTest.class
})
public class StorageSuiteTests {
}
