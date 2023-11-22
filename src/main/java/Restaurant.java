import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class Restaurant {
    private String name;
    private String location;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private List<Item> menu = new ArrayList<>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        LocalTime currentTime = LocalTime.now();
        return currentTime.isAfter(openingTime) && currentTime.isBefore(closingTime);
    }

    public List<Item> getMenu() {
        return menu;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name, price);
        menu.add(newItem);
    }

    public void removeFromMenu(String itemName) throws itemNotFoundException {
        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null) {
            throw new itemNotFoundException(itemName);
        }
        menu.remove(itemToBeRemoved);
    }

    public void displayDetails() {
        System.out.println("Restaurant:" + name + "\n"
                + "Location:" + location + "\n"
                + "Opening time:" + openingTime + "\n"
                + "Closing time:" + closingTime + "\n"
                + "Menu:" + "\n" + getMenuAsString());
    }

    public String getName() {
        return name;
    }
    
    


    private Item findItemByName(String itemName) {
        return menu.stream()
                .filter(item -> item.getName().equals(itemName))
                .findFirst()
                .orElse(null);
    }

    private String getMenuAsString() {
        StringBuilder menuString = new StringBuilder();
        for (Item item : menu) {
            menuString.append(item.getName()).append(": $").append(item.getPrice()).append("\n");
        }
        return menuString.toString();
    }

}
