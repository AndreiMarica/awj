package hello.controllers;

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
import java.util.HashMap;
import java.util.List;

/**
 * Profesor REST Controller.
 */
@RestController
public class ProfesorController {

    private HashMap<Integer, Profesor> profesorMap = new HashMap<>();

    ProfesorController() {
        Profesor prof1 = new Profesor(1, "Nume1", "Materia1", 15);
        Profesor prof2 = new Profesor(2, "Nume2", "Materia2", 20);
        Profesor prof3 = new Profesor(3, "Nume3", "Materia3", 11);
        Profesor prof4 = new Profesor(4, "Nume4", "Materia4", 4);

        profesorMap.put(prof1.getId(), prof1);
        profesorMap.put(prof2.getId(), prof2);
        profesorMap.put(prof3.getId(), prof3);
        profesorMap.put(prof4.getId(), prof4);
    }

    @RequestMapping(value = "/profesor", method = RequestMethod.GET)
    public List<Profesor> getAll() {
        return new ArrayList<Profesor>(this.profesorMap.values());
    }

    @RequestMapping(value = "/profesor", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveOne() {
        Profesor profesorNou = new Profesor(this.profesorMap.size() + 1, "NumeProfNou", "MaterieProfNou", 7);

        //verifica daca id-ul este duplicat
        if (profesorMap.containsKey(profesorNou.getId())) {
            return new ResponseEntity(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        this.profesorMap.put(profesorNou.getId(), profesorNou);
        return new ResponseEntity<Profesor>(profesorNou, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/profesor/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getOne(@PathVariable("id") int id) {

        //verifica daca id-ul exista in map
        if (!profesorMap.containsKey(id)) {
            return new ResponseEntity(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Profesor>(profesorMap.get(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/profesor/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateOne(@PathVariable("id") int id) {

        //verifica daca id-ul exista in map
        if (!profesorMap.containsKey(id)) {
            return new ResponseEntity(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }

        Profesor oldProfesor = this.profesorMap.get(id);

        this.profesorMap.remove(id);

        Profesor updatedProf = new Profesor(id, oldProfesor.getNumeProfesor(), "MaterieNoua", oldProfesor.getAniVechime());

        this.profesorMap.put(updatedProf.getId(), updatedProf);

        return new ResponseEntity<Elev>(null, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/profesor/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOne(@PathVariable("id") int id) {
        //verifica daca id-ul exista in map
        if (!this.profesorMap.containsKey(id)) {
            return new ResponseEntity(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        this.profesorMap.remove(id);
        return new ResponseEntity(null, new HttpHeaders(), HttpStatus.OK);
    }
}
