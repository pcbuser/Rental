package reservationBook;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import reservationBook.external.Book;
import reservationBook.external.BookService;

import java.util.List;

@Entity
@Table(name="Rental_table")
public class Rental {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Integer reservationId;
    private Integer bookId;
    private String rentalStatus;
    private String mType;

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    @PostUpdate
    public void onPostUpdate(){

        if(this.mType.equals("rented")){
            Rented rented = new Rented();
            BeanUtils.copyProperties(this,rented);
            rented.publish();
        }else{
            Returned returned = new Returned();
            BeanUtils.copyProperties(this,returned);
            returned.publish();
        }

        Book book = new Book();

        book.setId(Long.valueOf(this.getBookId()));

        if(this.mType.equals("rented")){
            book.setBookStatus("02");
        }else{
            book.setBookStatus("01");
        }
        BookService bookService = Application.applicationContext.getBean(BookService.class);
        bookService.bookStatusChange01(book);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    public String getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(String rentalStatus) {
        this.rentalStatus = rentalStatus;
    }




}
