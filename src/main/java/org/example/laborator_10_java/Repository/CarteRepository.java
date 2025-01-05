package org.example.laborator_10_java.Repository;
import org.example.laborator_10_java.Model.Carte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteRepository extends JpaRepository<Carte, String> {
    List<Carte> findByAutor(String autor);
}
