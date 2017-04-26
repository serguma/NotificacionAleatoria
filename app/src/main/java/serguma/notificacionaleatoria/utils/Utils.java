package serguma.notificacionaleatoria.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.concurrent.ExecutionException;

import serguma.notificacionaleatoria.asynctask.DescargaImagen;
import serguma.notificacionaleatoria.R;

/**
 * Created by g5 on 26/4/17.
 */

public class Utils {

    private Context ctx;

    public Utils(Context ctx) {
        this.ctx = ctx;
    }

    public Bitmap cargarImagen(String path) throws ExecutionException, InterruptedException {

        Bitmap bitmap = null;

        if(path == ""){
            //cargo la imagen almacenada en el dispositivo
            bitmap = BitmapFactory.decodeResource(ctx.getResources(), R.raw.playa2);
        }else{
            //cargo la imagen desde el servidor
            DescargaImagen descargaImagen = new DescargaImagen(ctx);
            bitmap = descargaImagen.execute(path).get();

        }

        return bitmap;
    }


}
