package homework10;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class GetProductAmountTest {
    // проверяем правильность возвращаемого значения, при наличии элемента
    @Test
    public void getPresentProductAmountTest() {
        String element = "giraffe";
        int expected = 7;
        Storage.addObject(element, expected); // добавляем жирафика
        int actual = Storage.getProductAmount(element);
        Assert.assertEquals(expected, actual);
    }

    // проверяем правильность возвращаемого значения, при отсутствии элемента
    @Test
    public void getNotPresentProductAmountTest() {
        String element = "giraffe"; // жирафика на складе нет
        int actual = Storage.getProductAmount(element);
        Assert.assertEquals(0, actual);
    }

    // Очищаем склад после каждого теста
    @After
    public void afterTest() {
        if (Storage.isInStock("giraffe")){
            Storage.removeObject("giraffe");
        }
    }

}
