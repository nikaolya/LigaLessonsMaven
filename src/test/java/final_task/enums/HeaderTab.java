package final_task.enums;

public enum HeaderTab {
    TAB_STATUS("Статус заказа"),
    TAB_LOGIN("Войти"),
    TAB_COMPARE("Сравнение"),
    TAB_FAV("Избранное"),
    TAB_CART("Корзина");

    private final String tabTitle;

    HeaderTab(final String tabTitle){
        this.tabTitle = tabTitle;
    }

    @Override
    public String toString() {
        return tabTitle;
    }
}
