package MediTrack.Medi.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails{
    @Id
    private String id;
    private String username;
    private String hashedPassword;
    private String userType; // e.g., "Doctor", "Patient"
    private String referenceId; // References the corresponding doctor's or patient's _id

    // Implement UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Add authority based on userType
        if ("Patient".equals(userType) || "Doctor".equals(userType)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userType.toUpperCase()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return hashedPassword;
    }

    @Override
    public String getUsername() {
        return username;
    }
    public String getReferenceId() {return referenceId;}
    @Override
    public boolean isAccountNonExpired() {
        // Implement logic to determine if account is expired. For now, returning true.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Implement logic to determine if account is locked. For now, returning true.
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Implement logic to determine if credentials are expired. For now, returning true.
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Implement logic to determine if account is enabled. For now, returning true.
        return true;
    }
}
