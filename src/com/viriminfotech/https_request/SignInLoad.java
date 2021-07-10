package com.viriminfotech.https_request;

import com.viriminfotech.interfaces.SignInListener;
import com.viriminfotech.utilities.Constants;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.net.http.HttpConnectTimeoutException;

public class SignInLoad {
    private SignInListener signInListener;

    public SignInLoad(SignInListener sign) {
        this.signInListener = sign;
    }

    public void signIn(String username, String password) {
        URL url;
        try {
            url = new URL(Constants.SIGN_IN_API);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            String jsonInputString = "{\n" +
                    "    \"username\" : " + "\""+ username +"\"" + ",\n" +
                    "    \"password\" : " + "\""+ password + "\"" +
            "}";
            System.out.println(jsonInputString);

            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }


            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
                JSONObject jsonObject = new JSONObject(response.toString());
                boolean status = jsonObject.getBoolean("status");
                if (status) {
                    signInListener.onSignInSuccessful();
                }else {
                    signInListener.onSignInError(con.getResponseCode());
                }
            }
        } catch (ConnectException e) {
            e.printStackTrace();
            signInListener.onSignInError(0);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            signInListener.onSignInError(0);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            signInListener.onSignInError(0);
        } catch (IOException e) {
            e.printStackTrace();
            signInListener.onSignInError(500);
        }
    }
}
