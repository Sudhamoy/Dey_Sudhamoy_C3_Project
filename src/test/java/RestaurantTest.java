import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RestaurantTest {
    private Restaurant restaurant;

    @BeforeEach
    public void setUp() {
        LocalTime openingTime = LocalTime.parse("10:15:30");
        LocalTime closingTime = LocalTime.parse("22:15:30");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
    }

    // Mock current time to simulate different scenarios
    @Test
public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        assertTrue(restaurant.isRestaurantOpen());

       
}

@Test
public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        assertFalse(restaurant.isRestaurantOpen());
}


    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class, () -> restaurant.removeFromMenu("French fries"));
    }
    //Passing
    @Test
    public void get_order_value_test(){
        List<String> items = Arrays.asList("Sweet Corn Soup", "Vegetable Lasagne", "Sizzling brownie");
        restaurant.addToMenu("Sweet Corn Soup", 119);
        restaurant.addToMenu("Vegetable Lasagne", 269);
        restaurant.addToMenu("Sizzling brownie", 319);

        int orderValue = restaurant.getOrderValue(items);

        assertEquals(orderValue, 707);
    }
    //Failing
    @Test
    public void get_order_value_with_invalid_item_test() {
        List<String> items = Arrays.asList("Sweet Corn Soup", "Vegetable Lasagne", "Invalid Item");
        restaurant.addToMenu("Sweet Corn Soup", 119);
        restaurant.addToMenu("Vegetable Lasagne", 269);
        restaurant.addToMenu("Sizzling brownie", 319);

        assertThrows(NullPointerException.class, () -> restaurant.getOrderValue(items));
    }

}
