package hello.controllers;

import hello.models.Elev;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Elev REST Controller.
 */
@RestController
public class ElevController {

    private HashMap<Integer, Elev> elevMap = new HashMap<>();

    ElevController() {
        Elev elev1 = new Elev(1, "Nume1", "CNP1", 18);
        Elev elev2 = new Elev(2, "Nume2", "CNP2", 17);
        Elev elev3 = new Elev(3, "Nume3", "CNP3", 17);
        Elev elev4 = new Elev(4, "Nume4", "CNP4", 16);

        elevMap.put(elev1.getId(), elev1);
        elevMap.put(elev2.getId(), elev2);
        elevMap.put(elev3.getId(), elev3);
        elevMap.put(elev4.getId(), elev4);
    }

    @RequestMapping(value = "/elev", method = RequestMethod.GET)
    public List<Elev> getAll() {
        return new ArrayList<Elev>(this.elevMap.values());
    }

    @RequestMapping(value = "/elev", method = RequestMethod.POST)
    public ResponseEntity saveOne() {
        Elev elev = new Elev(6, "Elev nou", "Cnp nou", 18);
        //verifica daca id-ul este duplicat
        //pe logica asta , id-ul duplicat s-ar transforma in update
        if (elevMap.containsKey(elev.getId())) {
            return new ResponseEntity(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        this.elevMap.put(elev.getId(), elev);
        return new ResponseEntity<Elev>(elev, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/elev/id", method = RequestMethod.GET)
    public ResponseEntity getOne(@PathVariable("id") int id) {
        if (!elevMap.containsKey(id)) {
            return new ResponseEntity(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Elev>(elevMap.get(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/elev/id", method = RequestMethod.PUT)
    public ResponseEntity updateOne(@PathVariable("id") int id) {
        if (!elevMap.containsKey(id)) {
            return new ResponseEntity(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }

        Elev elev = this.elevMap.get(id);

        this.elevMap.remove(id);

        Elev updatedElev = new Elev(id, elev.getNume(), elev.getCnp(), elev.getVarsta());
        this.elevMap.put(updatedElev.getId(), updatedElev);

        return new ResponseEntity<Elev>(null, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/elev/id", method = RequestMethod.DELETE)
    public ResponseEntity deleteOne(@PathVariable("id") int id) {
        if (!this.elevMap.containsKey(id)) {
            return new ResponseEntity(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        this.elevMap.remove(id);
        return new ResponseEntity(null, new HttpHeaders(), HttpStatus.OK);
    }

}
