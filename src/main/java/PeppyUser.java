import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class PeppyUser {
    private PeppyApi api;
    private int userId;
    private String username;
    private long count300;
    private long count100;
    private long count50;
    private int playcount;
    private String ranked_score;
    private String total_score;
    private int pp_rank;
    private float level;
    private float pp_raw;
    private float accuracy;
    private int pp_country_rank;
    private String country;
    private double hits_play;


//okay that's finished sort of okay
    public PeppyUser(PeppyApi api, int id) {
        try {
            this.userId = id;
            this.api = api;
            JSONArray userData = api.getResponse("get_user?k=" + api.getTOKEN() + "&u=" + id);

            JSONObject userDataObj = userData.getJSONObject(0);
            this.username = userDataObj.get("username").toString();
            this.country = userDataObj.get("country").toString();
            this.playcount = Integer.parseInt(userDataObj.get("playcount").toString());
            this.count300 = Integer.parseInt(userDataObj.get("count300").toString());
            this.count100 = Integer.parseInt(userDataObj.get("count100").toString());
            this.count50 = Integer.parseInt(userDataObj.get("count50").toString());
            this.ranked_score = userDataObj.get("ranked_score").toString();
            this.total_score = userDataObj.get("ranked_score").toString();
            this.pp_raw = (float) Math.round(Float.parseFloat(userDataObj.get("pp_raw").toString()) * 100) / 100;
            this.username = userDataObj.get("username").toString();
            this.pp_rank = Integer.parseInt(userDataObj.get("pp_rank").toString());
        } catch (Exception e) {

        }


    }

    public PeppyScore getRecent() throws IOException {
        ArrayList<PeppyScore> recent = new ArrayList<>();
        JSONArray recentRaw = api.getResponse("get_user_recent?k=" + api.getTOKEN() + "&u=" + userId + "&limit=50");
        System.out.println(recentRaw.toString());
        return new PeppyScore(recentRaw);
    }

    public int getUserId() {
        return userId;
    }
    public double getHits_play() {
        hits_play = (double)Math.round(100*(count50 + count100 + count300)/playcount)/100;
        return hits_play;
    }

    public String getUsername() {
        return username;
    }

    public long getCount300() {
        return count300;
    }

    public long getCount100() {
        return count100;
    }

    public long getCount50() {
        return count50;
    }

    public int getPlaycount() {
        return playcount;
    }

    public String getRanked_score() {
        return ranked_score;
    }

    public String getTotal_score() {
        return total_score;
    }

    public int getPp_rank() {
        return pp_rank;
    }

    public float getLevel() {
        return level;
    }

    public float getPp_raw() {
        return pp_raw;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public int getPp_country_rank() {
        return pp_country_rank;
    }

    public String getCountry() {
        return country;
    }
}
