package utilites;

import io.restassured.response.ResponseBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class BodyParser {

    public List<String> jsonBodyToListOfStrings(String jsonArrStr) {

        List<String> result = new ArrayList<>();
        String str = getSortedArray(jsonArrStr);
        str = str.replace("[", "");
        str = str.replace("]", "");
        String[] input = str.split("},");
        for (int i = 0; i < input.length; i++) {
            if (i < input.length-1) {
                result.add(input[i] + "}");
            }
        }
        //Collections.sort(result);
        return result;
    }


    private String getSortedArray(String jsonArrStr) {

        JSONArray jsonArr = new JSONArray(jsonArrStr);
        JSONArray sortedJsonArray = new JSONArray();

        List<JSONObject> jsonValues = new ArrayList<JSONObject>();
        for (
                int i = 0; i < jsonArr.length(); i++)

        {
            jsonValues.add(jsonArr.getJSONObject(i));
        }


        Collections.sort(jsonValues, new Comparator<JSONObject>() {
            Integer valA = 0;
            Integer valB = 0;
            private static final String KEY_NAME = "id";

            @Override
            public int compare(JSONObject a, JSONObject b) {

                try {
                    valA = (Integer) a.get(KEY_NAME);
                    valB = (Integer) b.get(KEY_NAME);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Ascending sorting
                //if you want to change the sort order, simply use the following:
                //return valA.compareTo(valB);
                return Integer.compare(valB, valA);

            }
        });

        for (
                int i = 0; i < jsonArr.length(); i++)

        {
            sortedJsonArray.put(jsonValues.get(i));
        }

        return sortedJsonArray.toString();
    }

}
