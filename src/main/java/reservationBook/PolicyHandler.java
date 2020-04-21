package reservationBook;

import reservationBook.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    @Autowired
    RentalRepository rentalRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReserved_RentalListRegist(@Payload Reserved reserved){

        if(reserved.isMe()){
            Rental rental = new Rental();
            rental.setBookId(reserved.getBookId());
            rental.setRentalStatus(reserved.getReservationStatus());
            rental.setReservationId(Integer.parseInt(reserved.getId().toString()));
            rentalRepository.save(rental);
            System.out.println("##### listener RentalListRegist : " + reserved.toJson());
        }
    }

}
