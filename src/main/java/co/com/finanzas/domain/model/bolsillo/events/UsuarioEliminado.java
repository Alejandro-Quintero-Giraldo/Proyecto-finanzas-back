package co.com.finanzas.domain.model.bolsillo.events;

import co.com.finanzas.domain.model.bolsillo.values.Email;
import co.com.finanzas.domain.model.bolsillo.values.EsEliminado;
import co.com.finanzas.domain.model.bolsillo.values.Nombre;
import co.com.finanzas.domain.model.bolsillo.values.UsuarioId;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.Objects;

public class UsuarioEliminado extends DomainEvent {
    private final UsuarioId uid;
    private final Nombre nombre;
    private final Email email;
    private final EsEliminado esEliminado;


    public UsuarioEliminado(UsuarioId uid, Nombre nombre, Email email, EsEliminado esEliminado) {
        super("finanzas.usuario.eliminado");
        this.uid = uid;
        this.nombre = nombre;
        this.email = email;
        this.esEliminado = esEliminado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEliminado that = (UsuarioEliminado) o;
        return Objects.equals(uid, that.uid) && Objects.equals(nombre, that.nombre) && Objects.equals(email, that.email) && Objects.equals(esEliminado, that.esEliminado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, nombre, email, esEliminado);
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

    public EsEliminado getEsEliminado() {
        return esEliminado;
    }
}
