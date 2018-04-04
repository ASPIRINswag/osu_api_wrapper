/*
[{
	"score_id"         : "7654321",
	"score"            : "1234567",
	"username"         : "User name",
	"count300"         : "300",
	"count100"         : "50",
	"count50"          : "10",
	"countmiss"        : "1",
	"maxcombo"         : "321",
	"countkatu"        : "10",
	"countgeki"        : "50",
	"perfect"          : "0",          // 1 = maximum combo of map reached, 0 otherwise
	"enabled_mods"     : "76",         // bitwise flag representation of mods used. see reference
	"user_id"          : "1",
	"date"             : "2013-06-22 9:11:16",
	"rank"             : "SH",
	"pp"               : "1.3019",        //Float value , 4 decimals
	"replay_available" : "1"              // 1 = osu! official servers store the replay, 0 - does not
}, { ... }, ...]
 */
// to be done as well
import org.json.JSONArray;
import org.json.JSONObject;

public class PeppyScore {
    private int score_id;
    private long score;
    private String username;
    private int count300;
    private int count100;
    private int count50;
    private int countmiss;


    public PeppyScore(JSONArray score) {
        JSONObject scoreObj = score.getJSONObject(0);
        this.count300 = scoreObj.getInt("count300");
        this.count100 = scoreObj.getInt("count100");
        this.count50 = scoreObj.getInt("count50");
        this.countmiss = scoreObj.getInt("countmiss");
//        this.username = scoreObj.getString("username");
    }
    public float accuracy(){
        int total = count300 + count100 + count50 + countmiss;
        float accuracy = count300/total + count100/(3*total) + count50/(6*total);
        return accuracy * 100;
    }

    @Override
    public String toString() {
        return "User " + username + " has a score of " + this.accuracy();
    }
}
