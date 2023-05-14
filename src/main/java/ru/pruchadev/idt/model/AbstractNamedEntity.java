package ru.pruchadev.idt.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractBaseEntity {
    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "userName", nullable = false)
    protected String userName;

    protected AbstractNamedEntity() {
    }

    protected AbstractNamedEntity(Integer id, String userName) {
        super(id);
        this.userName = userName;
    }

    public void setName(String name) {
        this.userName = name;
    }

    public String getName() {
        return this.userName;
    }

    @Override
    public String toString() {
        return super.toString() + '(' + userName + ')';
    }
}

