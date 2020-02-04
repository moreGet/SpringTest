package com.example.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
// 클래스 에는 그에 대응하는 User 클래스의 필드들을 추가 합니다.
@ToString(exclude = "customers")
public class User {
	@Id // userName 기본키 지정
	private String userName;
	// JPA와는 관계 없지만 API로 User클래스를 JSON 형식으로 출력할 경우,
	// 암호 필드를 제외하기 위해 이 어노테이션을 붙입니다.
	@JsonIgnore 
	private String encodedPassword;
	@JsonIgnore
	// User와 Customer를 1:N관계로 만들기 위해 이어노테이션을 붙입니다.
	// caseade는 User가 조작한 것들이 Customer에도 적용 됩니다.
	// fetch의 LAZY는 관련된 엔티티의 로드가 지연 됩니다.
	// User 엔티티를 가져올떄는 Customer 엔티티를 가져오지 않습니다.
	// customers 필드에 접속한 시점에 customer 엔티티를 가져옵니다.(SELECT문 실행)
	// 양방향 관련을 위해 mappedBy 속성에 연관 지을 속성의 이름을 지정합니다.
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private List<Customer> customers;
}
