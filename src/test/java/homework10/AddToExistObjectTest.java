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
public class AddToExistObjectTest {
    @Parameterized.Parameter(0)
    public String objectName;
    @Parameterized.Parameter(1)
    public int objectAmount;
    @Parameterized.Parameter(2)
    public int expectedAmount;
    @Parameterized.Parameter(3)
    public boolean expected;


    @Parameterized.Parameters(name = "addObjectTest: adding {1} {0} to 5 {1}")
    public static Collection<Object[]> setData() {
        return Arrays.asList(new Object[][]{
                {"elephant", 3, 8, true},
                {"elephant", -1, 5, false},
                {"elephant", 0, 5, false},
                {"elephant", 6, 5, false}
        });
    }

    // Задаем изначальное состояние склада: 5 слоников
    @Before
    public void beforeTest() {
        Storage.addObject(this.objectName, 5);
    }

    // проверяем, что объект успешно добавляется
    @Test
    public void successOfAdditionTest() {
        boolean actual = Storage.addObject(this.objectName, this.objectAmount);
        Assert.assertEquals(this.expected, actual);
    }

    // проверяем, что после добавления элемента, его количество на складе корректно увеличивается
    @Test
    public void rightAmountCalculationTest() {
        Storage.addObject(this.objectName, this.objectAmount);
        Assert.assertEquals(this.expectedAmount, Storage.getProductAmount(this.objectName));
    }

    // Очищаем склад после каждого теста
    @After
    public void afterTest() {
        if (Storage.isInStock(this.objectName)){
            Storage.removeObject(this.objectName);
        }
    }
}
