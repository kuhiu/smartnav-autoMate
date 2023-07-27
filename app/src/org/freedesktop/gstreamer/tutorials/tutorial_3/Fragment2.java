package org.freedesktop.gstreamer.tutorials.tutorial_3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;

import com.afra55.speedometer.SpeedometerDialog;

import org.freedesktop.gstreamer.GStreamer;

import HTTPClient.HttpClient;
import smartnavsys.R;

public class Fragment2 extends Fragment implements SurfaceHolder.Callback {
    private native void nativeInit();     // Initialize native code, build pipeline, etc
    private native void nativeFinalize(); // Destroy pipeline and shutdown native code
    private native void nativePlay();     // Set pipeline to PLAYING
    private native void nativePause();    // Set pipeline to PAUSED
    private static native boolean nativeClassInit(); // Initialize native class: cache Method IDs for callbacks
    private native void nativeSurfaceInit(Object surface);
    private native void nativeSurfaceFinalize();
    private long native_custom_data;      // Native code will use this to keep private data

    private boolean is_playing_desired;   // Whether the user asked to go to PLAYING

    private Thread backgroundThread;
    boolean isRunning;
    private Handler handler = new Handler();

    private HttpClient httpClient = null;

    private SpeedometerDialog mySpeedometerDialog = null;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View testSpeedometerView = view.findViewById(R.id.test_speedometer);
        if (testSpeedometerView instanceof SpeedometerDialog) {
            mySpeedometerDialog = (SpeedometerDialog) testSpeedometerView;
        }
        // Start the web client
        httpClient = new HttpClient();
        httpClient.start();

        final ProgressBar progressBarLeft = (ProgressBar) view.findViewById(R.id.progressBar3);
        progressBarLeft.setMax(100);
        final ProgressBar progressBarCenter = (ProgressBar) view.findViewById(R.id.progressBar2);
        progressBarCenter.setMax(100);
        final ProgressBar progressBarRight = (ProgressBar) view.findViewById(R.id.progressBar1);
        progressBarRight.setMax(100);

        final AppCompatImageButton forward_direction = view.findViewById(R.id.up_direction);
        final AppCompatImageButton left_direction = view.findViewById(R.id.left_direction);
        final AppCompatImageButton right_direction = view.findViewById(R.id.right_direction);
        final AppCompatImageButton back_direction = view.findViewById(R.id.back_direction);
        final AppCompatImageButton smartLights_on = view.findViewById(R.id.button_lights_on);
        final AppCompatImageButton smartLights_off = view.findViewById(R.id.button_lights_off);
        final AppCompatImageButton smartWorkingMode_default = view.findViewById(R.id.working_mode_default);
        final AppCompatImageButton smartWorkingMode_aut_evasion = view.findViewById(R.id.working_mode_aut_evasion);
        final AppCompatImageButton play_aut_evasion = view.findViewById(R.id.play_aut_evasion);

