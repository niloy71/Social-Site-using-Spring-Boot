package appcontroller.container;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<commentController, Long> {

}
