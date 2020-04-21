package reservationBook;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reservationBook.external.Book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

 @RestController
 public class RentalController {
   @Autowired
  RentalRepository rentalRepository;

  @RequestMapping(method= RequestMethod.PATCH, path="/rental/rented")
  public void rented(@RequestBody Rented rented) {
   Rental rental = new Rental();

   rental.setReservationId(rented.getReservationId());
   rental.setRentalStatus(rented.getRentalStatus());
   rental.setBookId(rented.getBookId());
   rental.setId(rented.getId());
   rental.setmType("rented");

   rentalRepository.save(rental);
  }

  @RequestMapping(method= RequestMethod.PATCH, path="/rental/returned")
  public void returned(@RequestBody Returned returned) {
   Rental rental = new Rental();

   rental.setId(returned.getId());
   rental.setRentalStatus(returned.getRentalStatus());
   rental.setBookId(returned.getBookId());
   rental.setmType("returned");
   rentalRepository.save(rental);
  }

 }
