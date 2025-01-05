package org.example.laborator_10_java.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Carte {

    @Id
    private String isbn;

    private String titlu;
    private String autor;

    public Carte() {}

    public Carte(String isbn, String titlu, String autor) {
        this.isbn = isbn;
        this.titlu = titlu;
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
