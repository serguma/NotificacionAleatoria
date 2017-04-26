package serguma.notificacionaleatoria.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import serguma.notificacionaleatoria.activitys.MainActivity;
import serguma.notificacionaleatoria.entidad.Descarga;

/**
 * Created by sergu on 24/04/2017.
 */

public class StreamAleatoria extends AsyncTask<Void, Void, ArrayList<Descarga>> {

    private static final String URL_SERVER = "http://gmd.ovh/peticiones/aleatorio/pruebas";
    private Context ctx;

    public StreamAleatoria(Context ctx) {
        this.ctx = ctx;
    }


    @Override
    protected ArrayList<Descarga> doInBackground(Void... params) {

        ArrayList<Descarga> descarga = null;

        URL url = null;
        HttpURLConnection httpURLConnection = null;
        int response = 0;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;


        try{

            url = new URL(URL_SERVER);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            response = httpURLConnection.getResponseCode();

            Gson gson = new Gson();
            Log.d(getClass().getCanonicalName(), "RESPONSE: " +response);


            switch (response){

                case HttpURLConnection.HTTP_NO_CONTENT:
                    Log.d(getClass().getCanonicalName(), "SIN MENSAJE");
                    break;

                case HttpURLConnection.HTTP_OK:
                    Log.d(getClass().getCanonicalName(), "TODO OK");

                    inputStream = httpURLConnection.getInputStream();
                    //le doy codificación para los caracteres con acentos
                    inputStreamReader = new InputStreamReader(inputStream, "iso-8859-1");
                    //obtengo los valores desde el json
                    descarga = gson.fromJson (inputStreamReader, new TypeToken<ArrayList<Descarga>>() {}.getType());

                    inputStream.close();

                    break;
                default:
                    Log.e(getClass().getCanonicalName(), "ERROR!!!!");
            }


        }catch (Throwable t){
            Log.e(getClass().getCanonicalName(), "ERROR: ", t);
        }finally {
            httpURLConnection.disconnect();
        }


        return descarga;
    }

    protected void onPostExecute(ArrayList<Descarga> descargas) {

        //Aunque solo paso un objeto, he usado un array para más adelante poder trabajar con más datos
        MainActivity activity = (MainActivity) ctx;
        try {
            //Paso el objeto de descarga a la notificación
            activity.lanzarNotiImagen(descargas.get(0));

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //super.onPostExecute(descargas);
    }
}
