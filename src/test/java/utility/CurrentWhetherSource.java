package utility;

import utility.models.Condition;
import utility.models.Current;
import utility.models.CurrentWhether;
import utility.models.Location;

public class CurrentWhetherSource {
    private static Location location = new Location("Paris", "Ile-de-France", "France", 48.87,
            2.33, "Europe/Paris", 1720966795, "2024-07-14 16:19");
    private static Condition condition = new Condition("Солнечно", "//cdn.weatherapi.com/weather/64x64/day/113.png",
            1000);
    private static Current current = new Current(1720966500, "2024-07-14 16:15", 22.2,
            72.0, 1, condition, 4.3, 6.8, 210, "SSW", 1013.0,
            29.91, 0.0, 0.0, 50, 0, 24.2, 75.6, 22.3,
            72.1, 24.2, 75.6, 9.0, 48.2, 10.0, 6.0, 6.0,
            7.6, 12.2);

    public static CurrentWhether whether = new CurrentWhether(location,current);
}
