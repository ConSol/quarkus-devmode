package de.consol.dus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "greeting")
public class Greeting {

  @Id
  @SequenceGenerator(name = "GreetingIdGenerator", sequenceName = "greeting_seq_id", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GreetingIdGenerator")
  @Column(name = "id", nullable = false, unique = true)
  @JsonIgnore
  private Long id;

  @Column(name = "salutation")
  private String salutation;

  @Column(name = "name")
  private String name;

  public Long getId() {
    return id;
  }

  public Greeting setId(Long id) {
    this.id = id;
    return this;
  }

  public String getSalutation() {
    return salutation;
  }

  public Greeting setSalutation(String salutation) {
    this.salutation = salutation;
    return this;
  }

  public String getName() {
    return name;
  }

  public Greeting setName(String name) {
    this.name = name;
    return this;
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (that == null || getClass() != that.getClass()) {
      return false;
    }
    Greeting thatGreeting = (Greeting) that;
    final Long thisId = getId();
    final Long thatId = thatGreeting.getId();
    if (Objects.nonNull(thisId) && Objects.nonNull(thatId)) {
      return Objects.equals(thisId, thatId);
    }
    return Objects.equals(getSalutation(), ((Greeting) that).getSalutation())
        && Objects.equals(getName(), ((Greeting) that).getName());
  }

  @Override
  public int hashCode() {
    if (Objects.nonNull(getId())) {
      return Objects.hash(getId());
    }
    return Objects.hash(getSalutation(), getName());
  }

  @Override
  public String toString() {
    return "Greeting{" +
        "id=" + id +
        ", salutation='" + salutation + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}
