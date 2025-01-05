package org.example.laborator_10_java.Controller;
import org.example.laborator_10_java.Model.Carte;
import org.example.laborator_10_java.Repository.CarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarteController {

    @Autowired
    private CarteRepository carteRepository;

    @GetMapping("/lista-carti")
    public String listaCarti(Model model) {
        model.addAttribute("carti", carteRepository.findAll());
        model.addAttribute("mesaj", "Lista cartilor preluate prin repository");
        return "carti";
    }


    @PostMapping("/operatii")
    public String operatii(@RequestParam(required = false) String isbn,
                           @RequestParam(required = false) String titlu,
                           @RequestParam(required = false) String autor,
                           @RequestParam(required = false) String adauga,
                           @RequestParam(required = false) String modifica,
                           @RequestParam(required = false) String sterge,
                           @RequestParam(required = false) String filtreaza,
                           Model model) {

        String mesaj = "";

        if (adauga != null) {
            if (isbn.isEmpty() || titlu.isEmpty() || autor.isEmpty()) {
                mesaj = "Adaugarea nu se realizează dacă nu completaţi toate caracteristicile!";
            } else {
                carteRepository.save(new Carte(isbn, titlu, autor));
                mesaj = "Adaugare realizata cu succes!";
            }
        } else if (modifica != null) {
            if (carteRepository.existsById(isbn)) {
                Carte carte = carteRepository.findById(isbn).get();
                carte.setTitlu(titlu);
                carte.setAutor(autor);
                carteRepository.save(carte);
                mesaj = "Cartea cu ISBN-ul " + isbn + " a fost modificata!";
            } else {
                mesaj = "Nu se gaseste nici o carte cu isbn-ul introdus.";
            }
        } else if (sterge != null) {
            if (carteRepository.existsById(isbn)) {
                carteRepository.deleteById(isbn);
                mesaj = "Cartea cu ISBN-ul " + isbn + " a fost stearsa!";
            } else {
                mesaj = "Nu se gaseste nici o carte cu isbn-ul introdus.";
            }
        } else if (filtreaza != null) {
            if (autor.isEmpty()) {
                model.addAttribute("carti", carteRepository.findAll());
                mesaj = "Toate cartile sunt afisate.";
            } else {
                List<Carte> cartiAutor = carteRepository.findByAutor(autor);
                model.addAttribute("carti", cartiAutor);
                mesaj = "Cartile urmatoare apartin autorului " + autor + ".";
            }
        }

        model.addAttribute("mesaj", mesaj);
        model.addAttribute("carti", carteRepository.findAll());
        return "carti";
    }
}
