package de.consol.dus;

public class Greeting {
  private String salutation;
  private String name;

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
}
