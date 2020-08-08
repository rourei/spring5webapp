package guru.springframework.spring5webapp.repositories;

import guru.springframework.spring5webapp.domain.Author;
import org.springframework.data.repository.CrudRepository;

// Arguments for CRUDRepository are object type (Author) and ID value type (Long)
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
