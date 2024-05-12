package uz.pdp.task2.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import uz.pdp.task2.entity.User;
import uz.pdp.task2.repo.bace.Repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static uz.pdp.task2.listener.MyListener.emf;

public class UserRepo extends Repo<User, UUID> {

}
