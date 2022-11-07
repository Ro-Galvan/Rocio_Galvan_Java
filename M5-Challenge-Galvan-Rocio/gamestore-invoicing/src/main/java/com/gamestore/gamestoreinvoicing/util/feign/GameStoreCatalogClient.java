package com.gamestore.gamestoreinvoicing.util.feign;

import com.gamestore.gamestoreinvoicing.model.Console;
import com.gamestore.gamestoreinvoicing.model.Game;
import com.gamestore.gamestoreinvoicing.model.TShirt;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* @FeignClient annotation marks our interface as a Feign client
The name parameter allows us to specify the web service that our client (app) interacts with
This name must match the name of a service registered with Eureka
this component will become an instance in the controller when it gets Autowired
*/
@FeignClient(name = "gamestore-catalog")
public interface GameStoreCatalogClient {
    /*
 This is the same annotation we use to define endpoints in our web service controllers
annotation allows us to specify which endpoint should be called when the annotated method is invoked
 In our case, a GET request will be issued to /console/{id} on the catalog-service whenever getConsoleById is invoked in our code.
     */

//    -------------Commented out some of the routes that I wasn't using but left in case I needed them later-----

//                ***************CONSOLE***************
//    create
//    @PostMapping("/console")
//    public Console createConsole(@RequestBody Console console);
//
//    //    Get all
//    @GetMapping("/console")
//    public List<Console> getAllConsoles();
////    public Console getAllConsoles(@PathVariable Console console);

//    Get by id
    @GetMapping("/console/{id}")
    public Console getConsoleById(@PathVariable long id);

    //                ***************GAME***************
//    @GetMapping("/game")
//    public List<Game> getAllGames();
    @GetMapping("/game/{id}")
    public Game getGameById (@PathVariable long id);

    //    ***************TSHIRT***************
//    @GetMapping("/tshirt")
//    public List<TShirt> getAllTShirts();
    @GetMapping("/tshirt/{id}")
    public TShirt getTShirtById (@PathVariable long id);
}
