package utility.models;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Current {
    Integer last_updated_epoch;
    String last_updated;
    Double temp_c;
    Double temp_f;
    Integer is_day;
    Condition condition;
    Double wind_mph;
    Double wind_kph;
    Integer wind_degree;
    String wind_dir;
    Double pressure_mb;
    Double pressure_in;
    Double precip_mm;
    Double  precip_in;
    Integer humidity;
    Integer cloud;
    Double feelslike_c;
    Double feelslike_f;
    Double windchill_c;
    Double windchill_f;
    Double heatindex_c;
    Double heatindex_f;
    Double dewpoint_c;
    Double dewpoint_f;
    Double vis_km;
    Double vis_miles;
    Double uv;
    Double gust_mph;
    Double gust_kph;
}
