import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import java.io.IOException;
//finished
public class PeppyApi {
    private String TOKEN;
    public PeppyApi(String token) throws IOException {
        this.TOKEN = token;
        if(this.testToken(TOKEN))
            System.out.println("Token is valid");
        else
            System.out.println("Token is invalid");


    }
    public PeppyUser updateUserStats(PeppyUser user) throws IOException {
        return new PeppyUser(this,user.getUserId());
    }
    public boolean testToken(String token) throws IOException {
        /*
        Returns true if token is valid
         */
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://osu.ppy.sh/api/get_user?k=" + TOKEN);
        CloseableHttpResponse response1 = httpclient.execute(httpGet);
        String response;
        try {
            response = response1.getStatusLine().toString();
            if(response.contains("200"))
                return true;
            else return false;
        } finally {
            response1.close();
        }
    }
    public JSONArray getResponse(String request) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://osu.ppy.sh/api/" + request);
        CloseableHttpResponse response1 = httpclient.execute(httpGet);
        try {
//            System.out.println(response1.getStatusLine());
            HttpEntity entity1 = response1.getEntity();
            String retSrc = EntityUtils.toString(entity1);
//            System.out.println("Received response " + retSrc);
            EntityUtils.consume(entity1);
            return new JSONArray(retSrc);

        } finally {
            response1.close();
        }
    }

    public String getTOKEN() {
        return TOKEN;
    }
}
