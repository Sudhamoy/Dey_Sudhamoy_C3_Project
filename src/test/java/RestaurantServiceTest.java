import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class RestaurantServiceTest {
    private RestaurantService restaurantService;

    @BeforeEach
    public void setUp() {
        restaurantService = new RestaurantService();
        restaurantService.addRestaurant("Amelie's cafe", "Chennai", LocalTime.parse("10:30:00"), LocalTime.parse("22:00:00"));
    }

    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException{
        Restaurant foundRestaurant = restaurantService.findRestaurantByName("Amelie's cafe");
        assertNotNull(foundRestaurant);
        assertEquals("Amelie's cafe", foundRestaurant.getName());
    }

    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() {
        // Make sure to import the exception class
        assertThrows(restaurantNotFoundException.class,
                () -> restaurantService.findRestaurantByName("Nonexistent Restaurant"));
    }
}
