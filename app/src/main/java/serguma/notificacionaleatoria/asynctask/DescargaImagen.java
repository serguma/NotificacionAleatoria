package serguma.notificacionaleatoria.asynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sergu on 25/04/2017.
 * Descarga una imagen pasandola a bitmap
 * desde una url.
 */

public class DescargaImagen extends AsyncTask<String, Void, Bitmap> {

    private Context ctx;

    public DescargaImagen(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {

        Bitmap bitmap = null;
        String urlFoto = null;
        URL url = null;
        HttpURLConnection httpURLConnection = null;
        int respuesta = 0;
        InputStream inputStream = null;


        try{
            urlFoto = urls[0];
            url = new URL(urlFoto);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            respuesta = httpURLConnection.getResponseCode();


            if(respuesta == HttpURLConnection.HTTP_OK){

                inputStream = httpURLConnection.getInputStream(); //Accedo al cuerpo del mensaje
                bitmap = BitmapFactory.decodeStream(inputStream);

                inputStream.close();

            }else{
                Log.e("MENSAJE", "Algo salió mal");
            }


        }catch (Throwable t){
            Log.e("MENSAJE", "ERROR ", t);
        }finally {
            httpURLConnection.disconnect();
        }


        return bitmap;

    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {


    }

}
