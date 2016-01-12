package com.nan.shopping.base;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nan.shopping.bean.BaseResult;
import com.nan.shopping.util.UtilGson;
import com.nan.shopping.util.UtilStr;

import org.apache.http.Header;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by liu on 15/5/29.
 */
public abstract class BaseManager {

    private BaseActivity activity;
    private IResultListener resultListener;
    private Map<String, String> param;
    private File file;


    public void executePrivate(BaseActivity activity, Map<String, String> param,
                               IResultListener resultListener) {
        try {
            this.resultListener = resultListener;
            this.param = param;
            this.activity = activity;
            onExecute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executePrivate(BaseActivity activity, Map<String, String> param, File file,
                               IResultListener resultListener) {
        try {
            this.resultListener = resultListener;
            this.param = param;
            this.activity = activity;
            this.file = file;
            onExecute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public abstract void onExecute();


    /**
     * 先取缓存数据，后取网络数据
     *
     * @param url
     * @param cla
     * @param method
     */
    public void getWebThenCacheData(String url, final Type type, final int method) {
        if (method == Request.Method.GET || method == Request.Method.DELETE) {
            if (param != null) {
                url += "?";
                Set<String> set = param.keySet();
                StringBuffer strParam = new StringBuffer();
                for (String s : set) {
                    if (UtilStr.isEmpty(strParam.toString())) {
                        strParam.append(s + "=" + param.get(s));
                    } else {
                        strParam.append("&" + s + "=" + param.get(s));
                    }
                }
                url += strParam.toString();
            }
        }
        if (BaseActivity.getRequestQueueinstance().getCache().get(url) != null) {
            String temp = new String(BaseActivity.getRequestQueueinstance().getCache().get(url).data);
            BaseResult result = UtilGson.fromJson(temp, type);
            if (result != null) {
                if (result.isStatus()) {
                    sendDataSuccess(result);
                } else {
                    sendDataFail(result);
                }
            } else {
                sendDataFail(null);
            }
        }
        StringRequest request = new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                BaseResult result = UtilGson.fromJson(s, type);
                if (result != null) {
                    if (result.isStatus()) {
                        sendDataSuccess(result);
                    } else {
                        sendDataFail(result);
                    }
                } else {
                    sendDataFail(null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                resultListener.onNetError();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (param != null) {
                    return param;
                } else {
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String parsed = null;
                try {
                    parsed = new String(response.data, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
            }

        };
        request.setShouldCache(true);
        request.setTag(activity.getClass().getName());
        BaseActivity.getRequestQueueinstance().add(request);
    }

    /**
     * 先取缓存数据，如果有数据就返回, 没有数据就取网络数据
     *
     * @param url
     * @param type
     * @param method
     */
    public void getCacheIfWebData(String url, final Type type, int method) {
        if (method == Request.Method.GET || method == Request.Method.DELETE) {
            if (param != null) {
                url += "?";
                Set<String> set = param.keySet();
                StringBuffer strParam = new StringBuffer();
                for (String s : set) {
                    if (UtilStr.isEmpty(strParam.toString())) {
                        strParam.append(s + "=" + param.get(s));
                    } else {
                        strParam.append("&" + s + "=" + param.get(s));
                    }
                }
                url += strParam.toString();
            }
        }
        if (BaseActivity.getRequestQueueinstance().getCache().get(url) != null) {
            String temp = new String(BaseActivity.getRequestQueueinstance().getCache().get(url).data);
            BaseResult result = UtilGson.fromJson(temp, type);
            if (result != null) {
                if (result.isStatus()) {
                    sendDataSuccess(result);
                    return;
                }
            }
        }
        StringRequest request = new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                BaseResult result = UtilGson.fromJson(s, type);
                if (result != null) {
                    if (result.isStatus()) {
                        sendDataSuccess(result);
                    } else {
                        sendDataFail(result);
                    }
                } else {
                    sendDataFail(null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                resultListener.onNetError();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (param != null) {
                    return param;
                } else {
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String parsed = null;
                try {
                    parsed = new String(response.data, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
            }

        };
        request.setShouldCache(true);
        BaseActivity.getRequestQueueinstance().add(request);
    }

    /**
     * 直接取网络数据
     *
     * @param url
     * @param cla
     * @param method
     */
    public void getWebData(String url, final Type type, int method) {
        if (method == Request.Method.GET || method == Request.Method.DELETE) {
            if (param != null) {
                url += "?";
                Set<String> set = param.keySet();
                StringBuffer strParam = new StringBuffer();
                for (String s : set) {
                    if (UtilStr.isEmpty(strParam.toString())) {
                        strParam.append(s + "=" + param.get(s));
                    } else {
                        strParam.append("&" + s + "=" + param.get(s));
                    }
                }
                url += strParam.toString();
            }
        }
        StringRequest request = new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                BaseResult result = UtilGson.fromJson(s, type);
                if (result != null) {
                    if (result.isStatus()) {
                        sendDataSuccess(result);
                    } else {
                        sendDataFail(result);
                    }
                } else {
                    sendDataFail(null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                resultListener.onNetError();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (param != null) {

                    return param;
                } else {
                    return null;
                }
            }


            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String parsed = null;
                try {
                    parsed = new String(response.data, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        request.setShouldCache(false);
        request.setTag(activity.getClass().getName());
        BaseActivity.getRequestQueueinstance().add(request);
    }


    /**
     * 获取本地缓存数据
     *
     * @param url
     * @param type
     * @param method
     */
    public void getCacheData(String url, final Type type, final int method) {
        if (method == Request.Method.GET || method == Request.Method.DELETE) {
            if (param != null) {
                url += "?";
                Set<String> set = param.keySet();
                StringBuffer strParam = new StringBuffer();
                for (String s : set) {
                    if (UtilStr.isEmpty(strParam.toString())) {
                        strParam.append(s + "=" + param.get(s));
                    } else {
                        strParam.append("&" + s + "=" + param.get(s));
                    }
                }
                url += strParam.toString();
            }
        }
        if (BaseActivity.getRequestQueueinstance().getCache().get(url) != null) {
            String temp = new String(BaseActivity.getRequestQueueinstance().getCache().get(url).data);
            BaseResult result = UtilGson.fromJson(temp, type);
            if (result != null) {
                if (result.isStatus()) {
                    sendDataSuccess(result);
                } else {
                    sendDataFail(result);
                }
            } else {
                sendDataFail(null);
            }
        }
    }


    public void sendDataSuccess(Object result) {
        resultListener.onSuccess(result);
    }

    public void sendDataFail(Object result) {
        resultListener.onFailure(result);
    }


}
