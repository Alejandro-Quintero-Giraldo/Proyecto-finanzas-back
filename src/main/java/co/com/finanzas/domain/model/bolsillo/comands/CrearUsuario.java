package co.com.finanzas.domain.model.bolsillo.comands;

import co.com.finanzas.domain.model.bolsillo.values.Email;
import co.com.finanzas.domain.model.bolsillo.values.EsEliminado;
import co.com.finanzas.domain.model.bolsillo.values.Nombre;
import co.com.finanzas.domain.model.bolsillo.values.UsuarioId;
import co.com.sofka.domain.generic.Command;

public class CrearUsuario implements Command {
    private final UsuarioId uid;
    private final Nombre nombre;
    private final Email email;


    public CrearUsuario(UsuarioId uid, Nombre nombre, Email email) {
        this.uid = uid;
        this.nombre = nombre;
        this.email = email;
    }

    public UsuarioId getUid() {
        return uid;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Email getEmail() {
        return email;
    }
}
