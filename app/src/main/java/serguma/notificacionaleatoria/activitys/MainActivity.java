package serguma.notificacionaleatoria.activitys;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.ExecutionException;

import serguma.notificacionaleatoria.R;
import serguma.notificacionaleatoria.asynctask.StreamAleatoria;
import serguma.notificacionaleatoria.entidad.Descarga;
import serguma.notificacionaleatoria.utils.Utils;


public class MainActivity extends AppCompatActivity {

    private Utils utils = new Utils(this);

    public void lanzarNotiImagen(Descarga descarga) throws ExecutionException, InterruptedException {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        builder.setContentTitle("GMD");
        builder.setContentText(descarga.getMensaje());
        builder.setAutoCancel(true);

        if(descarga.getTipo() == 2 ){
            //En el caso de que sea una imagen
            String path = descarga.getUrl();

            Bitmap bitmap = utils.cargarImagen(path);

            Intent intent = new Intent(this, VerNotificacion.class);
            intent.putExtra("DESCARGA", descarga);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, (int)System.currentTimeMillis(),intent, PendingIntent.FLAG_ONE_SHOT);
            builder.setContentIntent(pendingIntent);
            builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap));

        }else{
            //en el caso de que sea un video
            Log.d(getClass().getCanonicalName(),"Video..."+descarga.getTipo());
            Intent intent = new Intent(this, VerNotificacion.class);
            intent.putExtra("DESCARGA", descarga);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, (int)System.currentTimeMillis(),intent, PendingIntent.FLAG_ONE_SHOT);
            builder.setContentIntent(pendingIntent);
        }

        //lanzo la notificación
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = builder.build();
        notificationManager.notify(200, notification);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ejecuto la llamada del asyntask
        StreamAleatoria streamAleatoria = new StreamAleatoria(this);
        streamAleatoria.execute();

    }



}
