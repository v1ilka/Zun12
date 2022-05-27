package com.example.zun.rest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.zun.MainActivity;
import com.example.zun.domain.Comment;
import com.example.zun.domain.Theme;
import com.example.zun.domain.User;
import com.example.zun.domain.mapper.CommentMapper;
import com.example.zun.domain.mapper.ThemeMapper;
import com.example.zun.domain.mapper.UserMapper;
import com.example.zun.fakedb.NoDb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ZunApiImpl implements ZunApi {
//    public  static final String BASE_URL = "http://10.0.2.2";
    public  static final String BASE_URL = "http://192.168.1.124:8080";
    private final Context context_context;
    private Response.ErrorListener errorListener;

    public ZunApiImpl(Context context_context) {
        this.context_context = context_context;
        errorListener = new ErrorListenerImpl();
    }

    @Override
    public void fillTheme() {
        RequestQueue queue = Volley.newRequestQueue(context_context);
        String url = BASE_URL + "/theme";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new com.android.volley.Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {

                            NoDb.THEME_LIST.clear();
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);
                                List<Comment> list = new ArrayList<>();
                                for (int j = 0; j < jsonObject.getJSONArray("commentDtoList").length(); j++) {
                                    Comment comment = new CommentMapper().commentFromThemeJsonArray(jsonObject.getJSONArray("commentDtoList").getJSONObject(j));
                                    list.add(comment);
                                }

                                User user = new UserMapper().userFromThemeJsonArray(jsonObject.getJSONObject("userDto"));

                                Theme theme = new ThemeMapper().themeFromJsonArray(jsonObject, user, list);
                                NoDb.THEME_LIST.add(theme);
                            }
                            Log.d("THEME_LIST", NoDb.THEME_LIST.toString());
                            ((MainActivity) context_context).update();
                        } catch (JSONException e) {

                            Log.d("THEME_LIST", e.getMessage());
                        }

                    }
                },

                errorListener
        );

        queue.add(jsonArrayRequest);
    }

    @Override
    public void fillUser() {
        RequestQueue queue = Volley.newRequestQueue(context_context);
        String url = BASE_URL + "/user";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new com.android.volley.Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);

                                User user = new UserMapper().userFromThemeJsonArray(jsonObject);

                                NoDb.USER_LIST.add(user);
                            }
                            Log.d("USER_LIST", NoDb.USER_LIST.toString());
                        } catch (JSONException e) {

                            Log.d("USER_LIST", e.getMessage());
                        }

                    }
                },

                errorListener
        );

        queue.add(jsonArrayRequest);
    }

    @Override
    public void fillComment() {

    }

    @Override
    public void newTheme(Theme theme) {
        RequestQueue queue = Volley.newRequestQueue(context_context);
        String url = BASE_URL + "/theme";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Response", response);

            fillTheme();
                    }
                },

                errorListener
        ) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("nameTheme", theme.getName());
                params.put("nameUser", "");

                return params;
            }
        };

        queue.add(postRequest);
    }

    @Override
    public void updateTheme(int id, String newThemeName, String newUserName, String newCommentName) {

        RequestQueue queue = Volley.newRequestQueue(context_context);
        String url = BASE_URL + "/theme/" + id + "/";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Response", response);


                        fillTheme();
                    }
                },

                errorListener
        ) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("newThemeName", newThemeName);
                params.put("newUserName", newUserName);
                params.put("newCommentName", newCommentName);
                params.put("id", String.valueOf(id));
                return params;
            }
        };

        queue.add(postRequest);
    }

    @Override
    public void deleteTheme(int id) {
        RequestQueue queue = Volley.newRequestQueue(context_context);
        String url = BASE_URL + "/theme/" + id;

        StringRequest dr = new StringRequest(Request.Method.DELETE, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        fillTheme();
                    }
                },

                errorListener
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(id));
                return params;
            }
        };

        queue.add(dr);
    }
    private class ErrorListenerImpl implements Response.ErrorListener {


        @Override
        public void onErrorResponse(VolleyError error) {

            error.printStackTrace();
            Toast.makeText(context_context, "Ошибка подключения", Toast.LENGTH_SHORT).show();
        }
    }
}
