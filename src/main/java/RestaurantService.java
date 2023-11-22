import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;



public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {
        Restaurant foundRestaurant = restaurants.stream()
                .filter(restaurant -> restaurant.getName().equalsIgnoreCase(restaurantName))
                .findFirst()
                .orElse(null);
        if(foundRestaurant != null){
            return foundRestaurant;
        }
        else{
            throw new restaurantNotFoundException("Restaurant not found: " + restaurantName);
        }

    }

    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        if (restaurantToBeRemoved != null) {
            restaurants.remove(restaurantToBeRemoved);
            return restaurantToBeRemoved;
        } else {
            throw new restaurantNotFoundException("Restaurant not found: " + restaurantName);
        }
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    
}
