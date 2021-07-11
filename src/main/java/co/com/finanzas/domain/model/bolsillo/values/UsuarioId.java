package co.com.finanzas.domain.model.bolsillo.values;

import co.com.sofka.domain.generic.Identity;

public class UsuarioId extends Identity {
    public UsuarioId(String uid) {
        super(uid);
    }

    public UsuarioId() {
    }

    public static UsuarioId of(String uid){
        return new UsuarioId(uid);
    }
}
