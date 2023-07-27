package HTTPClient;

import com.afra55.speedometer.SpeedometerDialog;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.ByteString;

import org.freedesktop.gstreamer.tutorials.tutorial_3.Fragment2;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

public final class HttpClient {
    private AtomicInteger speed = new AtomicInteger(0);
    private AtomicInteger leftDistanceSensor = new AtomicInteger(0);
    private AtomicInteger centerDistanceSensor = new AtomicInteger(0);
    private AtomicInteger rightDistanceSensor = new AtomicInteger(0);
    private final OkHttpClient client = new OkHttpClient();

    private final String ip = "10.0.0.1";
    //private final String ip = "192.168.22.63";
    private final String port = "18080";

    public final void start() {
        final long interval = 500L;
        Thread thread = new Thread((Runnable)(new Runnable() {
            public final void run() {
                while(true) {
                    HttpClient.this.requestSpeed();
                    HttpClient.this.requestLeftDistanceSensor();
                    HttpClient.this.requestCenterDistanceSensor();
                    HttpClient.this.requestRightDistanceSensor();
                    try {
                        Thread.sleep(interval);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }));
        thread.start();
    }

    private final void requestSpeed() {
        Request request = (new Request.Builder()).url("http://" + this.ip + ':' + this.port + "/api/realtime/variables/speed")
                .addHeader("Content-Type", "application/json")
                .build();
        this.client.newCall(request).enqueue((Callback)(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();
                if (response.isSuccessful() && responseBody != null) {
                    JSONObject json = null;
                    try {
                        json = new JSONObject(responseBody.string());
                        HttpClient.this.speed.set(json.getInt("speed"));
                    } catch (JSONException e) {
                        //throw new RuntimeException(e);
                    }
                    String data = "Velocidad: " + HttpClient.this.speed;
                    System.out.println(data);
                } else {
                    String var4 = "Error: " + response.code();
                    System.out.println(var4);
                }

            }

            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
                String var3 = "Error in the request: " + e.getMessage();
                System.out.println(var3);
            }
        }));
    }

