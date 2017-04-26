package serguma.notificacionaleatoria.activitys;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.concurrent.ExecutionException;

import serguma.notificacionaleatoria.R;
import serguma.notificacionaleatoria.entidad.Descarga;
import serguma.notificacionaleatoria.utils.Utils;

public class VerNotificacion extends AppCompatActivity {

    private Descarga descarga;
    private Utils utils = new Utils(this);
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_notificacion);

        videoView = (VideoView) findViewById(R.id.mivideo);
        ImageView imageView = (ImageView) findViewById(R.id.mimagen);

        descarga = (Descarga) getIntent().getExtras().getSerializable("DESCARGA");
        String path = descarga.getUrl();


        if(descarga.getTipo() == 2 ) {
            //descarga trae una imagen

            Log.d(getClass().getCanonicalName(), "O" + descarga.getUrl());

            //oculto el objeto video
            videoView.setVisibility(View.GONE);

            //cargo la imagen
            try {
                imageView.setImageBitmap(utils.cargarImagen(path));
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }else{
            imageView.setVisibility(View.GONE);

            //Cambio la orientación de la app, para los videos
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            path = descarga.getUrl();

            if(path == ""){
                //ponemos el que está en local
                path = "android.resource://"+getPackageName()+"/"+R.raw.morning;
            }

            //Cargamos el video

            Uri urivideo = Uri.parse(path);

            videoView.setVideoURI(urivideo);
            videoView.setMediaController(new MediaController(this));
            videoView.requestFocus();
            videoView.start();

            Log.d(getClass().getCanonicalName(), ""+path);
        }

    }



}