        play_aut_evasion.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                if (action == MotionEvent.ACTION_DOWN) {
                    httpClient.setPlayAutEvasion();
                }
                return false;
            }
        });

        smartWorkingMode_default.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                if (action == MotionEvent.ACTION_DOWN) {
                    httpClient.setWorkingMode(0);
                }
                return false;
            }
        });

        smartWorkingMode_aut_evasion.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                if (action == MotionEvent.ACTION_DOWN) {
                    httpClient.setWorkingMode(1);
                }
                return false;
            }
        });

        smartLights_on.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                if (action == MotionEvent.ACTION_DOWN) {
                    httpClient.setSmartLightsOn();
                }
                return false;
            }
        });

        smartLights_off.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                if (action == MotionEvent.ACTION_DOWN) {
                    httpClient.setSmartLightsOff();
                }
                return false;
            }
        });

        forward_direction.setOnTouchListener(new View.OnTouchListener() {
            private Runnable periodicTask;
            private final int INTERVAL = 500;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                if (action == MotionEvent.ACTION_DOWN) {
                    startPeriodicTask();
                } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
                    stopPeriodicTask();
                }

                return false;
            }
            private void startPeriodicTask() {
                periodicTask = new Runnable() {
                    @Override
                    public void run() {
                        httpClient.setManualControlForward(5);
                        Log.d("PeriodicTask", "Tarea ejecutada periódicamente");
                        handler.postDelayed(this, INTERVAL);
                    }
                };
                handler.post(periodicTask);
            }
            private void stopPeriodicTask() {
                // Detener la tarea periódica
                handler.removeCallbacks(periodicTask);
            }
        });

        left_direction.setOnTouchListener(new View.OnTouchListener() {
            private Runnable periodicTask;
            private final int INTERVAL = 500;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                if (action == MotionEvent.ACTION_DOWN) {
                    startPeriodicTask();
                } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
                    stopPeriodicTask();
                }

                return false;
            }
            private void startPeriodicTask() {
                periodicTask = new Runnable() {
                    @Override
                    public void run() {
                        httpClient.setManualControlLeft(-5);
                        Log.d("PeriodicTask", "Tarea ejecutada periódicamente");
                        handler.postDelayed(this, INTERVAL);
                    }
                };
                handler.post(periodicTask);
            }
            private void stopPeriodicTask() {
                // Detener la tarea periódica
                handler.removeCallbacks(periodicTask);
            }
        });

        right_direction.setOnTouchListener(new View.OnTouchListener() {
            private Runnable periodicTask;
            private final int INTERVAL = 500;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                if (action == MotionEvent.ACTION_DOWN) {
                    startPeriodicTask();
                } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
                    stopPeriodicTask();
                }

                return false;
            }
            private void startPeriodicTask() {
                periodicTask = new Runnable() {
                    @Override
                    public void run() {
                        httpClient.setManualControlRight(5);
                        Log.d("PeriodicTask", "Tarea ejecutada periódicamente");
                        handler.postDelayed(this, INTERVAL);
                    }
                };
                handler.post(periodicTask);
            }
            private void stopPeriodicTask() {
                // Detener la tarea periódica
                handler.removeCallbacks(periodicTask);
            }
        });

        back_direction.setOnTouchListener(new View.OnTouchListener() {
            private Runnable periodicTask;
            private final int INTERVAL = 500;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                if (action == MotionEvent.ACTION_DOWN) {
                    startPeriodicTask();
                } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
                    stopPeriodicTask();
                }

                return false;
            }
            private void startPeriodicTask() {
                periodicTask = new Runnable() {
                    @Override
                    public void run() {
                        httpClient.setManualControlBack(-5);
                        Log.d("PeriodicTask", "Tarea ejecutada periódicamente");
                        handler.postDelayed(this, INTERVAL);
                    }
                };
                handler.post(periodicTask);
            }
            private void stopPeriodicTask() {
                // Detener la tarea periódica
                handler.removeCallbacks(periodicTask);
            }
        });

        // Create thread to update the window
        backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                isRunning = true;
                while (isRunning) {
                    handler.post(new Runnable() {
                        public void run() {
                            //System.out.println("Valor a actualizar:" + httpClient.getLeftDistanceSensor());
                            progressBarLeft.setProgress(httpClient.getLeftDistanceSensor());
                            progressBarCenter.setProgress(httpClient.getCenterDistanceSensor());
                            progressBarRight.setProgress(httpClient.getRightDistanceSensor());
                            mySpeedometerDialog.setCurrentNumber(httpClient.getSpeed());
                        }
                    });

                    try {
                        // Agregar un retraso para simular un proceso en curso
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        backgroundThread.start();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        try {
            isRunning = false;
            backgroundThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Called when the fragment is created.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        // Initialize GStreamer and warn if it fails
        try {
            GStreamer.init(requireContext());
        } catch (Exception e) {
            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            requireActivity().finish();
            return view;
        }

        ImageButton play = view.findViewById(R.id.button_play);
        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                is_playing_desired = true;
                nativePlay();
            }
        });

        ImageButton pause = view.findViewById(R.id.button_stop);
        pause.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                is_playing_desired = false;
                nativePause();
            }
        });

        SurfaceView sv = view.findViewById(R.id.surface_video);
        SurfaceHolder sh = sv.getHolder();
        sh.addCallback(this);

        if (savedInstanceState != null) {
            is_playing_desired = savedInstanceState.getBoolean("playing");
            Log.i("GStreamer", "Fragment created. Saved state is playing: " + is_playing_desired);
        } else {
            is_playing_desired = false;
            Log.i("GStreamer", "Fragment created. There is no saved state, playing: false");
        }

        // Start with disabled buttons, until native code is initialized
        view.findViewById(R.id.button_play).setEnabled(false);
        view.findViewById(R.id.button_stop).setEnabled(false);

        nativeInit();

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d("GStreamer", "Saving state, playing: " + is_playing_desired);
        outState.putBoolean("playing", is_playing_desired);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        nativeFinalize();
        super.onDestroy();
    }

    // Called from native code. This sets the content of the TextView from the UI thread.
    private void setMessage(final String message) {
        final TextView tv = requireView().findViewById(R.id.textview_message);
        requireActivity().runOnUiThread(new Runnable() {
            public void run() {
                tv.setText(message);
            }
        });
    }

    // Called from native code. Native code calls this once it has created its pipeline and
    // the main loop is running, so it is ready to accept commands.
    private void onGStreamerInitialized() {
        Log.i("GStreamer", "Gst initialized. Restoring state, playing: " + is_playing_desired);
        // Restore previous playing state
        if (is_playing_desired) {
            nativePlay();
        } else {
            nativePause();
        }

        // Re-enable buttons, now that GStreamer is initialized
        requireActivity().runOnUiThread(new Runnable() {
            public void run() {
                requireView().findViewById(R.id.button_play).setEnabled(true);
                requireView().findViewById(R.id.button_stop).setEnabled(true);
            }
        });
    }

    static {
        System.loadLibrary("gstreamer_android");
        System.loadLibrary("tutorial-3");
        nativeClassInit();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d("GStreamer", "Surface changed to format " + format + " width " + width + " height " + height);
        nativeSurfaceInit(holder.getSurface());
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("GStreamer", "Surface created: " + holder.getSurface());
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("GStreamer", "Surface destroyed");
        nativeSurfaceFinalize();
    }
}
