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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/getAll")
    public List<ItemDTO> getAllItems(){
        return itemService.getAllItem();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/search/{itmCode}")
    public ItemDTO getItem(@PathVariable("itmCode") String itmCode){
        return itemService.searchItem(itmCode);
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> saveItem(@ModelAttribute ItemDTO dto){
        itemService.saveItem(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateItem(@RequestBody ItemDTO dto){
        itemService.updateItem(dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(params = "itmCode")
    public ResponseEntity<Void> deleteItem(@RequestParam("itmCode") String itmCode){
        itemService.deleteItem(itmCode);
        return ResponseEntity.noContent().build();
    }
}
