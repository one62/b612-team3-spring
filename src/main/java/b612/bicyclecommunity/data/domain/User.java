package b612.bicyclecommunity.data.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	private String username;
	private String password;

	private String provider; // oauth2 종류(kakao)
	private String providerId; // oauth2 고유 id

	private LocalDateTime createDate;
	
	@Builder
	public User(String username, String password, String provider, String providerId, LocalDateTime createDate) {
		this.username = username;
		this.password = password;
		this.provider = provider;
		this.providerId = providerId;
		this.createDate = createDate;
	}
}