    private final void requestLeftDistanceSensor() {
        Request request = (new Request.Builder()).url("http://" + this.ip + ':' + this.port + "/api/realtime/variables/leftDistanceSensor")
                .addHeader("Content-Type", "application/json")
                .build();
        this.client.newCall(request).enqueue((Callback)(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();
                if (response.isSuccessful() && responseBody != null) {
                    JSONObject json = null;
                    try {
                        json = new JSONObject(responseBody.string());
                        leftDistanceSensor.set(json.getInt("leftDistanceSensor"));
                    } catch (JSONException e) {
                        //throw new RuntimeException(e);
                    }
                    String data = "LeftDistanceSensor: " + HttpClient.this.leftDistanceSensor;
                    System.out.println(data);
                } else {
                    String var4 = "Error: " + response.code();
                    System.out.println(var4);
                }

            }

            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
                String var3 = "Error in the request: " + e.getMessage();
                System.out.println(var3);
            }
        }));
    }

    private final void requestCenterDistanceSensor() {
        Request request = (new Request.Builder()).url("http://" + this.ip + ':' + this.port + "/api/realtime/variables/centerDistanceSensor")
                .addHeader("Content-Type", "application/json")
                .build();
        this.client.newCall(request).enqueue((Callback)(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();
                if (response.isSuccessful() && responseBody != null) {
                    JSONObject json = null;
                    try {
                        json = new JSONObject(responseBody.string());
                        HttpClient.this.centerDistanceSensor.set(json.getInt("centerDistanceSensor"));
                    } catch (JSONException e) {
                        //throw new RuntimeException(e);
                    }
                    String data = "centerDistanceSensor: " + HttpClient.this.centerDistanceSensor;
                    System.out.println(data);
                } else {
                    String var4 = "Error: " + response.code();
                    System.out.println(var4);
                }

            }

            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
                String var3 = "Error in the request: " + e.getMessage();
                System.out.println(var3);
            }
        }));
    }

    private final void requestRightDistanceSensor() {
        Request request = (new Request.Builder()).url("http://" + this.ip + ':' + this.port + "/api/realtime/variables/rightDistanceSensor")
                .addHeader("Content-Type", "application/json")
                .build();
        this.client.newCall(request).enqueue((Callback)(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();
                if (response.isSuccessful() && responseBody != null) {
                    JSONObject json = null;
                    try {
                        json = new JSONObject(responseBody.string());
                        HttpClient.this.rightDistanceSensor.set(json.getInt("rightDistanceSensor"));
                    } catch (JSONException e) {
                        //throw new RuntimeException(e);
                    }
                    String data = "rightDistanceSensor: " + HttpClient.this.rightDistanceSensor;
                    System.out.println(data);
                } else {
                    String var4 = "Error: " + response.code();
                    System.out.println(var4);
                }
            }

            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
                String var3 = "Error in the request: " + e.getMessage();
                System.out.println(var3);
            }
        }));
    }

    private final void requestSmartEvasionStatus(JSONObject data_json) {
        RequestBody requestContent = RequestBody.create(data_json.toString(), MediaType.get("application/json"));
        Request request = (new Request.Builder()).url("http://" + this.ip + ':' + this.port + "/api/realtime/variables/smartEvasion/status")
                .addHeader("Content-Type", "application/json")
                .post(requestContent)
                .build();
        this.client.newCall(request).enqueue((Callback)(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle error in case of request failure
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                int statusCode = response.code();
                if (statusCode == 200) {
                    System.out.println("The request was successful (status code 200)");
                } else {
                    System.out.println("The request was not successful. Status code: " + statusCode);
                }
                response.close();
            }
        }));
    };

    private final void requestManualControlForward(JSONObject data_json) {
        RequestBody requestContent = RequestBody.create(data_json.toString(), MediaType.get("application/json"));
        Request request = (new Request.Builder()).url("http://" + this.ip + ':' + this.port + "/api/realtime/variables/manualControlForward")
                .addHeader("Content-Type", "application/json")
                .post(requestContent)
                .build();
        this.client.newCall(request).enqueue((Callback)(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle error in case of request failure
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                int statusCode = response.code();
                if (statusCode == 200) {
                    System.out.println("The request was successful (status code 200)");
                } else {
                    System.out.println("The request was not successful. Status code: " + statusCode);
                }
                response.close();
            }
        }));
    };

    private final void requestManualControlBack(JSONObject data_json) {
        RequestBody requestContent = RequestBody.create(data_json.toString(), MediaType.get("application/json"));
        Request request = (new Request.Builder()).url("http://" + this.ip + ':' + this.port + "/api/realtime/variables/manualControlBack")
                .addHeader("Content-Type", "application/json")
                .post(requestContent)
                .build();
        this.client.newCall(request).enqueue((Callback)(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle error in case of request failure
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                int statusCode = response.code();
                if (statusCode == 200) {
                    System.out.println("The request was successful (status code 200)");
                } else {
                    System.out.println("The request was not successful. Status code: " + statusCode);
                }
                response.close();
            }
        }));
    };

    private final void requestManualControlLeft(JSONObject data_json) {
        RequestBody requestContent = RequestBody.create(data_json.toString(), MediaType.get("application/json"));
        Request request = (new Request.Builder()).url("http://" + this.ip + ':' + this.port + "/api/realtime/variables/manualControlLeft")
                .addHeader("Content-Type", "application/json")
                .post(requestContent)
                .build();
        this.client.newCall(request).enqueue((Callback)(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle error in case of request failure
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                int statusCode = response.code();
                if (statusCode == 200) {
                    System.out.println("The request was successful (status code 200)");
                } else {
                    System.out.println("The request was not successful. Status code: " + statusCode);
                }
                response.close();
            }
        }));
    };

    private final void requestManualControlRight(JSONObject data_json) {
        RequestBody requestContent = RequestBody.create(data_json.toString(), MediaType.get("application/json"));
        Request request = (new Request.Builder()).url("http://" + this.ip + ':' + this.port + "/api/realtime/variables/manualControlRight")
                .addHeader("Content-Type", "application/json")
                .post(requestContent)
                .build();
        this.client.newCall(request).enqueue((Callback)(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle error in case of request failure
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                int statusCode = response.code();
                if (statusCode == 200) {
                    System.out.println("The request was successful (status code 200)");
                } else {
                    System.out.println("The request was not successful. Status code: " + statusCode);
                }
                response.close();
            }
        }));
    };

    private final void requestLight(JSONObject data_json) {
        RequestBody requestContent = RequestBody.create(data_json.toString(), MediaType.get("application/json"));
        Request request = (new Request.Builder()).url("http://" + this.ip + ':' + this.port + "/api/realtime/variables/smartLights/status")
                .addHeader("Content-Type", "application/json")
                .post(requestContent)
                .build();
        this.client.newCall(request).enqueue((Callback)(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle error in case of request failure
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                int statusCode = response.code();
                if (statusCode == 200) {
                    System.out.println("The request was successful (status code 200)");
                } else {
                    System.out.println("The request was not successful. Status code: " + statusCode);
                }
                response.close();
            }
        }));
    };

    private final void requestWorkingMode(JSONObject data_json) {
        RequestBody requestContent = RequestBody.create(data_json.toString(), MediaType.get("application/json"));
        Request request = (new Request.Builder()).url("http://" + this.ip + ':' + this.port + "/api/realtime/variables/workingMode")
                .addHeader("Content-Type", "application/json")
                .post(requestContent)
                .build();
        this.client.newCall(request).enqueue((Callback)(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle error in case of request failure
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                int statusCode = response.code();
                if (statusCode == 200) {
                    System.out.println("The request was successful (status code 200)");
                } else {
                    System.out.println("The request was not successful. Status code: " + statusCode);
                }
                response.close();
            }
        }));
    };

    private final void requestPlayAutEvasion(JSONObject data_json) {
        RequestBody requestContent = RequestBody.create(data_json.toString(), MediaType.get("application/json"));
        Request request = (new Request.Builder()).url("http://" + this.ip + ':' + this.port + "/api/realtime/variables/workingMode/smartEvasion/status")
                .addHeader("Content-Type", "application/json")
                .post(requestContent)
                .build();
        this.client.newCall(request).enqueue((Callback)(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle error in case of request failure
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                int statusCode = response.code();
                if (statusCode == 200) {
                    System.out.println("The request was successful (status code 200)");
                } else {
                    System.out.println("The request was not successful. Status code: " + statusCode);
                }
                response.close();
            }
        }));
    };

    public final int getSpeed() {
        return this.speed.get();
    }

    public final int getLeftDistanceSensor() {
        return this.leftDistanceSensor.get();
    }

    public final int getCenterDistanceSensor() {
        return this.centerDistanceSensor.get();
    }

    public final int getRightDistanceSensor() {
        return this.rightDistanceSensor.get();
    }

    public final void setSmartEvasionStatus() throws JSONException {
        JSONObject data = new JSONObject();
        data.put("smartEvasionStatus", true);
        this.requestSmartEvasionStatus(data);
    }

    public final void setSmartEvasionNewTarget() {
    }

    public final void setSmartLightsOn() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("smartLightsStatus", true);
            System.out.println("Json object: " + jsonObject);
            requestLight(jsonObject);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public final void setSmartLightsOff() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("smartLightsStatus", false);
            System.out.println("Json object: " + jsonObject);
            requestLight(jsonObject);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public final void setManualControlForward(int data) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("manualControlForward", data);
                    System.out.println("Json object: " + jsonObject);
                    requestManualControlForward(jsonObject);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public final void setManualControlBack(int data) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("manualControlBack", data);
                    System.out.println("Json object: " + jsonObject);
                    requestManualControlBack(jsonObject);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public final void setManualControlLeft(int data) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("manualControlLeft", data);
                    System.out.println("Json object: " + jsonObject);
                    requestManualControlLeft(jsonObject);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public final void setManualControlRight(int data) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("manualControlRight", data);
                    System.out.println("Json object: " + jsonObject);
                    requestManualControlRight(jsonObject);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public final void setWorkingMode(int data) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("workingMode", data);
                    System.out.println("Json object: " + jsonObject);
                    requestWorkingMode(jsonObject);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
    public final void setPlayAutEvasion() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("smartEvasionStatus", 1);
                    System.out.println("Json object: " + jsonObject);
                    requestPlayAutEvasion(jsonObject);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
