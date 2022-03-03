package com.futark.cros2.repository;

import com.futark.cros2.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepo extends JpaRepository<Utilisateur,String> {
}
