//: guru.sfg.mssc.beer.service.web.controller.BeerController.java


package guru.sfg.mssc.beer.service.web.controller;


import guru.sfg.mssc.beer.service.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    @GetMapping("/{id}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("id") UUID id) {
        // TODO: Impl
        return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@Valid @RequestBody BeerDto beer) {
        // TODO: Impl
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBeerById(
            @PathVariable("id") UUID id, @Valid @RequestBody BeerDto beer) {
        // TODO: Impl
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}///:~