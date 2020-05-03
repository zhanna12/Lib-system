package kz.iitu.library.models;


import javax.persistence.*;
import java.util.Collection;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"books", "roles"})
@Table(name = "users")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Book> books;

@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name="book_user",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
private List<Book> books;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
private List<Role> roles;

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name=name;
//    }
//
//
//    public Long getId() {
//        return id;
//    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
//    public void setUsername(String username) {
//        this.name = username;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public List<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<Role> roles) {
//        this.roles = roles;
//    }
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + name +
//                '}';
//    }
}
