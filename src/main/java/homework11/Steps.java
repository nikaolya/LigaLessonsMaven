package homework11;

import com.codeborne.selenide.Condition;

import static homework11.PageObject.scrollDown;

public class Steps {

    private PageObject listingPage;

    public Steps(){
        listingPage = PageObject.getPageObject();
    }

    // Поиск товара на странице. До тех пор, пока товар не найден, происходит прокрутка страниц вправо
    public void findElement(String title){
        Pagination pagination = listingPage.getPagination();

        ProductCard productCard = listingPage.getProductCard(title);
        while (!productCard.getPriceLabel().exists()){
            // scrollDown - функция прокрутки страницы, перелистывая товары на странице (описана в PageObject)
            scrollDown(productCard.getPriceLabel());
            if (productCard.getPriceLabel().exists()){
                break;
            }
            pagination.scrollRight().shouldBe(Condition.visible).click();
        }
        // Сейчас выводится название найденного элемента,
        // но в принципе можно заменить getTitle() на другую функцию и вывести другой элемент
        System.out.println(productCard.getTitle().shouldBe(Condition.visible).getText());
    }

    // Установка check box
    public void checkCheckBox(String categoryName, String filterName){
        Filter filter = listingPage.getFilter(categoryName, filterName);
        filter.getCheckBox().scrollIntoView("{behavior: \"smooth\", block: \"center\", inline: \"center\"}");
        // Перед установкой происходит проверка, что он еще не установлен
        String checkBoxState = filter.getCheckBoxState().getAttribute("class");
        if (!checkBoxState.contains("checkbox__icon_checked")){
            filter.getCheckBox().click();
        }
    }

    // Снятие check box
    public void uncheckCheckBox(String categoryName, String filterName){
        Filter filter = listingPage.getFilter(categoryName, filterName);
        filter.getCheckBox().scrollIntoView("{behavior: \"smooth\", block: \"center\", inline: \"center\"}");
        // Перед снятием происходит проверка, что он установлен
        String checkBoxState = filter.getCheckBoxState().getAttribute("class");
        if (checkBoxState.contains("checkbox__icon_checked")){
            filter.getCheckBox().click();
        }
    }

    // Установка toggle button. В принципе работает в обе стороны (и чтобы установить, и чтобы снять)
    public void setToggleButton(String filterName){
        Filter filter = listingPage.getFilter(filterName);
        filter.getToggleButton().scrollIntoView("{behavior: \"smooth\", block: \"center\", inline: \"center\"}");
        filter.getToggleButton().shouldBe(Condition.visible).click();
    }

    // Установка табличного вида отображения (grid)
    public void setGridView(){
        ListingViewSwitcher listingViewSwitcher = new ListingViewSwitcher();
        String listingType = listingViewSwitcher.getViewState().getAttribute("class");
        if (listingType.contains("listing-view-switcher__pointer--list")){
            listingViewSwitcher.getViewSwitcher().click();
        }
    }

    // Установка отображение товаров списком (она не используется, но вроде по заданию должна была быть)
    public void setListView(){
        ListingViewSwitcher listingViewSwitcher = new ListingViewSwitcher();
        String listingType = listingViewSwitcher.getViewState().getAttribute("class");
        if (listingType.contains("listing-view-switcher__pointer--grid")){
            listingViewSwitcher.getViewSwitcher().click();
        }
    }

}
