package br.com.rcardoso.challenge.user.entrypoint;

import br.com.rcardoso.challenge.user.core.domain.dto.UserDto;
import br.com.rcardoso.challenge.user.core.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping()
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(userService.read(id));
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAll(Pageable pageable) {
        Page<UserDto> list = userService.list(pageable);
        return ResponseEntity.ok().body(list.getContent());
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @NotNull @RequestBody UserDto user) {
        return ResponseEntity.ok(userService.update(id, user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id) {
        return ResponseEntity.ok(userService.delete(id));
    }

}
