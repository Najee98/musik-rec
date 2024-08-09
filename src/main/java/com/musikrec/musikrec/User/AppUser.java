package com.musikrec.musikrec.User;

import com.musikrec.musikrec.Models.Like;
import com.musikrec.musikrec.Models.Playlist;
import com.musikrec.musikrec.Models.Song;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
//build the object using the builder design pattern
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    //The Relationship Between users And songs://
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
                name = "history",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<Song> userSongs = new ArrayList<>();


    //The Relationship Between users And likes://
    @OneToMany(mappedBy = "appUser")
    private List<Like> likes = new ArrayList<>();


    //The Relationship Between users And playlist://
    @OneToMany(mappedBy = "appUser")
    private List<Playlist> playlists = new ArrayList<>();

}
