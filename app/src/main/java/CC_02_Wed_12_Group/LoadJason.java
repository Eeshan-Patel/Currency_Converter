package CC_02_Wed_12_Group;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public interface LoadJason {
    public default ArrayList<Rate> loadExchangeList() {
        Gson gson = new Gson();
        ArrayList<Rate> exchangeList;
        try {
            String json = new String(Files.readAllBytes(Paths.get("./src/main/resources/Exchange.json")));
            Rate[] array = gson.fromJson(json, Rate[].class);
            return new ArrayList<>(Arrays.asList(array));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public default HashMap<String, ArrayList<Rate>>  loadTransitLogHashMap() {
        Gson gson = new Gson();
        HashMap<String, ArrayList<Rate>> map = null;
        File newFile = new File("./src/main/resources/TrasitLogs.json");

        if (newFile.length() == 0) {
            map = new HashMap<String, ArrayList<Rate>>();
            return map;
        }

        try {
            String json = new String(Files.readAllBytes(Paths.get("./src/main/resources/TrasitLogs.json")));
            Type type = new TypeToken<HashMap<String, ArrayList<Rate>>>() {}.getType();
            map = gson.fromJson(json, type);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }

    public default boolean checkIfInArrayList(String currency, ArrayList<Rate> exchangeList) {
        for(Rate i: exchangeList) {
            if (i.getName().equals(currency)) {
                return true;
            }
        }
        return false;
    }


}
