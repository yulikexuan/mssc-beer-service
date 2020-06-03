//: guru.sfg.mssc.beer.service.web.controller.BeerController.java


package guru.sfg.mssc.beer.service.web.controller;


import guru.sfg.mssc.beer.service.domain.services.IBeerService;
import guru.sfg.mssc.beer.service.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final IBeerService beerService;

    @Autowired
    public BeerController(IBeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("id") UUID id) {
        BeerDto beerDto = this.beerService.getById(id);
        return new ResponseEntity<>(beerDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@Valid @RequestBody BeerDto beerDto) {

        return new ResponseEntity(this.beerService.saveNewBeer(beerDto),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBeerById(
            @PathVariable("id") UUID id, @Valid @RequestBody BeerDto beerDto) {

        return new ResponseEntity(this.beerService.updateBeer(id, beerDto),
                HttpStatus.NO_CONTENT);
    }

}///:~