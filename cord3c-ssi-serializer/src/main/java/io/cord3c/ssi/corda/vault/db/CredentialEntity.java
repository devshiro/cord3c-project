package io.cord3c.ssi.corda.vault.db;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@JsonApiResource(type = "credential", resourcePath = "credential")
@Entity
@Table(name = "c3c_credential")
@FieldNameConstants
public class CredentialEntity {

	@Id
	@JsonApiId
	private String id;

	@Lob
	private String jsonValue;

	@ElementCollection
	@CollectionTable(name = "c3c_credential_type")
	private List<String> types;

	@ElementCollection
	@CollectionTable(name = "c3c_credential_context")
	private List<String> contexts;

	private String issuer;

	private Instant issuanceDate;

	private Instant expirationDate;

	// FIXME @MapKeyColumn(name = "name")
	@OneToMany(mappedBy = "credential", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ClaimEntity> claims;

	private ProofEmbeddable proof;

}
