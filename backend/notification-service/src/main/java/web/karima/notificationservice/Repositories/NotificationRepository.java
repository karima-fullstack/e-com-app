package web.karima.notificationservice.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import web.karima.notificationservice.Entites.Notification;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
}
