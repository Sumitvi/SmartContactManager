package com.scm.SmartContactManager.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String picture;

    @Version
    private Integer version = 0;
    @Lob
    private String description;

    public boolean isFaviourite() {
        return faviourite;
    }

    public void setFaviourite(boolean faviourite) {
        this.faviourite = faviourite;
    }

    private boolean faviourite = false;
    private String websiteLink;
    private String LinkedInList;

    private String cloudinaryImagePublicId;
    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SocialLink> links = new ArrayList<>();


}
