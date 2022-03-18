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
public class GetFreePlacesTest {
    @Parameterized.Parameter(0)
    public String objectName;
    @Parameterized.Parameter(1)
    public int number; // количество заполняемых полок
    @Parameterized.Parameter(2)
    public int expected;

    @Parameterized.Parameters(name = "getFreePlaces: adding {1} {0}")
    public static Collection<Object[]> setData() {
        return Arrays.asList(new Object[][]{
                {"object", 0, 3},
                {"object", 1, 2},
                {"object", 2, 1},
                {"object", 3, 0},
                {"object", 4, 0}
        });
    }

    // Заполняем склад согласно изначальным условиям
    @Before
    public void beforeTest() {
        for (int i = 1; i <= this.number; i++) {
            Storage.addObject(this.objectName+"_"+i, 5);
        }
    }

    @Test
    public void getFreePlacesTest() {
        Assert.assertEquals(this.expected, Storage.getFreePlaces());
    }

    // Очищаем склад после каждого теста
    @After
    public void afterTest() {
        for (int i = 1; i <= this.number; i++) {
            if (Storage.isInStock(this.objectName+"_"+i)){
                Storage.removeObject(this.objectName+"_"+i);
            }
        }
    }

}
