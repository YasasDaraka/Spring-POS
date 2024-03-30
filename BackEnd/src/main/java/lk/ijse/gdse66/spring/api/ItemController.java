package lk.ijse.gdse66.spring.api;

import lk.ijse.gdse66.spring.dto.ItemDTO;
import lk.ijse.gdse66.spring.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemController {
    public ItemController() {
        System.out.println("ItemController");
    }
    @Autowired
    ItemService itemService;
    @GetMapping(path = "/getAll")
    public List<ItemDTO> getAllItems(){
        return itemService.getAllItem();
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/search/{id}")
    public ItemDTO getItem(@PathVariable("id") String id){
        return itemService.searchItem(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Void> saveItem(@ModelAttribute ItemDTO dto){
        itemService.saveItem(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateItem(@RequestBody ItemDTO dto){
        itemService.updateItem(dto);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(params = "cusId")
    public ResponseEntity<Void> deleteItem(@RequestParam("cusId") String cusId){
        itemService.deleteItem(cusId);
        return ResponseEntity.ok().build();
    }
}
