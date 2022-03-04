package homework7;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Warehouse {
    private int capacity;
    private int maxItemsAmount ;
    public Map<String, Integer> warehouse = new HashMap<String, Integer>();

    public Warehouse(int capacity, int maxItemsAmount) {
        this.capacity = capacity;
        this.maxItemsAmount = maxItemsAmount;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getMaxItemsAmount() {
        return maxItemsAmount;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setMaxItemsAmount(int maxItemsAmount) {
        this.maxItemsAmount = maxItemsAmount;
    }

    public void addNewItems(String itemTitle, int itemsToAdd){

        if (warehouse.size() == capacity){
            if (!(warehouse.containsKey(itemTitle)))
            {
                System.out.println("Sorry, you reached the warehouse capacity.");
                System.out.println("You can add only items already stored in the warehouse.");
                System.out.println("Please try again.");
                return;
            }
        }

        int itemNumber;
        if (warehouse.containsKey(itemTitle)){
            itemNumber = warehouse.get(itemTitle);
        } else {
            itemNumber = 0;
        }
        int itemCurrentNumber = itemNumber + itemsToAdd;
        if (itemCurrentNumber <= maxItemsAmount){
            warehouse.put(itemTitle, itemCurrentNumber);
            System.out.println("The " + itemTitle + "(s) in amount of " + itemsToAdd + " are successfully added.");
        } else{
            System.out.println("Sorry, you are approaching the item amount limit.");
            System.out.println("You can add up to " + (maxItemsAmount - itemNumber) + " " + itemTitle + "(s).");
            System.out.println("\nPlease enter");
            System.out.println("\t \"yes\" - To add items;");
            System.out.println("\t \"no\" - To discard the operation");
            System.out.print("\nEnter your desired action: ");
            Scanner scanner = new Scanner(System.in);
            String action = scanner.nextLine().toLowerCase();
            switch (action) {
                case "yes":
                    itemsToAdd = maxItemsAmount - itemNumber;
                    itemCurrentNumber = maxItemsAmount;
                    warehouse.put(itemTitle, itemCurrentNumber);
                    System.out.println("The " + itemTitle + "(s) in amount of " + itemsToAdd + " are successfully added.");
                    break;
                case "no":
                default:
                    itemCurrentNumber = itemNumber;
                    System.out.println("No items is added.");
                }
            }
            System.out.println("Current amount of the " + itemTitle + "(s) is " + itemCurrentNumber +".");
        }

    public void takeOutSomeItems(String itemTitle, int itemsToTakeOut){

        boolean isStored = warehouse.containsKey(itemTitle);
        if (!isStored){
            System.out.println("The " + itemTitle + " is not found in the warehouse.");
            return;
        }

        int itemNumber = warehouse.get(itemTitle);
        if (itemsToTakeOut <= itemNumber){
            int itemCurrentNumber = itemNumber - itemsToTakeOut;
            warehouse.put(itemTitle, itemCurrentNumber);
            System.out.println("The " + itemTitle + "(s) in amount of " + itemsToTakeOut + " are successfully taken out.");
            System.out.println("Current amount of the " + itemTitle + "(s) is " + itemCurrentNumber +".");
        } else{
            System.out.println("Sorry, there are no enough " + itemTitle + " to take out.");
            System.out.println("You take out up to " + itemNumber  + " " + itemTitle + "(s).");
            System.out.println("\nPlease enter");
            System.out.println("\t \"yes\" - To take out items;");
            System.out.println("\t \"no\" - To discard the operation");
            System.out.print("\nEnter your desired action: ");
            Scanner scanner = new Scanner(System.in);
            String action = scanner.nextLine().toLowerCase();
            switch (action) {
                case "yes":
                    removeItem(itemTitle);
                    break;
                case "no":
                default:
                    System.out.println("No items is taken out.");
                    System.out.println("Current amount of the " + itemTitle + "(s) is " + itemNumber +".");
            }
        }

    }

    public void printStoredItems(){
        switch (warehouse.size()){
            case 0:
                System.out.println("There are no items. The warehouse is empty.");
                break;
            case 1:
                System.out.println("There is " + warehouse.size() + " item stored in the warehouse.");
                break;
            case 2:
                System.out.println("There are " + warehouse.size() + " items stored in the warehouse.");
        }

        for(String item: warehouse.keySet()) {
            System.out.println(item + ": " + warehouse.get(item));
        }
    }

    public boolean searchItem (String itemTitle){
        boolean isStored = warehouse.containsKey(itemTitle);
        if (isStored){
            System.out.println("The " + itemTitle + " is found in the warehouse in the amount of " + warehouse.get(itemTitle) + ".");
        } else{
            System.out.println("The " + itemTitle + " is not found in the warehouse.");
        }
        return isStored;
    }

    public void removeItem (String itemTitle){
        boolean isStored = warehouse.containsKey(itemTitle);
        if (isStored){
            warehouse.remove(itemTitle);
            System.out.println("The " + itemTitle + " is completely removed from the warehouse.");
        } else{
            System.out.println("The " + itemTitle + " is not found in the warehouse.");
        }
    }

    // Добавленная функция экспорта содержимого в xlsx
    public void exportStoredItems() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        // Заголовок
        XSSFRow row = sheet.createRow(0);

        CellStyle style=workbook.createCellStyle();
        XSSFFont font= workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        row.createCell(0).setCellValue("Item");
        row.createCell(1).setCellValue("Amount");
        row.getCell(0).setCellStyle(style);
        row.getCell(1).setCellStyle(style);

        int index = 1;
        for(String item: warehouse.keySet()) {
            XSSFRow rowi = sheet.createRow(index);
            rowi.createCell(0).setCellValue(item);
            rowi.createCell(1).setCellValue(warehouse.get(item));
            index++;
        }

        File file = new File("result.xlsx");
        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        outFile.close();

        System.out.println("The list of the items stored in the warehouse is exported.");
    }
}
