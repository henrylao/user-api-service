package com.hcl.spring.jpa.sql.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "users_table")
//@Data   // useful annotation to reduce boilerplate getter/setter
public class User {
    // -----------------------------------------
    //    ATTRIBUTES
    // -----------------------------------------
    //  MINIMUM SPEC
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="user_id")
    private long userId;

    @NonNull
//    @Column(name = "email")
    private String email;

    @NonNull
    @Column(name = "password")
    private String password;

    @NonNull
    @Column(name = "is_admin")
    private boolean isAdmin;

    @NonNull
    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    //	//    The target destination supplied by Destination service
//	@Column(name = "destination")
//	private Destination destination;
//
//	//    List of destinations for the specific destination supplied by the RecommendationService
//	@Column(name = "recommendations")
//	private Object recommendations;
//
//	//    List of reviews for the destination supplied by DestinationService
//	@Column(name = "reviews")
//	private Object reviews;
//

    // -----------------------------------------
    //    CONSTRUCTORS
    // -----------------------------------------
    public User() {

    }

    public User(@NonNull String email, @NonNull String password, boolean isAdmin) {
        this(email, password, false, "");
    }

    public User(@NonNull String email, @NonNull String password, boolean isAdmin, @NonNull String username) {
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.username = username;
    }

    // -----------------------------------------
    //    GETTERS / SETTERS
    // -----------------------------------------
    //  MINIMUM SPEC

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public long getUserId() {
        return userId;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", username='" + username + '\'' +
                '}';
    }
}

//	// -----------------------------------------
// SCRATCH
//import javax.persistence.*;
//import javax.print.attribute.standard.Destination;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "users")
//public class User {
//	// -----------------------------------------
//	//    ATTRIBUTES
//	// -----------------------------------------
//	//  MINIMUM SPEC
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private long id;
//
//	@Column(name = "email")
//	private String email;
//
//	@Column(name = "password")
//	private String password;
//
//	@Column(name = "is_admin")
//	private boolean isAdmin;
//
//	//    The target destination supplied by Destination service
//	@Column(name = "destination")
//	private Destination destination;
//
//	//    List of destinations for the specific destination supplied by the RecommendationService
//	@Column(name = "recommendations")
//	private Object recommendations;
//
//	//    List of reviews for the destination supplied by DestinationService
//	@Column(name = "reviews")
//	private Object reviews;
//
//	@Column(name = "username")
//	private String username;
//
//	//  STRETCH
//
//	@Column(name = "first_name")
//	private String firstName;
//
//	@Column(name = "last_name")
//	private String lastName;
//
//	@Column(name = "created_at")
//	private LocalDateTime createAt;
//
//	//  ARTIFACTS FROM ORIGINAL
//
//	@Column(name = "title")
//	private String title;
//
//	@Column(name = "description")
//	private String description;
//
//	@Column(name = "published")
//	private boolean published;
//
//	// -----------------------------------------
//	//    CONSTRUCTORS
//	// -----------------------------------------
//	public User() {
//
//	}
//
//	public User(long id, String email, String password,
//				boolean isAdmin,
////                Destination destination,
////                Object recommendations, Object reviews,
//				String username, String firstName, String lastName,
//				LocalDateTime createAt, String title, String description,
//				boolean published) {
//		this.id = id;
//		this.email = email;
//		this.password = password;
//		this.isAdmin = isAdmin;
////        this.destination = destination;
////        this.recommendations = recommendations;
////        this.reviews = reviews;
//		this.username = username;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.createAt = createAt;
//		this.title = title;
//		this.description = description;
//		this.published = published;
//	}
//
//	public User(String title, String description, boolean published) {
//		this.title = title;
//		this.description = description;
//		this.published = published;
//	}
//
//	// -----------------------------------------
//	//    GETTERS / SETTERS
//	// -----------------------------------------
//	//  MINIMUM SPEC
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public boolean isAdmin() {
//		return isAdmin;
//	}
//
//	public void setAdmin(boolean admin) {
//		isAdmin = admin;
//	}
//
//	public Destination getDestination() {
//		return destination;
//	}
//
//	public void setDestination(Destination destination) {
//		this.destination = destination;
//	}
//
//	public Object getRecommendations() {
//		return recommendations;
//	}
//
//	public void setRecommendations(Object recommendations) {
//		this.recommendations = recommendations;
//	}
//
//	public Object getReviews() {
//		return reviews;
//	}
//
//	public void setReviews(Object reviews) {
//		this.reviews = reviews;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//	public LocalDateTime getCreateAt() {
//		return createAt;
//	}
//
//	public void setCreateAt(LocalDateTime createAt) {
//		this.createAt = createAt;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public boolean isPublished() {
//		return published;
//	}
//
//	public void setPublished(boolean isPublished) {
//		this.published = isPublished;
//	}
//
//	@Override
//	public String toString() {
//		return "User{" +
//				"id=" + id +
//				", email='" + email + '\'' +
//				", password='" + password + '\'' +
//				", isAdmin=" + isAdmin +
////                ", destination=" + destination +
////                ", recommendations=" + recommendations +
////                ", reviews=" + reviews +
//				", username='" + username + '\'' +
//				", firstName='" + firstName + '\'' +
//				", lastName='" + lastName + '\'' +
//				", createAt=" + createAt +
//				", title='" + title + '\'' +
//				", description='" + description + '\'' +
//				", published=" + published +
//				'}';
//	}
//
//}
