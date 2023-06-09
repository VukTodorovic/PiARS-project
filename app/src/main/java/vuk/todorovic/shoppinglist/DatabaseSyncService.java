package vuk.todorovic.shoppinglist;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;

import android.os.Build;
import android.os.IBinder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class DatabaseSyncService extends Service {
    private static final int DATABASE_CHECK_INTERVAL = 5 * 1000;
    private DatabaseHelper database_helper;
    private HttpHelper http_helper;
    private boolean running = true;
    private static final String CHANNEL_ID = "my_channel_id";
    private static final String CHANNEL_NAME = "My Channel";

    @Override
    public void onCreate() {
        super.onCreate();

        http_helper = new HttpHelper();
        database_helper = new DatabaseHelper(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (running) {
                    try {

                        JSONArray shared_lists = http_helper.getJSONArrayFromURL("https://piars-server.cyclic.app/lists");

                        for (int i = 0; i < shared_lists.length(); i++) {
                            JSONObject jsonObject = shared_lists.getJSONObject(i);
                            String title = jsonObject.getString("name");
                            String creator = jsonObject.getString("creator");
                            boolean shared = jsonObject.getBoolean("shared");

                            if (!database_helper.queryLists(title)) {
                                database_helper.addList(title, creator, shared ? 1 : 0);
                            }

                            JSONArray shared_tasks = http_helper.getJSONArrayFromURL("https://piars-server.cyclic.app/tasks/" + title);

                            for (int j = 0; j < shared_tasks.length(); j++) {
                                JSONObject jsonObject1 = shared_tasks.getJSONObject(j);
                                String id = jsonObject1.getString("taskId");
                                String name = jsonObject1.getString("name");
                                boolean done = jsonObject1.getBoolean("done");

                                if (!database_helper.queryTasks(id)) {
                                    database_helper.addTask(name, title, id, done ? 1 : 0);
                                }
                            }
                        }

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        Thread.sleep(DATABASE_CHECK_INTERVAL);
                        sendNotification(getApplicationContext(), "Data synchronization", "Data Synced With The Server!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        running = false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void sendNotification(Context context, String title, String message) {
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Create a notification channel (required for Android Oreo and above)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationManager.createNotificationChannel(channel);
        }

        Notification.Builder builder = new Notification.Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true);

        notificationManager.notify(0, builder.build());
    }
}