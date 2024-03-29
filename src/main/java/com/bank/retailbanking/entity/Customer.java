package com.bank.retailbanking.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	private LocalDate dateOfBirth;
	@NotNull
	private Long mobileNo;
	@NotNull
	private Double salary;
	@NotNull
	@Email
	private String emailId;
	@NotNull
	private String address;
	@NotNull
	private String userName;
	@NotNull
	private String password;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="role_id")
	private Role role;

	public static String fullName(String firstName, String lastName) {
		return firstName.concat(" " + lastName);
	}

}
