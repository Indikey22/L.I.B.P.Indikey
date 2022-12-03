//package api.rest;
//
//import api.domain.dto.UserDTO;
//import api.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(value = "api/users")
//public class UserController {
//
//    @Autowired
//    private UserService service;
//
//    @PostMapping
//    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO dto){
//        dto = service.insert(dto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<UserDTO>> findAll(){
//        List<UserDTO> list = service.findAll();
//        return ResponseEntity.ok().body(list);
//    }
//
//    @GetMapping(value = "/{id}")
//    public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
//        UserDTO dto = service.findById(id);
//        return ResponseEntity.ok().body(dto);
//    }
//
//    @PutMapping(value = "/{id}")
//    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO dto){
//        dto = service.update(id, dto);
//        return ResponseEntity.ok().body(dto);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<UserDTO> delete(@PathVariable Integer id){
//        service.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//}
