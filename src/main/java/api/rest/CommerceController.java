package api.rest;

import api.domain.dto.CommerceDTO;
import api.domain.dto.UpdateUserStatusDTO;
import api.domain.entities.enums.UserStatus;
import api.service.CommerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/commerces")
public class CommerceController {

    @Autowired
    private CommerceService service;

    @PostMapping
    public ResponseEntity<CommerceDTO> insert(@RequestBody CommerceDTO dto){
        dto = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<CommerceDTO>> findAll(){
        List<CommerceDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/activated")
    public ResponseEntity<List<CommerceDTO>> ProfileActivated(){
        List<CommerceDTO> list = service.findAllProfileActivated();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CommerceDTO> findById(@PathVariable Integer id){
        CommerceDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CommerceDTO> update(@PathVariable Integer id, @RequestBody CommerceDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CommerceDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<UpdateUserStatusDTO> updateStatus(@PathVariable Integer id, @RequestBody UpdateUserStatusDTO dto){
        String newStatus = dto.getNewStatus();
        service.updateStatus(id, UserStatus.valueOf(newStatus));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dto);
    }
}
