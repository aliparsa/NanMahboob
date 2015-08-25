package DataModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ali on 8/26/2015.
 */
public class Group {

    int groupId;
    String groupName;
    String groupImage;


    public Group(int groupId, String groupName, String groupImage) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupImage = groupImage;
    }

    public static ArrayList<Group> getArrayListFromJsonArray(JSONArray jsonArray) {
        ArrayList<Group> groups = new ArrayList<Group>();
        for (int i = 0; i < jsonArray.length();i++){
            try {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Group group = new Group(jsonObject.getInt("groupId"), jsonObject.getString("groupName"), jsonObject.getString("groupImage"));
                groups.add(group);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return groups;
    }
}
