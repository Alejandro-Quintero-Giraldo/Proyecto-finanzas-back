package co.com.finanzas.domain.model.bolsillo;

import co.com.finanzas.domain.model.bolsillo.values.Email;
import co.com.finanzas.domain.model.bolsillo.values.UsuarioId;
import co.com.finanzas.domain.model.bolsillo.values.Nombre;
import co.com.sofka.domain.generic.Entity;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Usuario")
public class Usuario extends Entity<UsuarioId> {
    public final Nombre nombre;
    public final Email email;

    public Usuario(UsuarioId entityId, Nombre nombre, Email email) {
        super(entityId);
        this.nombre = nombre;
        this.email = email;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Email getEmail() {
        return email;
    }
}
