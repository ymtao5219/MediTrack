package MediTrack.Medi.repository;

import MediTrack.Medi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    // Additional custom queries can be defined here if needed
}
