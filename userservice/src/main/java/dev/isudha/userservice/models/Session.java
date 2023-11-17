package dev.isudha.userservice.models;

import dev.isudha.userservice.models.BaseModel;
import dev.isudha.userservice.models.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Session extends BaseModel {
    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private SessionState state;

}
