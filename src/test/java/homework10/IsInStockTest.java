package homework10;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class IsInStockTest {

    // проверяем, что объект присутствует на складе после добавления
    @Test
    public void presentAfterAdditionTest() {
        Storage.addObject("elephant", 10);
        Assert.assertTrue(Storage.isInStock("elephant"));
    }

    // проверяем, что объект изначально отсутствует на складе
    @Test
    public void notPresentInitiallyTest() {
        Assert.assertFalse(Storage.isInStock("elephant"));
    }

    // проверяем, что объект отсутствует на складе после добавления 0 объектов
    @Test
    public void presentAfterAdditionZeroTest() {
        Storage.addObject("elephant", 0);
        Assert.assertFalse(Storage.isInStock("elephant"));
    }

    // проверяем, что объект отсутствует на складе после удаления
    @Test
    public void notPresentAfterRemovingTest() {
        Storage.addObject("elephant", 10);
        Storage.removeObject("elephant");
        Assert.assertFalse(Storage.isInStock("elephant"));
    }

    // Очищаем склад после каждого теста
    @After
    public void afterTest(){
        if (Storage.isInStock("elephant")){
            Storage.removeObject("elephant");
        }
    }

}
