package com.consomiTounsi.entities;

public enum PaymentMethod {
	
	ONLINE, AFTERDELIVERY

}








/*
 * 
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date datePayment;
    private long cardNumber;
    private String cardType;
    @OneToOne(mappedBy = "payment")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Order order; 
 * 
 */
