import darthrusya.WeatherInformer.WeatherForecast;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        WeatherForecast wf = new WeatherForecast();
        wf.execute();
    }
}
