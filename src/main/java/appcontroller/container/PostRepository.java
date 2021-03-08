package appcontroller.container;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<postController, Long> {

}
