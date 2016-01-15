package hello.controllers;

import hello.models.Elev;
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

    @RequestMapping(value = "/elev", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveOne() {

        Elev elev = new Elev(elevMap.keySet().size()+1, "Elev nou", "Cnp nou", 18);

        //verifica daca id-ul este duplicat
        if (elevMap.containsKey(elev.getId())) {
            return new ResponseEntity(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        this.elevMap.put(elev.getId(), elev);

        return new ResponseEntity<Elev>(elev, new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/elev/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getOne(@PathVariable("id") int id) {
        if (!elevMap.containsKey(id)) {
            return new ResponseEntity(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Elev>(elevMap.get(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/elev/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateOne(@PathVariable("id") int id) {
        if (!elevMap.containsKey(id)) {
            return new ResponseEntity(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }

        //fetch inainte de stergere
        Elev oldElev = this.elevMap.get(id);

        this.elevMap.remove(id);

        //update doar de id,nume si varsta
        //CNP-ul se pastreaza
        Elev updatedElev = new Elev(id, "ElevNumeNou", oldElev.getCnp(), 19);

        this.elevMap.put(updatedElev.getId(), updatedElev);

        return new ResponseEntity<Elev>(elevMap.get(updatedElev.getId()), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/elev/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOne(@PathVariable("id") int id) {

        if (!this.elevMap.containsKey(id)) {
            return new ResponseEntity(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        this.elevMap.remove(id);

        return new ResponseEntity(null, new HttpHeaders(), HttpStatus.OK);
    }

}
