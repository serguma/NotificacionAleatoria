package serguma.notificacionaleatoria.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import serguma.notificacionaleatoria.activitys.MainActivity;

public class DatosReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(getClass().getCanonicalName(), "El mmóvil se ha encendido");

        Intent intent1 = new Intent(context, MainActivity.class);

        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);


    }
}
