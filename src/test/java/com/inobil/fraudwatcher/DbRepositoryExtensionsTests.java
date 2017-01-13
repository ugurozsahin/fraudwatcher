package com.inobil.fraudwatcher;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.inobil.fraudwatcher.dal.DbRepositoryExtensions;
import com.inobil.fraudwatcher.entity.Transaction;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DbRepositoryExtensionsTests {

	@Autowired
    private TestEntityManager entityManager;

   private DbRepositoryExtensions repository;
    
	@Test
	public void Test_Should_DbRepoExtensions_Select_TransactionCount_Bigger_Than_Zero_When_Check_Return_True() {
		Transaction transaction = new Transaction();
		transaction.setApplicationId("test");
		transaction.setEmail("test@mail.com");
		transaction.setIsPaid(true);
		transaction.setNameSurname("test test");
		transaction.setOrderId("testorderid");
		transaction.setReceiverPhone("01234567");
		transaction.setSenderPhone("76543210");
		this.entityManager.persist(transaction);
		Boolean result = repository.Check("Select Count(*) > 0 From \"transaction\"");
		assertThat(result).isEqualTo(true);
	}

	/**
	 * @param repository the repository to set
	 */
    @Autowired
    public void setRepository(DbRepositoryExtensions repository) {
		this.repository = repository;
	}

}
