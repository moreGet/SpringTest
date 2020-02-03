package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity	// JPA 개체(Entity)임을 표시 합니다.

// @Table 어노테이션을 붙여 엔티티에 대응하는 테이블 이름을 지정합니다.
// 기본적으로 테이블 이름을 클래스 이름과 동일하게 맞추는게 컨벤션이지만 
// 여기선 다르게 설정 합니다.
@Table(name = "customers")

// class파일을 생성할때 각 필드의 setter/getter, toString, equals, hashCode 메소드가
// 생성되므로스코드가 간결해진다. 아래 필드는 final형태라 setter/getter가 생성되지 않는다.
@Data

// JPA 명세에 따르면 엔티티 클래스에는 인자를 받지 않는 기본 생성자를 만들어야 합니다.
// 롬복으로 기본 생성자를 만들려면 아래 어노테이션을 추가 합니다.
@NoArgsConstructor

// JPA와는 관계 없지만 쉽게 프로그래밍을 할 수 있게 롬복이 기본 생성자 외에 전체 필드를
// 인자로 받는 생성자를 만들도록 설정 합니다.
@AllArgsConstructor
public class Customer {
	@Id // 엔티티의 기본 키인 필드에 @Id 어노테이션을 붙입니다.
	@GeneratedValue // DB가 기본 키 번호를 자동으로 매기도록 이 어노테이션을 붙여 지정합니다.
	private Integer id;
	
	// 필드에 이 어노테이션을 붙여서 DB의 대응하는 컬럼의 이름이나 제약 조건을 설정합니다.
	// 여기서는 NotNull 제약조건을 설정 합니다.
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
}