//: guru.sfg.mssc.beer.service.web.controller.BeerController.java


package guru.sfg.mssc.beer.service.web.controller;


import guru.sfg.mssc.beer.service.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List> validationErrorHandler(
            MethodArgumentNotValidException e) {

        int size = e.getBindingResult().getAllErrors().size();

        List<String> errors = new ArrayList<>(size);

        e.getBindingResult().getAllErrors().stream()
                .forEach(oe -> {
                    String field = ((FieldError) oe).getField();
                    String errMsg = String.join(" ", field,
                            oe.getDefaultMessage());
                    System.out.println(">>>>>>> " + errMsg);
                    errors.add(errMsg);
                });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}///:~