package co.com.finanzas.domain.model.bolsillo.events;

import co.com.finanzas.domain.model.bolsillo.values.Email;
import co.com.finanzas.domain.model.bolsillo.values.Nombre;
import co.com.finanzas.domain.model.bolsillo.values.UsuarioId;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.Objects;

public class UsuarioCreado extends DomainEvent{
    private final UsuarioId uid;
    private final Nombre nombre;
    private final Email email;


    public UsuarioCreado(UsuarioId uid, Nombre nombre, Email email) {
        super("finanzas.usuario.creado");
        this.uid = uid;
        this.nombre = nombre;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioCreado that = (UsuarioCreado) o;
        return Objects.equals(uid, that.uid) && Objects.equals(nombre, that.nombre) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, nombre, email);
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
