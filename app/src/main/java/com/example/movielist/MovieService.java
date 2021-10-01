package com.example.movielist;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MovieService extends Service {
        private Looper serviceLooper;
        private ServiceHandler serviceHandler;

        // Handler that receives messages from the thread
        private final class ServiceHandler extends Handler {
            public ServiceHandler(Looper looper) {
                super(looper);
            }
            @Override
            public void handleMessage(Message msg) {

                stopSelf(msg.arg1);
            }
        }

        @Override
        public void onCreate() {
            // Start up the thread running the service. Note that we create a
            // separate thread because the service normally runs in the process's
            // main thread, which we don't want to block. We also make it
            // background priority so CPU-intensive work doesn't disrupt our UI.
            HandlerThread thread = new HandlerThread("ServiceStartArguments",
                    Process.THREAD_PRIORITY_BACKGROUND);
            thread.start();

            // Get the HandlerThread's Looper and use it for our Handler
            serviceLooper = thread.getLooper();
            serviceHandler = new ServiceHandler(serviceLooper);
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

            // For each start request, send a message to start a job and deliver the
            // start ID so we know which request we're stopping when we finish the job
            Message msg = serviceHandler.obtainMessage();
            msg.arg1 = startId;
            serviceHandler.sendMessage(msg);

            Log.d("service working", "service");
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String title = intent.getStringExtra("title");
            String url ="https://www.omdbapi.com/?t=" + title + "&apikey=14d1cf78";

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("Response", response);
                            Intent intent = new Intent(getApplicationContext(), MyReceiver.class);
                            intent.setAction("com.example.movielist.RESPONSE");
                            intent.putExtra("data", response);
                            sendBroadcast(intent);

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Intent intent = new Intent(getApplicationContext(), MyReceiver.class);
                    intent.setAction("com.example.movielist.RESPONSE");
                    intent.putExtra("data", "no response");
                    sendBroadcast(intent);
                }
            });

// Add the request to the RequestQueue.
            queue.add(stringRequest);

            // If we get killed, after returning from here, restart
            return START_REDELIVER_INTENT;
        }

        @Override
        public IBinder onBind(Intent intent) {
            // We don't provide binding, so return null
            return null;
        }

        @Override
        public void onDestroy() {
            Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
        }
    }
