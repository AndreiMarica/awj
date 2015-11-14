package hello.controllers;

import hello.models.Clasa;
import hello.models.Elev;
import hello.models.Profesor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Clasa REST Controller.
 */
@RestController
public class ClasaController {

    private HashMap<Integer, Clasa> clasaMap = new HashMap<>();

    ClasaController() {

        Profesor prof1 = new Profesor(1, "NumeProf1", "Materia1", 15);
        Profesor prof2 = new Profesor(2, "NumeProf2", "Materia2", 20);
        Profesor prof3 = new Profesor(3, "NumeProf3", "Materia3", 11);
        Profesor prof4 = new Profesor(4, "NumeProf4", "Materia4", 4);
        Profesor prof5 = new Profesor(5, "NumeProf5", "Materia5", 9);
        Profesor prof6 = new Profesor(6, "NumeProf6", "Materia6", 28);

        Elev elev1 = new Elev(1, "NumeElev1", "CNP1", 18);
        Elev elev2 = new Elev(2, "NumeElev2", "CNP2", 17);
        Elev elev3 = new Elev(3, "NumeElev3", "CNP3", 17);
        Elev elev4 = new Elev(4, "NumeElev4", "CNP4", 16);
        Elev elev5 = new Elev(5, "NumeElev5", "CNP5", 18);
        Elev elev6 = new Elev(6, "NumeElev6", "CNP6", 17);

        Clasa clasa1 = new Clasa(1, "11 B1", Arrays.asList(elev1, elev2), Arrays.asList(prof1, prof2));
        Clasa clasa2 = new Clasa(2, "12 A4", Arrays.asList(elev3, elev4), Arrays.asList(prof1, prof2, prof3));
        Clasa clasa3 = new Clasa(3, "11 A2", Arrays.asList(elev5, elev6), Arrays.asList(prof1, prof2, prof3, prof4, prof5, prof6));


        clasaMap.put(clasa1.getId(), clasa1);
        clasaMap.put(clasa2.getId(), clasa2);
        clasaMap.put(clasa3.getId(), clasa3);
    }

    @RequestMapping(value = "/clasa", method = RequestMethod.GET)
    public List<Clasa> getAll() {
        return new ArrayList<Clasa>(this.clasaMap.values());
    }

    @RequestMapping(value = "/clasa", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveOne() {

        Profesor profesor7 = new Profesor(7, "NumeProf7", "MaterieProfNou", 2);
        Profesor profesor8 = new Profesor(11, "NumeProf8", "MaterieProfNou", 16);

        Elev elev7 = new Elev(7, "NumeElev7", "CNP7", 17);
        Elev elev8 = new Elev(8, "NumeElev8", "CNP8", 19);

        Clasa clasaNoua = new Clasa(this.clasaMap.size() + 1, "12 R2", Arrays.asList(elev7, elev8), Arrays.asList(profesor7, profesor8));

        //verifica daca id-ul este duplicat
        if (clasaMap.containsKey(clasaNoua.getId())) {
            return new ResponseEntity(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        this.clasaMap.put(clasaNoua.getId(), clasaNoua);

        return new ResponseEntity<Clasa>(clasaNoua, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/clasa/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getOne(@PathVariable("id") int id) {
        //verifica daca id-ul exista in map
        if (!clasaMap.containsKey(id)) {
            return new ResponseEntity(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Clasa>(clasaMap.get(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/clasa/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateOne(@PathVariable("id") int id) {
        //verifica daca id-ul exista in map
        if (!clasaMap.containsKey(id)) {
            return new ResponseEntity(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }

        Clasa clasa = this.clasaMap.get(id);

        this.clasaMap.remove(id);

        Clasa updatedClasa = new Clasa(id, "Nume nou clasa ", clasa.getElevi(), clasa.getProfesori());

        this.clasaMap.put(updatedClasa.getId(), updatedClasa);

        return new ResponseEntity(null, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/clasa/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOne(@PathVariable("id") int id) {
        //verifica daca id-ul exista in map
        if (!this.clasaMap.containsKey(id)) {
            return new ResponseEntity(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        this.clasaMap.remove(id);

        return new ResponseEntity(null, new HttpHeaders(), HttpStatus.OK);
    }
}
