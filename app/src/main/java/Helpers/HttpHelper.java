package Helpers;


import android.accounts.NetworkErrorException;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.PriorityQueue;

import Intefaces.CallBackAsync;

/**
 * Created by ashkan on 8/11/2014.
 */
public class HttpHelper {
    String  errorMessage;
    public  void post(Context context, final String serverUrl, final String params, final CallBackAsync callBack) {

        new AsyncTaskLoader<String>(context) {
            @Override
            public String loadInBackground() {
                try {

                    URL url = new URL(serverUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //Set to POST
                    connection.setDoOutput(true);
                    connection.setRequestMethod("POST");
                    connection.setReadTimeout(10000);
                    Writer writer = new OutputStreamWriter(connection.getOutputStream());
                    writer.write(params);
                    writer.flush();
                    writer.close();
                    //Get Response
                    InputStream is = connection.getInputStream();
                    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                    String line;
                    StringBuffer response = new StringBuffer();

                    while ((line = rd.readLine()) != null) {
                        response.append(line);
                        response.append('\r');
                    }

                    String result = response.toString();
                    return result;

                } catch (IOException e) {

                    return null;
                }

            }

            @Override
            public void deliverResult(String data) {
                super.deliverResult(data);
                if (data != null)
                    callBack.onSuccessFinish(data);
                else
                    callBack.onError("network error");
            }

        }.forceLoad();

    }
}
