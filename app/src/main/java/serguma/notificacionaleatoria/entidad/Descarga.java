package serguma.notificacionaleatoria.entidad;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by sergu on 24/04/2017.
 */

public class Descarga implements Serializable {

    private int tipo;
    private String mensaje;
    private String url;

    public Descarga(int tipo, String mensaje, String url) {
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.url = url;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
