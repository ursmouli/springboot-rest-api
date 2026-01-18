package com.app.restapi.resource;

import com.app.restapi.model.Relation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/properties")
public class PropertiesResource {

    @GetMapping("/relationships")
    public ResponseEntity<Relation[]> allRelationsShips() {
        return ResponseEntity.ok(Relation.values());
    }
}
