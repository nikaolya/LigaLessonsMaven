package homework10;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class RemoveObjectTest {
    @Parameterized.Parameter(0)
    public String objectName;
    @Parameterized.Parameter(1)
    public boolean expected;


    @Parameterized.Parameters(name = "removeObjectTest: removing {1} {0}")
    public static Collection<Object[]> setData() {
        return Arrays.asList(new Object[][]{
                {"elephant", true}, // слоник на складе есть
                {"giraffe", false} // жирафика на складе нет
        });
    }

    // Задаем изначальное состояние склада: 10 слоников, которые мы будем удалять
    @Before
    public void beforeTest() {
        Storage.addObject("elephant", 10);
    }

    // проверяем, что объект успешно удаляется
    @Test
    public void successOfRemovingTest() {
        boolean actual = Storage.removeObject(this.objectName);
        Assert.assertEquals(this.expected, actual);
    }

    // проверяем, что после удаления элемента, его количество на складе обнуляется
    @Test
    public void rightAmountCalculationTest() {
        Storage.removeObject(this.objectName);
        Assert.assertEquals(0, Storage.getProductAmount(this.objectName));
    }


    // Очищаем склад после каждого теста, на случай, если слоник не был удален до этого
    @After
    public void afterTest(){
        if (Storage.isInStock("elephant")){
            Storage.removeObject("elephant");
        }
    }


}

