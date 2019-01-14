package com.httphunt.http;

import java.io.*;
import java.net.HttpURLConnection;

public class HttpRequester {

    public static HttpResponse makeGetRequest(String url) throws IOException {
        return makeRequest(new HttpRequest(url, HttpMethod.GET));
    }


    public static HttpResponse makeRequest(HttpRequest request) {

        try {
            HttpURLConnection conn = (HttpURLConnection) request.getUrl().openConnection();

            conn.setRequestMethod(request.getMethod().toString());

            if (request.getConnectionTimeOut() > 0)
                conn.setConnectTimeout(request.getConnectionTimeOut() * 1000);
            if (request.getHeaders() != null) {

                for (String key : request.getHeaders().keySet()) {
                    conn.setRequestProperty(key, request.getHeaderValue(key));
                }
            }


            if (request.getMethod() != HttpMethod.GET) {

                conn.setRequestProperty("Content-Type", MediaType.APPLICATION_JSON);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                os.write(request.getPayload());
                os.flush();
                os.close();

            }

            InputStream is = null;
            StringBuffer responseContent = null;

            try {
                is = conn.getInputStream();
            } catch (IOException e) {
                if (conn.getResponseCode() != 200) {
                    is = conn.getErrorStream();
                }
            }

            if (is != null) {
                BufferedReader in = new BufferedReader(new InputStreamReader(is));

                String inputLine;
                responseContent = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    responseContent.append(inputLine);
                }
                in.close();
            }

            HttpResponse httpResponse = new HttpResponse();
            httpResponse.setStatusCode(conn.getResponseCode());
            httpResponse.setResponseContent(responseContent == null ? null : responseContent.toString());


            httpResponse.setHeaders(conn.getHeaderFields());
            conn.disconnect();

            return httpResponse;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}
