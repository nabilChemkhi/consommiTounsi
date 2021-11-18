package com.consomiTounsi.Repository;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.consomiTounsi.entities.Bill;
import com.consomiTounsi.entities.Order;
import com.consomiTounsi.entities.Users;






@Repository
public interface BillRepository extends JpaRepository<Bill, Long>{
	
	
//	@Query("select b from Bill b where b.order =: order")
//	public Bill findBillByOrder(@Param("order")Order order);
//	
	
	@Query("Select "
			+ "DISTINCT B from Bill B "
			+ "join B.order ord "
			+ "join ord.user us "
			+ "where us=:user")
    public List<Bill> findBillByUser(@Param("user") Users user);
	

	























//@Query(value = "SELECT  NEW tn.esprit.spring.Model.lignecommandeproduit(f.date,p.nomProduit,l.quantity,p.prix,l.quantity*p.prix,u.firstName,c.montant) FROM LigneCommande l join l.commande c  join l.produit p join c.factureid f join c.idUser u  WHERE c.idUser.id=:idc ")
//public List<lignecommandeproduit> FactureParIdUser(@Param("idc")long i);
}