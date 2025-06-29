package com.app.restapi.jpa.entity;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name = "contactdetails")
@Entity
public class ContactDetails implements UserDetails {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String firstName;

	@Column
	private String middleName;

	@Column
	private String lastName;

	@Column(nullable = false)
	private String password;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private Date dob;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles = new HashSet<>();

	@CreationTimestamp
	@Column(updatable = false, name = "created_at")
	private Date createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
		return authorities;
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
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	public Long getId() {
		return id;
	}

	public ContactDetails setId(Long id) {
		this.id = id;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public ContactDetails setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public ContactDetails setEmail(String email) {
		this.email = email;
		return this;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public ContactDetails setRoles(Set<Role> roles) {
		this.roles = roles;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public ContactDetails setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public ContactDetails setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public ContactDetails setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

	public Address getAddress() {
		return address;
	}

	public ContactDetails setAddress(Address address) {
		this.address = address;
		return this;
	}

	public String getMiddleName() {
		return middleName;
	}

	public ContactDetails setMiddleName(String middleName) {
		this.middleName = middleName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public ContactDetails setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public Date getDob() {
		return dob;
	}

	public ContactDetails setDob(Date dob) {
		this.dob = dob;
		return this;
	}
}
