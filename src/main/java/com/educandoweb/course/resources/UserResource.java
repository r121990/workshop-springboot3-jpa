//Endpoints

package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired // injeção de dependência transparente ao programador
	private UserService service;

	@GetMapping // método http de requisição do tipo getting no padrão REST
	public ResponseEntity<List<User>> findAll() { // método endpoint para acessar os usuários
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}") // requisição aceitará um id dentro da url
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping // anotação de inserção no BD
	public ResponseEntity<User> insert(@RequestBody User obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping(value = "/{id}") // anotação do padrão REST com método http para deletar objetos do BD
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}") // anotação do padrão REST com método http para atualizar objetos do BD
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
