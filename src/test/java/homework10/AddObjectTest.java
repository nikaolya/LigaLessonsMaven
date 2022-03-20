package homework10;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class AddObjectTest {
    @Parameterized.Parameter(0)
    public String objectName;
    @Parameterized.Parameter(1)
    public int objectAmount;
    @Parameterized.Parameter(2)
    public int expectedAmount;
    @Parameterized.Parameter(3)
    public boolean expected;


    @Parameterized.Parameters(name = "addObjectTest: adding {1} {0}")
    public static Collection<Object[]> setData() {
        return Arrays.asList(new Object[][]{
                {"elephant", 1, 1, true},
                {"elephant", 10, 10, true},
                {"elephant", -1, 0, false}, // этот тест падает,
                // хотя как может быть на складе отрицательное число элементов не понятно и как операция их добавления может считаться успешной
                {"elephant", 0, 0, false}, // этот тест тоже падает,
                // хотя не понятно, как может считаться успешной операция по добавлению 0 элементов
                {"elephant", 11, 0, false}
        });
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

    // Хотела написать тест на проверку, что названия сохраняются в нижнем регистре,
    // но не нашла способа вытащить из склада названия
/*    @Test
    public void rightNameSaved(){
        storage.addObject(this.objectName, this.objectAmount);
        Assert.assertEquals(this.objectName.toLowerCase(), Storage.);
    }*/


    // Очищаем склад после каждого теста
    @After
    public void afterTest() {
        if (Storage.isInStock(this.objectName)){
            Storage.removeObject(this.objectName);
        }
    }


}
