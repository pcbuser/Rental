
package reservationBook.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by uengine on 2018. 11. 21..
 */

@FeignClient(name="Book", url="http://localhost:8081")
public interface BookService {

    @RequestMapping(method= RequestMethod.PATCH, path="/bookupdate")
    public void bookStatusChange01(@RequestBody Book book);

}