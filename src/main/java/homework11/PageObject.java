package homework11;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.Objects;

public class PageObject {

    private static PageObject listingPage;

    // Карточка товара
    public ProductCard getProductCard(String productName) {
        return ProductCard.getProductCard(productName);
    }

    // Пагинация
    public Pagination getPagination(){
        return Pagination.getPagination();
    }

    // check box
    public Filter getFilter(String categoryName, String filterName) {
        return Filter.getFilter(categoryName, filterName);
    }

    // toggle button
    public Filter getFilter(String filterName) {
        return Filter.getFilter(filterName);
    }

    // Переключатель вида отображения товаров на странице
    public ListingViewSwitcher getListingViewSwitcher() {
        return ListingViewSwitcher.getListingViewSwitcher();
    }

    public PageObject() {
    }

    public static PageObject getPageObject() {
        if (Objects.isNull(listingPage)) listingPage = Selenide.page(new PageObject());
        return listingPage;
    }

    // Функция прокрутки страницы, перелистывая товары на странице
    public static SelenideElement scrollDown(SelenideElement element){
        // Определение количества товаров, отображаемых на странице
        SelenideElement paginationLimit = Selenide.$x("//mvid-dropdown[contains(@class, 'plp__pagination-limit')]//span");
        String[] str = paginationLimit.getText().split(" ");
        int elementsOnPage = Integer.valueOf(str[str.length-1]);

        int i = 1;
        SelenideElement first;
        while (!element.exists()){
            // Начинаем с первого товара на странице и мотаем вниз, пока тот что надо, не отобразится в коде страницы
            first = Selenide.$x(String.format("(//div[contains(@class, 'product-card__title-line-container')])[%d]", i));
            first.scrollIntoView("{behavior: \"smooth\", block: \"center\", inline: \"center\"}");
            i++; // это не супер логично и в принципе можно было бы поставить i+=4, но в зависимости от размера окна не всегда отображается ровно 4 товара на странице
            // и я не знаю, как это контролировать, поэтому просто перебираю последовательно, это медленно, но работает
            if (i >= elementsOnPage){
                break;
            }
        }
        Selenide.sleep(1000);
        return element;
    }


}
